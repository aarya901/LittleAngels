<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin Dashboard</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css" />
</head>
<body>
  <h1>Admin Dashboard</h1>
  <div class="dashboard-container">
    <aside class="sidebar">
      <div class="icon-circle">❤️</div>
      <ul>
        <li>Transactions</li>
        <li>Sales</li>
        <li>Products</li>
        <li>Customer Details</li>
        <li>Logout</li>
        <li><a href="${pageContext.request.contextPath}/add-product"><button>Add Product</button></a></li>
        <li><a href="${pageContext.request.contextPath}/admin-product">Product View</a></li>
      </ul>
    </aside>

    <main class="main-dashboard">
      <section class="top-cards">
        <div class="card red">New Orders<br>🛒</div>
        <div class="card purple">Visitors<br>👤</div>
        <div class="card blue">Total Income $<br>💰</div>
        <div class="card indigo">Total Sales<br>📈</div>
      </section>

      <section class="bottom-section">
        <div class="recent-orders">
          <h3>Recent Orders</h3>
          <table>
            <tr><th>User</th><th>Order Date</th><th>Status</th></tr>
          </table>
        </div>

        <div class="todo-list">
          <h3>To-do Lists</h3>
          <ul>
            <li>Important Task 1</li>
            <li>Regular Task 1</li>
            <li>Important Task 2</li>
            <li>Regular Task 2</li>
            <li>Important Task 3</li>
            <li>Regular Task 3</li>
            <li>Important Task 4</li>
            <li>Regular Task 4</li>
          </ul>
        </div>
      </section>
    </main>
  </div>
</body>
</html>
