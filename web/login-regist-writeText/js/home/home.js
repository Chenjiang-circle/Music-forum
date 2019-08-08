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
    $.ajax({//获取-home-音乐
        url:"http://localhost:8066/Music_forum/homeGetMusic",
        datatype:"json",
        type:"GET",
        success:function(data){
            // data = eval(data);
            data=JSON.parse(data);
            for(i=0;i<6;i++){
                $(".cover").eq(i).css({'background-image':'url("'+data.Musics[i].musiccover+'")'});
                $(".albumName").eq(i).text(data.Musics[i].musicname+"  -  "+data.Musics[i].songer);

            }

            $(".cover").click(function(){//点击音乐
                var a = $(".cover").index(this);
                $("#show-music").css('display','block');
                $("#showmusic-cover").css('background-image','url('+data.Musics[a].musiccover+')');
                $("#audio").attr('src',data.Musics[a].url)
                $("#show-songname").text(data.Musics[a].musicname);
                $("#show-songer").text('- '+data.Musics[a].songer+' -');
                $("#show-album").text('所属专辑：'+data.Musics[a].album);
                $("#show-time").text(data.Musics[a].publictime);
                $("#show-company").text('©发行公司：'+data.Musics[a].company)
            })

            $("#showOut").click(function(){
                $("#show-music").css('display','none');
            })
        },
        error:function(jqXHR){
            alert("OOPS! 服务器出现了一个小问题："+jqXHR.status)
        }
    })



    $.ajax({//获取-home-文章
        url:"http://localhost:8066/Music_forum/homeGetArticle",
        datatype:"json",
        type:"GET",
        success:function(data){
            data=JSON.parse(data);
            for(i=0;i<6;i++){
                                //用于打印type👇
                                var type = data[i].type.split("&ArticleSelect=");
                                type[0] = type[0].replace("ArticleSelect=","");
                                // console.log(type.length);
                                var typeHtml="";
                                for(j = 0;j<type.length;j++){
                                    typeHtml = typeHtml+"<div class='arttypes'>"+type[j]+"</div>"
                                }
                                // console.log(typeHtml);
                                //用于打印type👆
                                console.log(type);
                $(".artType").eq(i).html(typeHtml);
                $(".artTitle").eq(i).text(data[i].title);
                $(".artCover").eq(i).css({'background-image':'url("'+data[i].textimage+'")'});
                $(".artLike").eq(i).html( '<img src="http://172.20.151.112:8088/f7660bb8026a4d68b5f0de1861f57874.png" width="30px">'+data[i].likes);
                $(".artComm").eq(i).html('<img src="http://172.20.151.112:8088/2cf23c34d38f4cc29535415a2fd07ea9.png" width="30px">'+data[i].comment);
            }
            var artTitle=document.getElementsByClassName('artTitle');
            for(var i=0;i<artTitle.length;i++){
                artTitle[i].index=i;
                artTitle[i].onclick=function(){
                    var a=this.index;
                    $.ajax({
                        url:"http://localhost:8066/Music_forum/jumpPage",
                        datatype:"json",
                        type:"post",
                        data:{
                            thistext:data[a].textid
                        },
                        success:function(){
                            location.href = "http://localhost:8066/Music_forum/login-regist-writeText/article.html";//跳转进入文章详情页
                        },
                        error:function(jqXHR){
                            alert("请检查网络："+jqXHR.status)
                        }
                    })
                }
            }
        },
        error:function(jqXHR){
            alert("OOPS! 服务器出现了一个小问题："+jqXHR.status)
        }
    })

    // $(".artTitle").click(function(){//点击文章
    //     s=$(this).index();
    //     $.ajax({
    //       url:"http://localhost:8066/Music_forum/jumpPage",
    //       datatype:"json",
    //       type:"post",
    //       data:{
    //         thistext:data[s].textid
    //       },
    //       success:function(){
    //         location.href = "http://localhost:8066/Music_forum/login-regist-writeText/article.html";//跳转进入文章详情页
    //       },
    //       error:function(jqXHR){
    //         alert("请检查网络："+jqXHR.status)
    //       }
    //     })
    //
    //   })




     $("#fly-nav").mouseover(function(){
        var a=document.getElementById('fly-nav');
        var scroll_width = 100;
        a.onmousewheel = mousewheel_event;
        function mousewheel_event(e){
            var e=e||window.event,v;
            e.wheelDelta ? v=e.wheelDelta : v=e.detail;
            if(v>3||-v>3) v=-v;
            v>0 ? a.scrollLeft+=scroll_width : a.scrollLeft-=scroll_width;
    
            e.preventDefault();
        }
    })

    $("#fly-albums").mouseover(function(){
        var a=document.getElementById('fly-albums');
        var scroll_width = 100;
        a.onmousewheel = mousewheel_event;
        function mousewheel_event(e){
            var e=e||window.event,v;
            e.wheelDelta ? v=e.wheelDelta : v=e.detail;
            if(v>3||-v>3) v=-v;
            v>0 ? a.scrollLeft+=scroll_width : a.scrollLeft-=scroll_width;
    
            e.preventDefault();
        }

    })

    $("#fly-albums-ti").click(function(){
        location.href = 'http://localhost:8066/Music_forum/login-regist-writeText/Shop.html';
    })
})