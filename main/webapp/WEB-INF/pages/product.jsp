<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
<title>Products</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/product.css" />
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="listProduct">
		<c:forEach var="product" items="${productList}">
			<div class="item">
				<a href="${contextPath}"><img
					src="${pageContext.request.contextPath}/${product.productImage}"
					alt="Logo"></a>
				<h2>${product.productName}
					<br> <br>
				</h2>
				<h2>
					Rs ${product.price} <br> <br>
				</h2>


				<form method="post">
					<label for="quantity_${product.productId}">Quantity:</label> <input
						type="number" name="quantity" id="quantity_${product.productId}"
						min="1" max="10" value="1" /> <input type="hidden"
						name="product_id" value="${product.productId}" />

					<div class="buttons">
						<button type="submit" name="action" value="addToCart"
							class="btn btn-danger">Add to Cart</button>
						<button type="submit" name="action" value="buyNow" class="buyNow">Buy
							Now</button>
					</div>
				</form>

			</div>
		</c:forEach>
	</div>

</body>
</html>
