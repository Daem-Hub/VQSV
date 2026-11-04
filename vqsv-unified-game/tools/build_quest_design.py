"""Prepare a reviewable quest list from the user's existing workbook."""
import os
from collections import Counter
from pathlib import Path
import openpyxl

ROOT = Path(__file__).resolve().parents[1]
SOURCE = Path(os.environ.get('VQSV_SOURCE_ROOT', r'D:\TOAN BO FILE\Download\game'))
WORKBOOK = SOURCE / 'He_Thong_Nhiem_Vu_VQSV_96_Chinh_47_Phu.xlsx'
wb = openpyxl.load_workbook(WORKBOOK, read_only=True, data_only=True)
main = list(wb['96 Nhiệm Vụ Chính (Main Quests)'].values)[1:]
side = list(wb['47 Nhiệm Vụ Phụ (Side Quests)'].values)[1:]
if len(main) != 96 or len(side) != 47 or len({r[0] for r in main+side}) != 143:
    raise ValueError('Unexpected quest counts or duplicate IDs')
for prefix, rows in [('MQ',main),('SQ',side)]:
    for i,row in enumerate(rows,1):
        if row[0] != f'{prefix}{i:03d}' or len(row) < 9 or not all(row[j] for j in (0,1,2,3,5,6,7,8)):
            raise ValueError(f'Incomplete or out-of-order quest row: {row}')
def clean(v):
    return str(v if v is not None else 'Chưa xác định').replace('|','\\|').replace('\n','; ').strip()
out = [
    '# Danh sách nhiệm vụ chính và phụ — VQSV hợp nhất',
    '',
    '**Trạng thái: bản thiết kế để duyệt, chưa phải danh sách đã triển khai.** Giữ đủ 96 nhiệm vụ chính và 47 nhiệm vụ phụ trong bảng nguồn, cùng ID để dễ đối chiếu. Tên NPC, tình tiết bổ sung, cấp, số lượng và phần thưởng trong bảng là **đề xuất của bảng nhiệm vụ**, không tự động trở thành canon hay thông số game hoàn chỉnh.',
    '',
    'Nguồn cốt truyện nền: `Kich_Ban_Cot_Truyen_VQSV_Tron_Bo.docx`. Nguồn danh sách và các chi tiết từng nhiệm vụ: `He_Thong_Nhiem_Vu_VQSV_96_Chinh_47_Phu.xlsx`. Không chỉnh sửa hai file gốc.',
    '',
    '## Các điểm cần chốt trước khi triển khai',
    '',
    '- **Đầu truyện:** MQ001–MQ005 thêm NPC, địa điểm và nhiệm vụ chưa có trong kịch bản; MQ002 để Kael tìm la bàn, trong khi truyện đã giới thiệu Kael đang có la bàn. Tên Elden, Bran, Mae là đề xuất từ bảng, chưa xác nhận là NPC gốc hay NPC đã chốt.',
    '- **Thứ tự vùng:** bảng đặt Hồ Băng (MQ010–012) trước Hỏa Sơn (MQ013–015), còn kịch bản kể Viêm Long trước Tuyết Y; cần chốt trình tự hoặc cho phép lựa chọn.',
    '- **Tên chương:** bảng nhiệm vụ chia Aethelgard và Mặt Trăng thành chương 3–5 để quản lý tiến trình; kịch bản dùng Chương 3 cho Aethelgard, Chương 4 mô tả cơ chế boss và Chương 5 là hậu chiến, lên đường. Đây là hai cách đánh số, không mặc định là cùng cấu trúc chương.',
    '- **Sau khi lên đường:** MQ082–MQ096 và các nhiệm vụ phụ ở Mặt Trăng thêm nội dung, trong đó có việc mẹ còn sống và kết cục của Thực Thể Thứ 0; kịch bản hiện dừng ở cảnh đoàn bay lên không gian. Giữ các nhiệm vụ này là đề xuất cần duyệt, không khẳng định là canon.',
    '- **Mốc mở nhiệm vụ phụ:** các cấp trong bảng chỉ là gợi ý, chưa đủ để xác định thời điểm mở, điều kiện trước/sau nhiệm vụ khác, nhiệm vụ bị bỏ lỡ hay tính khả dụng của hệ thống. Phải thiết kế điều kiện cụ thể trước khi lập trình.',
    '- **Mạch cảm xúc và pet:** SQ001–010 cần xét thời điểm quan hệ Kael–Lyra phát triển; SQ011–022 cần đối chiếu roster, nhánh tiến hóa và hệ thống thu phục. Các NPC/pet/vật phẩm chưa xác nhận đều là ứng viên nội dung.',
    '- **Boss:** MQ052–054 và MQ068–071 chia giai đoạn boss thành các dòng để theo dõi; mặc định đây là các bước của trận liền mạch, không bắt buộc tách thành các trận riêng. Thông số, phần thưởng và điều kiện sẽ cân bằng sau.',
    '',
    'Bản đang chơi mới có người gác làng, dấu vết, một trận thử và cảnh la bàn. Chưa có nhiệm vụ nào trong bảng dưới đây được xác nhận đã hoàn thiện trọn vẹn; không tự đánh dấu tương ứng với MQ001–MQ008.',
    '',
    '## Nhiệm vụ chính',
    '',
    'Cột **Nội dung / hoàn thành** và **thưởng** được giữ theo bảng nguồn để rà; cấp và thưởng đều chưa cân bằng. Những nhiệm vụ dẫn tới và sau Mặt Trăng được giữ dưới dạng đề xuất khi vượt quá kịch bản nền.',
    ''
]
groups=[('Chương 1 · Ryan',main[:20]),('Chương 2 · Hải Vực và Atlantis',main[20:42]),('Chương 3 · Aethelgard và Boran',main[42:60]),('Chương 4 theo bảng · Kính Giới và hành trình lên trời',main[60:80]),('Chương 5 theo bảng · Mặt Trăng — nội dung mở rộng cần duyệt',main[80:])]
def table(rows):
    lines=['| Mã | Nhiệm vụ | Khu vực · cấp đề xuất | Kích hoạt | Nội dung / hoàn thành | Phần thưởng đề xuất |', '| --- | --- | --- | --- | --- | --- |']
    for row in rows:
        code,name,group,place,level,trigger,summary,condition,reward=row[:9]
        lines.append(f'| {clean(code)} | {clean(name)} | {clean(place)} · {clean(level)} | {clean(trigger)} | {clean(summary)}. **Hoàn thành:** {clean(condition)} | {clean(reward)} |')
    return lines
