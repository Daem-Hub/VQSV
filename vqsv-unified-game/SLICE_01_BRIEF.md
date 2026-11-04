# VQSV Unified — Brief lát chơi thử 01

**Trạng thái:** phạm vi khởi động đã chốt theo cốt truyện nền v1.0  
**Chương:** 1 — mở đầu ở làng biên giới sương mù  
**Thời lượng mục tiêu:** khoảng 5–10 phút

## Trải nghiệm cần chứng minh

Người chơi điều khiển Kael, gặp Lôi Miêu bị thương, khám phá một khu nhỏ, trò chuyện với một NPC được tuyển từ VQSV5 hoặc VQSV6, rồi cùng Lôi Miêu chống lại một sủng vật bị ảnh hưởng bởi Trăng Huyết. La bàn cũ của cha Kael phát sáng ở đoạn kết để mở câu hỏi cho phần tiếp theo.

## Phạm vi

1. Một khu làng/biên giới nhỏ, dựng từ tile và địa hình phù hợp trong hai bộ tài nguyên.
2. Di chuyển, va chạm và tương tác cơ bản.
3. Kael, Lôi Miêu và một NPC được chọn sau khi đối chiếu danh mục nhân vật thực tế.
4. Một đoạn hội thoại ngắn giới thiệu tình huống và mục tiêu.
5. Một trận pet đơn giản với một đối thủ bị tha hóa; kết thúc bằng cảnh la bàn thức tỉnh.
6. Một điểm kết thúc lát chơi thử, có thể chơi lại từ đầu.

## Chưa đưa vào lát đầu

- Lyra, tuyến tình cảm, Atlantis, Aethelgard và các chương sau.
- Hệ thống bắt/tiến hóa đầy đủ, cửa hàng, kinh tế và nhiệm vụ phụ.
- Boss nhiều giai đoạn, QTE, đổi nhân vật bắt buộc và bộ đếm thời gian.
- Toàn bộ bốn vùng và toàn bộ roster pet.

## Tiêu chí hoàn tất

- Có thể vào map, đi lại và va chạm đúng với vật cản đã xác minh.
- Có thể tương tác NPC và đọc hết đoạn thoại mở đầu.
- Có thể bắt đầu, hoàn tất và thoát khỏi trận pet.
- Cảnh la bàn phát sáng xuất hiện sau chiến thắng.
- Tài nguyên giữ provenance tới game/thư mục nguồn; phần chưa giải mã hoặc chưa xác minh phải được đánh dấu.

## Việc cần xác minh khi hoàn thiện lát chơi thử

- Danh tính NPC, map/tile phù hợp và pet đối thủ qua danh mục asset thật.
- Cấu trúc dữ liệu tối thiểu dùng chung cho map, hội thoại, pet và trận đấu.
- Prototype hiện chạy bằng HTML Canvas; lựa chọn engine cho game hoàn chỉnh vẫn để mở.

Shortlist và mức độ xác minh hiện tại: [SLICE_01_ASSET_SHORTLIST.md](SLICE_01_ASSET_SHORTLIST.md).
Prototype tương tác đang được chỉnh trong [prototype/README.md](prototype/README.md); hiện có chuyển động theo bước và hiệu ứng chiến đấu tạo bằng code, còn frame hoạt ảnh nguồn cần xác minh riêng.

## Ghi nhận rà asset ban đầu

- Ảnh tổng quan VQSV5 cho thấy các map 11–20 có nhiều cảnh ngoài trời với đường đất, cây, đá và nước; đây là nhóm ứng viên để lấy tile/địa hình cho làng biên giới.
- Chưa xác minh tên hoặc vai trò cốt truyện của nhóm map này, nên không lấy nguyên map nào làm bản đồ canon. Cần soi JSON, collision và tileset rồi dựng khu mới.
- Chưa chọn NPC hoặc pet nguồn; lựa chọn sẽ theo khả năng truy ngược sprite/ID và mức phù hợp với đoạn mở đầu.

## Ghi chú liên tục cốt truyện

Trong bản thoại sản xuất, cần làm rõ quan hệ giữa Bóng Ma Tận Thế, Hội Đồng Cổ Đại, Quân Đoàn Cơ Giới Hóa và Tiến sĩ Vane. Thống nhất đại từ của Seraphina. Các chi tiết này chưa ảnh hưởng lát đầu.
