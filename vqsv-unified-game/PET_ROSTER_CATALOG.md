# Pet roster hợp nhất — catalog sơ bộ

**Trạng thái:** v1.3, roster có 102 dòng pet VQSV5 và 100 dòng pet VQSV6, giữ ID theo từng game. VQSV5 đã nối tên, atlas, dạng hình và ba chuỗi animation cho mỗi dòng. VQSV6 đã nối tên, hệ, sprite và 38 liên kết tiến hóa suy luận. Chỉ số, kỹ năng và điều kiện thu phục/tiến hóa còn cần giải trước khi triển khai đầy đủ trong trận.

## Kết quả rà soát hiện tại

- **VQSV5:** `data.d` có 102 dòng tên và 102 cặp `[atlas, dạng]`. `ak.j()` nạp bảng tên vào `ak.j[]` và cặp hình vào `ak.e[][]`; `t.class` dùng cùng chỉ số dòng để chọn atlas và lấy chuỗi `dạng × 3`. 102 dòng bao phủ 101 tổ hợp atlas/dạng trên 53 atlas đánh số. Dòng 85 và 86 cùng tên **Quỷ Vương Địa Phủ** và dùng chung hình; cả hai được giữ theo ID nguồn. `37old.png` là ảnh cũ không có metadata riêng trong JAR và không được bảng 102 dòng tham chiếu.
- `monN.d` có 34 giá trị trỏ tới 17 atlas; quan hệ giữa các giá trị này và 102 dòng `data.d` vẫn cần làm rõ. Danh sách 34 tham chiếu được giữ riêng để nghiên cứu.
- `monType.d` có header đếm 85 byte giá trị (87 byte cả header). Con số này bằng 5 × 17 atlas khác nhau mà `monN.d` tham chiếu; đây mới là trùng khớp về kích thước, chưa chứng minh bảng có 17 record × 5 trường. Không chuyển các số này thành hệ hoặc chỉ số.
- **VQSV6:** bảng đầu `db.mid` có 100 record ID 10–109; tên tại cùng ID trong `chs.mid` và ảnh qua `sprite.mid` tạo thành roster 100 pet có tên. Trường hệ đã nối với 7 nhãn hệ trong `chs.mid`; trường tiến hóa nối được 38 record tới record cùng hệ có tên dạng tiếp theo. Ba starter còn được đối chiếu với lời thoại. Đã nối sơ bộ 111 mục `petArea.mid` theo chỉ số dòng DB tới 55 pet. Dải ảnh 500–621 còn asset ngoài roster pet.
- **Truyện mới:** có 7 ghi nhận về pet/đối thủ quan trọng; tính cả dạng tiến hóa được gọi tên riêng thì có 8 tên. Chỉ nhánh **Lôi Miêu → Cực Lôi Hoàng Hổ** được nêu rõ; điều kiện và cấp tiến hóa chưa được chốt.

## Pet và sinh vật đã được gọi tên trong truyện

| Tên | Vai trò theo truyện | Tiến hóa/quan hệ | Độ chắc chắn và phần còn thiếu |
|---|---|---|---|
| Lôi Miêu | Pet đồng hành khởi đầu của Kael; tai phải có sẹo/dị tật | Tiến hóa thành Cực Lôi Hoàng Hổ | Tên, vai trò và quan hệ tiến hóa là canon truyện; chưa nối được sprite nguồn. |
| Cực Lôi Hoàng Hổ | Dạng tiến hóa chiến đấu của Lôi Miêu | Lôi Miêu → Cực Lôi Hoàng Hổ | Canon truyện; điều kiện, chỉ số và sprite chưa xác minh. |
| Viêm Long Tí Nị | Kael cứu và thu phục tại Hỏa Sơn Bất Diệt | Chưa nêu | Canon truyện; ID ảnh nguồn và chuỗi tiến hóa chưa xác minh. |
| Tuyết Y Linh Thú | Được đánh thức, thanh tẩy ở Hồ Băng Cổ Xưa | Chưa nêu | Canon truyện; chưa rõ có gia nhập đội/thu phục được không. |
| Thương Hải Thần Cự Kình | Thần thú có quả trứng cuối cùng được Lyra bảo vệ | Gắn với quả trứng tại Atlantis | Canon truyện; vai trò đồng minh hay pet điều khiển chưa chốt. |
| Nham Bạo Long | Boss do Boran tạo ra từ Cổ Thú Nham Thạch và giáp/lò phản ứng | Dạng cơ giới hóa, không mặc định bắt được | Canon truyện; thiết kế/ID ảnh nguồn chưa xác minh. |
| Kính Giới Thiên Sứ | Boss kết hợp với Seraphina ở Tầng Mây Khởi Nguyên | Chưa nêu | Canon truyện; thiết kế/ID ảnh nguồn chưa xác minh. |
| Cổ Long Hắc Ám | Boss tại Tháp Thiên Không trong Chương 1 | Chưa nêu | Canon truyện; thiết kế/ID ảnh nguồn chưa xác minh. |

> Bảng trên tách dạng tiến hóa thành một dòng để dễ rà hình; CSV giữ 7 ghi nhận gốc và ghi Cực Lôi Hoàng Hổ trong trường tiến hóa của Lôi Miêu. Thực Thể Thứ 0 chưa đưa vào catalog pet vì truyện mô tả nó là thực thể cổ xưa, chưa xác nhận là sủng vật.

## Bảng pet VQSV6 nối tới ảnh

`db.mid` bắt đầu bằng bảng 100 record (ID 10–109), mỗi record dài 23 số. Tên của record nằm tại cùng chỉ số trong `chs.mid`. Trong các trường đánh số từ 0, trường 17 khớp khóa đầu của một hàng `sprite.mid`, rồi dẫn tới ảnh riêng. Trường 1 là mã hệ và khớp với nhãn hệ qua `chs.mid[365 + mã_hệ]`.

| Chỉ số trường | Tên đã giải | Cách đối chiếu |
|---:|---|---|
| 0 | `db_id` | Mã record dùng tra tên tại cùng ID trong `chs.mid`. |
| 1 | `element_id` | Các mã 0–6 tra thành nhãn hệ tại `chs.mid[365..371]`; ví dụ ba pet starter lần lượt Hỏa, Mộc, Thủy như lời thoại. |
| 17 | `sprite_key` | Khớp cột đầu của `sprite.mid`, cột sau đưa tới ID ảnh. |
| 19 | `evolution_target_row_index` | Nếu không âm thì trỏ tới dòng pet khác trong bảng; 38 liên kết đều trỏ tới pet cùng hệ, tạo thành các cặp/chuỗi tên hợp lý. Giá trị `-1` không có mục tiêu ở trường này. |
| 2–16, 18, 20–22 | Chưa giải | 19 trường còn lại giữ nguyên số thô; chưa gán ý nghĩa cho trường 20–22. |

