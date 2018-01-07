
function clickRoom(roomID) {
    var room = roomID.replace("img", "");
    $.ajax(
        {
            type: "post",
            url: "/api/game/enterRoom",
            timeout: 8000,
            dataType: "json",
            data: {
                "roomId": room
            },

            success: function (data) {
                window.location.href = "/trivial/JapanRoom/" + room;
            },

            error: function () {
                alert("404!!");

            }
        })
}

function initGameHall(rooms) {
    var isRealRoom = false;
    for (var i = 1; i <= 40; i++) {
        isRealRoom=false;
        for(var j=0;j<rooms.length;j++){
            if(i==rooms[j].roomId){
                isRealRoom=true;
                $("#img" + i).attr("src", "/images/JapanSmall.jpg");
                $("#ratio" + i).html(rooms[j].players.length + "/4");
                if (rooms[j].status == 0) {
                    $("#state" + i).html("等待中");
                } else {
                    $("#state" + i).html("正在游戏中");
                }
            }
        }
        if(!isRealRoom){
            $("img"+i).attr("src","/images/room/room"+((i%8)+1)+".jpg");
            $("#ratio"+i).hide();
            $("#state" + i).html("空的房间，快来创建吧!");
        }
    }
}

function nullHall() {
    for (var i = 0; i < 8; i++) {
        $("#state" + (i + 1)).html("空的房间，快来创建吧!");
    }
}