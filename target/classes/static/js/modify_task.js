function show_modal(e) {
    const id = e.target.id.slice(4);
    const modal = document.getElementById(id);
    modal.style.display = 'block';
}

function close_modal(e) {
    const id = e.target.id.slice(4);
    const modal = document.getElementById(id);
    modal.style.display = 'none';
}