- [CSV 100 record, ảnh và mục tiêu tiến hóa](asset_review/vqsv6_pet_db_sprite_map.csv), gồm mã/nhãn hệ cùng các trường số thô.
- [Danh sách 38 liên kết tiến hóa](asset_review/vqsv6_pet_evolution_links.csv), có ảnh nguồn/đích và ba trường số 20–22 chưa giải nghĩa.
- [CSV tham chiếu `petArea.mid`](asset_review/vqsv6_pet_area_roster.csv), nối các tuple pet tới DB ID, tên, hệ và ảnh.
- [JSON các bảng `db.mid`, `sprite.mid`, `petArea.mid`](asset_review/vqsv6_db_sprite_numeric_tables.json); giữ dữ liệu gốc và ghi rõ các trường đã giải cùng cấu trúc pet-area tạm thời.
- [Contact sheet 100 record theo ID DB và ảnh](asset_review/vqsv6_pet_db_contact.png).
- [Catalog 401 dòng](asset_review/pet_roster_candidates.csv) tách record pet, asset ảnh, nội dung lời thoại và pet mới trong truyện.
- [Roster dữ liệu dùng chung v1](prototype/data/pet_roster_v1.json) có 102 dòng VQSV5, 100 dòng VQSV6, starter IDs và nhánh tiến hóa VQSV6 suy luận.
- [Bảng 102 pet VQSV5, atlas và dạng hình](asset_review/vqsv5_pet_db_sprite_map.csv) giữ cả mô tả và các số gốc.
- [Ảnh tổng quan 102 pet VQSV5](asset_review/vqsv5_pet_102_contact.png) ghép ảnh đại diện và tên theo thứ tự dòng nguồn.
- Trong JSON, 34 mục VQSV5 nằm riêng ở `unresolved_source_references`, không nằm trong danh sách pet có thể chọn; mỗi mục giữ index `monN` và atlas ảnh để nối tiếp khi tìm được frame.

Ba lựa chọn khởi đầu hiện có chuỗi đối chiếu:

| Tên trong lời thoại | Tên trong bảng game (`chs.mid`) | Pet DB | Sprite table | Ảnh |
|---|---|---:|---:|---|
| Rồng Bảo Bối, hệ Hỏa | Long bảo bối | 16 | 92 | `img_506` |
| Ô Lá Ếch Frog, hệ Mộc | Diệp Tán Oa | 26 | 102 | `img_574` |
| Thủy thủ cánh cụt PenGuin, hệ Thủy | Thủy thủ chim cánh cụt | 61 | 137 | `img_520` |

Sau bảng đầu là 8 bảng số phụ. Em đã trích cả 9 bảng vào JSON. Còn 19 trường của record pet và schema của 8 bảng phụ chưa được giải; không suy ra chỉ số/chiêu chỉ từ kiểu hoặc độ lớn của số.

Đối chiếu mã dịch ngược của bản mẫu đã xác định `local_value` là dòng pet đích và nối được cột 20–21 với nguyên liệu. Xem mục “Đối chiếu mẫu VQSV_FIX_2.7” bên dưới; cột 22 và các trường còn lại vẫn cần nghiên cứu.

### Tham chiếu pet-area VQSV6

`petArea.mid` có 119 dòng. Dòng rỗng có 3 số và kết thúc bằng `-1`; các dòng còn lại có 5 số đầu chưa đặt nghĩa, sau đó là các cụm 4 số lặp lại. Số đầu mỗi cụm được nối tạm như chỉ số dòng 0–99 của bảng pet: cả 111 giá trị đều nằm trong dải này, còn coi chúng là DB ID trực tiếp thì các giá trị 1, 4, 9 không có record tương ứng. Theo cách nối đó có 55 pet duy nhất. Cần xác nhận lookup bằng code gameplay trước khi chốt tên trường; ba số còn lại mỗi cụm và 5 trường đầu vẫn để thô.

CSV: [vqsv6_pet_area_roster.csv](asset_review/vqsv6_pet_area_roster.csv). Tên trường `header_0..4` và `tuple_aux_0..2` chỉ biểu thị vị trí, không phải nhãn gameplay. Mối nối theo chỉ số dòng là suy luận từ cấu trúc/dải ID; cần đối chiếu lookup trong gameplay code trước khi dùng như dữ liệu canon.

## Pet khởi đầu được xác nhận trong VQSV6

Script sự kiện ghi rõ ba pet được đưa cho người chơi chọn. Tên và hệ lấy từ lời thoại đã giải mã. Liên kết DB→sprite→ảnh đã xác minh. Tên DB tương ứng được đối chiếu bằng tên hiển thị trong `chs.mid` và hình pet; bí danh starter trong lời thoại không có cột bí danh riêng trong bảng DB đã trích.

| Pet trong lời thoại | Hệ | Tên gốc trong DB | DB → sprite → ảnh | Mức xác minh |
|---|---|---|---|---|
| Thủy thủ cánh cụt PenGuin | Thủy | Thủy thủ chim cánh cụt | 61 → 137 → `img_520` | Chỉ số DB, khóa sprite và ảnh nối trực tiếp qua bảng; tên DB và hình khớp tên starter. |
| Ô Lá Ếch Frog | Mộc | Diệp Tán Oa | 26 → 102 → `img_574` | Chỉ số DB, khóa sprite và ảnh nối trực tiếp qua bảng; tên DB và hình khớp mô tả ếch lá. |
| Rồng Bảo Bối | Hỏa | Long bảo bối | 16 → 92 → `img_506` | Chỉ số DB, khóa sprite và ảnh nối trực tiếp qua bảng; tên DB tương ứng tên starter. |

Nguồn lời thoại: `vqsv6/text/vqsv6__data__event__scene_1.mid.txt`, dòng 42–53. [Ảnh so sánh ba ứng viên](asset_review/vqsv6_starter_pet_candidates.png). CSV giữ riêng mỗi pet canon và mỗi ảnh ứng viên để không biến nhận dạng bằng mắt thành ID đã xác nhận.

## Ứng viên hình ảnh cần đối chiếu

