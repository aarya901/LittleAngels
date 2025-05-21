package com.littleangels.model;

/**
 * Combines a cart item with its associated product details. Used to represent
 * detailed information about a cart entry, including product name, price, and
 * image along with cart specifics.
 * 
 * Author: Priya Soni
 */
public class CartProductModel {
	// Cart fields
	private int cartItemId;
	private int userId;
	private int productId;
	private int cartQuantity;

	// Product fields
	private String productName; // Name of the product
	private double price; // Price of the product
	private String productImage; // Image path or URL for the product

	/**
	 * Default constructor.
	 */
	public CartProductModel() {
	}

	/**
	 * Parameterized constructor to create a CartProductModel object.
	 * 
	 * @param cartItemId   Unique cart item ID
	 * @param userId       User ID associated with the cart
	 * @param productId    Product ID added to cart
	 * 
	 * @param cartQuantity Quantity of product in cart
	 * @param productName  Name of the product
	 * @param price        Price of the product
	 * @param productImage Image path or URL of the product
	 */
	public CartProductModel(int cartItemId, int userId, int productId, int cartQuantity, String productName,
			double price, String productImage) {
		this.cartItemId = cartItemId;
		this.userId = userId;
		this.productId = productId;
		this.cartQuantity = cartQuantity;
		this.productName = productName;
		this.price = price;
		this.productImage = productImage;
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	// Getter and setter for userId
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	// Getter and setter for productId
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	// Getter and setter for cartQuantity
	public int getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	// Getter and setter for productName
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	// Getter and setter for price
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// Getter and setter for productImage
	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
}
