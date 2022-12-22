//document.onload = drag_drop;
// Fonction qui s'exécute lorsqu'on commence à faire glisser un div
function dragStart(e) {
    // Stock l'ID de l'élément en train d'être glissé dans le dataTransfer
    e.dataTransfer.setData('text', e.target.id);
}

// Fonction qui s'exécute lorsqu'on fait glisser un div sur le div de destination
function dragOver(e) {
    // Annule l'action par défaut (interdiction de faire glisser des éléments dans le navigateur)
    e.preventDefault();
}

// Fonction qui s'exécute lorsqu'on lâche un div sur le div de destination
function drop(e) {
    // Annule l'action par défaut (interdiction de faire glisser des éléments dans le navigateur)
    e.preventDefault();
    // Récupère l'ID de l'élément en train d'être glissé à partir du dataTransfer
    const id = e.dataTransfer.getData('text');
    // Récupère la référence à l'élément en train d'être glissé à partir de son ID
    const draggableElement = document.getElementById(id);
    // Ajoute l'élément en train d'être glissé comme enfant du div de destination
    e.target.appendChild(draggableElement);
    const id_column = e.target.id;
    fetch(`/tasks/${id.slice(4)}/column/${id_column.slice(6)}`, {method: "PUT"}).then(() => location.reload())
}
