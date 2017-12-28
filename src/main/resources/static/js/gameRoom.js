
var userID;
function makeConnection(userId) {
    userID=userId;
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
}
function sError(e){
    alert("error " + e);
}
function sMessage(msg){
    //top.location.reload();
    var json=JSON.parse(msg.data);
    var playerList=json.players;
    var isAllReady=true;

    for(var i=0;i<playerList.length;i++){
        $("#name"+(i+1)).html(playerList[i].playerName);
        $("#winRate"+(i+1)).html("胜率"+playerList[i].user.winRate+"%");
        $("#player"+(i+1)).show();
        if(!playerList[i].isReady)
            isAllReady=false;
        if(i!=0&&!playerList[i].isReady&&userID==playerList[i].user.userId){
            $("#buttonP").html("Ready");
            $("#buttonP").show();
        }
    }

    if(userID==playerList[0].user.userId&&isAllReady&&playerList.length>=2)
    {
        $("#buttonP").html("Start");
        $("#buttonP").show();
    }

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

function ready(){
    doSend("ready");
}

function start(){
    doSend("start");
}