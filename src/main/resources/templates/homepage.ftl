<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>首页-答题大闯关</title>

    <link rel="stylesheet" href="/css/style.css" type="text/css">
    <link href="/css/login-register.css" rel="stylesheet"/>
    <link href="/bootstrap3/css/bootstrap.css" rel="stylesheet"/>

    <link rel="stylesheet" type="text/css" href="/css/flavr.css"/>
    <link rel="stylesheet" type="text/css" href="/css/questionStyle.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/flavr.min.js"></script>

</head>

<body>
<div id="header">
    <div>
        <a href="index" id="logo"><img src="/images/logo.png" alt="LOGO"></a>
    </div>
</div>

<div id="page">
    <div id="contents">
        <div id="welcome">
            <img src="/images/artist-small.png" alt="Welcome">
            <p>
                游戏规则：
                几个参赛的玩家通过轮流掷色子来决定每个人在游戏盘上的位置，即玩家位置，然后根据位置上的问题作答。
                回答正确就会获得1枚金币，否则就被关进禁闭室。被关禁闭的玩家，在下次掷色子，若掷出的点数是奇数，
                则可以走出禁闭室，继续在游戏棋盘上前进到新的位置，并有机会通过回答问题来赢取金币；若掷出的点数是偶数，
                则仍需待在禁闭室里，不能前进和回答问题。一旦产生了第一个获得6枚金币的玩家，游戏结束。
            </p>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <a class="btn big-login" data-toggle="modal" href="javascript:void(0)" onclick="openLoginModal();">Log in</a>
                <a class="btn big-register" data-toggle="modal" href="javascript:void(0)" onclick="openRegisterModal();">Register</a>
                <a class="btn big-login" data-toggle="modal" href="javascript:void(0)" onclick="adminLogin();">Admin</a>
            </div>
            <div class="col-sm-4"></div>
        </div>


        <div class="modal fade login" id="loginModal">
            <div class="modal-dialog login animated">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Login with</h4>
                    </div>
                    <div class="modal-body">
                        <div class="box">
                            <div class="content">
                                <div class="division">
                                    <div class="line l"></div>
                                    <div class="line r"></div>
                                </div>
                                <div class="error"></div>
                                <div class="form loginBox">
                                    <form method="post" action="/login" accept-charset="UTF-8">
                                        <input id="email" class="form-control" type="text" placeholder="Email" name="email">
                                        <input id="password" class="form-control" type="password" placeholder="Password" name="password">
                                        <input class="btn btn-default btn-login" type="button" value="Login" onclick="loginAjax()">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="box">
                            <div class="content registerBox" style="display:none;">
                                <div class="form">
                                    <form method="post" html="{:multipart=>true}" data-remote="true" action="/register" accept-charset="UTF-8">
                                        <input id="emailR" class="form-control" type="text" placeholder="Email" name="email">
                                        <input id="nickname" class="form-control" type="text" placeholder=" nickname" name="nickname">
                                        <input id="passwordR" class="form-control" type="password" placeholder="Password" name="password">
                                        <input id="password_confirmation" class="form-control" type="password" placeholder="Repeat Password" name="password_confirmation">
                                        <input class="btn btn-default btn-register" type="button" value="Create account" name="commit" onclick="registerAjax()">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="forgot login-footer">
                            <span>Looking to
                                 <a href="javascript: showRegisterForm();">create an account</a>
                            ?</span>
                        </div>
                        <div class="forgot register-footer" style="display:none">
                            <span>Already have an account?</span>
                            <a href="javascript: showLoginForm();">Login</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div id="articles">
        <div>
            <h5>Latest Game Results</h5>
            <ul class="blogs">
                <li>
                    <img src="/images/woman-small.jpg" alt="Img">
                    <p>
                        <span id="thelatest">${latestGame.endTimeStr}</span> <b>${latestGame.winnerName}</b> Lorem ipsum dolor sit amet, consectetur
                        adipiscing elit. Proin vel pulvinar tellus.
                    </p>
                    </a>
                </li>
                <li>
                    <img src="/images/smoker-small.jpg" alt="Img">
                    <p>
                        <span>${latestGame2.endTimeStr}</span> <b>${latestGame2.winnerName}</b> Lorem ipsum dolor sit amet,
                        consectetur adipiscing elit. Proin vel pulvinar tellus.
                    </p>
                    </a>
                </li>
            </ul>
        </div>
        <div>
            <h5>Figure Series</h5>
            <ul class="illustrations">
                <li>
                    <img src="/images/viking-small.jpg" alt="Img"></a>
                </li>
                <li>
                    <img src="/images/zombie-small.jpg" alt="Img"></a>
                </li>
                <li>
                    <img src="/images/caveman-small.jpg" alt="Img">
                </li>
                <li>
                    <img src="/images/cook-small.jpg" alt="Img"></a>
                </li>
            </ul>
            <h5>Figure Series</h5>
            <ul class="illustrations">
                <li>
                    <img src="/images/pirate-small.jpg" alt="Img"></a>
                </li>
                <li>
                    <img src="/images/doctor-small.jpg" alt="Img"></a>
                </li>
                <li>
                    <img src="/images/vendor-small.jpg" alt="Img"></a>
                </li>
                <li>
                    <img src="/images/engineer-small.jpg" alt="Img"></a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div id="footer">
    <p class="uppercase">
        Copyright © 2017. ECNU NaiChaDaDui. All rights reserved.&nbsp;
    </p>

</div>
</div>

<script src="/bootstrap3/js/bootstrap.js" type="text/javascript"></script>
<script src="/js/login-register.js" type="text/javascript"></script>
</body>
</html>