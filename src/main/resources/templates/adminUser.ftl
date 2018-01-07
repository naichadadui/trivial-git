<!DOCTYPE html>
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>答题大闯关-管理员与用户</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css">

    <link href="/css/pagination.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery-2.0.3.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap.js" type="text/javascript"></script>
    <script src="/js/adminUser.js" type="text/javascript"></script>
    <script src="/js/jquery.twbsPagination.user.js" type="text/javascript"></script>

    <link href="/css/allInfoTable.css" rel="stylesheet" type="text/css">
    <link href="/css/table.css" rel="stylesheet" type="text/css">

    <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <link rel="stylesheet" href="/css/adminUser.css" />
    <link rel="stylesheet" href="/dist/css/select-theme-dark.css" />
    <link rel="stylesheet" href="/css/searchUserBar.css" />

    <link rel="stylesheet" type="text/css" href="/css/flavr.css"/>
    <script type="text/javascript" src="/js/flavr.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/questionStyle.css"/>

    <style type="text/css">
        select {
            font-size: 1em;
        }
    </style>

    <script>
        table_data=${userList};
        loadAdminUser();

        var totalPage=0;
        var maxTotalPageNumber=0;
        maxTotalPageNumber=${maxPageNumber};
        if(maxTotalPageNumber<=10){
            totalPage=maxTotalPageNumber;
        }else{
            totalPage=10;
        }

        $(document).ready(function () {

            $('#pagination-demo').twbsPagination({
                totalPages: ${maxPageNumber},
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
                totalPages: maxTotalPageNumber,
                visiblePages: totalPage,
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
                <li>
                    <a href="adminGameRecord">gameRecord</a>
                </li>
                <li class="selected">
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
            <input type="text" placeholder="按邮箱搜索" id="searchEmail">
            <button type="button" onclick="searchByEmail()"></button>
        </form>
    </div>
    <div class="search bar71">
        <form>
            <input type="text" placeholder="按昵称搜索" id="nickName">
            <button type="button" onclick="searchByNickname()"></button>
        </form>
    </div>
</div>
<table id="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>昵称</th>
        <th>邮箱</th>
        <th>金币数</th>
    </tr>
    </thead>
    <tbody id="adminUserTbody">
    </tbody>
</table>
<br/>
<div class="pageGo">
    <ul id="visible-pages-example"></ul>
</div>
<script type="text/javascript" src="/js/tether.js"></script>
<script src="/dist/js/selectAdminUser.js"></script>
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