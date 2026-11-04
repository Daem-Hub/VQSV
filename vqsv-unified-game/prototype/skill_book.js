(() => {
  const book = document.getElementById('skillBook');
  const search = document.getElementById('skillSearch');
  const source = document.getElementById('skillSource');
  const list = document.getElementById('skillList');
  const detail = document.getElementById('skillDetail');
  const count = document.getElementById('skillCount');
  const atlas = document.getElementById('effectAtlas');
  const sequence = document.getElementById('effectSequence');
  const preview = document.getElementById('effectPreview');
  const data = window.VQSVBootstrap?.originalSkills || { records: [], effects: [] };
  let selected = data.records[0]?.id, timer = null;
  const normalized = s => String(s || '').normalize('NFD').replace(/[\u0300-\u036f]/g, '').replace(/[đĐ]/g, 'd').toLowerCase();
  function node(tag, text, cls) { const el = document.createElement(tag); if (text !== undefined) el.textContent = text; if (cls) el.className = cls; return el; }
  function stop() { if (timer !== null) clearTimeout(timer); timer = null; }
  function render() {
    const query = normalized(search.value.trim());
    const visible = data.records.filter(s => (source.value === 'all' || source.value === s.game) && normalized([s.name, s.description, s.source_label, s.id].join(' ')).includes(query));
    if (!visible.some(s => s.id === selected)) selected = visible[0]?.id;
    list.replaceChildren(); count.textContent = `${visible.length} kỹ năng`;
    for (const skill of visible) {
      const button = node('button', undefined, 'skill-row'); button.type = 'button'; button.setAttribute('aria-pressed', String(skill.id === selected));
      button.append(node('span', skill.name), node('small', `${skill.game} · #${skill.source_index}`));
      button.addEventListener('click', () => { selected = skill.id; render(); }); list.append(button);
    }
    const skill = visible.find(s => s.id === selected); detail.replaceChildren();
    if (!skill) { detail.append(node('p', 'Không tìm thấy kỹ năng phù hợp.')); return; }
    detail.append(node('h3', skill.name), node('p', `${skill.game} · mã nguồn ${skill.source_index}`));
    if (skill.source_label) detail.append(node('p', skill.source_label));
    if (skill.description) detail.append(node('p', skill.description));
    if (skill.max_uses !== undefined) detail.append(node('p', `Số lần tối đa trong dữ liệu gốc: ${skill.max_uses}`));
    if (skill.effect_source_rows) {
      const rows = skill.effect_source_rows;
      const same = JSON.stringify(rows.vqsv6_original) === JSON.stringify(rows.fix_2_7);
      detail.append(node('p', `Có dòng hiệu ứng trong cả VQSV6 và FIX 2.7 (${same ? 'giống nhau' : 'khác nhau'}). Đang giải cách phát hoạt ảnh.`, 'skill-note'));
    }
    detail.append(node('p', skill.note, 'skill-note'));
  }
  function play() {
    stop(); const group = data.effects[Number(atlas.value)]; const frames = group?.sequences[Number(sequence.value)] || [];
    if (!frames.length) { preview.removeAttribute('src'); return; }
    let index = 0;
    function frame() { const item = frames[index]; preview.src = item.image; index = (index + 1) % frames.length; timer = setTimeout(frame, Math.max(1, item.duration_ticks) * data.frame_duration_ms_per_tick); }
    frame();
  }
  function chooseAtlas() {
    const group = data.effects[Number(atlas.value)]; sequence.replaceChildren();
    (group?.sequences || []).forEach((frames, i) => { const opt = node('option', `Chuỗi ${i + 1} · ${frames.length} khung`); opt.value = String(i); sequence.append(opt); });
    sequence.value = '0'; play();
  }
  data.effects.forEach((effect, i) => { const opt = node('option', `Nhóm s${i}`); opt.value = String(i); atlas.append(opt); });
  search.addEventListener('input', render); source.addEventListener('change', render);
  atlas.addEventListener('change', chooseAtlas); sequence.addEventListener('change', play);
  document.getElementById('closeSkillBook').addEventListener('click', () => book.close());
  book.addEventListener('close', stop);
  window.openSkillBook = () => { if (!book.open) { book.showModal(); render(); atlas.value = '0'; chooseAtlas(); search.focus(); } };
})();
