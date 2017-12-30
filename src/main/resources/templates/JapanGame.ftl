<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" type="text/css" href="/css/japan.css">
    <link rel="stylesheet" type="text/css" href="/css/card.css">
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/tooltip-curved.css"/>
    <link type="text/css" rel="stylesheet" href="/css/timeO.css">
    <link rel="stylesheet" type="text/css" href="/css/questionStyle.css"/>
    <link rel="stylesheet" type="text/css" href="/css/flavr.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/flavr.min.js"></script>
    <script type="text/javascript" src="/js/game.js"></script>
    <style type="text/css">
        .demo {
            position: absolute;
            top: 400px;
            left: 1170px;
        }

        .wrap {
            width: 90px;
            height: 90px;
            margin: 120px auto 30px auto;
            position: relative
        }

        .dice {
            width: 90px;
            height: 90px;
            background-image: url(/images/dice.png);
            cursor: pointer;
        }

        .dice_1 {
            background-position: -5px -4px
        }

        .dice_2 {
            background-position: -5px -107px
        }

        .dice_3 {
            background-position: -5px -212px
        }

        .dice_4 {
            background-position: -5px -317px
        }

        .dice_5 {
            background-position: -5px -427px
        }

        .dice_6 {
            background-position: -5px -535px
        }

        .dice_t {
            background-position: -5px -651px
        }

        .dice_s {
            background-position: -5px -763px
        }

        .dice_e {
            background-position: -5px -876px
        }

        p#result {
            text-align: center;
            font-size: 16px
        }

        p#result span {
            font-weight: bold;
            color: #f30;
            margin: 6px
        }

        #dice_mask {
            width: 90px;
            height: 90px;
            background: #fff;
            opacity: 0;
            position: absolute;
            top: 0;
            left: 0;
            z-index: 999
        }

        img {
            position: absolute;
        }

    </style>

    <script type="text/javascript">
        $(document).ready(function () {

            $("#card1").hide();
            $("#card2").hide();
            $("#card3").hide();
            $("#card4").hide();

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
        });
    </script>
</head>
<body>
<div>
    <img src="/images/horse/horse1.png" style=" left:85px; top:515px">
    <img src="/images/horse/horse2.png" style=" left:204px; top:513px">
    <img src="/images/horse/horse3.png" style=" left:154px; top:439px">
    <img src="/images/horse/horse4.png" style=" left:243px; top:464px">
    <img src="/images/horse/horse1.png" style=" left:340px; top:508px">
    <img src="/images/horse/horse2.png" style=" left:290px; top:550px">
    <img src="/images/horse/horse3.png" style=" left:440px; top:550px">
    <img src="/images/horse/horse4.png" style=" left:450px; top:460px">
    <img src="/images/horse/horse3.png" style=" left:130px; top:315px">
</div>
<div class="tooltip">
    <span class="tooltip-item" id="demo-stacked-buttons">
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
                <time id="name1">
                </time>
            </div>
            <div class="card-title" id="score1">

            </div>
            <div class="card-exceprt" id="state1">
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
                <time id="name2">
                </time>
            </div>
            <div class="card-title" id="score2">

            </div>
            <div class="card-exceprt" id="state2">

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
                <time id="name3">
                </time>
            </div>
            <div class="card-title" id="score3">
            </div>
            <div class="card-exceprt" id="state3">
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
                <time id="name4">
                </time>
            </div>
            <div class="card-title" id="score4">
            </div>
            <div class="card-exceprt" id="state4">
            </div>
        </div>
    </div>
</div>
<div id="counter" class="counter">
    <div id="counter_id" class="nums">
     <!--   <span class="in">12</span>
        <span>11</span>
        <span>10</span>
        <span>9</span>
        <span>8</span>
        <span>7</span>
        <span>6</span>
        <span>5</span>
        <span>4</span>
        <span>3</span>
        <span>2</span>
        <span>1</span>
        <span>0</span> -->
    </div>
</div>
<div class="final">
    <h1 id="final_word">您超时啦</h1>
</div>

<div class="demo">
    <div class="wrap">
        <div id="dice" class="dice dice_1"></div>
    </div>
</div>
<script type="text/javascript" src="/js/timeO.js"></script>
<script type="text/javascript" src="/js/questionCommon.js"></script>
</body>
</html>