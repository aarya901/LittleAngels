<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Contact Us - Little Angels</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/contact.css">
</head>
<body>

	<jsp:include page="header.jsp" />

	<div class="contact-container">
		<div class="contact-card">
			<h2>Contact Us</h2>
			<p>Have a question or concern? We'd love to hear from you!</p>

			<form id="contactForm"
				action="${pageContext.request.contextPath}/product" method="post">
				<div class="form-group">
					<label for="name">Full Name</label> <input type="text" name="name"
						id="name" required>
				</div>

				<div class="form-group">
					<label for="email">Email Address</label> <input type="email"
						name="email" id="email" required>
				</div>

				<div class="form-group">
					<label for="subject">Subject</label> <input type="text"
						name="subject" id="subject" required>
				</div>

				<div class="form-group">
					<label for="message">Message</label>
					<textarea name="message" id="message" rows="6" required></textarea>
				</div>

				<button type="submit" class="submit-btn" id="submitBtn">Send
					Message</button>
			</form>
		</div>
	</div>
	<jsp:include page="footer.jsp" />


</body>
</html>
