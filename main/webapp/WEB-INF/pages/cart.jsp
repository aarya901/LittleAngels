<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Your Shopping Cart</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/addcart.css?v=1.0">
</head>
<body>
	<jsp:include page="header.jsp" />

	<h2>Your Shopping Cart</h2>

	<c:if test="${not empty sessionScope.cartSuccess}">
		<div class="success">${sessionScope.cartSuccess}</div>
		<c:remove var="cartSuccess" scope="session" />
	</c:if>

	<c:choose>
		<c:when test="${not empty cartItems}">
			<table>
				<thead>
					<tr>
						<th>Image</th>
						<th>Product</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Subtotal</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="grandTotal" value="0" />
					<c:forEach var="item" items="${cartItems}">
						<tr>
							<td><img
								src="${pageContext.request.contextPath}/${item.productImage}"
								alt="${item.productName}" width="80" height="80" /></td>
							<td>${item.productName}</td>
							<td>$<fmt:formatNumber value="${item.price }" type="number"
									minFractionDigits="2" /></td>
							<td>${item.cartQuantity}</td>
							<td>$<fmt:formatNumber
									value="${item.price *  item.cartQuantity}" type="number"
									minFractionDigits="2" /></td>
							<td>
								<form action="${pageContext.request.contextPath}/cart"
									method="post">
									<input type="hidden" name="cartItemId"
										value="${item.cartItemId}" /> <input type="hidden"
										name="action" value="remove" />
									<button type="submit">Remove</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>

		<c:otherwise>
			<div class="empty-message">Your cart is currently empty.</div>
		</c:otherwise>
	</c:choose>

</body>
</html>
