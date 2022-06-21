document.addEventListener("DOMContentLoaded", ()=>ready());


var stompClient = null;
let roomId =null;
let currentUserId = null;
let currentUserName = null;
function ready() {
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
        console.log('Connected: ' + frame);
        stompClient.subscribe(('/topic/room'+roomId+'/connected'), function (greeting) {
        console.log(greeting)
            addInPlayerList(JSON.parse(greeting.body));
        });
        stompClient.subscribe(('/topic/room'+roomId+'/userDisconnected'), function (greeting) {
                console.log(greeting)
                disconnectPlayer(JSON.parse(greeting.body));
                });
        stompClient.subscribe(('/topic/room'+roomId+'/chatGetMsg'), function (greeting) {
                console.log(greeting)
                addMsg(JSON.parse(greeting.body));
            });
    });
}

function disconnectPlayer(id){
 document.getElementById('User' + id).remove();
}
function disconnect() {
    let socketName = "/app/room"+roomId+"/userDisconnected";
    stompClient.send((socketName),{}, currentUserId);
    if (stompClient !== null) {
        stompClient.disconnect();
    }

    console.log("Disconnected");
    window.location.replace("/");
}

function sendName() {
    let socketName = "/app/room"+roomId+"/hello";
    stompClient.send((socketName),{}, "hi");
}

function  addInPlayerList(message) {
    if(currentUserId==null){
    currentUserId =message.id;
    currentUserName = message.name;
    }
    let div = document.createElement("div");
    div.className = "field1";
    div.id ="User" + message.id;
    let innerDiv = document.createElement("div");
    innerDiv.id = "redPlayer";
    innerDiv.innerHTML = message.name;
    div.innerHTML='<img class="imgGame" src="/img/avatar.jpg">';
    div.appendChild(innerDiv);
    inGameMenu.appendChild(div);

}
function addMsg(message) {
    if(message.authorId == currentUserId){
        console.log(message)
        console.log("same ID")
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
