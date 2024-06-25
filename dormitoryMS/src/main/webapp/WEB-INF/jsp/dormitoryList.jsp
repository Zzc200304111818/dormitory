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

#dormitoryForm {
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
				<th>宿舍名</th>
				<th>总床位</th>
				<th>剩余床位</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
				<c:forEach var="dormitory" items="${dormitories}">
					<tr>
						<td>${dormitory.dormitoryName}</td>
						<td>${dormitory.totalBeds}</td>
						<td>${dormitory.availableBeds}</td>
						<td>${dormitory.remark}</td>
						<td>
							<a href="javascript:void(0);" onclick="showDormitoryForm('${dormitory.dormitoryName}')">修改</a>
							<a href="${pageContext.servletContext.contextPath}/dormitory/deleteDormitory?dormitoryName=${dormitory.dormitoryName}">删除</a>
						</td>
				</tr>
				</c:forEach>
		</tbody>
	</table>
    <!-- 初始时隐藏的密码表单 -->  
    <div id="dormitoryForm">  
        <form action="${pageContext.servletContext.contextPath}/dormitory/updateDormitory" method="post">
        	<input type="hidden" id="dormitoryName" name="dormitoryName" value="">  
            <label for="totalBeds">总床位：</label>  
            <input type="text" id="totalBeds" placeholder="总床位" name="totalBeds"><br><br>
            <label for="availableBeds">剩余床位：</label>  
            <input type="text" id="availableBeds" placeholder="剩余床位" name="availableBeds"><br><br>
            <label for="remark">备注：</label>  
            <input type="text" id="remark" placeholder="备注" name="remark"><br><br>
            <button type="submit" onclick="return submitDormitoryForm()">保存更改</button>  
            <button type="button" onclick="hideDormitoryForm()">取消</button>  
        </form>  
    </div>
    <script>  
        function showDormitoryForm(dormitoryName) {  
            document.getElementById('dormitoryForm').style.display = 'block';  
            // 假设您在表单中有一个隐藏的字段来存储dormitoryName  
            document.getElementById('dormitoryName').value = dormitoryName; 
        }  
  
        function hideDormitoryForm() {  
            document.getElementById('dormitoryForm').style.display = 'none';  
        }  
  
        function submitDormitoryForm() {  
			
        }  
    </script>	
</body>
</html>