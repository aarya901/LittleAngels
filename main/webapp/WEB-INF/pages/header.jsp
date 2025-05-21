<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Little Angels</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css?v=1.0">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/login.js"></script>
</head>
<body>
	<!-- Navigation Bar -->
	<header>
		<nav class="navbar">
			<div class="logo">Little Angels</div>
			<ul class="navbar-links">
				<li><a href="${pageContext.request.contextPath}/home">Home</a></li>

				<!-- Show Login/Register only if not logged in -->
				<c:if test="${empty sessionScope.username}">
					<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
					<li><a href="${pageContext.request.contextPath}/register">Register</a></li>
				</c:if>

				<li><a href="${pageContext.request.contextPath}/product">Product</a></li>

				<!-- Show only when logged in -->
				<c:if test="${not empty sessionScope.username}">
					<li><a href="${pageContext.request.contextPath}/cart">My
							Cart</a></li>
					<li><a href="${pageContext.request.contextPath}/my-order">My
							Order</a></li>
					<li><a href="${pageContext.request.contextPath}/user-profile">
							<img
							src="${pageContext.request.contextPath}/images/profile_image.jpg"
							alt="Profile" class="profile_img">
					</a></li>
				</c:if>

				<li><a href="${pageContext.request.contextPath}/about">About
						Us</a></li>
			</ul>

			<div class="search-bar">
				<form id="searchForm"
					action="${pageContext.request.contextPath}/product" method="get">
					<input type="text" id="searchInput" name="query"
						placeholder="Search baby products...">
					<button type="submit">Search</button>
				</form>

			</div>


			<!-- Show username and logout button when logged in -->
			<c:if test="${not empty sessionScope.username}">
				<button class="logged-in-btn">Logged in as
					${sessionScope.username}</button>
				<form action="${pageContext.request.contextPath}/logout"
					method="post" style="display: inline;">
					<button type="submit" class="btn btn-danger">Logout</button>
				</form>
			</c:if>
		</nav>
	</header>
</body>
</html>
