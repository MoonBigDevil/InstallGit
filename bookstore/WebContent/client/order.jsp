<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单</title>

<script type="text/javascript">
	function createOrder() {
		document.getElementById("orderForm").submit();
	}
</script>

</head>
<body style="background: url(images/black.jpg)">
<jsp:include page="menu_search.jsp"></jsp:include>
	<h2 style="color: white;">order.jsp</h2>
	<form id="orderForm"
		action="${pageContext.request.contextPath}/createOrder" method="post">
		<table border="1" cellpadding="0" cellspacing="0" width="100%"style="color: white;">
			<tr>
				<td>
					<h2 style="color: white;">结算中心</h2>
					<p>你好，${user.username}！欢迎您来到网上书城结算中心</p></td>
			</tr>
		</table>

		<table style="text-align: center; margin: 0 auto; color:white;" border="1"
			cellpadding="0" cellspacing="0" width="100%">

			<tr>
				<td width="10%">序号</td>
				<td width="40%">商品名称</td>
				<td width="10%">价格</td>
				<td width="10%">类别</td>
				<td width="10%">数量</td>
				<td width="10%">小计</td>
			</tr>

		</table>
		<c:set value="0" var="totalPrice"></c:set>
		<c:forEach items="${cart}" var="entry" varStatus="vs">
			<table style="text-align: center; margin: 0 auto; color:white;" border="1"
				cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="10%">${vs.count}</td>
					<td width="40%">${entry.key.name }</td>
					<td width="10%">${entry.key.price }</td>
					<td width="10%">${entry.key.category}</td>
					<td width="10%"><input name="text" type="text"
						value="${entry.value}" style="width: 20px" readonly="readonly" />
					</td>
					<td width="10%">${entry.key.price*entry.value}</td>
				</tr>
			</table>
			<c:set var="totalPrice"
				value="${totalPrice+entry.key.price*entry.value}" />
		</c:forEach>
		<%
			//总合计
		%>
		<table style="text-align: center; margin: 0 auto;" border="2"
			cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td style="text-align: right; padding-right: 40px;"><font
					style="color: red;">合计：&nbsp;&nbsp;${totalPrice}元</font> <input
					type="hidden" name="money" value="${totalPrice}"></td>
			</tr>
		</table>

		<p style="color: white;">
			收货地址：<input name="receiverAddress" type="text" value=""
				style="width: 350px" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"></a> <br />
			收货人：&nbsp;&nbsp;&nbsp;&nbsp;<input name="receiverName" type="text"
				value="${user.username}" style="width: 150px" />&nbsp;&nbsp;&nbsp;&nbsp;<a
				href="#"></a> <br /> 联系方式：<input type="text" name="receiverPhone"
				value="${user.telephone}" style="width: 150px" />&nbsp;&nbsp;&nbsp;&nbsp;<a
				href="#"></a>

		</p>
		<p style="text-align: right"">
			<font color="#ff0000">${requestScope["create_order"]}</font>
		</p>

		<hr />
		<table style="" "text-align: right; margin: 0 auto;" border="0"
			cellpadding="0" cellspacing="0" width="100%">
			<tr>

				<td style="text-align: right"><input type="submit" value="提交订单" />
				</td>
			</tr>

		</table>
		<!-- 		<p style="text-align: right">
			<img src="images/gif53_029.gif" width="204" height="51" border="0"
				onclick="createOrder();" />
		</p>
 -->
	</form>
</body>
</html>