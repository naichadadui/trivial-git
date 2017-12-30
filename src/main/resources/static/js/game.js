var japanMap_left=new Array(85,154,204,243);
var japanMap_top=new Array(515,439,513,464);

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

function initPlayerCards(json){

    var playerList=json.players;
    console.log("enterGame:载入人物 "+playerList);
    for(var i=0;i<playerList.length;i++){
        $("#name"+(i+1)).html(playerList[i].playerName);
        $("#score"+(i+1)).html(playerList[i].sumOfGoldCoins);
        if(i==json.currentPlayerId)
        {
            $("#state"+(i+1)).html("进行");
        }else
        {
            if(playerList[i].isInPenaltyBox){
                $("#state"+(i+1)).html("监狱");
            }
            else{
                $("#state"+(i+1)).html("等待");
            }
        }
        $("#card"+(i+1)).show();
    }


}

