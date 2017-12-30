var userID;

function makeConnection(userId, roomId) {
    userID = userId;
    var ipURL = "ws://localhost:10000/" + "webSocket/" + roomId + "/" + userId;
    try {
        socket = new WebSocket(ipURL);
    } catch (e) {
        alert("error happend");
        close;
    }
    socket.onopen = sOpen;
    socket.onerror = sError;
    socket.onmessage = sMessage;
    socket.onclose = sClose;
}

function sOpen() {
    enterRoom();
}

function sError(e) {
    alert("error " + e);
}

function sMessage(msg) {
    //top.location.reload();
    var json = JSON.parse(msg.data);
    refreshRoom(json);
}

function sClose(e) {
    alert("connect closed:" + e.code);
}

function doSend(msg) {
    socket.send(msg);
}

function enterRoom() {
    doSend("enter");
}

function Close() {
    socket.close();
}

function ready() {
    doSend("ready");
}

function start() {
    doSend("start");
}

function refreshRoom(json){
    var playerList = json.players;
    var isAllReady = true;

    //console.log("players: "+playerList);

    console.log("This is " + userID);
    for (var i = 0; i < playerList.length; i++) {
        $("#name" + (i + 1)).html(playerList[i].playerName);
        $("#winRate" + (i + 1)).html("胜率" + playerList[i].user.winRate + "%");
        $("#player" + (i + 1)).show();
        if (!playerList[i].ready) {
            $("#state" + (i + 1)).html("未准备");
            isAllReady = false;
        }
        else {
            $("#state" + (i + 1)).html("已准备");
        }

        if (i != 0 && !playerList[i].ready && userID == playerList[i].user.userId) {
            console.log("test2");
            $("#buttonA").html("Ready");
            $("#buttonP").show();
            console.log(i + " button Ready show");
        }
        console.log("test3");
        if (i != 0 && playerList[i].ready && userID == playerList[i].user.userId) {
            console.log("test4");
            $("#buttonA").html("Ready");
            $("#buttonP").hide();
            console.log(i + " button Ready hide");
        }
        console.log("test5");
    }
    for (var i = playerList.length; i <= 4; i++) {
        $("#player" + (i+1)).hide();
    }
    if (userID == playerList[0].user.userId && playerList.length == 1) {
        $("#buttonP").hide();
        console.log(userID + " button Start hide");
    }
    if (userID == playerList[0].user.userId && isAllReady && playerList.length >= 2) {
        $("#buttonA").html("Start");
        $("#buttonA").attr("onclick", "fromRoomToGame()");
        $("#buttonP").show();
        console.log(userID + " button Start show");
    }
    if (userID == playerList[0].user.userId && !isAllReady) {
        $("#buttonP").hide();
        console.log(userID + " button Start hide");
    }
}

function fromRoomToGame(){
    $("#wholeRoom").hide();
    $("link").remove();
    $("style").remove();
    $("head").append("<style type=\"text/css\">"+" img {position :absolute}"+" </style>");
    $("head").append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/japan.css\">");
    $("head").append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/dice.css\"/>");
    $("head").append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/card.css\"><link rel=\"stylesheet\" href=\"/css/font-awesome.min.css\">");
    $("head").append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/tooltip-curved.css\"/><link type=\"text/css\" rel=\"stylesheet\" href=\"/css/timeO.css\">\n");
    $("head").append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/questionStyle.css\"/><link rel=\"stylesheet\" type=\"text/css\" href=\"/css/flavr.css\"/>\n");
    $("#wholeGame").show();


    var dice = $("#dice");
    dice.click(function () {
        $(".wrap").append("<div id='dice_mask'></div>");//加遮罩
        dice.attr("class", "dice");//清除上次动画后的点数
        dice.css('cursor', 'default');
        var num = Math.floor(Math.random() * 6 + 1);//产生随机数1-6
        dice.animate({left: '+2px'}, 100, function () {
            dice.addClass("dice_t");
        }).delay(200).animate({top: '-2px'}, 100, function () {
            dice.removeClass("dice_t").addClass("dice_s");
        }).delay(200).animate({opacity: 'show'}, 600, function () {
            dice.removeClass("dice_s").addClass("dice_e");
        }).delay(100).animate({left: '-2px', top: '2px'}, 100, function () {
            dice.removeClass("dice_e").addClass("dice_" + num);
            // $("#result").html("您掷得点数是<span>"+num+"</span>");
            dice.css('cursor', 'pointer');
            $("#dice_mask").remove();//移除遮罩
        });
    });
    /*  -------------------------------------------------------------------------------
            Stacked Buttons
        ------------------------------------------------------------------------------- */
    $('#demo-stacked-buttons ').on('click', function () {

        new $.flavr({
            buttonDisplay: 'stacked',
            content: 'Java的正式推出时间',
            buttons: {
                a: {
                    text:'1995',
                    style: 'info',
                    action: function(){
                        alert('true');
                    }
                },
                b: {
                    text:'1996',
                    style: 'info',
                    action: function(){
                        alert('false');
                    }
                },
                c: {
                    text:'1997',
                    style: 'info',
                    action: function(){
                        alert('false');
                    }
                },
                d: {
                    text:'1998',
                    style: 'info',
                    action: function(){
                        alert('false');
                    }
                }
            }
        });
    });
}