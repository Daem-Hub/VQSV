# Rà soát nguồn VQSV_FIX_2.7

Nguồn: `VQSV_FIX_2.7.zip`, bản VQSV Liệt Hỏa Rebuild do anh cung cấp. Đối chiếu với `vqsv-6.jar` hiện có; không coi hai bản là hoàn toàn giống nhau hoặc mặc định bản ZIP là bản web Hồi Sinh VQSV.

| Nhóm | Nội dung trong ZIP | Cách sử dụng hiện tại |
| --- | --- | --- |
| Script | 19 file gốc, 15 bản giải mã | `db.mid` giống từng byte bản VQSV6 đang dùng. Đã đối chiếu mã để đưa cấp, nguyên liệu và loại biến đổi của 38 pet vào sổ. Các file script khác cần kiểm tra trước khi hợp nhất. |
| Map | 102 file gốc và 102 bản giải mã | 86 file khớp, 8 khác và 8 không tìm thấy cùng đường dẫn trong JAR. Có thể làm nguồn so sánh bản đồ; chưa tự thay thế thế giới theo cốt truyện mới. |
| Ảnh | 308 file gốc và 308 ảnh giải mã | 98 khớp, 144 khác, 66 không tìm thấy cùng đường dẫn. Cần xem và đối chiếu ID trước khi chọn hình mới. |
| Sprite | 325 file gốc | 148 khớp, 151 khác, 26 không tìm thấy cùng đường dẫn. Chưa mặc định animation tương thích với dữ liệu của bản cũ. |
| Giao diện | 42 file gốc và 42 bản giải mã | Có giao diện chiến đấu, sổ/trạng thái pet và thiết lập; mã game mẫu được dùng đối chiếu các trường tiến hóa. |
| Sự kiện | 16 file gốc, 14 bản giải mã | Dùng đối chiếu nhịp game và bối cảnh, không thay cốt truyện mới đã chốt. |
| Âm thanh | 7 file gốc | Để nghiên cứu và nghe thử trước khi lựa chọn; prototype chưa gắn nhạc. |
| Mã Java | 118 file Java dịch ngược cùng tài nguyên khác | Bản đối chiếu nằm trong `reference_vqsv_fix_2_7/source_code`; tên bị làm rối và có đoạn dịch ngược không đầy đủ. |

Số liệu “không tìm thấy” chỉ nghĩa là không tìm thấy **cùng đường dẫn** trong JAR, không chứng minh tài nguyên hoàn toàn mới. Bảng [kiểm kê và SHA-256](asset_review/vqsv_fix_2_7_inventory.csv) ghi từng file gốc trong ZIP; có thể chạy `python tools/audit_vqsv_fix.py` với `VQSV_SOURCE_ROOT` trỏ tới thư mục chứa ZIP và `vqsv-6.jar` để dựng lại.

Prototype đã có bộ lọc 5 nguyên liệu tiến hóa VQSV6 và liên kết xem dạng tiếp theo trong sổ pet. Đây là dữ liệu tra cứu, chưa phải kho đồ, bắt pet hoặc tiến hóa có thể chơi. Tên và điều kiện từ bản gốc vẫn cần cân bằng cho game hợp nhất.
