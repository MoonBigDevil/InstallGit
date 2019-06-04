<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bookStore会员注册页面</title>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/client/css/main.css"
	type="text/css" />
</head>
<body style="background: url(images/black.jpg)">
<jsp:include page="menu_search.jsp"></jsp:include>
	<form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
		<br>
		<br>
				<h2 style="color: white;">会员注册</h2>
				<table style="text-align: center; margin: 0 auto;color: white;" border="1"
			cellpadding="0" cellspacing="0" width="30%">

					<tr>
						<td style="text-align: right">用户昵称：</td>
						<td><input value="" type="text" name="username" id="username"
							class="input" /></td>
						<td><font color="red">不能为空</font></td>
					</tr>
					<tr>
						<td style="text-align: right">密码：</td>
						<td><input type="password" name="password" id="password"
							class="input" /></td>
						<td><font color="red">不能为空</font></td>
					</tr>
					<tr>
						<td style="text-align: right">确认密码：</td>
						<td><input type="password" name="password1"
							id="reloginpass" class="input" /></td>
						<td>&nbsp;</td>
					</tr>
					
					<tr>
						<td style="text-align: right">性别：</td>
						<td colspan="2"><input type="radio" name="gender" value="男"
							checked="checked" />男 <input type="radio" name="gender"
							value="女" checked="checked" />女</td>

					</tr>
					<tr>
						<td style="text-align: right">会员邮箱：</td>
						<td><input value="" type="text" name="email" id="email"
							class="input" /></td>
						<td><font color="red">请输入正确邮箱</font></td>
					</tr>
					<tr>
						<td style="text-align: right">联系电话：</td>
						<td colspan="2"><input style="width: 350px" type="text"
							name="telephone" id="telephone" class="input" /></td>

					</tr>
					<tr>
						<td style="text-align: right">个人介绍：</td>
						<td colspan="2" ><textarea class="textarea" name="introduce"></textarea></td>

					</tr>
						<td colspan="3" style="text-align: center;"><input type="submit" value="提交"/></td>
					</tr>
		</table>
	</form>
</body>
</html>