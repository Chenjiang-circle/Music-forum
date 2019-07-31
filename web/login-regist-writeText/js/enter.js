$(document).ready(function(){
    $('#enter-form').validate({
        rules:{
            userid:{
                required:true,
                /*remote:{
                    url:"http://localhost:8066/Music_forum/signinservlet",
                    type:"POST",
                    datatype:"json",
                    data:{
                        userid:$("#enterEmail").val(),
                    }
                },//url,查询该用户名（邮箱）是否在数据库中存在，布尔值*/
                email:true
            },
            password:{
                required:true,
            }
        },
        messages:{
            userid:{
                required:"<br><img src='https://wx2.sinaimg.cn/mw690/a4629f5fly1g58l9s3dylj201r023748.jpg' width='30px'>这位亲，请输入登录邮箱哦",
                //remote:"<br><img src='https://wx2.sinaimg.cn/mw690/a4629f5fly1g58l9s3dylj201r023748.jpg' width='30px'>这位亲，您的邮箱还未注册哦",
                email:"<br><img src='https://wx2.sinaimg.cn/mw690/a4629f5fly1g58l9s3dylj201r023748.jpg' width='30px'>这位亲，请输入正确的邮箱格式哦",
            },
            password:{
                required:"<br><img src='https://wx2.sinaimg.cn/mw690/a4629f5fly1g58l9s3dylj201r023748.jpg' width='30px'>这位亲，请输入密码哦",
            }
        }
    })

    $("#enter-submit").click(function(){
        if($('#enter-form').valid()){
            $.ajax({
                url:"http://172.20.151.117:8066/Music_forum/signinservlet",
                type:"GET",
                data:{
                    userid:$("#enterEmail").val(),
                    password:$("#enterPassword").val()
                },
                datatype:"json",
                success:function(data){
                    if(data.success){
                        window.location.href="http://172.20.151.117:8066/Music_forum/FrontEnd-demo/head&foot/index.html";
                        //跳转到首页
                    }else{
                        alert("用户名或密码错误！");
                    }
                },
                error:function(jqXHR){
                    alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
                }
            })
        }
    })
})