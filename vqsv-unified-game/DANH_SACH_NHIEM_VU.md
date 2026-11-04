# Danh sách nhiệm vụ chính và phụ — VQSV hợp nhất

**Trạng thái: bản thiết kế để duyệt, chưa phải danh sách đã triển khai.** Giữ đủ 96 nhiệm vụ chính và 47 nhiệm vụ phụ trong bảng nguồn, cùng ID để dễ đối chiếu. Tên NPC, tình tiết bổ sung, cấp, số lượng và phần thưởng trong bảng là **đề xuất của bảng nhiệm vụ**, không tự động trở thành canon hay thông số game hoàn chỉnh.

Nguồn cốt truyện nền: `Kich_Ban_Cot_Truyen_VQSV_Tron_Bo.docx`. Nguồn danh sách và các chi tiết từng nhiệm vụ: `He_Thong_Nhiem_Vu_VQSV_96_Chinh_47_Phu.xlsx`. Không chỉnh sửa hai file gốc.

## Các điểm cần chốt trước khi triển khai

- **Đầu truyện:** MQ001–MQ005 thêm NPC, địa điểm và nhiệm vụ chưa có trong kịch bản; MQ002 để Kael tìm la bàn, trong khi truyện đã giới thiệu Kael đang có la bàn. Tên Elden, Bran, Mae là đề xuất từ bảng, chưa xác nhận là NPC gốc hay NPC đã chốt.
- **Thứ tự vùng:** bảng đặt Hồ Băng (MQ010–012) trước Hỏa Sơn (MQ013–015), còn kịch bản kể Viêm Long trước Tuyết Y; cần chốt trình tự hoặc cho phép lựa chọn.
- **Tên chương:** bảng nhiệm vụ chia Aethelgard và Mặt Trăng thành chương 3–5 để quản lý tiến trình; kịch bản dùng Chương 3 cho Aethelgard, Chương 4 mô tả cơ chế boss và Chương 5 là hậu chiến, lên đường. Đây là hai cách đánh số, không mặc định là cùng cấu trúc chương.
- **Sau khi lên đường:** MQ082–MQ096 và các nhiệm vụ phụ ở Mặt Trăng thêm nội dung, trong đó có việc mẹ còn sống và kết cục của Thực Thể Thứ 0; kịch bản hiện dừng ở cảnh đoàn bay lên không gian. Giữ các nhiệm vụ này là đề xuất cần duyệt, không khẳng định là canon.
- **Mốc mở nhiệm vụ phụ:** các cấp trong bảng chỉ là gợi ý, chưa đủ để xác định thời điểm mở, điều kiện trước/sau nhiệm vụ khác, nhiệm vụ bị bỏ lỡ hay tính khả dụng của hệ thống. Phải thiết kế điều kiện cụ thể trước khi lập trình.
- **Mạch cảm xúc và pet:** SQ001–010 cần xét thời điểm quan hệ Kael–Lyra phát triển; SQ011–022 cần đối chiếu roster, nhánh tiến hóa và hệ thống thu phục. Các NPC/pet/vật phẩm chưa xác nhận đều là ứng viên nội dung.
- **Boss:** MQ052–054 và MQ068–071 chia giai đoạn boss thành các dòng để theo dõi; mặc định đây là các bước của trận liền mạch, không bắt buộc tách thành các trận riêng. Thông số, phần thưởng và điều kiện sẽ cân bằng sau.

Bản đang chơi mới có người gác làng, dấu vết, một trận thử và cảnh la bàn. Chưa có nhiệm vụ nào trong bảng dưới đây được xác nhận đã hoàn thiện trọn vẹn; không tự đánh dấu tương ứng với MQ001–MQ008.

## Nhiệm vụ chính

Cột **Nội dung / hoàn thành** và **thưởng** được giữ theo bảng nguồn để rà; cấp và thưởng đều chưa cân bằng. Những nhiệm vụ dẫn tới và sau Mặt Trăng được giữ dưới dạng đề xuất khi vượt quá kịch bản nền.

### Chương 1 · Ryan (20)

| Mã | Nhiệm vụ | Khu vực · cấp đề xuất | Kích hoạt | Nội dung / hoàn thành | Phần thưởng đề xuất |
| --- | --- | --- | --- | --- | --- |
| MQ001 | Tia Chớp Phế Phẩm | Làng Sương Mù · Lv. 1 | Trưởng Làng Elden | Làm quen với Lôi Miêu mang vết sẹo dị tật tai phải. **Hoàn thành:** Hoàn thành bài tập đánh bia gỗ cùng Lôi Miêu | 100 EXP, 5 Bình Thuốc Nhỏ |
| MQ002 | Bí Mật Của Người Cha | Nhà Kael · Lv. 2 | Kỷ vật gia đình | Tìm kiếm chiếc la bàn đồng rỉ sét trong căn hầm cũ. **Hoàn thành:** Thu thập La Bàn Cổ & Nhật Ký Thất Lạc | 150 EXP, La Bàn Đồng Cổ |
| MQ003 | Tiếng Gầm Ngoài Biên Giới | Rừng Lôi Sương · Lv. 3 | Thợ Săn Bran | Khảo sát sự hung bạo bất thường của bầy Ma Thú Rừng. **Hoàn thành:** Đánh bại 3 Dã Lang Bão Tố | 220 EXP, 10 Bột Thảo Mộc |
| MQ004 | Thu Thập Thảo Dược Hồi Phục | Thung Lũng Rêu · Lv. 4 | Dược Sĩ Mae | Tìm kiếm cỏ Tinh Linh chữa lành vết sẹo cho Lôi Miêu. **Hoàn thành:** Hái 5 Ngọn Cỏ Dạ Quang | 300 EXP, 200 Vàng |
| MQ005 | Bóng Đêm Thập Thò | Bờ Suối Trầm Mặc · Lv. 5 | Dấu vết tà khí | Truy vết kẻ trùm áo choàng bí ẩn xuất hiện quanh làng. **Hoàn thành:** Lần theo dấu chân tà khí đến cửa rừng | 380 EXP, 3 Quả Cầu Bắt Thú |
| MQ006 | Đêm Trăng Huyết Thứ Bảy | Làng Sương Mù · Lv. 6 | Trưởng Làng Elden | Vết nứt không gian mở ra trên trời; làng bị tập kích. **Hoàn thành:** Cứu 3 dân làng khỏi ngọn lửa hắc ám | 480 EXP, Giáp Da Tân Thủ |
| MQ007 | Cuồng Bạo Nham Cự Tượng | Cổng Làng · Lv. 7 | Cuồng Nham Cự Tượng | Boss làng nổi điên dưới tác động của Ma Khống Cầu. **Hoàn thành:** Sống sót 3 phút trước đòn tấn công của Boss | 600 EXP, 500 Vàng |
| MQ008 | Lôi Sét Trắng Thức Tỉnh | Tàn Tích Làng · Lv. 8 | Lôi Miêu (Dị tật) | Lôi Miêu liều mình che chở Kael, kích hoạt dòng máu cổ. **Hoàn thành:** Dùng Lôi Kích Trắng phá vỡ Ma Khống Cầu | 750 EXP, Phong Ấn Cầu Nguyên Tố |
| MQ009 | Hạt Nhân Khởi Nguyên | Điện Thờ Cổ · Lv. 9 | Linh Hồn Cổ Thụ | Tìm hiểu về truyền thuyết 4 thánh vật phong ấn lục địa. **Hoàn thành:** Nghe lời tiên tri & nhận bản đồ 4 vùng đất | 900 EXP, Bản Đồ Ryan |
| MQ010 | Lộ Trình Băng Nguyên | Đèo Gió Bấc · Lv. 11 | Đoàn Buôn Du Mục | Vượt qua bão tuyết tiến về Hồ Băng Cổ Xưa. **Hoàn thành:** Dẫn đường an toàn cho đoàn buôn qua đèo tuyết | 1,100 EXP, Áo Choàng Giữ Nhiệt |
| MQ011 | Băng Giá Bất Tận | Hồ Băng Cổ Xưa · Lv. 13 | Băng Trụ Trấn Giữ | Tìm kiếm tung tích Tuyết Y Linh Thú ngàn năm. **Hoàn thành:** Phá giải 3 phong ấn băng lam trên mặt hồ | 1,350 EXP, Băng Tinh Thạch |
| MQ012 | Khúc Ca Đồng Cảm | Đáy Hồ Băng · Lv. 15 | Tuyết Y Linh Thú | Dùng sự kiên nhẫn và lòng thấu cảm hóa giải tà khí cho Thần Thú. **Hoàn thành:** Trấn an Tuyết Y Linh Thú mà không sát hại | 1,650 EXP, Nhẫn Băng Lam |
| MQ013 | Vượt Hoang Mạc Xích Diễm | Hẻm Núi Cát Đỏ · Lv. 17 | Hướng Dẫn Viên Jax | Băng qua sa mạc nóng bỏng để đến Hỏa Sơn Bất Diệt. **Hoàn thành:** Đánh bại Bọ Cạp Nham Thạch cấp 18 | 2,000 EXP, Bình Nước Thần |
| MQ014 | Nguy Hiểm Miệng Lửa | Chân Núi Hỏa Sơn · Lv. 19 | Dòng Dung Nham | Phát hiện tiếng kêu cứu của sủng vật mắc kẹt trong nham thạch. **Hoàn thành:** Dùng sủng vật hệ Băng mở đường qua nham thạch | 2,400 EXP, Đá Cường Hóa Lửa |
| MQ015 | Giải Cứu Viêm Long Tí Nị | Lòng Núi Lửa · Lv. 21 | Viêm Long Tí Nị | Mạo hiểm cứu Viêm Long non khỏi dòng dung nham độc hại. **Hoàn thành:** Thu phục thành công Viêm Long Tí Nị gia nhập đội | 2,900 EXP, Vòng Cổ Rồng Non |
| MQ016 | Bóng Ma Tận Thế Tụ Hội | Bình Nguyên Phong Lôi · Lv. 22 | Tàn Binh Giáo Phái | Phá tan cứ điểm trinh sát của giáo phái hắc ám. **Hoàn thành:** Tiêu diệt 10 Tín Đồ Ma Khống | 3,500 EXP, 1,200 Vàng |
| MQ017 | Đường Lên Tháp Thiên Không | Đỉnh Mây Nghịch Đảo · Lv. 23 | Cổng Tháp | Giải mã cơ quan 4 nguyên tố để mở đại môn Tháp Thiên Không. **Hoàn thành:** Đặt 4 tinh thể nguyên tố vào trụ đài | 4,200 EXP, Chìa Khóa Thiên Không |
| MQ018 | Cộng Hưởng Linh Hồn Bộc Phát | Tầng Trung Tháp · Lv. 24 | Lôi Miêu | Đối mặt ảo ảnh thù hận, tôi luyện mối liên kết chủ - tớ. **Hoàn thành:** Hoàn thành nghi thức tiến hóa Cực Lôi Hoàng Hổ | 5,000 EXP, Dây Cương Sấm Sét |
| MQ019 | Tội Ác Kẻ Đồng Đội | Đỉnh Tháp Thiên Không · Lv. 25 | Kẻ Cầm Đầu Giáo Phái | Đối mặt kẻ phản bội từng là chiến hữu của cha Kael. **Hoàn thành:** Đánh bại đợt quái hộ vệ của giáo chủ | 6,000 EXP, 2,000 Vàng |
| MQ020 | Quang Minh Trận Thanh Tẩy | Đỉnh Tháp Thiên Không · Lv. 25 | Cổ Long Hắc Ám | Kích hoạt Quang Minh Trận từ 4 nguyên tố, đánh bại Cổ Long. **Hoàn thành:** Hạ gục Cổ Long Hắc Ám & tìm thấy bức thư của cha | 7,500 EXP, Danh Hiệu Anh Hùng Ryan |

