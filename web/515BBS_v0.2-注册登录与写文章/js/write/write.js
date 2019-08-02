$(document).ready(function () {
    window.scroll()

    $("#write-title").focusin(function () {
        if ($('#write-title').val() == "请输入文章标题" || $('#write-title').val() == "别忘记写文章标题哦") {
            $('#write-title').val("");
        }
    })
    $("#write-title").focusout(function () {
        if ($('#write-title').val() == "") {
            $('#write-title').val("别忘记写文章标题哦");
        }
    })



    $("#submitArticle").click(function () {

        var title = $("#write-title"); //文章标题
        var content = $("#editor1").val(); //文章内容

        //判断是否在前面加0
        function getNow(s) {
            return s < 10 ? '0' + s : s;
        }

        var myDate = new Date();

        var year = myDate.getFullYear(); //获取当前年
        var month = myDate.getMonth() + 1; //获取当前月
        var date = myDate.getDate(); //获取当前日


        var h = myDate.getHours(); //获取当前小时数(0-23)
        var m = myDate.getMinutes(); //获取当前分钟数(0-59)
        var s = myDate.getSeconds();

        var now = year + '-' + getNow(month) + "-" + getNow(date) + " " + getNow(h) + ':' + getNow(m) + ":" + getNow(s);

        var check = $("input[name='ArticleSelect']:checked").serialize();
        if (check.length == 0) {
            alert("您至少需要选择一个文章分类哦!");
        } else if (title.val() == "请输入文章标题" || title.val() == "" || title.val() == "别忘记写文章标题哦") {
            alert("别忘了写文章标题哦!")
        } else if (content.length == 0) {
            alert("请编辑您的文章内容后再发表.")
        } else {
            $.ajax({
                type: "POST",
                url: "./write",
                data: {
                    userid: null,
                    textid: null,
                    time: now,
                    likes: null,
                    comment: null,
                    collection: null,
                    text: content,
                    title: title,
                    type: check,
                },
                dataType: "json",
                success: function (data) { //请求成功，data为后台数据
                    if (data.success) {
                        alert("❤成功提交! \n❤文章将在审核后发表.\n❤感谢您的贡献.祝您拥有愉快的一天");
                        window.location.href = "";
                        //跳转到首页
                    } else {
                        alert("OOOOPS! 服务器出现了一个小问题：" + data.msg);
                    }
                },
                error: function (jqXHR) {
                    alert("OOPS! 服务器出现了一个小问题：" + jqXHR.status);
                }
            })


        }

        return false;
    })
})