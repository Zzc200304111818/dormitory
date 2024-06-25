<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查找学生</title>
<style type="text/css">
#find {
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
	<div id="find">
		<form method="post" action="${pageContext.servletContext.contextPath}/student/queryStudent">
			<fieldset>
				<legend><h1>学生查找</h1></legend>
				<label>学号：<input type="text" name="stuNo"/></label><br/><br/>
				<div id="button">
					<input type="reset" value="重置"/>
					<input type="submit" value="查找"/>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>