### Chương 2 · Hải Vực và Atlantis (22)

| Mã | Nhiệm vụ | Khu vực · cấp đề xuất | Kích hoạt | Nội dung / hoàn thành | Phần thưởng đề xuất |
| --- | --- | --- | --- | --- | --- |
| MQ021 | Lời Cảnh Báo Ngoài Đại Dương | Bến Cảng Ryan · Lv. 26 | Thuyền Trưởng Drake | Chuẩn bị chiến thuyền vượt qua Hải Vực Vô Tận. **Hoàn thành:** Thu thập 10 Gỗ Lim Thần & 5 Cánh Buồm Bão | 8,200 EXP, Chiến Thuyền Viễn Chinh |
| MQ022 | Làn Nước Tím Ma Quái | Hải Vực Vô Tận · Lv. 27 | Thủy Thủ Đoàn | Khảo sát hiện tượng nước biển đổi màu huỳnh quang dị thường. **Hoàn thành:** Lấy 3 mẫu nước nhiễm Hư Không Tinh Thể | 9,000 EXP, Kính Thủy Tinh Lọc |
| MQ023 | Cơn Thịnh Nộ Thủy Quái Cơ Khí | Vùng Biển Chết · Lv. 28 | Cá Mập Cơ Khí Đột Biến | Chiến thuyền bị quái vật nửa sống nửa máy tấn công. **Hoàn thành:** Đánh chìm 2 Thủy Quái Bọc Thép | 10,000 EXP, Mảnh Hợp Kim Biển |
| MQ024 | Lốc Xoáy Lôi Đình Cuốn Phăng | Vòng Xoáy Tận Cùng · Lv. 29 | Bão Tố Biển Sâu | Tàu bị lốc xoáy lôi đình nuốt chửng xuống đáy biển. **Hoàn thành:** Điều khiển thuyền né tránh các cột sóng xoáy | 11,200 EXP, 2,500 Vàng |
| MQ025 | Vương Quốc Chìm Atlantis | Cổng San Hô Atlantis · Lv. 30 | Lính Gác Hải Tộc | Tỉnh dậy nơi vương quốc cổ dưới đáy đại dương. **Hoàn thành:** Thuyết phục Hải Tộc mình không phải kẻ xâm lăng | 12,500 EXP, Thuốc Thở Dưới Nước |
| MQ026 | Gặp Gỡ Nàng Lyra | Cung Điện San Hô · Lv. 31 | Lyra (Bán Nhân Ngư) | Gặp gỡ nữ hộ vệ bảo vệ quả trứng Thương Hải Thần Cự Kình. **Hoàn thành:** Tiếp nhận ủy thác bảo vệ quả trứng từ Lyra | 14,000 EXP, Vòng Tay San Hô |
| MQ027 | Tập Kích Của Quân Đoàn Cơ Giới | Vườn Ươm Cự Kình · Lv. 32 | Tiểu Đội Thiết Giáp | Quân đoàn cơ giới đột kích hòng cướp đoạt quả trứng thần. **Hoàn thành:** Bảo vệ Lyra và quả trứng trong 4 phút | 15,800 EXP, Khiên Nước Atlantis |
| MQ028 | Sự Thật Về Người Cha Hiến Tế | Đền Tưởng Niệm Biển · Lv. 33 | Lyra | Lyra tiết lộ cha Kael là vật tế phong ấn 10 năm trước. **Hoàn thành:** Tìm phiến đá ký ức của cha Kael | 17,800 EXP, Mảnh Ký Ức Lam |
| MQ029 | Âm Mưu Của Tiến Sĩ Vane | Phòng Thí Nghiệm Đáy Biển · Lv. 34 | Băng Ghi Âm Cổ | Khám phá dã tâm cấy linh hồn sủng vật vào cỗ máy. **Hoàn thành:** Vượt qua hệ thống bẫy điện tử ngầm | 20,000 EXP, Lõi Vi Mạch Cổ |
| MQ030 | Thử Thách San Hô Lân Quang | Hang San Hô Sâu · Lv. 35 | Ảo Cảnh Nỗi Sợ | Đối mặt ký ức ngày làng bị cháy và nỗi sợ bị bỏ rơi. **Hoàn thành:** Vượt qua ảo ảnh mà không dùng bạo lực | 22,500 EXP, Ngọc Lân Quang |
| MQ031 | Vết Sẹo Là Sức Mạnh | Đài Tinh Thần Lôi Đình · Lv. 36 | Cực Lôi Hoàng Hổ | Chấp nhận khiếm khuyết quá khứ, đánh thức đôi cánh lôi đình. **Hoàn thành:** Kích hoạt hình thái cánh sấm sét kim sắc | 25,500 EXP, Kỹ Năng: Lôi Dực Khiêu Vũ |
| MQ032 | Lạc Vào Nghĩa Địa Chiến Thuyền | Vực Đắm Tàu Cổ · Lv. 37 | Xác Tàu Cổ | Tìm kiếm lối đi bí mật dẫn xuống Vực Thẳm Tận Cùng. **Hoàn thành:** Tìm la bàn áp suất trong xác tàu hạm trưởng | 29,000 EXP, La Bàn Áp Suất |
| MQ033 | U Linh Sa Ngư Lạc Lối | Vùng Tối Thủy Triều · Lv. 38 | U Linh Sa Ngư | Chạm trán linh thể hệ Ám từng bị thí nghiệm tàn nhẫn. **Hoàn thành:** Dùng lòng yêu thương thuần hóa U Linh Sa Ngư | 33,000 EXP, Sủng Vật: U Linh Sa Ngư |
| MQ034 | Bẻ Khóa Trạm Quan Sát Ngầm | Cửa Vực Sâu Vane · Lv. 39 | Cơ Chế Khóa Sinh Học | Phá hủy các tháp phát sóng nhiễu loạn tâm trí sủng vật. **Hoàn thành:** Vô hiệu hóa 3 trạm phát sóng cơ giới | 37,500 EXP, Thẻ Truy Cập Hư Vô |
| MQ035 | Đường Xuống Vực Thẳm Tận Cùng | Vực Áp Suất Cực Hạn · Lv. 40 | Lyra | Lyra dùng Thương Hải Chi Lệ tạo màng bong bóng chịu áp suất. **Hoàn thành:** Hộ tống cả nhóm vượt qua vực đá nứt | 42,500 EXP, Áo Giáp Lặn Thần |
| MQ036 | Hình Bóng Trong Lồng Kính | Trung Tâm Thí Nghiệm Vane · Lv. 41 | Cha Kael (Bị giam cầm) | Chứng kiến cha Kael bị gắn vào cỗ máy hình nhện rút năng lượng. **Hoàn thành:** Đánh bại hộ vệ người máy canh giữ phòng giam | 48,000 EXP, Chìa Khóa Lồng Kính |
| MQ037 | Kỷ Nguyên Thần Thú Cơ Khí? | Sàn Đấu Lõi Vực Thẳm · Lv. 42 | Tiến Sĩ Vane | Nghe tuyên ngôn điên loạn của TS. Vane về sự bất tử máy móc. **Hoàn thành:** Đập tan 2 cánh tay robot khổng lồ bảo vệ bục | 54,000 EXP, Mảnh Thép Titan |
| MQ038 | Cơ Giới Thần Long Xuất Thế | Lõi Vực Thẳm · Lv. 43 | Cơ Giới Thần Long Hư Không | Quái vật kim loại khổng lồ hấp thụ toàn bộ nguyên tố xuất trận. **Hoàn thành:** Phá hủy hệ thống hấp thụ năng lượng ở 4 góc | 61,000 EXP, Tinh Thể Kháng Nguyên Tố |
| MQ039 | Giai Điệu Ru Thơ Ấu | Lõi Cỗ Máy · Lv. 44 | Chiếc La Bàn Đồng | Kael nghe tiếng hát cha ru, phát tín hiệu đánh thức linh hồn rồng. **Hoàn thành:** Kích hoạt khúc nhạc tuổi thơ qua la bàn cổ | 69,000 EXP, Kỷ Vật Gia Đình Cấp 2 |
| MQ040 | Xé Toạc Lớp Vỏ Sắt | Lưng Cự Long · Lv. 44 | Lõi Sinh Học Thần Long | Kael buông vũ khí, dùng năng lượng thanh tẩy giải thoát linh hồn rồng. **Hoàn thành:** Thanh tẩy hoàn toàn linh hồn rồng cổ đại | 78,000 EXP, Long Lân Bạc |
| MQ041 | Sụp Đổ Dưới Đáy Biển | Căn Cứ Sụp Đổ · Lv. 45 | Tiến Sĩ Vane & Vực Sâu | Căn cứ nổ tung, TS. Vane bị nuốt chửng; Kael cứu thoát cha. **Hoàn thành:** Thoát khỏi căn cứ trước khi đồng hồ về 0 (3 phút) | 88,000 EXP, Nhẫn Bảo Vệ Biển Sâu |
| MQ042 | Thương Hải Cự Kình Tái Sinh | Mặt Nước Đại Dương · Lv. 45 | Thần Cự Kình (Mới nở) | Trứng cự kình nở, đưa nhóm Kael trồi lên mặt biển an toàn. **Hoàn thành:** Xem hoạt cảnh cha con đoàn tụ & Lyra mỉm cười | 100,000 EXP, Tín Vật Tình Bạn Lyra |

