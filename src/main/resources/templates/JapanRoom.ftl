<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>游戏房间</title>

    <link rel="stylesheet" type="text/css" href="/css/room/bootstrap-grid.min.css" />
    <link href="/css/room/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/demo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">


    <link rel="stylesheet" href="/css/RoomTitle.css">
    <link rel="stylesheet" href="/css/button.css" media="screen" type="text/css" />
    <script src="/js/gameRoom.js"></script>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript">
       $(document).ready( function() {
            $("#player1").hide();
            $("#player2").hide();
            $("#player3").hide();
            $("#player4").hide();
            $("#buttonP").hide();
        });
        
        var id = ${Session.userId};
         makeConnection(id);
    </script>

    <style type="text/css">
        .demo{padding: 2em 0;}
        .box{
            text-align: center;
            overflow: hidden;
            position: relative;
        }
        .box:before{
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
        .box:hover:before{
            width: 100%;
            left: 0;
            opacity: 0.5 ;
        }
        .box img{
            width: 100%;
            height: auto;
        }
        .box .box-content{
            width: 100%;
            padding: 16px 18px;
            color: #fff;
            position: absolute;
            top: 40%;
            left: 0;
        }
        .box .title{
            font-size: 30px;
            font-weight: 600;
            line-height: 30px;
            text-transform: uppercase;
            margin: 0;
            opacity: 0;
            transition: all 0.5s ease 0s;
        }
        .box .post{
            font-size: 20px;
            text-transform: capitalize;
            opacity: 0;
            transition: all 0.5s ease 0s;
        }
        .box:hover .title,
        .box:hover .post{
            opacity: 1;
            transition-delay: 0.7s;
        }
        @media only screen and (max-width:990px){
            .box{ margin-bottom: 30px; }
        }
    </style>
</head>
<body>

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
    <p id="buttonP"><a id="buttonA" onclick="">

    </a>
    </p>
</div>
<script  src="/js/roomTitle.js"></script>
</body>
</html>