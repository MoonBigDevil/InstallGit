<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书列表</title>
</head>
<body style="background-color: black;">
	<jsp:include page="menu_search.jsp"></jsp:include>
	<br><br>
	<h2 style="color: white;">product_list.jsp</h2>

	<c:forEach items="${bean.psList}" var="p" varStatus="vs">
		
			<p style="color: white">
			书名：${p.name}&nbsp;&nbsp; 价格: ${p.price}元&nbsp;&nbsp;库存:${p.pnum}
			&nbsp;&nbsp;&nbsp;
			<a
			href="${pageContext.request.contextPath}/AddCartServlet?id=${p.id}">加入购物车</a>
		<br>
		</p>
	</c:forEach>
	<!-- 下一页上一页还没实现 -->
	<ul style="list-style-type: none;position: fixed;">
		<c:if test="${bean.currentPage!=1}">
			<li style="float: left;"><a
				href="${pageContext.request.contextPath}/showProductByPage?currentPage=${bean.currentPage-1}&category=${bean.category}">
				上一页</a>
			</li>
		</c:if>
		<c:if test="${bean.currentPage==1}">
			<li style="float: left;color: white;">首页</li>
		</c:if>
		<c:forEach begin="1" end="${bean.totalPage}" var="pageNum">
			<c:if test="${pageNum==bean.currentPage}">
				<li style="float: left;color: white;" >${pageNum }</li>
			</c:if>
			<c:if test="${pageNum!=bean.currentPage}">
				<li style="float: left;color: white;"><a 
				href="${pageContext.request.contextPath}/showProductByPage?currentPage=${pageNum}&category=${bean.category}">${pageNum}</a>
				</li>
			</c:if>
		</c:forEach> 

		<c:if test="${bean.currentPage==bean.totalPage||bean.totalPage==0}">
			<li style="float: left;color: white;">尾页</li>
		</c:if>
		<c:if test="${bean.currentPage!=bean.totalPage&&bean.totalPage!=0}">
			<li style="float: left;color: white;"><a
				href="${pageContext.request.contextPath}/showProductByPage?currentPage=${bean.currentPage+1}&category=${bean.category}">下一页
					</a></li>
		</c:if>
	</ul>
</body>
</html>