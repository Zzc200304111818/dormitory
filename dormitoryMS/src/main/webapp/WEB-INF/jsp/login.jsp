<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<style type="text/css">
#login {
	width: 300px;
	text-align: left;
	margin: 20px auto;
}
#button {
	text-align: center;
}
#register {
	width: 300px;
	text-align: right;
	margin: 20px auto;
}
body {
	background-color: #f2f0e4;
}
</style>
</head>
<body>
	<div id="login">
		<form:form modelAttribute="userForm" method="post" action="${pageContext.servletContext.contextPath}/user/doLogin">
			<fieldset>
				<legend><h1>登录</h1></legend>
				<form:errors path="username" style="color: red;"></form:errors><br>
				<label>用户名：<form:input path="username"/></label><br/><br/>
				<form:errors path="password" style="color: red;"></form:errors><br>
				<label>密码：<form:password path="password"/></label><br/><br/>
				<label>验证码：<form:input style="width: 100px;" path="code"/></label><img alt="验证码" src="${pageContext.servletContext.contextPath}/index/getCode"><br/><br/>
				<div id="button">
					<input type="reset" value="重置"/>
					<input type="submit" value="登录"/>
				</div>
			</fieldset>
		</form:form>
	</div>
	<div id="register"><a href="${pageContext.servletContext.contextPath}/index/register">没有账号？前往注册</a></div>
</body>
</html>