# Prototype lát chơi thử 01

## Chạy trên Windows

Có thể mở `index.html` trực tiếp. Dữ liệu map, tileset và roster pet đã được gộp vào `data/bootstrap.js` để trình duyệt không chặn tải JSON khi dùng `file://`.

Hoặc mở PowerShell tại thư mục `prototype`, chạy `node serve.mjs`, rồi vào `http://127.0.0.1:4177`.

Máy chủ chỉ phục vụ file tĩnh từ thư mục prototype và lắng nghe trên loopback. Nếu sửa `opening_map.json`, JSON tileset, `pet_roster_v1.json` hoặc `vqsv5_pet_animations.json`, chạy `python ..\tools\build_prototype_bootstrap.py` để cập nhật bootstrap trước khi mở lại.

## Điều khiển

- `WASD` hoặc phím mũi tên: đi lại.
- `E` hoặc `Enter`: nói chuyện / theo dấu vết / tiếp tục thoại.
- `J` hoặc nút **Nhật ký nhiệm vụ**: xem các bước của nhiệm vụ chính và mục tiêu tiếp theo; dấu `!` trên người gác làng chỉ nhiệm vụ mở đầu.
- `M` hoặc nút **Bản đồ** mở sơ đồ khu vực. Chọn **Thế giới** để xem các địa danh theo truyện. Hiện có làng và hai khu Phong Lôi để di chuyển; các vùng còn lại vẫn là thiết kế.
- Sau khi nói chuyện với người gác làng, đi tới lối phía đông và nhấn `E`: làng nối với rừng, rừng nối với Suối Sấm; nhấn `E` ở lối vào để quay lại. Suối Sấm là địa điểm thiết kế thử, không phải địa danh đã xác nhận trong kịch bản. Nước chặn đường và có cầu bắc qua.
- Nhiệm vụ phụ thử **Thảo dược cho Lôi Miêu**: trước khi kết thúc trận truyện, nói chuyện hết với người chăm sóc pet để nhận, tìm 3 khóm phát sáng quanh làng, rồi trở lại nói chuyện để hoàn thành. Nhật ký và bản lưu ghi lại tiến độ; hoàn thành được 12 xu. Đây là nhiệm vụ thử mới lấy cảm hứng từ chăm sóc pet, không phải MQ004 hoàn chỉnh trong bảng nhiệm vụ.
- Trong trận, `1`: Điện giật; `2`: thủ; `3`: Vồ nhanh. Chọn **Công kích** để mở bảng. Dùng mũi tên để chọn, Enter hoặc 5 để xác nhận, Esc để trở lại. Điện giật dùng tên, số lần (45) và chuỗi sprite của bản FIX 2.7 theo pet Điện Miêu tham khảo; sát thương và nhịp phát là thử nghiệm. Điện Miêu chưa được xác nhận là Lôi Miêu trong truyện. Vồ nhanh và Phòng thủ vẫn là thiết kế thử.
- Các mục lệnh tham khảo bản mẫu: **Đạo cụ** ưu tiên thuốc mua trong tiệm (hồi tối đa 30 HP), sau đó đến hai liều thuốc thử mỗi trận (20 HP); dùng thuốc mất một lượt; **Sủng vật** mở sổ để xem; **Rút khỏi trận** quay về bản đồ và có thể bắt đầu lại trận. **Bắt pet** chưa khả dụng trong trận truyện, nút Túi đồ cho xem vật phẩm đang mang. Thuốc và số lượng là thiết kế thử, không phải dữ liệu đã xác nhận từ game gốc.
- `P` hoặc nút **Mở sổ pet**: xem và tìm pet hai game; chọn pet VQSV5 để xem các chuỗi hoạt ảnh. Đóng bằng nút × hoặc Escape.
- **Sổ kỹ năng gốc**: tra 49 tên kỹ năng VQSV5 và 70 kỹ năng VQSV6; chọn một trong 32 chuỗi hiệu ứng VQSV5 đã ghép khung để xem. Liên hệ giữa kỹ năng và hiệu ứng chưa xác minh, chưa đưa vào trận.
- Trong sổ, chọn pet rồi bấm **Gom vào bộ sưu tập**; dùng bộ lọc **Pet đã gom** để xem chung pet của hai game. Bấm lại để bỏ. Danh sách lưu trong trình duyệt hiện tại nếu localStorage khả dụng; không đồng bộ giữa máy hoặc trình duyệt.
- Với pet VQSV6 có liên kết dạng tiếp theo, sổ cho xem loại biến đổi và điều kiện cấp, nguyên liệu theo game gốc. Điều kiện trong game hợp nhất chưa chốt; chức năng biến đổi chưa được triển khai.
- Bộ lọc **Nguyên liệu gốc** tìm pet theo nguyên liệu tiến hóa VQSV6; nút **Xem** tại chi tiết mở pet ở dạng tiếp theo. Đây là tra cứu, chưa phải kho vật phẩm.
- `R` hoặc nút bên cạnh: chơi lại.
- **Lưu / Tải lại**: lưu vị trí Kael, hướng nhìn và mốc truyện trên trình duyệt hiện tại; lưu khi đang ở map, đã đứng yên hoặc sau cảnh kết. Không lưu giữa trận hay giữa hội thoại. **Chơi lại** không xóa bản lưu; lưu lần tiếp theo sẽ thay bản lưu trước. Bản lưu không tự đồng bộ sang máy hoặc trình duyệt khác; bộ sưu tập pet được lưu riêng.

