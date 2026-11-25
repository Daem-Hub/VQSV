"""Prepare native-grid illustrations for the field renderer."""
from pathlib import Path

from PIL import Image

ART = Path(__file__).resolve().parents[1] / 'prototype' / 'assets'


def pixel_scene(image, size, colors=80):
    """Keep the authored light and shade in discrete clusters without speckled dithering."""
    native = image.convert('RGB').resize(size, Image.Resampling.LANCZOS)
    return native.quantize(colors=colors, dither=Image.Dither.NONE)


def pixel_sprite(image, size, colors):
    native = image.convert('RGBA').resize(size, Image.Resampling.LANCZOS)
    alpha = native.getchannel('A').point(lambda a: 255 if a >= 152 else 144 if a >= 86 else 0)
    # Learn the palette from visible pixels so padding never consumes the shade budget.
    rgb = native.convert('RGB')
    visible = [color[:3] for count, color in native.getcolors(native.width*native.height)
               if color[3] >= 86 for _ in range(count)]
    swatch = Image.new('RGB', (len(visible), 1))
    swatch.putdata(visible)
    palette = swatch.quantize(colors=colors)
    result = rgb.quantize(palette=palette, dither=Image.Dither.NONE).convert('RGBA')
    result.putalpha(alpha)
    return result


def build_kael_sheet():
    source = Image.open(ART / 'characters' / 'kael_pixel_v2.png')
    # Crop each pose independently. The source sheet has hand-aligned, uneven rows.
    native = Image.new('RGBA', (128, 160))
    for row, top in enumerate((50, 370, 690, 1020)):
        for column in range(4):
            left = 50 + column * 260
            pose = source.crop((left, top, left+260, top+330)).resize((32, 40), Image.Resampling.LANCZOS)
            native.paste(pose, (column*32, row*40))
    pixel_sprite(native, native.size, 44).save(ART / 'characters' / 'kael_field.png', optimize=True)


def build_kael_sheet_hd(scale=4):
    """The same 16 poses at 4× (128 × 160 per frame) for the high-resolution field canvas: the 32 × 40 sheet lost the
    face, buckle and scarf detail. Plain Lanczos keeps the source's own shading; alpha is snapped so edges stay crisp."""
    source = Image.open(ART / 'characters' / 'kael_pixel_v2.png').convert('RGBA')
    fw, fh = 32 * scale, 40 * scale
    sheet = Image.new('RGBA', (fw * 4, fh * 4))
    for row, top in enumerate((50, 370, 690, 1020)):
        for column in range(4):
            left = 50 + column * 260
            pose = source.crop((left, top, left+260, top+330)).resize((fw, fh), Image.Resampling.LANCZOS)
            alpha = pose.getchannel('A').point(lambda a: 255 if a >= 128 else 0)
            pose.putalpha(alpha)
            sheet.paste(pose, (column*fw, row*fh))
    sheet.save(ART / 'characters' / 'kael_field_hd.webp', lossless=True, method=6)


if __name__ == '__main__':
    build_kael_sheet()
    build_kael_sheet_hd()
