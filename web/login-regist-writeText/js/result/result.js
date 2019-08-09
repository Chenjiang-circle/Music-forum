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
    $.ajax({
        url:"http://localhost:8066/Music_forum/getSearchReasult",
        type:"get",
        datatype:"json",
        success:function(data){
            data=JSON.parse(data);
            if(data.texts){
                $('#main-content').html('<div id="tip">hey!这是关于<h1>'+data.keyworks+'</h1>的搜索结果：</div>')

                for(var i=0;i<data.texts.length;i++){
                    var inHtml="<div class='results'><div class='cover'></div><div class='results-title'>碧昂斯新歌-献给非洲大陆的礼物</div><div class='detail'>1231点赞|221评论</div></div>";
                    $('#main-content').html($('#main-content').html()+inHtml);

                }
                for(var i=0;i<data.texts.length;i++){
                    $('.cover').eq(i).css({'background-image':'url("'+data.texts[i].textimage+'")'});
                    $('.results-title').eq(i).text(data.texts[i].title);
                    $('.detail').eq(i).text(data.texts[i].likes+'点赞   |   '+data.texts[i].comment+'评论');
                }
                var result=document.getElementsByClassName("results");
                for(var i=0;i<result.length;i++){
                    result[i].index=i;
                    result[i].onclick=function(){
                        var a=this.index;
                        $.ajax({
                            url:"http://localhost:8066/Music_forum/jumpPage",
                            type:"get",
                            data:{
                                "thistext":data.texts[a].textid
                            },
                            success:function(){
                                window.open("http://localhost:8066/Music_forum/login-regist-writeText/article.html")
                            }
                        })
                    }
                }

            }else{
                $('#main-content').html('<div id="tip">OOPS!没有找到有关<h1>'+data.keyworks+'</h1>的搜索结果：</div>')
            }

        },
        error:function(jqXHR){
            alert("OOPS！搜索结果失败："+jqXHR.status)
        }
    })

    $('.results').click(function(){
        var a = $(".cover").attr('textid');
        $.ajax({
            url:"",
            type:"post",
            datatype:"json",
            data:{
                textid:a
            },
            success:function(data){
                location.href=data;
            },
            error:function(jqXHR){
                alert("OOPS！搜索结果失败："+jqXHR.status)
            }
        })

    })
})