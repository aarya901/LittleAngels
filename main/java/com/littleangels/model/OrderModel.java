package com.littleangels.model;

/**
 * Represents an order item with details such as order ID, product ID, and
 * quantity ordered.
 * 
 * <p>
 * This model is used to track each product within an order along with its
 * quantity.
 * </p>
 * 
 * @author Aryan Pudasaini
 */
public class OrderModel {
	private int orderId;
	private int productId;
	private int orderQuantity;

	/**
	 * Default no-argument constructor.
	 */
	public OrderModel() {
	}

	/**
	 * Constructs an OrderModel with specified order ID, product ID, and order
	 * quantity.
	 * 
	 * @param orderId       the unique identifier for the order
	 * @param productId     the unique identifier for the product
	 * @param orderQuantity the quantity of the product ordered
	 */
	public OrderModel(int orderId, int productId, int orderQuantity) {
		this.orderId = orderId;
		this.productId = productId;
		this.orderQuantity = orderQuantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
}
