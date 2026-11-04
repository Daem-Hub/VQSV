# Kỹ năng và hiệu ứng nguồn: kết quả trích xuất

## Kỹ năng

- VQSV5: `data/data.d`, hai bảng văn bản 49 dòng chứa nhãn và tên kỹ năng; bảng số tương ứng 49 dòng được giữ nguyên. Chưa gán các cột số cho công dụng cụ thể hoặc cho từng pet.
- VQSV6: bảng 1 `data/script/db.mid` có 70 dòng; màn `game/k.java` hàm `h(i)` tra tên qua cột 1, mô tả qua cột 2 và số lần tối đa qua cột 5. Các trường số khác giữ nguyên. Bản `db.mid` trong ZIP trùng với `vqsv-6.jar` hiện dùng.
- Các dòng kỹ năng nguồn không tự thay thế kỹ năng thử trong trận mở đầu: cần giải quy tắc học, công thức và lựa chọn pet trước khi đưa vào chiến đấu.

## Hiệu ứng

- Sáu cặp `data/battle/s0..s5.data` + PNG VQSV5 đã giải metadata sprite, ghép thành các tư thế và 32 chuỗi có thể xem. Nhịp 60 ms/tick là ước lượng. Chưa xác minh 32 chuỗi này bao phủ mọi hiệu ứng chiến đấu hoặc mối liên hệ giữa chuỗi với từng kỹ năng.
- Thư mục `reference_vqsv_fix_2_7/effect_sources` lưu 651 file để nghiên cứu: sprite và ảnh VQSV6 từ ZIP cùng tất cả tài nguyên `data/battle` của VQSV5. Đây là tập tài nguyên **ứng viên**, không phải danh sách khẳng định đều là hiệu ứng kỹ năng. `manifest.json` lưu đường dẫn nguồn và SHA-256.
- Đã giải định dạng sprite của 15 nhóm hiệu ứng actor do fallback ánh xạ từ mã 20–34: đọc crop, thành phần, phép biến đổi và chuỗi khung của FIX 2.7. Sprite `266`, trạng thái 0 được fallback gọi bởi kỹ năng 40 “Điện giật”; đã dùng chuỗi này trong trận thử. Vị trí và thời gian phát là điều chỉnh prototype, chưa phục hồi đầy đủ chuỗi sự kiện, hiệu ứng đặc biệt hoặc animation mọi kỹ năng.
- Từ `vqsv-libgdx-fallback.js` anh cung cấp, `VqsvBattleRuntime` gọi `VqsvBattleAnimationTables.effectRow(skillId)`; `VqsvBattleAnimationTables.load()` đọc `effect.mid`. Đã nối 70 dòng hiệu ứng thô theo chỉ số kỹ năng và giữ song song bản gốc và FIX 2.7 trong `asset_review/vqsv6_effect_rows.json`; 61/70 dòng khác nhau. Đây là bằng chứng từ bản rebuild, chưa chứng minh cách dựng lại toàn bộ animation hoặc quy tắc trong game gốc.
- File TXT là một đoạn lưu Base64 bị cắt: giải mã được 3.072 byte, kết thúc giữa dòng; khai báo 108 actor nhưng có 51 dòng actor. Không nhập nó thành save vì sẽ làm mất dữ liệu hoặc sai trạng thái.

## Dựng lại

Đặt `VQSV_SOURCE_ROOT` trỏ tới thư mục chứa `vqsv5.jar`, `vqsv-6.jar`, thư mục dữ liệu đã trích và `VQSV_FIX_2.7.zip`, sau đó chạy:

```
python tools/build_original_skill_catalog.py
python tools/collect_skill_effect_sources.py
python tools/analyze_fallback_reference.py
python tools/decode_vqsv6_skill_sprites.py
python tools/build_prototype_bootstrap.py
node tools/test_original_skills.cjs
```

Sổ kỹ năng trong prototype hỗ trợ tìm, lọc và xem chuỗi VQSV5. Nó hiện là thư viện tra cứu; chưa phục hồi toàn bộ cơ chế, animation kỹ năng hay gán chiêu vào pet.
