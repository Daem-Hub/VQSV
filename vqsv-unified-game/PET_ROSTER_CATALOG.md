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

### Đối chiếu mẫu VQSV_FIX_2.7

File `db.mid` trong `VQSV_FIX_2.7.zip` giống từng byte với `data/script/db.mid` của `vqsv-6.jar` đang dùng. Mã Java dịch ngược được lưu tại `reference_vqsv_fix_2_7/source_code/decoded/decompiled_source_cfr/` để đối chiếu; đây là mã dịch ngược và có thể không tái hiện hoàn chỉnh mã nguồn ban đầu.

`a/b/c.java` nạp chín bảng từ `db.mid` vào `c`, và hàm `a(byte, short, byte)` tra bảng, dòng, cột. Trong `game/k.java`, màn trạng thái pet đọc cột 19 của pet, tra cột 2 của dòng tương ứng trong bảng pet, rồi hiển thị “Có thể tiến hóa” nếu giá trị là 1 hoặc 2, “Có thể dị hoá” nếu giá trị là 3. Trong 38 liên kết hiện có, cột 2 của dòng đích có 17 giá trị 1, 8 giá trị 2 và 13 giá trị 3.

Màn biến đổi `game/k.java` hàm `bp()` tra cấp từ cột 2 của dòng đích thông qua `game/i.java` mảng `u = [12, 30, 5]`, lấy loại nguyên liệu bằng cột 20 của pet hiện tại cộng 12 để tra dòng trong bảng 3, và lấy số lượng từ cột 21. Tên vật phẩm được tra từ cột 0 của bảng 3 sang `chs.mid`. Sổ pet ghi các điều kiện của VQSV6 gốc để tham khảo; cân bằng cho game hợp nhất và các cơ chế khác chưa được chốt. Sổ chưa thực hiện tiến hóa.

Trong `game/l.java`, dữ liệu `petArea.mid` được tra theo vị trí, gồm nhóm bốn số; nhóm được truyền vào hàm tạo trận, trong đó phần tử đầu dùng để tra bảng pet. Các số còn lại chưa được đặt tên vì cần xác minh thêm. Không mặc định rằng bản mẫu trùng hoàn toàn với phiên bản web anh gửi.

- [Hồi Sinh VQSV 2.1](https://hoisinhvqsv.pages.dev/) là bản chạy trong trình duyệt được xem như tham khảo thứ cấp về trình bày và luồng chơi. Đoạn mở đầu có cảnh làng, lời thoại mời nhân vật dự Cuộc thi Chiến đấu Sủng vật Hoàng Gia, và lựa chọn nhảy tới mốc mười năm sau.
- Dùng bản này để tham khảo nhịp hội thoại, giao diện và cách đưa người chơi vào hoạt động pet. Đây không phải nguồn xác nhận ID hay schema; tên, hệ, sprite và chỉ số pet tiếp tục bám vào dữ liệu game đã trích và kịch bản hợp nhất.

## Quy tắc dữ liệu chung dự kiến

Khi ID loài được xác minh, mỗi pet sẽ có một `pet_id` mới của game hợp nhất và danh sách `source_ids` giữ nguyên mã gốc. Các trường chung gồm tên chuẩn, dạng/nhánh tiến hóa, hệ, chỉ số, kỹ năng, điều kiện bắt, nguồn ảnh/animation và mức độ xác minh. ID số trùng nhau giữa hai game không được gộp tự động; sprite tương tự cũng chưa đủ bằng chứng để coi là cùng loài.

## Bước kế tiếp

1. Giải quan hệ giữa 34 mục `monN` và 102 dòng pet `data.d`, kiểm tra hai dòng dùng chung hình.
2. Giải schema chỉ số/hệ của VQSV5 và `monType.d` trước khi dùng trong chiến đấu.
3. Giải nghĩa 19 trường còn lại và 8 bảng số phụ của VQSV6 để chuẩn hóa chỉ số/kỹ năng và điều kiện tiến hóa; tên/ID/hệ/ảnh của roster 100 pet đã nối. Xác định role gameplay riêng cho `img_514`.
4. Đánh dấu loài trùng, dạng tiến hóa, pet độc quyền và boss; rồi mới chuẩn hóa chỉ số/điều kiện bắt.
5. Khóa một roster nhỏ cho lát đầu: Lôi Miêu, một đối thủ bị tha hóa, sau đó thêm pet tuyển chọn từ hai nguồn.
