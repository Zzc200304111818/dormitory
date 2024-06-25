<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<style type="text/css">
#register {
	width: 300px;
	text-align: left;
	margin: 20px auto;
}
#button {
	text-align: center;
}
#login {
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
	<div id="register">
		<form:form modelAttribute="user" method="post" action="${pageContext.servletContext.contextPath}/user/addUser">
			<fieldset>
				<legend><h1>用户注册</h1></legend>
				<form:errors path="username" style="color: red;"></form:errors><br>
				<label>用户名：<form:input path="username"/></label><br/><br/>
				<form:errors path="password" style="color: red;"></form:errors><br>
				<label>密码：<form:password path="password"/></label><br/><br/>
				<div id="button">
					<input type="reset" value="重置"/>
					<input type="submit" value="注册"/>
				</div>
			</fieldset>
		</form:form>
	</div>
	<div id="login"><a href="${pageContext.servletContext.contextPath}/index/login">已有账号？前往登录</a></div>
</body>
</html>