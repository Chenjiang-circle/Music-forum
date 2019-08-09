var issignin=false;
var useridAva="";
var imageurl = "";
$(document).ready(function () {
  var yours;
    $.ajax({
        url: "http://localhost:8066/Music_forum/getUserIformation", // 获取登录信息,如果登录返回的data不为null
        type: "GET",
        dataType: "json",
        success: function (data) {
            if (data != null) {
                console.log(data)
                issignin=true;
                useridAva=data.userid;
                // 因为data不为空,所以需要将右上角的sign in 和 sign up隐藏起来,并展示登录用户头像
                //userid不为空 获取用户头像 用户昵称 id
                $("#login").css("display", "none");
                $(".loginOn").css("display", "block");
                $("#loginOn-name").html(data.username);
                $("#loginOn-image").attr("src", data.imageid);
                imageurl = data.imageid;

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

            isSelf(data.isself, data.isfollow, data.user.fans, data.follownum);
            yours=data.isself;
            if(yours){//上传头像
                $("#avatar").click(function(){
                    $('#upavatar').fadeIn();
                })
            }
            //console.log(typeof (data.isself))
        },
        error: function (err) {
            console.log(err);
            alert("网络似乎出现问题，请检查你的网络")
        }
    })



    $("#confirm-up").click(function(){//图床 for 封面

        // var upFile = document.getElementById("select-cover").files[0];
        var formData = new FormData();
        formData.append('file',document.getElementById("select-cover").files[0]);
        $.ajax({
            type:"POST",
            url:"http://localhost:8066//Music_forum/uploadfile",
            datatype:"json",
            data:formData,
            processData:false,
            contentType:false,
            success:function(data){
                // coverImg = data;
                data=data.replace("\"","");
                data=data.replace("\"","");
                var image = new Image();
                image.src = data;
                image.onload=function(){
                    var width=image.width;
                    var height=image.height;
                    if(width==height){
                        $("#tips").html("👍太棒了！头像上传成功！")
                        $("#up-cover-image").css('background-image','url('+data+')');
                        $('#avatar').css('background-image','url('+data+')');
                        $('#userimg').attr('src',data);
                        $('#upavatar').fadeOut();
                        $.ajax({
                            url:'http://172.20.151.112:8066/Music_forum/changeUserImage',
                            dataType:'json',
                            type:'post',
                            data:{
                                'newAvatar':data,
                                'useridAva':useridAva
                            }
                        })
                    }else{
                        $("#tips").html("图片不合格哦，请上传宽高比为1:1的图片")
                    }
                }
                // alert(data);
               
            },
            error:function(jqXHR){
                alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
            }

        })
    })

    $('#upMusicOut').click(function(){
        $('#upavatar').fadeOut();
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
    for (var i = 0; i < myart.length; i++) {

            var str='<div class="artLists  fadearts"><div class="artName ">蔡依林：我还怪美的！</div></div>';
            $('.arts').eq(0).html($('.arts').eq(0).html()+str);

        $('.artName').eq(i).text(myart[i].title)
        $('.artLists').eq(i).css({'background-image':"url(" + myart[i].textimage + ")"})

    }

    for (var i = 0; i < mycollection.length; i++) {

        var str='<div class="collectionLists"><div class="collectionName">蔡依林：我还怪美的！</div></div>';
        $('.collections').eq(0).html($('.collections').eq(0).html()+str);

        $('.collectionName').eq(i).text(mycollection[i].title)
        $('.collectionLists').eq(i).css({'background-image':"url(" + mycollection[i].textimage + ")"})

    }

    var morearts=document.getElementById('morearts')
    var morecollections=document.getElementById('morecollections')
    morearts.onclick=function(){
        s=myart.length/3;
        s=Math.floor(s);
        $(".arts").eq(0).css({'height':540+450*s+'px'})
    }
    morecollections.onclick=function(){
        s=mycollection.length/3;
        s=Math.floor(s);
        $(".collections").eq(0).css({'height':540+450*s+'px'})
    }
    for (var i = 0; i < artLists.length; i++) {
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
    for (var i = 0; i < collectionLists.length; i++) {
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


        var t = fans;

        $("#addfans-box").click(function () {
            //判断用户是否登录
            if(issignin){
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
            }else{
                if(confirm("您还未登录,不能关注,是否前往登录?")){
                    window.location.href="http://localhost:8066/Music_forum/login-regist-writeText/enter.html"
                }
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

