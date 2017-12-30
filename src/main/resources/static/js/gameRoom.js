
var userID;
function makeConnection(userId,roomId) {
    userID=userId;
    var ipURL = "ws://localhost:10000/" + "webSocket/"+roomId+"/" + userId;
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
    console.log("players: "+playerList);
    console.log("This is "+userID);
    for(var i=0;i<playerList.length;i++){
        $("#name"+(i+1)).html(playerList[i].playerName);
        $("#winRate"+(i+1)).html("胜率"+playerList[i].user.winRate+"%");
        $("#player"+(i+1)).show();
        if(!playerList[i].ready) {
            $("#state"+(i+1)).html("未准备");
            isAllReady = false;
        }
        else {
            $("#state"+(i+1)).html("已准备");
        }


        if(i!=0&&!playerList[i].ready&&userID==playerList[i].user.userId){
            $("#buttonA").html("Ready");
            $("#buttonP").show();
            console.log(i+ " button Ready show");
        }
        if(i!=0&&playerList[i].ready&&userID==playerList[i].user.userId){
            $("#buttonA").html("Ready");
            $("#buttonP").hide();
            console.log(i+ " button Ready hide");
        }

    }

    if(userID==playerList[0].user.userId&&isAllReady&&playerList.length>=2)
    {
        $("#buttonA").html("Start");
        $("#buttonA").attr("onclick","start()");
        $("#buttonP").show();
        console.log(userID + " button Start show");
    }
    if(userID==playerList[0].user.userId&&!isAllReady&&playerList.length>=2)
    {
        $("#buttonA").html("Start");
        $("#buttonA").attr("onclick","start()");
        $("#buttonP").hide();
        console.log(userID + " button Start hide");
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