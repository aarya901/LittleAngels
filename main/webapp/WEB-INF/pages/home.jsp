<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>homepage</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/home.css" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- Image Slider using internal JavaScript -->
	<section class="slider">
		<div id="img">
			<img src="images/baby_img1.jpg" alt="Baby Product">
		</div>
	</section>



	<!-- Image Slider -->
	<!--<section class="slider">
        <img src="baby_img1.jpg" class="slide active">
        <img src="baby_img2.jpg" class="slide">
        <img src="baby_img3.jpg" class="slide">
    </section> -->

	<!-- Home Content -->
	<section class="home">
		<h1>Welcome to Little Angels</h1>
		<p>Find the best and safest baby products for your little ones</p>
		<a href="${pageContext.request.contextPath}/product"
			class="cta-button">Shop Now</a> <a
			href="${pageContext.request.contextPath}/contact" class="cta-button">Contact
			US</a>
	</section>

	<jsp:include page="footer.jsp" />
</body>
</html>