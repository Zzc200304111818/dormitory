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
<title>账号管理</title>
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

#passwordForm {
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
				<th>用户名</th>
				<th>密码</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
				<tr>
					<td>${sessionScope.user.username}</td>
					<td>${sessionScope.user.password}</td>
					<td><a href="javascript:void(0);" onclick="showPasswordForm()">修改密码</a></td>
				</tr>
		</tbody>
	</table>
    <!-- 初始时隐藏的密码表单 -->  
    <div id="passwordForm">  
        <form action="${pageContext.servletContext.contextPath}/user/updatePassword" method="post">  
            <label for="newPassword">新密码：</label>  
            <input type="password" id="newPassword" placeholder="新密码" name="newPassword"><br><br>
            <label for="confirmPassword">确认密码：</label>  
            <input type="password" id="confirmPassword" placeholder="确认密码" name="confirmPassword"><br><br>
            <button type="submit" onclick="return submitPasswordForm()">保存更改</button>  
            <button type="button" onclick="hidePasswordForm()">取消</button>  
        </form>  
    </div>
    <script>  
        function showPasswordForm() {  
            document.getElementById('passwordForm').style.display = 'block';  
        }  
  
        function hidePasswordForm() {  
            document.getElementById('passwordForm').style.display = 'none';  
        }  
  
        function submitPasswordForm() {  
        	 let newPassword = document.getElementById('newPassword').value;  
             let confirmPassword = document.getElementById('confirmPassword').value;  
   
             if (newPassword !== confirmPassword) {  
                 alert('新密码和确认密码不匹配，请重新输入！');  
                 return false;  
             }
            return true;
        }  
    </script>	
</body>
</html>