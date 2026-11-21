"""Paint the southern village extension around the established village scene."""
from pathlib import Path
from random import Random
from PIL import Image, ImageDraw, ImageOps
from village_south import read_village_south

PROTO = Path(__file__).resolve().parents[1] / "prototype"
TRAILS = PROTO / "assets/trails"
TILE = 20


def paint():
    south = read_village_south(PROTO / "data")
    old = Image.open(TRAILS / "slice01-border-village.webp").convert("RGB")
    width, height = old.width, south["height"] * TILE
    image = Image.new("RGB", (width, height))
    grass = old.crop((0,0,width,136))
    for y in range(old.height, height, grass.height * 2):
        image.paste(grass,(0,y))
        image.paste(ImageOps.flip(grass),(0,y+grass.height))
    image.paste(old,(0,0))
    draw = ImageDraw.Draw(image)
    random = Random("village south")

    for trail in south["trails"]:
        points = [((x+.5)*TILE,(y+.5)*TILE) for x,y in trail["points"] if y >= 18]
        if len(points) != len(trail["points"]):
            points.insert(0, (points[0][0], old.height))
        wide = round(trail["width"] * TILE)
        draw.line(points,fill="#877149",width=wide+5,joint="curve")
        draw.line(points,fill="#bd9d67",width=wide,joint="curve")
        draw.line(points,fill="#ccb27d",width=wide-8,joint="curve")

    for x,y,w,h in south["gardenBeds"]:
        left, top = x*TILE, y*TILE
        draw.rounded_rectangle((left,top,left+w*TILE-3,top+h*TILE-3), radius=5, fill="#755839", outline="#c3a06a",width=3)
        for gx in range(left+9,left+w*TILE-6,12):
            draw.line((gx,top+5,gx,top+h*TILE-8),fill="#977651",width=4)
            for gy in range(top+11,top+h*TILE-7,14):
                draw.ellipse((gx-3,gy-3,gx+5,gy+4), fill="#496043")
                draw.rectangle((gx-1,gy-4,gx+1,gy+1), fill="#aebd62")
                draw.rectangle((gx+1,gy,gx+3,gy+2), fill="#d9ad75")

    for water in south["water"]:
        x,y,w,h = (water[k]*TILE for k in ("x","y","width","height"))
        draw.rectangle((x,y,x+w-1,y+h-1),fill="#537b6b")
        draw.rectangle((x,y+6,x+w-1,y+h-8),fill="#408b91")
        draw.rectangle((x,y+14,x+w-1,y+h-15),fill="#3e7683")
        for _ in range(34):
            px,py=x+random.randrange(w-12),y+random.randrange(12,h-12)
            draw.line((px,py,px+random.randrange(3,12),py),fill=random.choice(("#5a9d9c","#73ada0","#50969a")))
    # The bridge is the four walkable columns between the two water collision rectangles.
    west,east = south["water"]
    left,right = (west["x"]+west["width"])*TILE, east["x"]*TILE-1
    top,bottom = west["y"]*TILE, (west["y"]+west["height"])*TILE-1
    draw.rectangle((left,top,right,bottom),fill="#8e683e")
    for y in range(top+1,bottom,6):
        draw.line((left+3,y,right-3,y),fill="#d2ae6a",width=3)
    for x in (left,right-3):
        draw.rectangle((x,top,x+3,bottom),fill="#5c4e3e")
        for y in (top,top+25,top+50,bottom-4):
            draw.rectangle((x-1,y,x+5,y+5),fill="#bd985b",outline="#66533c")

    for _ in range(3200):
        x,y=random.randrange(width),random.randrange(old.height,height)
        if image.getpixel((x,y)) == (204,178,125):
            draw.point((x,y),fill=random.choice(("#d6be89","#c2a471","#dbca9b")))
    image.paste(old,(0,0))

    # Feather the road into the grass at the old southern edge.
    for y in range(old.height,old.height+20):
        color = Image.blend(Image.new("RGB",(1,1),"#739346"),Image.new("RGB",(1,1),"#ccb27d"),(y-old.height)/20).getpixel((0,0))
        draw.line((194,y,225,y),fill=color)
    path = TRAILS / "village-south.webp"
    image.save(path,quality=91,method=6)
    chart = image.convert("RGBA")
    chart.paste(Image.open(TRAILS / "slice01-border-village-chart.png").convert("RGBA"),(0,0))
    for kind,x,y in sorted(south["props"],key=lambda prop:prop[2]):
        asset = "stones" if kind == "stone" else kind
        sprite = Image.open(PROTO / "assets/scenery" / f"{asset}.png").convert("RGBA")
        chart.alpha_composite(sprite,(int((x+.5)*TILE-sprite.width/2),(y+1)*TILE-sprite.height))
    chart.convert("RGB").save(TRAILS / "village-south-chart.webp",quality=88,method=6)


if __name__ == "__main__":
    paint()
