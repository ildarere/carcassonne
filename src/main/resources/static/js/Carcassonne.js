let player = null;
let playerIndex= null;
let CurrentTile = {
    type: null,
    X: null,
    Y: null,
    occupied: null,
    meeple: null,
    type: null,
    rotation: null,
    maxRotation: null,
    numOgLRotation: 0,
    numOgRRotation: 0
};


const Rotation = { UP: 0, TILTED_RIGHT: 1, UPSIDE_DOWN: 2, TILTED_LEFT: 3 };

function startGame(){
    let socketName = "/app/room"+roomId+"/gameStart";
    stompClient.send((socketName),{},roomId);
    playerIndex =0;
    player = userList[playerIndex];
     setTimeout(function(){stateMachine(); }, 2000);
}
function nextTurn(){
    playerIndex++;
    player = userList[playerIndex]
//    stateMachine();
}
function placeTileBySpot(data1){
    if(data1!=null){
        data = data1.split("//");
        spot= data[0].split("%");
        highlight = data[1].split("@");
        highlightByCoord(highlight);
        let div = document.createElement("div");
        div.className = "Tile";
        div.innerHTML='<img class="tilesOnMap"  src="/img/tiles/'+spot[0]+Rotation[spot[3]]+'.png">';
        document.getElementById(spot[1]+'%'+spot[2]).appendChild(div);
        if(player != null){
        placeMeeple();
        }
    }
}
function rotateTileLeft(){
    CurrentTile.numOgLRotation+=1;
    CurrentTile.rotation= ((((CurrentTile.rotation-1) % 4) + 4) % 4);
    document.querySelector("#"+CurrentTile.type).innerHTML = '<img class="tilesOnMap"  src="/img/tiles/'+CurrentTile.type+CurrentTile.rotation+'.png">';
}
function highlightByCoord(coordinates){
    if(coordinates!=null){
        for(const coordinate of coordinates){
            if(coordinate!=""){
                console.log(coordinate);
                emptySpot = document.getElementById(coordinate);
                emptySpot.classList.add("highlightTile");
                emptySpot.addEventListener('click',placeTile);
//                emptySpot.addEventListener('dragover',onDragOver);
//                emptySpot.addEventListener('drop',onDrop);
            }
        }

    }
}

function placeTile(){
    console.log(this);
    coords= this.id.split("%");
    CurrentTile.X =coords[0];
    CurrentTile.Y =coords[1];
    let socketName = "/app/room"+roomId+"/isPlaceTileOnSpot";
    stompClient.send((socketName),{},JSON.stringify(CurrentTile));
}
function placeMeeple(){

}
function stateMachine(){
    if(gameStart == true && player == currentUserId){
        getTile();
    }
}
function getTile(){

    sendRequest('POST', "/room"+roomId+"/getNewTileForPlayer")
      .then(data => {

          inf =  data.split("%");
          console.log(data)
          CurrentTile.type = inf[0];
          CurrentTile.maxRotation = inf[1];
          CurrentTile.rotation = Rotation.UP;
          let div = document.createElement("div");
          div.className = "Tile";
          div.id = inf[0];
          div.innerHTML='<img class="tilesOnMap"  src="/img/tiles/'+inf[0]+Rotation.UP+'.png">';
          TileSpot.appendChild(div);
      })
      .catch(err => console.log(err))

}

function sendRequest(method, url, body = null) {
  const headers = {
    'Content-Type': 'application/json'
  }

  return fetch(url, {
    method: method,
    body: JSON.stringify(body),
    headers: headers
  }).then(response => {
    if (response.ok) {
      return response.text()
    }

    return response.json().then(error => {
      const e = new Error('Что-то пошло не так')
      e.data = error
      throw e
    })
  })
}