### Chương 3 · Aethelgard và Boran (18)

| Mã | Nhiệm vụ | Khu vực · cấp đề xuất | Kích hoạt | Nội dung / hoàn thành | Phần thưởng đề xuất |
| --- | --- | --- | --- | --- | --- |
| MQ043 | Lời Dặn Của Người Cha Bệnh Tật | Doanh Trại Bờ Biển · Lv. 46 | Cha Kael | Cha dưỡng thương dặn dò Kael tiến lên Lục Địa Bay. **Hoàn thành:** Tiếp nhận Thánh Vật Khí Cầu Cổ | 110,000 EXP, Thắt Lưng Bền Bỉ |
| MQ044 | Hẹn Ước Dưới Ánh Trăng | Mũi Tàu Bay · Lv. 47 | Lyra | Lyra thề nguyện sát cánh bên Kael dù bất kỳ hiểm nguy nào. **Hoàn thành:** Đối thoại tình cảm & mở khóa Kỹ Năng Đôi Bậc 1 | 122,000 EXP, Nhẫn Đôi Tương Tư |
| MQ045 | Xuyên Qua Vùng Dông Bão Khí Quyển | Tầng Mây Nghịch Lưu · Lv. 48 | Bão Từ Trên Không | Tàu bay bị nhiễu loạn bởi từ trường cực mạnh của Aethelgard. **Hoàn thành:** Điều khiển bánh lái vượt qua tâm bão | 135,000 EXP, Đá Từ Tính Bay |
| MQ046 | Đặt Chân Lên Lục Địa Bay | Cảng Trời Aethelgard · Lv. 49 | Cư Dân Thiên Không | Khám phá tàn tích lục địa lơ lửng ngập tràn công nghệ cổ. **Hoàn thành:** Kích hoạt trạm dịch chuyển Thiên Không Cổ | 150,000 EXP, Giày Lướt Gió |
| MQ047 | Bản Đồ Miệng Núi Lửa Hắc Diệu | Đài Thiên Văn Trời · Lv. 50 | Bản Đồ Cổ Tự | Định vị cứ điểm của Thống Lĩnh Thiết Giáp Boran. **Hoàn thành:** Giải mã tọa độ ngọn núi lửa hắc diệu | 166,000 EXP, Kính Đo Tọa Độ |
| MQ048 | Vượt Vùng Đất Chết Dung Nham | Hẻm Núi Nham Thạch · Lv. 52 | Quái Vật Dung Nham | Đánh bại các sủng vật bị cấy chip cuồng nộ của Boran. **Hoàn thành:** Hạ gục 8 Quái Nham Thạch Cơ Giới | 184,000 EXP, Giáp Kháng Lửa |
| MQ049 | Pháo Đài Sắt Boran | Cửa Pháo Đài Hắc Diệu · Lv. 54 | Lính Gác Titan | Đột nhập pháo đài khai thác địa nhiệt khổng lồ. **Hoàn thành:** Phá hủy 2 cổng sắt kiên cố bằng chất nổ | 204,000 EXP, 5,000 Vàng |
| MQ050 | Tiếng Rên Xiết Dưới Lòng Đất | Hầm Khoan Địa Nhiệt · Lv. 56 | Cổ Thú Nham Thạch | Phát hiện Cổ Thú Nham Thạch bị xích sắt cấy lò phản ứng. **Hoàn thành:** Thu thập dữ liệu giải phóng dây xích | 226,000 EXP, Kìm Bẻ Xích Cổ |
| MQ051 | Đối Đầu Thống Lĩnh Boran | Phòng Lò Phản Ứng · Lv. 58 | Thống Lĩnh Boran | Boran khoe khoang sức mạnh Nham Bạo Long bọc titan. **Hoàn thành:** Đánh bại đợt thiết giáp tiên phong của Boran | 250,000 EXP, Khiên Hợp Kim |
| MQ052 | Trùm 1 - Khởi Động: Pháo Đài Kim Loại | Miệng Núi Lửa · Lv. 60 | Nham Bạo Long (Phase 1) | Cơ chế phá giáp: Lyra dùng Thủy Luân kích nổ mạch nước. **Hoàn thành:** Hạ thanh Nhiệt Giáp về 0, rút Boss xuống 70% HP | 280,000 EXP, Rương Báu Titan |
| MQ053 | Trùm 1 - Nguy Hiểm: Lồng Sắt Từ Tính | Miệng Núi Lửa · Lv. 61 | Nham Bạo Long (Phase 2) | Kael bị giam, Lyra dùng Thương Hải Chi Lệ cản tia laser. **Hoàn thành:** Lyra phá vỡ lồng sắt giải cứu Kael | 315,000 EXP, Ngọc Chống Từ |
| MQ054 | Trùm 1 - Sinh Tử: Lôi Kích Băng Hàn | Miệng Núi Lửa · Lv. 62 | Nham Bạo Long (Phase 3) | Sàn đấu ngập nham thạch, đếm ngược 60 giây quá tải. **Hoàn thành:** Thi triển combo Lôi Kích Băng Hàn kết liễu Boss | 355,000 EXP, Vũ Khí: Thương Lôi Thạch |
| MQ055 | Cái Giá Của Sự Cuồng Bạo | Xác Nham Bạo Long · Lv. 63 | Boran Thất Trận | Boran sụp đổ, lò phản ứng vỡ nát, cổ linh được siêu thoát. **Hoàn thành:** Nói chuyện với linh hồn Cổ Thú Nham Thạch | 400,000 EXP, Lõi Dung Nham Tinh Khiết |
| MQ056 | Băng Bó Giữa Tàn Tro | Doanh Trại Tạm Núi Lửa · Lv. 63 | Lyra | Kael băng bó vết bỏng cho Lyra; tình cảm thăng hoa bền chặt. **Hoàn thành:** Hoàn thành đoạn cắt cảnh tình cảm Kael - Lyra | 450,000 EXP, Khăn Lụa Biển Xanh |
| MQ057 | Cột Khói Tím Trên Đỉnh Trời | Ranh Giới Tầng Mây · Lv. 64 | Dấu Vết Seraphina | Lần theo dấu vết tà khí dẫn lên Cung Điện Kính Vô Cực. **Hoàn thành:** Chế tạo Khiên Kháng Gương từ tro núi lửa | 505,000 EXP, Khiên Kháng Gương |
| MQ058 | Chiến Hạm Gió Đột Kích | Cầu Vồng Pha Lê · Lv. 64 | Phi Đội Kính Giới | Đánh chặn phi đội ảo ảnh phục kích trên đường bay. **Hoàn thành:** Tiêu diệt 12 Ảo Ảnh Phi Hành Cơ | 565,000 EXP, Mảnh Pha Lê Tím |
| MQ059 | Trước Thềm Tầng Mây Khởi Nguyên | Bậc Thang Pha Lê · Lv. 65 | Cổng Khởi Nguyên | Chuẩn bị trang bị và sủng vật cho trận chiến tối thượng. **Hoàn thành:** Kiểm tra toàn bộ trang bị & nâng cấp linh thú | 630,000 EXP, Đá Tẩy Tủy |
| MQ060 | Lời Nguyền Của Nữ Hoàng Hư Không | Cung Điện Khởi Nguyên · Lv. 65 | Giọng Nói Seraphina | Seraphina tuyên bố sẽ tẩy não cả hai người. **Hoàn thành:** Bước qua cánh cửa gương khổng lồ | 700,000 EXP, Hộ Phù Ý Chí |

