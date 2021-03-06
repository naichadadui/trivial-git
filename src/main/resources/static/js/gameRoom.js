var userID;
var gamePeriod = 0;
var turn = 0;
var japanMap_left = new Array(85, 154, 204, 243, 290, 340, 440, 450, 550, 590, 620, 660, 700, 780, 890, 820, 130);
var japanMap_top = new Array(515, 439, 513, 464, 550, 500, 550, 460, 550, 450, 380, 320, 210, 190, 190, 120, 315);

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

    if (json.actionType == "room") {
        refreshRoom(json);
    }
    if (json.actionType == "room to game") {
        console.log("执行了" + "room to game");
        fromRoomToGame();
    }
    if (json.actionType == "startGame") {
        console.log("执行了" + "startGame");
        gamePeriod = 0;
        startNewGame(json);
    }
    if (json.actionType == "stayPrison") {
        console.log("执行了" + "stayPrison");
        gamePeriod = 1;
        stayPrison(json);
    }
    if (json.actionType == "goOutPrison") {
        console.log("执行了" + "goOutPrison");
        gamePeriod = 1;
        goOutPrison(json);

    }
    if (json.actionType == "sendQuestion") {
        console.log("执行了" + "sendQuestion");
        gamePeriod = 1;
        answerQuestion(json);
    }
    if (json.actionType == "checkAnswer") {
        console.log("执行了" + "checkAnswer");
        checkAnswer(json);
    }
    if (json.actionType == "gameOver") {
        console.log("执行了" + "gameOver");
        for (var i = 0; i < json.players.length; i++) {
            if (userID == json.players[i].user.userId) {
                if (userID == json.winner.user.userId) {
                    var endWord = "恭喜您成为本局赢家";
                }else
                {
                    var endWord="本局赢家为 "+ json.winner.playerName+" 您得分为"+" "+json.players[i].sumOfGoldCoins;
                }

                new $.flavr({
                    content: endWord,
                    buttons: {
                        quit: {
                            text: 'quit', style: 'quit',
                            action: function () {
                                window.location.href="/trivial/work";
                                return false;
                            }
                        }
                    }
                });
            }
        }
    }
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


function sendChoice(choice) {
    console.log("sendChoice");
    doSend("answer:" + choice);
}


function gameReady() {
    console.log("send gameReady");
    doSend("gameReady");
}

function clickDice() {
    console.log("send clickDice");
    doSend("clickDice");
}

function nextTurn() {
    console.log("nextTurn");
    gamePeriod = 0;
    doSend("nextTurn");
}

/*刷新房间*/
function refreshRoom(json) {

    var playerList = json.players;
    var isAllReady = true;

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
            $("#buttonA").html("Ready");
            $("#buttonP").show();
            console.log(i + " button Ready show");
        }

        if (i != 0 && playerList[i].ready && userID == playerList[i].user.userId) {
            $("#buttonA").html("Ready");
            $("#buttonP").hide();
            console.log(i + " button Ready hide");
        }
    }
    for (var i = playerList.length; i <= 4; i++) {
        $("#player" + (i + 1)).hide();
    }
    if (userID == playerList[0].user.userId && playerList.length == 1) {
        $("#buttonP").hide();
        console.log(userID + " button Start hide");
    }
    if (userID == playerList[0].user.userId && isAllReady && playerList.length >= 2) {
        $("#buttonA").html("Start");
        $("#buttonA").attr("onclick", "start()");
        $("#buttonP").show();
        console.log(userID + " button Start show");
    }
    if (userID == playerList[0].user.userId && !isAllReady) {
        $("#buttonP").hide();
        console.log(userID + " button Start hide");
    }
}

/*进入游戏*/
function fromRoomToGame() {
    console.log(userID + "fromRoomToGame");
    $("#wholeRoom").hide();
    $("link").remove();
    $("style").remove();
    $("head").append("<style type=\"text/css\">" + " img {position :absolute}" + " </style>");
    $("head").append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/japan.css\">");
    $("head").append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/dice.css\"/>");
    $("head").append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/card.css\"><link rel=\"stylesheet\" href=\"/css/font-awesome.min.css\">");
    $("head").append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/tooltip-curved.css\"/><link type=\"text/css\" rel=\"stylesheet\" href=\"/css/timeO.css\">\n");
    $("head").append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/questionStyle.css\"/><link rel=\"stylesheet\" type=\"text/css\" href=\"/css/flavr.css\"/>\n");
    $("#wholeGame").show();
    gameReady();
}