## Nội dung có trong bản thử

- Làng, rừng, suối, hai tiệm và đấu trường dùng tranh phong cách pixel trên khung 480 × 360. Nền ngoài trời dùng tối đa 96 màu. Nhà, cây và nhân vật được vẽ theo cùng lưới. Canvas phóng hình theo cạnh pixel và vẽ chữ theo mật độ điểm ảnh màn hình.
- Mặt đường, lối qua cầu và bóng đổ được dựng từ dữ liệu bản đồ và hình trong `assets/landscape`. Nhà, cây, nhân vật và thú đồng hành được vẽ theo độ sâu khi Kael di chuyển. Hai cửa tiệm có nền nội thất riêng. Ảnh xem trước của làng ở `asset_review/opening_map_redesign.png`.
- NPC, đoạn thoại mở đầu, điểm dấu vết Trăng Huyết, trận pet lượt đơn giản và cảnh la bàn thức tỉnh.
- Làng có thêm người chăm sóc pet, dân làng và người đi rừng; đứng cạnh và nhấn E để nói chuyện. Hình NPC trong cảnh là bản vẽ mới dựa trên các sprite VQSV5. Vai trò và lời thoại thuộc cốt truyện của prototype.
- Trận mở đầu dùng bố cục màn hình 240 × 320 của VQSV 5. Hai tầng đồng cỏ đối xứng, đối thủ ở trên phải, Lôi Miêu ở dưới trái. Năm lệnh theo thứ tự trong bản gốc. Đạo cụ mở danh sách thuốc và bắt sủng vật. Cửa hàng dùng số xu trong túi. Thanh xanh dưới HP biểu thị lượt dùng Điện giật; huy hiệu số vàng hiển thị phần trăm HP. Mở `/#thu-tran` để thử trực tiếp cuộc chạm trán.
- Bảng mục tiêu đổi theo hội thoại, trận và kết quả; phím tương tác và phím đánh không tự lặp khi giữ để tránh bỏ qua hội thoại hoặc thực hiện nhiều lượt ngoài ý muốn.
- Tắt nội suy ảnh để giữ cạnh pixel; map vẽ nền, đồ vật sau/trước nhân vật theo thứ tự chiều sâu và tránh cắt tile từ atlas không được nạp.
- Kael dùng bộ sprite pixel mới dựa trên hình cũ và tham chiếu NPC VQSV5, với tóc đen, áo xanh, khăn đỏ và la bàn; có 4 hướng và 4 tư thế mỗi hướng. Nhân vật đi liên tục khoảng 220 ms/ô, nhịp chân theo quãng đường. Chạy `python tools/pixel_art.py` để dựng lại 16 khung 32 × 40 từ bộ hình Kael; NPC/pet có nhịp idle.
- Hình dùng thử được sao chép vào `assets/`; nguồn và độ chắc chắn ghi tại `assets/provenance.json`. `data/pet_roster_v1.json` chứa 102 dòng VQSV5 và 100 dòng VQSV6 với ID riêng theo từng game.
- Sổ pet hiển thị đủ 102 dòng VQSV5 có tên và hình từ dữ liệu gốc, cùng 100 dòng VQSV6. Có thể xem ba chuỗi hoạt ảnh cho từng pet VQSV5 với tốc độ gần đúng và gom pet vào bộ sưu tập thử; chưa có chức năng chọn đội chiến đấu hoặc điều khiển pet mới trong trận.

