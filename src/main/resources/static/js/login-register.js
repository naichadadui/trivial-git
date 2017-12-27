/*
 *
 * login-register modal
 * Autor: Creative Tim
 * Web-autor: creative.tim
 * Web script: #
 * 
 */

var sorcket;

function showRegisterForm(){
    $('.loginBox').fadeOut('fast',function(){
        $('.registerBox').fadeIn('fast');
        $('.login-footer').fadeOut('fast',function(){
            $('.register-footer').fadeIn('fast');
        });
        $('.modal-title').html('Register with');
    }); 
    $('.error').removeClass('alert alert-danger').html('');
       
}
function showLoginForm(){
    $('#loginModal .registerBox').fadeOut('fast',function(){
        $('.loginBox').fadeIn('fast');
        $('.register-footer').fadeOut('fast',function(){
            $('.login-footer').fadeIn('fast');    
        });
        
        $('.modal-title').html('Login with');
    });       
     $('.error').removeClass('alert alert-danger').html(''); 
}

function openLoginModal(){
    showLoginForm();
    setTimeout(function(){
        $('#loginModal').modal('show');    
    }, 230);
    
}
function openRegisterModal(){
    showRegisterForm();
    setTimeout(function(){
        $('#loginModal').modal('show');    
    }, 230);
    
}

function loginAjax(){
    var email=$("#email").val();
    var password=$("#password").val();
    if(email.length==0||password==0)
        shakeModal();
    $.ajax(
        {
            type:"post",
            url:"/api/homepage/login",
            timeout:8000,
            dataType:"json",
            data:{
                "email":email,
                "password":password
            },


            success:function(data){
                if(data){

                    if(data.userId=='0'){
                        shakeModal();
                    }
                    else if(data.userId=='-1'){
                        shakeModal();
                    }else{
                      //  makeConnection();
                        window.location.href="work";
                    }
                }
            },

            error:function(){
                shakeModal();
            }
        }
    )
    /*   Remove this comments when moving to server
    $.post( "/login", function( data ) {
            if(data == 1){
                window.location.replace("/home");            
            } else {
                 shakeModal(); 
            }
        });
    */

/*   Simulate error message from the server   */

}

function registerAjax(){
    var email=$("#emailR").val();
    var nickname=$("#nickname").val();
    var password=$("#passwordR").val();
    var password_comfirmation=$("#password_confirmation").val();

    if(email.length==0||nickname.length==0||password.length==0||password_comfirmation.length==0||(password!==password_comfirmation))
        shakeModal();

    $.ajax(
        {
            type:"post",
            url:"/api/homepage/register",
            timeout:8000,
            dataType:"json",
            data:{
                "email":email,
                "nickname":nickname,
                "password":password
            },

            success:function(data){
                if(data){
                    if(data.userId==='0'){
                        shakeModal();
                    }
                    else if(data.userId==='-1'){
                       shakeModal();
                    }else{
                        window.location.href="work";
                    }
                }
            },

            error:function(){
                shakeModal();
            }
        })
}

function shakeModal(){
    $('#loginModal .modal-dialog').addClass('shake');
             $('.error').addClass('alert alert-danger').html("Invalid email/password combination");
             $('input[type="password"]').val('');
             setTimeout( function(){ 
                $('#loginModal .modal-dialog').removeClass('shake'); 
    }, 1000 ); 
}

function makeConnection(userId) {
    var ipURL = "ws://localhost:10000/" + "webSocket/0/" + userId;
    try {
        socket=new WebSocket(ipURL);
    }catch(e) {
        alert("error happend");
        close;
    }
    socket.onopen = sOpen;
    socket.onerror = sError;
    socket.onmessage= sMessage;
    socket.onclose= sClose;
}

    function sOpen(){
        alert('connect success!');
    }
    function sError(e){
        alert("error " + e);
    }
    function sMessage(msg){
        alert('server says:' + msg);
    }
    function sClose(e){
        alert("connect closed:" + e.code);
    }
    function Send(){
        socket.send(document.getElementById("msg").value);
    }

    function Close(){
        socket.close();
    }



   