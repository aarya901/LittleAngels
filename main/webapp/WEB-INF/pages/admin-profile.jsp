<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Profile</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin-profile.css?v=${System.currentTimeMillis()}">

</head>
<body>
	<div class="profile-container">
		<!-- Profile Card -->
		<div class="profile-card">
			<div class="profile-image">
				<c:choose>
					<c:when test="${not empty user.imagePath}">
						<img src="${pageContext.request.contextPath}/${user.imagePath}"
							alt="Profile Image">
					</c:when>
					<c:otherwise>
						<img src="${pageContext.request.contextPath}/images/avatar.png"
							alt="Default Avatar">
					</c:otherwise>
				</c:choose>
			</div>
			<div class="user-info">
				<h2>${user.firstName}${user.lastName}</h2>
				<p class="email">${user.email}</p>
				<p class="phone">${user.phone}</p>
			</div>
		</div>
		<div class=action-buttons>
			<form action="/edit_details" method="get">
				<a href="${pageContext.request.contextPath}/edit_details">Edit
					Details</a>
			</form>
		</div>
		<!-- Details Card -->
		<div class="details-card">
			<h3>Profile Details</h3>
			<table>
				<tr>
					<th>Full Name</th>
					<td>${user.firstName}${user.lastName}</td>
				</tr>
				<tr>
					<th>Email</th>
					<td>${user.email}</td>
				</tr>
				<tr>
					<th>Mobile</th>
					<td>${user.phone}</td>
				</tr>
				<tr>
					<th>Username</th>
					<td>${user.userName}</td>
				</tr>
				<tr>
					<th>Address</th>
					<td><c:choose>
							<c:when test="${not empty user.address}">
				                ${user.address}
				            </c:when>
							<c:otherwise>
				                Not provided
				            </c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<th>Gender</th>
					<td><c:choose>
							<c:when test="${not empty user.gender}">
			                ${user.gender}
			            </c:when>
							<c:otherwise>
			                Not specified
			            </c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<th>Date of Birth</th>
					<td><c:choose>
							<c:when test="${not empty user.dob}">
			                ${user.dob}
			            </c:when>
							<c:otherwise>
			                Not provided
			            </c:otherwise>
						</c:choose></td>
				</tr>

			</table>
		</div>
	</div>
	<div class="links">
		<p>
			Go back to Dashboard <a
				href="${pageContext.request.contextPath}/adminn">Dashboard</a>
		</p>
	</div>
</body>
</html>
