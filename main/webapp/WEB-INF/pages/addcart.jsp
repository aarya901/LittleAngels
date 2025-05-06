<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>My Cart</title>
    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/addcart.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
	<jsp:include page="header.jsp" />
  </head>
  <body>
    <body>
      <div class="header">
        <h2>Cart Items</h2>
      </div>

      <div class="cart_table">
        <table>
          <thead>
            <tr>
              <th>Picture</th>
              <th>Products</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Add</th>
              <th>Remove</th>
              <th>Amount</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><img src="images/cart_image1.jpg" alt="Product 1" /></td>
              <td>Comfortable and highly absorbent baby diaper.</td>
              <td>800</td>
              <td><input type="number" value="1" /><button>Update</button></td>
              <td><button class="add">+</button></td>
              <td><button class="remove">-</button></td>
              <td>800</td>
            </tr>
            <tr>
              <td><img src="images/cart_image2.png" alt="Product 2" /></td>
              <td>Hydrates and protects your baby's soft skin</td>
              <td>400</td>
              <td><input type="number" value="1" /><button>Update</button></td>
              <td><button class="add">+</button></td>
              <td><button class="remove">-</button></td>
              <td>400</td>
            </tr>
            <tr>
              <td><img src="images/cart_image3.jpg" alt="Product 3" /></td>
              <td>Gentle shampoo for your baby's hair</td>
              <td>200</td>
              <td><input type="number" value="1" /><button>Update</button></td>
              <td><button class="add">+</button></td>
              <td><button class="remove">-</button></td>
              <td>200</td>
            </tr>
          </tbody>
        </table>

        <div class="total_Amount">
          <span>Total Amount</span>
          <span>1400</span>
        </div>

        <div class="cart_button">
          <button class="cancel">Cancel</button>
          <button class="paynow">Pay Now</button>
        </div>
      </div>
      <jsp:include page="footer.jsp" />
    </body>
    