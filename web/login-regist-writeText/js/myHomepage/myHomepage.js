$(document).ready(function () {
    var userid = '2464792469@qq.com';
    $.ajax({
        url: "http://172.20.151.117:8066/Music_forum/usercenter",
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
            isSelf(data.isself,data.isfollow,data.user.fans,data.follownum)
            console.log(typeof (data.isself))

        },
        error: function (err) {
            console.log(err);
            alert("网络似乎出现问题，请检查你的网络")
        }
    })
})

function showdata(data) {
    $("#userimg").attr("src",data.imageid);

    $("#avatar").css('background-image', "url("+data.user.imageid+")");

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
    if (isself!="false") {
        logout(); //退出登录
        alter();
        $("#addfans-box").css('display', "none");
    } else {
        //tudo 关注按钮
        if(isfollow=="true"){
            oAddfans.style.top = '-50px';
            odefans.style.top = '0px';
        }

        var t = fans;

        $("#addfans-box").click(function () {

            if (isfollow!="false") {
                defan(t,follow);
                isfollow="false";
                t-=1;
            } else {
                befan(t,follow);
                isfollow="true";
                t+=1;
            }
        })


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
    fans+=1;
        var userid='2464792469@qq.com';
        $.ajax({
            url: "http://172.20.151.117:8066/Music_forum/follow",
            type: "GET",
            dataType: "json",
            data: {
                "followed":userid
            },
        })
    oAddfans.style.top = '-50px';
    odefans.style.top = '0px';
    showfans(fans ,follow);
}

function defan(fans,follow) {
    fans-=1;
        var userid='2464792469@qq.com';
        $.ajax({
            url: "http://172.20.151.117:8066/Music_forum/follow",
            type: "GET",
            dataType: "json",
            data: {
                "followed":userid
            },
        })
    oAddfans.style.top = '0px';
    odefans.style.top = '50px';
    showfans(fans,follow)
}
function showfans(fans,follow){
    var fansbox=document.getElementById('fans')
    fansbox.innerHTML="粉丝："+fans+"关注："+follow;
}