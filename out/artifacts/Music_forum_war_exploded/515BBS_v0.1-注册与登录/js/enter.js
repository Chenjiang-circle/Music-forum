$(document).ready(function(){
    $("#login-submit").click(function(){
        
        $.ajax({
            url:"",
            type:"POST",
            datatype:"json",
            data:{
                email:$("#enterEmail").val(),
                password:$("#enterPassword").val()
            },
            success:function(data){
                if(data.success){
                    window.location.href="#";
                    //跳转到首页
                }else{
                    
                }
            },
            error:function(jqXHR){
                alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
            }
        })
    })
})