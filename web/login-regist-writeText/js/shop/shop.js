var issignin=false;
$(document).ready(function(){
    $.ajax({
        url: "http://localhost:8066/Music_forum/getUserIformation", // 获取登录信息,如果登录返回的data不为null
        type: "GET",
        dataType: "json",
        success: function (data) {
            if (data != null) {
                issignin=true;
            }
        }
    })
    var shoptohome=document.getElementById('shoptohome');
    shoptohome.onclick=function(){
        $.ajax({
            url:"http://172.20.151.112:8066/Music_forum/getUserIformation",
            type:"GET",
            dataType:"json",
            success:function (data) {
                if(data!=null){
                    $.ajax({
                        url:"http://172.20.151.112:8066/Music_forum/jumpPage",
                        type:"post",
                        data:{
                            "userid":data.userid
                        },
                        success:function(){
                            window.location.href="http://172.20.151.112:8066/Music_forum/login-regist-writeText/myHomepage.html";
                        },
                        error:function(err){
                            alert("跳转页面失败,请检查您的网络");
                        }
                    })
                }else{
                    alert("您还未登录")
                    if(confirm("您还未登录,是否前往登录")){
                        window.location.href="http://172.20.151.112:8066/Music_forum/login-regist-writeText/enter.html"
                    }


                }
            }
        })
    }

    //***************************************
    //***************************************
    //*********演************示*****👇********
    //***************************************
    //***************************************
    // dataa = [
    //     {
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },
    //     {
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },
    //     {
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },
    //     {
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/8f6ffb2d1139496a9377a12bae30233f.jpeg",
    //         "musicid":9,
    //         "musicname":"无名之辈",
    //         "publictime":"2019-07-08 00:00:00.0",
    //         "songer":"陈雪燃",
    //         "time":"2019-08-03 17:21:26.0",
    //         "url":"http://172.20.151.112:8088/2694c576a8af43f9a084cf0ef65cd69c.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },
    //     {
    //         "album":"6415",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/0d72776a2c5f45989fb5b7b7cc926e7c.jpg",
    //         "musicid":18,
    //         "musicname":"骄傲的少年",
    //         "publictime":"2015-10-01 00:00:00.0",
    //         "songer":"南征北战",
    //         "time":"2019-08-03 18:11:25.0",
    //         "url":"http://172.20.151.112:8088/0b49118b52a349db91aefaa9e4a42923.mp3",
    //         "userid":"1455075085@Qq.com"
    //     },
    //     {
    //         "album":"周杰伦的床边故事",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/1470abad5a2148a7a037f564e01222eb.jpeg",
    //         "musicid":20,
    //         "musicname":"告白气球",
    //         "publictime":"2016-06-24 00:00:00.0",
    //         "songer":"周杰伦",
    //         "time":"2019-08-03 18:17:16.0",
    //         "url":"http://172.20.151.112:8088/ca9db63f34664a209de60df7d70d853a.mp3",
    //         "userid":"1455075085@qq.com"
    //     },{
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },{
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },{
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },{
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },{
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },{
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },{
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },{
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },{
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },{
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },{
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },{
    //         "album":"我们爱音乐",
    //         "company":"麒麟童",
    //         "ispass":"pass",
    //         "musiccover":"http://172.20.151.112:8088/05d556518adc4d869e21d5733c9f357a.jpeg",
    //         "musicid":1,
    //         "musicname":"小跳蛙",
    //         "publictime":"1999-11-30 00:00:00.0",
    //         "songer":"青蛙乐队",
    //         "time":"2008-09-06 00:00:00.0",
    //         "url":"http://172.20.151.112:8088/123.mp3",
    //         "userid":"1455075085@qq.com",
    //         "verifier":"root@qq.com"
    //     },
    // ]
    // // alert(dataa.length);
    // var dataLength=dataa.length//共有多少个歌曲

    // for(i=0;i<dataLength;i++){
    //     $(".grid__img").eq(i).attr('src',dataa[i].musiccover);
    //     $(".grid__item-title").eq(i).text(dataa[i].musicname+" - "+dataa[i].songer);
    // }


    // $(".grid__item").click(function(){
    //     var a = $(".grid__item").index(this);
    //     $("#show-music").css('display','block');
    //     $("#showmusic-cover").css('background-image','url('+dataa[a].musiccover+')');
    //     $("#audio").attr('src',dataa[a].url)
    //     $("#show-songname").text(dataa[a].musicname);
    //     $("#show-songer").text('- '+dataa[a].songer+' -');
    //     $("#show-album").text('所属专辑：'+dataa[a].album);
    //     $("#show-time").text(dataa[a].publictime);
    //     $("#show-company").text('©发行公司：'+dataa[a].company)
    // })
    // $("#showOut").click(function(){
    //     $("#show-music").css('display','none');
    // })

    

    //***************************************
    //***************************************
    //*********演************示******👆*******
    //***************************************
    //***************************************

    //获取专辑封面和name👇

    $.ajax({ 
        type:"get",
        url:"http://172.20.151.112:8066/Music_forum/getAllMusicInformation",
        datatype:'json',
        success:function(dataa){




            dataa = eval(dataa);
            var dataLength=dataa.length//共有多少个歌曲
            for(i=0;i<dataLength;i++){
                $(".grid__img").eq(i).attr('src',dataa[i].musiccover);
                $(".grid__item-title").eq(i).text(dataa[i].musicname+" - "+dataa[i].songer);
            }


            $(".grid__item").click(function(){
                var a = $(".grid__item").index(this);
                $("#show-music").css('display','block');
                $("#showmusic-cover").css('background-image','url('+dataa[a].musiccover+')');
                $("#audio").attr('src',dataa[a].url)
                $("#show-songname").text(dataa[a].musicname);
                $("#show-songer").text('- '+dataa[a].songer+' -');
                $("#show-album").text('所属专辑：'+dataa[a].album);
                $("#show-time").text(dataa[a].publictime);
                $("#show-company").text('©发行公司：'+dataa[a].company)
            })

            $("#showOut").click(function(){
                $("#show-music").css('display','none');
            })
        },
        error:function(jqXHR){
            alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
        }
    })
   
    //获取专辑封面和name👆




    var coverImg;//全局变量，保存封面的url
    var musicup;//全局变量，保存音乐的url

    $("#confirm-up").click(function(){//图床 for 封面

        // var upFile = document.getElementById("select-cover").files[0];
        var formData = new FormData();
        formData.append('file',document.getElementById("select-cover").files[0]);
        $.ajax({
            type:"POST",
            url:"http://172.20.151.112:8066/Music_forum/uploadfile",
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
                    if(width==height && width>400){
                        $("#tips").html("👍太棒了！专辑封面上传成功！")
                        $("#up-cover-image").css('background-image','url('+data+')');
                        coverImg = data;
                    }else{
                        $("#tips").html("图片不合格哦，请上传宽高比为1:1的专辑封面且不能小于400像素。")
                    }
                }
                // alert(data);
               
            },
            error:function(jqXHR){
                alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
            }

        })
    })

    $("#confirm-up2").click(function(){//图床 for 音乐

        // var upFile = document.getElementById("select-cover").files[0];
        var formData = new FormData();
        formData.append('file',document.getElementById("select-cover2").files[0]);
        $.ajax({
            type:"POST",
            url:"http://172.20.151.112:8066//Music_forum/uploadfile",
            datatype:"json",
            data:formData,
            processData:false,
            contentType:false,
            success:function(data){
                data=data.replace("\"","");
                data=data.replace("\"","");
                $("#tips").html("😝太棒了！音乐上传成功！");
                musicup = data;
            },
            error:function(jqXHR){
                alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
            }

        })
    })

    $("#musicGo").click(function(){//上传音乐
        if(!coverImg){
            alert("OOPS！您还未上传专辑封面。")
        }else if(!$("#song-name").val()){
            alert("OOPS！您还未填写歌曲名！")
        }else if(!$("#album").val()){
            alert("OOPS！您还未填写专辑名！")
        }else if(!$("#songer").val()){
            alert("OOPS！您还未填写歌手！")
        }else if(!$("#time").val()){
            alert("OOPS！您还未填写发行时间！")
        }else if(!$("#company").val()){
            alert("OOPS！您还未填写发行公司！")
        }else if(!musicup){
            alert("OOPS！您还未上传音乐！")
        }else{
            $.ajax({
                type:"post",
                url:"http://172.20.151.112:8066/Music_forum/uploadMusic",
                datatype:"json",
                data:{
                    musiccover:coverImg,
                    musicname:$("#song-name").val(),
                    album:$("#album").val(),
                    songer:$("#songer").val(),
                    publictime:$("#time").val(),
                    company:$("#company").val(),
                    url:musicup
                },
                success:function(data){
                    alert("❤上传成功! \n❤音乐将在审核后展示于唱片小店页.\n❤感谢您的贡献.祝您拥有愉快的一天")
                },
                error:function(jqXHR){
                    alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
                }
            })
        }
        // alert($("#song-name"));
        // alert($("#time").val());
    })

    $("#upMusicOut").click(function(){ //关闭上传音乐的窗口
        $("#upMusic").css("display","none");
    })
    //打开上传音乐的窗口
    $("#appearUpmusic").click(function(){
        if(issignin){
            $("#upMusic").css("display","block");
        }else{
            if(confirm("您还未登录,不能贡献自己的力量,是否前往登录?")){
                window.location.href="http://localhost:8066/Music_forum/login-regist-writeText/enter.html"
            }
        }

    })
    //hometopage

})