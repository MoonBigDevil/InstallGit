<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table style="text-align: center; margin: 0 auto;" border="1"
			cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td style="text-align: left"><h1 style="color: white;">网上书城</h1></td>
			<td>
				<ur>
					<li style="float: left;"><a href="${pageContext.request.contextPath}/client/index.jsp">首页</a></li>
					<li style="float: left;"><a href="${pageContext.request.contextPath}/client/register.jsp">新用户注册</a></li>
					<li style="float: left;"><a href="${pageContext.request.contextPath}/client/login.jsp">登录</a></li>
					<li style="float: left;"><a href="${pageContext.request.contextPath}/client/cart.jsp">我的购物车</a></li>
					<li style="float: left;"><a href="${pageContext.request.contextPath}/client/myAccount.jsp">我的账户</a></li>
				</ur>
			</td>
		</tr>
	</table>
</body>
</html>