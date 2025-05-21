<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>About Us</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/about.css?v=1.0" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<section class="about-section">
		<h1 class="heading">Meet our team members</h1>
		<p class="subheading">
			We focus on the details of everything we do. All to help businesses
			around the world<br /> focus on what’s most important to them.
		</p>

		<div class="team-grid">
			<!-- Team Member Cards -->
			<div class="team-card">
				<img src="${pageContext.request.contextPath}/images/priya.png"
					alt="Priya Soni" />
				<div class="info">
					<h3>Priya Soni</h3>
					<p class="role">CEO</p>
					<p>Enjoys adventurous travel, software developer and seeks new
						cultures and offbeat destinations</p>
				</div>
			</div>

			<div class="team-card">
				<img src="${pageContext.request.contextPath}/images/rachina.png"
					alt="Rachina Gosai" />
				<div class="info">
					<h3>Rachina Gosai</h3>
					<p class="role">Founder</p>
					<p>Pop music lover, software developer, seeks joy and exciting
						pop concerts</p>
				</div>
			</div>

			<div class="team-card">
				<img src="${pageContext.request.contextPath}/images/aarya.png"
					alt="Aarya Gautam" />
				<div class="info">
					<h3>Aarya Gautam</h3>
					<p class="role">CTO</p>
					<p>Bookworm, creative software developer with precision</p>
				</div>
			</div>

			<div class="team-card">
				<img src="${pageContext.request.contextPath}/images/anushree.png"
					alt="Anushree Gami" />
				<div class="info">
					<h3>Anushree Gami</h3>
					<p class="role">Product Designer</p>
					<p>Creative painter, capturing beauty with imaginative artwork
						and software developer</p>
				</div>
			</div>

			<div class="team-card">
				<img src="${pageContext.request.contextPath}/images/aryan.png"
					alt="Aryan Pudasaini" />
				<div class="info">
					<h3>Aryan Pudasaini</h3>
					<p class="role">Software Engineer</p>
					<p>Passionate coder, loves solving complex problems and
						exploring new technologies</p>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="footer.jsp" />
</body>
</html>
