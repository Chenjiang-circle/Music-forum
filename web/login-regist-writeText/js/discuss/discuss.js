jQuery(document).ready(function($){
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

            $('.list-group-item').click(function(){//点击文章
              s=$(this).index();
              $.ajax({
                url:"",
                datatype:"json",
                type:"post",
                data:{
                  thistext:data[s].textid
                },
                success:function(dataa){
                  location.href = dataa;//跳转进入文章详情页
                },
                error:function(jqXHR){
                  alert("请检查网络："+jqXHR.status)
                }
              })
              
            })
        },
        error:function(jqXHR){
            alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
        }
    })


  });