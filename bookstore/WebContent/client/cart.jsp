<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的购物车</title>
<script>
	function changeProductNum(count, totalCount, id) {
		//count:改完后数量 	totalCount:图书剩余数量	id:产品id
		count = parseInt(count);//转换成int
		totalCount = parseInt(totalCount);//转换成int
		//改完后数量为0，代表删除，看是不是要删除该产品
		if (count == 0) {
			var flag = window.confirm("确认删除该商品吗?");

			if (!flag) {
				//如果不删，就让他为1
				count = 1;
			}
		}

		if (count > totalCount) {
			alert("已达到商品最大购买量");
			count = totalCount;
		}

		location.href = "${pageContext.request.contextPath}/changeCart?id="
				+ id + "&count=" + count;
	}
</script>
</head>
<body style="background: url(images/black.jpg)">
	<jsp:include page="menu_search.jsp"></jsp:include>
	<br>
	<br>
	<h1 style="color: white;">cart.jsp</h1>
	<h2 style="color: white;">购物车</h2>
	<table style="text-align: center; margin: 0 auto; color: white;"
		border="1" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td width="10%">序号</td>
			<td width="30%">商品名称</td>
			<td width="10%">价格</td>
			<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量</td>
			<td width="10%">库存</td>
			<td width="10%">小计</td>
			<td width="10%">取消</td>
		</tr>
	</table>
	<c:set var="total" value="0"></c:set>
	<%
		//c:forEach()是个循环 	item；要被循环的信息，var：当前目的变量名称  varStatus：循环状态的变量名称
		//begin。end，step，默认值就代表全遍历，
	%>
	<c:forEach items="${cart}" var="entry" varStatus="vs">
		<table style="text-align: center; margin: 0 auto; color: white;"
			border="1" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td width="10%">${vs.count}</td>
				<td width="30%">${entry.key.name }</td>
				<td width="10%">${entry.key.price }</td>
				<td width="20%"><input type="button" value='-'
					style="width: 20px"
					onclick="changeProductNum('${entry.value-1}','${entry.key.pnum}','${entry.key.id}')">

					<input name="text" type="text" value="${entry.value}"
					style="width: 40px; text-align: center" /> <input type="button"
					value='+' style="width: 20px"
					onclick="changeProductNum('${entry.value+1}','${entry.key.pnum}','${entry.key.id}')">

				</td>
				<td width="10%">${entry.key.pnum}</td>
				<td width="10%">${entry.key.price*entry.value}</td>
				<td width="10%"><a
					href="${pageContext.request.contextPath}/changeCart?id=${entry.key.id}&count=0"
					style="color: #FF0000; font-weight: bold">X</a></td>
			</tr>
		</table>
		<c:set value="${total+entry.key.price *entry.value}" var="total"></c:set>
	</c:forEach>
	<table style="text-align: center; margin: 0 auto;" border="1"
		cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td style="text-align: right; padding-right: 40px;"><font
				style="color: #FF6600; font-weight: bold">合计：&nbsp;&nbsp;${total}元</font>
			</td>
		</tr>
	</table>
	<%-- 返回图书列表 --%>
	<p style="float: right;">
		<a href="${pageContext.request.contextPath}/showProductByPage">返回购物</a>
		&nbsp;&nbsp;&nbsp;
		<a href="${pageContext.request.contextPath}/client/order.jsp">结账</a>
	</p>

</body>
</html>