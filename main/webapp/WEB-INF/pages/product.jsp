  <html lang="en">
<head>
    <title>product</title>
  <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/home.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
	
	<jsp:include page="header.jsp" />
    
    <style>        
        .listProduct .item img{
            width: 90%;
        
        }
        .listProduct{
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            margin-top: 30px;
        }
        .listProduct .item{
            background-color: #ffe9e3;
            padding: 20px;
            border-radius: 20px;
        }
        .listProduct .item h2{
            font-weight: 500;
            font-size: large;
        }
        .listProduct .item .price{
            font-size: small;
            text-decoration: line-through;
        }
        .listProduct .item button{
            background-color: #353432;
            color: #eee;
            padding: 5px 10px;
            border-radius: 20px;
            margin-top: 10px;
            border: none;
            cursor: pointer;
        }
        
        .listProduct .item img{
            width: 90%;
            
        }
        .listProduct{
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
        }
        .listProduct .item{
            background-color: #eeeee6;
            padding: 20px;
            border-radius: 20px;
        }
        .listProduct .item h2{
            font-weight: 500;
            font-size: large;
        }
        .listProduct .item .price{
            font-size: small;
        }
        .listProduct .item button{
            background-color: #353432;
            color: #eee;
            padding: 5px 10px;
            border-radius: 20px;
            margin-top: 10px;
            border: none;
            cursor: pointer;
        }
            
        </style>
</head>
<body>
    <div class="listProduct"> <!--This is the starting div for first row-->

        <div class="item">                                      
            <img src="images\oil_image4.jpg" alt="Baby oil">
            <h2>Johnson's Baby Oil <br><br></h2> 
            <p>Protects your baby's soft skin. Enriched with Vitamin E - 300 ml <br><br></p> 
            <div class="price">Price:400</div>
            <div class="Dprice">Discounted Price:350</div>
            <label for="quantity2">Quantity:</label>
            <input type="number" id="quantity2" min="1" max="10" value="1" />
            <!--<button class="addCart"> -->
            <!--buy now--> 
            <div class="buttons">
                <button class="addCart">Add to Cart</button>
                <button class="buyNow">Buy Now</button>
              </div>
        </div>
            
        
    
        <!--This div is for first row second column-->
        <div class="item">                                      
            <img src="images\toothpaste_image5.jpg" alt="Baby toothpaste">
            <h2>Milk Teeth <br><br></h2> 
            <p>Protects your baby's Teeth.Now in mint flavour - 50 ml<br><br></p> 
            <div class="price">Price:300</div>
            <div class="Dprice">Discounted Price:250</div>
            <label for="quantity2">Quantity:</label>
            <input type="number" id="quantity2" min="1" max="10" value="1" />
            <!--<button class="addCart"> -->
            <!--buy now--> 
            <div class="buttons">
                <button class="addCart">Add to Cart</button>
                <button class="buyNow">Buy Now</button>
              </div>
        </div>
    
    <!--This div is for first row third column-->
    <div class="item">                                      
        <img src="images\moisturizer2.jpg" alt="Baby Moisturizer"> moist.png
        <h2>Eco-Store Baby Moisturizer <br><br></h2> 
        <p>Plant and Mineral based. Hydrates and protects your baby's soft skin. Lavender scented - 200ml <br><br></p> 
        <div class="price">Price:600</div>
        <div class="Dprice">Discounted Price:550</div>
        <label for="quantity2">Quantity:</label>
        <input type="number" id="quantity2" min="1" max="10" value="1" />
        <!--<button class="addCart"> -->
        <!--buy now--> 
        <div class="buttons">
            <button class="addCart">Add to Cart</button>
            <button class="buyNow">Buy Now</button>
          </div>
    </div>
    
    <!--This div is for first row fourth column-->
    <div class="item">                                      
        <img src="images\moist.png" alt="Baby Moisturizer">
        <h2>Gentle Baby Moisturizer <br><br></h2> 
        <p>Hydrates and protects your baby's soft skin. <br><br></p> 
        <div class="price">Price:400</div>
        <div class="Dprice">Discounted Price:350</div>
        <label for="quantity2">Quantity:</label>
        <input type="number" id="quantity2" min="1" max="10" value="1" />
        <!--<button class="addCart"> -->
        <!--buy now--> 
        <div class="buttons">
            <button class="addCart">Add to Cart</button>
            <button class="buyNow">Buy Now</button>
          </div>
    </div>
</div>
<!--This is the end div of first row-->


