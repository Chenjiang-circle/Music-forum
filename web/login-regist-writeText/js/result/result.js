$(document).ready(function(){
    $.ajax({
        url:"",
        type:"get",
        datatype:"json",
        success:function(){

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