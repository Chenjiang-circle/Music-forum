$(document).ready(function () {
    // $.cookie("userid","lixiaotao",{expirers:7,path:'/'})

    // $.cookie("userid","sadoifjo",{expirers:7,path:'/'})
    // var userid = $.cookie("userid");
       var userid = "2464792469@qq.com";
    $.removeCookie('userid');
    $.ajax({
        url: "http://172.20.151.112:8066/Music_forum/usercenter",
        type: "get",
        dataType: "json",
        data: {
            "userid": userid
        },
        success: function (data) {
            //var data = eval("(" + str + ")");
            console.log(data);
            //写入数据
            showdata(data);
            //判断是不是自己的个人主页
            isSelf(data.isself)

        },
        error: function (err) {
            console.log(err);
            alert("网络似乎出现问题，请检查你的网络")
        }
    })
})

function showdata(data) {
    $("#userimg").src = data.user.imageid;
    $("#avatar").css("background-image",data.user.imageid);
    $("#loginOn-name").html(data.user.username);
    $("#username").html(data.user.username);
    var str = "<div id=\"fans\">粉丝：" + data.user.fans + "关注：" + data.user.follownum + "</div>"
    $("#avatarBack").append(str);
    //TODO  myart  mycollection
    var myart = data.listArticle;
    //console.log(myart);
    var mycollection = data.listCollection;
    var collectionLists=document.getElementsByClassName('collectionLists');
    var artLists=document.getElementsByClassName('artLists');
    var collectionName=document.getElementsByClassName('collectionName');
    var artName=document.getElementsByClassName('artName');
    for(var i=0;i<3;i++){
        if(myart[i]){
            artName[i].innerHTML=myart[i].title;
            artLists[i].style.backgroundImage="url("+myart[i].textimage+")";
        }else{
            artLists[i].style.display='none';
        }
        if(mycollection[i]){
            collectionName[i].innerHTML=mycollection[i].title;
            collectionLists[i].style.backgroundImage="url("+mycollection[i].textimage+")";
        }else{
            collectionLists[i].style.display='none';
        }



    }
    // createArtList(myart);
    // createCollectionList(mycollection)

};

function isSelf(b) {
    if (b) {
        logout();
        alter();
    } else {
        $("#out").css("display", "none");
    }
}

function logout() {
    $("#out").click(function () {

    })

}

function alter() {

}