var sorcket;

function makeConnection(userId) {
    var ipURL = "ws://localhost:10000/" + "webSocket/0/" + userId;
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
    alert('connect success!');
}
function sError(e){
    alert("error " + e);
}
function sMessage(msg){
    alert('server says:' + msg);
}
function sClose(e){
    alert("connect closed:" + e.code);
}
function Send(){
    socket.send(document.getElementById("msg").value);
}

function Close(){
    socket.close();
}