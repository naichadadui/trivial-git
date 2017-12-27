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

    <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto:100,700'>

    <link rel="stylesheet" href="/css/RoomTitle.css">
    <link rel="stylesheet" href="/css/button.css" media="screen" type="text/css" />
    <script  src="/js/roomTitle.js"></script>
    <script src="/js/gameRoom.js"></script>
    <script>
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
        <h2>Room No.1</h2>
    </div>
</div>

<div class="demo">
    <div class="container">
        <div class="row">
            <div class="col-md-3 col-sm-6">
                <div class="box">
                    <a href="#">  <img src="/images/doctor.jpg"></a>
                    <div class="box-content">
                        <h3 class="title">雷神索尔</h3>
                        <span class="post">胜率:20%</span>
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-sm-6">
                <div class="box">
                    <img src="/images/pirate.jpg">
                    <div class="box-content">
                        <h3 class="title">蜘蛛侠</h3>
                        <span class="post">胜率:30%</span>
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-sm-6">
                <div class="box">
                    <img src="/images/zombie.jpg">
                    <div class="box-content">
                        <h3 class="title">钢铁侠</h3>
                        <span class="post">胜率:40%</span>
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-sm-6">
                <div class="box">
                    <img src="/images/caveman.jpg">
                    <div class="box-content">
                        <h3 class="title">钢铁侠</h3>
                        <span class="post">胜率10%</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="buttoncontainer">

    <p><a href="JapanGame">
        Start
    </a></p>
</div>

</body>
</html>