<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/register.css" />
</head>
<body>
<jsp:include page="header.jsp" />
<div class="form-container">
    <h2>User Registration Form</h2>

    <!-- Error Message Display -->
    <c:if test="${not empty error}">
        <div style="color: red; font-weight: bold; margin-bottom: 10px;">${error}</div>
    </c:if>

    <form action="register" method="post" enctype="multipart/form-data">
        <label for="fname">First Name:</label>
        <input type="text" id="fname" name="user_fname" value="${user_fname}">

        <label for="lname">Last Name:</label>
        <input type="text" id="lname" name="user_lname" value="${user_lname}">
        
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${user_name}" >
		
		<div class="form-group role-select">
		    <label for="user_role">Select Role:</label>
		    <select id="user_role" name="user_role" class="form-control">
		        <option value="">-- Select Role --</option>
		        <option value="customer">Customer</option>
		        <option value="admin">Admin</option>
		    </select>
		</div>

        <label>Gender:</label>
        <div class="gender-group">
            <label><input type="radio" name="gender" value="Female" ${gender == 'Female' ? 'checked' : ''} > Female</label>
            <label><input type="radio" name="gender" value="Male" ${gender == 'Male' ? 'checked' : ''}> Male</label>
        </div>

        <label for="birth">Birth Date:</label>
        <input type="date" id="birth" name="dob" value="${dob}">

        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="${email}">

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="${phone}" >

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${address}">
        
        <!-- Password -->
            <div class="form-group mb-3">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>

            <!-- Retype Password -->
            <div class="form-group mb-3">
                <label for="retypePassword">Retype Password</label>
                <input type="password" class="form-control" id="retypePassword" name="retypePassword" >
            </div>
			
			<div class="form-group image-upload">
			    <label for="user_image">Upload Profile Image:</label>
			    <input type="file" id="user_image" name="user_image" accept="image/*" class="form-control">
			</div>
        <input type="submit" value="Register">
    </form>
</div>

</body>
</html>