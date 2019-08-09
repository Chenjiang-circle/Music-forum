$('.ui-choose').ui_choose();

$(document).ready(function(){
    $.ajax({
        url:"http://localhost:8066/Music_forum/getUserIformation",
        type:"GET",
        dataType:"json",
        success:function (data) {
            if(data!=null){
                //userid不为空 获取用户头像 用户昵称 id
                $("#login").css("display","none");
                $(".loginOn").css("display","block");
            }else{
                //显示登录注册按钮
                $("#login").css("display","block");
                $(".loginOn").css("display","none");
            }
        }
    })
    var v = $('#login-form').validate({
        rules:{
            username:{
                required:true,
                minlength:2,
                maxlength:10
            },
            password:{
                required:true,
                minlength:2,
                maxlength:10
            },
            userid:{
                required:true,
                email:true,
            },
            confirmPassword:{
                required:true,
                equalTo:"#loginPassword"
            },
        },

        messages:{
            username:{
                required:"<br><img src='https://wx2.sinaimg.cn/mw690/a4629f5fly1g58l9s3dylj201r023748.jpg' width='30px'>这位亲，请设置昵称哦",
                minlength:"<br><img src='https://wx2.sinaimg.cn/mw690/a4629f5fly1g58l9s3dylj201r023748.jpg' width='30px'>这位亲，昵称字数要在2个以上哦",
                maxlength:"<br><img src='https://wx2.sinaimg.cn/mw690/a4629f5fly1g58l9s3dylj201r023748.jpg' width='30px'>这位亲，昵称字数要在10个以下哦"

            },
            password:{
                required:"<br><img src='https://wx2.sinaimg.cn/mw690/a4629f5fly1g58l9s3dylj201r023748.jpg' width='30px'>这位亲，请设置密码哦",
                minlength:"<br><img src='https://wx2.sinaimg.cn/mw690/a4629f5fly1g58l9s3dylj201r023748.jpg' width='30px'>这位亲，密码字数要在2个以上哦",
                maxlength:"<br><img src='https://wx2.sinaimg.cn/mw690/a4629f5fly1g58l9s3dylj201r023748.jpg' width='30px'>这位亲，密码字数要在10个以下哦"
            },
            userid:{
                required:"<br><img src='https://wx2.sinaimg.cn/mw690/a4629f5fly1g58l9s3dylj201r023748.jpg' width='30px'>这位亲，请设置邮箱哦",

                email:"<br><img src='https://wx2.sinaimg.cn/mw690/a4629f5fly1g58l9s3dylj201r023748.jpg' width='30px'>这位亲，请填写正确邮箱格式哦",

            },
            confirmPassword:{
                required:"<br><img src='https://wx2.sinaimg.cn/mw690/a4629f5fly1g58l9s3dylj201r023748.jpg' width='30px'>这位亲，请再输入一次密码哦",
                equalTo:" <br><img src='https://wx2.sinaimg.cn/mw690/a4629f5fly1g58l9s3dylj201r023748.jpg' width='30px'>这位亲，两次输入的密码不一致呀",
            },
        },

        submitHander:function(form){
            
        }
    })//验证表单格式

    $("#login-submit").click(function(){
        if($('#login-form').valid()){
            $.ajax({
                type:"GET",
                url:"http://localhost:8066/Music_forum/registerservlet",
                data:{
                    username:$("#username").val(),
                    sex:$("#sex").val(),
                    userid:$("#loginEmail").val(),
                    password:$("#loginPassword").val()
                },
                dataType:"json",
                success:function(data){//请求成功，data为后台数据
                    if(data.success){
                        window.location.href="http://localhost:8066/Music_forum/login-regist-writeText/enter.html";
                    }
                    else{
                        alert("邮箱已存在!");
                    }
                },
                error:function(jqXHR){
                    alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
                }
            })
         }
    })//发送注册数据

    $("#loginPassword").focus(function(){
        $("#flytips").fadeIn();
    });
    $("#loginPassword").blur(function(){
        $("#flytips").fadeOut();
    })
})

