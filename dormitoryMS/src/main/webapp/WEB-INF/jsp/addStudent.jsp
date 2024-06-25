<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生注册</title>
<style type="text/css">
#register {
	width: 300px;
	text-align: left;
	margin: 20px auto;
}
#button {
	text-align: center;
}
body {
	background-color: #f2f0e4;
}
</style>
</head>
<body>
	<div id="register">
		<form:form modelAttribute="stu" method="post" action="${pageContext.servletContext.contextPath}/student/addStudent">
			<fieldset>
				<legend><h1>学生注册</h1></legend>
				<form:errors path="stuNo" style="color: red;"></form:errors><br>
				<label>学号：<form:input path="stuNo"/></label><br/><br/>
				<form:errors path="name" style="color: red;"></form:errors><br>
				<label>姓名：<form:input path="name"/></label><br/><br/>
				<form:errors path="sex" style="color: red;"></form:errors><br>
				<label>性别：<form:input path="sex"/></label><br/><br/>
				<form:errors path="age" style="color: red;"></form:errors><br>
				<label>年龄：<form:input path="age"/></label><br/><br/>
				<form:errors path="dormitoryName" style="color: red;"></form:errors><br>
				<label>宿舍名：<form:input path="dormitoryName"/></label><br/><br/>
				<div id="button">
					<input type="reset" value="重置"/>
					<input type="submit" value="注册"/>
				</div>
			</fieldset>
		</form:form>
	</div>
</body>
</html>