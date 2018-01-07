<!DOCTYPE html>
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>答题大闯关-管理员与用户</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css">

    <link href="/css/pagination.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery-2.0.3.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap.js" type="text/javascript"></script>
    <script src="/js/jquery.twbsPagination.question.js" type="text/javascript"></script>

    <link href="/css/allInfoTable.css" rel="stylesheet" type="text/css">
    <link href="/css/table.css" rel="stylesheet" type="text/css">

    <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="/js/adminQuestion.js" type="text/javascript"></script>

    <link rel="stylesheet" href="/css/adminUser.css" />
    <link rel="stylesheet" href="/dist/css/select-theme-dark.css" />
    <link rel="stylesheet" href="/css/searchQuestionBar.css" />
    <link rel="stylesheet" type="text/css" href="/css/flavr.css"/>
    <script type="text/javascript" src="/js/flavr.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/questionStyle.css"/>

    <style type="text/css">
        select {
            font-size: 1em;
        }
    </style>

    <script>
        table_data=${question};
        loadQuestion();
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
                <li>
                    <a href="adminUser">User</a>
                </li>
                <li>
                    <a href="adminLog">Log</a>
                </li>
                <li class="selected">
                    <a href="adminQuestion">Question</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="selectDown">
<select data-select-like-alignement="never" class="drop-select" id="selection">
    <option value="0">全部</option>
    <option value="1">科学知识</option>
    <option value="2">流行音乐</option>
    <option value="3">体育知识</option>
    <option value="4">计算机知识</option>
    <option value="5">其他</option>
</select>
</div>
<div id="searchContainer">
    <div class="search bar7">
        <form>
            <input type="text" placeholder="按问题搜索" id="searchQuestion">
            <button type="button" onclick="searchByContent()"></button>
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
        <th>问题类型</th>
        <th>问题文本</th>
        <th>正确答案</th>
    </tr>
    </thead>
    <tbody id="adminQuestionTbody">
    </tbody>
</table>
<br/>
<div class="pageGo">
    <ul id="visible-pages-example"></ul>
</div>
<div class="qdark-matter">
    <form action="" method="post" class="STYLE-NAME">
        <h1>Question Form
            <span>Please fill all the texts in the fields.</span>
        </h1>
        <label>
            <span>Question Content :</span>
            <input id="content" type="text" name="name" placeholder="QuestionContent" />
        </label>

        <label>
            <span>Right Answer :</span>
            <input id="right" type="email" name="email" placeholder="Right Answer" />
        </label>
        <label>
            <span>Wrong Answer1 :</span>
            <input id="wrong1" type="email" name="email" placeholder="Wrong Answer1 " />
        </label>
        <label>
            <span>Wrong Answer2 :</span>
            <input id="wrong2" type="email" name="email" placeholder="Wrong Answer2 " />
        </label>
        <label>
            <span>Wrong Answer3 :</span>
            <input id="wrong3" type="email" name="email" placeholder="Wrong Answer3 " />
        </label>

        <label>
            <span>Type :</span><select name="selection" id="qselection">
            <option value="1">科学知识</option>
            <option value="2">流行音乐</option>
            <option value="3">体育知识</option>
            <option value="4">计算机知识</option>
            <option value="5">其他</option>
        </select>
        </label>
        <label>
            <span></span>
            <input type="button" class="qbutton" onclick="newQuestion()" value="Send" />
        </label>
    </form>
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