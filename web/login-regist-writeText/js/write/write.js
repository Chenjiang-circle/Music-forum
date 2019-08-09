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
    window.scroll()

    $("#write-title").focusin(function(){
        if($('#write-title').val()=="请输入文章标题"||$('#write-title').val()=="别忘记写文章标题哦"){
            $('#write-title').val("");
        }
    })
    $("#write-title").focusout(function(){
        if($('#write-title').val()==""){
            $('#write-title').val("别忘记写文章标题哦");
        }
    })


    // $("#uppicture").click(function(){
    //     $("#uploadFile").click();
    // }
    // );

    var coverImg;//全局变量！保存的是发往后端的图片url地址 not'base64编码图片'.
    // var picFiles;//全局变量
    // $("#uploadFile").change(function(){
    //     picFiles = $(this)[0].files[0];//获取文件信息
    //     if(picFiles){
    //         var reader = new FileReader();//调用FileReader
    //         reader.readAsDataURL(picFiles);//将文件读取为base64
    //         reader.onload = function(evt){ //读取操作完成时触发
    //             var image = new Image();
    //             image.src=evt.target.result;
    //             image.onload=function(){
    //                 var width=image.width;
    //                 var height = image.height;
    //                 if(width>=720 && height>=470){
    //                     $("#upPic h3").html('"Cool ! 上传成功"');
    //                     $("#uppicture").attr('src',evt.target.result)//将img标签绑定为DataURL

    //                 }else{
    //                     $("#upPic h3").html('"OOPS ! 上传的封面尺寸不能小于720*470px哦!"');
    //                 }
    //             }
    //             img64=this.result.split(',')[1];
    //             // console.log(this.result);
    //             // $("#hhh").html('<img src="'+this.result+'"/>');

    //         }
    //     }else{
    //         alert("OOPS!未选择图片哦")
    //     }

    // });

    $("#confirm-uppic").click(function(){//图床 for 封面
        var formData = new FormData();
        formData.append('file',document.getElementById("uploadFile").files[0]);
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
                    if(width>=720 && height>=470){
                        $("#upPic h3").html('"Cool ! 上传成功"');
                        // $("#uppicture").attr('src',data);
                        $("#uppicture").css('background-image','url('+data+')');
                        coverImg = data;
                    }else{
                        $("#upPic h3").html('"OOPS ! 上传的封面尺寸不能小于720*470px哦!"');
                    }
                }
                // alert(data);
               
            },
            error:function(jqXHR){
                alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
            }

        })
    })





    $("#submitArticle").click(function(){
        
        var title = $("#write-title").val();//文章标题
        var content = $("#editor1").val();
        var items = content.split("data:image/png;base64,");
        var contents = items.join("");
        //文章内容

        //判断是否在前面加0
        function getNow(s) {
            return s < 10 ? '0' + s: s;
        }
    
        var myDate = new Date();             
        
        var year=myDate.getFullYear();        //获取当前年
        var month=myDate.getMonth()+1;   //获取当前月
        var date=myDate.getDate();            //获取当前日
        
        
        var h=myDate.getHours();              //获取当前小时数(0-23)
        var m=myDate.getMinutes();          //获取当前分钟数(0-59)
        var s=myDate.getSeconds();
        
        var now=year+'-'+getNow(month)+"-"+getNow(date)+" "+getNow(h)+':'+getNow(m)+":"+getNow(s);

        var check = $("input[name='ArticleSelect']:checked").serialize();
        check = decodeURIComponent(check,true);
        console.log(check);
        if(check.length==0){
            alert("您至少需要选择一个文章分类哦!");
        }else if(title=="请输入文章标题"||title==""||title=="别忘记写文章标题哦"){
            alert("别忘了写文章标题哦!")
        }else if(content.length==0){
            alert("请编辑您的文章内容后再发表.")
        }else if(!coverImg){
            alert("请上传文章封面以展示在首页.")
        }else{
            $.ajax({
                type:"POST",
                url:"http://localhost:8066/Music_forum/createText",
                dataType:"json",
                data:{
                    userid:0,
                    textid:0,
                    time:now,
                    likes:0,
                    comment:0,
                    collection:0,
                    text:content,
                    title:title,
                    type:check,
                    textimage:coverImg//文章封面
                },
                success:function(data){//请求成功，data为后台数据
                    if(data.success){
                        alert("❤成功提交! \n❤文章将在审核后发表.\n❤感谢您的贡献.祝您拥有愉快的一天");
                        //window.location.href="";
                        //跳转到首页
                    }else{
                        alert("OOOOPS! 服务器出现了一个小问题："+data.msg);
                    }
                },
                error:function(jqXHR){
                    alert("OOPS! 服务器出现了一个小问题："+jqXHR.status);
                }
            })
            
            // console.log(content);
            //  console.log(contents);
            
        }

         return false;

    })
})