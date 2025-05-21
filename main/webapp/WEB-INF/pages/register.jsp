<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register.css?v=1.0" />
</head>
<body>
	<div class="form-container">
		<h2>User Registration Form</h2>

		<!-- Error Message Display -->
		<c:if test="${not empty error}">
			<div style="color: red; font-weight: bold; margin-bottom: 10px;">${error}</div>
		</c:if>

		<form action="register" method="post" enctype="multipart/form-data">
			<h2>Register Here</h2>

			<div class="form-grid">

				<!-- First Name and Last Name side by side -->
				<div>
					<label for="fname">First Name</label> <input type="text" id="fname"
						name="user_fname" value="${user_fname}">
				</div>
				<div>
					<label for="lname">Last Name</label> <input type="text" id="lname"
						name="user_lname" value="${user_lname}">
				</div>

				<!-- Username full row -->
				<div class="full-width">
					<label for="username">Username</label> <input type="text"
						id="username" name="username" value="${user_name}">
				</div>

				<!-- Role and Gender side by side -->
				<div>
					<label for="user_role">Select Role</label> <select id="user_role"
						name="user_role">
						<option value="">-- Select Role --</option>
						<option value="customer">Customer</option>
						<option value="admin">Admin</option>
					</select>
				</div>
				<div>
					<label>Gender</label>
					<div class="gender-group">
						<label><input type="radio" name="gender" value="Female"
							${gender == 'Female' ? 'checked' : ''}> Female</label> <label><input
							type="radio" name="gender" value="Male"
							${gender == 'Male' ? 'checked' : ''}> Male</label>
					</div>
				</div>

				<!-- DOB and Email side by side -->
				<div>
					<label for="birth">Birth Date</label> <input type="date" id="birth"
						name="dob" value="${dob}">
				</div>
				<div>
					<label for="email">Email</label> <input type="text" id="email"
						name="email" value="${email}">
				</div>

				<!-- Phone and Address side by side -->
				<div>
					<label for="phone">Phone</label> <input type="text" id="phone"
						name="phone" value="${phone}">
				</div>
				<div>
					<label for="address">Address</label> <input type="text"
						id="address" name="address" value="${address}">
				</div>

				<!-- Password and Retype side by side -->
				<div>
					<label for="password">Password</label> <input type="password"
						id="password" name="password">
				</div>
				<div>
					<label for="retypePassword">Retype Password</label> <input
						type="password" id="retypePassword" name="retypePassword">
				</div>

				<!-- File Upload full row -->
				<div class="full-width">
					<label for="user_image">Upload Profile Image</label> <input
						type="file" id="user_image" name="user_image" accept="image/*">
				</div>

				<!-- Submit Button full row -->
				<div class="full-width">
					<input type="submit" value="Register">
				</div>
			</div>
		</form>

		<div class="logged">
			Already have an account? <a
				href="${pageContext.request.contextPath}/login">Login</a>
		</div>
		<div class="logged in">
			Look into the website <a
				href="${pageContext.request.contextPath}/home">Little Angels</a>
		</div>
	</div>

</body>
</html>