

function clickRoom(roomID) {
    var room = roomID.replace("img", "");
    $.ajax(
        {
            type:"post",
            url:"/api/game/enterRoom",
            timeout:8000,
            dataType:"json",
            data:{
                "roomId":room
            },

            success:function(data){
                window.location.href="/trivial/JapanRoom/"+room;
            },

            error:function(){
                alert("404!!");

            }
        })
}

function initGameHall(rooms){
    for(var i=0;i<rooms.length;i++){
        $("#img"+(i+1)).attr("src","/images/JapanSmall.jpg");
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

function nullHall(){
       for(var i=0;i<8;i++){
           $("#state"+(i+1)).html("空的房间，快来创建吧!");
       }
}