<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Admin - Stock Products</title>
  <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/admin-product.css" />
</head>
<body>
<header class="header">
  <h1>Little Angels</h1>
</header>

<main class="container">
  <h2>Stock Products</h2>

  <!-- Add Product Button -->
  <div style="margin-bottom: 15px;">
  </div>

  <table>
    <thead>
      <tr>
        <th>Image</th>
        <th>ProductId</th>
        <th>Name</th>
        <th>Category</th>
        <th>Price</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="product" items="${productList}">
        <tr>
          <td>
            <img src="${pageContext.request.contextPath}/product-images/${product.productImage}" 
                 alt="img" width="80" height="80" />
          </td>
          <td>${product.productId}</td>
          <td>${product.productName}</td>
          <td>${product.categoryId}</td>
          <td>${product.price}</td>
          <td>
            <!-- Update -->
            <form method="get" action="admin-product" style="display:inline;">
              <input type="hidden" name="action" value="edit" />
              <input type="hidden" name="product_id" value="${product.productId}" />
              <button class="update-btn" type="submit">Update</button>
            </form>
            <!-- Remove -->
            <form method="get" action="admin-product" style="display:inline;" 
                  onsubmit="return confirm('Are you sure you want to delete this product?');">
              <input type="hidden" name="action" value="delete" />
              <input type="hidden" name="product_id" value="${product.productId}" />
              <button class="remove-btn" type="submit">Remove</button>
            </form>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</main>
</body>
</html>
