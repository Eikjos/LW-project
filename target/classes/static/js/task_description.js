function show_description(e) {
    const id = "div_"+e.target.id
    const desc = document.getElementById(id);
    desc.style.display = 'block';
}

function close_description(e) {
    const id = "div_"+e.target.id
    const desc = document.getElementById(id);
    desc.style.display = 'none';
}