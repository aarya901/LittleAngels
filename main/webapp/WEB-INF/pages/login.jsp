<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BabyProduct - Online Shop</title>
    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login.css" />
</head>
<body>
	  
    <div class="container">
    <div style="color: red; font-weight: bold; margin-bottom: 10px;">
        <c:if test="${not empty error}">
            ${error}
        </c:if>
    </div>
        <div class="main-content">
            <div class="shop-info">
                <h1 class="shop-title">Little Angels - Online Shop</h1>
                <p class="shop-description">We specialize in BabyProducts</p>
                
                <div class="search-box">
                    <input type="text" placeholder="Search Items">
                    <button>Search</button>
                </div>
            </div>

            <div class="promo-image">
                <img src="images/images.jpeg" alt="promo-image" />
            </div>
            
            <div class="login-section">
                <div class="login-form">
                    <h2>Login Form</h2>
                    <div id="success-message" class="success-message hidden">Login successful.</div>
                    
                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" id="user_name" name="user_name">
                            <div id="username-error" class="error hidden">Please enter your username</div>
                        </div>
                        
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" id="user_password" name="user_password">
                            <div id="password-error" class="error hidden">Please enter your password</div>
                        </div>
                        
                        <div class="form-group">
						    <label for="login-as">Login As</label>
						    <select id="login-as" name="user_role"> <!-- Changed from login-as to user_role -->
						        <option value="customer">CUSTOMER</option>
						        <option value="admin">ADMIN</option>
						    </select>
						</div>
						                        
                        <a href="forgotPassword.jsp" class="forgot-password">Forgot Password?</a>
                        <button type="submit" class="login-btn">Login</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="login.js"></script>
</body>
</html>

 
    
