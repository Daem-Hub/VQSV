from math import cos, sin, pi
from pathlib import Path
from PIL import Image, ImageDraw, ImageFont

ROOT = Path(__file__).resolve().parents[1]
OUT = ROOT / "asset_review" / "vqsv_unified_world_overview_v1.png"
W, H = 1600, 1000
im = Image.new("RGB", (W, H))
p = im.load()
for y in range(H):
    t = y / H
    c = (int(18 - 10*t), int(44 - 20*t), int(71 - 22*t))
    for x in range(W):
        p[x, y] = c
d = ImageDraw.Draw(im, "RGBA")
fontdir = Path("C:/Windows/Fonts")
regular = str(fontdir / "segoeui.ttf")
bold = str(fontdir / "segoeuib.ttf")
def F(size, weight=False): return ImageFont.truetype(bold if weight else regular, size)

# Quiet wave marks in the sea.
for y in range(160, 980, 38):
    for x in range((y // 38 % 2) * 28, W, 84):
        d.arc((x, y, x+42, y+14), 190, 350, fill=(100, 171, 194, 32), width=2)

d.text((58, 34), "VQSV UNIFIED · WORLD OVERVIEW · DỰ THẢO V1", font=F(18, True), fill="#efcb7b")
d.text((58, 65), "THẾ GIỚI MỚI TỪ HAI NGUỒN GAME", font=F(36, True), fill="#fff7df")
d.text((58, 112), "Bố cục vùng theo cốt truyện mới · tuyến nối là đề xuất thiết kế, không phải tọa độ map gốc", font=F(18), fill="#d4dfdc")

# Ryan landmass.
ryan = [(104,278),(143,224),(239,208),(338,185),(413,202),(461,186),(527,214),(594,199),(710,227),(783,270),(833,334),(817,413),(852,466),(813,523),(779,601),(726,641),(669,633),(622,699),(531,704),(454,676),(381,709),(313,673),(264,633),(200,639),(154,588),(119,522),(89,463),(102,407),(77,356)]
d.polygon(ryan, fill=(116, 157, 103), outline=(215, 221, 181), width=4)
d.line(ryan+[ryan[0]], fill=(215,221,181), width=4, joint="curve")
# Forest, river and meadow strokes.
d.line([(155,315),(220,290),(290,325),(360,334),(430,318),(500,338),(575,330),(650,353),(745,385)], fill=(53,105,74,180), width=21, joint="curve")
d.line([(210,465),(310,444),(421,475),(520,462),(615,464),(704,483),(753,500)], fill=(54,101,77,170), width=13, joint="curve")
d.line([(576,580),(625,548),(688,562)], fill=(138,199,204,190), width=9, joint="curve")
d.text((122, 162), "LỤC ĐỊA RYAN · CHƯƠNG 1", font=F(20,True), fill="#f3e4a5")

def route(points, color="#f2d58a", width=5, dash=14):
    for (x1,y1),(x2,y2) in zip(points, points[1:]):
        length=((x2-x1)**2+(y2-y1)**2)**.5
        n=max(1,int(length/(dash*1.65)))
        for i in range(n):
            a=i/n; b=min(1,(i+.68)/n)
            d.line((x1+(x2-x1)*a,y1+(y2-y1)*a,x1+(x2-x1)*b,y1+(y2-y1)*b), fill=color, width=width)
    x1,y1=points[-2]; x2,y2=points[-1]; ang=__import__('math').atan2(y2-y1,x2-x1)
    tip=(x2,y2); left=(x2-17*cos(ang)+8*sin(ang),y2-17*sin(ang)-8*cos(ang)); right=(x2-17*cos(ang)-8*sin(ang),y2-17*sin(ang)+8*cos(ang))
    d.polygon([tip,left,right], fill=color)

def marker(cx,cy,r,color,icon,label,sub, tx=None, ty=None, label_size=20):
    d.ellipse((cx-r+5,cy-r+7,cx+r+5,cy+r+7),fill=(0,0,0,70))
    d.ellipse((cx-r,cy-r,cx+r,cy+r),fill=color,outline=(255,242,209,230),width=3)
    d.text((cx,cy-1),icon,font=F(max(22,r)),fill="#fff8e2",anchor="mm",stroke_width=1,stroke_fill=(27,42,46,120))
    if tx is None: tx=cx-58
    if ty is None: ty=cy+r+10
    d.text((tx,ty),label,font=F(label_size,True),fill="#fff7df",stroke_width=2,stroke_fill=(14,32,45,170))
    if sub: d.text((tx,ty+label_size+3),sub,font=F(15),fill="#e0e5d9",stroke_width=1,stroke_fill=(14,32,45,150))

marker(264,402,42,(64,105,73),"1","Rừng Rậm","Phong Lôi",198,451)
marker(260,576,40,(193,112,66),"2","Hoang Mạc","Xích Diễm",190,622)
marker(506,293,40,(91,157,177),"3","Băng Nguyên","Vĩnh Cửu",443,341)
marker(616,482,40,(46,124,126),"4","Biển Đầm","Thẳm Sâu",554,529)
marker(461,593,25,(229,119,74),"5","Hỏa Sơn Bất Diệt","",395,628,16)
marker(691,347,27,(188,159,102),"6","Tháp Thiên Không","",635,385,16)

route([(390,437),(330,420),(264,402),(335,344),(425,300),(506,293)])
route([(506,293),(575,313),(625,326),(691,347)])
route([(691,347),(706,407),(666,455),(616,482)])
route([(616,482),(563,535),(514,572),(461,593)])
route([(461,593),(388,618),(326,607),(260,576)])
d.text((515,200),"TUYẾN CHƯƠNG 1",font=F(17,True),fill="#f2d58a")
d.text((515,223),"Bốn nguyên tố hội tụ tại Tháp Thiên Không",font=F(14),fill="#eff0db")
marker(390,437,23,(210,157,77),"A","Làng biên giới","",323,467,15)

# Playable opening callout.
d.rounded_rectangle((108,710,393,817),radius=16,fill=(17,39,60,242),outline=(242,213,138,255),width=3)
d.ellipse((128,735,154,761),fill="#f2d58a"); d.polygon([(129,781),(141,764),(153,781)],fill="#ea9857")
d.text((169,731),"LÁT CHƠI THỬ ĐANG LÀM",font=F(13,True),fill="#f2d58a")
d.text((169,755),"Làng biên giới sương mù",font=F(19,True),fill="#fff7df")
d.text((128,789),"Mở đầu Kael & Lôi Miêu · map hiện tại",font=F(14),fill="#d1dde0")

# Ocean journey and Atlantis.
route([(795,330),(867,314),(926,348),(1000,378),(1075,386),(1156,435)],"#a8e4df",5)
d.text((852,291),"CHƯƠNG 2",font=F(16,True),fill="#a8e4df"); d.text((852,313),"Hải Vực Vô Tận",font=F(15),fill="#e1efec")
d.line([(825,420),(900,396),(986,428),(1068,414),(1144,449)],fill=(108,189,208,100),width=3)
d.line([(842,455),(918,431),(1000,463),(1080,450),(1162,483)],fill=(108,189,208,100),width=3)
d.polygon([(944,653),(971,603),(1023,590),(1062,601),(1100,573),(1153,608),(1210,625),(1220,680),(1182,715),(1128,711),(1074,743),(1018,716),(966,725)],fill=(38,96,122),outline=(143,213,223),width=3)
d.polygon([(990,665),(1002,623),(1045,619),(1081,591),(1121,628),(1168,635),(1178,678),(1146,702),(1109,685),(1068,714),(1034,684)],fill=(62,159,159),outline=(177,232,215),width=2)
d.polygon([(1037,661),(1063,619),(1090,661)],fill=(180,217,194),outline=(232,240,208)); d.polygon([(1080,670),(1104,620),(1135,670)],fill=(180,217,194),outline=(232,240,208))
d.text((1002,762),"Vương Quốc Chìm Atlantis",font=F(20,True),fill="#fff7df",stroke_width=2,stroke_fill=(14,32,45,170))
d.text((1025,787),"Lyra · Thương Hải Thần Cự Kình",font=F(15),fill="#e0e5d9")
route([(1135,585),(1174,518),(1199,455),(1235,362)],"#a8e4df",5)
d.text((1125,489),"CHƯƠNG 3",font=F(16,True),fill="#a8e4df")

# Floating Aethelgard.
aeth=[(1191,275),(1238,220),(1316,245),(1367,206),(1437,238),(1501,249),(1515,308),(1498,355),(1446,358),(1399,393),(1347,366),(1280,400),(1226,366),(1172,337)]
d.polygon(aeth,fill=(155,147,192),outline=(238,230,255),width=4)
d.polygon([(1228,284),(1270,309),(1312,295),(1343,327),(1382,310),(1417,330),(1461,293),(1497,273)],fill=(75,70,116))
marker(1285,297,20,(185,111,83),"▲","","",label_size=16)
d.text((1222,423),"Lục Địa Bay Aethelgard",font=F(20,True),fill="#fff7df",stroke_width=2,stroke_fill=(14,32,45,170))
d.text((1220,449),"Miệng Núi Lửa Hắc Diệu · Boran",font=F(14),fill="#e0e5d9")
marker(1433,298,15,(207,198,232),"S","","",label_size=15)
d.text((1374,391),"Tầng Mây Khởi Nguyên",font=F(15,True),fill="#fff7df",stroke_width=2,stroke_fill=(14,32,45,170))
d.text((1395,412),"Cung điện pha lê · Seraphina",font=F(13),fill="#e0e5d9")

# Red Moon finale.
route([(1447,231),(1466,189),(1491,137),(1505,98)])
d.ellipse((1428,40,1538,150),fill=(159,55,83),outline=(255,177,154),width=4)
d.ellipse((1437,48,1529,141),fill=(223,107,97))
d.ellipse((1445,62,1520,133),fill=(242,132,101))
d.ellipse((1448,55,1518,99),outline=(236,189,145),width=4)
d.ellipse((1432,72,1533,117),outline=(236,189,145),width=4)
d.text((1238,55),"CHƯƠNG 5",font=F(14,True),fill="#ffb6a2")
d.text((1238,76),"Mặt Trăng Đỏ",font=F(19,True),fill="#fff7df",stroke_width=2,stroke_fill=(14,32,45,170))
d.text((1238,100),"Vành đai cơ khí · Thực Thể Thứ 0",font=F(12),fill="#e0e5d9")
d.line([(1345,368),(1351,405),(1345,461),(1325,516),(1284,565),(1234,599)],fill=(242,213,138,200),width=3)
d.text((1260,479),"Cột Mốc Vọng Thiên",font=F(13,True),fill="#f2d58a")

# Legend and provenance note.
d.rounded_rectangle((1085,846,1545,951),radius=15,fill=(13,32,50,235),outline=(64,98,122,255),width=2)
d.line((1108,876,1192,876),fill="#f2d58a",width=4); d.polygon([(1192,876),(1181,869),(1181,883)],fill="#f2d58a")
d.text((1206,866),"Tuyến truyện / di chuyển dự kiến",font=F(14),fill="#dbe5e3")
d.line((1108,909,1192,909),fill="#a8e4df",width=4); d.polygon([(1192,909),(1181,902),(1181,916)],fill="#a8e4df")
d.text((1206,899),"Hải trình và chuyển vùng",font=F(14),fill="#dbe5e3")
d.text((1108,927),"Vị trí tương đối · chưa phải tọa độ playable",font=F(12),fill="#c1d0d5")
d.text((58,907),"VQSV5: 68 map đã có ảnh và dữ liệu. VQSV6: 94 file map đã giải mã cấu trúc.",font=F(14),fill="#c1d0d5")
d.text((58,933),"Sơ đồ theo cốt truyện đã chốt; chưa ghép pixel-to-pixel toàn bộ map gốc hoặc xác minh ID tile VQSV6.",font=F(14),fill="#c1d0d5")

im.save(OUT)
print(f"Wrote {OUT}")
