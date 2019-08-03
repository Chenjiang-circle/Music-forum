$('document').ready(function () {
    //获取数据
    $.ajax({
        type: 'get',
        url: 'http://172.20.151.112:8066/Music_forum/getmusic',
        success: (str) => {
            //console.log(typeof(data))
            var data = eval(str);
            console.log(data);


            //将json分为10个一组，push到arr
            var arr = [];
            var a = Math.ceil(data.length / 10);
            var p = -1;
            for (var i = 0; i < a; i++) {
                var arr_child = []
                for (var j = 0; j < 10; j++) {
                    arr_child.push(data[++p])
                    // console.log(p)
                }
                arr.push(arr_child)
            }
            //创建分页
            judgeLength(data);
            //显示数据
            showData(arr[0]);
            trylisten(arr[0]);
            dataBack(arr[0]);
            //点击分页跳转相应页
            showpage(arr);
        },
        error: (err) => {
            console.log(err)
        }
    })
})

function showData(data) { //将数据渲染进表

    var table = $("#tbody");
    table.empty();
    if(data){
        for (var i = 0; i < data.length; i++) {
        //拼接表格的行和列
        if (data[i]) {
            var str = "<tr class=\"tr\"><td>" + data[i].musicname + "</td><td>" +
                data[i].album + "</td><td>" + data[i].time +
                "</td><td> <button class=\"btn btn-default ado_btn\">试听</button></td><td><button class=\"btn btn-default pass_btn\">通过</button><button class=\"btn btn-default unpass_btn\">不过</button></td></tr>";
            //追加到table中
            table.append(str);
        }
    }
    }
    
}
// 数据类型
// 数组嵌套多个json
//表格分页

//创建点击试听
function trylisten(data) {
    var oBtn = document.getElementsByClassName('ado_btn');
    var ado = document.getElementById('ado');
    for (var i = 0; i < oBtn.length; i++) {
        oBtn[i].index = i;
        oBtn[i].onclick = function () {
            var a = this.index;
            ado.src = data[a].url;
            ado.play();
        }
    }
}

//创建分页
function createPage(length) {
    var fenye = $("#fenye");
    fenye.empty();
    var page_num = Math.ceil(length / 10);
    var str1 = "<li><a href=\"#\">&laquo;</a></li>";
    var str2 = "<li><a href=\"#\">&raquo;</a></li>";
    fenye.append(str1);
    for (var i = 0; i < page_num; i++) {
        var str = "<li><a href=\"#\">" + (i + 1) + "</a></li>";
        fenye.append(str);
    }
    fenye.append(str2);
}

//判断是否用分页
function judgeLength(obj) {
    if (obj.length > 10) {
        createPage(obj.length);
    }
}

//分页点击移动到相应页
function showpage(arr) {
    var fenye = document.getElementById('fenye');
    var oLi = fenye.getElementsByTagName('li');
    for (var i = 0; i < oLi.length; i++) {
        oLi[i].index = i;
        // var now=0;
        oLi[i].onclick = function () {
            now = this.index - 1;
            showData(arr[this.index - 1]);
            trylisten(arr[this.index - 1]);
            dataBack(arr[this.index - 1]);
        }
        // oLi[0].onclick=function(){
        //     alert(now)
        // }
        // oLi[oLi.length-1].onclick=function(){
        //     alert(now)
        // }
    }

}
//返回审核数据
function dataBack(arr) {
    var passBtn = document.getElementsByClassName('pass_btn');
    var unpassBtn = document.getElementsByClassName('unpass_btn');

    for (var i = 0; i < passBtn.length; i++) {
        passBtn[i].index = i;
        passBtn[i].onclick = function () {
            var a=this.index;
            $.ajax({
                url: "http://172.20.151.112:8066/Music_forum/pass",
                type: "get",
                data: {
                    "url": arr[this.index].url,
                    "userid":arr[this.index].userid,
                    "ispass": true,
                    "verifier": $.cookie("user")
                },
                success: function (str) {
                    var data = eval("(" + str + ")");
                    console.log(data);
                    if (data.do == true) {
                        alert('操作成功')
                        disappear(a);
                    } else {
                        alert("操作失败")
                    }
                },
                error: function (err) {
                    console.log(err)
                    alert("网络错误")
                }
            })
        }
    }
    for (var i = 0; i < unpassBtn.length; i++) {
        unpassBtn[i].index = i;
        unpassBtn[i].onclick = function () {
            var a=this.index;
            $.ajax({
                url: "http://172.20.151.112:8066/Music_forum/pass",
                type: "get",
                data: {
                    "url": arr[this.index].url,
                    "userid":arr[this.index].userid,
                    "ispass": false,
                    "verifier": $.cookie("user")
                },
                success: function (str) {
                    var data = eval("(" + str + ")");
                    
                    if (data.do == true) {
                        alert('操作成功');
                        disappear(a);
                    } else {
                        alert("操作失败")
                    }
                },
                error: function (err) {
                    console.log(err)
                    alert("网络错误")
                }
            })
        }
    }
}

function disappear(n){
    
    var tr=document.getElementsByClassName('tr');
    tr[n].style.display='none';
}