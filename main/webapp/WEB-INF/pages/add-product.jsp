<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>${product == null ? 'Add Product' : 'Update Product'}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-product.css" />
</head>
<body>
<header class="header">
    <h1>Little Angels</h1>
</header>

<main class="container">
    <h2>${product == null ? 'Add Product' : 'Update Product'}</h2>
    <form method="post" action="admin-product">
        <input type="hidden" name="action" value="${product == null ? 'add' : 'update'}" />
        <input type="hidden" name="product_id" value="${product != null ? product.productId : ''}" />

        <div class="form-group">
            <label for="product_name">Product Name:</label>
            <input type="text" name="product_name" id="product_name" 
                   value="${product != null ? product.productName : ''}" />
        </div>

        <div class="form-group">
            <label for="category_id">Category:</label>
            <input type="number" name="category_id" id="category_id"
                   value="${product != null ? product.categoryId : ''}" />
        </div>

        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" step="0.01" name="price" id="price"
                   value="${product != null ? product.price : ''}" />
        </div>

        <div class="form-group">
            <label for="product_image">Product Image:</label>
            <input type="text" name="product_image" id="product_image"
                   value="${product != null ? product.productImage : ''}" />
        </div>

        <button type="submit">${product == null ? 'Add Product' : 'Update Product'}</button>
    </form>
</main>
</body>
</html>