| Nguồn | Ứng viên | Bằng chứng hiện có | Trạng thái |
|---|---|---|---|
| VQSV6 | `db[78] → sprite[154] → img_540` | `chs.mid[78]` là Điện Miêu; nhiệm vụ cũng yêu cầu bắt Điện Miêu | Tên/record/ảnh khớp theo ID và tên nhiệm vụ; prototype dùng làm ứng viên cho Lôi Miêu nhưng chưa coi là cùng danh tính canon. |
| VQSV6 | `db[76] → sprite[152] → img_538` | `chs.mid[76]` là Điện Nhãn Miêu | Phân biệt với Điện Miêu; không dùng ảnh này làm pet nhiệm vụ. |
| VQSV6 | `db[24] → sprite[100] → img_514` | `chs.mid[24]` là Tà Vân Khuyển Thần | Prototype đang dùng làm đối thủ tạm; vai trò trận chưa xác minh. |
| VQSV6 | `db[16]→sprite[92]→img_506`; `db[26]→sprite[102]→img_574`; `db[61]→sprite[137]→img_520` | Tên DB lần lượt là Long bảo bối, Diệp Tán Oa, Thủy thủ chim cánh cụt | Các tên này khớp ba lựa chọn starter theo tên và ảnh. |
| VQSV5 | `monN[0..33] → npc2/{id}.png` | 34 giá trị trong `monN.d` đều trỏ tới ảnh `npc2` tồn tại; có 17 atlas đích duy nhất | Quan hệ giữa `monN` và dòng pet trong `data.d` chưa xác minh. |
| VQSV5 | `npc2/1.png`–`npc2/53.png`, `npc2/37old.png` | Nhóm ảnh atlas pet/monster; mỗi ảnh có file metadata `.data` tương ứng | Cần nối metadata khung ảnh theo từng mục `monN` và tìm tên từ bảng game. |

Ảnh xem nhanh: [contact sheet VQSV5](asset_review/vqsv5_npc_contact.png) · [contact sheet VQSV6](asset_review/vqsv6_img_contact.png) · [17 atlas mà `monN.d` tham chiếu](asset_review/vqsv5_monN_atlas_contact.png). Nhãn trong ảnh ghi chỉ số bảng trỏ tới từng atlas.

Danh sách 401 dòng pet, asset và story records: [pet_roster_candidates.csv](asset_review/pet_roster_candidates.csv). CSV phân biệt ID bảng loài, ID ảnh và ghi nhận truyện để tránh vô tình xem một atlas hoặc một frame là một pet riêng.

### Ánh xạ `monN` đã xác nhận

| Chỉ số bảng | Atlas ảnh | Chỉ số bảng | Atlas ảnh | Chỉ số bảng | Atlas ảnh | Chỉ số bảng | Atlas ảnh |
|---:|---|---:|---|---:|---|---:|---|
| 0 | `npc2/33.png` | 1 | `npc2/3.png` | 2 | `npc2/10.png` | 3 | `npc2/7.png` |
| 4 | `npc2/10.png` | 5 | `npc2/9.png` | 6 | `npc2/2.png` | 7 | `npc2/2.png` |
| 8 | `npc2/3.png` | 9 | `npc2/11.png` | 10 | `npc2/11.png` | 11 | `npc2/8.png` |
| 12 | `npc2/10.png` | 13 | `npc2/13.png` | 14 | `npc2/5.png` | 15 | `npc2/8.png` |
| 16 | `npc2/10.png` | 17 | `npc2/8.png` | 18 | `npc2/5.png` | 19 | `npc2/5.png` |
| 20 | `npc2/6.png` | 21 | `npc2/6.png` | 22 | `npc2/11.png` | 23 | `npc2/3.png` |
| 24 | `npc2/14.png` | 25 | `npc2/18.png` | 26 | `npc2/21.png` | 27 | `npc2/10.png` |
| 28 | `npc2/10.png` | 29 | `npc2/8.png` | 30 | `npc2/35.png` | 31 | `npc2/52.png` |
| 32 | `npc2/30.png` | 33 | `npc2/3.png` |  |  |  |  |

### Frame VQSV5 đã tách từ metadata

Đọc các hàm giải mã sprite trong `vqsv5.jar` cho thấy `.data` chứa ba lớp dữ liệu: hình chữ nhật cắt atlas, các phần ghép thành tư thế, và chuỗi animation. Công cụ [decode_vqsv5_npc2_animations.py](tools/decode_vqsv5_npc2_animations.py) đã khôi phục 2.224 crop, 859 tư thế ghép và 303 chuỗi animation từ cả 53 atlas đánh số; các preview được ráp lại từ metadata nguồn.

Hàm vẽ `ah.class` dùng bảng biến đổi `[0, 5, 3, 6, 2, 4, 1, 7]` trước khi gọi MIDP `Graphics.drawRegion`. Bộ dựng đã áp dụng các phép lật/xoay cho các cờ trong 53 atlas. Sổ pet trong prototype cho xem từng chuỗi; thời gian mỗi nhịp được ước lượng là 60 ms theo vòng lặp gốc và có thể khác khi game gốc chạy chậm.

- [CSV vị trí 2.224 crop](asset_review/vqsv5_npc2_frames.csv)
- [JSON tư thế, phần ghép và chuỗi animation](asset_review/vqsv5_npc2_animation_data.json)
- [Preview atlas `npc2/10`](asset_review/vqsv5_npc2_animations/npc2_10_composites.png)
- [Preview atlas `npc2/3`](asset_review/vqsv5_npc2_animations/npc2_3_composites.png)
- [Preview atlas `npc2/33`](asset_review/vqsv5_npc2_animations/npc2_33_composites.png)

Bảng `data.d` cho phép nối từng tên với đúng dạng trong atlas; mỗi dạng chọn ba chuỗi liên tiếp. Các tư thế đã được ghép từ metadata và hiển thị theo pet, còn tên chức năng của từng chuỗi cần xác minh thêm.

## Tham khảo gameplay ngoài

### Chỉ số và công thức chiến đấu VQSV6 (đã giải mã)

Nguồn: mã dịch ngược VQSV_FIX_2.7 (`db.mid` giống từng byte bản VQSV6): `game/i.java` (pet), `game/a.java` (trận). Script dựng lại: `python tools/decode_vqsv6_pet_stats.py` → `prototype/data/vqsv6_pet_stats.json` và [CSV chỉ số 100 pet](asset_review/vqsv6_pet_stats.csv).

