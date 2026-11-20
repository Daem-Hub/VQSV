# Việc giao cho Codex: điều khiển cảm ứng và menu kiểu VQSV6

Trước khi làm: `git pull origin main`. Làm và đẩy lên nhánh `main`. Chỉ sửa phần giao diện; không đổi map, dữ liệu trận đấu hay công thức.

## 1. Nút bấm và joystick trên điện thoại

Anh chơi thử trên điện thoại thấy nút bấm chưa ăn ổn định, joystick nhỏ khó bấm.

- Joystick (`#joystick` trong `prototype/expedition.css` và `classic.css`): hiện `clamp(90px,17vmin,140px)`. Làm to hơn (khoảng 130–170px trên điện thoại dọc), vùng chạm rộng hơn hình vẽ. Cho phép đặt ngón tay ở bất kỳ chỗ nào nửa trái dưới màn hình để bắt đầu kéo (joystick nổi), tùy chọn.
- Nút tròn hành động `#touchAction` và các nút `.shortcuts`, `.hud-actions`: kiểm tra một lần chạm luôn kích hoạt đúng một lần, kể cả ngay sau khi vừa kéo joystick. Phần xử lý chạm nằm trong `prototype/village_ui.js` (đoạn `pointerdown`/`pointerup` cho `main button`).
- Nút trong khung lệnh trận (`#battleControls`, `prototype/battle.css`) và các bảng chọn (`.duel-picker`): kiểm tra chạm trên điện thoại, cỡ chạm tối thiểu 44px.
- Thử bằng Playwright với `devices['Pixel 7']` và một máy iPhone, cả dọc và ngang.

## 2. Menu kiểu VQSV6

Nút ☰ (`#openMenu`) mở menu `#gameMenu`. Làm lại menu theo game gốc VQSV6.

Nguồn đã giải mã có trong repo:
- Nhãn và thứ tự mục: `reference_vqsv_fix_2_7/source_code/decoded/decompiled_source_cfr/game/k.java`, hàm `k()`: **Tùy thân cửa hàng, Sủng vật, Lưng bao, Đồ giám, Nhiệm vụ, Lưu dữ liệu**. Hàm `l()` cho biết mỗi mục mở màn hình nào.
- Bố cục màn hình: `reference_vqsv_fix_2_7/ui/decoded/`, gồm `data__ui__gamemenu.ui.json` (menu chính), `gamesystem.ui.json` (hệ thống), `bag.ui.json` (lưng bao), `petstate.ui.json` / `petsetting.ui.json` (sủng vật), `shop*.ui.json` (cửa hàng), `task.ui.json` (nhiệm vụ), `record.ui.json` (lưu), `option.ui.json`. Tọa độ theo màn hình gốc 240 × 320.

Mỗi mục nối vào chức năng đã có trong game (không viết lại logic):

| Mục VQSV6 | Gọi hàm có sẵn trong `index.html` |
|---|---|
| Tùy thân cửa hàng | `openShop()` (trong trận thì giá gấp đôi, đã có) |
| Sủng vật | `window.openPetBook()`; nếu làm được màn trạng thái pet riêng theo `petstate.ui` thì càng tốt |
| Lưng bao | `openBag()` |
| Đồ giám | sổ pet ở chế độ bộ sưu tập (`window.openPetBook()`, bộ lọc "Pet đã bắt") |
| Nhiệm vụ | `openQuestJournal()` |
| Lưu dữ liệu | `saveGame()` / `loadGame()` |

Ngoài ra giữ đường vào: Bản đồ (`openMap()`), Sổ kỹ năng (`window.openSkillBook()`), âm thanh (`toggleSound()`, `#soundVolume`), Chơi lại (`reset()`). Có thể để các mục này trong trang Hệ thống như VQSV6 làm với `gamesystem.ui`.

Điều khiển như máy gốc: phím 2/4/6/8 hoặc mũi tên để chọn, 5/Enter để mở, phím mềm trái **Chọn**, phải **Quay lại**.

## 3. Ràng buộc

- Không sửa: `data/*.json` của map, `tools/build_vqsv5_walk_maps.py`, `skill_fx.js`, `sound_fx.js`, phần trận đấu trong `index.html` (trừ phần giao diện).
- Toàn bộ `tools/test_*.cjs` phải chạy qua: `for t in tools/test_*.cjs; do node "$t" || echo "FAIL $t"; done`. Test nào phải sửa vì đổi giao diện thì sửa test cho đúng hành vi mới, không xóa.
- Ghi thay đổi vào `prototype/README.md` (tiếng Việt).
- Chạy `python3 tools/build_mobile_html.py` (cần esbuild: `npm i esbuild@0.24.0`) để kiểm tra bản điện thoại vẫn dựng được.
- Commit rồi `git push origin main`.
