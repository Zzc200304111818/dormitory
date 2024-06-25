<%@ page  
    language="java"  
    contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html>  
<html>  
<head>  
<meta charset="UTF-8">  
<title>主页</title>  
<style>  
body {  
    margin: 0;  
    padding: 0;  
    font-family: Arial, sans-serif;  
    background-image: url('images/background.jpg');  
    background-repeat: no-repeat;  
    background-position: center left;  
    background-color: #f2f0e4;   
}  
  
.main {  
    display: flex;  
    flex-direction: column;  
    align-items: center;  
    justify-content: center;  
    height: 100vh; /* 使内容垂直居中 */  
    text-align: center;  
}  
  
.button-link {  
    display: inline-block; /* 使链接可以设置为按钮宽度 */  
    width: 200px;  
    height: 50px;  
    line-height: 50px; /* 垂直居中文字 */  
    text-align: center;  
    background: linear-gradient(to right, rgb(50, 150, 250), rgb(119, 68, 255)); 
    color: #ffdd99; /* 白色文字 */  
    border: none;  
    border-radius: 50px; /* 圆角 */  
    text-decoration: none; /* 去除下划线 */  
    margin-bottom: 10px; /* 底部间距 */  
    font-size: large;  
    cursor: pointer; /* 鼠标悬停时显示为小手 */  
}  
  
.button-link:hover {  
    background-color: #45a049; /* 悬停时颜色变化 */  
}  
</style>  
</head>  
<body>  
    <div class="main">  
        <a href="${pageContext.servletContext.contextPath}/index/register" class="button-link">注册</a>  
        <a href="${pageContext.servletContext.contextPath}/index/login" class="button-link">登录</a>  
    </div>  
</body>  
</html>