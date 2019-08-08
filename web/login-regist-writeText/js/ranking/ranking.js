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
                $("#loginOn-name").html(data.username);
                $("#loginOn-image").attr("src",data.imageid);
                //点击发送ajax给后端  后端存取userid

                $(".loginOn").click(function(){
                    //console.log(data.userid);
                    $.ajax({
                        url:"http://localhost:8066/Music_forum/jumpPage",
                        type:"GET",
                        data:{
                            "userid":data.userid
                        }
                    })
                    window.location.href="http://localhost:8066/Music_forum/login-regist-writeText/myHomepage.html";
                })
            }else{
                //显示登录注册按钮
                $("#login").css("display","block");
                $(".loginOn").css("display","none");
            }
        }
    })
    $.ajax({//公告牌
        url:"http://localhost:8066/Music_forum/getRankingList",
        datatype:"json",
        type:"post",
        data:{
            wherefrom:"B"
        },
        success:function(data){
            // JSON.parse(data);
            data = eval(data);
            for(i=0;i<30;i++){
                var people=data[i].artists[0].name;
                for(j=1;j<data[i].artists.length;j++){
                    people=people+" "+data[i].artists[j].name;
                }
                $("#box-bill .rank-cover").eq(i).css({'background-image':'url("'+data[i].album.picUrl+'")'});
                $("#box-bill .rank-songname").eq(i).text(data[i].name);
                $("#box-bill .rank-album").eq(i).text(data[i].album.name);
                $("#box-bill .rank-songer").eq(i).text(people);
            }
        },
        error:function(jqXHR){
            alert("OOPS! 资源加载失败："+jqXHR.status);
        }
    })

    $.ajax({//苹果
        url:"http://localhost:8066/Music_forum/getRankingList",
        datatype:"json",
        type:"post",
        data:{
            wherefrom:"I"
        },
        success:function(data){
            // JSON.parse(data);
            data = eval(data);
            for(i=0;i<30;i++){
                var people=data[i].artists[0].name;
                for(j=1;j<data[i].artists.length;j++){
                    people=people+" "+data[i].artists[j].name;
                }
                $("#box-apple .rank-cover").eq(i).css({'background-image':'url("'+data[i].album.picUrl+'")'});
                $("#box-apple .rank-songname").eq(i).text(data[i].name);
                $("#box-apple .rank-album").eq(i).text(data[i].album.name);
                $("#box-apple .rank-songer").eq(i).text(people);
            }
        },
        error:function(jqXHR){
            alert("OOPS! 资源加载失败："+jqXHR.status);
        }
    })

    $.ajax({//腾讯
        url:"http://localhost:8066/Music_forum/getRankingList",
        datatype:"json",
        type:"post",
        data:{
            wherefrom:"Q"
        },
        success:function(data){
            // JSON.parse(data);
            data = eval(data);
            for(i=0;i<30;i++){
                $("#box-tencent .rank-cover").eq(i).css({'background-image':'url("'+data[i].coverImages+'")'});
                $("#box-tencent .rank-songname").eq(i).text(data[i].songName);
                $("#box-tencent .rank-album").eq(i).html("打榜成绩："+data[i].uniIndex+"&nbsp&nbsp&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp&nbsp&nbsp成绩浮动："+data[i].uniChange+"&nbsp&nbsp&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp&nbsp&nbsp排名浮动："+data[i].rankChange);
                $("#box-tencent .rank-songer").eq(i).text(data[i].singerName);
             }
            },
        error:function(jqXHR){
            alert("OOPS! 资源加载失败："+jqXHR.status);
        }
    })

    $.ajax({//515
        url:"http://172.20.151.112:8066/Music_forum/getRankingList515",
        datatype:"json",
        type:"post",
        success:function(data){
            // JSON.parse(data);
            data = eval(data);
            console.log(data)
            var t=30;
            if(data.length<30){
                t=data.length;
            }

            for(i=0;i<t;i++){
                //用于打印type👇
                var type = data[i].type.split("&ArticleSelect=");
                type[0] = type[0].replace("ArticleSelect=","");
                console.log(type);
                var typeHtml="";
                for(j = 0;j<type.length;j++){
                    typeHtml = typeHtml+"<div class='arttypes'>"+type[j]+"</div>"
                }
                // console.log(typeHtml);
                //用于打印type👆
                $("#box-515 .rank-cover").eq(i).css({'background-image':'url("'+data[i].textimage+'")'});
                $("#box-515 .rank-songname").eq(i).text(data[i].title);
                $("#box-515 .rank-album").eq(i).html(typeHtml);
                $("#box-515 .rank-songer").eq(i).html("💖热度："+data[i].likes+"&nbsp&nbsp|&nbsp&nbsp💬跟帖："+data[i].comment);
             }
            var box515=document.getElementById('box-515');
            var songname=box515.getElementsByClassName('rank-songname');
            for(var i=0;i<songname.length;i++){
                songname[i].index=i;
                songname[i].onclick=function(){
                    var a=this.index;
                    console.log(a)
                    console.log(data[0])
                    $.ajax({
                        url:"http://localhost:8066/Music_forum/jumpPage",
                        type:"get",
                        data:{
                            "thistext":data[a].textid
                        },
                        success:function(){
                            window.open("http://localhost:8066/Music_forum/login-regist-writeText/article.html")
                        }
                    })
                }
            }
            },
        error:function(jqXHR){
            alert("OOPS! 资源加载失败："+jqXHR.status);
        }
    })

    $("#bill").click(function(){
        $(".rank-box").css({'display':'none'});        
        $("#box-bill").css({'display':'block'});
        $('.main-wrap').animate({scrollTop:520},1000);
        $('#rocket').css({'display':'block'});
    })
    $("#apple").click(function(){
        $(".rank-box").css({'display':'none'});  
        $("#box-apple").css({'display':'block'});
        $('.main-wrap').animate({scrollTop:520},1000);
        $('#rocket').css({'display':'block'});
    })
    $("#tencent").click(function(){
        $(".rank-box").css({'display':'none'});  
        $("#box-tencent").css({'display':'block'});
        $('.main-wrap').animate({scrollTop:520},1000);
        $('#rocket').css({'display':'block'});
    })
    $("#fof").click(function(){
        $(".rank-box").css({'display':'none'});  
        $("#box-515").css({'display':'block'});
        $('.main-wrap').animate({scrollTop:520},1000);
        $('#rocket').css({'display':'block'});
    })

    $('#rocket').click(function(){
        $('.main-wrap').animate({scrollTop:0},1000);
    });
})