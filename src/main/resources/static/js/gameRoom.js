var sorcket;

function makeConnection(userId) {
    var ipURL = "ws://localhost:10000/" + "webSocket/1/" + userId;
    try {
        socket=new WebSocket(ipURL);
    }catch(e) {
        alert("error happend");
        close;
    }
    socket.onopen = sOpen;
    socket.onerror = sError;
    socket.onmessage= sMessage;
    socket.onclose= sClose;
}

function sOpen(){
    enterRoom();
    alert('connect success!');
}
function sError(e){
    alert("error " + e);
}
function sMessage(msg){
    //top.location.reload();
}
function sClose(e){
    alert("connect closed:" + e.code);
}
function doSend(msg){
    socket.send(msg);
}

function enterRoom(){
    doSend("enter");
}

function Close(){
    socket.close();
}