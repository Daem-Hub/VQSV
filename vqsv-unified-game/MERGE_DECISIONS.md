# Danh sách lựa chọn hợp nhất VQSV5 + VQSV6

> Đã ghi nhận lựa chọn của anh ngày 2026-10-26. “Bỏ” nghĩa là không đưa vào bản game mới; tài nguyên gốc vẫn được giữ trong bộ nguồn. Có thể đổi quyết định sau.

## Lựa chọn đã ghi nhận

| Mục | Chọn | Quyết định |
|---|---|---|
| 1. Cốt truyện | **D** | Viết cốt truyện mới, giữ nhân vật và thế giới từ hai game |
| 2. Bản đồ | **D** | Dựng thế giới mới từ tile, nhà cửa và địa hình cũ |
| 3. Nhân vật/NPC | **C** | Chọn nhân vật từ cả hai game và viết lại các cuộc gặp |
| 4. Pet/chỉ số | **C** | Gộp roster, chuẩn hóa ID và cân bằng lại |
| 5. Chiến đấu | **C** | Kết hợp cơ chế phù hợp của cả hai |
| 6. Bắt/tiến hóa | **C** | Thống nhất điều kiện bắt và nhánh tiến hóa |
| 7. Nhiệm vụ | **D** | Viết tuyến chính mới từ chủ đề/cảnh của hai game |
| 8. Vật phẩm/kinh tế | **C** | Gộp vật phẩm tương đương, một kho đồ và một hệ tiền tệ |
| 9. Hình ảnh/hoạt ảnh | **C** | Dùng hình ảnh từ cả hai, thống nhất tỷ lệ hiển thị và giao diện |
| 10. UI/âm thanh | **C** | Chọn phần phù hợp của mỗi game và thống nhất lại |
| 11. Bản đầu | **A** | Khám phá map, NPC và một trận pet |

### Hướng thiết kế rút ra

- Đây là một game có thế giới và tuyến truyện mới; map cũ được dùng làm nguồn tile/địa hình, không ghép nguyên tuyến map cũ thành thế giới chính.
- Nhân vật và pet từ hai game được tuyển chọn rồi đưa vào một hệ thống chung.
- Bản chơi thử cần có khám phá, hội thoại NPC và một trận pet ngay từ đầu.
- Tuyến cổng `VQSV5 map 0 → map 1 → VQSV6 map 0` ở `opening_route.json` đã được thay bằng hướng dựng thế giới mới; giữ file đó làm nghiên cứu tham khảo, không dùng làm tuyến chính.

## Cách chọn

Với từng mục, chọn một phương án:

- **A — Giữ VQSV5 làm chuẩn**
- **B — Giữ VQSV6 làm chuẩn**
- **C — Gộp cả hai**, rồi cân bằng/chỉnh lại để cùng hoạt động
- **D — Làm mới** dựa trên ý tưởng của hai game
- **E — Bỏ khỏi bản hợp nhất**

## Các quyết định

### 1. Cốt truyện và thứ tự game

- A. Chơi VQSV5 trước, hoàn thành chương rồi mở VQSV6
- B. Mở đầu ở VQSV6, sau đó quay lại nội dung VQSV5
- C. Hai game thành hai vùng/chương song song, có cổng qua lại
- D. Viết cốt truyện mới, chỉ giữ nhân vật và thế giới
- E. Chỉ lấy một cốt truyện

### 2. Bản đồ và vùng chơi

- A. Giữ map VQSV5 làm phần chính, chọn một số map VQSV6 để nối
- B. Giữ map VQSV6 làm phần chính, chọn một số map VQSV5 để nối
- C. Giữ các map hay của cả hai, nối thành một thế giới liên tục
- D. Dựng thế giới mới từ các tile, nhà cửa và địa hình cũ
- E. Chỉ giữ một bộ map

### 3. Nhân vật chính, NPC và hội thoại

- A. Giữ nhân vật/NPC VQSV5
- B. Giữ nhân vật/NPC VQSV6
- C. Chọn nhân vật từ cả hai game; viết lại phần gặp gỡ và hội thoại nối
- D. Tạo nhân vật mới, dùng NPC cũ làm nhân vật phụ
- E. Bỏ các NPC/nhiệm vụ phụ, chỉ giữ tuyến chính

### 4. Danh sách pet và chỉ số