### Chương 4 theo bảng · Kính Giới và hành trình lên trời (20)

| Mã | Nhiệm vụ | Khu vực · cấp đề xuất | Kích hoạt | Nội dung / hoàn thành | Phần thưởng đề xuất |
| --- | --- | --- | --- | --- | --- |
| MQ061 | Bước Vào Kính Giới Vô Cực | Cung Điện Gương · Lv. 66 | Seraphina | Cung điện phản chiếu ngàn hình bóng ma mị. **Hoàn thành:** Tìm ra lối đi thật qua mê cung gương | 780,000 EXP, Kính Phù Thủy |
| MQ062 | Huyễn Ảnh Quá Khứ | Hành Lang Ký Ức · Lv. 67 | Gương Ký Ức | Ảo ảnh Seraphina tái hiện nỗi đau và sự mất mát. **Hoàn thành:** Phá hủy 3 tấm gương tạo ảo giác | 865,000 EXP, Thuốc Bình Tâm |
| MQ063 | Lời Thì Thầm Của Hư Không | Điện Gương Thứ 2 · Lv. 68 | Bóng Ma Tâm Linh | Chống lại sóng âm thao túng tâm lý của Seraphina. **Hoàn thành:** Bảo vệ thanh Ý Chí không tụt về 0 | 955,000 EXP, Dây Chuyền Bạc |
| MQ064 | Khúc Hát Đại Dương Của Lyra | Đài Vọng Kính · Lv. 69 | Lyra | Lyra cất tiếng hát biển khơi thanh tẩy ô uế cung điện. **Hoàn thành:** Hộ tống Lyra hát trọn vẹn bản hùng ca | 1,050,000 EXP, Bản Nhạc Cổ Biển |
| MQ065 | Kích Hoạt Trận Đồ Pha Lê | Trung Tâm Kính Giới · Lv. 71 | Cột Trụ Pha Lê | Chiếm lĩnh 4 trụ năng lượng ánh sáng. **Hoàn thành:** Giải câu đố ánh sáng hội tụ | 1,150,000 EXP, Tinh Cầu Pha Lê |
| MQ066 | Đao Kiếm Phản Chiếu | Quảng Trường Gương · Lv. 73 | Binh Đoàn Tinh Thể | Đánh bại đội cận vệ tinh thể phản chiếu sát thương. **Hoàn thành:** Tiêu diệt 15 Cận Vệ Gương | 1,260,000 EXP, Kiếm Pha Lê Tinh Khiết |
| MQ067 | Phá Vỡ Phong Ấn Thời Gian | Đồng Hồ Cát Pha Lê · Lv. 75 | Đồng Hồ Vô Tận | Ngăn chặn Seraphina tua ngược thời gian chữa lành quái. **Hoàn thành:** Đập vỡ đồng hồ cát hư không | 1,380,000 EXP, Bụi Thời Gian |
| MQ068 | Trùm 2 - Khởi Động: Gương Đa Chiều | Điện Pha Lê Chính · Lv. 77 | Seraphina (Phase 1) | Không đánh trực diện, dụ Boss đánh vỡ 4 gương phản đòn. **Hoàn thành:** Phá vỡ 4 gương góc, Lyra phun sương làm mờ | 1,520,000 EXP, Áo Choàng Quang Học |
| MQ069 | Trùm 2 - Đối Đầu: Ảo Ảnh Song Trùng | Điện Pha Lê Chính · Lv. 79 | Bản Sao Kael & Lyra (Phase 2) | Chiến thuật chéo: Kael diệt bản sao Lyra, Lyra cản Kael. **Hoàn thành:** Tiêu diệt đồng thời 2 bản sao trong vòng 5 giây | 1,680,000 EXP, Gương Song Sinh |
| MQ070 | Bẫy Phân Rã Tâm Trí | Không Gian Vô Định · Lv. 80 | Seraphina | Kael bị kéo vào hố đen ý thức, suýt mất kiểm soát. **Hoàn thành:** Nắm lấy bàn tay ánh sáng của Lyra trong hư không | 1,850,000 EXP, Trái Tim Dũng Cảm |
| MQ071 | Trùm 2 - Sinh Tử: Thủy Lôi Thần Hợp Nhất | Không Gian Vỡ Vụn · Lv. 82 | Kính Giới Thiên Sứ (Phase 3) | Sàn vỡ vụn; Lyra rải màng nước dẫn sét toàn bộ sàn kính. **Hoàn thành:** Hoàn thành QTE phóng điện kết liễu Seraphina | 2,050,000 EXP, Pháp Bảo: Kính Giới Chi Lệ |
| MQ072 | Tro Tàn Của Kẻ Kiến Trúc | Sàn Đấu Đổ Nát · Lv. 83 | Xác Pha Lê Seraphina | Seraphina tan biến, để lại lời tiên tri rùng mình về Mặt Trăng. **Hoàn thành:** Nhặt Khối Lập Phương Hư Vô rơi ra | 2,280,000 EXP, Khối Lập Phương Hư Vô |
| MQ073 | Cú Nhảy Vực Mây | Mép Lục Địa Bay · Lv. 83 | Cực Lôi Hoàng Hổ | Cung điện sụp đổ, Kael ôm Lyra nhảy xuống lưng Hoàng Hổ. **Hoàn thành:** Điều khiển thần thú lượn qua các mảnh vỡ rơi tự do | 2,520,000 EXP, Yên Cương Hoàng Kim |
| MQ074 | Bản Đồ Chiếu Lên Đêm Đen | Đài Tinh Tú Aethelgard · Lv. 84 | La Bàn Cổ & Khối Lập Phương | Khối lập phương cộng hưởng la bàn, chiếu bản đồ lên trời. **Hoàn thành:** Xem hologram vành đai cơ khí Mặt Trăng Đỏ | 2,800,000 EXP, La Bàn Vũ Trụ |
| MQ075 | Bí Mật Về Mẹ Của Kael | Tàn Tích Đài Tinh Tú · Lv. 84 | Bản Ghi Âm Của Cha | Cha Kael tiết lộ Thực Thể Thứ 0 và lý do tìm kiếm năng lượng. **Hoàn thành:** Nghe trọn vẹn lời trăn trối của người cha | 3,100,000 EXP, Bức Ảnh Mẹ Kael |
| MQ076 | Cột Mốc Vọng Thiên Kích Hoạt | Đỉnh Tháp Vọng Thiên · Lv. 85 | Cột Năng Lượng Đỏ | Tia sáng đỏ từ mặt đất bắn thẳng lên quỹ đạo Mặt Trăng. **Hoàn thành:** Khởi động bàn điều khiển Cột Mốc Vọng Thiên | 3,450,000 EXP, Chìa Khóa Vọng Thiên |
| MQ077 | Đại Hạm Đội Trồi Lên | Bầu Trời Aethelgard · Lv. 85 | Hạm Đội Hư Không | Những cỗ tàu bay bọc thép đen kịt trồi lên từ tầng mây. **Hoàn thành:** Bắn hạ 5 khinh khí cầu trinh sát của Hư Không | 3,850,000 EXP, Pháo Bắn Nhanh |
| MQ078 | Tiếng Sáo Xé Tầng Mây | Đài Quan Sát Trời · Lv. 85 | Kael & Lyra | Kael huýt sáo hiệu triệu toàn bộ đồng minh sủng vật đã cứu. **Hoàn thành:** Tập hợp đội quân sủng vật cho chiến dịch cuối | 4,300,000 EXP, Tù Và Hiệu Triệu |
| MQ079 | Nụ Hôn Dưới Ánh Đỏ | Mũi Tàu Chiến Thần · Lv. 85 | Lyra | Khoảnh khắc trao trọn niềm tin và tình yêu trước giờ xuất trận. **Hoàn thành:** Mở khóa Danh Hiệu: Tình Yêu Vượt Thiên Cương | 4,800,000 EXP, Danh Hiệu & Kháng Tối Thượng |
| MQ080 | Mở Đường Lên Quỹ Đạo | Bệ Phóng Cột Mốc · Lv. 85 | Toàn Quân Liên Minh | Toàn quân xông lên theo cột sáng Vọng Thiên tiến vào không gian. **Hoàn thành:** Vượt qua lưới lửa phòng không của quân Hư Không | 5,500,000 EXP, Bộ Giáp Phi Hành Thần Thánh |

