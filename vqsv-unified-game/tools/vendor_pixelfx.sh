#!/bin/sh
# Copies the pixelfx core and the effects the battle uses into prototype/vendor/pixelfx (MIT, see LICENSE and NOTICE.md there).
# Usage: tools/vendor_pixelfx.sh /path/to/pixelfx-checkout
set -e
SRC="${1:?path to a patch-gg/pixelfx checkout}"
DST="$(cd "$(dirname "$0")/.." && pwd)/prototype/vendor/pixelfx"
LIST="$(cd "$(dirname "$0")" && pwd)/pixelfx_used.txt"
rm -rf "$DST"; mkdir -p "$DST/src/fx"
cp -r "$SRC/src/core" "$DST/src/core"
cp "$SRC/LICENSE" "$SRC/NOTICE.md" "$DST/"
for id in $(cat "$LIST"); do
  mkdir -p "$DST/src/fx/$(dirname "$id")"; cp "$SRC/src/fx/$id.js" "$DST/src/fx/$id.js"
done
for d in "$DST"/src/fx/*/; do c=$(basename "$d"); [ -f "$SRC/src/fx/$c/_lib.js" ] && cp "$SRC/src/fx/$c/_lib.js" "$d/_lib.js"; done; true
cd "$SRC" && echo "pixelfx $(git rev-parse HEAD) (package.json $(node -p "require('./package.json').version"))" > "$DST/VERSION"
