<!DOCTYPE html>
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>答题大闯关-管理员与用户</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css">

    <link href="/css/pagination.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery-2.0.3.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap.js" type="text/javascript"></script>
    <script src="/js/adminGameRecord.js" type="text/javascript"></script>
    <script src="/js/jquery.twbsPagination.gameRecord.js" type="text/javascript"></script>

    <link href="/css/allInfoTable.css" rel="stylesheet" type="text/css">
    <link href="/css/table.css" rel="stylesheet" type="text/css">

    <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/adminUser.css" />
    <link rel="stylesheet" href="/dist/css/select-theme-dark.css" />

    <link rel="stylesheet" href="/css/searchGameRecordBar.css" />

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
        <a href="adminUser" id="logo"><img src="/images/logo.png" alt="LOGO"></a>
        <div id="navigation">
            <ul>
                <li  class="selected">
                    <a href="adminGameRecord">gameRecord</a>
                </li>
                <li>
                    <a href="adminUser">User</a>
                </li>
                <li>
                    <a href="adminLog">Log</a>
                </li>
                <li>
                    <a href="adminQuestion">Question</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div id="searchContainer">
    <div class="search bar7">
        <form>
            <input type="text" placeholder="按赢家搜索">
            <button type="button" onclick="searchByEmail()"></button>
        </form>
    </div>
    <div class="search bar71">
        <form>
            <input type="text" placeholder="按结束时间搜索">
            <button type="button" onclick="searchByNickname()"></button>
        </form>
    </div>
    <div class="search bar711">
        <form>
            <input type="text" placeholder="按开始时间搜索">
            <button type="button" onclick="searchByNickname()"></button>
        </form>
    </div>
</div>

<table id="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>赢家</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
<br/>
<div class="pageGo">
    <ul id="visible-pages-example"></ul>
</div>

<script type="text/javascript" src="/js/tether.js"></script>
<script src="/dist/js/select.js"></script>
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