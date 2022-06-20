document.addEventListener("DOMContentLoaded", ()=>ready());


var stompClient = null;
let roomId =null;
let currentUserId = null;
function ready() {
roomId=document.getElementsByTagName('body')[0].id ;
connect();
setTimeout(function(){
    sendName();
}, 2000);
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

function ping(){
sendName();
}

