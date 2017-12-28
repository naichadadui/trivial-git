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

function initPlayerCards(playerList){
    var cardsList=document.getElementById("player_cards");
    for(var i=0;i<playerList.length;i++){
        var div1=document.createElement("div");
        div1.className="card";
        var div2=document.createElement("div");
        div2.className="card-image";
        var img1=document.createElement("img");
        img1.src="/images/players/player"+(i+1)+".jpg";
        div2.appendChild(img1);
        var div3=document.createElement("div");
        div3.className="card-body";
        var div4=document.createElement("div");
        div4.className="card-date";
        var time1=document.createElement("time");
        time1.innerHTML=playerList[i].playerName;
        div4.appendChild(time1);
        var div5=document.createElement("div");
        div5.className="card-title";
        var div6=document.createElement("div");
        div6.className="card-exceprt";
    }
}