| Cột bảng 0 | Ý nghĩa | Bằng chứng |
|---:|---|---|
| 3 | Hạng (quality) mặc định 1–5 | `i.a(...)`: khi không truyền hạng, lấy ngẫu nhiên trong [cột 3, cột 3] |
| 4 | Số sao tối đa hiển thị | Màn trạng thái `k.java` ẩn sao có chỉ số lớn hơn cột 4 − 1 |
| 5–7 | HP = (c5 + c6 × cấp + c7) × hạng% / 100 | `i.a(...)`, `i.W()`; chỉ số 1 là HP (thuốc hồi/so sánh với HP tối đa) |
| 8–10 | Công = (c8 + c9 × cấp + c10) × hạng% / 100 | Sát thương = Công người đánh − Thủ người nhận (`i.C()`) |
| 11–13 | Thủ = (c11 + c12 × cấp / 10 + c13) × hạng% / 100 | như trên |
| 14–16 | Tốc = (c14 + c15 × cấp / 10 + c16) × hạng% / 100 | `a.Q()` xếp lượt theo Tốc giảm dần; chí mạng 5% + Tốc/2 |
| 18 | Dòng đường học kỹ năng trong bảng 8 | `i.G()` |
| 22 | Cờ áp dụng khắc hệ | `i.a(i)`: nếu chỉ một bên có giá trị 2 thì chỉ hệ số có lợi cho bên đó được tính |

- Hạng%: `[90, 95, 100, 110, 125]` cho hạng 1–5. Phép chia là chia nguyên kiểu Java.
- Sát thương: (Công − Thủ) → chí mạng ×1,5 → × sức mạnh kỹ năng% (bảng 1 cột 3) → khắc hệ ×3 / bị khắc ×0,6 → lệch ±1 → tối thiểu 1.
- Vòng khắc hệ: Hỏa → Mộc → Thổ → Thủy → Hỏa; Quỷ → Phong → Điện → Quỷ.
- Kỹ năng: bảng 1 có 70 dòng, mỗi hệ 10 dòng (`hệ × 10 … hệ × 10 + 9`). Cột 3 sức mạnh %, cột 4 bậc mở khóa, cột 5 số lần dùng. Tới cấp 5 pet chỉ biết dòng đầu của hệ; từ cấp 5 trở đi mở thêm khi cột 4 ≤ `bảng8[cột 18][bậc cấp]`, bậc cấp theo ngưỡng `[5, 10, 20, 30, 40]`.
- Kiểm chứng chéo: Điện Miêu (hệ Điện) ở cấp 5 chỉ biết **Điện giật**, sức mạnh 100%, 45 lần, trùng với kỹ năng prototype đã lấy từ bản FIX 2.7.
- **Gặp pet hoang dã** (`game/l.java`): mỗi dòng `petArea.mid` là `[map, khu, số cụm (−1 = không có), cấp tối thiểu, cấp tối đa]` rồi các cụm `[dòng pet, địa hình (0 cạn, 2 nước), hạng tối thiểu, hạng tối đa]`; cấp và hạng bốc ngẫu nhiên đều trong dải. Như vậy cách nối cột đầu cụm với **chỉ số dòng** bảng pet (ghi ở mục petArea phía trên) đã được xác nhận bằng code.
- **Bắt pet** (`game/a.java` hàm `m(cầu)`): Tất Trung Cầu luôn 100%; còn lại HP ≤ 15% → 85, ≤ 50% → 45, còn lại → 20; × cột 6 bảng 4 của cầu (Phong ấn 100, Cao cấp 140, Đại sư 200) / 100; × `[110, 100, 95, 80, 70][hạng − 1]` / 100; × hệ số trạng thái `[10, 11, 12, 12, 12]` / 10; × `[1000, 500, 1, 1000][cột 22]` / 1000; pet cấp ≥ 20 bị chặn ở `[0, 15, 35, 65][cầu]`; kẹp 1–100; thành công khi `random(100) < tỉ lệ`. Prototype dùng hệ số trạng thái 1 vì hiệu ứng trạng thái chưa giải.
- **Kinh nghiệm** (`game/i.java` `A()`, `w()`; `game/a.java` `h()`): lên cấp L cần `15·L² − 200`, tối đa cấp 50 trong game gốc (game hợp nhất chọn giới hạn **70**, kéo dài cùng công thức), lên cấp thì tính lại chỉ số và hồi đầy HP/số lần kỹ năng. Thưởng khi hạ đối thủ cấp L hạng q: `(2·L² + 50) · [10,11,12,13,15][q−1] / 10 + 400`; mỗi pet tham gia nhận `thưởng / n · [10,12,13,14,15,16][n−1] · hệ số cấp / 1000`, hệ số cấp `[105, 100, 80, 60, 40, 20, 5]` (thấp hơn đối thủ; bằng hoặc +1; +2…+5; ≥ +6).
- **Vật phẩm mang theo** (bảng 3 dòng 0–11; pet cầm món có mã = `d[5]` của pet, kiểm tra bằng `game/i.java` `f(byte)`): 0 Mạn Đà La Thạch (HP ≤ cột 5% thì Công + cột 6%), 1 Hồng Sắc Hải Loa (Công + cột 5%), 2 Quy Xác Toái Phiến (Thủ + cột 5%), 3 Ô Nha Uế (trạng thái bất lợi lên pet nhân `(100 − cột 5)/100`), 4 Viễn Cổ Long Cốt (bạo kích + cột 5 điểm), 5 Mật Phong Sào (kinh nghiệm + cột 5%), 6 Ký Cư Giải Xác (chia kinh nghiệm dù không ra trận), 7 Linh Trùng Thi Hài (đi trước trong lượt, `game/a.java` `Q()`), 8 Hấp Huyết Đằng Mạn (cột 5% xác suất hồi cột 6% sát thương), 9 Cá Thờn Bơn (đòn luôn trúng), 10 Cảm Lãm Chi Diệp (HP đối thủ không dưới cột 5), 11 Sủng vật lôi đạt (tỉ lệ bắt + cột 5%). Giá 5 huy hiệu mỗi món.
- **Đánh trượt** (`game/a.java`): `trượt% = clamp(2 × (cấp đối thủ − cấp người đánh), 0, 20)`; trạng thái Mục (bảng 7 dòng 4) trừ cấp người đánh đi `bảng 1 cột 8` của kỹ năng; Cá Thờn Bơn đặt trượt% = 0. Khác với giả định thử nghiệm trước đây, Mục không phải một xác suất trượt cố định.
- **Quên/thay kỹ năng:** không có trong mã gốc; các phương thức `game/i.java` chỉ có `g(byte)` để thêm kỹ năng vào ô trống, và ô chỉ mở thêm theo cấp. Game hợp nhất thêm cơ chế quên và thay như quyết định thiết kế (miễn phí, giữ ít nhất một kỹ năng, kỹ năng mới vẫn phải nằm trong tập pet có thể học ở cấp hiện tại).
- **Cửa hàng và đạo cụ** (`game/k.java` `a()`/`b()`/`O()`/`aZ()`, `game/j.java` `b()`, `game/i.java` `w()`/`x()`): hàng bán là bảng 4 (cầu và đạo cụ) và bảng 3 (nguyên liệu, vật mang theo). Mỗi dòng `[tên, biểu tượng, mô tả, giá, loại tiền, kiểu hiệu ứng, p1, p2, p3]`; loại tiền 0 là kim tiền, 1 và 2 là huy hiệu (chỉ loại 0 trừ kim tiền). Giá trong làng = cột 3; trong trận nhân đôi; bán lại = giá / 2 bằng chính loại tiền của món (chỉ bảng 4). Kiểu hiệu ứng đạo cụ: 1 hồi `HP tối đa·p1/100 + p2`; 2 hồi `p1` lần dùng mỗi kỹ năng; 3 hồi HP như kiểu 1 và `p3` lần dùng; 4 hồi sinh pet kiệt sức (HP như kiểu 1, thêm `p3` lần dùng); 5 xóa trạng thái bất lợi; 7–10 không dùng trong trận (Tị quái hàn kiểu 10: `p1 = 500` bước). Quà đầu game (`game/k.java` mảng `N`): 2000 kim tiền, 5 Phong ấn cầu, 5 Bánh Sandwich, 2 Sinh mệnh thạch, 2 huy hiệu. Nguồn kim tiền trong mã gốc là phần thưởng sự kiện và đồng xu trên map (`game/e.java` case 31, `game/h.java`), không có từ trận đấu.
- **Tiến hóa** (`game/k.java` `bp()`, `game/i.java` `K()`, `u[]`): dạng đích = cột 19 của pet hiện tại; ngưỡng cấp theo cột 2 của dạng đích (1 và 2 tiến hóa, 3 dị hóa) qua `u = [12, 30, 5]`; nguyên liệu = dòng `cột 20 + 12` của bảng 3 (Tinh Nguyên Thạch, Thiên Giới Tinh Thạch, Thiên Địa Thần Thạch, Hồn Tinh Thạch, Quỷ Thần Tinh Thạch), số lượng = cột 21. Pet mới giữ cấp, kinh nghiệm và kỹ năng, hồi đầy HP, giữ hạng nếu không thấp hơn hạng mặc định của dạng đích. Nguồn nguyên liệu không có trong mã hay văn bản đã trích; prototype dùng bảng rơi thử nghiệm.
- **Học kỹ năng** (`game/i.java` `G()`, `H()`, `X()`; `game/k.java` `aq()`): số ô = `min(5, cấp/10 + 1)`; ứng viên là các dòng `hệ×10 … hệ×10+9` có cột 4 ≤ `bảng8[cột 18 của pet][bậc]`, bậc theo ngưỡng `[5, 10, 20, 30, 40]`; tới cấp 5 chỉ biết dòng `hệ×10`. Pet hoang dã: có dòng đầu, rồi từ bậc 1 bốc ngẫu nhiên ứng viên cho tới `cấp/10 + 1` kỹ năng. Bảng 8 có 4 đường học: `[0,0,0,1,1]`, `[0,0,1,1,2]`, `[0,0,1,2,3]`, `[0,1,2,3,3]`.
- **Hiệu ứng kỹ năng** (`game/i.java` `a(byte,int,int)`, `b(i)`, `q(int)`, `c()`, `d()`): bảng 1 cột 6 = loại (0 không, 1 trạng thái có lợi cho bản thân = dòng cột 7 của bảng 6, 2 trạng thái bất lợi cho đối thủ = dòng cột 7 của bảng 7), cột 8 = tham số, cột 9 = 1 không gây sát thương. Bảng 6 `[tên, mô tả, số hiệp, tham số A, tham số B]` (15 dòng), bảng 7 `[tên, mô tả, số hiệp]` (11 dòng). Chỉ các dòng kỹ năng 2, 8, 22, 28, 41, 47 dùng cột 8 làm tỉ lệ kích hoạt. Mỗi bên tối đa 3 trạng thái. Tê Liệt, Mê Muội, Mục, Quỷ Mị không có đoạn xử lý rõ trong mã dịch ngược; prototype làm theo mô tả và đánh dấu thử nghiệm.
- Chưa giải: buff vật phẩm ở bảng 2–3 trong trận, ý nghĩa đầy đủ của cột 22 ngoài phép khắc hệ. Chỉ số VQSV5 đã giải ở mục riêng bên dưới.

