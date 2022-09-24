





//let Tile = {
//    gridSpot: null,
//    occupied: null,
//    meeple: null,
//    type: null,
//    rotation: null
//};
let dragged;
function createTile(tile){
    let div = document.createElement("div");
    div.className = "Tile";
   // elem.classList.add = tile.name;
    div.id = "startTile";
    deckOfCard.appendChild(div);
    div.addEventListener('dragstart',onDragStart);
}

function onDragStart(event) {

    dragged = event.target;
    event
      .dataTransfer
      .setData('text/plain', event.target.id);

    event
      .currentTarget
      .style
      .backgroundColor = 'yellow';
  }
  function onDragOver(event) {
    event.preventDefault();
  }
  function onDrop(event) {
    const id = event
      .dataTransfer
      .getData('text');
    const draggableElement = document.getElementById(id);
    event.target.appendChild(draggableElement);
    sendTileData(event);


    event
      .dataTransfer
      .clearData();

  }
 function sendTileData(event){
    let socketName = "/app/room"+roomId+"/getNewTile";
    stompClient.send((socketName),{},event.target.id);
    let spot = document.getElementById(event.target.id);
    console.log(spot)
    spot.removeEventListener('dragover',onDragOver);
    spot.removeEventListener('drop',onDrop);
    dragged.removeEventListener('dragstart',onDragStart);

  }
