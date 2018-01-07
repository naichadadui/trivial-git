<!DOCTYPE html>

<html>
<head>
<#include "title.ftl">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/gamehall.js"></script>
        <script type="text/javascript">
                $(document).ready( function() {
                    var rooms=${rooms};

                    if(rooms.length!=0)
                    {
                        initGameHall(rooms);
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
                    <img src="/images/room/room1.jpg" id="img1" onclick="clickRoom(this.id)">
                    <h4 id="title1">room No.1</h4>
                    <h4 id="ratio1"></h4>
                    <h4 id="state1"></h4>
                </li>
                <li id="room2">
                    <img src="/images/room/room2.jpg" id="img2" onclick="clickRoom(this.id)">
                    <h4 id="title2">room No.2</h4>
                    <h4 id="ratio2"></h4>
                    <h4 id="state2"></h4>
                </li>
                <li id="room3">
                   <img src="/images/room/room3.jpg"  id="img3" onclick="clickRoom(this.id)">
                    <h4 id="title3">room No.3</h4>
                    <h4 id="ratio3"></h4>
                    <h4 id="state3"></h4>
                </li>
                <li id="room4">
                    <img src="/images/room/room4.jpg" id="img4" onclick="clickRoom(this.id)">
                    <h4 id="title4">room No.4</h4>
                    <h4 id="ratio4"></h4>
                    <h4 id="state4"></h4>
                </li>
                <li id="room5">
                    <img src="/images/room/room5.jpg" id="img5" onclick="clickRoom(this.id)">
                    <h4 id="title5">room No.5</h4>
                    <h4 id="ratio5"></h4>
                    <h4 id="state5"></h4>
                </li>
                <li id="room6">
                    <img src="/images/room/room6.jpg" id="img6" onclick="clickRoom(this.id)">
                    <h4 id="title6">room No.6</h4>
                    <h4 id="ratio6"></h4>
                    <h4 id="state6"></h4>
                </li>
                <li id="room7">
                    <img src="/images/room/room7.jpg" id="img7" onclick="clickRoom(this.id)">
                    <h4 id="title7">room No.7</h4>
                    <h4 id="ratio7"></h4>
                    <h4 id="state7"></h4>
                </li>
                <li id="room8">
                    <img src="/images/room/room8.jpg" id="img8" onclick="clickRoom(this.id)">
                    <h4 id="title8">room No.8</h4>
                    <h4 id="ratio8"></h4>
                    <h4 id="state8"></h4>
                </li>
                <li id="room9">
                    <img src="/images/room/room1.jpg" id="img9" onclick="clickRoom(this.id)">
                    <h4 id="title9">room No.9</h4>
                    <h4 id="ratio9"></h4>
                    <h4 id="state9"></h4>
                </li>
                <li id="room10">
                    <img src="/images/room/room2.jpg" id="img10" onclick="clickRoom(this.id)">
                    <h4 id="title10">room No.10</h4>
                    <h4 id="ratio10"></h4>
                    <h4 id="state10"></h4>
                </li>
                <li id="room11">
                    <img src="/images/room/room3.jpg"  id="img11" onclick="clickRoom(this.id)">
                    <h4 id="title11">room No.11</h4>
                    <h4 id="ratio11"></h4>
                    <h4 id="state11"></h4>
                </li>
                <li id="room12">
                    <img src="/images/room/room4.jpg" id="img12" onclick="clickRoom(this.id)">
                    <h4 id="title12">room No.12</h4>
                    <h4 id="ratio12"></h4>
                    <h4 id="state12"></h4>
                </li>
                <li id="room13">
                    <img src="/images/room/room5.jpg" id="img13" onclick="clickRoom(this.id)">
                    <h4 id="title13">room No.13</h4>
                    <h4 id="ratio13"></h4>
                    <h4 id="state13"></h4>
                </li>
                <li id="room14">
                    <img src="/images/room/room6.jpg" id="img14" onclick="clickRoom(this.id)">
                    <h4 id="title14">room No.14</h4>
                    <h4 id="ratio14"></h4>
                    <h4 id="state14"></h4>
                </li>
                <li id="room15">
                    <img src="/images/room/room7.jpg" id="img15" onclick="clickRoom(this.id)">
                    <h4 id="title15">room No.15</h4>
                    <h4 id="ratio15"></h4>
                    <h4 id="state15"></h4>
                </li>
                <li id="room16">
                    <img src="/images/room/room8.jpg" id="img16" onclick="clickRoom(this.id)">
                    <h4 id="title16">room No.16</h4>
                    <h4 id="ratio16"></h4>
                    <h4 id="state16"></h4>
                </li>
                <li id="room17">
                    <img src="/images/room/room1.jpg" id="img17" onclick="clickRoom(this.id)">
                    <h4 id="title17">room No.17</h4>
                    <h4 id="ratio17"></h4>
                    <h4 id="state17"></h4>
                </li>
                <li id="room18">
                    <img src="/images/room/room2.jpg" id="img18" onclick="clickRoom(this.id)">
                    <h4 id="title18">room No.18</h4>
                    <h4 id="ratio18"></h4>
                    <h4 id="state18"></h4>
                </li>
                <li id="room19">
                    <img src="/images/room/room3.jpg"  id="img19" onclick="clickRoom(this.id)">
                    <h4 id="title19">room No.19</h4>
                    <h4 id="ratio19"></h4>
                    <h4 id="state19"></h4>
                </li>
                <li id="room20">
                    <img src="/images/room/room4.jpg" id="img20" onclick="clickRoom(this.id)">
                    <h4 id="title20">room No.20</h4>
                    <h4 id="ratio20"></h4>
                    <h4 id="state20"></h4>
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