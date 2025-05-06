<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>My Orders</title>
  <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/my-orders.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
	
    
</head>
<body>
	<jsp:include page="header.jsp" />

<div class="orders-container">
  <h2>My Orders</h2>

  <div class="orders-table">
    <div class="order-header">
      <div>Order ID</div>
      <div>Date</div>
      <div>Status</div>
      <div>Total</div>
      <div>Action</div>
    </div>

    <!-- Repeat this order row for multiple orders -->
    <div class="order-row">
      <div>#1001</div>
      <div>April 12, 2025</div>
      <div><span class="status delivered">Delivered</span></div>
      <div>$89.99</div>
      <div><button class="view-btn">View</button></div>
    </div>

    <div class="order-row">
      <div>#1002</div>
      <div>April 10, 2025</div>
      <div><span class="status pending">Pending</span></div>
      <div>$45.50</div>
      <div><button class="view-btn">View</button></div>
    </div>

    <div class="order-row">
      <div>#1003</div>
      <div>April 8, 2025</div>
      <div><span class="status shipped">Shipped</span></div>
      <div>$129.00</div>
      <div><button class="view-btn">View</button></div>
    </div>

  </div>
</div>

</body>
</html>
