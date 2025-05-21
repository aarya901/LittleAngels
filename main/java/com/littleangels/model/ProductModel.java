package com.littleangels.model;

/**
 * Represents a product with its details.
 * 
 * @author Anushree Gami
 */
public class ProductModel {
	private int productId;
	private String productName;
	private double price;
	private String productImage;

	/**
	 * Default constructor.
	 */
	public ProductModel() {
	}

	/**
	 * Constructor with all fields.
	 * 
	 * @param productIdthe product ID
	 * @param productName  the name of the product
	 * @param price        the price of the product
	 * @param productImage the image path of the product
	 */
	public ProductModel(int productId, String productName, double price, String productImage) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.productImage = productImage;
	}

	/**
	 * Gets the product ID.
	 * 
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * Sets the product ID.
	 * 
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * Gets the product name.
	 * 
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the product name.
	 * 
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the price.
	 * 
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 * 
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the product image path.
	 * 
	 * @return the productImage
	 */
	public String getProductImage() {
		return productImage;
	}

	/**
	 * Sets the product image path.
	 * 
	 * @param productImage the productImage to set
	 */
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
}
