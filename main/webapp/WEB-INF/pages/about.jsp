<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>About Us</title>
  <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/about.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
	<jsp:include page="header.jsp" />
  	<section class="about-section">
    <span class="tag">🌐 Our Team</span>
    <h1 class="heading">Meet our team members</h1>
    <p class="subheading">
      We focus on the details of everything we do. All to help businesses around the world<br/>
      focus on what’s most important to them.
    </p>

    <div class="team-grid">
      <!-- Team Member Cards -->
      <div class="team-card">
        <img src="images/team1.jpg" alt="Priya Soni" />
        <div class="info">
          <h3>Priya Soni</h3>
          <p class="role">CEO</p>
          <p>Enjoys adventurous travel,software developer and seeks new cultures and offbeat destinations</p>
        </div>
      </div>

      <div class="team-card">
        <img src="images/team2.jpg" alt="Rachina Gosai" />
        <div class="info">
          <h3>Rachina Gosai</h3>
          <p class="role">Founder</p>
          <p>Pop music lover, software developer, seeks joy and exciting pop concerts</p>
        </div>
      </div>

      <div class="team-card">
        <img src="images/team3.jpg" alt="Aarya Gautam" />
        <div class="info">
          <h3>Aarya Gautam</h3>
          <p class="role">CTO</p>
          <p>Bookworm, creative software developer with precision</p>
        </div>
      </div>

      <div class="team-card">
        <img src="images/team4.jpg" alt="Anushree Gami" />
        <div class="info">
          <h3>Anushree Gami</h3>
          <p class="role">Product Designer</p>
          <p>Creative painter, capturing beauty with imaginative artwork and software developer</p>
        </div>
      </div>
    </div>
  </section>
  <jsp:include page="footer.jsp" />
</body>
</html>
