'use strict';


    let roomId =document.getElementsByTagName('body')[0].id ;

    const ws = new WebSocket("ws://localhost:8080/pong");
    ws.addEventListener('open', function (event) {
        action('open connection');
    });

    ws.addEventListener('message', function (event) {
       action(event.data);
    });





function action(message){
console.log(message);
   test.innerHTML = message;
    let newP = document.createElement('p');
    newP.appendChild(document.createTextNode(message));
}
function ping(){
    let message = "user connected";
    action('send: ' + message);
    ws.send(message);
}