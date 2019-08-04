$(document).ready(function () {
    // $.cookie("userid","lixiaotao",{expirers:7,path:'/'})

    // $.cookie("userid","sadoifjo",{expirers:7,path:'/'})
    var userid = $.cookie("userid");
    $.removeCookie('userid');
    $.ajax({
        url: "http://172.20.151.117:8066/Music_forum/usercenter",
        type: "get",
        data: {
            "userid": userid
        },
        success: function (str) {
            var data = eval("(" + str + ")");
            //写入数据
            showdata(data);
            //判断是不是自己的个人主页
            isSelf(data.success)

        },
        error: function (err) {
            console.log(err);
            alert("网络似乎出现问题，请检查你的网络")
        }
    })
})

function showdata(data) {
    $("#userimg").src = data.imageid;
    $("#avatar").src = data.imageid;
    $("#loginOn-name").html(data.username);
    $("#username").html(data.username);
    var str = "<div id=\"fans\">粉丝：" + data.fans + "关注：" + data.follownum + "</div>"
    $("#avatarBack").append(str);
    //TODO  myart  mycollection
    var myart = data.listArtical;
    var mycollection = data.listComment;
    var collections=document.getElementsByClassName('collections');
    var arts=document.getElementsByClassName('arts');
    var collectionName=document.getElementsByClassName('collectionName');
    var artName=document.getElementsByClassName('artName');
    for(var i=0;i<3;i++){
        artName[i].innerHTML=myart[i].artName;
        collectionName[i].innerHTML=mycollection[i].collectionName;
        arts[i].style.backgroundImage=url(myart[i].artimage);
        collections[i].style.backgroundImage=url(mycollection[i].collectionimage);
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