<div class="listProduct"> <!--This is the starting div for second row-->
    
    <!--This div is for second row first column-->
    <div class="item">                                      
        <img src="images\shampoo2.jpg" alt="Baby shampoo">
        <h2>Little Ones Shampoo <br><br></h2> 
        <p>Protects your baby's soft hair. <br><br></p> 
        <div class="price">Price:380</div>
        <div class="Dprice">Discounted Price:350</div>
        <label for="quantity2">Quantity:</label>
        <input type="number" id="quantity2" min="1" max="10" value="1" />
        <!--<button class="addCart"> -->
        <!--buy now--> 
        <div class="buttons">
            <button class="addCart">Add to Cart</button>
            <button class="buyNow">Buy Now</button>
          </div>
    </div>

    <!--This div is for second row second column-->
    <div class="item">                                      
        <img src="images\toothpaste2.jpg" alt="Baby toothpaste">
        <h2>TE PE toothpaste<br><br></h2> 
        <p>Protects your baby's TEETH AND GUM. <br><br></p> 
        <div class="price">Price:330</div>
        <div class="Dprice">Discounted Price:300</div>
        <label for="quantity2">Quantity:</label>
        <input type="number" id="quantity2" min="1" max="10" value="1" />
        <!--<button class="addCart"> -->
        <!--buy now--> 
        <div class="buttons">
            <button class="addCart">Add to Cart</button>
            <button class="buyNow">Buy Now</button>
          </div>
    </div>

    <!--This div is for second row third column-->
    <div class="item">                                      
        <img src="images\toothpaste3.jpg" alt="Baby Toothpaste">
        <h2>KODOMO Toothpaste <br><br></h2> 
        <p>Hydrates and protects your baby's soft skin. <br><br></p> 
        <div class="price">Price:400</div>
        <div class="Dprice">Discounted Price:350</div>
        <label for="quantity2">Quantity:</label>
        <input type="number" id="quantity2" min="1" max="10" value="1" />
        <!--<button class="addCart"> -->
        <!--buy now--> 
        <div class="buttons">
            <button class="addCart">Add to Cart</button>
            <button class="buyNow">Buy Now</button>
          </div>
    </div>
    
    <!--This div is for second row fourth column-->
    <div class="item">                                      
        <img src="images\diaper2.jpeg" alt="Baby diaper">
        <h2>Gentle Baby diaper <br><br></h2> 
        <p>Hydrates and protects your baby's soft butt. <br><br></p> 
        <div class="price">Price:450</div>
        <div class="Dprice">Discounted Price:370</div>
        <label for="quantity2">Quantity:</label>
        <input type="number" id="quantity2" min="1" max="10" value="1" />
        <!--<button class="addCart"> -->
        <!--buy now--> 
        <div class="buttons">
            <button class="addCart">Add to Cart</button>
            <button class="buyNow">Buy Now</button>
          </div>
    </div>

    <div class="item">                                      
        <img src="images\shampoo3.jpg" alt="Baby shampoo">
        <h2>Attitude baby Shampoo <br><br></h2> 
        <p>Protects your baby's soft hair. <br><br></p> 
        <div class="price">Price:460</div>
        <div class="Dprice">Discounted Price:400</div>
        <label for="quantity2">Quantity:</label>
        <input type="number" id="quantity2" min="1" max="10" value="1" />
        <!--<button class="addCart"> -->
        <!--buy now--> 
        <div class="buttons">
            <button class="addCart">Add to Cart</button>
            <button class="buyNow">Buy Now</button>
          </div>
    </div>



    <!--This div is for third row second column-->
    <div class="item">                                      
        <img src="images\shampoo1.jpg" alt="Baby Moisturizer">
        <h2>Johmsom's baby shampoo<br><br></h2> 
        <p>Cleans and protects your baby's soft hair. <br><br></p> 
        <div class="price">Price:360</div>
        <div class="Dprice">Discounted Price:320</div>
        <label for="quantity2">Quantity:</label>
        <input type="number" id="quantity2" min="1" max="10" value="1" />
        <!--<button class="addCart"> -->
        <!--buy now--> 
        <div class="buttons">
            <button class="addCart">Add to Cart</button>
            <button class="buyNow">Buy Now</button>
          </div>
    </div>

    <!--This div is for third row third column-->
    <div class="item">                                      
        <img src="images\toothpaste2.jpg" alt="Baby toothpaste">
        <h2>TE PE toothpaste<br><br></h2> 
        <p>Protects your baby's TEETH AND GUM. <br><br></p> 
        <div class="price">Price:330</div>
        <div class="Dprice">Discounted Price:300</div>
        <label for="quantity2">Quantity:</label>
        <input type="number" id="quantity2" min="1" max="10" value="1" />
        <!--<button class="addCart"> -->
        <!--buy now--> 
        <div class="buttons">
            <button class="addCart">Add to Cart</button>
            <button class="buyNow">Buy Now</button>
          </div>
	</div>

    <!--This div is for third row fourth column-->
    <div class="item">                                      
        <img src="images\diaper1.jpeg" alt="Baby Diaper">
        <h2>MumsButt <br><br></h2> 
        <p>Protects your baby's soft butt. Prevents rashes - 15 pieces <br><br></p> 
        <div class="price">Price:750</div>
        <div class="Dprice">Discounted Price:650</div>
        <label for="quantity2">Quantity:</label>
        <input type="number" id="quantity2" min="1" max="10" value="1" />
        <!--<button class="addCart"> -->
        <!--buy now--> 
        <div class="buttons">
            <button class="addCart">Add to Cart</button>
            <button class="buyNow">Buy Now</button>
          </div>
       
    </div>
</div>
<!--This is the end div of second row-->


   
    <!--Using internal javascript-->
    <script>
        function message(){
            alert("Product added to cart");
        }
    </script>
</body>
</html>