### Chương 5 theo bảng · Mặt Trăng — nội dung mở rộng cần duyệt (16)

| Mã | Nhiệm vụ | Khu vực · cấp đề xuất | Kích hoạt | Nội dung / hoàn thành | Phần thưởng đề xuất |
| --- | --- | --- | --- | --- | --- |
| MQ081 | Xé Rách Tầng Điện Ly | Cột Mốc Vọng Thiên · Lv. 86 | Tầng Thượng Khí Quyển | Hoàng Hổ tung cánh sấm sét đưa Kael & Lyra thoát ly trọng lực. **Hoàn thành:** Duy trì thanh Lôi Đình bay thẳng vào quỹ đạo | 6,200,000 EXP, Lông Vũ Thần Tốc |
| MQ082 | Vành Đai Rác Cơ Khí | Quỹ Đạo Tầm Thấp · Lv. 87 | Trận Địa Mìn Vũ Trụ | Tránh né mìn cơ khí và mảnh vỡ vệ tinh cổ đại. **Hoàn thành:** Phá hủy 8 quả mìn từ tính hư không | 7,000,000 EXP, Kim Loại Vũ Trụ |
| MQ083 | Tập Kích Tuần Dương Hạm | Bên Ngoài Soái Hạm · Lv. 88 | Hạm Đội Bảo Hộ | Đột nhập soái hạm Hư Không đang hộ tống chìa khóa. **Hoàn thành:** Đổ bộ thành công lên boong tàu chỉ huy | 7,900,000 EXP, Khiên Lực Từ Trường |
| MQ084 | Chiến Đấu Không Trọng Lực | Boong Tàu Chiến Hạm · Lv. 89 | Vệ Binh Trọng Lực | Chiến đấu trong môi trường không trọng lực cực kỳ mới lạ. **Hoàn thành:** Hạ gục 10 Robot Kháng Trọng Lực | 8,900,000 EXP, Giày Nam Châm |
| MQ085 | Vô Hiệu Hóa Động Cơ Hư Vô | Phòng Động Cơ Tàu Mẹ · Lv. 90 | Lõi Nhiệt Hạch Tàu | Lyra dùng nước biển đông kết làm tê liệt động cơ warp. **Hoàn thành:** Kích nổ buồng đốt chính của tàu mẹ | 10,000,000 EXP, Lõi Động Cơ Tinh Vân |
| MQ086 | Chạm Trán Tổng Chỉ Huy Hư Không | Đài Chỉ Huy Soái Hạm · Lv. 91 | Đại Tướng Cơ Giới | Đánh bại kẻ điều phối hạm đội trước khi tàu rơi. **Hoàn thành:** Tiêu diệt Đại Tướng Cơ Giới trong 3 phút | 11,200,000 EXP, Kiếm Quang Học Bậc S |
| MQ087 | Con Tàu Bốc Cháy | Khoang Thoát Hiểm · Lv. 92 | Cực Lôi Hoàng Hổ | Tàu mẹ nổ tung; Hoàng Hổ chở Kael và Lyra phóng về Mặt Trăng. **Hoàn thành:** Điều khiển thoát hiểm né mảnh vỡ nổ | 12,500 EXP x 1000, Nhẫn Tinh Vân |
| MQ088 | Bề Mặt Mặt Trăng Đỏ | Bình Nguyên Bụi Máu · Lv. 93 | Bề Mặt Mặt Trăng | Đặt chân lên cát đỏ rực, cảm nhận nhịp đập của Thực Thể Thứ 0. **Hoàn thành:** Tìm thấy cửa hầm dẫn vào Lõi Mặt Trăng | 14,000,000 EXP, Bình Dưỡng Khí Vũ Trụ |
| MQ089 | Dấu Vết Của Người Mẹ | Trạm Nghiên Cứu Số 1 · Lv. 94 | Hình Chiếu Của Mẹ | Tìm thấy phòng ngủ đóng băng của mẹ Kael mười mấy năm trước. **Hoàn thành:** Mở khóa buồng ngủ đông bằng la bàn gia tộc | 15,800,000 EXP, Vòng Tay Người Mẹ |
| MQ090 | Sự Thật Về Giấc Ngủ Đông | Hệ Thống Duy Trì Sự Sống · Lv. 95 | Máy Chủ Cổ Đại | Mẹ Kael không chết, bà tình nguyện ngủ đông duy trì phong ấn. **Hoàn thành:** Bảo vệ hệ thống hỗ trợ sự sống của người mẹ | 17,800,000 EXP, Tinh Thể Sự Sống |
| MQ091 | Đánh Thức Vành Đai Cơ Khí | Trung Tâm Vành Đai Quỹ Đạo · Lv. 96 | Bộ Xương Cơ Khí Khổng Lồ | Hệ thống phòng thủ vệ tinh của Thực Thể Thứ 0 thức giấc. **Hoàn thành:** Phá hủy 4 tháp phát tia plasma quỹ đạo | 20,000,000 EXP, Giáp Trụ Mặt Trăng |
| MQ092 | Nhịp Đập Của Trái Tim Đen | Đại Sảnh Lõi Mặt Trăng · Lv. 97 | Lõi Hư Không Đỏ | Tiến sâu vào tâm Mặt Trăng, nơi kén chứa Thực Thể Thứ 0. **Hoàn thành:** Dọn sạch quái vật phôi thai hư không | 22,500,000 EXP, Thạch Hư Không |
| MQ093 | Nguyện Ước Chung Đôi | Trước Kén Thần · Lv. 98 | Lyra | Kael và Lyra kết hợp toàn bộ thánh vật và tình yêu chân chính. **Hoàn thành:** Kích hoạt Kết Giới Uyên Ương Bất Tử | 25,500,000 EXP, Kỹ Năng Đôi Tối Thượng |
| MQ094 | Cắt Đứt Ống Dẫn Sinh Mệnh | Lõi Kén Thực Thể Thứ 0 · Lv. 99 | Hệ Thống Dẫn Máu Máy | Cắt đứt dòng chảy tà khí đang truyền vào kén ngủ say. **Hoàn thành:** Phá hủy 6 ống dẫn năng lượng titan | 29,000,000 EXP, Thần Dược Hồi Sinh |
| MQ095 | Mắt Thần Hé Mở: Trận Chiến Mở Đầu | Lõi Kén Thức Giác · Lv. 100 | Thực Thể Thứ 0 (Avatar) | Trực tiếp giao chiến phân thân thức tỉnh đầu tiên của Boss cuối. **Hoàn thành:** Rút 50% HP của Phân Thân Thực Thể Thứ 0 | 33,000,000 EXP, Vũ Khí Thần Thoại |
| MQ096 | Khúc Khải Hoàn Bất Tận | Bệ Phóng Niềm Tin · Lv. 100 | Kael, Lyra, Gia Đình | Tái lập phong ấn thành công, đưa mẹ trở về nhân gian, mở ra kỷ nguyên mới. **Hoàn thành:** Xem Đại Kết Cục & Mở Khóa Chế Độ Tự Do | 50,000,000 EXP, Danh Hiệu Chúa Tể VQSV |

