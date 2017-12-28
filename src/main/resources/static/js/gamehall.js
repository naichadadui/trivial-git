var userId;
var rooms;
function clickRoom(roomID) {
    var room = roomID.replace("room", "");
    $.ajax(
        {
            type:"post",
            url:"/api/game/enterRoom",
            timeout:8000,
            dataType:"json",
            data:{
                "roomId":roomID
            },

            success:function(data){
                if(data){
                        window.location.href="";
                    }
            },

            error:function(){
                alert("404!!");
            }
        })
}
initGameHall();
function initGameHall(){
    for(var i=0;i<rooms.length;i++){
        $("#room"+(i+1)).attr("src","/images/JapanSmall.jpg");
        $("#ratio"+(i+1)).html(rooms[i].players.length +"/4");
        if(rooms[i].status==0)
        {
            $("#state"+(i+1)).html("等待中");
        }else {
            $("#state"+(i+1)).html("正在游戏中");
        }
    }
    for(var i=rooms.length;i<8;i++){
        $("#state"+(i+1)).html("空的房间，快来创建吧!");
    }
}