## Đối chiếu mẫu VQSV_FIX_2.7

File `db.mid` trong `VQSV_FIX_2.7.zip` giống từng byte với `data/script/db.mid` của `vqsv-6.jar` đang dùng. Mã Java dịch ngược được lưu tại `reference_vqsv_fix_2_7/source_code/decoded/decompiled_source_cfr/` để đối chiếu; đây là mã dịch ngược và có thể không tái hiện hoàn chỉnh mã nguồn ban đầu.

`a/b/c.java` nạp chín bảng từ `db.mid` vào `c`, và hàm `a(byte, short, byte)` tra bảng, dòng, cột. Trong `game/k.java`, màn trạng thái pet đọc cột 19 của pet, tra cột 2 của dòng tương ứng trong bảng pet, rồi hiển thị “Có thể tiến hóa” nếu giá trị là 1 hoặc 2, “Có thể dị hoá” nếu giá trị là 3. Trong 38 liên kết hiện có, cột 2 của dòng đích có 17 giá trị 1, 8 giá trị 2 và 13 giá trị 3.

Màn biến đổi `game/k.java` hàm `bp()` tra cấp từ cột 2 của dòng đích thông qua `game/i.java` mảng `u = [12, 30, 5]`, lấy loại nguyên liệu bằng cột 20 của pet hiện tại cộng 12 để tra dòng trong bảng 3, và lấy số lượng từ cột 21. Tên vật phẩm được tra từ cột 0 của bảng 3 sang `chs.mid`. Sổ pet ghi các điều kiện của VQSV6 gốc để tham khảo; cân bằng cho game hợp nhất và các cơ chế khác chưa được chốt. Sổ chưa thực hiện tiến hóa.

