# Prototype lát chơi thử 01

## Chạy trên Windows

Có thể mở `index.html` trực tiếp. Dữ liệu map, tileset và roster pet đã được gộp vào `data/bootstrap.js` để trình duyệt không chặn tải JSON khi dùng `file://`.

Hoặc mở PowerShell tại thư mục `prototype`, chạy `node serve.mjs`, rồi vào `http://127.0.0.1:4177`.

Máy chủ chỉ phục vụ file tĩnh từ thư mục prototype và lắng nghe trên loopback. Nếu sửa `opening_map.json`, JSON tileset, `pet_roster_v1.json` hoặc `vqsv5_pet_animations.json`, chạy `python ..\tools\build_prototype_bootstrap.py` để cập nhật bootstrap trước khi mở lại.

## Chơi trên web (HTML)

Game là trang HTML tĩnh, không cần cài gì thêm:

- **Máy chủ thử nhanh:** `cd prototype && node serve.mjs` rồi mở `http://127.0.0.1:4177`.
- **GitHub Pages:** vào *Settings → Pages → Build and deployment → Deploy from a branch*, chọn nhánh (ví dụ `main` sau khi gộp, hoặc đúng nhánh đang làm) và thư mục `/ (root)`. Trang gốc `index.html` của repo tự chuyển tới `vqsv-unified-game/prototype/index.html`, địa chỉ có dạng `https://<tên-tài-khoản>.github.io/VQSV/`. Toàn bộ đường dẫn là tương đối nên chạy được ở thư mục con; tiến trình và túi đồ lưu ở trình duyệt của từng người chơi.
- **Điện thoại và máy tính bảng:** trên màn hình cảm ứng (hoặc cửa sổ hẹp) có bàn phím cảm ứng ngay dưới khung game: bốn nút mũi tên để đi và nút **E** để nói chuyện/tương tác. Các menu trận đấu, cửa hàng, sổ pet và túi đồ đều bấm được bằng tay.

## Điều khiển