- A. Dùng pet và chỉ số VQSV5
- B. Dùng pet và chỉ số VQSV6
- C. Gộp toàn bộ pet; chuẩn hóa ID và cân bằng lại chỉ số
- D. Chọn một roster nhỏ từ cả hai, thiết kế chỉ số mới
- E. Chỉ giữ pet của một game

### 5. Chiến đấu

- A. Lấy cơ chế VQSV5 làm chuẩn
- B. Lấy cơ chế VQSV6 làm chuẩn
- C. Kết hợp cơ chế phù hợp của cả hai
- D. Thiết kế trận đấu mới, dùng lại pet/chiêu/thành phần hình ảnh
- E. Để chiến đấu ngoài bản đầu, tập trung vào khám phá trước

### 6. Bắt pet, tiến hóa và phát triển đội hình

- A. Giữ cách bắt/tiến hóa VQSV5
- B. Giữ cách bắt/tiến hóa VQSV6
- C. Gộp, thống nhất điều kiện bắt và nhánh tiến hóa
- D. Làm hệ thống mới
- E. Giảm tính năng, chỉ giữ bắt pet hoặc chỉ giữ tiến hóa

### 7. Nhiệm vụ và tiến trình

- A. Theo nhiệm vụ chính VQSV5
- B. Theo nhiệm vụ chính VQSV6
- C. Nối hai tuyến thành các chương; chọn nhiệm vụ phụ tiêu biểu
- D. Viết tuyến chính mới từ các chủ đề/cảnh cũ
- E. Bỏ nhiệm vụ phụ và hoạt động lặp lại

### 8. Vật phẩm, tiền tệ và cửa hàng

- A. Dùng bảng vật phẩm/giá VQSV5
- B. Dùng bảng vật phẩm/giá VQSV6
- C. Gộp vật phẩm tương đương, dùng một kho đồ và một hệ tiền tệ
- D. Làm lại cân bằng kinh tế
- E. Bỏ cửa hàng/tính năng không cần cho bản đầu

### 9. Hình ảnh, hoạt ảnh và phong cách

- A. Giữ phong cách VQSV5
- B. Giữ phong cách VQSV6
- C. Giữ hình ảnh cả hai, thống nhất kích thước hiển thị và giao diện
- D. Vẽ lại theo một phong cách chung, dùng ảnh cũ làm tham khảo
- E. Chỉ giữ một số nhân vật, pet và map tiêu biểu

### 10. Giao diện, âm thanh và nhạc

- A. Dùng giao diện/âm thanh VQSV5
- B. Dùng giao diện/âm thanh VQSV6
- C. Chọn phần tốt của mỗi game rồi thống nhất lại
- D. Làm mới giao diện, giữ nhạc/âm thanh cũ được chọn
- E. Để phần này sau bản chơi thử

### 11. Tính năng đưa vào bản đầu

- A. Khám phá map + NPC + một trận pet
- B. Thêm nhiệm vụ và vật phẩm ngay từ đầu
- C. Thêm bắt pet/tiến hóa ngay từ đầu
- D. Chỉ làm bản đi lại và chuyển map để kiểm tra dữ liệu
- E. Anh chỉ định một nhóm tính năng khác

## Thứ tự triển khai đề xuất

1. **Bản đồ và di chuyển:** nạp một map mỗi game, va chạm, điểm chuyển vùng.
2. **NPC và cốt truyện:** một đoạn hội thoại, một cờ mở đường nối.
3. **Pet và chiến đấu:** một pet đại diện từ mỗi game, một trận thử.
4. **Tiến trình:** nhiệm vụ, bắt pet, tiến hóa, vật phẩm và lưu game.
5. **Mở rộng nội dung:** thêm map/NPC/pet theo từng cụm đã xác nhận.
6. **Hoàn thiện:** giao diện, âm thanh, hoạt ảnh và đóng gói.

## Mẫu trả lời nhanh

Anh có thể trả lời theo mã, ví dụ: `1A, 2C, 3C, 4D, 5B, 6C, 7C, 8C, 9C, 10E, 11A`.

Nếu muốn quyết dần, chỉ cần chọn **mục 1–3 trước**; các mục còn lại để mở. Sau khi chốt mỗi mục, em sẽ cập nhật thiết kế và triển khai đúng phần đó.
