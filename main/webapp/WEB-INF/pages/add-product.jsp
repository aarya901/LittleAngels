<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/add-product.css" />
<title>${product == null ? 'Add Product' : 'Update Product'}</title>
</head>
<body>
	<header class="header">
		<h1>Little Angels</h1>
	</header>

	<main class="container">
		<h2>${product == null ? 'Add Product' : 'Update Product'}</h2>

		<form method="post"
			action="${pageContext.request.contextPath}/add-product"
			enctype="multipart/form-data">
			<input type="hidden" name="action"
				value="${product == null ? 'add' : 'update'}" /> <input
				type="hidden" name="product_id"
				value="${product != null ? product.productId : ''}" />

			<div class="form-group">
				<label for="product_name">Product Name:</label> <input type="text"
					name="product_name" id="product_name"
					value="${product != null ? product.productName : ''}" />
			</div>

			<div class="form-group">
				<label for="price">Price:</label> <input type="number" step="0.01"
					name="price" id="price"
					value="${product != null ? product.price : ''}" />
			</div>

			<div class="form-group">
				<label for="product_image">Product Image:</label> <input type="file"
					name="product_image" id="product_image"
					${product == null ? "required" : ""} />
				<c:if test="${product != null && product.productImage != null}">
					<p>
						Current image: <img
							src="${pageContext.request.contextPath}/${product.productImage}"
							alt="Product Image" style="max-height: 100px;" />
					</p>
				</c:if>
			</div>

			<button type="submit">${product == null ? 'Add Product' : 'Update Product'}</button>
		</form>
		<div class="links">
			<p>
				Go back to Dashboard <a
					href="${pageContext.request.contextPath}/adminn">Dashboard</a>
			</p>
		</div>

	</main>
</body>
</html>
