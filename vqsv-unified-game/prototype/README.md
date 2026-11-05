# Prototype lát chơi thử 01

## Chạy trên Windows

Có thể mở `index.html` trực tiếp. Dữ liệu map, tileset và roster pet đã được gộp vào `data/bootstrap.js` để trình duyệt không chặn tải JSON khi dùng `file://`.

Hoặc mở PowerShell tại thư mục `prototype`, chạy `node serve.mjs`, rồi vào `http://127.0.0.1:4177`.

Máy chủ chỉ phục vụ file tĩnh từ thư mục prototype và lắng nghe trên loopback. Nếu sửa `opening_map.json`, JSON tileset, `pet_roster_v1.json` hoặc `vqsv5_pet_animations.json`, chạy `python ..\tools\build_prototype_bootstrap.py` để cập nhật bootstrap trước khi mở lại.

## Điều khiển

- `WASD` hoặc phím mũi tên: đi lại.
- `E` hoặc `Enter`: nói chuyện / theo dấu vết / tiếp tục thoại.
- `J` hoặc nút **Nhật ký nhiệm vụ**: xem các bước của nhiệm vụ chính và mục tiêu tiếp theo; dấu `!` trên người gác làng chỉ nhiệm vụ mở đầu.
- `M` hoặc nút **Bản đồ thế giới**: xem tranh tổng thể và danh sách các vùng/địa điểm theo truyện. Hiện có làng và hai khu Phong Lôi để di chuyển; các vùng còn lại vẫn là thiết kế.
- Sau khi nói chuyện với người gác làng, đi tới lối phía đông và nhấn `E`: làng nối với rừng, rừng nối với Suối Sấm; nhấn `E` ở lối vào để quay lại. Suối Sấm là địa điểm thiết kế thử, không phải địa danh đã xác nhận trong kịch bản. Nước chặn đường và có cầu bắc qua.
- Nhiệm vụ phụ thử **Thảo dược cho Lôi Miêu**: trước khi kết thúc trận truyện, nói chuyện hết với người chăm sóc pet để nhận, tìm 3 khóm phát sáng quanh làng, rồi trở lại nói chuyện để hoàn thành. Nhật ký và bản lưu ghi lại tiến độ; hiện chưa có phần thưởng vật phẩm. Đây là nhiệm vụ thử mới lấy cảm hứng từ chăm sóc pet, không phải MQ004 hoàn chỉnh trong bảng nhiệm vụ.
- Trong trận, `1`: Điện giật; `2`: thủ; `3`: Vồ nhanh. Chọn **Chiến đấu** để mở bảng. Điện giật dùng tên, số lần (45) và chuỗi sprite của bản FIX 2.7 theo pet Điện Miêu tham khảo; sát thương và nhịp phát là thử nghiệm. Điện Miêu chưa được xác nhận là Lôi Miêu trong truyện. Vồ nhanh và Phòng thủ vẫn là thiết kế thử.
- Các mục lệnh tham khảo bản mẫu: **Đạo cụ** dùng thuốc thử hồi tối đa 20 HP (2 lần mỗi trận và mất một lượt); **Sủng vật** (phím `4`) mở bảng đội hình để đổi pet, kèm nút xem sổ pet; **Rút khỏi trận** quay về bản đồ và có thể bắt đầu lại trận. **Bắt pet** chưa khả dụng trong trận truyện, cửa hàng chưa được triển khai. Thuốc và số lượng là thiết kế thử, không phải dữ liệu đã xác nhận từ game gốc.
- `P` hoặc nút **Mở sổ pet**: xem và tìm pet hai game; chọn pet VQSV5 để xem các chuỗi hoạt ảnh. Đóng bằng nút × hoặc Escape.
- **Sổ kỹ năng gốc**: tra 49 tên kỹ năng VQSV5 và 70 kỹ năng VQSV6; chọn một trong 32 chuỗi hiệu ứng VQSV5 đã ghép khung để xem. Liên hệ giữa kỹ năng và hiệu ứng chưa xác minh, chưa đưa vào trận.
- Trong sổ, chọn pet rồi bấm **Gom vào bộ sưu tập**; dùng bộ lọc **Pet đã gom** để xem chung pet của hai game. Bấm lại để bỏ. Danh sách lưu trong trình duyệt hiện tại nếu localStorage khả dụng; không đồng bộ giữa máy hoặc trình duyệt.
- **Đội hình thử:** trong sổ, chọn pet rồi bấm **Đưa vào đội** (tối đa 2 pet dự bị; Lôi Miêu luôn là pet chính, nên hình tham khảo Điện Miêu không thêm được). Đội được chụp lại khi bắt đầu trận. Trong trận, mở **Sủng vật** để đổi pet: đổi chủ động mất một lượt, còn khi pet đang ra trận kiệt sức thì bắt buộc chọn pet khác và không mất lượt; chỉ thua khi cả đội kiệt sức. Pet dự bị có 40 HP, dùng **Đòn thường** (10 sát thương) và Phòng thủ; không có Điện giật. Các con số này là giá trị thử nghiệm vì chỉ số gốc của hai game chưa giải mã. Pet VQSV6 hiện bằng ảnh gốc; pet VQSV5 chạy chuỗi hoạt ảnh nguồn thứ nhất khi đứng và chuỗi thứ hai khi tấn công. Việc chuỗi nào là đứng hay đánh chưa được xác minh, đây chỉ là lựa chọn trình bày. Danh sách đội lưu riêng trên trình duyệt (`vqsv-unified-pet-team-v1`).
- Với pet VQSV6 có liên kết dạng tiếp theo, sổ cho xem loại biến đổi và điều kiện cấp, nguyên liệu theo game gốc. Điều kiện trong game hợp nhất chưa chốt; chức năng biến đổi chưa được triển khai.
- Bộ lọc **Nguyên liệu gốc** tìm pet theo nguyên liệu tiến hóa VQSV6; nút **Xem** tại chi tiết mở pet ở dạng tiếp theo. Đây là tra cứu, chưa phải kho vật phẩm.
- `R` hoặc nút bên cạnh: chơi lại.
- **Lưu / Tải lại**: lưu vị trí Kael, hướng nhìn và mốc truyện trên trình duyệt hiện tại; lưu khi đang ở map, đã đứng yên hoặc sau cảnh kết. Không lưu giữa trận hay giữa hội thoại. **Chơi lại** không xóa bản lưu; lưu lần tiếp theo sẽ thay bản lưu trước. Bản lưu không tự đồng bộ sang máy hoặc trình duyệt khác; bộ sưu tập pet được lưu riêng.

