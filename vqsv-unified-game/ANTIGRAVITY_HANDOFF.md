# Bàn giao VQSV Unified cho Antigravity

Cập nhật 20/11/2026. Antigravity làm cùng Claude Code và Codex trên repo `Daem-Hub/VQSV`, nhánh `main`. Đọc `../AGENTS.md` (quy tắc làm chung) và `prototype/README.md` trước khi sửa. Bản ghi cũ từ 29/10 vẫn xem được trong lịch sử git.

## Game đang có gì

- **Bản chơi:** `prototype/index.html`, chạy bằng `node serve.mjs` trong `prototype/`.
- **Giao diện điện thoại:** joystick, nút hành động, menu ☰ theo VQSV6 (Tùy thân cửa hàng, Sủng vật, Lưng bao, Đồ giám, Nhiệm vụ, Lưu dữ liệu, Hệ thống).
- **Map:**
  - làng biên giới vẽ pixel và hai tiệm (thuốc, bùa)
  - 12 map đi lại dựng từ map thật VQSV5, có rương và địa hình
  - 29 map tranh trên đường lớn từ làng đến Mặt Trăng Đỏ
- **Pet:** 102 pet VQSV5 và 100 pet VQSV6, dùng chỉ số, kỹ năng, bắt pet, tiến hóa và dị hóa đã giải mã. Hệ VQSV5 được xếp vào 7 hệ VQSV6 (`UNIFY5`). Có ba lô 6 con, ngân hàng 100 con và ghép pet VQSV5 + VQSV6.
- **Trận đấu:** bố cục VQSV5, nền theo vùng và hệ, hiệu ứng pixelfx, âm thanh tổng hợp.
- **Nhiệm vụ:** truyện mở đầu, Thảo dược cho Lôi Miêu, Khảo sát Suối Sấm.

## Phân việc

Chủ dự án giao việc cho từng agent trong lời nhắn. Nếu chưa có việc cụ thể, hỏi chủ dự án trước khi làm. Không tự làm lại phần agent khác vừa làm.

## Nguồn tham khảo

- Mã VQSV6 (bản FIX 2.7) đã dịch ngược: `reference_vqsv_fix_2_7/source_code/decoded/decompiled_source_cfr/`
- Bố cục giao diện VQSV6 đã giải mã: `reference_vqsv_fix_2_7/ui/decoded/*.ui.json`
- Dữ liệu đã giải mã: `prototype/data/` (`vqsv5_pet_stats.json`, `vqsv6_pet_stats.json`, `pet_roster_v1.json`, ...)
- Danh sách pet: `PET_ROSTER_CATALOG.md`
