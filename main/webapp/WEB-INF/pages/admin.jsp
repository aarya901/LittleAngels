<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin Dashboard</title>
  <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin.css" />
</head>
<body>
  <div class="container">
    <aside class="sidebar">
      <div class="avatar">👤</div>
      <ul class="sidebar-links">
        <li>Dashboard</li>
        <li>Home</li>
        <li>Transaction</li>
        <li>Sales</li>
        <li>Products</li>
        <li>Customer Details</li>
      </ul>
    </aside>

    <main class="dashboard">
      <h2>Admin Dashboard</h2>

      <div class="dashboard-cards">
        <div class="card">New Order</div>
        <div class="card">Order Fulfillment</div>
        <div class="card">Pending Order</div>
        <div class="card">Total Income</div>
      </div>

      <div class="middle-section">
        <div class="sales-graph">Sales Graph</div>
        <div class="top-products">
          <h3>Top Selling Products</h3>
          <ul>
            <li>📷 Product Name</li>
            <li>📷 Product Name</li>
            <li>📷 Product Name</li>
          </ul>
        </div>
      </div>

      <div class="bottom-section">
        <div class="order-activity">
          <h3>Order Activity</h3>
          <ul>
            <li>🚚 Delivered</li>
            <li>📄 Invoice</li>
            <li>📦 Received</li>
          </ul>
        </div>
        <div class="image-box">Image</div>
      </div>
    </main>
  </div>
</body>
</html>
