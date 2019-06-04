<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.yao.bookStore.domain.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的数据</title>
</head>
<body style="background: url(images/black.jpg)">
<jsp:include page="menu_search.jsp"></jsp:include>
<br>
<br>
	<h1 style="color: white;">myAccount.jsp</h1>
	<%-- <%
		String name = request.getParameter("name");
	%> --%>
	<c:if test="${user.username==null}">
		<p style="text-align: left; margin: 0 auto;color: white;font-size: 30px;" 
			 width="60%">
			 很抱歉，您还没有登录呢。
			 </p>
	</c:if>
	<c:if test="${user.username != null}">
	<p style="text-align: left; margin: 0 auto;color: white;font-size: 30px;" 
			 width="60%">普通用户：${user.username}<br>
			&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 您好！恭喜您登陆成功。“我的数据”页面正在升级中。。。<br>
			 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 快去买书吧</p>
			 </c:if>
	
	
	<table>
	
	</table>
	
	
</body>
</html>