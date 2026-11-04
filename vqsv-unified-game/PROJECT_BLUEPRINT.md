# Dự án hợp nhất VQSV5 và VQSV6

**Trạng thái:** thiết kế đã nhận lựa chọn định hướng ngày 2026-10-26; dựa trên tài nguyên trong `D:\TOAN BO FILE\Download\game`  
**Tên dự án:** chưa chốt; tạm gọi là `VQSV Unified`.

## Mục tiêu

Tạo một game RPG 2D với thế giới và cốt truyện mới, dùng nhân vật, pet, cơ chế và tài nguyên hình ảnh được tuyển chọn từ VQSV5 (PKM – Cầu Vồng) và VQSV6 (Vương Quốc Pokemon). Map mới được dựng từ tile, nhà cửa và địa hình nguồn. Dữ liệu được chuyển qua một lớp định dạng chung để dễ cân bằng và mở rộng.

**Cốt truyện nền đã chốt:** `D:\TOAN BO FILE\Download\game\Kich_Ban_Cot_Truyen_VQSV_Tron_Bo.docx`, được đánh giá tại [STORY_REVIEW.md](STORY_REVIEW.md). Đây là canon nền v1.0; file nguồn không bị chỉnh sửa.

## Định hướng đã chọn

- Cốt truyện mới; giữ và tuyển chọn nhân vật từ cả hai game.
- Thế giới mới dựng từ tile, nhà cửa và địa hình cũ; không lấy nguyên tuyến map VQSV5→VQSV6 làm tuyến chính.
- Gộp roster pet, chiến đấu, bắt/tiến hóa, vật phẩm và kinh tế vào hệ thống chung.
- Chọn hình ảnh, giao diện, âm thanh và nhạc từ cả hai, thống nhất tỷ lệ hiển thị.
- Bản chơi thử đầu tiên có khám phá map, NPC và một trận pet.
- Bảng lựa chọn chi tiết: [MERGE_DECISIONS.md](MERGE_DECISIONS.md).

## Dữ liệu hiện có

Nguồn tài nguyên: `D:\TOAN BO FILE\Download\game`.

| Nguồn | Đã xác nhận | Còn cần làm rõ |
|---|---|---|
| VQSV5 | 68 map đã có JSON, ảnh ghép, lưới va chạm và tileset trong `files.zip`; tổng thư mục có 159 file map nhị phân | Cổng đổi map, vị trí NPC, tên/vai trò một số map và cấu trúc đầy đủ của `mN.d` |
| VQSV6 | 94 file map, 242 ảnh trong `data/img`, 299 sprite, 40 UI, 13 cảnh phim, 17 script | Cách giải mã map, ghép khung sprite, cấu trúc pet/chỉ số và cơ chế chiến đấu |
| Văn bản | Khoảng 4.600 chuỗi trích từ nhiệm vụ, thoại, kỹ năng và cảnh phim | Một số chuỗi bị thiếu ký tự đầu hoặc lẫn ký tự rác; cần kiểm chứng bằng ngữ cảnh |
| Cốt truyện | VQSV5 nói về Thành Cầu Vồng và nhiệm vụ bắt sủng vật; VQSV6 mở đầu ở Làng Neil với Hắc Long Quân | Chưa có bằng chứng rằng hai cốt truyện vốn nối trực tiếp với nhau |

Gói map VQSV5 cho biết map `.mid` là dữ liệu bản đồ, không phải nhạc; tile là 20×20 px. `map.d` là bản đồ thế giới/chọn vùng. Tài liệu hiện có ghi rõ cổng chuyển map và vị trí NPC chưa được tìm thấy trong các map JSON.

**Cập nhật VQSV6:** em đã đọc loader `j.a(I)V` trong JAR và giải mã 94 file map thành JSON. Header map khai báo mode tọa độ, kích thước, tile 16 px và 5 lớp. Tên/chức năng chính xác của các lớp và bảng nối ID tile sang ảnh vẫn đang được xác minh; xem [VQSV6_MAP_FORMAT.md](VQSV6_MAP_FORMAT.md).

## Hướng hợp nhất đề xuất

