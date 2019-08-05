$(document).ready(function () {
    // $.cookie("userid","lixiaotao",{expirers:7,path:'/'})

    // $.cookie("userid","sadoifjo",{expirers:7,path:'/'})
    // var userid = $.cookie("userid");

    var userid = '';
    //$.removeCookie('userid');

    $.ajax({
        url: "http://172.20.151.112:8066/Music_forum/usercenter",
        type: "get",
        dataType: "json",
        data: {
            "userid": userid
        },

        dataType: "json",
        success: function (data) {
            console.log(data)
            //写入数据
            showdata(data);
            //判断是不是自己的个人主页
            isSelf(data.isself,data.isfollow,data.user.fans,data.user.follownum)


        },
        error: function (err) {
            console.log(err);
            alert("网络似乎出现问题，请检查你的网络")
        }
    })
})

function showdata(data) {
    $("#userimg").src = data.user.imageid;

    $("#avatar").css('background-image', data.user.imageid);

    $("#loginOn-name").html(data.user.username);
    $("#username").html(data.user.username);
    var str = "<div id=\"fans\">粉丝：" + data.user.fans + "关注：" + data.user.follownum + "</div>"
    $("#avatarBack").append(str);
    //TODO  myart  mycollection
    var myart = data.listArticle;
    var mycollection = data.listCollection;
    var collectionLists = document.getElementsByClassName('collectionLists');
    var artLists = document.getElementsByClassName('artLists');
    var collectionName = document.getElementsByClassName('collectionName');
    var artName = document.getElementsByClassName('artName');
    console.log(myart[0].title)
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
};

function isSelf(isself,isfollow,fans,follow) {
    if (isself) {
        logout(); //退出登录
        alter();
        $("#addfans-box").css('display', "none");
    } else {
        //tudo 关注按钮 
        if (isfollow) {
            defan(fans,follow);
        } else {
            befan(fans,follow);
        }

        $("#out").css("display", "none");
    }
}

function logout() {
    $("#out").click(function () {
        if (confirm("确定要退出吗？")) {
            window.location.href = "home.html";
        }
    })

}

function alter() {

}

function befan(fans,follow) {
    $("#addfans-box").click(function () {
        $.ajax({
            url: "http://172.20.151.117:8066/Music_forum/follow",
            type: "GET",
            dataType: "json",
            data: {
                "followed":userid
            },
            success: function (data) {
                console.log(data)
                oAddfans.style.top = '-50px';
                odefans.style.top = '0px';
                fans+=1;
                showfans(fans ,follow);
                if (data.isfollow) {
                    defan(fans,follow);
                } else {
                    befan(fans,follow);
                }
            },
            error: function (err) {
                alert('网络错误' + err.status);
                console.log(err)
            }
        })
    })
}

function defan(fans,follow) {
    $("#addfans-box").click(function () {
        $.ajax({
            url: "http://172.20.151.117:8066/Music_forum/follow",
            type: "GET",
            dataType: "json",
            data: {
                "followed":userid
            },
            success: function (data) {
                console.log(data)
                oAddfans.style.top = '0px';
                odefans.style.top = '50px';
                fans-=1;
                showfans(fans,follow)
                if (data.isfollow) {
                    defan(fans,follow);
                } else {
                    befan(fans,follow);
                }
            },
            error: function (err) {
                alert('网络错误' + err.status);
                console.log(err)
            }
        })
    })
}
function showfans(fans,follow){
    var fans=document.getElementById('fans')
    fans.innerHTML="粉丝："+fans+"关注："+follow;
}