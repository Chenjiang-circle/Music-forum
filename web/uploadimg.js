window.onload=function () {
    var a = document.getElementById('upload');
    a.onclick=function () {
        function success(text) {
            var textarea = document.getElementById('abc');
            var img = document.getElementById('img');
            text = text.substring(1, text.length-1);
            textarea.value = text;
            img.style.width='50%';
            img.style.height='50%';
            img.src=text;
        }

        function fail(code) {
            var textarea = document.getElementById('abc');
            textarea.value = 'Error code: ' + code;
        }

        var request = new XMLHttpRequest(); // 新建XMLHttpRequest对象

        request.onreadystatechange = function () { // 状态发生变化时，函数被回调
            if (request.readyState === 4) { // 成功完成
                // 判断响应结果:
                if (request.status === 200) {
                    // 成功，通过responseText拿到响应的文本:
                    return success(request.responseText);
                } else {
                    // 失败，根据响应码判断失败原因:
                    return fail(request.status);
                }
            } else {
                // HTTP请求还在继续...
            }
        }

        // 发送请求:
        request.open('POST', '/Music_forum/uploadfile');
        var formData = new FormData();
        formData.append('file', document.getElementById('file').files[0]);
        request.send(formData);
    };


};