## Nhiệm vụ phụ

Các tuyến dưới đây là nội dung bổ sung tùy chọn. Tên tuyến lấy theo bảng nguồn; trình tự mở, NPC, mức độ liên quan tới truyện và phần thưởng cần duyệt trước khi triển khai.

### Tuyến A · Kael và Lyra (10)

| Mã | Nhiệm vụ | Khu vực · cấp đề xuất | Kích hoạt | Nội dung / hoàn thành | Phần thưởng đề xuất |
| --- | --- | --- | --- | --- | --- |
| SQ001 | Vỏ Sò Ánh Trăng Tặng Nàng | Vương Quốc Atlantis · Lv. 32 | Kael (Chủ động) | Tìm 3 Vỏ Sò Ánh Trăng dưới rạn san hô xanh làm dây chuyền cho Lyra. **Hoàn thành:** Nhặt đủ 3 Vỏ Sò & Tặng Lyra | Mở khóa biểu cảm Hẹn Hò 1, +500 Hảo Cảm |
| SQ002 | Món Ăn Quê Hương Của Kael | Nhà Cũ Làng Sương Mù · Lv. 35 | Lyra | Thu thập nguyên liệu lục địa nấu món Bánh Ngô Nướng đặc sản cho Lyra nếm thử. **Hoàn thành:** Nấu thành công món ăn & chia sẻ | Công thức Bánh Ngô Hồi Máu, +600 Hảo Cảm |
| SQ003 | Bí Ẩn Tóc Bạc Của Bán Nhân Ngư | Hồ Tinh Khiết Biển · Lv. 38 | Nữ Tế Tư Atlantis | Hái hoa Băng Thủy Nguyệt để Lyra chải tóc dưới ánh trăng rằm. **Hoàn thành:** Thu thập 5 Bông Băng Thủy Nguyệt | Trang phục Dạ Hội Biển Sâu cho Lyra |
| SQ004 | Khiêu Vũ Giữa Tầng Mây | Đài Quan Sát Gió · Lv. 51 | Kael & Lyra | Kael dạy Lyra các bước nhảy truyền thống của tộc gió trên đỉnh khí cầu. **Hoàn thành:** Hoàn thành minigame nhịp điệu (Rhythm) | Kỹ Năng Phối Hợp: Nhịp Bước Uyên Ương |
| SQ005 | Trái Tim Pha Lê Ấm Áp | Đỉnh Núi Lửa Hắc Diệu · Lv. 55 | Lyra | Thu thập Tinh Thể Nhiệt Băng làm túi sưởi tay cho Lyra chống rét. **Hoàn thành:** Khai thác 3 Tinh Thể Nhiệt Băng | Phụ kiện: Găng Tay Sưởi Ấm Lyra |
| SQ006 | Nguyện Ước Dưới Mưa Sao Băng | Đồi Sao Aethelgard · Lv. 64 | Kael | Ngồi cạnh Lyra ngắm trận mưa sao băng lịch sử 100 năm mới có một lần. **Hoàn thành:** Chụp 3 bức ảnh khoảnh khắc sao rơi | Danh hiệu: Lời Thề Dưới Sao Băng |
| SQ007 | Bài Hát Ru Chưa Đặt Tên | Phòng Nghỉ Tàu Bay · Lv. 72 | Lyra | Lắng nghe Lyra hoàn thiện bài hát ru mẹ cô từng hát thuở bé. **Hoàn thành:** Đạt điểm Perfect ở bài hát ru | Vật phẩm nghe nhạc: Bản Hát Ru Biển Khơi |
| SQ008 | Cùng Nhau Chăm Sóc Sủng Vật | Trang Trại Sủng Vật · Lv. 78 | Chủ Trang Trại | Cùng Lyra tắm rửa và cho đàn sủng vật non ăn trong kỳ nghỉ ngắn. **Hoàn thành:** Hoàn thành 3 hoạt động chăm sóc | Tăng 20% tốc độ tiến hóa của toàn đội |
| SQ009 | Chiếc Nhẫn San Hô Lôi Đình | Thợ Rèn Huyền Thoại · Lv. 83 | Thợ Rèn Hepha | Rèn một cặp nhẫn từ Bạc Sấm Sét và San Hô Đỏ cho Kael và Lyra. **Hoàn thành:** Thu thập 2 Thỏi Lôi Bạc & 1 San Hô Đỏ | Cặp Nhẫn Định Tình (+15% Sát Thương Đôi) |
| SQ010 | Hẹn Ước Sau Ngày Chiến Thắng | Boong Tàu Quỹ Đạo · Lv. 88 | Lyra | Tâm sự về ngôi nhà bên bờ biển hai người sẽ cùng xây sau khi thế giới hòa bình. **Hoàn thành:** Đối thoại lựa chọn tương lai | Danh hiệu độc quyền: Bạn Đời Tri Kỷ |

### Tuyến B · Pet và tiến hóa (12)

