<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>游戏房间</title>

    <link rel="stylesheet" type="text/css" href="/css/room/bootstrap-grid.min.css"/>
    <link href="/css/room/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/demo.css">
    <link rel="stylesheet" href="/css/reset.min.css">
    <link rel="stylesheet" href="/css/RoomTitle.css">
    <link rel="stylesheet" href="/css/button.css" media="screen" type="text/css"/>

    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/flavr.min.js"></script>
    <script src="/js/gameRoom.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#wholeGame").hide();
            $("#player1").hide();
            $("#player2").hide();
            $("#player3").hide();
            $("#player4").hide();
            $("#buttonP").hide();

            $("#horse1").hide();
            $("#horse2").hide();
            $("#horse3").hide();
            $("#horse4").hide();
            $("#questionCircle").hide();
            $("#card1").hide();
            $("#card2").hide();
            $("#card3").hide();
            $("#card4").hide();
        });
        var id = ${Session.userId};
        var roomId =${Session.roomId};
        makeConnection(id, roomId);

    </script>

    <style type="text/css">
        .demo {
            padding: 2em 0;
        }

        .box {
            text-align: center;
            overflow: hidden;
            position: relative;
        }

        .box:before {
            content: "";
            width: 0;
            height: 100%;
            padding: 5px 18px;
            position: absolute;
            top: 0;
            left: 50%;
            opacity: 0;
            transition: all 500ms cubic-bezier(0.47, 0, 0.745, 0.715) 0s;
        }

        .box:hover:before {
            width: 100%;
            left: 0;
            opacity: 0.5;
        }

        .box img {
            width: 100%;
            height: auto;
        }

        .box .box-content {
            width: 100%;
            padding: 16px 18px;
            color: #fff;
            position: absolute;
            top: 40%;
            left: 0;
        }

        .box .title {
            font-size: 30px;
            font-weight: 600;
            line-height: 30px;
            text-transform: uppercase;
            margin: 0;
            opacity: 0;
            transition: all 0.5s ease 0s;
        }

        .box .post {
            font-size: 20px;
            text-transform: capitalize;
            opacity: 0;
            transition: all 0.5s ease 0s;
        }

        .box:hover .title,
        .box:hover .post {
            opacity: 1;
            transition-delay: 0.7s;
        }

        @media only screen and (max-width: 990px) {
            .box {
                margin-bottom: 30px;
            }
        }
    </style>
</head>
<body>

<div id="wholeRoom">
    <div class="titlecontainer">
        <div class="sentence">
            <h1>Hey! Welcome To Japan </h1>
            <h2>Room No.${roomId}</h2>
        </div>
    </div>

    <div class="demo">
        <div class="container" id="playerContainer">
            <div class="row" id="playerTip">
                <div class="col-md-3 col-sm-6" id="player1">
                    <div class="box">
                        <img src="/images/player/player1.jpg">
                        <div class="box-content">
                            <h3 class="title" id="name1"></h3>
                            <span class="post" id="winRate1"></span>
                            <h3></h3>
                            <span class="post" id="state1"></span>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-sm-6" id="player2">
                    <div class="box">
                        <img src="/images/player/player2.jpg">
                        <div class="box-content">
                            <h3 class="title" id="name2"></h3>
                            <span class="post" id="winRate2"></span>
                            <h3></h3>
                            <span class="post" id="state2"></span>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-sm-6" id="player3">
                    <div class="box">
                        <img src="/images/player/player3.jpg">
                        <div class="box-content">
                            <h3 class="title" id="name3"></h3>
                            <span class="post" id="winRate3"></span>
                            <h3></h3>
                            <span class="post" id="state3"></span>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-sm-6" id="player4">
                    <div class="box">
                        <img src="/images/player/player4.jpg">
                        <div class="box-content">
                            <h3 class="title" id="name4"></h3>
                            <span class="post" id="winRate4"></span>
                            <h3></h3>
                            <span class="post" id="state4"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="buttoncontainer">
        <p id="buttonP"><a id="buttonA" onclick="ready()">
        </a>
        </p>
    </div>
</div>
<div id="wholeGame">
    <div id="horses">
        <img id="horse1" src="/images/horse/horse1.png" style=" left:85px; top:515px">
        <img id="horse2" src="/images/horse/horse2.png" style=" left:204px; top:513px">
        <img id="horse3" src="/images/horse/horse3.png" style=" left:154px; top:439px">
        <img id="horse4" src="/images/horse/horse4.png" style=" left:243px; top:464px">
    </div>
    <div class="tooltip" id="questionCircle">
    <span class="tooltip-item" id="demo-stacked-buttons" style=" left:710px; top:160px">
    </span>
    </div>
    <div class="card-position" id="player_cards">
        <div id="card1" class="card">
            <div class="card-image">
                <img src="/images/pirate.jpg"
                     alt="pirate"/>
            </div>
            <div class="card-body">
                <div class="card-date">
                    <time id="gameName1">
                    </time>
                </div>
                <div class="card-title" id="score1">

                </div>
                <div class="card-exceprt" id="gameState1">
                </div>
            </div>
        </div>
        <div id="card2" class="card">
            <div class="card-image">
                <img src="/images/doctor.jpg"
                     alt="pirate"/>
            </div>
            <div class="card-body">
                <div class="card-date">
                    <time id="gameName2">
                    </time>
                </div>
                <div class="card-title" id="score2">

                </div>
                <div class="card-exceprt" id="gameState2">

                </div>
            </div>
        </div>
        <div id="card3" class="card">
            <div class="card-image">
                <img src="/images/zombie.jpg"
                     alt="pirate"/>
            </div>
            <div class="card-body">
                <div class="card-date">
                    <time id="gameName3">
                    </time>
                </div>
                <div class="card-title" id="score3">
                </div>
                <div class="card-exceprt" id="gameState3">
                </div>
            </div>
        </div>
        <div id="card4" class="card">
            <div class="card-image">
                <img src="/images/caveman.jpg"
                     alt="pirate"/>
            </div>
            <div class="card-body">
                <div class="card-date">
                    <time id="gameName4">
                    </time>
                </div>
                <div class="card-title" id="score4">
                </div>
                <div class="card-exceprt" id="gameState4">
                </div>
            </div>
        </div>
    </div>
    <div id="counter" class="counter">
        <div id="counter_id" class="nums">
        </div>
    </div>
    <div class="final">
        <h1 id="final_word"></h1>
    </div>

    <div class="demo">
        <div class="wrap">
            <div id="dice" class="dice dice_1"></div>
        </div>
    </div>
</div>
<script src="/js/roomTitle.js"></script>
<script type="text/javascript" src="/js/questionCommon.js"></script>
</body>
</html>