<%@page pageEncoding="UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>

springMVC上传文件
<form name="form" action="/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="springMVC上传文件">
</form>

<p>
    <label>请选择一个图像文件：</label>
    <input type="file" id="file_input"/>
</p>
<div id="result"></div>

<script type="text/javascript">
    var result = document.getElementById("result");
    var input = document.getElementById("file_input");

    if (typeof FileReader === 'undefined') {
        result.innerHTML = "抱歉，你的浏览器不支持 FileReader";
        input.setAttribute('disabled', 'disabled');
    } else {
        input.addEventListener('change', readFile, false);
    }


    function readFile() {
        var file = this.files[0];
        if (!/image\/\w+/.test(file.type)) {
            alert("文件必须为图片！");
            return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function (e) {
            result.innerHTML = '<img src="' + this.result + '" alt=""/>'
        }
    }
</script>

</body>
</html>
