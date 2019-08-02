$(document).ready(function () {
    $("#loginBtn").click(function () {
        if ($("#username").val() == "") {
            $(".login_msg").html("请输入你的账户名")
        } else if ($("#password").val() == "") {
            $(".login_msg").html("请输入你的密码")
        } else {
            $.ajax({
                url: "",
                data: {
                    "username": $("#username").val(),
                    "password": $("#password").val()
                },
                type: "get",
                success: function (data) {
                    
                    $.cookie("user",$("#username").val(),{expirers:7,path:'/'})
                    window.document.f.action = "./administrator.html";
                    window.document.f.submit();
                    if (data.pass == true) {
                        alert("登入成功");
                        window.document.f.action = "./administrator.html";
                        window.document.f.submit();
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