## Nội dung có trong bản thử

- Ba map 24×18 ô được dựng từ tile VQSV5, kết hợp tileset làng `00` và địa hình `04`; mặt nước và cầu là đồ họa thử dựng bằng canvas. Các vùng chưa có nhiệm vụ truyện riêng.
- Hai căn nhà được ghép từ hai nửa sprite lật gương như cách map VQSV5 đặt chúng; bỏ các mảnh vách đá đơn lẻ ở viền khiến cảnh trông như texture bị cắt.
- Khu mở đầu được bố trí lại thành làng biên giới trước biến cố: hai ngôi nhà có sân và đường ra trục làng, lối riêng tới các NPC, rừng thông và cây lá rộng; thêm bảng tin và cây cảnh từ VQSV5. Mái nhà và tán cây được ghép đủ các mảnh nguồn. Đây là diễn giải từ kịch bản, chưa thể hiện cảnh làng bị thiêu rụi sau đó. Bản xem tĩnh ở `asset_review/opening_map_redesign.png`.
- NPC, đoạn thoại mở đầu, điểm dấu vết Trăng Huyết, trận pet lượt đơn giản và cảnh la bàn thức tỉnh.
- Làng có thêm người chăm sóc pet, dân làng và người đi rừng; đứng cạnh và nhấn E để nói chuyện. Hình được chọn từ VQSV5, còn vai trò và lời thoại là nội dung mới của prototype; chưa xác nhận danh tính NPC gốc.
- Trận mở đầu có nền bìa rừng, bảng máu có số, bộ đếm lượt và hàng sáu nhóm lệnh bên dưới theo bố cục giao diện mẫu. Chỉ số và sức mạnh hiện vẫn là giá trị thử nghiệm.
- Bảng mục tiêu đổi theo hội thoại, trận và kết quả; phím tương tác và phím đánh không tự lặp khi giữ để tránh bỏ qua hội thoại hoặc thực hiện nhiều lượt ngoài ý muốn.
- Tắt nội suy ảnh để giữ cạnh pixel; map vẽ nền, đồ vật sau/trước nhân vật theo thứ tự chiều sâu và tránh cắt tile từ atlas không được nạp.
- Kael dùng bộ sprite pixel mới dựa trên hình cũ và tham chiếu NPC VQSV5, với tóc đen, áo xanh, khăn đỏ và la bàn; có 4 hướng và 4 tư thế mỗi hướng. Nhân vật đi liên tục khoảng 220 ms/ô, nhịp chân theo quãng đường. Các khung được cắt và căn riêng theo bộ ảnh mới; NPC/pet có nhịp idle.
- Hình dùng thử được sao chép vào `assets/`; nguồn và độ chắc chắn ghi tại `assets/provenance.json`. `data/pet_roster_v1.json` chứa 102 dòng VQSV5 và 100 dòng VQSV6 với ID riêng theo từng game.
- Sổ pet hiển thị đủ 102 dòng VQSV5 có tên và hình từ dữ liệu gốc, cùng 100 dòng VQSV6. Có thể xem ba chuỗi hoạt ảnh cho từng pet VQSV5 với tốc độ gần đúng và gom pet vào bộ sưu tập thử, đưa tối đa 2 pet vào đội dự bị và đổi pet trong trận. Chưa có bắt pet, tiến hóa hay chỉ số theo dữ liệu gốc.

Để dựng lại tài nguyên sổ pet từ nguồn: lần lượt chạy `python tools/decode_vqsv5_pet_tables.py`, `python tools/decode_vqsv5_npc2_animations.py`, `python tools/build_pet_roster_catalog.py`, `python tools/build_pet_gallery_assets.py`, rồi `python tools/build_prototype_bootstrap.py` từ thư mục gốc dự án.

## Ranh giới hiện tại

Đây là prototype tương tác để duyệt vòng lặp chơi. Sprite Kael là hình mới dựng theo mô tả bằng chữ trong kịch bản; không phải sprite gốc của hai game. Kael có chu kỳ đi bộ 4 khung theo 4 hướng; chưa có hoạt ảnh chiến đấu riêng. NPC/pet và một số va chạm vẫn là ứng viên hoặc placeholder. Pet tham khảo đã đổi sang `img_540` (Điện Miêu, DB 78); `img_538` là Điện Nhãn Miêu và không còn được dùng làm ảnh Lôi Miêu. Liên kết Lôi Miêu ↔ Điện Miêu vẫn là lựa chọn thiết kế của prototype, chưa phải bí danh canon từ dữ liệu gốc. Map chưa có âm thanh hoặc bản dịch hoàn chỉnh; hệ lưu tiến trình hiện chỉ phục vụ lát mở đầu. Hình dáng cụ thể của Kael là diễn giải mỹ thuật để dùng trong prototype.