| Mã | Nhiệm vụ | Khu vực · cấp đề xuất | Kích hoạt | Nội dung / hoàn thành | Phần thưởng đề xuất |
| --- | --- | --- | --- | --- | --- |
| SQ011 | Tiếng Sấm Trong Rừng Phong Lôi | Rừng Lôi Phạt · Lv. 15 | Thợ Săn Già | Săn lùng Lôi Điêu Đột Biến mang lông vũ tích điện cao cấp. **Hoàn thành:** Bắt hoặc đánh bại Lôi Điêu Đột Biến | Đá Tiến Hóa Hệ Lôi Cấp 1 |
| SQ012 | Hạt Giống Cổ Thụ Ngàn Năm | Cổ Thụ Ryan · Lv. 22 | Linh Hồn Rừng | Đánh thức Sủng Vật Mộc Tinh Thạch đang hóa đá dưới rễ cây. **Hoàn thành:** Tưới 3 bình Nước Suối Thần | Trứng Sủng Vật: Mộc Tinh Thú |
| SQ013 | Băng Cốt Long Say Ngủ | Đỉnh Núi Băng Vĩnh Cửu · Lv. 28 | Nhà Khảo Cổ Băng | Tìm lại 4 đốt xương rồng băng cổ đại bị quái thú gặm nhấm. **Hoàn thành:** Thu thập đủ 4 Khúc Băng Cốt | Mở khóa nhánh tiến hóa Rồng Băng |
| SQ014 | Ngọn Lửa Không Bao Giờ Tắt | Hầm Dung Nham Sâu · Lv. 35 | Linh Hồn Lửa Cổ | Thách thức Hỏa Kỳ Lân bảo vệ ngọn đuốc nguyên thủy. **Hoàn thành:** Đánh bại Hỏa Kỳ Lân trong 2 phút | Tinh Hoa Hỏa Diễm Bất Diệt |
| SQ015 | Cá Voi Lân Quang Biển Sâu | Rãnh Nứt Đại Dương · Lv. 42 | Hộ Vệ Biển Sâu | Chữa lành vết thương cho đàn cá voi lân quang bị máy hút cạn dầu. **Hoàn thành:** Hồi máu cho 3 chú Cá Voi Con | Sủng Vật Thú Cưỡi Biển: Bạch Kình |
| SQ016 | Linh Hồn Sa Ngư Tái Sinh | Đáy Biển U Tối · Lv. 45 | U Linh Sa Ngư | Thu thập 10 Tinh Phách U Uẩn giúp Sa Ngư tiến hóa lên bậc Hoàng Kim. **Hoàn thành:** Nộp đủ 10 Tinh Phách U Uẩn | U Linh Sa Ngư Hoàng Kim Bậc SS |
| SQ017 | Vuốt Sấm Của Hoàng Hổ | Đỉnh Sấm Sét · Lv. 55 | Sư Tôn Ẩn Dật | Để Cực Lôi Hoàng Hổ hấp thụ 100 tia sét tự nhiên không gián đoạn. **Hoàn thành:** Hứng chịu 100 tia sét trong lôi đài | Kỹ Năng Tối Thượng: Lôi Bạo Cuồng Nộ |
| SQ018 | Phượng Hoàng Lửa Tái Sinh | Tổ Phượng Hoàng Tro · Lv. 62 | Đền Lửa Aethelgard | Dâng 5 Ngọn Lửa Tinh Khiết để ấp nở quả trứng Phượng Hoàng Tro. **Hoàn thành:** Ấp nở thành công Trứng Phượng Hoàng | Sủng Vật Cực Phẩm: Hỏa Phượng Hoàng |
| SQ019 | Cánh Bướm Pha Lê Ảo Mộng | Vườn Hoa Kính · Lv. 68 | Tiên Hoa Pha Lê | Bắt 5 chú bướm phát sáng trong cung điện kính để chiết xuất phấn hoa. **Hoàn thành:** Bắt đủ 5 Điệp Tinh Thể | Thuốc Đột Phá Tiềm Năng Sủng Vật |
| SQ020 | Thần Thú Cơ Khí Thuần Hóa | Bãi Rác Phế Liệu · Lv. 75 | Robot Bị Hỏng K-9 | Cài đặt lại hệ điều hành cho một chú chó máy bị Vane bỏ rơi. **Hoàn thành:** Sửa chữa bo mạch & lắp pin năng lượng | Sủng Vật Đồng Hành: Khuyển Cơ Giới K-9 |
| SQ021 | Lôi Long Giáng Thế | Tháp Sét Cổ Đại · Lv. 82 | Bia Ký Thần Thú | Hợp nhất linh hồn Cực Lôi Hoàng Hổ với Long Hồn Thượng Cổ. **Hoàn thành:** Đánh bại Thử Thách Long Hồn | Hình Thái Mới: Hoàng Kim Lôi Long Thú |
| SQ022 | Thần Thú Tinh Vân Quỹ Đạo | Bụi Sao Mặt Trăng · Lv. 92 | Tinh Linh Tinh Tú | Thu thập 7 Mảnh Bụi Sao Đỏ để kích hoạt sủng vật ngoài hành tinh. **Hoàn thành:** Ghép đủ 7 Mảnh Bụi Tinh Tú | Sủng Vật Vũ Trụ: Tinh Vân Ấu Thú |

### Tuyến C · Giải mã và khám phá (9)

| Mã | Nhiệm vụ | Khu vực · cấp đề xuất | Kích hoạt | Nội dung / hoàn thành | Phần thưởng đề xuất |
| --- | --- | --- | --- | --- | --- |
| SQ023 | Bí Ẩn Chữ Viết Cổ Ryan | Thư Viện Vương Đô · Lv. 18 | Học Giả Noel | Dịch 3 tấm bia đá cổ rải rác quanh làng sương mù. **Hoàn thành:** Dập bản khắc của 3 tấm bia | Sách Kỹ Năng: Hiểu Biết Cổ Tự |
| SQ024 | Mảnh Vỡ La Bàn Đầu Tiên | Mê Cung Dưới Lòng Đất · Lv. 24 | Rương Cổ Bị Khóa | Giải câu đố bánh răng đồng hồ để mở rương báu của nhà thám hiểm cổ. **Hoàn thành:** Xoay đúng trục cơ quan 3 tầng | Mảnh Ghép La Bàn Số 1 |
| SQ025 | Hải Đồ Thất Lạc Của Hải Tộc | Kho Lưu Trữ Atlantis · Lv. 33 | Thủ Thư Biển Sâu | Khôi phục tấm hải đồ vẽ đường đi của các dòng hải lưu ngầm. **Hoàn thành:** Nối ghép 5 mảnh giấy da cừu | Bản Đồ Bí Mật Hải Vực Biển Sâu |
| SQ026 | Chìa Khóa Nước Vĩnh Cửu | Suối Nguồn Atlantis · Lv. 39 | Trụ Cột Nguyên Thủy | Tìm 3 viên ngọc nước ẩn giấu trong mê cung san hô. **Hoàn thành:** Đặt 3 viên ngọc vào bệ thờ | Thánh Vật Cổ: Giọt Lệ Biển Ngàn Năm |
| SQ027 | Nhật Ký Của Kẻ Phản Bội | Trạm Thí Nghiệm Cũ · Lv. 48 | Tủ Hồ Sơ Bị Cháy | Thu thập các trang nhật ký rời rạc của đồng đội cũ của cha Kael. **Hoàn thành:** Tìm đủ 6 trang nhật ký mật | Trang Bị: Áo Choàng Học Giả Cổ |
| SQ028 | Thiên Cung Đồ Aethelgard | Đài Quan Sát Thiên Văn · Lv. 57 | Kính Viễn Vọng Cổ | Điều chỉnh 4 lăng kính hướng về các chòm sao hộ mệnh. **Hoàn thành:** Khớp đúng 4 chòm sao trên bầu trời | Ngọc Tăng Tỉ Lệ Bạo Kích Cổ |
| SQ029 | Bia Đá Tiên Tri Về Thực Thể 0 | Vách Đá Gió Hú · Lv. 66 | Bia Đá Nứt Nẻ | Tẩy sạch rêu phong và giải mã lời cảnh báo về Mặt Trăng Đỏ. **Hoàn thành:** Dùng bột dạ quang chép lại ký tự | Mảnh Chìa Khóa Tinh Thạch Số 3 |
| SQ030 | Mật Mã Cửa Hầm Soái Hạm | Tàu Hư Không Đắm · Lv. 87 | Bảng Điều Khiển Mã Hóa | Giải chuỗi mật mã số nhị phân của nền văn minh máy móc. **Hoàn thành:** Nhập đúng mã logic 4 ký tự | Mở kho vũ khí tối mật: 100.000 Vàng |
| SQ031 | Bức Thư Tay Của Người Mẹ | Phòng Thí Nghiệm Mặt Trăng · Lv. 94 | Hộp Kỷ Vật | Dùng hơi ấm từ chiếc la bàn để làm hiện chữ mực tàng hình. **Hoàn thành:** Đọc toàn bộ bức thư của mẹ | Vật Phẩm Vĩnh Cửu: Nụ Cười Của Mẹ |