for title,rows in groups:
    out += [f'### {title} ({len(rows)})','',*table(rows),'']
out += ['## Nhiệm vụ phụ','','Các tuyến dưới đây là nội dung bổ sung tùy chọn. Tên tuyến lấy theo bảng nguồn; trình tự mở, NPC, mức độ liên quan tới truyện và phần thưởng cần duyệt trước khi triển khai.','']
side_groups=[('Tuyến A · Kael và Lyra',side[:10]),('Tuyến B · Pet và tiến hóa',side[10:22]),('Tuyến C · Giải mã và khám phá',side[22:31]),('Tuyến D · Truy quét và giải cứu',side[31:41]),('Tuyến E · Huấn luyện sư',side[41:])]
for title,rows in side_groups:
    out += [f'### {title} ({len(rows)})','',*table(rows),'']
out += ['## Thứ tự thực hiện đề xuất','','1. Duyệt các điểm xung đột và cốt truyện sau Mặt Trăng trước khi khóa danh sách.','2. Chọn một nhóm nhiệm vụ đầu làng để chuyển thành nhiệm vụ có thể chơi, gắn NPC, điều kiện hoàn thành và bản lưu.','3. Làm tiếp theo từng khu vực; cân bằng cấp, số lượng và phần thưởng sau khi hệ chiến đấu, pet và kho đồ hoạt động.','']
target=ROOT/'DANH_SACH_NHIEM_VU.md'
target.write_text('\n'.join(out),encoding='utf8')
print(f'Wrote {target}: {len(main)} main, {len(side)} side; {len(out)} lines')
