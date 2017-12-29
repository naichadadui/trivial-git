<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>排行-答题大闯关</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css">
    <link rel="stylesheet" href="/css/styleNewThreeTable.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/table.css">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src='/js/table.js'></script>
    <script>
        table_data=${rankingData};
        var json =  eval("(" + table_data + ")");
        initRanking(json);
    </script>
</head>
<body>
<div id="header">
    <div>
        <a href="work" id="logo"><img src="/images/logo.png" alt="loading..."></a>
        <div id="navigation">
            <ul>
                <li>
                    <a href="work">Work</a>
                </li>
                <li>
                    <a href="blog">Profile</a>
                </li>
                <li class="selected">
                    <a href="ranking">Rankings</a>
                </li>
            </ul>
        </div>
    </div>
</div>

</br></br>

<div class="promos">
    <div class="promo first">
        <h4>No.2</h4>
        <ul class="features">
            <li class="brief">${numberTwo.score}</li>
            <li class="price">${numberTwo.name}</li>
        </ul>
    </div>
    <div class="promo second">
        <h4>No.3</h4>
        <ul class="features">
            <li class="brief">${numberThree.score}</li>
            <li class="price">${numberThree.name}</li>
        </ul>
    </div>
    <div class="promo third scale">
        <h4>No.1</h4>
        <ul class="features">
            <li class="brief">${numberOne.score}</li>
            <li class="price">${numberOne.name}</li>
        </ul>
    </div>
</div>

<br/>
<br/>
<br/>
<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
<br/><br/>
<table id="table">
    <thead>
    <tr>
        <th>排名</th>
        <th>昵称</th>
        <th>金币</th>
        <th>胜率</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>
<br/>

<div id="page">
    <div id="footer">
        <ul class="navigation">
            <li>
                <a href="work">Work</a>
            </li>
            <li>
                <a href="blog">Profile</a>
            </li>
            <li class="selected">
                <a href="ranking">Rankings</a>
            </li>
        </ul>
    </div>
</div>

</body>
</html>