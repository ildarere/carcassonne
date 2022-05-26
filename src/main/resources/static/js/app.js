document.addEventListener("DOMContentLoaded", ()=>ready());


var stompClient = null;
let roomId =null;
function ready() {
roomId=document.getElementsByTagName('body')[0].id ;
connect();
setTimeout(function(){
    sendName();
}, 2000);

}

function connect() {

    let socketName = '/room'+roomId+'/hello';
    var socket = new SockJS(socketName);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        //setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe(('/topic/room'+roomId+'/connected'), function (greeting) {
        console.log(greeting)
            addInPlayerList(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    let socketName = "/app/room"+roomId+"/hello";
    stompClient.send((socketName),{}, "hi");
}

function  addInPlayerList(message) {

    let div = document.createElement("div");
    div.className = "field1";
    div.id = message.id;
    let innerDiv = document.createElement("div");
    innerDiv.id = "redPlayer";
    innerDiv.innerHTML = message.name;
    div.innerHTML='<img class="imgGame" src="/img/avatar.jpg">';
    div.appendChild(innerDiv);
    inGameMenu.appendChild(div);

}

function ping(){
sendName();
}

