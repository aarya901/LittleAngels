<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin.css" />
</head>
<body>
	<h1>Admin Dashboard</h1>
	<div class="dashboard-container">
		<aside class="sidebar">
			<ul>
				<li><a href="${pageContext.request.contextPath}/admin-profile">Profile</a></li>
				<li><a href="${pageContext.request.contextPath}/add-product">Add
						Product</a></li>
				<li><a href="${pageContext.request.contextPath}/admin-product">Product
						View</a></li>
				<c:if test="${not empty sessionScope.username}">
					<button class="logged-in-btn">Logged in as
						${sessionScope.username}</button>
					<form action="${pageContext.request.contextPath}/logout"
						method="post" style="margin-top: 10px;">
						<button type="submit" class="btn btn-danger">Logout</button>
					</form>
				</c:if>
			</ul>
		</aside>

		<main class="main-dashboard">
			<section class="bottom-section">
				<h2>Recent Orders</h2>

				<div class="recent-orders">
					<table>
						<thead>
							<tr>
								<th>Order ID</th>
								<th>Product</th>
								<!-- Removed Image column as requested -->
								<th>Quantity</th>
								<th>Price</th>
								<th>Order Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="order" items="${recentOrders}">
								<tr>
									<td>${order.orderId}</td>
									<td>${order.productName}</td>
									<td>${order.orderQuantity}</td>
									<td>Rs. ${order.price}</td>
									<td>${order.orderDate}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<c:if test="${empty recentOrders}">
						<p class="no-orders">No orders found.</p>
					</c:if>
				</div>
			</section>
		</main>
	</div>
</body>
</html>
