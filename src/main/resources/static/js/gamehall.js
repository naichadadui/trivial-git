var userId;
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

function initGameHall(){
    var roomHall=document.getElementById("gallery");

}