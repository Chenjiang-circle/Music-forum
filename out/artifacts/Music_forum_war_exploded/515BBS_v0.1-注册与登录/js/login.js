$('.ui-choose').ui_choose();

$(document).ready(function(){
    $('#login-form').validate({
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
        }
    })//验证表单格式

    $("#login-submit").click(function(){
        $.ajax({
            type:"POST",
            url:"http://172.20.151.112:8066/Music_forum/registerservlet",
            data:{
                username:$("#username").val(),
                sex:$("#sex").val(),
                userid:$("#loginEmail").val(),
                password:$("#loginPassword").val()
            },
            dataType:"json",
            success:function (data) {
                if (data.success){
                    window.location.href="#";
                } else{
                    alert("OOOOPS! 服务器出现一个小问题：" + data.msg);
                }
            },
            error:function (jqXHR) {
                alert("OOPS! 服务器出现一个小问题：" + jqXHR.status);
            }
        })
    })//发送注册数据
})

