$(document).ready(function(){
    // $.cookie("userid","lixiaotao",{expirers:7,path:'/'})
    
    // $.cookie("userid","sadoifjo",{expirers:7,path:'/'})
     var userid=$.cookie("userid");
    //   $.removeCookie('userid');
    $.ajax({
        url:"",
        type:"get",
        data:{"userid":userid},
        success:function(str){
            var data=eval("("+str+")");

        },
        error:function(err){
            console.log(err);
            alert("网络似乎出现问题，请检查你的网络")
        }
    })
})