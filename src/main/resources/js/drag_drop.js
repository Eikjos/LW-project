// Récupère les références aux divs que vous voulez rendre draggables
const collection = document.getElementsByClassName('drag_drop_task');

// Ajoute l'événement 'dragstart' aux divs
for (let i = 0; i < collection.length; i++) {
    collection[i].addEventListener('dragstart', dragStart);
}

// Fonction qui s'exécute lorsqu'on commence à faire glisser un div
function dragStart(e) {
    // Stock l'ID de l'élément en train d'être glissé dans le dataTransfer
    e.dataTransfer.setData('text/plain', e.target.id);
}

// Récupère la référence au div où vous voulez faire glisser les autres divs
const dropZone = document.getElementsByClassName('drag_drop_target');

// Ajoute l'événement 'dragover' et 'drop' aux div de destination
for (let i = 0; i < dropZone.length; i++) {
    dropZone[i].addEventListener('dragover', dragOver);
    dropZone[i].addEventListener('drop', drop);
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
    const id = e.dataTransfer.getData('text/plain');

    // Récupère la référence à l'élément en train d'être glissé à partir de son ID
    const draggableElement = document.querySelector('#' + id);

    // Ajoute l'élément en train d'être glissé comme enfant du div de destination
    e.target.appendChild(draggableElement);
}
