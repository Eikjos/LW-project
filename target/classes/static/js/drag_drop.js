function dragStart(e) {
    e.dataTransfer.setData('text', e.target.id);
}

function dragOver(e) {
    e.preventDefault();
}

function drop(e) {
    e.preventDefault();
    const id = e.dataTransfer.getData('text');
    const draggableElement = document.getElementById(id);
    e.target.appendChild(draggableElement);
    const id_column = e.target.id;
    fetch(`/tasks/${id.slice(4)}/column/${id_column.slice(6)}`, {method: "PUT"}).then(() => location.reload())
}
