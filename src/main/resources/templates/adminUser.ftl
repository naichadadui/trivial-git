<!DOCTYPE html>
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>答题大闯关-管理员与用户</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css">

    <link href="/css/pagination.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery-2.0.3.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap.js" type="text/javascript"></script>
    <script src="/js/jquery.twbsPagination.js" type="text/javascript"></script>
    <script src="/js/zzsc.js" type="text/javascript"></script>

    <link href="/css/allInfoTable.css" rel="stylesheet" type="text/css">
    <link href="/css/table.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
    <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="/js/adminUser.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/css/adminUser.css" />
    <link rel="stylesheet" href="/dist/css/select-theme-dark.css" />
    <link rel="stylesheet" href="/css/searchBar.css" />

    <link rel="stylesheet" type="text/css" href="/css/flavr.css"/>
    <script type="text/javascript" src="/js/flavr.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/questionStyle.css"/>

    <style type="text/css">
        select {
            font-size: 1em;
        }
    </style>

    <script>
        $(document).ready(function () {
            $('#pagination-demo').twbsPagination({
                totalPages: 35,
                visiblePages: 7,
                version: '1.1',
                onPageClick: function (event, page) {
                    $('#page-content').text('Page ' + page);
                }
            });

            $('#navigation').affix({
                offset: {
                    top: 200
                }
            });

            $('#visible-pages-example').twbsPagination({
                totalPages: 35,
                visiblePages: 10,
                version: '1.1'
            });


        });

    </script>

</head>


<div id="header">
    <div>
        <a href="adminUser.html" id="logo"><img src="images/logo.png" alt="LOGO"></a>
        <div id="navigation">
            <ul>
                <li>
                    <a href="adminGameRecord.html">gameRecord</a>
                </li>
                <li class="selected">
                    <a href="adminUser.html">User</a>
                </li>
                <li>
                    <a href="adminLog.html">Log</a>
                </li>
                <li>
                    <a href="adminQuestion.html">Question</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="selectDown">
    <select data-select-like-alignement="never" class="drop-select">
        <option value="">Default</option>
        <option value="United States">Score</option>
    </select>
</div>
<div id="searchContainer">
    <div class="search bar7">
        <form>
            <input type="text" placeholder="按邮箱搜索">
            <button type="submit"></button>
        </form>
    </div>
    <div class="search bar71">
        <form>
            <input type="text" placeholder="按昵称搜索">
            <button type="submit"></button>
        </form>
    </div>
</div>
<div id="deleteButton">
    <a class="btn btn-medium btn-red btn-radius" onclick="clickDelete()">Delete</a>
</div>
<table id="table">
    <thead>
    <tr>
        <th class="checkbox checkbox-primary">
            <input type="checkbox" class="styled" id="selectAll">
            <label for="selectAll">全选</label>
        </th>
        <th>ID</th>
        <th>昵称</th>
        <th>邮箱</th>
        <th>金币数</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
<br/>
<div class="pageGo">
    <ul id="visible-pages-example"></ul>
</div>
<script type="text/javascript" src="js/tether.js"></script>
<script src="dist/js/select.js"></script>
<script>
    $('select.drop-select').each(function(){
        new Select({
            el: this,
            selectLikeAlignment: $(this).attr('data-select-like-alignement') || 'auto',
            className: 'select-theme-dark'
        });
    });
</script>

</body>
</html>