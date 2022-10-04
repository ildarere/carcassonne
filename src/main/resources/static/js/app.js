document.addEventListener("DOMContentLoaded", ()=>ready());

var gameStart = false;
var stompClient = null;
let roomId =null;
const roomSize = 2;
let currentUserId = null;
let currentUserName = null;
const userList = [];
function ready() {
    for(let x=1; x<40; x++) {
    let div = document.createElement("div");
    div.className = "field";
    div.id = "field_" + x;
    document.querySelector('.allField').appendChild(div);
    for(let y=1; y<40; y++) {
        let parent = document.querySelector('#field_' + x)
        let div = document.createElement("div");
        div.id = y+"%"+x ;
        div.classList.add("tile");

        parent.appendChild(div);


    }
    }
    rotateLeft.addEventListener('click', rotateTileLeft);
  //  rotateRight.addEventListener('click', rotateTileRight);
    roomId=document.getElementsByTagName('body')[0].id ;
    connect();
    setTimeout(function(){sendName(); }, 2000);
    disconnectBtn.addEventListener('click',()=>{disconnect()});

}

function connect() {

    let socketName = '/room'+roomId+'/hello';
    let socket = new SockJS(socketName);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        //setConnected(true);

        stompClient.subscribe(('/topic/room'+roomId+'/connected'), function (greeting) {
            addInPlayerList(JSON.parse(greeting.body));
        });
        stompClient.subscribe(('/topic/room'+roomId+'/userDisconnected'), function (greeting) {
            disconnectPlayer(JSON.parse(greeting.body));
        });
        stompClient.subscribe(('/topic/room'+roomId+'/chatGetMsg'), function (greeting) {
            addMsg(JSON.parse(greeting.body));
        });
        stompClient.subscribe(('/topic/room'+roomId+'/getTile'), function (greeting) {
            createTile(JSON.parse(greeting.body));
        });
        stompClient.subscribe(('/topic/room'+roomId+'/placeTile'), function (greeting) {
            placeTileBySpot(greeting.body);
        });
        stompClient.subscribe(('/topic/room'+roomId+'/placeMeeple'), function (greeting) {
            placeMeeple(greeting.body);
        });
        stompClient.subscribe(('/topic/room'+roomId+'/removeMeeples'), function (greeting) {
            removeMeeple(greeting.body);
        });
        stompClient.subscribe(('/topic/room'+roomId+'/updateScores'), function (greeting) {
            updateScore(greeting.body);
        });
    });
}
function updateScore(data){
let a = 0;
let scores = data.slice(1, data.length-1).replaceAll('"', '').toLocaleLowerCase().split(",");
console.log(scores +"saasasasas");
    for(let s of scores){
        let score =  document.getElementById("Score"+userList[a])
        score.innerHTML="Score: "+s;
        a++;
    }

}
function disconnectPlayer(id){
 document.getElementById('User' + id).remove();
 let index = userList.indexOf(id);
  if (index !== -1) {
    userList.splice(index, 1);
  }
  if(userList.length<roomSize && gameStart){
    gameEnd();
  }

}

function gameEnd(){
gameStart= false;

console.log("game END")
}
function disconnect() {
    let socketName = "/app/room"+roomId+"/userDisconnected";
    stompClient.send((socketName),{}, currentUserId);
    if (stompClient !== null) {
        stompClient.disconnect();
    }

    window.location.replace("/");
}

function sendName() {
    let socketName = "/app/room"+roomId+"/hello";
    stompClient.send((socketName),{}, "hi");
}

function  addInPlayerList(users) {
    console.log(users);
    for (const user of users){
        if(currentUserId==null){
            currentUserId =user.id;
            currentUserName = user.name;
        }

        if(document.getElementById("User" + user.id)==null){
            userList.push(user.id);
            let div = document.createElement("div");
            div.className = "field1";
            div.id ="User" + user.id;
            let innerDiv = document.createElement("div");
            innerDiv.id = "redPlayer";
            innerDiv.innerHTML = user.name;
             let innerDiv2 = document.createElement("div");
             innerDiv2.id = "Score"+ user.id;
             innerDiv2.innerHTML = "Score: 0"
            div.innerHTML='<img class="imgGame" src="/img/avatar.jpg">';
            innerDiv.appendChild(innerDiv2);
            div.appendChild(innerDiv);
            inGameMenu.appendChild(div);
        }
    }
    userList.sort((a, b) => a - b);

    if(userList.length == roomSize){
        gameStart = true;
        startGame();
    }
}
function addMsg(message) {
    if(message.authorId == currentUserId){
        return;
    }else{
        let messageText = message.authorName + ": "+ message.message;
        document.querySelector('.conversation__body').insertAdjacentHTML('beforeend', `<div class="conversation__bubble conversation__bubble--left"><p class="conversation__text">${messageText}</p></div>`);
    }

   };

const addMessageToConversation = function() {
	let messageText = document.querySelector('.conversation__write').value;
	if ( messageText.trim().length == 0 ) {
		return false;
	} else {
	    let MSG ={};
	    MSG.authorId = currentUserId;
	    MSG.message = messageText;
	    MSG.roomId = roomId;
	    MSG.authorName = currentUserName;

	    let socketName = "/app/room"+roomId+"/chatSendMsg";

        stompClient.send((socketName),{}, JSON.stringify(MSG));
		document.querySelector('.conversation__body').insertAdjacentHTML('beforeend', `<div class="conversation__bubble conversation__bubble--right"><p class="conversation__text">${messageText}</p></div>`);
		document.querySelector('.conversation__write').value = '';
		document.querySelector('.conversation__write').focus();
	}
};

document.querySelectorAll('.conversation__info, .btn--close').forEach(function(e) {
	e.addEventListener('click', function() {
		document.querySelector('.mask').classList.toggle('minimize');
	});
});
document.querySelector('.btn--send').addEventListener('click', addMessageToConversation);
document.querySelector('.conversation__write').addEventListener('keydown', function(e) {
	if (e.key == 'Enter') {
		addMessageToConversation();
	} else {
		return false;
	}
});
