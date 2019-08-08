$(document).ready(function () {

    $.ajax({
        url: "http://localhost:8066/Music_forum/getUserIformation", // 获取登录信息,如果登录返回的data不为null
        type: "GET",
        dataType: "json",
        success: function (data) {
            if (data != null) {
                // 因为data不为空,所以需要将右上角的sign in 和 sign up隐藏起来,并展示登录用户头像
                //userid不为空 获取用户头像 用户昵称 id
                $("#login").css("display", "none");
                $(".loginOn").css("display", "block");
                $("#loginOn-name").html(data.username);
                $("#loginOn-image").attr("src", data.imageid);

                //点击发送ajax给后端并进行页面跳转,跳转到个人主页  后端存取userid
                $(".loginOn").click(function () {
                    //console.log(data.userid);
                    $.ajax({
                        url: "http://localhost:8066/Music_forum/jumpPage",
                        type: "GET",
                        data: {
                            "userid": data.userid
                        }
                    })
                    window.location.href = "http://localhost:8066/Music_forum/login-regist-writeText/myHomepage.html";
                })


            } else {
                //显示登录注册按钮
                $("#login").css("display", "block");
                $(".loginOn").css("display", "none");
            }
        }
    })

    // 向usercenter发送请求,获取"所查看"的用户的信息
    $.ajax({
        url: "http://localhost:8066/Music_forum/usercenter",
        type: "get",
        dataType: "json",
        //data: {
        //   "userid": userid
        //},

        dataType: "json",
        success: function (data) {
            console.log(data)
            //写入数据
            showdata(data);
            //判断是不是自己的个人主页

            isSelf(data.isself, data.isfollow, data.user.fans, data.follownum);

            //console.log(typeof (data.isself))
        },
        error: function (err) {
            console.log(err);
            alert("网络似乎出现问题，请检查你的网络")
        }
    })
})

function showdata(data) {
    $("#userimg").attr("src", data.imageid);

    $("#avatar").css('background-image', "url(" + data.user.imageid + ")");

    $("#loginOn-name").html(data.username);
    $("#username").html(data.user.username);
    var str = "<div id=\"fans\">粉丝：" + data.user.fans + "关注：" + data.follownum + "</div>"
    $("#avatarBack").append(str);
    //TODO  myart  mycollection
    var myart = data.listArticle;
    var mycollection = data.listCollection;
    var collectionLists = document.getElementsByClassName('collectionLists');
    var artLists = document.getElementsByClassName('artLists');
    var collectionName = document.getElementsByClassName('collectionName');
    var artName = document.getElementsByClassName('artName');
    //console.log(myart[0].title)
    for (var i = 0; i < 3; i++) {
        if (myart[i]) {
            artName[i].innerHTML = myart[i].title;
            artLists[i].style.backgroundImage = "url(" + myart[i].textimage + ")";
        } else {
            artLists[i].style.display = 'none';
        }
        if (mycollection[i]) {
            collectionName[i].innerHTML = mycollection[i].title;
            collectionLists[i].style.backgroundImage = "url(" + mycollection[i].textimage + ")";
        } else {
            collectionLists[i].style.display = 'none';
        }

    }
    for (var i = 0; i < 3; i++) {
        artLists[i].index = i;

        artName[i].onclick = artLists[i].onclick = function () {
            var a = this.index;
            $.ajax({
                url: "http://localhost:8066/Music_forum/jumpPage",
                type: "get",
                data: {
                    "thistext": myart[a].textid
                },
                success: function () {
                    window.location.href = "http://localhost:8066/Music_forum/login-regist-writeText/article.html";
                }
            })
        }
    }
    for (var i = 0; i < 3; i++) {
        collectionLists[i].index = i;

        collectionLists[i].onclick = function () {
            var a = this.index;
            $.ajax({
                url: "http://localhost:8066/Music_forum/jumpPage",
                type: "get",
                data: {
                    "thistext": mycollection[a].textid
                },
                success: function () {
                    window.location.href = "http://localhost:8066/Music_forum/login-regist-writeText/article.html";
                }
            })
        }
    }
};

var oAddfans = document.getElementById("addfans");
var odefans = document.getElementById("defans");

function isSelf(isself, isfollow, fans, follow) {
    //console.log("isself被调用");
    if (isself == "true") {
        //console.log("判断是个人主页");
        logout(); //退出登录
        alter();
        $("#addfans-box").css('display', "none");
    } else {
        //console.log("判断不是个人主页");
        //tudo 关注按钮 
        if (isfollow == "true") {

            oAddfans.style.top = '-50px';
            odefans.style.top = '0px';
        }
        // $("#addfans-box").click(function () {
        //
        //     var t = fans;
        //     if (isfollow == "true") {
        //         defan(fans, follow);
        //         isfollow = "false";
        //     } else {
        //         befan(fans, follow);
        //         isfollow = "true";
        //     }
        // })

        var t = fans;

        $("#addfans-box").click(function () {
            //console.log("添加关注被调用");
            if (isfollow != "false") {
                defan(t, follow);
                isfollow = "false";
                t -= 1;
            } else {
                befan(t, follow);
                isfollow = "true";
                t += 1;
            }
        })


        $("#out").css("display", "none");
    }
}

function logout() {
    $("#out").click(function () {
        if (confirm("确定要退出吗？")) {
            $.ajax({
                url: "http://localhost:8066/Music_forum/exit",
                type: "post"
            })
            window.location.href = "home.html";
        }
    })

}

function alter() {

}

function befan(fans, follow) {
    fans += 1;
    $.ajax({
        url: "http://localhost:8066/Music_forum/follow",
        type: "GET",
        dataType: "json",
        // data: {
        //     "followed":userid
        // },
    })
    oAddfans.style.top = '-50px';
    odefans.style.top = '0px';
    showfans(fans, follow);
}

function defan(fans, follow) {
    fans -= 1;
    $.ajax({
        url: "http://localhost:8066/Music_forum/follow",
        type: "GET",
        dataType: "json",
        // data: {
        //     "followed":userid
        // },
    })
    oAddfans.style.top = '0px';
    odefans.style.top = '50px';
    showfans(fans, follow)
}

function showfans(fans, follow) {
    var fansbox = document.getElementById('fans')
    fansbox.innerHTML = "粉丝：" + fans + "关注：" + follow;
}