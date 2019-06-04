<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
</head>
<body style="background: url(images/black.jpg)">
<jsp:include page="menu_search.jsp"></jsp:include>
	<%
		String name = "";
		String pwd = "";
		Cookie[] c = request.getCookies();
		for (int i = 0; i < c.length; i++) {
			if (c[i].getName().equals("remembername")) {
				name = c[i].getValue().split("#")[0];
				pwd = c[i].getValue().split("#")[1];

				request.setAttribute("name1", name);
				request.setAttribute("pwd1", pwd);
			}
		}
	%>
<br> 
<br> 
	<form action="${pageContext.request.contextPath}/LoginServlet"
		method="post">
		<table style="text-align: center; margin: 0 auto;color: white;" border="1"
			cellpadding="0" cellspacing="0" width="30%">
			<tr>

				<h2 style="color: white;">用户登录</h2>
				<td colspan="2" style="text-align: center">个人用户登录</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center; padding-top: 20px;"><font
					color="#ff0000">${requestScope["register_message"]}</font></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center; padding-top: 20px;"><font
					color="#ff0000">${requestScope["user_null"]}</font></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><font
					color="#ff0000">${requestScope["login_m"]}</font></td>
			</tr>
			<tr>
				<td style="text-align: right padding-top:5px; width: 25%">用户名：</td>
				<td style="text-align: left"><input type="text" name="username"
					value="${name1}"></td>

			</tr>
			<tr>
				<td style="text-align: right padding-top:5px; width: 25%">密&nbsp;&nbsp;&nbsp;码：</td>
				<td style="text-align: left"><input type="password"
					name="password"></td>

			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><input
					type="checkbox" name="checkbox1" value="checkbox" />
					记住用户名&nbsp;&nbsp;&nbsp; <input type="checkbox" name="checkbox2"
					value="autologin" /> 自动登录</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><input type="submit"
					value="登录"></td>
			</tr>

		</table>
		<!-- //修改成p标签 -->
		<table style="text-align: center; margin: 0 auto; color: white;" border="0"
			cellpadding="0" cellspacing="0" width="30%">
			<tr>
				<h2 style="color: white;">会员注册</h2>
				<td colspan="2" style="text-align: left;">
					<h3 color="red">您还没有注册？</h3>
				</td>
			</tr>

			<tr>
				<td colspan="2" style="text-align: left;">
					<p>
						注册新会员，享受更优惠的价格！<br> <br> 千万种图书，供你挑选！注册即享受丰富折扣和优惠，便宜有好货，
						超过万本图书任您选。 <br> <br> 超人气社区，精彩活动每一天！
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: left;">点击<a
					href="${pageContext.request.contextPath}/client/register.jsp">注册</a>,
					立即享受优惠活动！
				</td>
			</tr>
		</table>

	</form>


</body>
</html>