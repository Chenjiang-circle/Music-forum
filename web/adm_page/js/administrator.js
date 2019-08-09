$('document').ready(function () {
    //获取所有网站数据
    getalldata();
    //获取音乐审核数据
    getmusicdata();
    //获取待审核文章数据
    getallarticle();
    //获取已审核文章
    gethomearticle();
})

//获取音乐数据
function getmusicdata() {
    $.ajax({
        type: 'get',
        url: 'http://172.20.151.112:8066/Music_forum/getmusic',
        success: function(str) {

            var data = eval(str);



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
        error: function(err) {
            console.log(err)
        }
    })
}

//获取待审核文章数据
function getallarticle() {
    $.ajax({
        url: "http://172.20.151.112:8066/Music_forum/getallarticle",
        type: "GET",
        success: function (str) {
            var data = eval(str);

            printdata(data);
            commitdata(data);
        },
        error: function (err) {
            alert("网络出错：" + err.status)
        }
    })
}

//获取已审核文章
function gethomearticle() {
    $.ajax({
        url: "http://172.20.151.112:8066/Music_forum/gethomearticle",
        type: "get",
        success: function (str) {
            var data = eval(str);

            print(data);
            //取消放到主页
            cancelcommit(data);
        },
        error: function (err) {
            alert("网络出错：" + err.status)
        }
    })
}

function showData(data) { //将数据渲染进表

    var table = $("#tbody");
    table.empty();
    if (data) {
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

        oLi[i].onclick = function () {
            now = this.index - 1;
            showData(arr[this.index - 1]);
            trylisten(arr[this.index - 1]);
            dataBack(arr[this.index - 1]);
        }

    }

}
//返回审核数据
function dataBack(arr) {
    var passBtn = document.getElementsByClassName('pass_btn');
    var unpassBtn = document.getElementsByClassName('unpass_btn');

    for (var i = 0; i < passBtn.length; i++) {
        passBtn[i].index = i;
        passBtn[i].onclick = function () {
            var a = this.index;
            $.ajax({
                url: "http://172.20.151.112:8066/Music_forum/pass",
                type: "get",
                data: {
                    "url": arr[this.index].url,
                    "userid": arr[this.index].userid,
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
            var a = this.index;
            $.ajax({
                url: "http://172.20.151.112:8066/Music_forum/pass",
                type: "get",
                data: {
                    "url": arr[this.index].url,
                    "userid": arr[this.index].userid,
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

function disappear(n) {

    var tr = document.getElementsByClassName('tr');
    tr[n].style.display = 'none';
}

//打印文章列表
function printdata(data) {
    console.log(data)
    $("#abody").empty();
    for (var i = 0; i < data.length; i++) {
        var str = "<tr class=\"t\"><td class='all-title'>" + data[i].title +
            "</td><td><img class=\"all-image\" alt=\"文章封面\"></td><td><button class=\"btn btn-default btn_text\">通过</button></td></tr>"
        $("#abody").append(str);
    }
    var allImage = document.getElementsByClassName('all-image')
    for (var i = 0; i < data.length; i++) {
        allImage[i].src = data[i].textimage;
    }

    $("#alltext").html("共："+data.length+"篇")
//跳转文章详情页
    var allLine = document.getElementsByClassName('all-title');
    for (var i = 0; i < allLine.length; i++) {
        allLine[i].index = i;
        allLine[i].onclick = function () {
            var a = this.index;
            $.ajax({
                url: "http://localhost:8066/Music_forum/jumpPage",
                type: "get",
                data: {
                    "thistext": data[a].textid
                },
                success: function () {
                    window.open("http://localhost:8066/Music_forum/login-regist-writeText/article.html");
                }
            })
            getallarticle();
        }
    }

}

function commitdata(data) {
    var textLine = document.getElementsByClassName('t');

    var btnText = document.getElementsByClassName('btn_text');
    for (var i = 0; i < textLine.length; i++) {
        textLine[i].index = i;
        btnText[i].index = i;
        btnText[i].onclick = function () {
            var a = this.index;

            $.ajax({
                url: "http://172.20.151.112:8066/Music_forum/passarticle",
                data: {
                    "textid": data[this.index].textid
                },
                type: "get",
                success: function (str) {
                    var data = eval(str);
                    print(data)
                    cancelcommit(data)
                },
                error: function (err) {
                    alert("网络出错" + err.status)
                }
            })
        }
    }
}
//打印已审核文章列表
function print(data) {
    console.log(data)
    $("#cbody").empty();
    for (var i = 0; i < data.length; i++) {
        var str = "<tr class=\"c\"><td class='text-title'>" + data[i].title + "</td><td><img class=\"home-image\" alt=\"文章封面\"></td><td><button class=\"btn btn-default btn_cancel\">取消展示</button></td></tr>"
        $("#cbody").append(str);
    }
    var homeImage = document.getElementsByClassName('home-image')
    for (var i = 0; i < data.length; i++) {
        homeImage[i].src = data[i].textimage;
    }
    $("#hometext").html("已展示：" + data.length + "篇")
    if (data.length > 6) {
        $("#message").html("注意：主页只能展示最多6篇文章")
    } else {
        $("#message").html("")
    }
    jumphometext(data)
}
function jumphometext(data){
    //点击文章跳转文章内容
    var textLine = document.getElementsByClassName('text-title');
    for (var i = 0; i < textLine.length; i++) {
        textLine[i].index = i;
        textLine[i].onclick = function () {
            var a = this.index;
            $.ajax({
                url: "http://localhost:8066/Music_forum/jumpPage",
                type: "get",
                data: {
                    "thistext": data[a].textid
                },
                success: function () {
                    window.open("http://localhost:8066/Music_forum/login-regist-writeText/article.html");

                }
            })
            gethomearticle();
        }
    }
}
//取消展示
function cancelcommit(data) {
    var textLine = document.getElementsByClassName('c');
    var btnText = document.getElementsByClassName('btn_cancel');
    for (var i = 0; i < textLine.length; i++) {
        textLine[i].index = i;
        btnText[i].index = i;
        btnText[i].onclick = function () {
            var a = this.index;
            $.ajax({
                url: "http://172.20.151.112:8066/Music_forum/cancelarticle",
                data: {
                    "textid": data[this.index].textid
                },
                type: "get",
                success: function (str) {
                    var data = eval(str);
                    print(data);
                    jumphometext(data);
                    cancelcommit(data);
                },
                error: function (err) {
                    alert("网络出错" + err.status)
                }
            })
        }
    }
}

function getalldata() {
    $.ajax({
        url: "http://172.20.151.112:8066/Music_forum/getWebData",
        type: "get",
        dataType: "json",
        success: function (data) {
            console.log(data);
            showwebData(data);
            var arr = arrdata(data);
            showcircle(arr)
        },
        error: function (err) {
            alert("错误信息：" + err.status)
        }
    })
}

function showwebData(data) {
    var allarticle = data.allArticle;
    $("#mandarin").width((data.mandarin / allarticle) * 100 + "%");
    $("#mandarin p:first").html("华语：" + Math.ceil((data.mandarin / allarticle) * 100 )+ "%")
    $("#western").width((data.western / allarticle) * 100 + "%");
    $("#western p:first").html("欧美：" + (data.western / allarticle) * 100 + "%")
    $("#rock").width((data.rock / allarticle) * 100 + "%");
    $("#rock p:first").html("摇滚：" + (data.rock / allarticle) * 100 + "%")
    $("#pop").width((data.pop / allarticle) * 100 + "%");
    $("#pop p:first").html("流行：" + (data.pop / allarticle) * 100 + "%")
    $("#rap").width((data.rap / allarticle) * 100 + "%");
    $("#rap p:first").html("嘻哈：" + (data.rap / allarticle) * 100 + "%")
    $("#tod_view span:first").html(data.todayPageview)
    $("#total_view span:first").html(data.allPageview)
    $("#yes_post span:first").html(data.yestArticle)
    $("#total_post span:first").html(data.allArticle)
    $("#musicReviews").width((data.musicReviews / allarticle) * 100 + "%")
    $("#musicReviews p:first").html("乐评：" + (data.musicReviews / allarticle) * 100 + "%")
    $("#musicStory").width((data.musicStory / allarticle) * 100 + "%")
    $("#musicStory p:first").html("音乐故事：" + (data.musicStory / allarticle) * 100 + "%")
}

function arrdata(data) {
    var arr = [];
    arr.push(data.mandarin);
    arr.push(data.western);
    arr.push(data.rock);
    arr.push(data.pop);
    arr.push(data.rap);
    return arr;
}

function showcircle(arr) {
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        data: {
            datasets: [{
                data: arr,
                backgroundColor: [
                    'rgba(1,1,235,0.8)',
                    'rgba(54, 162, 235, 0.8)',
                    'rgba(255, 206, 86, 0.8)',
                    'rgba(255,1,1,0.8)',
                    'rgba(1,255,1,0.8)'
                ],
            }],
            // These labels appear in the legend and in the tooltips when hovering different arcs
            labels: [
                '华语',
                '欧美',
                '摇滚',
                '流行',
                '嘻哈'
            ]
        },
        type: 'polarArea',
        options: {
            animation: {
                animateRotate: true,
                animateScale: true
            }
        }
    });
}