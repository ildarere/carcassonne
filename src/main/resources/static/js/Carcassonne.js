let player = null;
let playerIndex= null;
let StartTile = true;
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
let CurrentMeeple = {
    type: null,
    gridDirection: null
};
const Rotation = { UP: 0, TILTED_RIGHT: 1, UPSIDE_DOWN: 2, TILTED_LEFT: 3 };
let GridDirection = { NORTH_WEST: 0, NORTH: 1, NORTH_EAST: 2, WEST: 3, CENTER: 4, EAST: 5, SOUTH_WEST: 6, SOUTH: 7, SOUTH_EAST: 8 };

function startGame(){

    playerIndex =0;
    player = userList[playerIndex];
    if(player == currentUserId){
    let socketName = "/app/room"+roomId+"/gameStart";
        stompClient.send((socketName),{},roomId);
    }
     setTimeout(function(){stateMachine(); }, 2000);
}
function nextTurn(){
    if(player == currentUserId){
        document.querySelectorAll('td').forEach(e=> {e.innerHTML = '<img class="meeple"  src="/img/meeple/empty.png">' ;});
        TileSpot.removeChild(document.getElementById(CurrentTile.type));
    }

    playerIndex = ++playerIndex % 2;
    player = userList[playerIndex]
    sendRequest('POST', "/room"+roomId+"/nextTurn");

    stateMachine();
}

function meeplePreview(){
     sendRequest('POST', "/room"+roomId+"/requestManingState")
          .then(data => {
                console.log(data)
          })
          .catch(err => console.log(err))
          sendRequest('POST', "/room"+roomId+"/getMeeplesSpot")
          .then(data => {
                    let meepleSpots = data.slice(1, data.length-1).replaceAll('"', '').toLocaleLowerCase().split(",");
                    console.log(meepleSpots);
                    let index = 0;
                    document.querySelectorAll('td').forEach(e=> {
                        if(meepleSpots[index]!="empty"){
                            e.innerHTML = '<img class="meeple"  src="/img/meeple/'+meepleSpots[index]+'.png">' ;
                            e.addEventListener('click',requestPlaceMeeple);
                        }
                    	index++;
                    });
                    index=0;
                    for (let dir in GridDirection) {
                        GridDirection[dir] = meepleSpots[index];
                        index++;
                    }
          })
          .catch(err => console.log(err))
}

function placeTileBySpot(data1){
    if(data1!=null){
        data = data1.split("//");
        spot= data[0].split("%");
        highlight = data[1].split("@");
        highlightByCoord(highlight);
        let div = document.createElement("div");
        div.className = "Tile";
         CurrentTile.X =spot[1];
            CurrentTile.Y =spot[2];
        div.innerHTML='<img class="tilesOnMap"  src="/img/tiles/'+spot[0]+Rotation[spot[3]]+'.png">';
        document.getElementById(spot[1]+'%'+spot[2]).appendChild(div);
        if(StartTile == false){
            meeplePreview();
        }
        StartTile = false;
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

    coords= this.id.split("%");
    CurrentTile.X =coords[0];
    CurrentTile.Y =coords[1];
    let socketName = "/app/room"+roomId+"/isPlaceTileOnSpot";
    stompClient.send((socketName),{},JSON.stringify(CurrentTile));
}
function requestPlaceMeeple(){


    let socketName = "/app/room"+roomId+"/isPlaceMeeple";
    stompClient.send((socketName),{},this.id);


}
function removeMeeple(pos){
if(pos != null){

    let position = pos.slice(1, pos.length-1).split("@");
    console.log(position)
    for(let coord in position){
        let sNode =document.getElementById(coord)
        if(sNode!=null){
        sNode.removeChild(sNode.lastChild);
        }


    }
    let socketName = "/app/room"+roomId+"/Score";
    stompClient.send((socketName),{});
}


}
function placeMeeple(position){
    let meepleImg = document.createElement("img");
    meepleImg.classList.add("meeple");
    meepleImg.classList.add("meeple-pos-"+position);
    meepleImg.src  ='/img/meeple/'+GridDirection[position]+'.png';
    document.getElementById(CurrentTile.X+'%'+CurrentTile.Y).appendChild(meepleImg);
    socketName = "/app/room"+roomId+"/processGridPatterns";
    stompClient.send((socketName),{});
    nextTurn();

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