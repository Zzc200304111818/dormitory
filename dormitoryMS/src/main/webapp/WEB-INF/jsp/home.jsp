<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>主页</title>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

body {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	margin: 20px auto;
	height: auto;
	width: 1000px;
	border: 1px solid #006633;
	background-color: #f2f0e4;
}

#header {
	height: 90px;
	width: 1000px;
	margin: 0px 0px 3px 0px;
}

#header h1 {
	text-align: center; font-family：华文彩云;
	color: #000000;
	font-size: 30px;
}

#navigator {
	height: 25px;
	width: 1000px;
	font-size: 14px;
}

#navigator ul {
	list-style-type: none;
}

#navigator li {
	float: left;
	position: relative;
}

#navigator li a {
	color: #000000;
	text-decoration: none;
	padding-top: 4px;
	display: block;
	width: 98px;
	height: 22px;
	text-align: center;
	background-color: white;
	margin-left: 2px;
}

#navigator li a:hover {
	background-color: #006633;
	color: #FFFFFF;
	cursor: pointer;
}

#navigator ul li ul {
	visibility: hidden;
	position: absolute;
}

#navigator ul li:hover ul, #navigator ul a:hover ul {
	visibility: visible;
}

#content {
	height: auto;
	width: 780px;
	padding: 10px;
}

#content iframe {
	height: 600px;
	width: 980px;
}

#footer {
	height: 30px;
	width: 980px;
	line-height: 2em;
	text-align: center;
	background-color: white;
	padding: 10px;
}
</style>
</head>
<body>
	<div id="header">
		<br><br>
		<h1>欢迎进入宿舍管理系统！</h1>
	</div>
	<div id="navigator">
		<ul>
			<li><a>个人中心</a>
				<ul>
					<li><a onclick="getUserIframe()">账号管理</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/user/deleteUser">注销账户</a></li>
				</ul></li>
			<li><a>学生管理</a>
				<ul>
					<li><a onclick="getStudentIframe()">学生列表</a></li>
					<li><a onclick="getAddStudentIframe()">添加学生</a></li>
				<li><a onclick="getQueryStudentIframe()">查找学生</a></li>
				</ul></li>
			<li><a>宿舍管理</a>
				<ul>
					<li><a onclick="getDormitoryIframe()">宿舍列表</a></li>
					<li><a onclick="getAddDormitoryIframe()">添加宿舍</a></li>
					<li><a onclick="getQueryDormitoryIframe()">查找宿舍</a></li>
				</ul></li>
			<li><a href="${pageContext.servletContext.contextPath}/index/logout">安全退出</a></li>
		</ul>
	</div>
	<div id="content">
		<div id="contextPath" data-context-path="${pageContext.servletContext.contextPath}"></div> 
		<iframe src="../images/dormitory.jpg" name="center"></iframe>
	</div>
	<div id="footer">HELLO JAVAEE!!!</div>
	<script>  
		function getUserIframe() {
			let iframe = document.querySelector('#content iframe')
			let contextPath = document.getElementById('contextPath').getAttribute('data-context-path')
			iframe.src = contextPath + "/index/getUserList"
		}
		function getStudentIframe() {
			let iframe = document.querySelector('#content iframe')
			let contextPath = document.getElementById('contextPath').getAttribute('data-context-path')
			iframe.src = contextPath + "/student/getStudentList"
		}
		function getDormitoryIframe() {
			let iframe = document.querySelector('#content iframe')
			let contextPath = document.getElementById('contextPath').getAttribute('data-context-path')
			iframe.src = contextPath + "/dormitory/getDormitoryList"
		}
		function getAddStudentIframe() {
			let iframe = document.querySelector('#content iframe')
			let contextPath = document.getElementById('contextPath').getAttribute('data-context-path')
			iframe.src = contextPath + "/index/getAddStudent"	
		}
		function getAddDormitoryIframe() {
			let iframe = document.querySelector('#content iframe')
			let contextPath = document.getElementById('contextPath').getAttribute('data-context-path')
			iframe.src = contextPath + "/index/getAddDormitory"
		}
		function getQueryDormitoryIframe() {
			let iframe = document.querySelector('#content iframe')
			let contextPath = document.getElementById('contextPath').getAttribute('data-context-path')
			iframe.src = contextPath + "/index/getQueryDormitory"
		}
		function getQueryStudentIframe() {
			let iframe = document.querySelector('#content iframe')
			let contextPath = document.getElementById('contextPath').getAttribute('data-context-path')
			iframe.src = contextPath + "/index/getQueryStudent"
		}
	</script>
</body>
</html>