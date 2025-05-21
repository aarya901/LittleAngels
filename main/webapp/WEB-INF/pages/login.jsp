<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BabyProduct - Online Shop</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login.css?v=1.0" />


</head>
<body>
	<div class="login-container">
		<h1 class="shop-title">Little Angels - Online Shop</h1>
		<p class="shop-description">We specialize in Baby Products</p>

		<div class="login-form">
			<h2>Login</h2>

			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/login" method="post">
				<div class="form-group">
					<label for="user_name">Username</label> <input type="text"
						id="user_name" name="user_name">
				</div>

				<div class="form-group">
					<label for="user_password">Password</label> <input type="password"
						id="user_password" name="user_password">
				</div>

				<button type="submit" class="login-btn">Login</button>
			</form>

			<div class="links">
				<p>
					Don't have an account? <a
						href="${pageContext.request.contextPath}/register">Register</a>
				</p>
				<p>
					Go back to <a href="${pageContext.request.contextPath}/home">Little
						Angels</a>
				</p>
			</div>
		</div>
	</div>
</body>

</html>



