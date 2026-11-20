@echo off
rem Chay game qua localhost (can Node.js: https://nodejs.org). Mo trinh duyet o http://127.0.0.1:4177
cd /d "%~dp0prototype"
start "" http://127.0.0.1:4177
node serve.mjs
pause
