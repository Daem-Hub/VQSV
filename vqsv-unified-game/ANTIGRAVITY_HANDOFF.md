# Bàn giao VQSV Unified cho Antigravity

Ngày 29/10/2026. Chủ dự án muốn Antigravity tiếp tục phát triển game trong khi dùng Codex cho việc khác. Thư mục làm việc: `D:\EDIT AI\vqsv-unified-game`. Hãy đọc file này rồi đọc `MERGE_DECISIONS.md`, `PROJECT_BLUEPRINT.md`, `SLICE_01_BRIEF.md`, `PET_ROSTER_CATALOG.md` và `prototype/README.md` trước khi sửa.

## Mục tiêu đã chốt

- Hợp nhất toàn bộ pet của VQSV5 và VQSV6; giữ ID nguồn, chuẩn hóa dữ liệu, cân bằng dần chiến đấu, bắt và tiến hóa.
- Cốt truyện và thế giới mới theo quyết định của chủ dự án. Lát đầu giữ Kael, Lôi Miêu, NPC, một trận pet và cảnh la bàn thức tỉnh; không tự đổi cốt truyện đã chốt.
- Người dùng muốn tiếp tục phần pet, có kết quả chạy được trong prototype; không chỉ viết kế hoạch.

## Trạng thái đã có

- `prototype/index.html`: bản chơi thử Canvas. Mở `OPEN_GAME.cmd`, hoặc `prototype/index.html`. Nhấn P hoặc nút Sổ pet.
- `prototype/data/pet_roster_v1.json`: 102 record VQSV5 và 100 record VQSV6. VQSV5 dùng bảng tên và cặp atlas/dạng trong `data.d`; 102 dòng có 101 tổ hợp hình vì dòng 85 và 86 cùng tên Quỷ Vương Địa Phủ và cùng hình. Giữ cả hai ID nguồn.
- Sổ pet có tìm kiếm và lọc; 53 atlas VQSV5 có 303 chuỗi animation, ba chuỗi cho mỗi tổ hợp dạng. Frame ghép tại `prototype/assets/pets/vqsv5/`; ảnh VQSV6 tại `prototype/assets/pets/vqsv6/`. Tốc độ VQSV5 là ước lượng, và tên chức năng từng chuỗi chưa xác minh.
- VQSV6: tên/hệ/ảnh đã nối; 38 liên kết tiến hóa là suy luận dữ liệu. `petArea.mid` còn là phép nối sơ bộ. Chỉ số, chiêu, điều kiện bắt và tiến hóa chưa giải đầy đủ.
- Sổ pet là màn hình duyệt, chưa triển khai chọn đội, bắt, tiến hóa hay đưa toàn bộ pet vào trận. `img_540` là ứng viên hình Lôi Miêu của truyện, chưa chứng minh danh tính canon với Điện Miêu gốc.
- Các kiểm tra trước đây gồm cú pháp JavaScript, dữ liệu roster, các ảnh được tham chiếu và tính toàn vẹn ZIP. Trình duyệt tích hợp của Codex chặn mở `file://`, nên UI và vòng chơi mới chưa được xác nhận bằng thao tác trực tiếp.

## Việc tiếp theo được giao

1. Kiểm tra bản hiện tại trong môi trường của Antigravity: mở game, mở Sổ pet, tìm vài tên VQSV5 và VQSV6, xem animation của các pet ở nhiều atlas, kiểm tra lọc, đóng sổ và trở lại game. Sửa lỗi thực tế phát hiện được.
2. Tiếp tục hoàn thiện phần pet trong lát chơi thử: cải thiện cách dùng dữ liệu pet đã nối và hoạt ảnh trong trận thử hiện có, giữ Lôi Miêu làm pet của Kael và không làm mất di chuyển, hội thoại hoặc kết cảnh. Chỉ gán tên/hệ/chỉ số/điều kiện theo dữ liệu có bằng chứng; ghi rõ các giá trị thử nghiệm và suy luận.
3. Tiếp tục lần lượt nghiên cứu hệ, chỉ số, kỹ năng và tiến hóa của hai game để chuẩn hóa. Những hệ thống chọn đội/bắt/tiến hóa đầy đủ là các bước mở rộng sau lát đầu, theo `SLICE_01_BRIEF.md`.
4. Chạy kiểm tra phù hợp với thay đổi, nêu rõ phần đã chạy trực tiếp và phần chưa xác minh; cập nhật `PET_ROSTER_CATALOG.md` và `prototype/README.md` khi thay đổi dữ liệu hoặc cách chơi.

## Nguồn và cách dựng lại

Nguồn máy hiện tại: `D:\TOAN BO FILE\Download\game`. Gói chuyển máy đặt nguồn tại `sources/game`; các script hỗ trợ biến `VQSV_SOURCE_ROOT`. Có thể dựng lại dữ liệu và hình pet theo thứ tự:

```
python tools/decode_vqsv5_pet_tables.py
python tools/decode_vqsv5_npc2_animations.py
python tools/build_pet_roster_catalog.py
python tools/build_pet_gallery_assets.py
python tools/build_prototype_bootstrap.py
```

Các bảng và bằng chứng: `asset_review/vqsv5_pet_db_sprite_map.csv`, `asset_review/vqsv5_pet_102_contact.png`, `asset_review/vqsv6_pet_db_sprite_map.csv`, `asset_review/vqsv6_pet_evolution_links.csv`, `prototype/assets/provenance.json`. Tham khảo thứ cấp: https://hoisinhvqsv.pages.dev/.

Không xóa hoặc ghi đè tài nguyên nguồn. Không tự gộp hai pet chỉ vì trùng số ID hoặc hình giống nhau. Dự án hiện không có Git trong thư mục này; lưu bản sao trước các thay đổi lớn, giữ công việc đang có và ghi rõ tệp đã thay đổi.