Trong `game/l.java`, dữ liệu `petArea.mid` được tra theo vị trí, gồm nhóm bốn số; nhóm được truyền vào hàm tạo trận, trong đó phần tử đầu dùng để tra bảng pet. Các số còn lại chưa được đặt tên vì cần xác minh thêm. Không mặc định rằng bản mẫu trùng hoàn toàn với phiên bản web anh gửi.

- [Hồi Sinh VQSV 2.1](https://hoisinhvqsv.pages.dev/) là bản chạy trong trình duyệt được xem như tham khảo thứ cấp về trình bày và luồng chơi. Đoạn mở đầu có cảnh làng, lời thoại mời nhân vật dự Cuộc thi Chiến đấu Sủng vật Hoàng Gia, và lựa chọn nhảy tới mốc mười năm sau.
- Dùng bản này để tham khảo nhịp hội thoại, giao diện và cách đưa người chơi vào hoạt động pet. Đây không phải nguồn xác nhận ID hay schema; tên, hệ, sprite và chỉ số pet tiếp tục bám vào dữ liệu game đã trích và kịch bản hợp nhất.

## Chỉ số VQSV5 (đã giải mã từ vqsv5.jar)

**Cách làm:** `vqsv5.jar` được dịch ngược bằng CFR 0.152 (cùng bản dùng cho mẫu FIX 2.7; SHA-1 của jar CFR tải từ Maven Central: `48ef4892cfe8feffddbbd0ff077735140557db74`) và tháo mã bằng `javap -p -c`. Tên bị làm rối nên nhiều trường trùng tên khác kiểu (lớp `al` có cả `byte[] b` lẫn `short[] b`); mọi kết luận dưới đây đã đối chiếu kiểu trường trong bytecode, không chỉ đọc mã CFR. Script dựng lại: `python tools/decode_vqsv5_pet_stats.py` → `prototype/data/vqsv5_pet_stats.json` và [CSV 102 pet](asset_review/vqsv5_pet_stats.csv).

`data.d` được `ak.j()` đọc hết (40664/40664 byte) theo thứ tự: ma trận `p` (id kỹ năng theo ô × hệ), **ma trận `d` (bảng pet, 102 dòng × 15 byte)**, ma trận `n` (49 kỹ năng), 9 bảng chuỗi (nhãn và tên kỹ năng, tên hệ, tên pet, văn bản đồ vật, tên đồ vật, địa điểm, tên và mô tả đặc tính), ma trận `o` (pet được tìm thấy bằng cách nào), `e` (cặp hình), `q` (dòng tiến hóa: đích và nguyên liệu), chuỗi mô tả pet, bốn mảng byte (`a` là giá trị đặc tính), và hai ma trận của lớp `at`.

| Cột `d[x]` | Ý nghĩa | Bằng chứng |
|---:|---|---|
| 0 và 7 | HP gốc và hệ số: `HP = c0 + c7 × cấp / 10` | `al` constructor: `S[2] = d[0] + d[7] * cấp / 10`; sát thương trừ vào `S[0]` (= `S[2]` lúc tạo) |
| 1 và 8 | Giá trị kỹ năng (SP) gốc và hệ số | `S[3]`; dùng kỹ năng trừ `ak.n[kỹ năng][1]` vào `S[1]` |
| 2 và 9 | Linh hoạt (tỉ lệ trúng) | `S[5]`; `trúng% = 92 + (linh hoạt người đánh − người nhận) / 2` (`ak.c`) |
| 3 và 10 | Công | `S[6]` |
| 4 và 11 | Thủ | `S[7]` |
| 5 | Hạng 1–5 (hạng > 3 thì độ trung thành 120, còn lại 100) | `byte[4]`, `byte[6]` |
| 6 | Hệ 0–4 (255 = không hệ); tên ở bảng `b` | `byte[3]`; 100/100 pet có mô tả "[khắc chế X hệ]" khớp bảng khắc hệ |
| 12 | Số lần tiến hóa khởi đầu của cá thể mới (0–3); giảm 1 mỗi lần tiến hóa | `byte[5]`; bằng độ dài chuỗi tiến hóa của cả 52 dạng gốc |
| 13–14 | Chưa giải nghĩa (c13 = 1 ở 5 pet, c14 luôn 0) | — |

- **Sát thương** (`ak.b(z,z,al,al,int)`): `(Công × sức mạnh × (1000 − Thủ × 5) / 10000 − Thủ / 10 − cấp / (2 × sức mạnh) + 6 − (cấp − 1) / 10) × (4 × clamp(chênh cấp, −10, 10) + 100) / 100`, rồi nhân `(thưởng% − 30 nếu thủ + 100) / 100`; thưởng% gồm +20 (độ trung thành 120), −20 nếu hệ pet khác hệ phe và +30 khi thắng thế hệ (`ak.b(z,al,al)`); bạo kích ×1,5 theo đặc tính.
- **Bảng khắc hệ** (hàm tạo `ak`): Thực Vật thắng Phi Hành, Kim Loại thắng Mãnh Thú, Phi Hành thắng Hải Dương, Mãnh Thú thắng Thực Vật, Hải Dương thắng Kim Loại (+30%).
- **Kỹ năng:** ô 0 là kỹ năng 25 ("Công Kích thông dụng", sức mạnh 8, không tốn SP) từ cấp 0; ô k (1–5) mở ở cấp 7k với kỹ năng `hệ × 5 + (k − 1)` (`ak.a(al)`); bảng `n` mỗi dòng `[sức mạnh, chi phí SP, tỉ lệ hiệu ứng, tham số]`. Kỹ năng 26–48 là kỹ năng đặc biệt (hồi máu, phong bế, đặc tính bị động…), chưa giải hiệu ứng.
- **Đặc tính** (`al.b(ak)`, bảng `a`): 13 đặc tính; đặc tính 1 và 2 đổi HP −15% và +15% khi pet mang chúng; mỗi pet có hai ô đặc tính chọn theo hệ.
- **Tiến hóa** (`ak.E()`, `ak.d(int)`, `ak.b(byte,byte)`, `al.b(ak,int,int)`; bảng `ak.q`): mỗi dòng `q[loài] = [đích dị hóa, đích thường, vật phẩm, số lượng, vật phẩm, số lượng…]` (dòng có đích 0 hoặc trỏ về chính nó là chỗ giữ chỗ, 8 dòng). Cần mọi nguyên liệu trong túi, cấp tối thiểu theo hạng (hạng 1: 15, hạng 2: 25, hạng 3: 30, hạng ≥ 4: không) và bộ đếm tiến hóa của cá thể > 0. Thành công thì trừ nguyên liệu, giữ cấp, đổi loài, tính lại chỉ số, hồi đầy HP và SP, bộ đếm giảm 1. Dị hóa (4 pet) chỉ khả dụng khi có thêm 1 vật phẩm 34 "Dị hoá tài liệu", thay thế tiến hóa thường, trừ nguyên liệu như thường và thêm vật phẩm 34, và đưa bộ đếm về 0. Bảng `ak.o` là bảng *nguồn gốc* của sổ tay pet (254 = tiến hóa, 253 = dị hóa, 255 = nhiệm vụ, 252 = đấu trường, 251 = ấp trứng, giá trị khác là mã địa điểm), không phải đích tiến hóa; dùng nó để kiểm chứng chéo: đích thường của 46 dòng hợp lệ trùng đúng tập pet "từ tiến hóa", đích dị hóa trùng tập "từ dị hóa".
- **Nguyên liệu và cửa hàng VQSV5** (`data/shop.d`, đọc hết 234/234 byte): hàng thứ hai bán vật phẩm 16–25 bằng kim tiền (500 hoặc 1000); vật phẩm 26–30 (Bay lượn thiên thạch, Cổ thụ chi hạch, Tinh khiết thủy tinh, Cổ kình chi tâm, Vương chi chứng minh) và 34 (Dị hoá tài liệu) không bán, nguồn gốc chưa rõ.
- **Không tìm thấy** trong jar: trình nạp `monN.d` và `monType.d` (hai bảng đó có thể là dữ liệu thừa của bản này); `data/map/area.dat` có lệnh nạp nhưng chưa giải.
- **Đặc tính ngẫu nhiên** (`al` byte[16], byte[17]; `ak.a[]` giá trị): mỗi pet có hai ô. Ô 1 là một trong hai đặc tính của hệ (`2t+3`, `2t+4`), ô 2 là thể chất (0 không có, 1 HP −15%, 2 HP +15%). Ba cách sinh: cố định (ô 1 = `2t+4`, ô 2 = 2), ngẫu nhiên (hoang dã), may mắn (khi pet dẫn đầu có kỹ năng 39 Hạnh Vận Chi Tinh: 70% ra kết quả tốt). Tác dụng đọc từ bytecode: 3 Hỏa/Hủ thực gây thêm 20%, 4 hồi 6% HP mỗi hiệp, 5/6 ±20% Thạch Hóa, 7/8 ±20% giam cầm, 9 giảm nửa sát thương 20%, 10 tăng sát thương 20%, 11/12 ±20% tê liệt/hủ thực. Điểm lạ trong nguồn: đặc tính 3 kiểm tra `a(3)` thay vì `e(3)` nên có thể không bao giờ kích hoạt; giá trị đặc tính 10 là −20 dù mô tả nói tăng. Dữ liệu ở `trait_rules` và `traits[].effect` trong `vqsv5_pet_stats.json`.
- **Kỹ năng đặc biệt 26–48 và trạng thái VQSV5** (`ak.c()`, `ak.b(z,al,al,int)`, mã cuối hiệp; dữ liệu ở `skills[].special`, `status_rules`, `skill_books`): mỗi pet có 2 ô kỹ năng đặc biệt (`al.b[14]`, `al.b[15]`), học từ sách (vật phẩm = id kỹ năng + 9, tức 35–57, bán bằng huy chương ở cửa hàng thứ ba, giá 1–9; mỗi sách đòi hạng huấn luyện sư `ak.u`). 26–30 là chiêu dùng được: 26 Tàn quân (đánh, 25% độ trung thành của đối thủ), 27/28 hồi 70/200 HP, 29 Tử Vong Chi Xúc (công×2 + toàn bộ SP, SP về 0), 30 Phong Bế. 31–48 là bị động: 31 Hủ Thực mạnh hơn (h = id+3 thay vì +1), 32 Tê Liệt 5 hiệp thay vì 4, 33 Kim Loại nhận 80% sát thương, 34 Phi Hành rút năng lượng, 35 Hải Dương giảm tiêu hao (phí × uy lực / 100), 36/37 kinh nghiệm ×2/×3 (cộng dồn thành ×4), 38 hồi sinh, 39 Hạnh Vận (đặc tính sinh theo chế độ may mắn), 40 độ trung thành không giảm, 41 bắt hụt không bỏ chạy, 42 chạy trốn chắc chắn, 43–47 coi như đứng trên địa hình cùng hệ, 48 giảm nửa sát thương 30%. Trạng thái (mỗi bên một cái): 0 Thạch Hóa (2 hiệp, không hành động, nhận −30%), 2 Tê Liệt (mất 10% HP hiện tại mỗi hiệp), 3 Mị Hoặc (không chạy được, dễ bắt hơn), 4 Giam Cầm (không chạy, đổi pet, dùng đạo cụ), 5 Hủ Thực (mất h²+4), 6 Phong Bế (không dùng kỹ năng bậc trên 3); kỹ năng hệ gây trạng thái theo hệ (Thực Vật→5, Kim Loại→0, Phi Hành→4, Mãnh Thú→2, Hải Dương→3). Điểm lạ trong nguồn: kỹ năng 38 ghi 70% nhưng mã kiểm tra `n[38][1]` = 60; kỹ năng 34 có giá trị 0 nên rút năng lượng bằng 0 như đã mã hóa. Độ trung thành còn có thành phần sát thương: +20% khi 120, âm khi dưới 80, và dưới 41 pet 25% bỏ qua lệnh.
- **Nguồn sách kỹ năng: bảo rương** (`ak.c()` vật phẩm 32/33, mã chiến thắng, `data/box.d`; dữ liệu ở `chest_rules`): thắng trận có 50% nhận Bảo rương (vật phẩm 32, dưới 99 cái). Cái chìa khóa (33) bán ở cửa hàng đạo cụ giá 50 kim. Có cả hai thì mở được: trừ mỗi thứ một cái, tung bậc (70% bậc 0, 15% bậc 1, 10% bậc 2, 5% bậc 3) rồi tung tiếp trong bảng của bậc đó. Bảng 2 chứa sách kỹ năng 35, 41, 51, 52, 53, 54, 55 (15% và sáu mục 10%; 25% trống), tức Tàn quân, Tiêm Nha Lợi Xỉ, chạy trốn chắc chắn và bốn kỹ năng Ý Chí địa hình. Bảng 3 chứa nguyên liệu tiến hóa 26–28, 30, 31; bảng 1 chứa Dị hoá tài liệu (34, chỉ khi chưa có), Bảo rương, chìa khóa và Mật tâm đường; bảng 0 chứa đạo cụ cửa hàng (đồ uống, bánh, năng lượng thạch…). Mục đã ở 99 cái thì thành trống. Các sách còn lại (27–34 và 36–50, 56–57) chỉ mua được bằng huy chương ở cửa hàng thứ ba. Ngoài ra kịch bản sự kiện (`data/event`, lệnh 18 cho vật phẩm, lệnh 21 cho kim tiền ×100) và rương ngoài bản đồ (kim ×100, huy chương hoặc một vật phẩm) cũng cho thưởng; bộ giải mã kịch bản chưa chắc chắn nên chưa đưa vào game. Nguồn của kim tiền và huy chương vì thế đã rõ một phần: kịch bản và rương bản đồ, còn bán đồ thì chia giá cho 5.
- **Kịch bản sự kiện, hạng huấn luyện sư và huy chương** (`tools/decode_vqsv5_events.py`, `prototype/data/vqsv5_event_rewards.json`, `asset_review/vqsv5_event_rewards.csv`): `data/event` có 68 khối (mỗi bản đồ một khối) chứa 386 kịch bản; độ dài lệnh lấy từ bytecode `at.u()` cộng vài dạng đoán từ dữ liệu (28, 51, 13), 385 kịch bản đọc đúng đến hết. Lệnh 18 cho hoặc thu vật phẩm, 21 cho hoặc thu kim tiền ×100, 26 tặng pet, 19 mở trận trùm, 13 cập nhật nhiệm vụ. 47 kịch bản có phần thưởng, ví dụ: quà khởi đầu (Tụ năng thạch ×2, Chocolate ×2, chìa khóa ×4), quà tặng 10 sinh mệnh thạch, 10 Hoàng kim đường, 10 Cao cấp cầu, trứng pet, đổi vật liệu lấy pet (Hanabusa, Vực Bắc Bắc Hải), tiền thưởng đấu trường 800, các phòng trung chuyển cho 2000, 1000 kim và sách kỹ năng (Triều tịch năng lượng ở Phòng trung chuyển Bạch Kim, Phong Bế ở Phòng trung chuyển Lam Chi). Trung Tâm Thương Mại bán 1 Dị hoá tài liệu giá 1500 kim, mỗi lần một cái. Huy chương: mỗi loài mới bắt được cộng 1 vào bộ đếm, đem đến quầy đăng ký lấy huy chương bằng số loài mới; hạng huấn luyện sư 1 lên 2, 3, 4, 5 khi số loài đã bắt vượt 4, 9, 14, 29. Tên bản đồ gắn theo chỉ số khối là đoán, lời thoại trong tệp là tiếng Trung.
- **Rương trên bản đồ VQSV5** (`tools/decode_vqsv5_chests.py`, `prototype/data/vqsv5_map_chests.json`, `asset_review/vqsv5_map_chests.csv`): tài nguyên `raw_data/4` có 68 khối, mỗi bản đồ một khối gồm danh sách byte đầu, ba ma trận thực thể, ma trận rương và một ma trận nữa (`at.p()`); đọc hết 68/68 khối đến byte cuối. Mỗi rương là `[x, y, trạng thái, nội dung, số lượng]` (trạng thái 1 = chưa mở); nội dung − 2 là −2 thì cho kim tiền ×100, −1 thì cho huy chương, còn lại là vật phẩm. Có 143 rương trên 48 bản đồ: 28 rương kim tiền (tổng 15.200 kim), 1 rương huy chương, 114 rương vật phẩm (đạo cụ hồi phục, bóng bắt pet, vật liệu tiến hóa 16–25, 29, Bảo rương ×10, chìa khóa). Không rương nào chứa sách kỹ năng. Tên nơi gắn theo chỉ số khối là đoán.
- **Địa hình sân đấu và pet hoang dã VQSV5** (`tools/decode_vqsv5_terrain.py`, `prototype/data/vqsv5_map_terrain.json`): `data/map/m<N>.d` (`ak.c(int,boolean)`) là mảng byte `c`, mảng byte `b` và một ma trận. `c[0]` là loại sân (0–4 = loại pet), `c[1]` cấp thấp nhất, `c[2]` độ rộng cấp, `c[3…]` mã pet hoang dã (bốc trong ba mục đầu, 5% ra mục thứ bảy nếu có hơn sáu mục). Pet khác loại sân gây −20% sát thương trừ khi biết kỹ năng 43 + loại của nó. 67 trong 68 bản đồ có loại sân (26 Kim Loại, 14 Phi Hành, 13 Hải Dương, 8 Thực Vật, 6 Mãnh Thú), 35 bản đồ có pet hoang dã. Tên nơi gắn theo chỉ số bản đồ là đoán.

Mức độ chắc chắn: công thức chỉ số, hệ, hạng, ô kỹ năng, chi phí SP, công thức sát thương và trúng đã đọc trực tiếp từ bytecode và có đối chiếu chéo bằng mô tả pet. Game hợp nhất không dùng nguyên công thức sát thương VQSV5 mà quy đổi chỉ số về thang VQSV6 (xem `prototype/README.md`).

## Quy tắc dữ liệu chung dự kiến

Khi ID loài được xác minh, mỗi pet sẽ có một `pet_id` mới của game hợp nhất và danh sách `source_ids` giữ nguyên mã gốc. Các trường chung gồm tên chuẩn, dạng/nhánh tiến hóa, hệ, chỉ số, kỹ năng, điều kiện bắt, nguồn ảnh/animation và mức độ xác minh. ID số trùng nhau giữa hai game không được gộp tự động; sprite tương tự cũng chưa đủ bằng chứng để coi là cùng loài.

## Bước kế tiếp

1. Giải quan hệ giữa 34 mục `monN` và 102 dòng pet `data.d`, kiểm tra hai dòng dùng chung hình.
2. Dùng bảng pet hoang dã VQSV5 thật cho trận hoang dã, và độ trung thành lâu dài của VQSV5.
3. Chỉ số, khắc hệ và kỹ năng VQSV6 đã giải (mục trên); còn hiệu ứng trạng thái kỹ năng và các bảng buff 2–7; tên/ID/hệ/ảnh của roster 100 pet đã nối. Xác định role gameplay riêng cho `img_514`.
4. Đánh dấu loài trùng, dạng tiến hóa, pet độc quyền và boss; rồi mới chuẩn hóa chỉ số/điều kiện bắt.
5. Khóa một roster nhỏ cho lát đầu: Lôi Miêu, một đối thủ bị tha hóa, sau đó thêm pet tuyển chọn từ hai nguồn.
