var sorcket;
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
    alert('connect success!');
}
function sError(e){
    alert("error " + e);
}
function sMessage(msg){
    //top.location.reload();
    var json=JSON.parse(msg.data);

    var playerContainer=document.getElementById("playerContainer");
    var player = document.getElementById("playerTip");


    playerContainer.parentNode.removeChild(player);

   //  var buttonContainer=document.getElementById("buttoncontainer");
   //  var buttonP=document.getElementById("buttonP");
   //  buttonContainer.removeChild(buttonP);
   //  buttonP=document.createElement("p");
   //  buttonP.id="buttonP";
   //  var isAllReady=true;
   //
     var playerList=json.players;
   for(var i=0;i<playerList.length;i++){

        //var div1=document.createElement("div");
        //div1.className="col-md-3 col-sm-6";
       //  var div2=document.createElement("div");
       //  div2.className="box";
       //  var imgPlayer=document.createElement("img");
       //  imgPlayer.src="/images/player/player1.jpg";
       //  var div3=document.createElement("div");
       //  div3.className="box-content";
       //  var h1=document.createElement("h3");
       //  h1.className="title";
       //  h1.innerHTML=playerList[i].playerName;
       //  var span1=document.createElement("span");
       //  span1.className="post";
       //  span1.innerHTML=playerList[i].user.winRate;
       //  div3.appendChild(h1);
       //  div3.appendChild(span1);
       //  div2.appendChild(imgPlayer);
       // div2.appendChild(div3);
       //  div1.appendChild(div2);
      // player.appendChild(div1);

      //  if(!playerList[i].ready)
      //      isAllReady=false;
      //
      //  if(playerList[i].user.userId==userID&&i!=0) {
      //      if (!playerList[i].ready) {
      //          var readyA = document.createElement("a");
      //          readyA.onclick = "ready()";
      //          readyA.innerHTML = "Ready";
      //          buttonP.appendChild(readyA);
      //      }
      //      buttonContainer.appendChild(buttonP);
      // }
   }

    //playerContainer.appendChild(player);
    //alert(playerContainer.html());

    // if(userID==playerList[0].user.userId){
    //     if (playerList.length >= 2&&isAllReady) {
    //         var readyA = document.createElement("a");
    //         readyA.onclick = "start()";
    //         readyA.innerHTML = "Start";
    //         buttonP.appendChild(readyA);
    //     }
    //     buttonContainer.appendChild(buttonP);
    // }
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