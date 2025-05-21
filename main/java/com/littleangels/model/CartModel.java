package com.littleangels.model;

/**
 * Represents a cart item in the shopping cart. Contains information about the
 * cart item ID, associated user ID, product ID, and quantity added to the cart.
 * 
 * Author: Rachina Gosai
 */
public class CartModel {
	private int cartItemId;
	private int userId;
	private int productId;
	private int cartQuantity;

	/**
	 * Default constructor.
	 */
	public CartModel() {
	}

	/**
	 * Parameterized constructor to create a CartModel object.
	 * 
	 * @param cartItemId   unique ID of the cart item
	 * @param userId       user ID owning the cart item
	 * @param productId    product ID added to the cart
	 * @param cartQuantity quantity of the product
	 */
	public CartModel(int cartItemId, int userId, int productId, int cartQuantity) {
		this.cartItemId = cartItemId;
		this.userId = userId;
		this.productId = productId;
		this.cartQuantity = cartQuantity;
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}
}