- Dùng mã map có namespace để tránh trùng ID: `vqsv5:0`, `vqsv6:0`.
- Đưa NPC, hội thoại, sự kiện, pet, chiêu thức và vật phẩm vào các định dạng dữ liệu chung; giữ lại ID nguồn để truy ngược.
- Chuẩn hóa tọa độ bằng đơn vị ô lưới. Không giả định mọi game dùng cùng kích thước tile hoặc cùng tỷ lệ hình ảnh.
- Xây bộ nạp riêng cho dữ liệu VQSV5 và VQSV6, sau đó xuất sang định dạng runtime chung. Giữ nguyên thư mục nguồn làm bản tham chiếu.
- Nối truyện theo dạng chương: hoàn tất một mốc chính ở VQSV5 mở tuyến sang VQSV6. Đây là hướng thiết kế mới, chưa phải quan hệ cốt truyện đã xác minh.

## Lát cắt chơi thử đầu tiên

**Mục tiêu:** chứng minh vòng lặp khám phá–hội thoại–chiến đấu trong phần mở đầu cốt truyện mới. Phạm vi chi tiết ở [SLICE_01_BRIEF.md](SLICE_01_BRIEF.md).

**Bản map thử:** dựng một khu nhỏ từ tile/địa hình nguồn, đặt Kael, Lôi Miêu và NPC vào mạch mở đầu Chương 1. Route cũ dùng VQSV5 `map 0 → map 1 → VQSV6 map 0` chỉ còn là tài liệu tham khảo tại [opening_route.json](opening_route.json); các tọa độ cũ không phải lựa chọn chính thức.

## Sơ đồ thế giới hợp nhất — dự thảo v1

Xem [sơ đồ tổng thể](asset_review/vqsv_unified_world_overview_v1.png). Đây là bố cục vùng và tuyến truyện tương đối theo cốt truyện đã chốt: Ryan → Hải Vực Vô Tận → Atlantis → Aethelgard → Mặt Trăng Đỏ. Chỉ map làng biên giới đang được dựng thành lát chơi thử; các vùng còn lại là kế hoạch, chưa phải map playable hay vị trí địa lý đã chốt. Chưa gán từng map VQSV5/VQSV6 vào vùng mới vì chức năng map và ID tile VQSV6 còn cần xác minh.

**Atlas trong prototype:** nhấn M để xem tranh tổng thể và 12 khu vực/điểm đến cốt truyện. Tranh là minh họa dự kiến, không phải bản đồ chơi được hay tọa độ địa lý đã chốt; hiện chỉ làng mở đầu có thể đi lại. Mặt Trăng là điểm đến ở cuối kịch bản, diễn biến sau khi tới vẫn cần duyệt.

**Pet roster:** catalog, [CSV nguồn](asset_review/pet_roster_candidates.csv) và [JSON schema dùng chung v1](prototype/data/pet_roster_v1.json). Có 102 dòng VQSV5 nối tên, atlas và dạng hình từ `data.d`, cùng 100 dòng VQSV6 nối tên, hệ và ảnh; hai dòng VQSV5 dùng chung một tên/hình vẫn giữ ID riêng. Ba starter VQSV6 và liên kết tiến hóa suy luận được giữ cùng dữ liệu gốc. Prototype dùng `img_540` (Điện Miêu) làm ứng viên hình cho Lôi Miêu; liên kết cốt truyện vẫn là lựa chọn thiết kế. [Bảng nối sơ bộ `petArea.mid`](asset_review/vqsv6_pet_area_roster.csv) còn chờ xác nhận cách lookup trong gameplay code. Xem [catalog chi tiết](PET_ROSTER_CATALOG.md), [bảng VQSV5](asset_review/vqsv5_pet_db_sprite_map.csv) và [bảng VQSV6](asset_review/vqsv6_pet_db_sprite_map.csv).

