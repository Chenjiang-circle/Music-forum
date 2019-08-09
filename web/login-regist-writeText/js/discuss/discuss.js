jQuery(document).ready(function($){
    var yours=false;
    $.ajax({
        url:"http://localhost:8066/Music_forum/getUserIformation",
        type:"GET",
        dataType:"json",
        success:function (data) {
            if(data!=null){
                yours=true;
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
    $.ajax({//渲染数据到页面上
        url:"http://localhost:8066/Music_forum/getsimplearticle",
        datatype:"json",
        type:"get",
        success:function(data){
            data=eval(data);

            for (var i = 0; i < data.length; i++) {
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
              $('.list-group').append('<li class="list-group-item"> <div class="artCover"></div> <div class="art-title">'+data[i].title+'</div> <div class="art-type">'+typeHtml+'</div> <div class="art-feedback"><div class="art-like"><img src="http://172.20.151.112:8088/f7660bb8026a4d68b5f0de1861f57874.png" width="30px">'+data[i].likes+'</div><div class="art-comm"><img src="http://172.20.151.112:8088/2cf23c34d38f4cc29535415a2fd07ea9.png" width="30px">'+data[i].comment+'</div></div></li>');
              $(".artCover").eq(i).css({'background-image':"url('"+data[i].textimage+"')"})
              console.log("url('"+data[i].textimage+"')");
              typeHtml="";
            }
        
            $('.list-group').paginathing({
              perPage: 5,
              limitPagination: 2,
              containerClass: 'panel-footer',
              pageNumbers: true
            })
            var list=document.getElementsByClassName('list-group-item');
            for(var i=0;i<list.length;i++){
                list[i].index=i;
                list[i].onclick=function(){

                    var a=this.index;
                    console.log(a)
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
            // $('.list-group-item').click(function(){//点击文章
            //     alert("点击文章")
            //   s=$(this).index();
            //   $.ajax({
            //     url:"http://localhost:8066/Music_forum/jumpPage",
            //     datatype:"json",
            //     type:"post",
            //     data:{
            //       thistext:data[s].textid
            //     },
            //     success:function(){
            //       location.href = "http://localhost:8066/Music_forum/login-regist-writeText/article.html";//跳转进入文章详情页
            //     },
            //     error:function(jqXHR){
            //       alert("请检查网络："+jqXHR.status)
            //     }
            //   })
            //
            // })
        },
        error:function(jqXHR){
            alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
        }
    })

    $("#plus").click(function(){
        if(yours){
            location.href = 'http://localhost:8066/Music_forum/login-regist-writeText/write.html';
        }else{
            if(confirm("您还未登录,不能创作,是否前往登录?")){
                window.location.href="http://localhost:8066/Music_forum/login-regist-writeText/enter.html"
            }
        }

    })
    $("#plus").hover(function(){
        $("#plustip").fadeIn()
    },function(){
        $("#plustip").fadeOut()
    })


  });