# Định dạng map của vqsv5 (PKM – Cầu Vồng)

Tất cả suy ra từ code dịch ngược (at.java, ad.java, ah.java). Cả 68 map đọc hết đến byte cuối cùng.
Kích thước ô: 20x20 pixel. Mọi số nhiều byte đều là little-endian, trừ độ dài ở đầu file tài nguyên (big-endian).

## Các file
| File | Nội dung |
|---|---|
| `data/map/area.dat` | `u16BE độ dài` + [n][n byte: map→bộ tile] [k]{[len][số ảnh]...}. 68 map, 11 bộ tile (0–10), mỗi bộ 1 ảnh. |
| `data/map/N.data` (N=0–10) | `u16BE độ dài` + [số ô+100 (signed byte)] {[số trường+100] s16LE...}. Mỗi ô: `ảnh, src_x, src_y, rộng, cao`. |
| `data/map/N.png` (N=0–10) | Ảnh bộ tile (ví dụ 140x140). Ô được cắt theo bảng `.data`, kích thước tuỳ ý (nhà 40x60...). |
| `data/map/N.mid` (N=0–67) | **Map** (tên file `.mid` nhưng KHÔNG phải nhạc). Cấu trúc bên dưới. |
| `data/map/mN.d` | Dữ liệu kèm map. `m0`–`m67` khớp 1-1 với map 0–67 và đọc được hết. `m68`–`m78` (11 file) có cấu trúc khác, không thuộc map nào. |

## File map `N.mid`
```
2 byte bỏ qua
u32LE  màu nền 0x00RRGGBB
u8 n   (=1), n byte bỏ qua, n byte RỘNG (ô), n byte CAO (ô)
lặp 3 lần:
  u8 loại      (1 = lớp nền, 2 = lưới va chạm, 3 = lớp phủ trên nhân vật, 0xFF = không có)
  u32LE số mục
  mỗi mục: u8 x | u8 y (lưu từ DƯỚI lên: hàng = cao-1-y) | s16LE giá trị
```
Giá trị: `>> 3` = số ô trong bảng `.data` (lớp 1 và 3) hoặc loại địa hình (lớp 2); `& 7` = kiểu lật/xoay,
đổi qua bảng `[0,5,3,6,2,4,1,7]` ra hằng số MIDP (`drawRegion`). Giá trị âm = bỏ qua.
Ô được vẽ neo ở góc dưới-trái của ô lưới (BOTTOM|LEFT), nên ô cao hơn 20px mọc lên phía trên.
Lớp 3 (lớp phủ) vẽ SAU nhân vật, ví dụ tán cây, mái nhà.

## Lưới va chạm (lớp 2)
Giá trị `>>3` gặp trong dữ liệu: 1, 2, 3, 4 (ô không có mục = 0 = đi được).
Quy tắc đọc từ code (hàm kiểm tra ô):
- `1`: luôn chặn.
- Với chế độ di chuyển `c`: c=0 chặn khi 2 hoặc 4; c=2 chặn khi 2 hoặc 3; c=4 chặn mọi giá trị trừ 2;
  các chế độ còn lại chặn khi 2, 3 hoặc 4.
- Xác nhận bằng mắt: `3` phủ đúng các vùng nước ở map 11.
- Một số hàm khác so sánh với 2/3/4 theo hướng (0,2,4), có thể là bậc nhảy hoặc mặt trượt. Chưa kiểm chứng.

## Chưa chắc chắn
- `mN.d`: gồm hai mảng byte và một danh sách mảng byte. Mảng đầu hợp với bảng quái hoang dã
  `[?, cấp_min, biên_độ, id_quái...]` ở nhiều map (ví dụ map 10: cấp 16–18, quái 21, 25, 31), đây là SUY ĐOÁN từ
  hàm chọn quái trong code. Các map thành phố có cấu trúc khác (mảng thứ hai có dữ liệu). Chưa rõ ý nghĩa.
- Cổng dịch chuyển giữa map và vị trí NPC chưa thấy trong các file trên. Có thể nằm ở `data/event`, `map.d`, `data.d`.
- Thứ tự id quái chưa gắn được với tên pet (cần giải mã dữ liệu pet).
