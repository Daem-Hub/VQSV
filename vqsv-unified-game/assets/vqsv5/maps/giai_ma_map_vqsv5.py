"""Giải mã map của vqsv5 (PKM - Cầu Vồng).
Định dạng suy ra từ code (at.java / ad.java):
 area.dat : u16BE len | [n][map->tileset ...] [k]{ [len][ảnh...] }
 N.data   : u16BE len | [count+100 (signed byte)] { [len+100] u16LE x len }   -> (img,sx,sy,w,h)
 N.mid    : 2 byte bỏ | u32LE màu nền | n | n byte bỏ | n byte rộng | n byte cao
            rồi 3 phần: type(1=nền, 2=lưới va chạm, 3=lớp phủ trên nhân vật; 0xFF=trống)
            u32LE số mục | mỗi mục: x(1) y(1) giá trị s16LE ; y lưu từ dưới lên
 giá trị: >>3 = số ô trong bảng .data, &7 = kiểu lật/xoay (bảng 0,5,3,6,2,4,1,7 -> MIDP)
"""
import struct, json, os, sys
from PIL import Image
ROOT = sys.argv[1] if len(sys.argv) > 1 else '/home/claude/vqsv5/data/map'
OUT  = sys.argv[2] if len(sys.argv) > 2 else '/home/claude/out/vqsv5/maps'
T = 20  # kích thước ô

def s8(b): return b-256 if b>127 else b
def s16(lo,hi): v=lo|(hi<<8); return v-65536 if v>32767 else v

def payload(path):
    b=open(path,'rb').read(); L=(b[0]<<8)|b[1]; return b[2:2+L]

def parse_area():
    p=payload(f'{ROOT}/area.dat'); i=0
    n=p[i]; i+=1; mp=list(p[i:i+n]); i+=n
    k=p[i]; i+=1; sets=[]
    for _ in range(k):
        l=p[i]; i+=1; sets.append(list(p[i:i+l])); i+=l
    return mp, sets

def parse_defs(ts):
    p=payload(f'{ROOT}/{ts}.data'); i=0
    cnt=s8(p[i])+100; i+=1; defs=[]
    for _ in range(cnt):
        ln=s8(p[i])+100; i+=1; v=[]
        for _ in range(ln):
            v.append(s16(p[i],p[i+1])); i+=2
        defs.append(v)
    return defs   # (img, sx, sy, w, h, ...)

def parse_map(mid):
    b=open(f'{ROOT}/{mid}.mid','rb').read(); i=2
    bg=struct.unpack_from('<I',b,i)[0]; i+=4
    n=b[i]; i+=1; i+=n
    w=b[i]; i+=n; h=b[i]; i+=n
    layers={}
    for _ in range(3):
        if i>=len(b): break
        t=b[i]; i+=1
        if t==0xFF: continue
        cnt=struct.unpack_from('<I',b,i)[0]; i+=4
        items=[]
        for _ in range(cnt):
            x=b[i]; y=h-1-b[i+1]; v=s16(b[i+2],b[i+3]); i+=4
            items.append((x,y,v))
        layers[t]=items
    return dict(bg=bg,w=w,h=h,layers=layers,rest=len(b)-i)

def transform(im, code):
    # MIDP: 0 NONE,1 MIRROR_ROT180,2 MIRROR,3 ROT180,4 MIRROR_ROT270,5 ROT90,6 ROT270,7 MIRROR_ROT90
    F=Image
    if code==0: return im
    if code==1: return im.transpose(F.FLIP_TOP_BOTTOM)
    if code==2: return im.transpose(F.FLIP_LEFT_RIGHT)
    if code==3: return im.transpose(F.ROTATE_180)
    if code==5: return im.transpose(F.ROTATE_270)
    if code==6: return im.transpose(F.ROTATE_90)
    if code==7: return im.transpose(F.FLIP_LEFT_RIGHT).transpose(F.ROTATE_270)
    if code==4: return im.transpose(F.FLIP_LEFT_RIGHT).transpose(F.ROTATE_90)
TBL=[0,5,3,6,2,4,1,7]

def render(m, defs, sheet, layers=(1,3)):
    W,H=m['w']*T, m['h']*T
    r,g,bb=(m['bg']>>16)&255,(m['bg']>>8)&255,m['bg']&255
    img=Image.new('RGBA',(W,H),(r,g,bb,255))
    for lt in layers:
        for x,y,v in m['layers'].get(lt,[]):
            if v<0: continue
            d=defs[v>>3]; si,sx,sy,w,h=d[:5]
            tile=sheet.crop((sx,sy,sx+w,sy+h)).convert('RGBA')
            tile=transform(tile,TBL[v&7])
            px,py=x*T, (y+1)*T-tile.height   # neo BOTTOM|LEFT tại đáy ô
            img.alpha_composite(tile,(px,py)) if 0<=px<W and 0<=py<H and px+tile.width<=W and py+tile.height<=H else img.paste(tile,(px,py),tile)
    return img

if __name__=='__main__':
    os.makedirs(OUT+'/png',exist_ok=True); os.makedirs(OUT+'/json',exist_ok=True)
    mp,sets=parse_area(); cache={}
    for mid,ts in enumerate(mp):
        if ts not in cache:
            cache[ts]=(parse_defs(ts), Image.open(f'{ROOT}/{sets[ts][0]}.png').convert('RGBA'))
        defs,sheet=cache[ts]
        m=parse_map(mid)
        render(m,defs,sheet).convert('RGB').save(f'{OUT}/png/map_{mid:02d}.png')
        grid=[[0]*m['w'] for _ in range(m['h'])]
        for x,y,v in m['layers'].get(2,[]):
            if 0<=x<m['w'] and 0<=y<m['h']: grid[y][x]=v>>3
        json.dump(dict(map_id=mid,tileset=ts,width=m['w'],height=m['h'],tile=T,
            background=f"#{m['bg']&0xFFFFFF:06x}",
            background_layer=[dict(x=x,y=y,tile=v>>3,flip=v&7) for x,y,v in m['layers'].get(1,[]) if v>=0],
            overlay_layer=[dict(x=x,y=y,tile=v>>3,flip=v&7) for x,y,v in m['layers'].get(3,[]) if v>=0],
            collision_grid=grid, unparsed_bytes=m['rest']),
            open(f'{OUT}/json/map_{mid:02d}.json','w'),separators=(',',':'))
        print(mid,'tileset',ts,f"{m['w']}x{m['h']}",{k:len(v) for k,v in m['layers'].items()},'dư',m['rest'])
