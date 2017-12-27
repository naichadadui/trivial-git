<!DOCTYPE html>

<html>
<head>
    <link rel="stylesheet" href="/css/style.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/table.css">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src='/js/gameHistory.js'></script>
    <script>
        var table_data =   ${userGameHistory} ;
        var json =  eval("(" + table_data + ")");
        initData(json);
    </script>
</head>
<body>
<div id="header">
    <div>
        <a href="#" id="logo"><img src="/images/logo.png" alt="LOGO"></a>
        <div id="navigation">
            <ul>
                <li>
                    <a href="work">Work</a>
                </li>
                <li class="selected">
                    <a href="blog">Profile</a>
                </li>
                <li>
                    <a href="ranking">Rankings</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div id="page">
    <div id="contents">
        <div id="welcome">
            <img src="/images/artist-small.png" alt="Welcome">
            <p>
                您好! ${user.name}
            </p>
            <p>
                您的当前胜率为80% 所得金币数为${user.score}$
            </p>
        </div>
    </div>
</div>

<table id="table">
    <thead>
    <tr>
        <th>游戏编号</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>得分情况</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>
<br/><br/>
<div id="pagination">
    <div>
        <a href="#">Prev</a> <a href="#" class="selected">1</a> <a href="#">2</a> <a href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">6</a> <a href="#" class="last-child">Next</a>
    </div>
</div>
</body>
</html>