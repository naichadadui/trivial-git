var userID;
var gamePeriod = 0;
var japanMap_left = new Array(85, 154, 204, 243,290,340,440,450,550,590,620,660,700,780,890,820);
var japanMap_top = new Array(515, 439, 513, 464,550,500,550,460,450,380,550,320,210,190,190,120);

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
    console.log(json.actionType);
    if (json.actionType == "room") {
        refreshRoom(json);
    }
    if (json.actionType == "room to game") {
        console.log("执行了"+"room to game");
        fromRoomToGame();
        game(json);
    }
    if (json.actionType == "game") {
        game(json);
    }
    if (json.actionType == "question") {
        var result = json.result;
        alert(result);
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

function outOfPrison() {
    doSend("outOfPrison");
}

function notOutOfPrison() {
    doSend("notOutOfPrison");
}

function sendChoice(choice) {
    doSend("answer:" + choice);
}

function refreshRoom(json) {

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


function fromRoomToGame() {
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
}

function game(json) {
    gamePeriod = 0;
    var playerList = json.players;

    for (var i = 0; i < playerList.length; i++) {

        console.log("载入人物");
        $("#name" + (i + 1)).html(playerList[i].playerName);
        $("#score" + (i + 1)).html(playerList[i].sumOfGoldCoins);
        if (i == json.currentPlayerId) {
            $("#state" + (i + 1)).html("进行");
        } else {
            if (playerList[i].isInPenaltyBox) {
                $("#state" + (i + 1)).html("监狱");
            }
            else {
                $("#state" + (i + 1)).html("等待");
            }
        }
        $("#card" + (i + 1)).show();

        console.log("载入骰子");
        var dice = $("#dice");
        dice.click(function () {
            gamePeriod = 1;
            clickDiceFun(json);
        });
        if (json.currentPlayerId == i && userID == playerList[i].user.userId) {
            $("#dice_mask").remove();
        }
        if (json.currentPlayerId != i && userID == playerList[i].user.userId) {
            $(".wrap").append("<div id='dice_mask'></div>");
        }

        console.log("载入马");
        if (playerList[i].isInPenalyBox) {
            movePerson(i, 16);
        } else {
            movePerson(i, playerList[i].place);
        }
        $("#horse" + (i + 1)).show();
    }
    console.log("载入15s倒计时");
    $("#counter_id").append("<span class=\"in\">15</span>");
    for (var i = 14; i >= 0; i--) {
        $("#counter_id").append("<span>" + i + "</span>");
    }
}

function answerQuestion(json) {

    console.log("载入60s倒计时");
    $("#counter_id").append("<span class=\"in\">60</span>");
    for (var i = 59; i >= 0; i--) {
        $("#counter_id").append("<span>" + i + "</span>");
    }
    var question = json.currentQuestion.content;
    var op1 = json.currentQuestion.option[0];
    var op2 = json.currentQuestion.option[1];
    var op3 = json.currentQuestion.option[2];
    var op4 = json.currentQuestion.option[3];
    console.log("载入问题" + question);

    if (userID == json.players[currentPlayerId].user.userID) {
        $('#demo-stacked-buttons ').on('click', function () {
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


/** 倒计时  */
const nums = document.querySelectorAll('.nums span');
const counter = document.querySelector('.counter');
const finalMessage = document.querySelector('.final');
const replay = document.getElementById('replay');

runAnimation(json);

function resetDOM() {
    counter.classList.remove('hide');
    finalMessage.classList.remove('show');

    nums.forEach(function (num) {
        num.classList.value = '';
    });

    nums[0].classList.add('in');
}

function runAnimation(json) {
    nums.forEach(function (num, idx) {
        var penultimate = nums.length - 1;
        num.addEventListener('animationend', function (e) {
            if (e.animationName === 'goIn' && idx !== penultimate) {
                num.classList.remove('in');
                num.classList.add('out');
            } else if (e.animationName === 'goOut' && num.nextElementSibling) {
                num.nextElementSibling.classList.add('in');
            } else {
                counter.classList.add('hide');
                timeOver(json);
            }
        });
    });
}

replay.addEventListener('click', function () {
    resetDOM();
    runAnimation();
});


function timeOver(json) {
    if (gamePeriod == 0) {
        clickDiceFun(json);
    }
    if (gamePeriod == 1) {
        sendChoice(json.currentQuestion.option[0]);
    }
}


/* 执行点击骰子*/
function clickDiceFun(json) {
    clearCounter();
    gamePeriod = 1;
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
    if (playerList[json.currentPlayerId].isInPenaltyBox) {
        if (num % 2 == 0) {
            var num = Math.floor(Math.random() * 15);
            $("#horse" + json.currentPlayerId).delay(200);
            movePerson(json.currentPlayerId, playerList[json.currentPlayerId].place);
            $("#horse" + json.currentPlayerId).delay(200);
            outOfPrison();
        } else {
            notOutOfPrison();
        }
    } else {
        $("#horse" + json.currentPlayerId).delay(200);
        movePerson(json.currentPlayerId, playerList[json.currentPlayerId].place);
        moveCircle(playerList[json.currentPlayerId].place);
        $('#demo-stacked-buttons ').show();
        $("#horse" + json.currentPlayerId).delay(200);
        answerQuestion(json);
    }
}

/*执行人物移动*/
function movePerson(id, index) {
    $("#horse" + id).attr("style", "left:" + japanMap_left[index] + "px ; top:" + japanMap_top[index] + " px");
}

function moveCircle(index) {
    $('#demo-stacked-buttons ').attr("style", "left:" + japanMap_left[index]+10 + "px ; top:" + japanMap_top[index]-50 + " px");
}

function clearCounter() {
    $("#counter_id").empty();
}

