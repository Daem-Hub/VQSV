# Báo cáo tài nguyên: vqsv5 (PKM – Cầu Vồng) và vqsv6 (Vương Quốc Pokemon)

## 1. Hai game khác nhau thế nào
| | vqsv5 | vqsv6 |
|---|---|---|
| MIDlet chính | main.XConnection | game.GameMIDLet |
| Tác giả đóng gói | Holyeyed | GiaiTri321 |
| Cốt truyện (theo văn bản) | Thành Cầu Vồng, Bành Bành, nhiệm vụ bắt sủng vật | Làng Neil, Hắc Long Quân, thần thú, đạo quán |
| Ảnh | PNG thật (đúng đuôi) | PNG thật nhưng bị đổi đuôi thành .mid (242 file ở data/img) |
| Nhạc | 9 file MIDI trong music/ | Chỉ 1 MIDI (data/sound/0.mid); nhạc map có thể nằm trong data/map |
| Dữ liệu | .d / .t / .data (nhị phân riêng) | .mid / .mib đổi đuôi (script, event, mod, spr, tex, ui) |
| Còn sót | Tin nhắn thu phí (yicha*.txt, sms.t) | Chuỗi gửi tin nhắn thu phí trong chs.mid |

## 2. Cấu trúc thư mục đã trích
- `vqsv5/images`, `vqsv6/images`: ảnh PNG, đã sửa lại đúng đuôi .png.
- `vqsv5/music`, `vqsv6/music`: file MIDI thật.
- `vqsv5/raw_data`, `vqsv6/raw_data`: dữ liệu nhị phân chưa giải mã (map, sprite, NPC, kịch bản).
- `text/`: văn bản tiếng Việt trích từ nhiệm vụ, thoại, kỹ năng, cảnh phim. Mỗi chuỗi là một dòng.
  Lưu ý: định dạng chuỗi là UTF-16 có tiền tố độ dài, mình đoán nên một số dòng bị mất vài ký tự đầu và lẫn ký tự rác ở cuối.

## 3. Thống kê
- vqsv5: 159 file map nhị phân, 11 ảnh map, 3 nhóm NPC (npc0 79 ảnh, npc1 7, npc2 54), 11 ảnh chiến đấu, 9 file nhạc.
- vqsv6: 94 file map, 242 ảnh (thẻ pet/nhân vật), 299 file sprite (spr), 40 file giao diện (ui), 13 cảnh phim (event), 17 file kịch bản.
- Văn bản trích được: khoảng 4.600 chuỗi.

## 4. Những gì CHƯA giải mã được
- Định dạng map (.d / map/*.mid): chưa biết bố cục tile.
- Dữ liệu pet và chỉ số: nằm trong data.d, monN.d, monType.d (vqsv5) và script/sprite.mid, petArea.mid (vqsv6). Chưa đọc được cấu trúc.
- Sprite/khung hoạt hình (vqsv6 data/spr): chưa biết cách ghép thành khung hình.
- Cơ chế chiến đấu: nằm trong bytecode đã làm rối, chưa đọc.

## 5. Kế hoạch dựng game mới (gợi ý)
1. Xác định 1 engine mới (Godot hoặc Phaser) và độ phân giải mục tiêu (240x320 cho gần bản gốc).
2. Giải mã map vqsv5 trước (nhiều map nhất, có kèm ảnh tile) và dựng lại bằng tilemap.
3. Giải mã dữ liệu pet: lập bảng chung gồm id, tên, hệ, chỉ số, chiêu, đường tiến hóa. Gộp từ data.d (vqsv5) và sprite.mid (vqsv6).
4. Dùng ảnh thẻ pet ở vqsv6/images làm hình đại diện, ảnh chiến đấu ở vqsv5/images/data/battle làm nền.
5. Viết lại chiến đấu theo kiểu của vqsv6 (đã mô tả bằng văn bản chs/bufDebuf), dựa trên bảng chung.
6. Viết kịch bản nối: kết thúc chuỗi nhiệm vụ vqsv5 thì mở vùng vqsv6.
