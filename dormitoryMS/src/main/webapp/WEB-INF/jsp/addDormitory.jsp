<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>寝室注册</title>
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
		<form:form modelAttribute="dormitory" method="post" action="${pageContext.servletContext.contextPath}/dormitory/addDormitory">
			<fieldset>
				<legend><h1>寝室注册</h1></legend>
				<form:errors path="dormitoryName" style="color: red;"></form:errors><br>
				<label>宿舍名：<form:input path="dormitoryName"/></label><br/><br/>
				<form:errors path="totalBeds" style="color: red;"></form:errors><br>
				<label>总床位：<form:input path="totalBeds"/></label><br/><br/>
				<form:errors path="availableBeds" style="color: red;"></form:errors><br>
				<label>剩余床位：<form:input path="availableBeds"/></label><br/><br/>
				<form:errors path="remark" style="color: red;"></form:errors><br>
				<label>备注：<form:input path="remark"/></label><br/><br/>
				<div id="button">
					<input type="reset" value="重置"/>
					<input type="submit" value="注册"/>
				</div>
			</fieldset>
		</form:form>
	</div>
</body>
</html>