/*开始一轮新游戏*/
function startNewGame(json) {
    console.log(userID + "startNewGame");
    $("#dice_mask").remove();
    var playerList = json.players;

    for (var i = 0; i < playerList.length; i++) {

        $("#gameName" + (i + 1)).html(playerList[i].playerName);
        $("#score" + (i + 1)).html(playerList[i].sumOfGoldCoins);
        if (i == json.currentPlayerId) {
            $("#gameState" + (i + 1)).html("进行");
        } else {
            if (playerList[i].inPenaltyBox) {
                $("#gameState" + (i + 1)).html("监狱");
            }
            else {
                $("#gameState" + (i + 1)).html("等待");
            }
        }
        $("#card" + (i + 1)).show();


        var dice = $("#dice");
        if (json.currentPlayerId == i && userID == playerList[i].user.userId) {
            $("#dice_mask").remove();
        }
        if (json.currentPlayerId != i && userID == playerList[i].user.userId) {
            $(".wrap").append("<div id='dice_mask'></div>");
        }

        if (playerList[i].inPenaltyBox) {
            movePerson(i, 16);
        } else {
            movePerson(i, playerList[i].place);
        }
        $("#horse" + (i + 1)).show();
    }


    $("#dice").off("click").on("click", function () {
        clearCounter();
        clickDice();
    });


    $("#counter_nums").append("<span class=\"in\">15</span>");
    for (var i = 14; i >= 0; i--) {
        $("#counter_nums").append("<span>" + i + "</span>");
    }
    var nums = document.querySelectorAll('.nums span');
    var counter = document.querySelector('.counter');
    runAnimation(json, nums, counter);
}

/*留在监狱*/
function stayPrison(json) {
    console.log(userID + "stayPrison");
    var playerList = json.players;

    clearCounter();

    for (var i = 0; i < playerList.length; i++) {
        $("#gameName" + (i + 1)).html(playerList[i].playerName);
        $("#score" + (i + 1)).html(playerList[i].sumOfGoldCoins);
        if (i == json.currentPlayerId) {
            $("#gameState" + (i + 1)).html("进行");
        } else {
            if (playerList[i].inPenaltyBox) {
                $("#gameState" + (i + 1)).html("监狱");
            }
            else {
                $("#gameState" + (i + 1)).html("等待");
            }
        }

        if (playerList[i].inPenaltyBox) {
            movePerson(i, 16);
        } else {
            movePerson(i, playerList[i].place);
        }
        $("#horse" + (i + 1)).show();
    }

    clickDiceFun(json);
    new $.flavr({
        content: "第" + (json.currentPlayerId + 1) + "号玩家不幸留在了监狱",
        closeOverlay: true,
        closeEsc: true
    });
    nextTurn();

}

function goOutPrison(json) {
    console.log(userID + "goOutPrison");
    var playerList = json.players;

    clearCounter();

    for (var i = 0; i < playerList.length; i++) {

        $("#gameName" + (i + 1)).html(playerList[i].playerName);
        $("#score" + (i + 1)).html(playerList[i].sumOfGoldCoins);
        if (i == json.currentPlayerId) {
            $("#gameState" + (i + 1)).html("进行");
        } else {
            if (playerList[i].inPenaltyBox) {
                $("#gameState" + (i + 1)).html("监狱");
            }
            else {
                $("#gameState" + (i + 1)).html("等待");
            }
        }

        if (playerList[i].inPenaltyBox) {
            movePerson(i, 16);
        } else {
            movePerson(i, playerList[i].place);
        }
        $("#horse" + (i + 1)).show();
    }
    clickDiceFun(json);
    new $.flavr({
        content: "第" + (json.currentPlayerId + 1) + "号玩家终于从监狱里出来了",
        closeOverlay: true,
        closeEsc: true
    });
    nextTurn();
}