- `WASD` hoặc phím mũi tên: đi lại.
- `E` hoặc `Enter`: nói chuyện / theo dấu vết / tiếp tục thoại.
- `J` hoặc nút **Nhật ký nhiệm vụ**: xem các bước của nhiệm vụ chính và mục tiêu tiếp theo; dấu `!` trên người gác làng chỉ nhiệm vụ mở đầu.
- `M` hoặc nút **Bản đồ thế giới**: xem tranh tổng thể và danh sách các vùng/địa điểm theo truyện. Bản đồ thế giới có 15 vùng đi được. Dùng **Phóng to bản đồ** rồi cuộn ngang hoặc dọc để xem các vùng ở rìa. Sau khi nói chuyện với người gác làng, đi qua vườn và cầu gỗ ở phía nam làng, rồi bấm **Tương tác** tại lối sang **Hoang Mạc Xích Diễm**. Tuyến này nối 29 cảnh qua 10 vùng tới **Mặt Trăng Đỏ**. 29 cảnh dùng 29 bố cục và lưới va chạm VQSV5. Nhấn M để xem các cổng và chọn Chỉ đường. Có 8 lối tắt hai chiều, chẳng hạn từ Hoang Mạc Xích Diễm đến Hỏa Sơn Bất Diệt. TRIAL: cách ghép địa danh, bảng màu và vị trí cổng do prototype thiết kế. Bản lưu từ bố cục cũ đưa Kael về lối vào cùng địa danh. Chọn địa điểm trong tab **Thế giới** để xem tranh và bấm **Xem lối đi** để tìm cổng gần nhất dẫn tới đó. Tuyến mới phục vụ khám phá phong cảnh.
- **Phía nam làng biên giới (TRIAL):** map làng dài 56 ô. Đi bộ từ đoạn đường đất cạnh giếng qua vườn, cầu gỗ và đường vòng ở bờ nam để tới cổng Hoang Mạc Xích Diễm. Kael di chuyển liên tục trên cùng map. Dữ liệu dùng chung cho va chạm và ảnh nằm trong `data/village_south.json`. Chạy `python tools/render_village_south.py` để dựng nền và ảnh bản đồ khu vực.
- **Ba vùng ngoài khơi (TRIAL):** Cao nguyên Ngọc Lục nối Rừng Rậm Phong Lôi với Đèo băng, Quần đảo San Hô Hồng nối Bến cảng Ryan với Hải Vực Vô Tận, Bán đảo Đồng Đỏ nối Hoang Mạc Xích Diễm với Hẻm núi cát đỏ. Mỗi vùng có hai bản đồ pixel gốc mới, kích thước 76–84 × 60–66 ô, với đường vòng, sân rộng, cầu và cổng hai chiều. Dữ liệu nằm trong `data/atlas_expansion.json`. Chạy `python tools/render_atlas_expansion.py` để dựng ảnh.
- Sau khi nói chuyện với người gác làng, đi tới lối phía đông và nhấn `E`: làng nối với rừng, rừng nối với Suối Sấm; nhấn `E` ở lối vào để quay lại. Suối Sấm là địa điểm thiết kế thử, không phải địa danh đã xác nhận trong kịch bản. Nước chặn đường và có cầu bắc qua.
- Nhiệm vụ phụ thử **Thảo dược cho Lôi Miêu**: trước khi kết thúc trận truyện, nói chuyện hết với người chăm sóc pet để nhận, tìm 3 khóm phát sáng quanh làng, rồi trở lại nói chuyện để hoàn thành. Nhật ký và bản lưu ghi lại tiến độ; phần thưởng thử nghiệm: 300 kim tiền, 1 huy hiệu và 2 Bánh Sandwich (nhận một lần). Đây là nhiệm vụ thử mới lấy cảm hứng từ chăm sóc pet, không phải MQ004 hoàn chỉnh trong bảng nhiệm vụ.
- Trong trận, `1`: kỹ năng hệ của pet đang ra trận; `2`: thủ; `3`: đòn thường; `4`: đổi pet. Chọn **Chiến đấu** để mở bảng. **Trận dùng công thức VQSV6 đã giải mã** (xem `PET_ROSTER_CATALOG.md`): chỉ số theo cấp và hạng, sát thương = Công − Thủ nhân sức mạnh kỹ năng, chí mạng 5% + Tốc/2, khắc hệ ×3 / ×0,6, bên Tốc cao hơn đánh trước. Lôi Miêu dùng record Điện Miêu (cấp 5: HP 115, Công 26, Thủ 15, Tốc 5) với Điện giật 45 lần; Điện Miêu chưa được xác nhận là Lôi Miêu trong truyện. Đối thủ dùng record Tà Vân Khuyển Thần (hệ Hỏa) ở cấp 3. **Giá trị thử nghiệm:** cấp 5 của đội và cấp 3 của đối thủ, Vồ nhanh/Đòn thường 70% không giới hạn, Phòng thủ nhận 40% sát thương, thuốc hồi 30% HP.
- Các mục lệnh tham khảo bản mẫu: **Đạo cụ** mở túi đạo cụ hồi phục (xem mục Cửa hàng); **Sủng vật** (phím `4`) mở bảng đội hình để đổi pet, kèm nút xem sổ pet; **Thương điếm** mở cửa hàng (giá gấp đôi trong trận); **Rút khỏi trận** quay về bản đồ và có thể bắt đầu lại trận. **Bắt pet** chưa khả dụng trong trận truyện.
- **Cửa hàng, ví và đạo cụ** (bảng 3 và 4 của `db.mid`, luật từ `game/k.java`, `game/j.java`, `game/i.java`): nhấn `B` hoặc nút **Thương điếm** ở làng biên giới để mua/bán; trong trận nút **Thương điếm** mở cùng cửa hàng với giá gấp đôi và không bán lại được. Hai loại tiền: **kim tiền** và **huy hiệu** (mỗi món dùng đúng loại tiền của nó, ví dụ Phong ấn cầu 50 kim tiền, Tất Trung Cầu 2 huy hiệu, Tinh Nguyên Thạch 1000 kim tiền, Hồn Tinh Thạch 2 huy hiệu). Bán lại được nửa giá bằng chính loại tiền đó (chỉ đạo cụ và cầu). Hàng có 4 loại cầu, 9 đạo cụ hồi phục, Tị quái hàn và 5 loại đá tiến hóa. Quà khởi đầu theo game gốc: 2000 kim tiền, 2 huy hiệu, 5 Phong ấn cầu, 5 Bánh Sandwich, 2 Sinh mệnh thạch (thêm 2 Cao cấp cầu là phần thử nghiệm). **Đạo cụ trong trận** (phím nút **Đạo cụ**, chọn món rồi chọn pet, mất một lượt): Bánh Sandwich/Chocolate hồi HP (50% + 50 / 100%), Năng lượng Thạch/Tụ Năng Thạch hồi số lần dùng kỹ năng (25 / 45), Khôi phục tề/Siêu năng tề hồi cả hai, Vạn năng dược xóa trạng thái bất lợi, Sinh mệnh thạch/Linh Hồn Thạch hồi sinh pet kiệt sức (50% + 50 / 100% HP kèm số lần kỹ năng). Quấn Quanh cấm dùng đạo cụ. Tị quái hàn (Túi đồ, phím `I`, nút **Dùng**): 500 bước không gặp pet hoang dã. Không còn thuốc thử và không còn tự bổ sung cầu khi về làng. **Thử nghiệm:** game gốc không cho kim tiền từ trận đấu (chỉ từ nhiệm vụ và đồng xu trên map), nên prototype cho 15 kim tiền mỗi cấp pet hoang dã (cộng ngẫu nhiên tới 5 mỗi cấp) khi thắng hoặc bắt, 200 kim tiền cho lần thắng cốt truyện đầu tiên, và phần thưởng nhiệm vụ Thảo dược (300 kim tiền, 1 huy hiệu, 2 Bánh Sandwich); các phần thưởng cốt truyện/nhiệm vụ chỉ nhận một lần cho mỗi bản lưu trình duyệt. Mỗi loại đồ giữ tối đa 99. **Chưa làm:** Chìa khóa vàng, Gia tốc dược (ấp trứng) và cửa hàng cao cấp giá ×1,5 của game gốc. Ví lưu ở `vqsv-unified-wallet-v1`, túi ở `vqsv-unified-bag-v1`.
- **Các vùng pet hoang dã cấp cao:** từ **Suối Sấm** đi tiếp về phía đông (lối ở mép phải mỗi map) qua 6 vùng, mỗi vùng dùng các khu `petArea.mid` riêng của VQSV6 (không khu nào dùng lại): **Hồ Sương Mù** (cấp 8–14, có cầu bắc qua nước: đứng trên cầu gặp pet nước), **Đồng Cỏ Lộng Gió** (13–19), **Cao Nguyên Đá** (19–25), **Hẻm Núi Đá** (24–32), **Bãi Tro Núi Lửa** (31–36) và **Dải Mây Cao** (35–46). Dải cấp, danh sách pet và hạng của từng khu lấy nguyên từ dữ liệu gốc; pet hoang dã cấp cao có số ô kỹ năng theo cấp (cấp 40 trở lên có 5 kỹ năng) và đưa ra nhiều đá tiến hóa cao cấp hơn khi bị đánh bại (xem bảng rơi thử nghiệm). Khi vào vùng mới thanh trạng thái và mục tiêu ghi dải cấp pet hoang dã. **Giới hạn:** hình ảnh các vùng là bản tạm dựng từ hai bộ tile đã có (đổi nền, đá, cây và sắc màu) vì bản đồ nguồn của các khu này chưa giải mã; dữ liệu nguồn chỉ có pet hoang dã tới cấp 46 nên cấp 47–70 chưa có đối thủ hoang dã. Chạy `python tools/design_wild_maps.py` rồi `python tools/build_prototype_bootstrap.py` để dựng lại các map.
- **Bắt pet hoang dã:** sau khi nói chuyện với người gác làng, đi trong **Rừng Rậm Phong Lôi** hoặc **Suối Sấm** sẽ ngẫu nhiên gặp pet hoang dã. Danh sách pet, dải cấp và hạng lấy từ `petArea.mid` của VQSV6: rừng dùng khu 1-1 và 1-2 (cấp 5), suối dùng khu 1-3 và 1-4 (cấp 5–7, có Điện Miêu hạng 2). Trong trận chọn **Bắt pet** (phím `5`) rồi chọn cầu; bảng hiện tỉ lệ bắt theo công thức gốc (HP ≤15% → 85, ≤50% → 45, còn lại 20; nhân hệ số cầu Phong ấn 100% / Cao cấp 140% và hạng pet 110/100/95/80/70%; pet cấp ≥ 20 bị giới hạn). Ném cầu mất một lượt. Bắt được thì pet vào sổ (bộ lọc **Pet đã bắt**), tự vào đội nếu còn chỗ và khi ra trận dùng đúng cấp, hạng đã bắt. Trận cốt truyện không cho bắt. **Giá trị thử nghiệm:** việc gán khu nguồn cho map prototype, tỉ lệ gặp 1/14 mỗi bước sau 6 bước an toàn, Cầu mua ở Thương điếm (xem mục Cửa hàng); túi đồ và danh sách pet đã bắt lưu trên trình duyệt (`vqsv-unified-bag-v1`, `vqsv-unified-caught-pets-v1`). Tất Trung Cầu (100%) và Đại sư cầu (hệ số 200%) cũng dùng được khi đang có.
- **Kinh nghiệm và lên cấp** (công thức VQSV6 gốc): thắng trận (cả trận cốt truyện lẫn hoang dã) thì mỗi pet đã ra trận chia nhau kinh nghiệm = (2 × cấp đối thủ² + 50) × hệ số hạng đối thủ (10/11/12/13/15) / 10 + 400, chia số pet tham gia rồi nhân thưởng nhóm (10/12/13/14/15/16 phần 10) và hệ số chênh cấp (thấp hơn đối thủ 105%, bằng hoặc hơn 1 cấp 100%, hơn 2–5 cấp 80/60/40/20%, hơn từ 6 cấp 5%). Lên cấp L cần 15 × L² − 200 KN, **tối đa cấp 70** (game gốc VQSV6 dừng ở 50; game hợp nhất nâng lên 70 và dùng tiếp cùng công thức KN và chỉ số cho cấp 51–70); chỉ số tính lại theo cấp mới. Thanh vàng dưới HP là KN; bảng Sủng vật hiện cấp và KN. Tiến độ lưu riêng trên trình duyệt (`vqsv-unified-pet-progress-v1`) và không bị xóa khi **Chơi lại**. **Học kỹ năng** (luật VQSV6 gốc): pet có tối đa min(5, cấp/10 + 1) ô kỹ năng (cấp 5: 1, cấp 10: 2, 20: 3, 30: 4, 40 trở lên: 5). Sau khi thắng trận, pet nào đã ra trận mà còn ô trống thì hiện bảng **"Có thể học tập kỹ năng mới"** với các kỹ năng cùng hệ đã mở khóa theo bậc cấp (5/10/20/30/40) và đường học riêng của từng loài; chọn một kỹ năng hoặc **Để sau** (lần thắng sau sẽ hỏi lại). Kỹ năng đã học lưu trong tiến độ pet và hiện thành các nút thêm trong bảng **Chiến đấu**. Pet hoang dã mang kỹ năng ngẫu nhiên theo đúng luật gốc (chỉ kỹ năng đầu tới cấp 9, sau đó bốc ngẫu nhiên đủ số ô) và đối thủ chọn ngẫu nhiên kỹ năng gây sát thương còn lượt; bắt được thì pet giữ các kỹ năng đó. **Quên và thay kỹ năng** (thiết kế của game hợp nhất: mã nguồn VQSV6 chỉ thêm kỹ năng chứ không có cách bỏ): trong Sổ pet, chi tiết của pet bạn đang có hiện khung **Kỹ năng** cho từng cá thể (pet của Kael, pet trong đội, pet đã bắt). Nút **Quên** bỏ một kỹ năng (miễn phí, pet luôn giữ ít nhất một kỹ năng); danh sách **Có thể học** gồm các kỹ năng cùng hệ đã mở khóa theo cấp và đường học của loài (đúng luật học ở trên), còn ô trống thì bấm **Học**, đã đủ ô thì bấm **Thay…** rồi chọn kỹ năng bị thay (kỹ năng mới giữ nguyên vị trí ô). Không đổi được kỹ năng trong trận. Kỹ năng đã quên có thể học lại bất cứ lúc nào khi còn đủ điều kiện.
- **Hiệu ứng kỹ năng** (bảng 6 và 7 của `db.mid`): kỹ năng có cột 6 = 1 tự tạo trạng thái có lợi (▲, bảng 6), cột 6 = 2 gây trạng thái bất lợi lên đối thủ (▼, bảng 7); số hiệp lấy từ bảng; mỗi bên tối đa 3 trạng thái; nhãn hiện cạnh bảng HP kèm số hiệp còn lại. Chỉ 6 kỹ năng có tỉ lệ kích hoạt (Diễm kích 10%, Liệt diễm phong bạo 20%, hai Bão cát 25%, Lôi thiểm và Chùm sấm sét 10%); các kỹ năng còn lại luôn gây hiệu ứng. Theo code gốc: Gieo Hạt (đốt cháy) trừ sát thương gốc / tham số mỗi hiệp; Thực Loại nổ sát thương gốc × tham số% ở hiệp cuối; Chậm Chạp/Phòng Ngự giảm Tốc/Thủ theo %; Nhụt Chí giảm sát thương gây ra; Súc Lực, Phá Phủ, Kinh Cức, Phòng ngự, Linh Xảo, Hỏa Thạch, Man Lực đổi Thủ/Tốc/Công; Điện áp +30% sát thương và tốn gấp đôi lượt kỹ năng; Khôi phục và Thạch Hóa hồi máu mỗi hiệp; Thạch Hóa/Thạch Phu giải trạng thái bất lợi (Thạch Phu còn miễn nhiễm); Vô hình, Kinh Cức dội sát thương; Kiên nhẫn 50% giảm nửa sát thương nhận; Thâu Thủ cướp trạng thái có lợi; Gia Tốc đánh 2 lần. Quấn Quanh cấm dùng đạo cụ, đổi pet và chạy trốn; Hỗn Loạn cấm đổi pet (theo mô tả). Pet hoang dã có trạng thái Phá Phủ / Kinh Cức / Man Lực dễ bắt hơn ×1,1 / ×1,2 / ×1,2 như code gốc. **Thử nghiệm (không tìm thấy đoạn xử lý riêng trong mã dịch ngược, làm theo mô tả):** Mê Muội mất lượt, Tê Liệt 50% mất lượt, Quỷ Mị dội ngược 40% đòn tấn công của pet bị ảnh hưởng. Kỹ năng không gây sát thương giờ dùng được để tạo hiệu ứng.
- `P` hoặc nút **Mở sổ pet**: xem và tìm pet hai game; chọn pet VQSV5 để xem các chuỗi hoạt ảnh. Đóng bằng nút × hoặc Escape.
- **Sổ kỹ năng gốc**: tra 49 tên kỹ năng VQSV5 và 70 kỹ năng VQSV6; chọn một trong 32 chuỗi hiệu ứng VQSV5 đã ghép khung để xem. Liên hệ giữa kỹ năng và hiệu ứng chưa xác minh, chưa đưa vào trận.
- Trong sổ, chọn pet rồi bấm **Gom vào bộ sưu tập**; dùng bộ lọc **Pet đã gom** để xem chung pet của hai game. Bấm lại để bỏ. Danh sách lưu trong trình duyệt hiện tại nếu localStorage khả dụng; không đồng bộ giữa máy hoặc trình duyệt.
- **Đội hình thử:** trong sổ, chọn pet rồi bấm **Đưa vào đội** (tối đa 2 pet dự bị; Lôi Miêu luôn là pet chính, nên hình tham khảo Điện Miêu không thêm được). Đội được chụp lại khi bắt đầu trận. Trong trận, mở **Sủng vật** để đổi pet: đổi chủ động mất một lượt, còn khi pet đang ra trận kiệt sức thì bắt buộc chọn pet khác và không mất lượt; chỉ thua khi cả đội kiệt sức. Pet VQSV6 dự bị dùng chỉ số và kỹ năng hệ đầu tiên theo dữ liệu gốc ở cấp 5; pet VQSV5 dùng chỉ số giải mã từ `vqsv5.jar` (xem mục Chỉ số VQSV5). Sổ pet hiện bảng HP/Công/Thủ/Tốc ở cấp 5 và 30 cùng danh sách kỹ năng hệ cho từng pet VQSV6. Pet VQSV6 hiện bằng ảnh gốc; pet VQSV5 chạy chuỗi hoạt ảnh nguồn thứ nhất khi đứng và chuỗi thứ hai khi tấn công. Việc chuỗi nào là đứng hay đánh chưa được xác minh, đây chỉ là lựa chọn trình bày. Danh sách đội lưu riêng trên trình duyệt (`vqsv-unified-pet-team-v1`).
- **Vật phẩm mang theo** (bảng 3 dòng 0–11 của `db.mid`, luật từ `game/i.java` và `game/a.java`): mỗi pet cầm tối đa một món. Mua ở Thương điếm (5 huy hiệu mỗi món; huy hiệu thử nghiệm: 2 đầu game, +1 nhiệm vụ Thảo dược, +1 mỗi lần đến một vùng pet hoang dã mới lần đầu), rồi mở **Sổ pet**, chọn pet bạn có và dùng khung **Vật phẩm mang theo** để **Mang / Đổi / Gỡ** (không đổi được trong trận; món đang đeo không còn nằm trong túi; tiến hóa giữ nguyên món đang cầm). 12 món: *Mạn Đà La Thạch* (HP ≤ 30% thì Công +100%), *Hồng Sắc Hải Loa* (Công +10%), *Quy Xác Toái Phiến* (Thủ +15%), *Ô Nha Uế* (trạng thái bất lợi lên pet chỉ trúng với 80% xác suất), *Viễn Cổ Long Cốt* (bạo kích +10 điểm), *Mật Phong Sào* (kinh nghiệm +20%), *Ký Cư Giải Xác* (pet còn sống đang mang món này nhận phần kinh nghiệm dù không ra trận), *Linh Trùng Thi Hài* (luôn ra đòn trước), *Hấp Huyết Đằng Mạn* (đòn gây sát thương có 10% hồi 20% lượng sát thương), *Cá Thờn Bơn* (đòn luôn trúng), *Cảm Lãm Chi Diệp* (đối thủ không bị đánh xuống dưới 10 HP, dùng khi bắt pet), *Sủng vật lôi đạt* (tỉ lệ bắt +20%). Bản gốc nhân dấu hiệu "luôn trúng" (-1) với hệ số của Ô Nha Uế nên gần như chặn mọi trạng thái; prototype hiểu "luôn trúng" là 100% rồi nhân 80%.
- **Đánh trượt theo chênh lệch cấp** (luật gốc trong `game/a.java`, mới thêm): tỉ lệ trượt = 2 × (cấp đối thủ − cấp người đánh)%, từ 0 đến 20%. Trạng thái **Mục** coi người đánh thấp hơn một số cấp bằng tham số của kỹ năng (thay cho cách tính thử nghiệm cũ); Cá Thờn Bơn bỏ tỉ lệ trượt. Vì vậy đánh pet hoang dã cao cấp hơn nhiều sẽ trượt tới 20%.
- **Chỉ số pet VQSV5** (giải mã từ `vqsv5.jar`, `tools/decode_vqsv5_pet_stats.py`): mỗi pet VQSV5 có 15 số trong `data.d`; HP, kỹ năng (SP), linh hoạt, công và thủ tính theo cấp bằng `gốc + hệ số × cấp / 10`, hệ (Thực Vật, Kim Loại, Phi Hành, Mãnh Thú, Hải Dương; 2 pet boss không có hệ), hạng 1–5, và kỹ năng hệ tự học ở cấp 7/14/21/28/35. Sổ pet hiện hệ, hạng, bảng chỉ số gốc cấp 5/30 và các mốc học kỹ năng cho từng pet VQSV5. **Trong trận** pet VQSV5 ra trận với chỉ số quy đổi: hai game dùng công thức sát thương khác nhau (VQSV5 nhân tỉ lệ, VQSV6 lấy Công trừ Thủ) và quy mô lệch nhau theo cấp, nên game hợp nhất nhân mỗi chỉ số của pet VQSV5 với tỉ lệ (trung bình pet VQSV6) / (trung bình pet VQSV5) ở cùng cấp. Pet VQSV5 trung bình ngang pet VQSV6 trung bình, pet mạnh yếu hơn trong VQSV5 vẫn mạnh yếu hơn như vậy. Kỹ năng hệ có sức mạnh = sức mạnh gốc / 15 (kỹ năng bậc 1 = 100%), số lần dùng mỗi kỹ năng = SP / chi phí gốc; đòn thường dùng sức mạnh của "Công Kích thông dụng" (53%). Pet VQSV5 nhận kinh nghiệm theo cùng công thức và lên cấp thì tự học kỹ năng mới. **Thử nghiệm:** phép quy đổi, số lần dùng và kinh nghiệm dùng công thức VQSV6. **Chưa làm:** đặc tính ngẫu nhiên (13 loại, ví dụ ±15% HP), kỹ năng đặc biệt số 26–48, và lợi thế hệ giữa hệ VQSV5 và hệ VQSV6.
- **Tiến hóa và dị hóa pet VQSV5** (giải mã từ `ak.E()`, `ak.d()`, `ak.b()` của `vqsv5.jar`): 46 pet có dạng tiếp theo, 4 pet có thêm dạng dị hóa. Điều kiện: cấp tối thiểu theo **hạng** (hạng 1 cần cấp 15, hạng 2 cần 25, hạng 3 cần 30, hạng 4 trở lên không yêu cầu cấp); đủ **nguyên liệu** ghi trong dữ liệu của loài (1–2 loại, 1–3 cái mỗi loại, bị tiêu hao); và cá thể còn **lượt tiến hóa** (mỗi cá thể có bộ đếm, khởi đầu bằng số trong dữ liệu loài, giảm 1 mỗi lần tiến hóa và về 0 sau khi dị hóa). Dị hóa cần đúng nguyên liệu như tiến hóa thường cộng thêm 1 **Dị hoá tài liệu** và thay thế tiến hóa thường. Pet giữ cấp và kinh nghiệm, nhận chỉ số của dạng mới (hồi đầy HP). Dùng nút **Tiến hóa / Dị hóa** trong Sổ pet như pet VQSV6. Nguyên liệu 16–25 mua ở **Thương điếm** bằng kim tiền (500 hoặc 1000, đúng `shop.d` của VQSV5, ghi nhãn "VQSV5"); nguyên liệu 26–30 và Dị hoá tài liệu **không bán** trong game gốc nên prototype cho rơi thử nghiệm khi thắng hoặc bắt pet hoang dã cấp cao (5% mỗi món từ cấp 25, Dị hoá tài liệu 4% từ cấp 15). Các phép kiểm tra chéo của bộ giải mã: đích tiến hóa trùng đúng với các pet mà bảng nguồn gốc ghi "tiến hóa", đích dị hóa trùng với "dị hóa", cùng hệ và hạng +1, và số lần tiến hóa khởi đầu của cả 52 dạng gốc bằng độ dài chuỗi. Mỗi loài vẫn chỉ giữ một cá thể nên không tiến hóa được nếu đã có sẵn dạng đích.
- **Tiến hóa và dị hóa** (luật VQSV6 gốc, 38 pet): chọn pet trong sổ, nếu bạn có cá thể đó (pet của Kael, pet trong đội hoặc pet đã bắt) thì chi tiết hiện nút **Tiến hóa** / **Dị hóa**. Điều kiện: cấp đủ theo loại dạng đích (tiến hóa 12 hoặc 30, dị hóa 5) và đủ nguyên liệu (Tinh Nguyên Thạch, Thiên Giới Tinh Thạch, Thiên Địa Thần Thạch, Hồn Tinh Thạch, Quỷ Thần Tinh Thạch) theo từng pet; nguyên liệu bị tiêu hao. Pet sang dạng mới giữ cấp, kinh nghiệm và kỹ năng, chỉ số tính lại theo dạng mới, hạng giữ nguyên nếu không thấp hơn hạng mặc định của dạng mới. Lôi Miêu vẫn giữ tên Lôi Miêu nhưng dùng hình và chỉ số của dạng mới (Điện Miêu → Thiểm Điện Miêu theo dữ liệu nguồn; dạng Cực Lôi Hoàng Hổ của truyện chưa có liên kết nguồn nên chưa dùng). Bộ lọc **Nguyên liệu gốc** vẫn dùng để tra cứu pet theo nguyên liệu; nút **Xem** mở pet ở dạng tiếp theo. Không tiến hóa được trong trận. **Giới hạn prototype:** mỗi loài chỉ giữ một cá thể nên nếu đã có sẵn dạng đích thì phải xử lý trước. **Thử nghiệm:** game gốc không có đoạn nào nói cách kiếm nguyên liệu, nên prototype tặng sẵn 2 Tinh Nguyên Thạch + 1 Hồn Tinh Thạch và cho nguyên liệu rơi khi thắng hoặc bắt pet hoang dã (Tinh Nguyên Thạch 25%, Hồn Tinh Thạch 12%, cao cấp hơn nếu pet hoang dã cấp cao). Túi nguyên liệu lưu trên trình duyệt (`vqsv-unified-stones-v1`) và hiện ở cột bên phải.
- `R` hoặc nút bên cạnh: chơi lại.
- **Lưu / Tải lại**: lưu vị trí Kael, hướng nhìn và mốc truyện trên trình duyệt hiện tại; lưu khi đang ở map, đã đứng yên hoặc sau cảnh kết. Không lưu giữa trận hay giữa hội thoại. **Chơi lại** không xóa bản lưu; lưu lần tiếp theo sẽ thay bản lưu trước. Bản lưu không tự đồng bộ sang máy hoặc trình duyệt khác; bộ sưu tập pet được lưu riêng.

