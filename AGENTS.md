# Quy tắc làm chung cho các AI agent (Claude Code, Codex, Antigravity)

Ba agent cùng làm trên repo `Daem-Hub/VQSV`. Chủ dự án giao việc cho từng agent. File này giúp các agent không ghi đè việc của nhau.

## Trước khi làm

1. `git pull origin main`. Mọi agent đều làm trên nhánh `main`. Không bắt đầu từ bản cũ.
2. Đọc `vqsv-unified-game/prototype/README.md`. Mỗi tính năng đã làm đều có một mục trong đó.
3. Chỉ sửa những phần thuộc việc được giao. Nếu buộc phải sửa phần khác, ghi rõ lý do trong commit.

## Trong khi làm

- Game là trang tĩnh trong `vqsv-unified-game/prototype/`. Chạy thử bằng `node serve.mjs` trong thư mục đó, rồi mở http://127.0.0.1:4177. Không mở bằng `file://`.
- Dữ liệu map và pet được dựng bằng script, không sửa tay `data/bootstrap.js`. Sau khi đổi dữ liệu, chạy `python3 tools/build_prototype_bootstrap.py` từ thư mục `vqsv-unified-game`.
- Đừng đổi những phần đã chốt nếu chủ dự án chưa yêu cầu:
  - map đi lại dựng từ map thật VQSV5 (`tools/build_vqsv5_walk_maps.py`)
  - công thức trận đấu đã giải mã từ hai game
  - hiệu ứng kỹ năng (`skill_fx.js`, vendored pixelfx) và âm thanh (`sound_fx.js`)
  - bảng hệ chung `UNIFY5`
- Giá trị nào là đoán hoặc thiết kế thử thì ghi là TRIAL trong code và trong README.

## Trước khi đẩy

1. Chạy hết test: `for t in tools/test_*.cjs; do node "$t" || echo "FAIL $t"; done` (trong `vqsv-unified-game`). Test nào phải đổi theo hành vi mới thì sửa cho đúng, không xóa test.
2. Viết thêm hoặc cập nhật mục trong `prototype/README.md` (tiếng Việt).
3. `git pull origin main` lần nữa. Nếu có xung đột thì giải quyết hoặc dừng lại báo chủ dự án; không dùng `git push --force`.
4. Commit với mô tả rõ ràng, rồi `git push origin main`.

## Bản cho điện thoại

`python3 tools/build_mobile_html.py` (cần `npm i esbuild@0.24.0`) tạo `dist_mobile/`. Thư mục này không đưa lên git. Claude Code cập nhật link chơi trên điện thoại sau mỗi lần `main` có thay đổi.
