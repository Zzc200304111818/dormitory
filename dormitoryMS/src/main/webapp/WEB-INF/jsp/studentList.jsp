<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	%>
<%@taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生列表</title>
<style type="text/css">
table {
	border: 1px solid black;
	width: 100%;
	border-collapse: collapse;
}

th, td {
	width: 100px;
	border: 1px solid black;
	text-align: center;
}

a {
	text-decoration: none;
}

#studentForm {
	display: none;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	border: 1px solid #ccc;
	padding: 20px;
	background-color: #fff;
	z-index: 1000;
	text-align: left;
}
button {
	text-align: center;
}
</style>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>宿舍名</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
				<c:forEach var="student" items="${students}">
					<tr>
						<td>${student.stuNo}</td>
						<td>${student.name}</td>
						<td>${student.sex}</td>
						<td>${student.age}</td>
						<td>${student.dormitoryName}</td>
						<td>
							<a href="javascript:void(0);" onclick="showStudentForm('${student.stuNo}','${student.dormitoryName}')">修改</a>
							<a href="${pageContext.servletContext.contextPath}/student/deleteStudent?stuNo=${student.stuNo}">删除</a>
						</td>
				</tr>
				</c:forEach>
		</tbody>
	</table>
    <!-- 初始时隐藏的密码表单 -->  
    <div id="studentForm">  
        <form action="${pageContext.servletContext.contextPath}/student/updateStudent" method="post">
        	<input type="hidden" name="stuNo" value="" id="stuNo"/>
            <label for="name">姓名：</label>  
            <input type="text" id="name" placeholder="请输入姓名" name="name"><br><br>
            <label for="sex">性别：</label>  
            <input type="text" id="sex" placeholder="请输入性别" name="sex"><br><br>
            <label for="age">年龄：</label>  
            <input type="text" id="age" placeholder="请输入年龄" name="age"><br><br>
            <input type="hidden" value="" name="dormitoryName" id="dormitoryName"/>
            <button type="submit" onclick="return submitStudentForm()">保存更改</button>  
            <button type="button" onclick="hideStudentForm()">取消</button>  
        </form>  
    </div>
    <script>  
        function showStudentForm(stuNo,dormitoryName) {  
            document.getElementById('studentForm').style.display = 'block';
            document.getElementById('stuNo').value = stuNo
            document.getElementById('dormitoryName').value = dormitoryName
        }  
  
        function hideStudentForm() {  
            document.getElementById('studentForm').style.display = 'none';  
        }  
  
        function submitStudentForm() {  

        }  
    </script>	
</body>
</html>