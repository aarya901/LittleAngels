package com.littleangels.model;

import java.sql.Date;

/**
 * Represents an order combined with product details.
 * 
 * @author Anuhsree Gami
 */
public class OrderProductModel {
	private int orderId;
	private int productId;
	private String productName;
	private Date orderDate;
	private double price;
	private int orderQuantity;
	private String productImage;

	/**
	 * Default constructor.
	 */
	public OrderProductModel() {
	}

	/**
	 * Constructor with all fields.
	 *
	 * @param orderId       the order ID
	 * @param productId     the product ID
	 * @param productName   the name of the product
	 * @param orderDate     the date of the order
	 * @param price         the price of the product
	 * @param orderQuantity the quantity ordered
	 * @param productImage  the product image path
	 */
	public OrderProductModel(int orderId, int productId, String productName, Date orderDate, double price,
			int orderQuantity, String productImage) {
		this.orderId = orderId;
		this.productId = productId;
		this.productName = productName;
		this.orderDate = orderDate;
		this.price = price;
		this.orderQuantity = orderQuantity;
		this.productImage = productImage;
	}

	/**
	 * Gets the order ID.
	 *
	 * @return the order ID
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * Sets the order ID.
	 *
	 * @param orderId the order ID to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * Gets the product ID.
	 *
	 * @return the product ID
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * Sets the product ID.
	 *
	 * @param productId the product ID to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the product name.
	 *
	 * @param productName the product name to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the order date.
	 *
	 * @return the order date
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * Sets the order date.
	 *
	 * @param orderDate the order date to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * Gets the product price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the product price.
	 *
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the order quantity.
	 *
	 * @return the order quantity
	 */
	public int getOrderQuantity() {
		return orderQuantity;
	}

	/**
	 * Sets the order quantity.
	 *
	 * @param orderQuantity the order quantity to set
	 */
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	/**
	 * Gets the product image path.
	 *
	 * @return the product image path
	 */
	public String getProductImage() {
		return productImage;
	}

	/**
	 * Sets the product image path.
	 *
	 * @param productImage the product image path to set
	 */
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
}
