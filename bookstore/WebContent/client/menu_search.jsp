<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/client/css/menu.css"
	type="text/css" />
</head>
<body>
	<hr />

	<table style="margin: 0 auto;" border="0" cellpadding="0"
		cellspacing="0" width="100%">
		<tr>
			<td style="text-align: left"><h1 style="color: white;">网上书城</h1></td>
			<td style="text-align: right; float: right;">
				<li style="float: left;"><a
					href="${pageContext.request.contextPath}/client/index.jsp">首页</a></li>
				<li style="float: left;"><a
					href="${pageContext.request.contextPath}/client/register.jsp">新用户注册</a></li>
				<li style="float: left;"><a
					href="${pageContext.request.contextPath}/client/login.jsp">登录</a></li>
				<li style="float: left;"><a
					href="${pageContext.request.contextPath}/client/cart.jsp">我的购物车</a></li>
				<li style="float: left;"><a
					href="${pageContext.request.contextPath}/client/myAccount.jsp">我的账户</a></li>
			</td>
		</tr>
	</table>
	<hr />

	<div class="menu">
		<ul>
			<li><a
				href="javascript:location.href=encodeURI(
				'${pageContext.servletContext.contextPath}/showProductByPage?category=wenxue')">文学</a></li>
			<li><a
				href="${pageContext.request.contextPath}/showProductByPage?category=life">生活</a></li>
			<li><a
				href="${pageContext.request.contextPath}/showProductByPage?category=computer">计算机</a></li>
			<li><a
				href="${pageContext.request.contextPath}/showProductByPage?category=waiyu">外语</a></li>
			<li><a
				href="${pageContext.request.contextPath}/showProductByPage?category=lizhi">励志</a></li>
			<li><a
				href="${pageContext.request.contextPath}/showProductByPage?category=xueshu">学术</a></li>
			<li><a
				href="${pageContext.request.contextPath}/showProductByPage?category=shaoer">少儿</a></li>
			<li><a
				href="${pageContext.request.contextPath}/showProductByPage?category=wenxue">艺术</a></li>
			<li><a
				href="${pageContext.request.contextPath}/showProductByPage?category=keji">科技</a></li>
			<li><a
				href="${pageContext.request.contextPath}/showProductByPage?category=kaoshi">考试</a></li>
			<li><a
				href="${pageContext.request.contextPath}/showProductByPage?category=baike">生活百科</a></li>
			<li><a
				href="${pageContext.request.contextPath}/showProductByPage?category=JavaWeb">JavaWeb</a></li>
			<li><a
				href="javascript:location.href=encodeURI(
				'${pageContext.servletContext.contextPath}/showProductByPage?category=all')">全部商品</a></li>
			<li>
				<form action="${pageContext.request.contextPath }/menuSearch"
					method="post" id="searchform">
					<table width="100%" border="0" cellspacing="0">
						<tr>
							<td style="text-align: right;"><input type="text"
								name="textfield" value="请输入书名进行搜索" onmouseover="this.focus();"
								onclick="my_click(this, 'textfield');"
								onBlur="my_blur(this, 'textfield');" /> <input type="submit"
								value="搜索"></td>
						</tr>
					</table>
				</form>
			</li>
		</ul>
	</div>
	<hr />


</body>
</html>