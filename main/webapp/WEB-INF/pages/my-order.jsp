<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>My Orders</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/my-orders.css?v=${System.currentTimeMillis()}">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="orders-container">
		<h2>My Orders</h2>

		<c:choose>
			<c:when test="${not empty orders}">
				<div class="orders-table">
					<div class="order-header">
						<div>Product Image</div>
						<div>Product Name</div>
						<div>Quantity</div>
						<div>Price (Rs)</div>
					</div>

					<c:forEach var="order" items="${orders}">
						<div class="order-row">
							<img
								src="${pageContext.request.contextPath}/${order.productImage}"
								alt="Product Image">

							<div>${order.productName}</div>
							<div>${order.orderQuantity}</div>
							<div>Rs ${order.price * order.orderQuantity}</div>
						</div>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<p>You have no past orders.</p>
			</c:otherwise>
		</c:choose>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>