**Tham khảo gameplay bổ sung:** [Hồi Sinh VQSV 2.1](https://hoisinhvqsv.pages.dev/) được xem như tham khảo thứ cấp về giao diện và luồng mở đầu; không dùng làm nguồn ID/schema thay cho file game đã trích.

**Sổ pet prototype:** mở bằng nút hoặc phím P. Có thể tìm và xem 102 dòng VQSV5 cùng 100 dòng VQSV6; mỗi pet VQSV5 hiển thị ba chuỗi hoạt ảnh theo dạng hình trong dữ liệu nguồn. Sổ phục vụ duyệt roster, chưa phải màn hình chọn pet vào đội hoặc triển khai chỉ số trận.

**Danh sách nhiệm vụ để duyệt:** [DANH_SACH_NHIEM_VU.md](DANH_SACH_NHIEM_VU.md) tổng hợp 96 nhiệm vụ chính và 47 nhiệm vụ phụ từ bảng nhiệm vụ đã có, đánh dấu các điểm cần đối chiếu với cốt truyện. Đây là thiết kế, chưa đồng nghĩa các nhiệm vụ đã được triển khai hoặc phê duyệt.

## Các mốc công việc

### Mốc 0 — Khóa phạm vi và bảo toàn nguồn

- Ghi manifest tên file, kích thước, SHA-256 và nguồn của tài nguyên.
- Tách rõ file gốc, file đã giải mã, và file do dự án tạo.
- Chốt nền tảng chạy và engine sau khi kiểm tra yêu cầu điều khiển, độ phân giải, đóng gói và khả năng đọc dữ liệu.

### Mốc 1 — Hoàn tất khảo sát dữ liệu

- VQSV5: giải mã luồng đổi map, bảng event/NPC, cấu trúc `mN.d` và quan hệ nhiệm vụ.
- VQSV6: tìm định dạng map, pet/chỉ số, sprite frame và lệnh chiến đấu.
- Lập bảng ID map, NPC, pet, chiêu thức và nhiệm vụ có ghi nguồn cùng mức độ tin cậy.

### Mốc 2 — Bản chơi thử

- Dựng khu mở đầu Chương 1 theo [SLICE_01_BRIEF.md](SLICE_01_BRIEF.md): Kael, Lôi Miêu, một NPC được tuyển chọn và một trận pet.
- Nạp map và collision; điều khiển nhân vật; tương tác NPC; hội thoại; trận chiến tối thiểu.
- Ghi chú rõ nội dung dùng ảnh/hoạt ảnh thật và nội dung tạm dựng.

### Mốc 3 — Nối nội dung

- Hoàn thiện tuyến truyện nối hai game.
- Mở rộng map, NPC, pet, nhiệm vụ và chiến đấu theo từng cụm có thể kiểm tra.
- Bổ sung lưu game, menu, âm thanh, hướng dẫn và đóng gói.

## Điều kiện đạt bản chơi thử

- Vào được map của cả hai nguồn trong cùng một bản chạy.
- Nhân vật đi lại đúng trên lưới và không xuyên vật cản đã xác minh.
- Hội thoại gọi đúng NPC; cổng đưa đến map và tọa độ đích đúng.
- Có thể hoàn tất một trận chiến tối thiểu và lưu/tải lại trạng thái.
- Mỗi dữ liệu chưa xác minh được đánh dấu riêng, không trình bày như dữ kiện gốc.

## Rủi ro kỹ thuật cần xử lý sớm

- Hai game có định dạng nhị phân và cách biểu diễn sự kiện khác nhau.
- ID pet, map, vật phẩm có thể trùng nhưng không cùng nghĩa.
- Văn bản trích xuất có lỗi mã hóa; cần kiểm tra trước khi dùng trong hội thoại game.
- Một số nội dung có thể phụ thuộc vào bytecode gốc; bản hợp nhất sẽ cần tái triển khai hành vi thay vì chỉ ghép file.

## Việc kế tiếp

Manifest nguồn đã được tạo trong `SOURCE_MANIFEST.json`; map VQSV5 gốc đã được đưa vào `assets/vqsv5/maps/`, còn VQSV6 đã xuất JSON trong `vqsv6_maps/`. Cốt truyện nền và phạm vi lát chơi thử đã chốt. Đã tạo contact sheet/index hình ảnh và shortlist tại [SLICE_01_ASSET_SHORTLIST.md](SLICE_01_ASSET_SHORTLIST.md). Prototype tương tác đầu tiên nằm trong [prototype/README.md](prototype/README.md); dùng HTML Canvas cho lát thử, theo quyết định [ENGINE_DECISION.md](ENGINE_DECISION.md). Đã sửa tải dữ liệu để hỗ trợ mở trực tiếp `file://`, tắt nội suy pixel, sửa ground tile bị lặp sai và bổ sung chuyển động/hiệu ứng. Việc tiếp theo là duyệt prototype trong trình duyệt, tinh chỉnh tile layering/va chạm và truy ID sprite VQSV6 từ script trước khi khóa art cho trận pet.