Để dựng lại tài nguyên sổ pet từ nguồn: lần lượt chạy `python tools/decode_vqsv5_pet_tables.py`, `python tools/decode_vqsv5_npc2_animations.py`, `python tools/build_pet_roster_catalog.py`, `python tools/build_pet_gallery_assets.py`, rồi `python tools/build_prototype_bootstrap.py` từ thư mục gốc dự án.

## Ranh giới hiện tại

Đây là prototype tương tác để duyệt vòng lặp chơi. Sprite Kael là hình mới dựng theo mô tả bằng chữ trong kịch bản; không phải sprite gốc của hai game. Kael có chu kỳ đi bộ 4 khung theo 4 hướng; chưa có hoạt ảnh chiến đấu riêng. NPC/pet và một số va chạm vẫn là ứng viên hoặc placeholder. Pet tham khảo đã đổi sang `img_540` (Điện Miêu, DB 78); `img_538` là Điện Nhãn Miêu và không còn được dùng làm ảnh Lôi Miêu. Liên kết Lôi Miêu ↔ Điện Miêu vẫn là lựa chọn thiết kế của prototype, chưa phải bí danh canon từ dữ liệu gốc. Map chưa có âm thanh hoặc bản dịch hoàn chỉnh; hệ lưu tiến trình hiện chỉ phục vụ lát mở đầu. Hình dáng cụ thể của Kael là diễn giải mỹ thuật để dùng trong prototype.

## Chơi trên điện thoại và tìm đường

Kéo joystick ở góc trái để đi, đổi hướng trong khi giữ và thả tay để dừng. Nút ⛶ bật hoặc thoát toàn màn hình khi trình duyệt hỗ trợ. Nút bên phải đổi theo vị trí của Kael: nói chuyện, hái thảo dược, qua cổng hoặc khảo sát cầu. Trong hội thoại, chạm **Tiếp tục** để đọc câu kế. Trên máy tính vẫn có thể dùng phím E và các phím tắt trong menu ☰.

Trong **Menu → Bản đồ**, chọn một cửa tiệm, lối ra hoặc mục tiêu rồi nhấn **Chỉ đường**. Các chấm vàng bám theo đường đi được, ưu tiên mặt đường. Chuyển sang **Thế giới** để tra 12 địa danh. Tại cổng đã mở, đi qua rìa màn hình hoặc bấm nút tương tác để sang khu kế tiếp.

**Khảo sát Suối Sấm** là nhiệm vụ phụ mới của bản thử. Sau cuộc gặp với người gác làng, nói chuyện hết với người đi rừng để nhận lời nhờ. Đi theo hai lối phía đông, kiểm tra giữa cầu gỗ ở Suối Sấm, rồi theo các lối phía tây về báo tin. Có thể lưu và tải lại giữa chuyến đi. Bản lưu trước khi bổ sung nhiệm vụ vẫn dùng được. Hoàn thành chuyến khảo sát trước trận đánh kết thúc chương nếu muốn xem hết nhiệm vụ.


## Vào nhà và mua đồ

Kael bắt đầu với 40 xu. Đến trước cửa nhà bên trái để vào **Tiệm thuốc**, hoặc nhà bên phải để vào **Tiệm bùa**. Bên trong, đến cạnh người bán rồi bấm **Mua đồ** (phím E). Thuốc hồi phục giá 8 xu mỗi liều. Bùa lá rừng giá 24 xu, tự trang bị và giảm 2 sát thương trong mỗi đòn phản công. Dùng cửa phía nam để quay lại làng. Nút **Túi đồ** hiển thị số xu, thuốc và bùa; vào **Menu → Hệ thống → Lưu trò chơi** để giữ vị trí và đồ đã mua. Mỗi tiệm nhận mua lại mặt hàng của mình với nửa giá. Chọn số lượng rồi xác nhận trước khi giao dịch. Nhiệm vụ khảo sát Suối Sấm thưởng 16 xu. Các tiệm, nội thất, giá và hiệu quả là thiết kế cho bản thử này.

Đường chính trong làng lát đá, đường vòng qua vườn và lối rừng dùng đất nện. Sơ đồ trong **Bản đồ khu vực** dùng cùng tuyến với cảnh chơi. Khi chỉnh dữ liệu `trails` trong các trình tạo bản đồ, chạy lại trình tạo tương ứng, sau đó chạy `python tools/paint_trails.py` và `python tools/build_prototype_bootstrap.py` từ thư mục dự án. Bước vẽ cũng kiểm tra có thể đi theo bốn hướng trên mặt đường đến các cửa, NPC, dấu vết và cầu.