## Nội dung có trong bản thử

- Ba map 24×18 ô được dựng từ tile VQSV5, kết hợp tileset làng `00` và địa hình `04`; mặt nước và cầu là đồ họa thử dựng bằng canvas. Các vùng chưa có nhiệm vụ truyện riêng.
- Hai căn nhà được ghép từ hai nửa sprite lật gương như cách map VQSV5 đặt chúng; bỏ các mảnh vách đá đơn lẻ ở viền khiến cảnh trông như texture bị cắt.
- Khu mở đầu được bố trí lại thành làng biên giới trước biến cố: hai ngôi nhà có sân và đường ra trục làng, lối riêng tới các NPC, rừng thông và cây lá rộng; thêm bảng tin và cây cảnh từ VQSV5. Mái nhà và tán cây được ghép đủ các mảnh nguồn. Đây là diễn giải từ kịch bản, chưa thể hiện cảnh làng bị thiêu rụi sau đó. Bản xem tĩnh ở `asset_review/opening_map_redesign.png`.
- NPC, đoạn thoại mở đầu, điểm dấu vết Trăng Huyết, trận pet lượt đơn giản và cảnh la bàn thức tỉnh.
- Làng có thêm người chăm sóc pet, dân làng và người đi rừng; đứng cạnh và nhấn E để nói chuyện. Hình được chọn từ VQSV5, còn vai trò và lời thoại là nội dung mới của prototype; chưa xác nhận danh tính NPC gốc.
- Trận mở đầu có nền bìa rừng, bảng máu có số, bộ đếm lượt và hàng sáu nhóm lệnh bên dưới theo bố cục giao diện mẫu. Chỉ số và sức mạnh hiện vẫn là giá trị thử nghiệm.
- Bảng mục tiêu đổi theo hội thoại, trận và kết quả; phím tương tác và phím đánh không tự lặp khi giữ để tránh bỏ qua hội thoại hoặc thực hiện nhiều lượt ngoài ý muốn.
- Tắt nội suy ảnh để giữ cạnh pixel; map vẽ nền, đồ vật sau/trước nhân vật theo thứ tự chiều sâu và tránh cắt tile từ atlas không được nạp.
- Kael dùng bộ sprite pixel mới dựa trên hình cũ và tham chiếu NPC VQSV5, với tóc đen, áo xanh, khăn đỏ và la bàn; có 4 hướng và 4 tư thế mỗi hướng. Nhân vật đi liên tục khoảng 220 ms/ô, nhịp chân theo quãng đường. Các khung được cắt và căn riêng theo bộ ảnh mới; NPC/pet có nhịp idle.
- Hình dùng thử được sao chép vào `assets/`; nguồn và độ chắc chắn ghi tại `assets/provenance.json`. `data/pet_roster_v1.json` chứa 102 dòng VQSV5 và 100 dòng VQSV6 với ID riêng theo từng game.
- Sổ pet hiển thị đủ 102 dòng VQSV5 có tên và hình từ dữ liệu gốc, cùng 100 dòng VQSV6. Có thể xem ba chuỗi hoạt ảnh cho từng pet VQSV5 với tốc độ gần đúng và gom pet vào bộ sưu tập thử, đưa tối đa 2 pet vào đội dự bị và đổi pet trong trận. Chưa có bắt pet, tiến hóa hay chỉ số theo dữ liệu gốc.

Để dựng lại tài nguyên sổ pet từ nguồn: lần lượt chạy `python tools/decode_vqsv5_pet_tables.py`, `python tools/decode_vqsv5_npc2_animations.py`, `python tools/build_pet_roster_catalog.py`, `python tools/build_pet_gallery_assets.py`, rồi `python tools/build_prototype_bootstrap.py` từ thư mục gốc dự án.

## Ranh giới hiện tại

