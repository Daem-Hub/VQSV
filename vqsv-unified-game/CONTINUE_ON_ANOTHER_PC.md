# Tiếp tục dự án VQSV Unified trên máy khác

Snapshot: 2026-10-29. Gói ZIP chứa toàn bộ thư mục dự án và tài nguyên nguồn dưới `sources/game/`. Các đường dẫn D: cũ trong tài liệu là dấu vết nguồn trên máy trước; khi làm tiếp, dùng đường dẫn tương đối trong gói này.

## Mở bản chơi thử

1. Giải nén toàn bộ ZIP vào một thư mục có quyền ghi, ví dụ `Documents/vqsv-unified-game`.
2. Mở `OPEN_GAME.cmd` trên Windows, hoặc mở `prototype/index.html` trong trình duyệt. Không cần cài Node để chơi bản hiện tại.
3. Dùng WASD/phím mũi tên để đi, E/Enter để nói chuyện, 1/2 khi chiến đấu, P để mở sổ pet, R để chơi lại.

Nếu muốn chạy qua HTTP, cài Node.js rồi chạy `node serve.mjs` tại `prototype/`, mở địa chỉ được in ra. Để dùng công cụ xử lý ảnh, cài Python 3 và chạy `python -m pip install -r requirements.txt` tại gốc dự án.

## Mở trong Codex trên máy mới

Chọn thư mục `vqsv-unified-game` làm workspace. Dán yêu cầu sau vào chat mới:

> Tiếp tục dự án hợp nhất VQSV5 + VQSV6 trong workspace này. Đọc CONTINUE_ON_ANOTHER_PC.md, PROJECT_BLUEPRINT.md, MERGE_DECISIONS.md, STORY_REVIEW.md và prototype/README.md trước. Cốt truyện nền đã chốt từ sources/game/Kich_Ban_Cot_Truyen_VQSV_Tron_Bo.docx. Tiếp tục sửa texture và bổ sung hoạt ảnh thực từ dữ liệu nguồn; kiểm tra bản chạy trước khi báo đã hoạt động. Tôn trọng các lựa chọn đã chốt và giữ provenance của asset.

## Những gì đã chốt

- Thế giới và cốt truyện mới; dựng map mới từ tile của hai game.
- Tuyển NPC, gộp pet, chiến đấu, vật phẩm và kinh tế theo MERGE_DECISIONS.md.
- Cốt truyện nền v1.0: Kael, Lôi Miêu, la bàn của cha; tuyến Lyra/Atlantis và các chương tiếp theo trong DOCX nguồn.
- Lát đầu gồm khám phá, hội thoại NPC, một trận pet và cảnh la bàn thức tỉnh. Canvas chỉ là runtime của prototype.

## Trạng thái kỹ thuật thực tế

- Có 68 map JSON VQSV5 và 94 map JSON VQSV6. Roster `prototype/data/pet_roster_v1.json` có 102 dòng VQSV5 nối tên, atlas và dạng hình, cùng 100 dòng VQSV6 nối tên, hệ và ảnh. VQSV6 có 38 liên kết tiến hóa suy luận và 111 tham chiếu sơ bộ từ `petArea.mid`. Chỉ số và điều kiện chiến đấu/thu phục còn cần giải.
- Prototype có map mới 24×18 ô, NPC, hội thoại, trận pet đơn giản, kết cảnh và sổ pet xem hình/chuỗi hoạt ảnh nguồn. Map, tileset và dữ liệu pet được gộp vào `prototype/data/bootstrap.js` để mở bằng file trực tiếp.
- Chuyển động hiện gồm nội suy bước đi, nhịp idle, pet theo sau, lao đánh, tia điện, vòng thủ và la bàn phát sáng. Chưa giải mã đủ frame hoạt ảnh nguồn.
- Sprite `npc0/30`, `npc0/31`, `img_540`, `img_514` là ứng viên/placeholder. Catalog nguồn xác định `img_538` là Điện Nhãn Miêu, `img_540` là Điện Miêu và `img_514` là Tà Vân Khuyển Thần; prototype dùng `img_540` làm ứng viên hình cho Lôi Miêu, chưa chốt danh tính canon với truyện mới.
- Tile có `image != 0` đang được bỏ qua vì chưa có atlas tương ứng được xác minh. Không cắt tọa độ của chúng từ atlas 0.
- Đã xem preview map render bằng Python; chưa kiểm tra đầy đủ thao tác, animation và vòng chơi trong trình duyệt. Có thể còn lỗi runtime/va chạm/lớp vẽ cần sửa.

## File nên đọc tiếp

- `PROJECT_BLUEPRINT.md`, `MERGE_DECISIONS.md`, `STORY_REVIEW.md`, `SLICE_01_BRIEF.md`.
- `prototype/index.html`, `prototype/data/opening_map.json`, `prototype/assets/provenance.json`.
- `SLICE_01_ASSET_SHORTLIST.md`, `asset_review/`, `VQSV6_MAP_FORMAT.md`.
- Tham khảo gameplay thứ cấp: [Hồi Sinh VQSV 2.1](https://hoisinhvqsv.pages.dev/); dùng để xem luồng/hình ảnh, còn dữ liệu pet lấy từ nguồn game đã trích.
- `sources/game/`: JAR hai game, toàn bộ ảnh/dữ liệu/nhạc đã trích, text, archive gốc, DOCX cốt truyện và XLSX nhiệm vụ.

Sau khi sửa JSON map/tileset, chạy `python tools/build_prototype_bootstrap.py` để đồng bộ bootstrap. Các script khảo sát tự dùng `sources/game/` khi có thư mục này; có thể chỉ định nguồn khác bằng biến môi trường `VQSV_SOURCE_ROOT`.

Trong ZIP có `PACKAGE_MANIFEST.json` ghi SHA-256 của từng file để kiểm tra snapshot. Gói này là bản chuyển máy tại thời điểm đóng gói, không tự đồng bộ các sửa đổi sau đó.
