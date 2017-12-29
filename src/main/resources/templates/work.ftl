<!DOCTYPE html>

<html>
<head>
<#include "title.ftl">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script src="/js/gamehall.js"></script>
        <script type="text/javascript">
                $(document).ready( function() {
                    var rooms=${rooms};
                    if(!rooms)
                    {
                        var json = eval("(" + rooms + ")");
                        initGameHall(json);
                    }
                    else{
                        nullHall();
                    }
                });
    </script>
</head>
<body>
<div id="header">
    <div>
        <a href="#" id="logo"><img src="/images/logo.png" alt="LOGO"></a>
        <div id="navigation">
            <ul>
                <li class="selected">
                    <a href="work">Work</a>
                </li>
                <li >
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
        <div>
            <h5>Recent Illustrations</h5>
            <ul id="gallery">
                <li id="room1">
                    <img src="/images/shoes.jpg" id="img1" onclick="clickRoom(this.id)">
                    <h4 id="title1">room No.1</h4>
                    <h4 id="ratio1"></h4>
                    <h4 id="state1"></h4>
                </li>
                <li id="room2">
                    <img src="/images/zombie.jpg" id="img2" onclick="clickRoom(this.id)">
                    <h4 id="title2">room No.2</h4>
                    <h4 id="ratio2"></h4>
                    <h4 id="state2"></h4>
                </li>
                <li id="room3">
                   <img src="/images/doctor.jpg"  id="img3" onclick="clickRoom(this.id)">
                    <h4 id="title3">room No.3</h4>
                    <h4 id="ratio3"></h4>
                    <h4 id="state3"></h4>
                </li>
                <li id="room4">
                    <img src="/images/scientist.jpg" id="img4" onclick="clickRoom(this.id)">
                    <h4 id="title4">room No.4</h4>
                    <h4 id="ratio4"></h4>
                    <h4 id="state4"></h4>
                </li>
                <li id="room5">
                    <img src="/images/viking.jpg" id="img5" onclick="clickRoom(this.id)">
                    <h4 id="title5">room No.5</h4>
                    <h4 id="ratio5"></h4>
                    <h4 id="state5"></h4>
                </li>
                <li id="room6">
                    <img src="/images/pirate.jpg" id="img6" onclick="clickRoom(this.id)">
                    <h4 id="title6">room No.6</h4>
                    <h4 id="ratio6"></h4>
                    <h4 id="state6"></h4>
                </li>
                <li id="room7">
                    <img src="/images/caveman.jpg" id="img7 " onclick="clickRoom(this.id)">
                    <h4 id="title7">room No.7</h4>
                    <h4 id="ratio7"></h4>
                    <h4 id="state7"></h4>
                </li>
                <li id="room8">
                    <img src="/images/vendor.jpg" id="img8" onclick="clickRoom(this.id)">
                    <h4 id="title8">room No.8</h4>
                    <h4 id="ratio8"></h4>
                    <h4 id="state8"></h4>
                </li>

          <!--      <li>
                    <img src="/images/JapanSmall.jpg" id="room1" onclick="clickRoom(this.id)" alt="Illustration">
                    <h4>room No.1</h4>
                    <h4>3/4</h4>
                    <h4>游戏进行中</h4>
                </li>
                <li>
                    <a href="work"><img src="/images/zombie.jpg" alt="Illustration"></a>
                    <h4>Lorem Ipsum</h4>
                    <p>
                        Pellentesque ut felis nec est vestibulum viverra. Vestibulum a diam eget metus cursus porttitor
                        eu ac elit.
                    </p>
                </li>
                <li>
                    <a href="work"><img src="/images/doctor.jpg" alt="Illustration"></a>
                    <h4>Lorem Ipsum</h4>
                    <p>
                        Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae.
                    </p>
                </li>
                <li>
                    <a href="work"><img src="/images/scientist.jpg" alt="Illustration"></a>
                    <h4>Lorem Ipsum</h4>
                    <p>
                        Aenean vitae massa nulla. Ut tincidunt interdum dui at lobortis.
                    </p>
                </li>
                <li>
                    <a href="work"><img src="/images/viking.jpg" alt="Illustration"></a>
                    <h4>Lorem Ipsum</h4>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel pulvinar tellus.
                    </p>
                </li>
                <li>
                    <a href="work"><img src="/images/pirate.jpg" alt="Illustration"></a>
                    <h4>Lorem Ipsum</h4>
                    <p>
                        Pellentesque ut felis nec est vestibulum viverra. Vestibulum a diam eget metus cursus porttitor
                        eu ac elit.
                    </p>
                </li>
                <li>
                    <a href="work"><img src="/images/caveman.jpg" alt="Illustration"></a>
                    <h4>Lorem Ipsum</h4>
                    <p>
                        Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae.
                    </p>
                </li>
                <li>
                    <a href="work"><img src="/images/vendor.jpg" alt="Illustration"></a>
                    <h4>Lorem Ipsum</h4>
                    <p>
                        Aenean vitae massa nulla. Ut tincidunt interdum dui at lobortis.
                    </p>
                </li>  -->
            </ul>
            <div id="pagination">
                <div>
                    <a href="#">Prev</a> <a href="#" class="selected">1</a> <a href="#">2</a> <a href="#">3</a> <a
                        href="#">4</a> <a href="#">5</a> <a href="#">6</a> <a href="#" class="last-child">Next</a>
                </div>
            </div>
        </div>
    </div>
    <div id="footer">
        <ul class="navigation">
               <li class="selected">
                    <a href="work">Work</a>
                </li>
                <li>
                    <a href="blog">Profile</a>
                </li>
                <li>
                    <a href="ranking">Rankings</a>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>