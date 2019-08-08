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

                $(".loginOn").click(function(){//右上角跳转个人主页
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
    var like;
    var collection;
    var ifColl=0;//这篇文章是否被该用户收藏？全局
    var textid = null;
    function left(depth){  //printComments函数会用到
        var leftComm=50;
        for(var i=0;i<depth;i++){
            leftComm += 50;
        }
        return leftComm;
    }
    function printComments(comments, depth) {//这个函数用来打印多层评论
        for(var i = 0; i < comments.length; i++) {
            var c = comments[i];
            var commentDiv=document.createElement('div');
            commentDiv.setAttribute('class','comments');
            //插入头像
            commentAvatar = document.createElement('div');
            commentAvatar.setAttribute('class','comment-avatar');
            commentDiv.appendChild(commentAvatar);
            // commentAvatar.style.backgroundImage = '/img/touxiang.jpg';
            
            
            //用户信息
            commentUser = document.createElement('div');
            commentUser.setAttribute('class','comment-user');
            commentDiv.appendChild(commentUser);
            commentUser.innerHTML = '<p>'+c.username+"</p> 发表于 "+c.time;
    
    
            //插入评论内容
            commentText = document.createElement('div');
            commentText.setAttribute('class','comment-text');
            commentText.setAttribute("textid",c.textid);
            commentDiv.appendChild(commentText);
            commentText.innerHTML = c.text;
    
            // //插入反馈内容
            // commentFB = document.createElement('div');
            // commentFB.setAttribute('class','feedback');
            // commentFBLike = document.createElement('div');
            // commentFBLike.setAttribute('class','comments-like');
            // commentFBLike.innerHTML = c.likes+" 喜欢  ";
            // commentFBComment = document.createElement('div');
            // commentFBComment.setAttribute('class','comment-comments');
            // commentFBComment.innerHTML = c.comment+" 评论  ";
            // commentFB.appendChild(commentFBLike);
            // commentFB.appendChild(commentFBComment);
            // commentDiv.appendChild(commentFB);
                    
            commentDiv.style.left=left(depth)+'px';
    
            document.getElementById('allComments').appendChild(commentDiv);
                //console.log(getSpaces(depth * 2) + "评论：" + c.text);
            if(c.hasOwnProperty("list")||c.list!="") {
                printComments(c.list, depth + 1);
            }
        }
    }

    $.ajax({//请求得到文章、标题、点赞数、收藏数、作者等
        url:"http://localhost:8066/Music_forum/getText",
        type:"GET",
        datatype:"text",
        success:function(data){
            textid = data.text.textid;
            //alert("text-->"+textid);
            if(data){
                $("#article-title").html(data.text.title);
                $("#author-time").html("发表于 "+data.text.time);
                $("#author-username").html(data.text.username);
                $("#article-content").html("<pre>"+data.text.text+"</pre>");
                like=data.text.likes;
                $("#article-like").html("喜欢"+like);
                $("#article-conmmet-title").html("评论"+data.text.comment);
                collection=data.text.collection;
                $("#arti-collections").html("收藏"+collection);
                if(data.ifColl){//说明该用户已经收藏该文章
                    ifColl=1;
                    $("#arti-collections").css({'background':'yellow'});
                }else{//莫得收藏
                    ifColl=0;
                    $("#arti-collections").css({'background':'wheat'});
                }
                /*if(cookies.get('userid')){//说明该用户已经登录
                    $.ajax({//则发送请求看该用户是否已经收藏这篇文章
                        url:"http://172.20.151.117:8066/Music_forum/getText",
                        type:"POST",
                        datatype:"json",
                        data:{userid:cookies.get('userid'),textid:data.tetxid},//发送了用户id和文章id
                        success:function(data){
                            if(data.ifColl){//说明该用户已经收藏该文章
                                ifColl=1;
                                $("#arti-collections").css({'background':'yellow'});
                            }else{//莫得收藏
                                alert("jfkajf");
                                ifColl=0;
                                $("#arti-collections").css({'background':'wheat'});
                            }
                        }
                    })
                }else{
                    //说明该用户没有登陆
                }*/
            }else{
                alert("文章加载失败："+data.msg)
            }
            var avatar=document.getElementsByClassName('avatar');
            avatar[0].onclick=function(){
                $.ajax({
                    url:"http://localhost:8066/Music_forum/jumpPage",
                    type:"GET",
                    data:{
                        "userid":data.text.userid
                    }
                })
                window.location.href="http://localhost:8066/Music_forum/login-regist-writeText/myHomepage.html";
            }
        },
        error:function(jqXHR){
            alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
        }
    })



        //****************************************************************************
        //********演********示********
        //****************************
        //******将评论打印在页面上*****
        //**************************

//  var data=
//         [{
//         "collection":0,
//         "comment":3,
//         "likes":0,
//         "list":[
//             {
//                 "collection":0,
//                 "comment":2,
//                 "likes":0,
//                 "list":[
//                     {
//                         "collection":0,
//                         "comment":1,
//                         "likes":0,
//                         "list":[
//                             {
//                                 "collection":0,
//                                 "comment":0,
//                                 "likes":0,
//                                 "list":[

//                                 ],
//                                 "text":"3楼评论2楼1",
//                                 "textid":27,
//                                 "time":"2019-08-05 00:00:00.0",
//                                 "userid":"1455075085@qq.com",
//                                 "username":"1812050146"
//                             }
//                         ],
//                         "text":"2楼评论1楼的错过的人生的评论",
//                         "textid":25,
//                         "time":"2019-08-04 00:00:00.0",
//                         "userid":"1455075085@qq.com",
//                         "username":"1812050146"
//                     }
//                 ],
//                 "text":"1楼评论错过的人生",
//                 "textid":24,
//                 "time":"2019-08-03 00:00:00.0",
//                 "userid":"1455075085@qq.com",
//                 "username":"1812050146"
//             },
//             {
//                 "collection":0,
//                 "comment":0,
//                 "likes":0,
//                 "list":[

//                 ],
//                 "text":"1楼评论错过的人生2",
//                 "textid":26,
//                 "time":"2019-08-04 00:00:00.0",
//                 "userid":"1455075085@qq.com",
//                 "username":"1812050146"
//             }
//         ],
//         "text":"里跌下年、中的态度。不想错过，但难免错过；允许错过，但不允许同样的错误一犯再犯。马尚有失蹄的时候，人又永远不会真正得到它。",
//         "textid":23,
//         "time":"2019-07-25 00:00:00.0",
//         "title":"错过的人生",
//         "userid":"1455075085@qq.com",
//         "username":"1812050146"
//     }]

//     printComments(data,0);//打印出来



//     $(".comment-text").click(function(){//为评论添加点击事件，当点击评论时可对该评论再评论
//         $(".commentBack").remove();
//         commentBack = document.createElement('div');
//         commentBack.setAttribute("class","commentBack");
//         commentBack.innerHTML = "<textarea placeholder='善意的评论是友好交流的开始'></textarea><div class='bobo'>发送</div>";
//         $(this).after(commentBack);

//         $(".bobo").click(function(){
//             var comText = $(this).prev().val();//回复评论的内容
//             //把回复评论的内容打印在页面上
//             commentDiv = document.createElement('div');
//             commentDiv.setAttribute("class","comments");
//              //插入头像
//              commentAvatar = document.createElement('div');
//              commentAvatar.setAttribute('class','comment-avatar');
//              commentDiv.appendChild(commentAvatar);
//              // commentAvatar.style.backgroundImage = '/img/touxiang.jpg';


//              // //用户信息
//              // commentUser = document.createElement('div');
//              // commentUser.setAttribute('class','comment-user');
//              // commentDiv.appendChild(commentUser);
//              // commentUser.innerHTML = '<p>'+c.username+"</p> 发表于 "+c.time;


//              //插入评论内容
//              commentText = document.createElement('div');
//              commentText.setAttribute('class','comment-text');
//              // commentText.setAttribute("textid",c.textid);
//              commentDiv.appendChild(commentText);
//              commentText.innerHTML = comText;
//              var left=parseInt($(this).parent().parent().css('left'))+50;
//              commentDiv.style.left=left+'px';
//              $(this).parent().parent().after(commentDiv);
//              $(this).parent().remove();
//             //传递ajax
//             // $.ajax({
//             //     url:"",
//             //     dataType:"json",
//             //     type:"post",
//             //     data:{
//             //         ctext:comText,
//             //         username:"",
//             //         userid:"",
//             //         // time:time,
//             //         commentid:$(this).parent().attr("textid")
//             //     },
//             //     success:function(){
                   
//             //     },
//             //     error:function(jqXHR){
//             //         alert("OOPS!服务器出现了一个小问题："+jqXHR.status)
//             //     }
//             // })

//         })
//     })

        //****************************
        //********演********示********
        //****************************
        //******将评论打印在页面上*****
        //******************************************************************************



        $('#commit-comment').click(function(){  //回复文章
            console.log('hhh');
            var comText = $('#article-writeComment').val();
            console.log(comText);
            commentDiv = document.createElement('div');
            commentDiv.setAttribute("class","comments");
            //插入头像
            commentAvatar = document.createElement('div');
            commentAvatar.setAttribute('class','comment-avatar');
            commentDiv.appendChild(commentAvatar);
            //插入评论内容
            commentText = document.createElement('div');
            commentText.setAttribute('class','comment-text');
            // commentText.setAttribute("textid",c.textid);
            commentDiv.appendChild(commentText);
            commentText.innerHTML = comText;
            console.log(commentDiv);
            var left=parseInt($('#allComments').find('.comments:first').css('left'));
            commentDiv.style.left=left+'px';
            $('#allComments').find('div:first').before(commentDiv);
                        // 传递ajax
                $.ajax({
                    url:"http://localhost:8066/Music_forum/changecomment",
                    dataType:"json",
                    type:"post",
                    data:{
                        'text':comText,
                        // username:"",
                        // userid:"",
                        'time':getNowFormatDate(),
                        'textid':$(this).parent().attr("textid")
                    },
                    success:function(){
                       
                    },
                    error:function(jqXHR){
                        alert("OOPS!服务器出现了一个小问题："+jqXHR.status)
                    }
                })
    
        })

    $.ajax({//请求得到评论
        url:"http://localhost:8066/Music_forum/comment",
        type:"GET",
        datatype:"json",
        success:function(data){
            data=eval(data);
            printComments(data,0);//打印出来

            $(".comment-text").click(function(){//为评论添加点击事件，当点击评论时可对该评论再评论
                $(".commentBack").remove();
                commentBack = document.createElement('div');
                commentBack.setAttribute("class","commentBack");
                commentBack.innerHTML = "<textarea placeholder='善意的评论是友好交流的开始'></textarea><div class='bobo'>发送</div>";
                $(this).after(commentBack);
        
                $(".bobo").click(function(){//发送对评论的回复
                    var comText = $(this).prev().val();//回复评论的内容
                    //把回复评论的内容打印在页面上
                    commentDiv = document.createElement('div');
                    commentDiv.setAttribute("class","comments");


                    //插入头像
                    commentAvatar = document.createElement('div');
                    commentAvatar.setAttribute('class','comment-avatar');
                    commentDiv.appendChild(commentAvatar);
                    // commentAvatar.style.backgroundImage = '/img/touxiang.jpg';


                    // //用户信息
                    // commentUser = document.createElement('div');
                    // commentUser.setAttribute('class','comment-user');
                    // commentDiv.appendChild(commentUser);
                    // commentUser.innerHTML = '<p>'+c.username+"</p> 发表于 "+c.time;


                    //插入评论内容
                    commentText = document.createElement('div');
                    commentText.setAttribute('class','comment-text');
                    // commentText.setAttribute("textid",c.textid);
                    commentDiv.appendChild(commentText);
                    commentText.innerHTML = comText;
                    var left=parseInt($(this).parent().parent().css('left'))+50;
                    commentDiv.style.left=left+'px';
                    $(this).parent().parent().after(commentDiv);
                    // $(this).parent().remove();
                    var currentTime=getNowFormatDate();
                    //传递ajax

                    $.ajax({
                        url:"http://localhost:8066/Music_forum/changecomment",
                        dataType:"json",
                        type:"post",
                        data:{
                            "text":comText
                            ,
                            // username:"",
                            // userid:"",

                            "time":currentTime,//time,
                            "textid":$(this).parent().prev('div').attr("textid")
                            //"textid":52
                        },
                        success:function(){
                            console.log('123');


                        },
                        error:function(jqXHR){
                            alert("OOPS!服务器出现了一个小问题："+jqXHR.status)
                        }
                    })
        
                })
            })
        
        },
        error:function(jqXHR){
            alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
        }
    })

    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
        return currentdate;
    }


    $("#article-like").click(function(){//点击喜欢
        like++;
        $("#article-like").html("喜欢"+like);

        var x = 100;       
		var y = 900;  
		var num = Math.floor(Math.random() * 11 + 1);
		var index=$('.love').children('img').length;
        var rand = parseInt(Math.random() * (x - y + 1) + y); 
        
		$(".love").css
		$(".love").append("<img src=''>");
		$(".love").find('img:eq(' + index + ')').attr('src','img/lovepic/'+num+'.png')
		$(".love").find("img").animate({
			bottom:"800px",
			opacity:"0",
			left: rand,
		},3000)

        $.ajax({
            url:"http://localhost:8066/Music_forum/like",
            type:"POST",
            datatype:"json",
            data:{
                likes:1,
                textid:textid,
            },
            error:function(jqXHR){
                alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
            }
        })
    })

        
        $("#arti-collections").click(//点击收藏或取消收藏
            function(){
                if(ifColl==1){
                    ifColl=0;
                    $("#arti-collections").css({'background':'wheat'});
                    collection--;
                }else{
                    ifColl=1;
                    $("#arti-collections").css({'background':'yellow'});
                    collection++;
                }
                $("#arti-collections").html("收藏"+collection);

                $.ajax({//每次点击都会传一次新的collection值
                    url:"http://localhost:8066/Music_forum/collect",
                    type:"POST",
                    datatype:"text",
                    data:{
                        collection:collection,
                        collectiontextid:textid,
                        ifColl:ifColl,
                    }
                })

                
            }
        )



    

    
})