Đây là prototype tương tác để duyệt vòng lặp chơi. Sprite Kael là hình mới dựng theo mô tả bằng chữ trong kịch bản; không phải sprite gốc của hai game. Kael có chu kỳ đi bộ 4 khung theo 4 hướng; chưa có hoạt ảnh chiến đấu riêng. NPC/pet và một số va chạm vẫn là ứng viên hoặc placeholder. Pet tham khảo đã đổi sang `img_540` (Điện Miêu, DB 78); `img_538` là Điện Nhãn Miêu và không còn được dùng làm ảnh Lôi Miêu. Liên kết Lôi Miêu ↔ Điện Miêu vẫn là lựa chọn thiết kế của prototype, chưa phải bí danh canon từ dữ liệu gốc. Map chưa có âm thanh hoặc bản dịch hoàn chỉnh; hệ lưu tiến trình hiện chỉ phục vụ lát mở đầu. Hình dáng cụ thể của Kael là diễn giải mỹ thuật để dùng trong prototype.
- **Đặc tính ngẫu nhiên VQSV5**: mỗi pet VQSV5 được tung đặc tính một lần (2 ô, xem `PET_ROSTER_CATALOG.md`), lưu cùng tiến trình và giữ nguyên khi tiến hóa. Trong trận áp dụng: HP ±15% (làm tròn về 0), hồi 6% HP mỗi hiệp, +20% sát thương Gieo Hạt/Thực Loại, 20% giảm nửa sát thương gây ra, 20% nhân 1,5 sát thương gây ra (diễn giải, giá trị gốc −20), ±20% tỉ lệ dính Quấn Quanh/Tê Liệt/Gieo Hạt. Đặc tính 5/6 (Thạch Hóa) chỉ hiển thị vì chưa có trạng thái tương ứng. Pet VQSV5 hiện sinh đặc tính ngẫu nhiên khi vào đội; chế độ cố định/may mắn đã có hàm nhưng chưa nối vào nguồn pet (trứng, nhiệm vụ). Sổ pet có mục Đặc tính.
- **Hiệu ứng kỹ năng bằng pixelfx** ([patch-gg/pixelfx](https://github.com/patch-gg/pixelfx), MIT): thư viện được đưa vào `vendor/pixelfx` (chỉ phần lõi và các hiệu ứng đang dùng; giấy phép và `NOTICE.md` đi kèm, phiên bản ghi ở `vendor/pixelfx/VERSION`). Cập nhật bằng `tools/vendor_pixelfx.sh <thư mục pixelfx>`, danh sách hiệu ứng ở `tools/pixelfx_used.txt`. `skill_fx.js` vẽ trên khung 240×180 phóng 2 lần để điểm ảnh khớp với sprite. Đòn đánh chọn theo hệ (Hỏa, Mộc, Thổ, Thủy, Điện, Quỷ, Phong của VQSV6; Thực Vật, Kim Loại, Phi Hành, Mãnh Thú, Hải Dương của VQSV5), đòn thường dùng nhát chém thường, chí mạng thêm tia sáng. Trạng thái có lợi/bất lợi, thủ thế và hồi máu mỗi hiệp có hiệu ứng riêng, ôm theo hình dáng thật của pet. Việc ghép hệ với hiệu ứng là lựa chọn thiết kế của prototype, không phải dữ liệu gốc. Nếu module không tải được (ví dụ mở bằng file://), game dùng lại hiệu ứng cũ. Trang `fx_preview.html` cho xem thử từng hiệu ứng.
- **Kỹ năng đặc biệt VQSV5** (xem `PET_ROSTER_CATALOG.md`): pet VQSV5 có 2 ô kỹ năng đặc biệt, học từ sách mua ở Thương điếm bằng huy hiệu (giá thử lấy theo giá huy chương gốc 1–9; mua và học đều đòi hạng huấn luyện sư của sách, xem bên dưới) và quản lý trong sổ pet. Chiêu 26–30 hiện trong menu kỹ năng; bị động 31–48 tác dụng trong trận (kinh nghiệm ×2/×3, hồi sinh, giảm sát thương, chạy trốn, địa hình…). Kỹ năng hệ VQSV5 giờ gây trạng thái thật (Thạch Hóa, Tê Liệt, Mị Hoặc, Giam Cầm, Hủ Thực, Phong Bế) với đặc tính 5–8, 11–12 chỉnh tỉ lệ dính, có nhãn trạng thái và hiệu ứng pixelfx. Giá trị thử: độ trung thành chỉ tồn tại trong một trận (100, hoặc 120 với hạng trên 3; −25% bởi Tàn quân; dưới 41 thì 25% không nghe lệnh); địa hình lấy từ dữ liệu thật (xem mục Địa hình bên dưới) và pet khác hệ gây −20% trừ khi có kỹ năng 43–47; Tử Vong Chi Xúc dùng tổng lượt còn lại quy ra SP; kỹ năng 34 rút 1 lượt kỹ năng của đối thủ; hồi 27/28 nhân tỉ lệ hiệu chỉnh; kỹ năng 41 chưa có tác dụng vì game chưa có chuyện đối thủ bỏ chạy khi bắt hụt; kỹ năng 39 xét mọi pet VQSV5 trong đội vì pet dẫn đầu của game là VQSV6.
- **Bảo rương (nguồn sách kỹ năng)**: thắng trận có 50% nhận Bảo rương, đúng như nguồn; chìa khóa mua ở Thương điếm giá 50 kim. Trong Túi bấm "Mở bảo rương" (ngoài trận) để tung bậc và bảng phần thưởng của `box.d`: sách kỹ năng ở bậc 2, nguyên liệu tiến hóa 26–28, 30, 31 ở bậc 3, Dị hoá tài liệu ở bậc 1. Nguyên liệu 26–28, 30 và 34 không còn rơi sau trận (bản cũ là giá trị thử); chỉ Cổ kình chi tâm (29), không nằm trong bảng nào, giữ lượt rơi thử. Đạo cụ trong bảng 0 và 1 quy đổi thử sang đạo cụ gần nhất của túi (đồ uống và bánh thành Bánh Sandwich, Năng lượng thạch, Hoàng kim đường thành Chocolate, Cao cấp cầu, Tránh quái hoàn thành Tị quái hàn) hoặc kim tiền khi không có (Hưng phấn cô 200, Mật tâm đường 500, vì game chưa có độ trung thành lâu dài).
- **Hạng huấn luyện sư, huy hiệu và Dị hoá tài liệu** (giải mã từ `as.b(boolean)`, lệnh sự kiện 32 và `data/event`): hạng khởi đầu là 1, lên 2, 3, 4, 5 khi đã bắt được 5, 10, 15, 30 loài khác nhau (tự động trong prototype; game gốc đổi ở quầy đăng ký). Mỗi loài mới đổi được 1 huy hiệu, bấm "Đổi huy hiệu" trong Túi. Sách kỹ năng đòi hạng huấn luyện sư của riêng nó, cả khi mua lẫn khi học. Dị hoá tài liệu bán ở Thương điếm giá 1500 kim và chỉ giữ được một cái, theo kịch bản ở Trung Tâm Thương Mại. Số loài tính theo bộ sưu tập bắt trong prototype (cả VQSV5 lẫn VQSV6), giá trị thử.
- **Kịch bản sự kiện VQSV5**: `tools/decode_vqsv5_events.py` đọc `data/event` (386 kịch bản của 68 bản đồ, 385 đọc đúng đến hết) và ghi `data/vqsv5_event_rewards.json`, `asset_review/vqsv5_event_rewards.csv`: vật phẩm, kim tiền, pet tặng, trận trùm, nhiệm vụ. Chưa đưa vào game vì cốt truyện VQSV5 chưa có trong prototype.
- **Rương trên bản đồ**: 8 bản đồ khám phá (rừng, suối, hồ, đồng cỏ, cao nguyên, hẻm núi, dải mây, bãi tro) có 3–4 rương mỗi bản đồ, tổng 31. Nội dung là nội dung thật của rương VQSV5 (kim tiền, huy hiệu hoặc vật phẩm), lấy từ khu vực giống nhất (ví dụ hẻm núi lấy rương Kim Loại Di Tích, hồ lấy rương Bắc Hải; bãi tro không có khu riêng nên lấy rương còn dư). Vị trí do `tools/design_map_chests.py` đặt (chạy sau các tool `design_*`), tránh đường đi, điểm xuất phát và cổng, rương nào cũng có ô trống bên cạnh tới được. Đứng cạnh rương nhấn E để mở; rương cứng, mở một lần, lưu trên trình duyệt. Vật phẩm VQSV5 vào kho nguyên liệu hoặc quy đổi gần nhất sang đạo cụ trong túi (Hưng phấn cô 200 kim, Mật tâm đường 500 kim mỗi cái), kho đầy vẫn mở được nhưng không nhận thêm. Làng khởi đầu không có rương. Nguyên liệu Cổ kình chi tâm (29), nằm trong một rương ở khu làm việc Học Viện Cầu Vồng của bản gốc, chưa có rương tương ứng nên vẫn rơi thử sau trận.
- **Địa hình thật** (`tools/decode_vqsv5_terrain.py` đọc `data/map/m<N>.d`, 68 bản đồ đọc hết từng byte; ghi `data/vqsv5_map_terrain.json` và `asset_review/vqsv5_map_terrain.csv`): mỗi bản đồ VQSV5 có một loại sân đấu (0 Thực Vật … 4 Hải Dương), mức cấp dã thú và danh sách pet hoang dã VQSV5 (35 bản đồ). Kết quả đáng chú ý: Rừng rậm phía tây là sân Mãnh Thú, Cao nguyên phía đông là Thực Vật (khu hoa viên), Kim Loại Di Tích và Nam Chi Phế Tích là Kim Loại, Bầu trời phương bắc là Phi Hành, vùng Bắc Hải là Hải Dương, còn Rừng rậm u ám có đủ năm loại ở năm khu. Mỗi bản đồ khám phá mang trường `terrain5.types` theo khu nguồn đã ghép (cùng cách ghép với rương, vẫn là lựa chọn thử): rừng Mãnh Thú, suối nhiều loại (mỗi trận bốc một loại), hồ Hải Dương, đồng cỏ Thực Vật, cao nguyên và hẻm núi Kim Loại, dải mây Phi Hành, bãi tro Thực Vật (mượn Rừng rậm thần bí). Làng khởi đầu không có sân đấu nên không phạt. Bảng pet hoang dã này đã được dùng, xem mục Pet VQSV5 hoang dã.
- **Pet VQSV5 hoang dã**: trên đất liền của 8 bản đồ khám phá, khoảng nửa số lần chạm trán (giá trị thử `V5_WILD_SHARE` = 50%) ra pet VQSV5 thay vì pet VQSV6; nửa kia và cầu/đường thủy giữ như cũ. Pet, cấp và hệ lấy từ bảng thật của các khu nguồn mà bản đồ đại diện (`terrain5.source_maps`; ví dụ đồng cỏ lấy bốn khu Cao nguyên phía đông, cấp 8–13): bốc một khu, cấp = cấp thấp nhất + ngẫu nhiên trong độ rộng, pet trong danh sách của khu (không khu nào có mục hiếm thứ tư nên nhánh 5% chưa kích hoạt). Pet mới sinh đặc tính ngẫu nhiên (hoặc may mắn nếu đội có pet biết kỹ năng 39), dùng chỉ số và kỹ năng hệ VQSV5 đã quy đổi, kỹ năng hệ của nó gây trạng thái thật lên pet của anh, sân đấu cũng phạt nó −20% nếu khác hệ. Bắt được thì cá thể giữ đặc tính, loài tính vào hạng huấn luyện sư và huy hiệu, hoạt ảnh của nó chạy trong trận. Thêm luật hệ khắc của VQSV5: +30% khi hệ của người đánh khắc hệ người chịu (hoặc người đánh không thuộc hệ nào), chỉ áp dụng giữa hai pet VQSV5. Cấp pet VQSV5 hoang dã thấp hơn pet VQSV6 cùng vùng (cấp nguồn 8–35).
- **Nền trận đấu theo vùng**: dùng 5 ảnh nền gốc của VQSV5 (`data/battle/0..4.png`, chép vào `assets/battle/vqsv5_field_*.png`), mỗi loại sân một ảnh: Thực Vật là vườn nấm lá, Kim Loại là đại sảnh di tích, Phi Hành là bầu trời mây, Mãnh Thú là đồng cỏ, Hải Dương là biển sóng. Như `ak.ak()` của nguồn, nửa trên là sân phía đối thủ, nửa dưới (sau dải tối, nằm dưới bảng máu và hộp thoại) là sân phía mình, lật ngang. Sân mỗi phía là loại sân của bản đồ, trừ khi pet VQSV5 đang ra trận biết kỹ năng địa hình đúng hệ của nó (43–47): khi đó phía mình đổi thành sân của hệ nó, cùng lúc không còn bị phạt −20%. Bản gốc xếp hai pet ở hai nửa ảnh; prototype giữ bố cục cũ nên cả hai pet đứng trên ảnh trên, có bóng đổ. Suối Sấm có nhiều loại sân nên mỗi trận một ảnh. Làng và trận truyện (không có loại sân) giữ nền vẽ tay cũ.
- **Bốn bản đồ mới cho pet VQSV6** (`tools/design_extra_wild_maps.py`, chạy sau `design_wild_maps.py` và trước `design_map_chests.py`): 45/100 pet VQSV6 không có trong vùng hoang dã nào của dữ liệu gốc; 26 con là dạng tiến hóa (có qua tiến hóa), 19 dạng cơ bản còn lại trước đây không gặp được. Bốn bản đồ nhánh chứa chúng, cùng vùng gốc duy nhất chưa dùng (1-6):
  - Rừng Thông Gió (lối ▲ phía bắc Rừng Rậm Phong Lôi, cấp 7–10): vùng gốc 1-6 + Đậu ưng, Uy Qua Đệ Đệ, Nhiên Dực Bức, Dị Tiểu Hiệp.
  - Đầm Mực Đen (lối ▼ phía nam Hồ Sương Mù, cấp 15–19): đầm nước có cầu; Mực Ma và Thủy Thượng Phiêu chỉ gặp trên cầu, Hư không hành giả và Lục Hành Điểu trên bờ.
  - Đồng Bằng Sấm Sét (lối ▲ phía bắc Hẻm Núi Đá, cấp 26–30): Chuột Điện, Thảo Diệp Cô, Viêm Ma, Nhiên Liệp Sư.
  - Thánh Địa Thần Thú (lối ▶ phía đông Dải Mây Cao, cấp 46–50): bảy thần thú hạng 5, mỗi hệ một con (Hỏa Phượng Hoàng, Linh Quang Lộc, Chiến Thần Đà, Lôi Kỳ Lân, Minh vương Long, Tinh Vân Hạc; Tương Quân Giải dưới nước, gặp trên cầu).
  Bảng pet của các bản đồ này nằm trong trường `customWildAreas` và là thiết kế của prototype, không phải dữ liệu gốc (cấp theo bản đồ nhánh từ đó, hạng bắt đầu từ hạng mặc định của loài). Mỗi bản đồ có một loại sân để có nền trận VQSV5 (rừng thông: Thực Vật, đầm: Hải Dương, đồng bằng sấm: Mãnh Thú, thánh địa: Phi Hành), không có pet VQSV5 hoang dã và chưa có rương. Lối mới hiện mũi tên ▲/▼/▶ và một vệt đường đất trên bản đồ gốc.
- **Nền trận đấu theo hệ VQSV6**: VQSV6 không có ảnh nền trận (tệp `backPic.mid` chỉ lưu một màu phẳng cho mỗi vùng), nên 7 nền này là tranh mới của prototype, vẽ bằng `tools/make_vqsv6_battle_backdrops.py` theo khổ và phong cách của nền VQSV5 (240×108, phóng 2 lần): Hỏa núi lửa và sông dung nham, Mộc rừng thông có nắng xuyên, Thổ hẻm núi sa mạc có xương rồng, Thủy hồ sương có lá sen, Điện đồng cỏ giông bão (chớp lóe mỗi vài giây), Quỷ đầm ma dưới trăng tím, Phong núi tuyết trên biển mây. Trận với pet VQSV6 dùng nền của vùng, chọn theo hệ chiếm nhiều nhất trong pet hoang dã ở đó, hoặc theo chủ đề khi số lượng sát nhau: rừng Phong Lôi và đồng cỏ Mộc, suối Sấm và đồng bằng sấm Điện, hồ Thủy, cao nguyên và hẻm núi Thổ, bãi tro Hỏa, dải mây và rừng thông gió Phong, đầm mực Quỷ. Thánh Địa Thần Thú lấy theo hệ của thần thú gặp được; trận truyện ở làng dùng nền Quỷ. Trận với pet VQSV5 hoang dã vẫn dùng nền gốc VQSV5. Pet VQSV5 biết kỹ năng địa hình đúng hệ vẫn đổi nửa sân phía mình sang nền VQSV5 của hệ nó.
- **Hiệu ứng kỹ năng, bản đầy đủ** (`skill_fx.js`, xem thử ở `fx_preview.html`): mỗi hệ có 3 mức theo sức mạnh của chiêu (kỹ năng VQSV6: uy lực dưới 100% là mức 1, 100–149% mức 2, từ 150% mức 3; kỹ năng hệ VQSV5: bậc 1–2 mức 1, bậc 3–4 mức 2, bậc 5 mức 3; đòn thường mức 1). Mức cao chồng thêm hiệu ứng lớn: Hỏa thêm lốc lửa và vòng lửa rộng, Mộc gai đất và bão lá, Thổ động đất và cú dậm đất, Thủy sóng đổ, Điện sét giáng, Quỷ xúc tu bóng tối, Phong bão tuyết và nhát xoáy, Kim Loại búa giáng, Mãnh Thú vuốt đôi và móc lên. Đủ 15/15 trạng thái có lợi (thêm Súc Lực, Phá Phủ, Kinh Cức, Vô hình, Điện áp, Hỏa Thạch, Thâu Thủ, Thạch Phu), 11/11 trạng thái bất lợi, 6/6 trạng thái VQSV5 (Phong Bế nay là vòng ấn). Hiệu ứng riêng cho: đánh trượt (khói tan), phản đòn (chặn đỡ), hút máu, hồi sinh (cột sáng), bắt pet (bóng chạm, lông vũ khi bắt được, khói khi thoát), Trị liệu sơ cấp và chuyên nghiệp, Tàn quân (ấn nguyền trên đối thủ), Tử Vong Chi Xúc (sụp nổ rồi đòn mạnh nhất của hệ). Các hiệu ứng ôm theo hình pet thật. Việc chọn hiệu ứng cho từng hệ, mức và sự kiện là thiết kế của prototype.
- **Âm thanh hiệu ứng** (`sound_fx.js`): hai game gốc chỉ có nhạc MIDI (`vqsv5/music/*.mid`, `vqsv6/music/data/sound/0.mid`) và không có tệp âm thanh hiệu ứng, nên toàn bộ âm thanh được tổng hợp bằng Web Audio ngay trong trình duyệt (kiểu chiptune, không cần tệp). Mỗi hệ có tiếng riêng và mạnh dần theo 3 mức của chiêu: Hỏa lửa bùng, Mộc lá xào, Thổ đất rung, Thủy nước bắn, Điện tiếng điện giật, Quỷ tiếng trầm rền, Phong gió rít, Kim Loại tiếng kim loại ngân, Mãnh Thú vuốt cào. Có thêm tiếng cho đòn thường, chí mạng, trạng thái có lợi và bất lợi, hồi máu, hồi sinh, trượt, phản đòn, hút máu, kỹ năng đặc biệt VQSV5, ném bóng, bóng chạm, bắt được, thoát ra, gặp pet hoang dã, thắng trận, lên cấp, kiệt sức, mở rương, tiền, chạy trốn. Tiếng phát đúng lúc hiệu ứng chạm mục tiêu. Bật/tắt bằng nút "Âm thanh" ở cột bên hoặc phím N, kéo thanh trượt để chỉnh âm lượng; lựa chọn lưu trên trình duyệt. Trình duyệt chỉ cho phát sau lần bấm hoặc gõ phím đầu tiên. Trang `fx_preview.html` cũng có tiếng.
- **Map đi lại vẽ từ bản đồ thật của VQSV5** (`tools/build_vqsv5_walk_maps.py`, dựa trên bộ giải mã `assets/vqsv5/maps/`): 12 vùng khám phá giờ là 12 bản đồ gốc của VQSV5, vẽ đúng lớp nền, lớp phủ trên nhân vật (mái, tán cây, mây) và lưới va chạm của nguồn (ô 20 px; khi đi bộ 1, 2, 4 chặn, 3 là nước nông lội được). Ghép vùng với bản đồ gốc là lựa chọn của prototype, theo hình dáng: Rừng Rậm Phong Lôi → Rừng rậm phía tây 1 (map 16), Suối Sấm → Rừng rậm phía tây 3 (18, có suối), Hồ Sương Mù → Vực Bắc Bắc Hải (31), Đồng Cỏ Lộng Gió → Cao nguyên phía đông 1 (12), Cao Nguyên Đá → Cao nguyên phía đông 3 (14), Hẻm Núi Đá → Cao nguyên phía đông 4 (15), Bãi Tro Núi Lửa → Rừng rậm u ám 4 (41), Dải Mây Cao → Bầu trời phương bắc 1 (24), Rừng Thông Gió → Rừng rậm phía tây 5 (20), Đầm Mực Đen → Khu trường cũ Bắc Hải 2 (36), Đồng Bằng Sấm Sét → Bên ngoài thành phía nam (11), Thánh Địa Thần Thú → Thiên Không Thành 1 (49). Bản đồ lớn hơn màn hình thì cuộn theo Kael, bản đồ nhỏ thì nằm giữa. Lối ra đặt trên ô đi được ở đúng phía cũ, điểm đến khi qua lối được tính lại. Rương là rương thật của chính bản đồ gốc đó, ở đúng ô gốc (bỏ những cái sát lối ra). Loại sân đấu (nền trận VQSV5, luật −20%) và pet VQSV5 hoang dã lấy theo chính bản đồ gốc; bốn bản đồ nhánh cho pet VQSV6 vẫn không có pet VQSV5 hoang dã. Pet nước gặp khi đứng sát nước sâu hoặc trong nước nông (thay cho cây cầu cũ); Tương Quân Giải ở Thánh Địa chuyển lên đất vì thành trên mây không có nước. Thứ tự chạy công cụ: `design_wild_maps.py` → `design_extra_wild_maps.py` → `design_map_chests.py` → `build_vqsv5_walk_maps.py` → `build_prototype_bootstrap.py`. Làng khởi đầu giữ bản đồ vẽ tay cũ.


## Giao diện điện thoại, làng vẽ mới và trận kiểu VQSV5 (gộp từ nhánh `main`)

Phần này ghép bản Codex trên `main` vào engine hiện tại. Rừng Phong Lôi và Suối Sấm vẫn dùng map thật của VQSV5. Bản vẽ lại hai map này của Codex (`assets/trails/ryan-*.webp`) vẫn nằm trong repo nhưng game không dùng.

- **Khung màn hình:** game phủ kín màn hình. Góc trái trên ghi tên khu vực, dưới đó là thanh mục tiêu; bấm `!` để mở nhật ký. Góc phải có nút toàn màn hình và menu ☰. Kéo joystick ở góc trái dưới để đi, hoặc đặt ngón tay vào nửa trái của vùng dưới màn hình để kéo từ vị trí đó. Trên màn hình dọc, joystick rộng 130–170 px. Nút tròn bên phải đổi chữ theo việc nhấn E sẽ làm: nói chuyện, mua đồ, hái thảo dược, mở rương, qua cổng, khảo sát cầu. Bàn phím vẫn dùng như cũ.
- **Menu ☰:** hiển thị sáu mục theo thứ tự VQSV6: Tùy thân cửa hàng, Sủng vật, Lưng bao, Đồ giám, Nhiệm vụ, Lưu dữ liệu. Đồ giám lọc pet đã bắt. Mục Lưu dữ liệu cho phép lưu và tải lại. Trong Hệ thống có bản đồ, sổ kỹ năng, âm thanh và chơi lại. Dùng phím 2/4/6/8 hoặc mũi tên để di chuyển, 5/Enter hoặc Chọn để mở, Esc hoặc Quay lại để trở về. Ví tiền hiện ở đầu menu.
- **Làng biên giới:** dùng tranh pixel của Codex (`assets/trails/slice01-border-village.webp`), cây và nhà vẽ theo chiều sâu, cùng hình mới cho Kael, NPC và Lôi Miêu đi theo. Hai cửa nhà dẫn vào **Tiệm thuốc** và **Tiệm bùa** (`data/village_interiors.json`). Người bán trong tiệm mở Thương điếm chung của game (kim tiền, huy hiệu, danh mục VQSV6), không dùng bảng xu riêng của bản Codex. Mỗi tiệm bán hàng riêng (xem mục Cửa hàng bên dưới).
- **Đi qua cổng:** cổng làng/rừng/suối đã mở thì giữ phím hoặc joystick đi qua mép map là sang khu kế tiếp, vẫn giữ hướng đi. Cửa tiệm không cần gặp người gác làng trước.
- **Nhiệm vụ phụ Khảo sát Suối Sấm:** nhận từ người đi rừng sau khi gặp người gác làng, đến điểm ◆ ở Suối Sấm rồi về báo tin. Map Suối Sấm thật không có đối tượng cầu riêng, nên `tools/build_prototype_bootstrap.py` đặt điểm khảo sát ở ô đi được gần tâm map nhất (`surveyPoint`). Phần thưởng thử là 200 kim tiền, nhận một lần. Bản lưu cũ chưa có nhiệm vụ này vẫn tải được.
- **Bản đồ khu vực** (Menu → Bản đồ, phím M): vẽ cả map hiện tại, kể cả map VQSV5 lớn hơn màn hình, kèm lối đi, mục tiêu và nút **Chỉ đường**. Tab **Thế giới** có 12 địa danh.
- **Trận đấu kiểu VQSV5:** đối thủ đứng trên phải, pet của Kael dưới trái, hiệu ứng pixelfx bay theo hướng mới. Lời nhắn và năm lệnh **Công kích, Đạo cụ, Sủng vật, Cửa hàng, Chạy trốn** nằm trong khung xanh bên dưới. **Bắt sủng vật** nằm trong **Đạo cụ**. Nền trận theo vùng và hệ vẫn giữ như trước. Mở `/#thu-tran` để vào thẳng trận truyện.

## Sủng vật: ba lô và ngân hàng (theo VQSV6)

Menu ☰ → **Sủng vật** (hoặc phím `P` ngoài trận) mở màn hình quản lý pet. Quy tắc lấy từ mã nguồn VQSV6: `game/k.java` cho màn `petstate.ui` / `petsetting.ui`, `game/j.java` cho sức chứa.

- **Ba lô Sủng vật:** tối đa 6 con, gồm Lôi Miêu và 5 pet khác. Đây là đội mang vào trận và đổi được trong trận. Pet bắt được tự vào ba lô nếu còn chỗ, nếu không thì vào ngân hàng.
- **Ngân hàng Sủng vật:** tối đa 100 con. Gồm mọi pet đã bắt hoặc đã luyện mà không nằm trong ba lô.
- Mỗi dòng hiện hình, cấp, hệ, thanh HP (đỏ) và thanh EXP (xanh). Bảng chi tiết có HP, Công, Thủ, Tốc, EXP, kỹ năng, trang bị và tình trạng tiến hóa.
- **Xuất chiến:** chọn pet ra trận đầu tiên ở trận sau. Các pet còn lại giữ thứ tự trong ba lô.
- **Gởi lại:** chuyển pet sang ngân hàng. Không gởi được khi ngân hàng đã đầy.
- **Lấy ra:** chuyển pet từ ngân hàng về ba lô. Không lấy được khi ba lô đã đủ 6 con.
- **Phóng sinh:** bấm hai lần (Phóng sinh rồi Xác nhận phóng sinh). Pet bị xóa cùng bản ghi bắt và tiến trình luyện.
- **Tiến hóa / Dị hóa:** nút hiện khi đủ điều kiện, dùng cùng quy tắc với sổ pet.
- Lôi Miêu là bạn đồng hành của Kael trong truyện: luôn ở trong ba lô và không phóng sinh được, giống cách bản gốc chặn phóng sinh Thần thú. Bản gốc chỉ yêu cầu ba lô còn ít nhất một sủng vật; prototype luôn giữ Lôi Miêu nên điều kiện đó luôn đúng.
- Không mở được màn hình này trong trận; trong trận đổi pet bằng lệnh **Sủng vật** như trước.
- Sổ pet (Đồ giám) vẫn cho đưa một loài vào đội từ danh mục, kể cả loài chưa bắt. Đây là cách thử của prototype, không phải luật của game gốc.
- Pet hiện vẫn lưu theo loài: mỗi loài một con. Bắt thêm con cùng loài sẽ giữ con mạnh hơn, như trước.

## Kết hợp sủng vật hai bản

Cả ba phần dưới đây là thiết kế thử của bản hợp nhất, không có trong game gốc nào.

- **Một bảng hệ chung:** 5 hệ VQSV5 được xếp vào 7 hệ VQSV6, gồm Thực Vật → Mộc, Kim Loại → Điện, Phi Hành → Phong, Mãnh Thú → Thổ, Hải Dương → Thủy. Hai pet VQSV5 không có hệ (Quỷ Vương Địa Phủ) tính là Quỷ hệ. Bảng nằm ở `UNIFY5` trong `index.html` và trong `tools/atlas_wild.py`.
  - Trận có pet hai bản đấu nhau: tính khắc chế theo bảng VQSV6.
  - Hai pet VQSV5 đấu nhau: vẫn dùng luật gốc +30% theo bảng hệ VQSV5, không cộng thêm bảng VQSV6.
  - Nền trận khi gặp pet VQSV5 hoang dã chọn theo hệ chung.
  - Hiệu ứng kỹ năng của pet VQSV5 vẫn giữ màu theo hệ gốc.
  - Màn Sủng vật hiện cả hai tên hệ, ví dụ "Hải Dương hệ (Thủy hệ)".
- **Pet hoang dã trên 29 map mới:** mỗi map có pet hoang dã trộn cả hai bản, theo hệ của vùng (`wildElements`). Ví dụ: hoang mạc ra Thổ và Hỏa, vùng băng và biển ra Thủy, tháp và trời ra Phong. Pet sinh ra chỉ là dạng gốc; pet VQSV5 chỉ lấy hạng 1–3. Cấp tăng dần theo đường đi, map thứ i có cấp từ 8 + 2i đến 11 + 2i (map cuối là cấp 64–67). Làng và hai tiệm không có pet hoang dã.
- **Ghép sủng vật:** trong màn Sủng vật, chọn pet chính rồi bấm **Ghép**, sau đó chọn một pet của bản kia (ở ba lô hoặc ngân hàng).
  - Điều kiện: cả hai đạt cấp 10.
  - Phí: 100 kim tiền cho mỗi cấp của pet ghép.
  - Pet chính nhận một nửa số EXP mà pet ghép cần cho cấp cuối của nó.
  - Nếu hai pet cùng hệ chung: pet VQSV6 lên một bậc phẩm chất (tối đa 5); pet VQSV5 được gieo lại đặc tính theo kiểu "may mắn".
  - Pet ghép mất đi sau khi ghép. Lôi Miêu làm được pet chính nhưng không làm pet ghép.

## Tính sát thương pet

Trình tự một đòn, theo `game/i.java` `C()` và `b(i)` của VQSV6:

1. **Gốc:** Công người đánh − Thủ người nhận. Vật phẩm mang theo được tính vào bước này: Mạn Đà La Thạch, Hồng Sắc Hải Loa, Quy Xác Toái Phiến.
2. **Bạo kích:** tỉ lệ 5% + Tốc / 2 (cộng thêm của Viễn Cổ Long Cốt nếu có). Trúng bạo kích thì sát thương gốc × 1,5.
3. **Theo kỹ năng:** phần lớn chiêu tính sát thương × uy lực %. Một số chiêu có luật riêng (mới thêm, giải từ bảng 1 cột 3 = uy lực và cột 8 = tham số):
   - **Dương viêm, Chước nhiệt chi xúc** (dòng 1/7): uy lực % cộng thêm sát thương / tham số (4 và 3).
   - **Hỏa Vân triệu, Vĩnh hằng hỏa ảnh** (3/9): đối thủ đang mang trạng thái bất lợi số 0 (hiệu ứng cháy) thì dùng tham số 120% / 250% thay cho uy lực 100% / 200%.
   - **Nham băng, Thổ Chi Loạn Vũ** (23/29): đối thủ đang Mê Muội thì dùng 250% / 300%.
   - **Sóng điện từ, Cảm ứng điện từ** (43/49): đánh theo uy lực và xóa hết trạng thái có lợi của đối thủ.
   - **Cơn ác mộng, Lời nguyền cuối cùng** (53/59): sát thương × (tham số − % HP còn lại)%. Mô tả trong game nói "HP kẻ địch càng thấp càng mạnh", nhưng mã gốc đọc HP của **người dùng chiêu**; prototype làm theo mã.
4. **Trạng thái của người đánh:** Súc Lực, Phá Phủ, Điện áp làm tăng; trạng thái bất lợi số 6 làm giảm.
5. **Khắc hệ:** khắc thì × 3, bị khắc thì × 0,6. Pet VQSV5 dùng bảng hệ chung; hai pet VQSV5 đấu nhau thì chỉ dùng luật +30% gốc.
6. **Làm tròn cuối:** sát thương tối thiểu 1. Đòn rất nhỏ được cộng hoặc trừ 1 ngẫu nhiên. Sau đó áp các luật VQSV5 (trung thành, khắc hệ +30%, địa hình −20%, kỹ năng đặc biệt) và các luật trong trận (phòng thủ, phản đòn, hút máu).

Mã gốc còn hai chỗ prototype **không** làm:
- Điều kiện bạo kích 30% (`D == X[hệ] + Y[hệ] − 1`): với dữ liệu thật, điều kiện này không bao giờ đúng.
- Hai mức tăng sát thương thuộc tính năng nạp tiền của bản gốc.

**Sát thương dự tính:** trong bảng **Công kích**, mỗi chiêu hiện `≈ số` là sát thương một đòn không bạo kích lên đối thủ hiện tại. ▲ là khắc hệ, ▼ là bị khắc. Số này bỏ qua các phần ngẫu nhiên: bạo kích, ±1, đánh trượt, đặc tính, và kỹ năng bị động có xác suất.

## Chỉ số và tiến hóa trong màn Sủng vật

- **Phẩm chất:** pet VQSV6 hiện sao theo dạng ★★☆☆ (đang có / tối đa). Mức tối đa là số sao của loài, lấy từ bảng 0 cột 4 của `db.mid` (`max_quality_stars`); 106 trên 111 dòng pet hoang dã của bản gốc nằm trong giới hạn này. Ghép pet không nâng phẩm chất quá mức tối đa của loài. Pet hoang dã trên các map mới cũng không sinh ra vượt mức này.
- **Lên cấp:** dòng "Lên cấp N" cho biết HP, Công, Thủ, Tốc tăng bao nhiêu ở cấp kế tiếp. Số được tính bằng cùng công thức dùng trong trận: VQSV6 theo `growth` và phẩm chất; VQSV5 theo bảng gốc đã quy đổi sang thang VQSV6.
- **Trước/sau tiến hóa:** với mỗi dạng pet có thể tiến hóa hoặc dị hóa thành, màn hình hiện chỉ số của dạng mới ở cấp hiện tại và mức chênh lệch. Phẩm chất sau tiến hóa theo luật gốc: giữ nguyên nếu không thấp hơn mức mặc định của dạng mới, nếu thấp hơn thì lấy mức mặc định đó.
- **Map mới của Codex** (Cao nguyên Ngọc Lục, Quần đảo San Hô Hồng, Bán đảo Đồng Đỏ) có pet hoang dã trộn hai bản theo hệ của vùng. Cấp tính theo map mà vùng đó rẽ ra, cộng thêm 2 cấp cho mỗi map đi xa hơn. Đây là giá trị thử.

## Màn trận theo thiết kế của Codex

Màn trận dùng lại thiết kế VQSV5 Codex làm trong commit `7c4f864`. Khi gộp lần đầu, phần hình này chưa được đưa vào. Nay toàn bộ được vẽ trên canvas của game, nên đổi pet, bắt pet, hiệu ứng pixelfx, trạng thái và nền theo vùng vẫn chạy như trước.

- **Bố cục:** hai dải cảnh xếp chồng. Dải trên là đối thủ (đứng bên phải), dải dưới là pet của Kael (đứng bên trái). Vị trí theo màn gốc 240 × 320 của VQSV5.
- **Bảng tên và HP:** bảng tên màu băng ghi cấp. Ô xanh ghi % HP bằng chữ số vàng (`assets/ui/duel-digits.png`). Dưới chân pet có số HP và thanh máu đỏ; pet của Kael có thêm thanh xanh lam (số lần dùng kỹ năng chính) và vạch vàng dưới bảng tên (EXP).
- **Nền:** mỗi dải dùng nền theo vùng hoặc theo hệ như trước. Trận VQSV5 mỗi bên một nền địa hình. Dải dưới lật gương như bản Codex. Ảnh nền được kéo vừa chiều ngang; khi dải cao hơn ảnh thì phần trời phía trên được kéo dài từ hàng điểm ảnh đầu tiên của ảnh. Trận truyện dùng hình Lôi Miêu và sói tha hóa Codex vẽ.
- **Khung lệnh:** thanh "Lượt N", khung lệnh xanh với 5 lệnh, và hàng phím mềm **Chọn / ⛶ / Menu**. Khi đang mở một bảng (Công kích, Đạo cụ...), phím phải đổi thành **Trở lại**.
- **Kích thước:** canvas trận đổi theo khoảng trống phía trên khung lệnh: cao 300–900 đơn vị, rộng 480, độ nét theo màn hình. Hết trận, canvas trở lại 480 × 360. `skill_fx.js` có thêm `setLayout()` để hiệu ứng bay đúng vị trí hai pet trong bố cục này; đây là thay đổi duy nhất trong phần hiệu ứng.

## Động tác pet, độ lớn sprite và nhịp hiệu ứng, âm thanh

- **Độ lớn sprite:** pet được vẽ theo phần hình thật, đã cắt bỏ viền trong suốt. Khung hình VQSV5 thường có viền trong suốt rộng, nên trước đây pet VQSV5 trông nhỏ hơn hẳn Lôi Miêu. Mỗi chuỗi hoạt ảnh dùng một khung cắt chung để các khung không bị nhảy. Hình vẫn phóng theo bội số nguyên nên giữ nét pixel.
- **Hướng nhìn:** hình VQSV6 quay sang phải, hình VQSV5 quay sang trái (bản gốc lật hình cho phía người chơi). Mỗi pet được lật để luôn quay mặt về đối thủ.
- **Động tác khi dùng chiêu:**
  - Chiêu gây sát thương: lấy đà (ngả về sau, hơi dẹt) trong 170 ms, lao chéo về phía đối thủ (kéo dài người), rồi trở về chỗ.
  - Chiêu trạng thái: nảy lên, có quầng sáng theo màu hệ.
  - Pet trúng đòn: bị đẩy lùi, rung (bạo kích rung mạnh hơn) và nháy trắng.
- **Nhịp khớp nhau:** hiệu ứng bay ra ngay sau lúc lấy đà. `VQSVSkillFx.cast()` trả về thời điểm trúng (`impactAt`) và thời điểm hiệu ứng cuối cùng kết thúc (`endAt`).
  - Tại thời điểm trúng: phát âm thanh hệ và tiếng đòn chạm thân, pet nhận đòn rung và nháy, thanh HP và % HP bắt đầu tụt (tụt dần trong 350 ms).
  - Âm thanh khi chiêu rời khỏi pet: chiêu hệ có tiếng tụ lực (`charge`), đòn thường có tiếng vút (`swing`).
  - Lượt tiếp theo, kể cả phản đòn của đối thủ, chỉ bắt đầu khi hiệu ứng trước đã chạy xong, nên các chiêu không chồng lên nhau. Trong lúc chờ, các nút lệnh bị khóa.

## Nhiệm vụ chính Chương 1–4 (MQ001–MQ080)

`quests.js` đưa 9 nhiệm vụ đầu của `DANH_SACH_NHIEM_VU.md` vào game. Nhiệm vụ làm lần lượt: xong nhiệm vụ này mới mở nhiệm vụ kế tiếp. Mục tiêu hiện ở thanh nhiệm vụ, nút hành động đổi chữ theo việc đang làm, và Nhật ký (phím J) liệt kê cả 9 nhiệm vụ kèm phần thưởng. Tiến độ được lưu cùng bản lưu game.

| Mã | Ở đâu | Cách hoàn thành trong game |
| --- | --- | --- |
| MQ001 | Làng, cạnh Trưởng làng Elden | Nói chuyện với Elden (người gác làng cũ, nay mang tên theo bảng), rồi đánh bia gỗ đặt cạnh ông. Bia gỗ không đánh trả. |
| MQ002 | Căn hầm cũ phía tây làng | Mở hầm, nhận La Bàn Đồng Cổ và Nhật Ký Thất Lạc. |
| MQ003 | Rừng Phong Lôi | Gặp Thợ săn Bran ở bìa rừng, hạ 3 Dã Lang Bão Tố (một nửa số lần gặp pet trong rừng là con này), rồi về báo Bran. |
| MQ004 | Làng → Đồng Cỏ (Thung Lũng Rêu) | Nói chuyện với Dược sĩ Mae (người chăm sóc pet), hái 5 Cỏ Dạ Quang phát sáng trên map Đồng Cỏ, mang về cho Mae. |
| MQ005 | Suối Sấm | Lần theo 3 dấu chân tà khí theo thứ tự, rồi báo Elden. |
| MQ006 | Làng, ban đêm | Làng nhuốm màu đỏ. Ba dân làng bị lửa hắc ám vây; mỗi người là một trận với ma thú. |
| MQ007 | Cổng đông của làng | Trận với Cuồng Nham Cự Tượng: không chạy trốn được, thắng khi cầm cự đủ 6 lượt. |
| MQ008 | Giữa làng (chỗ dấu vết cũ) | Trận với Tà Vân Khuyển Thần bị Ma Khống Cầu điều khiển. Riêng trận này Lôi Miêu có chiêu Lôi Kích Trắng. Thắng thì hiện cảnh la bàn thức tỉnh, nhấn E hoặc **Tiếp tục** để đi tiếp. |
| MQ009 | Điện thờ cổ trong Rừng Phong Lôi | Nghe Linh Hồn Cổ Thụ kể về 4 thánh vật, nhận Bản Đồ Ryan. Hết phần Làng Sương Mù của Chương 1. |

Những chỗ bảng nhiệm vụ còn để ngỏ, prototype tự chọn mặc định (TRIAL, chờ anh duyệt):
- **Tên NPC:** Elden, Bran, Mae dùng tên đề xuất trong bảng. Elden là người gác làng có sẵn; Mae là người chăm sóc pet có sẵn. Bran là NPC mới, mượn hình người đi rừng.
- **MQ002 và cái la bàn:** kịch bản nói Kael đã có la bàn từ đầu. Prototype vẫn làm theo bảng: tìm la bàn và nhật ký trong hầm.
- **Địa điểm:** Thung Lũng Rêu dùng map Đồng Cỏ; Bờ Suối Trầm Mặc dùng Suối Sấm; Điện Thờ Cổ đặt trong Rừng Phong Lôi. Vị trí các điểm nhiệm vụ được tính từ dữ liệu map lúc chạy, nên vẫn đúng khi map đổi.
- **MQ007:** bảng ghi "sống sót 3 phút"; trận theo lượt nên đổi thành 6 lượt. Nham Cự Tượng là Nham Sơn Long cấp 9 với HP gấp 6.
- **Kẻ địch:** Dã Lang Bão Tố là Nhiệt Bạo Phong Tốc Khuyển; ma thú đêm trăng là Đan nhãn thú; Ma Khống Cầu điều khiển Tà Vân Khuyển Thần.
- **Phần thưởng:** EXP cộng cho Lôi Miêu, vàng thành kim tiền. Các món chưa có trong game được thay bằng món gần nhất: Bình Thuốc Nhỏ → Khôi phục tề; Bột Thảo Mộc → Bánh Sandwich; Quả Cầu Bắt Thú → Phong ấn cầu; Giáp Da Tân Thủ → Quy Xác Toái Phiến; Phong Ấn Cầu Nguyên Tố → Đại sư cầu. La Bàn, Nhật Ký và Bản Đồ Ryan là vật phẩm cốt truyện, hiện trong Nhật ký.
- **Trận nhiệm vụ:** thua thì không mất game; Kael được đưa về chỗ cũ để thử lại.
- **Lôi Kích Trắng** (uy lực 320%, 3 lần dùng) chỉ có trong trận MQ008.
- **Phần còn lại của Chương 1 (MQ010–MQ020)** đặt trên các map Codex dựng ở phía nam làng. Mỗi nhiệm vụ được mô tả bằng dữ liệu trong `quests.js` (ai giao, việc cần làm, phần thưởng):

| Mã | Map | Cách hoàn thành |
| --- | --- | --- |
| MQ010 | Đèo băng | Gặp Trưởng đoàn buôn, đẩy lùi 2 đợt phục kích theo thứ tự, đưa đoàn tới lối ra phía Hồ Băng. |
| MQ011 | Hồ Băng Cổ Xưa | Chạm vào Băng Trụ Trấn Giữ, phá 3 phong ấn băng lam (mỗi phong ấn một trận). |
| MQ012 | Hồ Băng Cổ Xưa | Trấn an Tuyết Y Linh Thú: cầm cự 5 lượt mà không hạ gục nó. Hạ gục là phải làm lại. |
| MQ013 | Hẻm núi cát đỏ | Gặp Hướng dẫn viên Jax, đánh bại Bọ Cạp Nham Thạch. |
| MQ014 | Hỏa Sơn Bất Diệt | Cần một sủng vật Thủy hệ trong ba lô để làm nguội dung nham (bảng ghi "hệ Băng"; VQSV6 không có hệ Băng). |
| MQ015 | Hỏa Sơn Bất Diệt | Thu phục Viêm Long Tí Nị (Long bảo bối) bằng cầu bắt. Đánh ngất nó là phải làm lại. |
| MQ016 | Đồng Bằng Sấm Sét | Gặp Tàn binh giáo phái, hạ 10 Tín Đồ Ma Khống (mỗi trận gặp một nhóm 2 tên), về báo tàn binh. |
| MQ017 | Đường lên Tháp Thiên Không | Đặt 4 tinh thể Băng, Hỏa, Lôi, Phong vào 4 trụ đài. |
| MQ018 | Các tầng Tháp Thiên Không | Thắng "Ảo ảnh thù hận"; nghi thức cho Lôi Miêu tiến hóa lên dạng kế tiếp (nghi thức tự cấp nguyên liệu, vẫn cần đủ cấp). Bảng gọi dạng này là "Cực Lôi Hoàng Hổ"; game dùng dạng tiến hóa có trong dữ liệu gốc. |
| MQ019 | Đỉnh Tháp Thiên Không | Thắng 3 đợt hộ vệ giáo chủ tại cùng một chỗ. |
| MQ020 | Đỉnh Tháp Thiên Không | Đánh bại Cổ Long Hắc Ám (Minh vương Long), nhận Bức thư của cha và danh hiệu Anh Hùng Ryan. Cảnh kết Chương 1. |

  Quy ước chung cho các trận nhiệm vụ: trận boss không chạy trốn được; tên và hình đối thủ lấy từ dữ liệu pet VQSV6. Nền trận dùng địa hình của map; trận trong làng giữ cảnh đêm.
- **Chọn mặc định (TRIAL):** cấp và độ trâu của từng đối thủ; các món thưởng được thay bằng món gần nhất đã có trong game (xem bảng thưởng trong Nhật ký); thứ tự vùng theo bảng nhiệm vụ: Hồ Băng trước rồi mới tới Hỏa Sơn. Bảng nhiệm vụ ghi điểm này "cần chốt".
- **Chương 2 · Hải Vực và Atlantis (MQ021–MQ042)** đặt trên các map biển của Codex, theo thứ tự đường đi: Bến cảng Ryan, Hải Vực Vô Tận, Vòng xoáy lôi đình, Vương Quốc Chìm Atlantis, Hang Động San Hô Lân Quang, Nghĩa Địa Chiến Thuyền, Vực Thẳm Tận Cùng.

| Mã | Map | Cách hoàn thành |
| --- | --- | --- |
| MQ021 | Bến cảng Ryan | Gặp Thuyền trưởng Drake; nhặt gỗ ở 5 đống gỗ và lấy buồm trong kho; về báo Drake. |
| MQ022 | Hải Vực Vô Tận | Gặp thủy thủ đoàn; lấy 3 mẫu nước tím. |
| MQ023 | Hải Vực Vô Tận | Đánh chìm 2 Thủy Quái Bọc Thép. |
| MQ024 | Vòng xoáy lôi đình | Né 3 cột sóng xoáy theo thứ tự. |
| MQ025 | Atlantis | Gặp lính gác Hải Tộc; đặt vũ khí ở cột đá; nói chuyện lại với lính gác. |
| MQ026 | Atlantis | Nói chuyện với Lyra để nhận lời ủy thác. |
| MQ027 | Atlantis | Bảo vệ quả trứng: trụ 6 lượt trước Tiểu đội thiết giáp. |
| MQ028 | Atlantis | Chạm phiến đá ký ức của cha. |
| MQ029 | Hang San Hô | Phá 3 bẫy điện tử theo thứ tự. |
| MQ030 | Hang San Hô | Đối mặt Ảo cảnh nỗi sợ: trụ 5 lượt, không được hạ gục nó. |
| MQ031 | Vòng xoáy lôi đình | Đưa Lôi Miêu lên đài lôi đình. Lôi Miêu có thêm chiêu Lôi Dực Khiêu Vũ (uy lực 260%, 12 lần dùng) từ đây về sau. |
| MQ032 | Nghĩa Địa Chiến Thuyền | Đánh lui oan hồn rồi lục buồng hạm trưởng tìm la bàn áp suất. |
| MQ033 | Nghĩa Địa Chiến Thuyền | Thu phục U Linh Sa Ngư bằng cầu bắt (dùng loài Tấn Cá Kiếm). |
| MQ034 | Vực Thẳm Tận Cùng | Phá 3 trạm phát sóng. |
| MQ035 | Vực Thẳm Tận Cùng | Dẫn cả nhóm qua 3 khe đá nứt theo thứ tự. |
| MQ036 | Vực Thẳm Tận Cùng | Đánh hộ vệ người máy canh phòng giam. |
| MQ037 | Vực Thẳm Tận Cùng | Đập 2 cánh tay robot. |
| MQ038 | Vực Thẳm Tận Cùng | Phá 4 bộ hấp thụ năng lượng ở bốn góc. |
| MQ039 | Vực Thẳm Tận Cùng | Mở khúc hát ru qua la bàn đồng. |
| MQ040 | Vực Thẳm Tận Cùng | Thanh tẩy Cơ Giới Thần Long: trụ 6 lượt, không được hạ gục nó. |
| MQ041 | Vực Thẳm Tận Cùng | Dìu cha chạy qua 3 hành lang sụp đổ theo thứ tự. |
| MQ042 | Atlantis | Đến bên quả trứng. Cảnh kết Chương 2. |

  Bảng nhiệm vụ đặt đồng hồ đếm ngược cho MQ027 (4 phút) và MQ041 (3 phút). Vì trận đánh theo lượt, MQ027 đổi thành trụ 6 lượt, còn MQ041 thành 3 chặng chạy theo thứ tự, không đếm giờ. Cơ Giới Thần Long, robot và bẫy điện tử dùng pet VQSV6 cùng hệ, vì dữ liệu gốc không có pet máy. Các món thưởng chưa có trong game được thay bằng món gần nhất (Nhật ký ghi rõ từng món).
- **Vị trí điểm nhiệm vụ:** chỉ đặt ở ô Kael đi tới được từ lối vào map (tìm bằng loang từ các lối ra) và luôn có ô trống bên cạnh để đứng.
- **Hội thoại:** lời thoại hiện trong khung riêng phía trên nút điều khiển, không vẽ trên canvas nữa, vì trên điện thoại dọc canvas bị cắt mất phần đáy.
- **Chương 3 · Aethelgard và Boran (MQ043–MQ060)** đặt trên các map Lối vào Aethelgard, Miệng Núi Lửa Hắc Diệu, Pháo đài và đấu trường Boran, Đường lên Tầng Mây Khởi Nguyên, Cung điện pha lê.

| Mã | Map | Cách hoàn thành |
| --- | --- | --- |
| MQ043 | Lối vào Aethelgard | Nói chuyện với cha Kael ở doanh trại, nhận Khí Cầu Cổ. |
| MQ044 | Lối vào Aethelgard | Nói chuyện với Lyra dưới ánh trăng. |
| MQ045 | Lối vào Aethelgard | Giữ bánh lái qua 3 tâm bão theo thứ tự. |
| MQ046 | Lối vào Aethelgard | Gặp cư dân Thiên Không, kích hoạt trạm dịch chuyển. |
| MQ047 | Lối vào Aethelgard | Giải mã bản đồ cổ tự ở đài thiên văn. |
| MQ048 | Miệng Núi Lửa Hắc Diệu | Hạ 8 Quái Nham Thạch Cơ Giới (gặp khi đi trong map). |
| MQ049 | Miệng Núi Lửa Hắc Diệu | Phá 2 cổng sắt do lính gác Titan canh. |
| MQ050 | Miệng Núi Lửa Hắc Diệu | Ghi dữ liệu ở 3 trụ xích. |
| MQ051 | Pháo đài Boran | Thắng 3 đợt thiết giáp tiên phong. |
| MQ052 | Pháo đài Boran | Phá lớp Nhiệt Giáp của Nham Bạo Long. |
| MQ053 | Pháo đài Boran | Cầm cự 5 lượt trong lồng sắt. |
| MQ054 | Pháo đài Boran | Kết liễu Nham Bạo Long; riêng trận này Lôi Miêu có chiêu Lôi Kích Băng Hàn. |
| MQ055 | Pháo đài Boran | Nói chuyện với linh hồn Cổ Thú Nham Thạch. |
| MQ056 | Pháo đài Boran | Nói chuyện với Lyra ở doanh trại. |
| MQ057 | Đường lên Tầng Mây | Lần theo 3 cột khói tím rồi chế Khiên Kháng Gương. |
| MQ058 | Cung điện pha lê | Hạ 12 Ảo Ảnh Phi Hành Cơ (mỗi trận một tốp 3). |
| MQ059 | Cung điện pha lê | Bước lên bậc thang khi ba lô có ít nhất 3 sủng vật. |
| MQ060 | Cung điện pha lê | Bước qua cánh cửa gương. Cảnh kết Chương 3. |

  Một số mục tiêu trong bảng nhiệm vụ không làm được đúng nguyên văn trong trận đánh theo lượt, nên được đổi như sau. Ba giai đoạn boss Nham Bạo Long thành ba trận liền nhau. Thanh "Nhiệt Giáp về 0, Boss còn 70% HP" thành trận phá lớp Nhiệt Giáp. Đếm ngược 60 giây ở giai đoạn 3 được bỏ. MQ059 "kiểm tra trang bị và nâng cấp linh thú" thành điều kiện ba lô có ít nhất 3 sủng vật. Nham Bạo Long dùng loài Bạo Long Thú, Quái Nham Thạch Cơ Giới dùng Sừng tê giác bạo long, Ảo Ảnh Phi Hành Cơ dùng Quáng thạch dực long. Các món thưởng chưa có trong game được thay bằng món gần nhất.
- **Chương 4 · Kính Giới và hành trình lên trời (MQ061–MQ080)** đặt trên các map Điện gương, Đỉnh Aethelgard, Cột Mốc Vọng Thiên và Quỹ đạo. Bảng nhiệm vụ ghi chương 4–5 là nội dung mở rộng còn cần duyệt; prototype làm đúng theo bảng.

| Mã | Map | Cách hoàn thành |
| --- | --- | --- |
| MQ061 | Điện gương | Chạm 4 tấm gương thật theo thứ tự. |
| MQ062 | Điện gương | Phá 3 gương ảo giác. |
| MQ063 | Điện gương | Cầm cự 6 lượt trước Bóng ma tâm linh. |
| MQ064 | Điện gương | Đẩy lùi 3 đợt bóng gương theo thứ tự để Lyra hát trọn bài. |
| MQ065 | Đỉnh Aethelgard | Kích hoạt 4 trụ pha lê theo thứ tự Bắc, Đông, Nam, Tây. |
| MQ066 | Đỉnh Aethelgard | Hạ 15 Cận Vệ Gương (mỗi trận một tốp 3). |
| MQ067 | Đỉnh Aethelgard | Đập vỡ Đồng Hồ Vô Tận. |
| MQ068 | Đỉnh Aethelgard | Phá 4 gương phản đòn ở bốn góc. |
| MQ069 | Đỉnh Aethelgard | Đánh bại bản sao Lôi Miêu và bản sao của Lyra. |
| MQ070 | Đỉnh Aethelgard | Cầm cự 5 lượt trong hố đen ý thức. |
| MQ071 | Đỉnh Aethelgard | Đánh bại Kính Giới Thiên Sứ; riêng trận này Lôi Miêu có chiêu Thủy Lôi Thần Hợp Nhất. |
| MQ072 | Đỉnh Aethelgard | Nhặt Khối Lập Phương Hư Vô. |
| MQ073 | Cột Mốc Vọng Thiên | Lượn qua 3 mảnh vỡ theo thứ tự. |
| MQ074–MQ076 | Cột Mốc Vọng Thiên | Chiếu bản đồ sao, nghe bản ghi âm của cha, khởi động Cột Mốc. |
| MQ077 | Cột Mốc Vọng Thiên | Bắn hạ 5 khinh khí cầu trinh sát (gặp khi đi trong map). |
| MQ078 | Cột Mốc Vọng Thiên | Huýt sáo hiệu triệu khi ba lô có đủ 6 sủng vật. |
| MQ079 | Cột Mốc Vọng Thiên | Nói chuyện với Lyra. |
| MQ080 | Quỹ đạo | Phá 3 pháo phòng không theo thứ tự. Cảnh kết Chương 4. |

  Bảng nhiệm vụ cho đối thủ cấp 66–85, nhưng game chỉ có tới cấp 70; cấp đối thủ giờ theo cấp của map (xem mục Cân bằng bên dưới). Boss Seraphina chia thành nhiều trận theo các giai đoạn trong bảng. Mục "QTE" và "tiêu diệt 2 bản sao trong 5 giây" đổi thành trận đánh thường. Các món thưởng chưa có trong game được thay bằng món gần nhất.
- **Chương 5 · Mặt Trăng (MQ081–MQ096)** đặt trên các map Cột Mốc Vọng Thiên, Quỹ đạo, Vành đai cơ khí và Mặt Trăng Đỏ. Bảng ghi chương này cần chủ dự án duyệt, vì có chi tiết mẹ Kael còn sống và kết cục của Thực Thể Thứ 0. Prototype tạm làm đúng theo bảng (TRIAL) để chơi được trọn tuyến; nếu cốt truyện đổi thì chỉ cần sửa khối `PART2.push` của chương 5 trong `quests.js`.

| Mã | Map | Cách hoàn thành |
| --- | --- | --- |
| MQ081 | Cột Mốc Vọng Thiên | Giữ thanh Lôi Đình qua 3 tầng khí quyển theo thứ tự. |
| MQ082 | Quỹ đạo | Phá 8 mìn từ tính (gặp khi đi trong map, mỗi trận một cụm 2). |
| MQ083 | Quỹ đạo | Đánh lui tàu hộ tống rồi đổ bộ lên boong. |
| MQ084 | Quỹ đạo | Hạ 10 Robot Kháng Trọng Lực (mỗi trận một tốp 2). |
| MQ085 | Quỹ đạo | Giúp Lyra đông kết động cơ, rồi phá Lõi nhiệt hạch. |
| MQ086 | Quỹ đạo | Đánh bại Đại Tướng Cơ Giới (không chạy được). |
| MQ087 | Quỹ đạo | Né 3 đợt mảnh vỡ theo thứ tự. |
| MQ088–MQ089 | Mặt Trăng Đỏ | Tìm cửa hầm, mở buồng ngủ đông của mẹ bằng la bàn. |
| MQ090 | Mặt Trăng Đỏ | Cầm cự 6 lượt để bảo vệ hệ thống sự sống. |
| MQ091 | Vành đai cơ khí | Phá 4 tháp plasma. |
| MQ092 | Mặt Trăng Đỏ | Dọn 9 phôi thai hư không (mỗi trận một ổ 3). |
| MQ093 | Mặt Trăng Đỏ | Nói chuyện với Lyra. Lôi Miêu học hẳn chiêu đôi **Uyên Ương Lôi Hải** (sức mạnh 340, 8 lần; TRIAL). |
| MQ094 | Mặt Trăng Đỏ | Cắt 6 ống dẫn titan. |
| MQ095 | Mặt Trăng Đỏ | Đánh bại phân thân Thực Thể Thứ 0 (dùng loài Minh vương Long, máu gấp 6). |
| MQ096 | Mặt Trăng Đỏ | Tái lập phong ấn. Cảnh **Đại kết cục**, sau đó chơi tự do. |

  Các trận "trong 3 phút" và "điều khiển né mảnh vỡ" đổi thành trận thường và chặng theo thứ tự, vì trận đánh theo lượt. Mìn, robot, tháp plasma, Đại Tướng Cơ Giới và Thực Thể Thứ 0 dùng pet VQSV6 cùng hệ, vì dữ liệu gốc không có loài riêng (TRIAL). Cấp đối thủ theo cấp của map (xem mục Cân bằng). Các món thưởng chưa có trong game được thay bằng món gần nhất. Khi xong MQ096, dòng mục tiêu ghi đã hoàn thành cả 96 nhiệm vụ chính và người chơi đi tự do khắp các vùng.

## Sửa lỗi treo game ở chiêu Quỷ hệ cấp 3

Hiệu ứng `shadowTendrils` của pixelfx bị kẹt vòng lặp vô tận với một số seed ngẫu nhiên. Cứ khoảng 15 lần tung chiêu Quỷ hệ cấp 3 thì có 1 lần game đứng hình; test `test_skill_fx.cjs` cũng thỉnh thoảng bị treo vì lỗi này. Đã chạy thử mọi hiệu ứng pixelfx mà game dùng, mỗi hiệu ứng với hàng trăm seed: chỉ có hiệu ứng này treo thật. Chiêu Quỷ hệ cấp 3 giờ mở đầu bằng hiệu ứng `implosion` trên mục tiêu thay cho xúc tu. Thư viện pixelfx đã vendor được giữ nguyên, không sửa.

## Nhiệm vụ phụ SQ001–SQ047

`side_quests.js` thêm 47 nhiệm vụ phụ của bảng nhiệm vụ (5 tuyến A–E). Nhiệm vụ phụ chạy song song với nhiệm vụ chính, nhận được nhiều cái cùng lúc, mỗi cái có tiến độ riêng. Tiến độ nằm trong bản lưu của game (cùng chỗ với nhiệm vụ chính).

- **Mở khóa (TRIAL):** bảng chỉ ghi cấp gợi ý, nên mỗi nhiệm vụ phụ mở khi xong một nhiệm vụ chính có cấp ngay dưới cấp gợi ý, và map của nó đã đi tới được. Ví dụ SQ032 (Lv. 12) mở sau MQ010, SQ001 (Lv. 32) mở sau MQ026, SQ047 mở sau MQ096.
- **Nhận nhiệm vụ:** người giao việc đứng trên map, có dấu **!** xanh trên đầu. Nhiệm vụ do đồ vật giao (rương cổ, bia đá, kính viễn vọng, bảng điều khiển…) thì có một điểm sáng xanh; bấm vào đó để bắt đầu.
- **Cách làm:** giống nhiệm vụ chính. Điểm sáng xanh là chỗ cần đến, điểm sáng cam là trận đánh. Có săn pet hoang dã theo số lượng, bắt sủng vật bất kỳ (SQ043), cầm cự theo lượt (SQ017, SQ040, SQ046) và đấu nhiều trận liền (SQ042, SQ044). Nhật ký ghi từng nhiệm vụ phụ đã mở, việc cần làm tiếp và phần thưởng.
- **Minigame trong bảng** (nhảy theo nhịp, chụp ảnh, hát Perfect, giải mã, QTE) đổi thành các điểm đi theo thứ tự. Mục "trong 2 phút", "trong 3 phút", "5 phút" đổi thành trận thường hoặc cầm cự theo lượt.
- **Phần thưởng:** EXP, kim tiền, vật phẩm, đá và thánh vật như nhiệm vụ chính. Có thêm:
  - **Hảo cảm với Lyra** (tuyến A), hiện trong Nhật ký.
  - **Sủng vật thưởng:** Khốc Tị Mộc Linh (SQ012), Tiểu Bạch Long (SQ013), Cá Kình khổng lồ (SQ015), Sa Vương Cuồng Bạo (SQ016), Hỏa Phượng Hoàng (SQ018), Tuyệt Đối Linh Độ Khuyển (SQ020), Tinh Vân Hạc (SQ022). Loài nào bảng ghi mà game không có thì dùng loài gần nhất.
  - **Chiêu mới cho Lôi Miêu:** Nhịp Bước Uyên Ương (SQ004, sức mạnh 220, 10 lần) và Lôi Bạo Cuồng Nộ (SQ017, sức mạnh 300, 6 lần). Hai con số này là TRIAL.
  - Thưởng chưa có trong game (trang phục, trang bị, danh hiệu có chỉ số, +% chỉ số vĩnh viễn, tốc độ tiến hóa) được thay bằng món gần nhất hoặc giữ thành vật phẩm cốt truyện; Nhật ký ghi rõ từng món.
- **Map:** dùng các map đã có, kể cả các map mới của Codex (Rừng Bậc Thang Ngọc Lục, Đài Quan Sát Lục Bảo, Đầm Phá San Hô Hồng, Hẻm Sông Đồng Đỏ, Phế Tích Thành Đồng). Đối thủ nhiệm vụ phụ là pet VQSV6 cùng hệ, tối đa cấp 70.
- **Test:** `tools/test_side_quests.cjs` kiểm tra khóa/mở theo nhiệm vụ chính, lưu và tải, thua trận rồi thử lại, và chơi trọn cả 47 nhiệm vụ phụ.

Sửa kèm: khi một hội thoại nhiệm vụ kết thúc rồi mở tiếp hội thoại, trận đánh hay cảnh kết, game không còn tự đưa về chế độ đi lại nữa. Trước đây cảnh **Đại kết cục** sau MQ096 bị bỏ qua vì lỗi này. Thông báo cuối các chương 1–4 cũng không còn ghi "chương sau chưa có".

## Cân bằng cấp đối thủ và EXP nhiệm vụ (TRIAL)

Bảng nhiệm vụ cho cấp 1–100, còn game chỉ tới cấp 70. EXP thưởng trong bảng cũng tăng khoảng 1,15 lần sau mỗi nhiệm vụ (MQ096 thưởng 50.000.000 EXP). Đo thử thì chỉ riêng EXP nhiệm vụ đã đưa Lôi Miêu lên cấp 70 ở MQ049, nên từ Chương 3 trở đi không còn lên cấp nữa. Cách cân bằng mới:

- **Cấp đối thủ nhiệm vụ** lấy theo cấp pet hoang dã của map nơi diễn ra trận (`customWildAreas`, dựng theo tuyến đường trong `tools/atlas_wild.py`). Đối thủ thường lấy cấp cao nhất của map, boss (không chạy được hoặc máu từ gấp 3) thêm 2 cấp, tối đa 70. Pet nhiệm vụ gặp khi đi trong map ra cấp trong khoảng cấp của map đó. Các trận riêng của Chương 1 (MQ001–MQ009, ở làng và rừng chưa có bảng pet) giữ nguyên.
- **EXP nhiệm vụ chính (MQ010–MQ096)** tính theo đường cong lên cấp của game (`expToReach`): mỗi nhiệm vụ cho khoảng một nửa số EXP cần để đi từ cấp map của nhiệm vụ này lên cấp map của nhiệm vụ sau, ít nhất bằng 35% một cấp. Phần còn lại đến từ đánh pet hoang dã. Chỉ riêng EXP nhiệm vụ thì Lôi Miêu lên cấp đều và chạm 70 ở nhiệm vụ cuối. Ví dụ: MQ010 7.800 EXP, MQ042 28.000 EXP, MQ096 200.000 EXP.
- **Nhiệm vụ phụ:** EXP bằng khoảng một cấp ở cấp map của nhiệm vụ. Sủng vật thưởng vào đội ở cấp của map đó.
- Dòng thưởng trong Nhật ký và thông báo hoàn thành hiện số EXP mới. Vật phẩm, đá và thánh vật giữ nguyên.
- Test `test_chapter1.cjs` kiểm tra cấp boss MQ086, cấp pet săn ở Quỹ đạo và việc EXP nhiệm vụ chỉ chạm cấp tối đa ở cuối truyện.

## Chỉ đường nhiệm vụ

Thế giới có hơn 40 map, nên game chỉ lối cho nhiệm vụ đang làm:

- **Dòng mục tiêu** ghi thêm `➜ đi lối …` khi chỗ cần đến nằm ở map khác. Đó là lối ra trên map hiện tại dẫn tới map đích theo đường ngắn nhất (tính theo số lần chuyển map, dựa trên các lối của map).
- **Trên sân:** lối cần đi sáng màu xanh nhạt và có mũi tên ➜. Một mũi tên nhỏ cạnh Kael luôn chỉ về phía cần đi: xanh nhạt khi phải sang map khác, vàng khi điểm cần đến ở ngay map này.
- **Bản đồ (nút Bản đồ):** chọn sẵn lối hoặc điểm nhiệm vụ; bấm **Chỉ đường** để vẽ vệt đường trên sân như trước.
- Áp dụng cho mọi nhiệm vụ chính (cả MQ001–MQ009 của Chương 1). Khi đã xong cốt truyện, chỉ đường theo nhiệm vụ phụ đang làm đầu tiên.
- Test `test_chapter1.cjs` đi theo chỉ đường từ làng tới map của từng nhiệm vụ MQ010–MQ096.

## Sửa lỗi nhân vật lòi ra ngoài map trên điện thoại

Trên điện thoại dọc, khung hình bị cắt và bị bảng tên vùng (trên) cùng cụm nút bấm (dưới) che. Trước đây camera không được nhìn quá mép map, nên khi Kael tới mép (ví dụ vào Mặt Trăng Đỏ từ lối phía trên) thì nhân vật nằm dưới bảng tên vùng, đầu lòi ra ngoài mép map hoặc bị cắt nửa người ở mép màn hình.

- `cameraOf` (chỉ khi màn hình dọc, trừ trong tiệm) cho camera nhìn quá mép map một khoảng (trên 95, dưới 105, hai bên 90 điểm ảnh của khung 480×360), phần ngoài map tô nền tối như các map nhỏ. Kael luôn nằm giữa bảng tên vùng và cụm nút. Trên máy tính hoặc khi xoay ngang, camera giữ như cũ.
- Khi vào map qua lối đặt ở hàng ô trên cùng, Kael xuất hiện lùi vào một hàng (nếu ô đó đi được), để đầu nhân vật không vẽ ra ngoài hình map.

## Sửa đồ họa mờ và vỡ trên điện thoại

- **Nguyên nhân 1:** sân chơi được vẽ vào khung 480×360 điểm ảnh rồi trình duyệt phóng to khoảng 6,5 lần lên màn hình điện thoại (độ phân giải ×3). Chữ vẽ trên sân bị vỡ hạt; trên trình duyệt làm mịn khi phóng to thì cả hình bị mờ. Giờ canvas sân chơi có `FIELD.k` điểm ảnh thật cho mỗi điểm ảnh logic (k = tỉ lệ hiển thị × devicePixelRatio, làm tròn, tối đa 6). Mọi thứ vẫn vẽ theo tọa độ 480×360 qua `setTransform(k)`, nên logic game không đổi. Hình pixel được phóng một lần theo số nguyên (khối điểm ảnh sắc nét) và chữ vẽ ở độ phân giải màn hình. Màn trận đấu vốn đã có cách tương tự (`DUEL.k`).
- **Nguyên nhân 2:** `tools/render_village_south.py` (phần làng phía nam) lưu `village-south.webp` và `village-south-chart.webp` dạng nén có mất chất lượng, làm nhòe nét pixel. Tool giờ lưu không mất chất lượng (`lossless=True`) và hai ảnh đã được dựng lại; file còn nhỏ hơn bản cũ.

## Dọn hiển thị trên điện thoại

Chạy thử các màn chính trên khung iPhone (hội thoại, trận đấu, menu, nhật ký, túi đồ, sủng vật, tiệm, bản đồ) và sửa:

- **Bia gỗ (MQ001):** ảnh gốc chỉ 18×13 điểm ảnh nhưng bị phóng to hơn 10 lần, thành vài khối vuông chiếm cả nửa sân trận. `drawFitted` giờ phóng tối đa 4 lần cho ảnh nhỏ dưới 24 điểm ảnh; sprite pet vẫn phóng theo số nguyên lớn nhất vừa khung như trước.
- **Chữ trên sân:** khi màn hình dọc, dòng gợi ý `E …` (bị cần điều khiển che) và tên vùng vẽ mờ trên sân (nằm sau bảng tên vùng) không vẽ nữa, vì nút tròn và bảng tên vùng đã hiện đúng nội dung đó. Trên máy tính vẫn hiện như cũ.
- **Lôi Miêu đi theo Kael ngoài sân:** trước đây vẽ từ ảnh thu nhỏ 24×21 (`loi-mieu-field.webp`) nên nét bị nhòe. Vì canvas sân giờ có nhiều điểm ảnh thật cho mỗi điểm ảnh logic, game vẽ thẳng hình gốc 94×82 (`loi-mieu.webp`), thu nhỏ có làm mịn, cùng kích thước như cũ. Ảnh 24×21 chỉ còn là ảnh dự phòng.

## Cửa hàng: tùy thân ở mọi nơi, hai tiệm bán hàng riêng (TRIAL)

- **Tùy thân cửa hàng** (nút trong menu, phím B) trước đây chỉ mở ở làng biên giới, nên từ Chương 2 trở đi không mua được cầu hay thuốc, trừ khi mua trong trận với giá gấp đôi. Giờ cửa hàng tùy thân mở ở mọi nơi Kael đi lại (theo cách của VQSV6), bán đủ danh mục với giá niêm yết. Trong trận vẫn giá gấp đôi như cũ.
- **Tiệm thuốc** chỉ bán đồ ăn, thuốc hồi phục, đá hồi sinh và Tị quái hàn (đạo cụ túi từ dòng 4 trở đi).
- **Tiệm bùa** bán cầu bắt pet, vật phẩm mang theo (bùa), đá tiến hóa, sách kỹ năng và vật phẩm VQSV5.
- Hai tiệm cộng lại đủ cả danh mục; bán lại đạo cụ ở đâu cũng được.
- Dòng gợi ý ở vùng pet hoang dã giờ chỉ tới Tùy thân cửa hàng trong menu.
- Test: `tools/test_shops.cjs`.

## Lôi Miêu tiến hóa thì con đi theo cũng đổi hình

Sau khi Lôi Miêu tiến hóa (ví dụ thành Thiểm Điện Miêu), con đi theo Kael ngoài sân dùng hình pixel của dạng mới, giống trong trận; quay mặt theo hướng Kael. Chưa tiến hóa thì vẫn dùng hình Lôi Miêu như cũ.

## Kael nét hơn ngoài sân

Hình Kael ngoài sân (`kael_field.png`, 32×40 mỗi tư thế) được thu nhỏ từ bộ hình gốc `kael_pixel_v2.png` (mỗi tư thế khoảng 260×330), nên mất mặt, khóa thắt lưng và khăn. `tools/pixel_art.py` giờ dựng thêm `kael_field_hd.webp`: cùng 16 tư thế nhưng 128×160 mỗi tư thế, lưu không mất chất lượng. Game vẽ bản HD ở đúng kích thước cũ (32×40 điểm ảnh logic) trên canvas độ phân giải cao, có làm mịn khi thu nhỏ; bản 32×40 vẫn giữ làm dự phòng.

NPC trong làng (trưởng làng, dược sĩ, dân làng, người gác) chỉ có hình 18–21×38 điểm ảnh và repo không có bản lớn hơn. Đã thử phóng kiểu Scale4x nhưng gần như không khác, nên giữ nguyên. Muốn NPC nét như Kael thì cần vẽ lại hình NPC ở độ phân giải lớn hơn.

## NPC đi lại ngẫu nhiên (TRIAL)

`ambient_npcs.js` cho một vài NPC đi lại quanh chỗ đứng của mình:

- **Làng biên giới:** Dân làng (NPC cũ) và ba người qua đường mới: Bà bán hoa, Thợ săn trẻ, Ông lão câu cá.
- **Bến cảng Ryan:** Thủy thủ, Cô bán cá. **Atlantis:** Cư dân Hải Tộc, Lính tuần Hải Tộc. **Lối vào Aethelgard:** Lữ khách tầng mây.
- Người qua đường dùng lại hình NPC sẵn có, mỗi người có 2 câu thoại; bấm Nói chuyện như NPC thường.
- **Cách đi:** cứ 1,7–4,7 giây bước một ô theo hướng ngẫu nhiên, không xa nhà quá 3 ô. Không bước vào ô bị chặn, ô lối ra hay ô của NPC khác. Chỉ bước vào ô thoáng (ít nhất 3 trong 4 ô xung quanh đi được), để không chắn lối hẹp. Đứng yên khi Kael ở trong 3 ô (để nói chuyện), khi đang hội thoại, trong trận và trong đêm tập kích MQ006–MQ008.
- NPC giao nhiệm vụ và người bán hàng vẫn đứng yên, để chỉ đường nhiệm vụ và test luôn tìm thấy họ.
- Test: `tools/test_ambient_npcs.cjs`.

## Vẽ lại NPC trong làng cho nét

Hình NPC cũ (`elder`, `herbalist`, `villager`, `scout` trong `assets/illustrations/`) chỉ có 18–22×38 điểm ảnh nên nhìn thô cạnh Kael. `tools/draw_npcs.py` vẽ lại cả bốn bằng code, bám theo đúng hình gốc từng mảng: cùng dáng người thon (đầu khoảng 1/3 chiều cao), cùng kiểu tóc, cùng trang phục và màu. Mỗi NPC vẽ ở cỡ gấp đôi hình gốc (36–44×76) thành pixel art sạch, mỗi lớp (tóc sau, chân, thân, tay, mặt, mái tóc, phụ kiện) có viền đậm riêng, có đổ bóng, mắt có mi và điểm sáng. Hình được lưu ở cỡ gấp 4 hình gốc (`*_hd.png`).

- **Người gác làng:** tóc xám bồng che trán, tay áo kem, gilê nâu có cúc, quần đỏ sẫm, ủng nâu.
- **Dược sĩ:** tóc hồng rất bồng có lọn xoăn hai bên và kẹp vàng, áo nâu cam có mảng vàng ở ngực, tạp dề vàng, viền ren kem, tất xám.
- **Dân làng:** tóc xanh xù rẽ ngôi giữa, mắt xanh, áo khoác nâu đỏ có lót be, thắt lưng.
- **Người gác:** khăn trùm xanh có đuôi dài bay sang trái, tóc nâu dài, khăn cổ cam, áo xanh pha nâu, váy quấn cam.

Đã thử hai cách tự động trước đó (phóng to hình gốc bằng thuật toán pixel art, và tách mảng màu hình gốc rồi tô lại) nhưng hình gốc quá nhỏ và nhiễu nên kết quả loang lổ, mất mặt; vì vậy vẽ tay bằng code.

Game dùng các hình `*_hd.png` cho NPC trong làng, NPC nhiệm vụ, nhiệm vụ phụ và người qua đường (vẫn vẽ cao 38 điểm ảnh như cũ). Hình gốc giữ nguyên trong repo. Chạy lại `python3 tools/draw_npcs.py` sau khi sửa hình trong tool.
