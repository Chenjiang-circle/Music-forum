$(document).ready(function () {
    $("#loginBtn").click(function () {
        if ($("#username").val() == "") {
            $(".login_msg").html("请输入你的账户名")
        } else if ($("#password").val() == "") {
            $(".login_msg").html("请输入你的密码")
        } else {
            $.ajax({
                url: "http://172.20.151.112:8066/Music_forum/enterservlet",
                data: {
                    "userid": $("#username").val(),
                    "password": $("#password").val()
                },
                type: "get",
                success: function (str) {
                    var data=eval('('+str+')');
                    if (data.pass == true) {
                        alert("登入成功");
                        $.cookie("user",data.userid,{expirers:7,path:'/'})
                        $.cookie("image",data.url,{expirers:7,path:'/'})
                        //页面跳转
                        window.location.href = "./administrator.html"
                    } else {
                        $(".login_msg").html("账户名或密码有问题，请检查")
                    }

                },
                error: (err) => {
                    $(".login_msg").html("网络似乎有点问题")
                    console.log(err)
                }
            })
        }
    })
})