function answerQuestion(json) {
    console.log(userID + "answerQuestion")
    clearCounter();
    var playerList = json.players;
    clickDiceFun(json);

    $("#counter_nums").append("<span class=\"in\">60</span>");
    for (var i = 59; i >= 0; i--) {
        $("#counter_nums").append("<span>" + i + "</span>");
    }
    var nums = document.querySelectorAll('.nums span');
    var counter = document.querySelector('.counter');
    runAnimation(json, nums, counter);


    for (var i = 0; i < playerList.length; i++) {
        if (playerList[i].inPenaltyBox) {
            movePerson(i, 16);
        } else {
            movePerson(i, playerList[i].place);
        }
    }

    moveCircle(playerList[json.currentPlayerId].place);
    $("#questionCircle").show();


    if (userID == playerList[json.currentPlayerId].user.userId) {
        var question = json.currentQuestion.content;
        var op1 = json.currentQuestion.options[0];
        var op2 = json.currentQuestion.options[1];
        var op3 = json.currentQuestion.options[2];
        var op4 = json.currentQuestion.options[3];
        console.log("载入问题" + question);

        if (userID == json.players[json.currentPlayerId].user.userId) {
            $('#demo-stacked-buttons ').off("click").on('click', function () {
                clearCounter();
                new $.flavr({
                    buttonDisplay: 'stacked',
                    content: question,
                    buttons: {
                        a: {
                            text: op1,
                            style: 'info',
                            action: function () {
                                sendChoice(op1);
                            }
                        },
                        b: {
                            text: op2,
                            style: 'info',
                            action: function () {
                                sendChoice(op2);
                            }
                        },
                        c: {
                            text: op3,
                            style: 'info',
                            action: function () {
                                sendChoice(op3);
                            }
                        },
                        d: {
                            text: op4,
                            style: 'info',
                            action: function () {
                                sendChoice(op4);
                            }
                        }
                    }
                });
            });
        }
    }
}

function checkAnswer(json) {
    console.log(userID + "checkAnswer");
    clearCounter();
    var playerList = json.players;
    $("#questionCircle").hide();
    for (var i = 0; i < playerList.length; i++) {

        $("#gameName" + (i + 1)).html(playerList[i].playerName);
        $("#score" + (i + 1)).html(playerList[i].sumOfGoldCoins);
        if (i == json.currentPlayerId) {
            $("#gameState" + (i + 1)).html("进行");
        } else {
            if (playerList[i].inPenaltyBox) {
                $("#gameState" + (i + 1)).html("监狱");
            }
            else {
                $("#gameState" + (i + 1)).html("等待");
            }
        }

        if (userID == playerList[i].user.userId) {
            if (json.right) {
                new $.flavr({
                    content: "恭喜第" + ( json.currentPlayerId + 1) + "号玩家回答正确",
                    closeOverlay: true,
                    closeEsc: true
                });

            }
            else {
                new $.flavr({
                    content: "第" + (json.currentPlayerId + 1) + "号玩家回答错误\n进入监狱",
                    closeOverlay: true,
                    closeEsc: true
                });
            }
        }

        if (playerList[i].inPenaltyBox) {
            movePerson(i, 16);
        } else {
            movePerson(i, playerList[i].place);
        }
        $("#horse" + (i + 1)).show();
    }
    nextTurn();
}


/** 倒计时  */



function timeOver(json) {
    if (gamePeriod == 0) {
        console.log(userID + "超时自动抛");
        clickDice();
    }
    if (gamePeriod == 1) {
        console.log(userID + "超时自动回答问题");
        sendChoice(json.currentQuestion.options[0]);
    }
}


/* 执行点击骰子*/
function clickDiceFun(json) {

    var dice = $("#dice");
    var playerList = json.players;
    var currentDice = json.rollNumber;
    $(".wrap").append("<div id='dice_mask'></div>");//加遮罩
    dice.attr("class", "dice");//清除上次动画后的点数
    dice.css('cursor', 'default');
    //var num = Math.floor(Math.random() * 6 + 1);//产生随机数1-6
    var num = Number(currentDice);
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
        //$("#dice_mask").remove();//移除遮罩
    });
}

/*执行人物移动*/
function movePerson(id, index) {
    //  $("#horse" + (id+1)).attr("style", "left:" + japanMap_left[index] + "px ; top:" + japanMap_top[index] + " px");
    $("#horse" + (id + 1)).css("left", japanMap_left[index]);
    $("#horse" + (id + 1)).css("top", japanMap_top[index]);
}

function moveCircle(index) {
    $("#demo-stacked-buttons").css("left", japanMap_left[index] + 10);
    $("#demo-stacked-buttons").css("top", japanMap_top[index] - 45);
}

function clearCounter() {
    $("#counter_nums").empty();
}

function runAnimation(json, nums, counter) {

    nums.forEach(function (num, idx) {

        var penultimate = nums.length - 1;
        num.addEventListener('animationend', function (e) {
            if (e.animationName === 'goIn' && idx !== penultimate) {
                num.classList.remove('in');
                num.classList.add('out');
            } else if (e.animationName === 'goOut' && num.nextElementSibling) {
                num.nextElementSibling.classList.add('in');
            } else {
                if (userID == json.players[json.currentPlayerId].user.userId) {
                    timeOver(json);
                }
            }
        });
    });
}

/*倒计时*/
