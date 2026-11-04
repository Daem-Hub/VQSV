# Lát chơi thử 01 — Shortlist tài nguyên

**Trạng thái:** rà sơ bộ; chưa khóa asset cụ thể  
**Mục tiêu:** tìm hình và dữ liệu phù hợp cho phần mở đầu Chương 1 mà vẫn truy được nguồn gốc.

| Vai trò | Ứng viên hiện tại | Bằng chứng | Còn phải xác minh |
|---|---|---|---|
| Nền cảnh ngoài trời | VQSV5 map 11–20; ưu tiên map 17 và 19 để dựng khu mới | Contact sheet cho thấy map 17 có đường và nước, map 19 có khoảng trống giữa bãi cỏ/đá; hai bố cục gợi ý tốt cho một clearing ở rìa làng | Tên/vai trò map, tileset cụ thể, collision, khả năng tái sử dụng từng tile; đây là gợi ý bố cục, không phải map đã chốt |
| NPC mở đầu | Ứng viên hình ảnh: VQSV5 `npc0/31` | Contact sheet cho thấy một sprite người lớn tuổi, có thể hợp vai trưởng thôn/người hướng dẫn | Chưa nối được sprite ID 31 với NPC hay hội thoại cụ thể; không coi là nhân vật đã chọn |
| Pet đồng hành | Lôi Miêu theo kịch bản mới | Được nêu trực tiếp trong cốt truyện nền | Tìm sprite/khung hình và dữ liệu pet có thể dùng làm nền; nếu không có mẫu phù hợp thì ghi rõ cần vẽ/dựng mới |
| Pet nguồn để tham khảo | VQSV6 `img_540` = Điện Miêu; `img_538` = Điện Nhãn Miêu | `chs.mid` đặt tên record DB78 là Điện Miêu và DB76 là Điện Nhãn Miêu; `db.mid → sprite.mid → ảnh` nối lần lượt tới `img_540` và `img_538`. Text nhiệm vụ cũng nhắc Điện Miêu. | Tên DB, ID sprite và ảnh đã nối; chưa xác minh `img_540` làm sprite chính thức cho Lôi Miêu của truyện mới |
| Pet đối thủ bị tha hóa | Chưa chọn | Lát đầu cần một pet bị tha hóa để có trận chiến ngắn | Đối chiếu roster, ảnh battle và dữ liệu chiêu/chỉ số; không chọn chỉ dựa trên tên file |
| Nhạc/âm thanh | Chưa chọn | Cả hai game có thư mục âm thanh riêng | Kiểm tra định dạng, thời lượng và ngữ cảnh sử dụng sau khi chốt không khí cảnh mở đầu |

## Nhận định ban đầu

- Map 11–20 chỉ là shortlist hình ảnh để xem tiếp, không phải map canon hay lựa chọn map đã chốt.
- File ảnh nhân vật và pet chủ yếu dùng ID số; tên file tự nó không đủ để kết luận danh tính.
- VQSV5 `npc2` hình ảnh có vẻ là các atlas pet/monster; VQSV5 `battle` chủ yếu là nền và hiệu ứng trận đấu. Danh tính từng pet vẫn cần bảng tham chiếu.
- Có thể dùng tile/địa hình nguồn để dựng khu mới. Không đưa nguyên map cũ vào tuyến truyện nếu chưa được chọn riêng.
- Contact sheet NPC VQSV5: [vqsv5_npc_contact.png](asset_review/vqsv5_npc_contact.png).
- Contact sheet pet/ảnh VQSV6: [vqsv6_img_contact.png](asset_review/vqsv6_img_contact.png).
- Contact sheet map VQSV5 11–20: [vqsv5_maps_11_20_contact.png](asset_review/vqsv5_maps_11_20_contact.png).
- Contact sheet ảnh chiến đấu VQSV5: [vqsv5_battle_contact.png](asset_review/vqsv5_battle_contact.png).
- Contact sheet 11 bộ tileset VQSV5: [vqsv5_tilesets_contact.png](asset_review/vqsv5_tilesets_contact.png); các tile cắt riêng có ID xem tại `vqsv5_tileset_00_tile_ids.png` và `vqsv5_tileset_04_tile_ids.png`.
- Bảng đường dẫn/kích thước của 414 ảnh/tile asset đã rà: [visual_asset_index.csv](asset_review/visual_asset_index.csv).
- Script tái tạo các contact sheet: [make_asset_contact_sheets.py](tools/make_asset_contact_sheets.py).

## Prototype đã dùng thử

- Tileset `00` cung cấp nhà/cây; tileset `04` cung cấp cỏ, đường và địa hình. Bản đồ thử dựng mới trong `prototype/data/opening_map.json`.
- NPC `npc0/31`, người chơi `npc0/30`, pet `img_538` (Điện Nhãn Miêu) và `img_514` (Tà Vân Khuyển Thần) đã được sao chép vào prototype kèm provenance. Đây là asset tham khảo/placeholder; chưa chốt chúng làm nhân vật canon của truyện mới.
- Chạy theo hướng dẫn trong [prototype/README.md](prototype/README.md). Engine quyết định cho prototype: [ENGINE_DECISION.md](ENGINE_DECISION.md).

## Việc kế tiếp

1. Lập contact sheet theo ID cho nhóm NPC, pet và sprite của cả hai game.
2. Đối chiếu ID hình với bảng tham chiếu và script/event để đặt tên đúng cho ứng viên.
3. Chọn một NPC, một pet đồng hành mẫu và một đối thủ; ghi nguồn/độ tin cậy.
4. Sau khi có ứng viên hình ảnh và kiểm tra cấu trúc map, chốt engine/runtime rồi dựng bản chạy đầu.
