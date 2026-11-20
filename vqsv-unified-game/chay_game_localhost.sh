#!/bin/sh
# Run the game on http://127.0.0.1:4177 (needs Node.js)
cd "$(dirname "$0")/prototype" && (sleep 1; (xdg-open http://127.0.0.1:4177 || open http://127.0.0.1:4177) >/dev/null 2>&1 &) ; node serve.mjs
