# Quyết định runtime cho lát prototype

**Quyết định:** dùng HTML Canvas 2D và JavaScript thuần cho lát chơi thử đầu tiên.

## Lý do

- Máy hiện có Node.js nhưng không thấy Godot trong PATH; không cần cài engine hoặc kéo thư viện ngoài để tạo bản chạy.
- Lát đầu chỉ cần tilemap nhỏ, di chuyển, tương tác NPC, hội thoại và một trận pet theo lượt.
- Canvas cho phép thử nhanh vòng lặp chơi và giữ dữ liệu map/sprite độc lập trong JSON cùng asset PNG.

## Phạm vi của quyết định

Đây là quyết định cho prototype, không khóa engine của game hoàn chỉnh. Sau khi vòng lặp và mô hình dữ liệu được duyệt, có thể chuyển dữ liệu chung sang engine chính thức nếu cần.

## Giới hạn

- Prototype cần được mở qua máy chủ HTTP cục bộ do trình duyệt giới hạn `fetch()` khi mở file trực tiếp.
- Chưa triển khai save/load, mobile controls, âm thanh, menu đầy đủ hoặc hoạt ảnh pet.
- Một số hình nhân vật/pet đang là ứng viên chưa khớp ID với dữ liệu script; chúng được ghi rõ trong provenance.
