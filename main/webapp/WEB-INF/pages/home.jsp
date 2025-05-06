<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>homepage</title>
    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/home.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
	
    
</head>
<body>
	<jsp:include page="header.jsp" />
   <!-- Image Slider using internal JavaScript -->
<section class="slider">
    <div id="img">
        <img src="images/baby_img1.jpg" alt="Baby Product">
    </div>
</section>

<!--Using internal javascript-->  
<script> 
    var image = ['images/baby_img1.jpg', 'images/baby_img2.jpg', 'images/baby_img3.jpg']; 
    var x = 0;
    var imgs = document.getElementById('img');
    setInterval(slider, 2000); // Auto play

    function slider() {
        if (x < image.length) {
            x = x + 1;
        } else {
            x = 1;
        }
        imgs.innerHTML = "<img src='" + image[x - 1] + "' alt='Baby Product'>";
    }
</script>



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
        <button class="cta-button">Shop Now</button>
    </section>

    <jsp:include page="footer.jsp" />
</body>
</html>