### Tuyến D · Truy quét và giải cứu (10)

| Mã | Nhiệm vụ | Khu vực · cấp đề xuất | Kích hoạt | Nội dung / hoàn thành | Phần thưởng đề xuất |
| --- | --- | --- | --- | --- | --- |
| SQ032 | Tiêu Hủy Trinh Sát Cơ | Bình Nguyên Ryan · Lv. 12 | Đội Trưởng Tuần Tra | Bắn hạ 5 drone trinh sát không người lái bay do thám làng. **Hoàn thành:** Bắn hạ đủ 5 Drone | 10 Quả Đạn Sấm Sét |
| SQ033 | Phá Hủy Trạm Khai Thác Cát | Sa Mạc Xích Diễm · Lv. 20 | Thương Nhân Sa Mạc | Kích nổ cỗ máy xúc đào cát trái phép của bọn tay sai. **Hoàn thành:** Cài 3 gói thuốc nổ vào bánh xích máy xúc | 500 Quặng Sắt Siêu Bền |
| SQ034 | Giải Cứu Đàn Rùa Biển Bị Gắn Chip | Bãi Cát Đẻ Trứng · Lv. 29 | Tình Nguyện Viên Biển | Tháo chip kiểm soát điện tử gắn trên mai của 6 con rùa biển. **Hoàn thành:** Gỡ chip thành công không gây thương tích | Khiên Mai Rùa Bọc Thép |
| SQ035 | Tập Kích Kho Nhiên Liệu Ngầm | Đáy Biển Vane · Lv. 36 | Biệt Đội Kháng Chiến | Phóng hỏa kho chứa dầu tinh luyện của xưởng chế tạo quái thú. **Hoàn thành:** Phá hủy 4 bồn chứa nhiên liệu | Lõi Nhiệt Hạch Thu Nhỏ |
| SQ036 | Săn Lùng Sát Thủ Người Máy V-1 | Khu Ổ Chuột Atlantis · Lv. 41 | Cảnh Sát Trưởng Biển | Lần theo dầu rỉ để tiêu diệt người máy sát thủ ngầm. **Hoàn thành:** Đánh bại Kẻ Ám Sát V-1 | Dao Găm Năng Lượng Laser |
| SQ037 | Phá Lò Rèn Titan Của Boran | Xưởng Cơ Khí Núi Lửa · Lv. 53 | Nô Lệ Đào Tẩu | Đập tan 3 khuôn đúc giáp rồng của xưởng sản xuất Boran. **Hoàn thành:** Phá vỡ 3 khuôn thép titan | 2 Thỏi Titan Tinh Chế |
| SQ038 | Chặn Chuyến Xe Tiếp Tế Năng Lượng | Đường Ray Núi Lửa · Lv. 59 | Quân Khởi Nghĩa | Phục kích đoàn tàu hỏa bọc thép chở tinh thể dung nham. **Hoàn thành:** Cướp đoàn tàu chở hàng | 10 Bình Tinh Thể Nhiệt Siêu Bền |
| SQ039 | Giải Thoát Đàn Chim Máy Bị Giam Cầm | Chuồng Thú Aethelgard · Lv. 67 | Người Nuôi Thú Trời | Bẻ khóa 5 lồng điện giam các giống chim quý bị gắn cánh cơ học. **Hoàn thành:** Bẻ khóa 5 lồng phóng sinh | Lông Vũ Tốc Độ Tối Thượng |
| SQ040 | Hacker Đột Nhập Mạng Lưới | Trạm Viễn Thông Hư Không · Lv. 76 | Thiên Tài Lập Trình Rin | Bảo vệ Hacker Rin trong khi cô ta cài virus vào máy chủ Hư Không. **Hoàn thành:** Bảo vệ Hacker trong 3 phút | Phần Mềm Diệt Virus Hư Không |
| SQ041 | Xóa Sổ Căn Cứ Chế Tạo Phân Thân | Nhà Máy Gương · Lv. 81 | Bản Sao Phản Tỉnh | Cho nổ tung xưởng sản xuất hàng loạt các bản sao người máy Seraphina. **Hoàn thành:** Kích hoạt ngòi nổ trung tâm xưởng | Rương Báu Vũ Khí Hạng Nặng |

### Tuyến E · Huấn luyện sư (6)

| Mã | Nhiệm vụ | Khu vực · cấp đề xuất | Kích hoạt | Nội dung / hoàn thành | Phần thưởng đề xuất |
| --- | --- | --- | --- | --- | --- |
| SQ042 | Đấu Trường Tân Thủ Ryan | Đấu Trường Ryan · Lv. 20 | Chủ Đấu Trường Ryan | Đánh bại 3 đối thủ trong giải đấu nghiệp dư đầu tiên. **Hoàn thành:** Chiến thắng 3 trận liên tiếp | Cúp Tân Thủ, Áo Choàng Đấu Sĩ |
| SQ043 | Bậc Thầy Bắt Thú Bằng Tay Không | Thảo Nguyên Xanh · Lv. 30 | Bác Thợ Săn Lão Luyện | Dùng cầu bắt thú cơ bản thu phục 3 sủng vật hiếm mà không làm chúng mất máu. **Hoàn thành:** Bắt thành công 3 sủng vật | Danh hiệu: Bàn Tay Thép |
| SQ044 | Khiêu Chiến Đấu Trường Đại Dương | Vực Đấu Thủy Cung · Lv. 44 | Quán Quân Tộc Biển | Vượt qua thử thách đánh bại 3 Quán Quân Tộc Biển dưới đáy nước. **Hoàn thành:** Hạ gục 3 Huấn Luyện Sư Biển | Huy Hiệu Hải Vương, Khiên Thủy Tinh |
| SQ045 | Giải Đấu Lôi Đài Không Trung | Đấu Trường Mây Aethelgard · Lv. 65 | Trọng Tài Bầu Trời | Đánh bại Đội Trưởng Phi Hành Đoàn trong điều kiện sàn đấu rung lắc. **Hoàn thành:** Đánh bại Quán Quân Bay | Đôi Cánh Danh Dự, Cúp Thiên Không |
| SQ046 | Rèn Luyện Ý Chí Kim Cương | Phòng Rèn Trọng Lực · Lv. 80 | Đại Sư Lực Lượng | Sống sót 5 phút trong phòng áp suất trọng lực gấp 10 lần. **Hoàn thành:** Hoàn thành bài tập trọng lực | Tăng vĩnh viễn 15% Máu & Thủ |
| SQ047 | Đỉnh Cao Huấn Luyện Sư Vũ Trụ | Sàn Đấu Quỹ Đạo Mặt Trăng · Lv. 95 | Linh Hồn Huấn Luyện Sư Đầu Tiên | Khiêu chiến bóng ma của Huấn Luyện Sư Huyền Thoại vĩ đại nhất lịch sử. **Hoàn thành:** Chiến thắng trận solo 1v1 đỉnh cao | Danh Hiệu: Đại Tông Sư VQSV Vĩnh Hằng |

## Thứ tự thực hiện đề xuất

1. Duyệt các điểm xung đột và cốt truyện sau Mặt Trăng trước khi khóa danh sách.
2. Chọn một nhóm nhiệm vụ đầu làng để chuyển thành nhiệm vụ có thể chơi, gắn NPC, điều kiện hoàn thành và bản lưu.
3. Làm tiếp theo từng khu vực; cân bằng cấp, số lượng và phần thưởng sau khi hệ chiến đấu, pet và kho đồ hoạt động.
