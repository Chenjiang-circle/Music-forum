$(document).ready(function(){
    $.ajax({//公告牌
        url:"http://172.20.151.112:8066/Music_forum/getRankingList",
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
        url:"http://172.20.151.112:8066/Music_forum/getRankingList",
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
        url:"http://172.20.151.112:8066/Music_forum/getRankingList",
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