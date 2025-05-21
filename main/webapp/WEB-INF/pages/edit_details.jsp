<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Edit Profile - Little Angels</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/edit_details.css" />
</head>
<body>

	<div class="container">
		<h1>Edit Your Profile</h1>

		<c:if test="${not empty message}">
			<div class="alert success">${message}</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert error">${error}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/edit_details"
			method="post" enctype="multipart/form-data" class="profile-form">

			<div class="profile-image">
				<c:choose>
					<c:when test="${not empty user.imagePath}">
						<img src="${pageContext.request.contextPath}/${user.imagePath}"
							alt="Profile Image" />
					</c:when>
					<c:otherwise>
						<img
							src="${pageContext.request.contextPath}/images/default-avatar.png"
							alt="Default Avatar" />
					</c:otherwise>
				</c:choose>
			</div>

			<label for="image">Upload New Profile Image</label> <input
				type="file" name="image" id="image" accept="image/*" /> <label
				for="firstName">First Name</label> <input type="text" id="firstName"
				name="firstName" value="${user.firstName}" required /> <label
				for="lastName">Last Name</label> <input type="text" id="lastName"
				name="lastName" value="${user.lastName}" required /> <label
				for="email">Email</label> <input type="email" id="email"
				name="email" value="${user.email}" required /> <label for="phone">Phone
				Number</label> <input type="tel" id="phone" name="phone"
				value="${user.phone}" pattern="[0-9\-+ ]*" /> <label for="address">Address</label>
			<textarea id="address" name="address" rows="3">${user.address}</textarea>

			<label>Gender</label>
			<div class="radio-group">
				<label><input type="radio" name="gender" value="Male"
					${user.gender == 'Male' ? 'checked' : ''} /> Male</label> <label><input
					type="radio" name="gender" value="Female"
					${user.gender == 'Female' ? 'checked' : ''} /> Female</label>
			</div>

			<label for="dob">Date of Birth</label> <input type="date" id="dob"
				name="dob" value="${user.dob != null ? user.dob : ''}" />

			<button type="submit" class="btn-primary">Save Changes</button>
		</form>
	</div>
	<div class="back-link">
		<c:choose>
			<c:when test="${user.role == 'admin'}">
				<a href="${pageContext.request.contextPath}/admin-profile">←
					Back to Admin Profile</a>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/user-profile">← Back
					to Profile</a>
			</c:otherwise>
		</c:choose>
	</div>


</body>
</html>
