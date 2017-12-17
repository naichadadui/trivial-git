<!DOCTYPE html>
<html>
<head>
    <#include "title.ftl">
</head>
<body>
    <div id="header">
        <#include "head.ftl">
    </div>
    <div id="page">
    <div id="contents">
        <div id="welcome">
            <p>
                Hello! Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut in mi sit amet felis accumsan
                dignissim ut vel est. Duis molestie libero ut tortor commodo bibendum.
            </p>
            <img src="/images/artist-small.png" alt="Welcome">
            <p>
                Suspendisse laoreet, nisi a fermentum mollis, dui elit feugiat leo, vel commodo leo justo at felis.
                Aenean mollis leo nisl, vel luctus sem. Aliquam vitae sem velit, vitae sodales augue.
            </p>
        </div>
        <div id="featured">
            <h5>Recent Illustrations</h5>
            <ul>
                <li>
                    <a href="work"><img src="/images/shoes.jpg" alt="Illustration"></a>
                    <h4>Lorem Ipsum</h4>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel pulvinar tellus.
                    </p>
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
            </ul>
        </div>
        <div id="articles">
            <div>
                <h5>Latest Blog Entries</h5>
                <ul class="blogs">
                    <li>
                        <a href="blog"> <img src="/images/smoker-small.jpg" alt="Img">
                            <p>
                                <span>01 March 2012</span> <b>Blog Title 1</b> Lorem ipsum dolor sit amet, consectetur
                                adipiscing elit. Proin vel pulvinar tellus.
                            </p>
                        </a>
                    </li>
                    <li>
                        <a href="blog"> <img src="/images/woman-small.jpg" alt="Img">
                            <p>
                                <span>27 February 2012</span> <b>Blog Title 2</b> Lorem ipsum dolor sit amet,
                                consectetur adipiscing elit. Proin vel pulvinar tellus.
                            </p>
                        </a>
                    </li>
                </ul>
            </div>
            <div>
                <h5>Lorem Ipsum</h5>
                <ul class="illustrations">
                    <li>
                        <a href="work"><img src="/images/viking-small.jpg" alt="Img"></a>
                    </li>
                    <li>
                        <a href="work"><img src="/images/zombie-small.jpg" alt="Img"></a>
                    </li>
                    <li>
                        <a href="work"><img src="/images/caveman-small.jpg" alt="Img"></a>
                    </li>
                    <li>
                        <a href="work"><img src="/images/cook-small.jpg" alt="Img"></a>
                    </li>
                </ul>
                <h5>Lorem Ipsum</h5>
                <ul class="illustrations">
                    <li>
                        <a href="work"><img src="/images/pirate-small.jpg" alt="Img"></a>
                    </li>
                    <li>
                        <a href="work"><img src="/images/doctor-small.jpg" alt="Img"></a>
                    </li>
                    <li>
                        <a href="work"><img src="/images/vendor-small.jpg" alt="Img"></a>
                    </li>
                    <li>
                        <a href="work"><img src="/images/engineer-small.jpg" alt="Img"></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div id="footer">
        <#include "foot.ftl">
    </div>
</div>
<script>
    //这里写js函数
</script>
</body>

</html>
