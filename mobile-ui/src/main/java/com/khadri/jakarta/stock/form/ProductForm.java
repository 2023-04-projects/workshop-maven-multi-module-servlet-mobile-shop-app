package com.khadri.jakarta.stock.form;

import java.sql.Date;

public class ProductForm {
	private String productBrand;
	private Double productPrice;
	private String productModel;
	private String productType;
	private Date arrivedDateTime;

	public ProductForm(String productBrand, Double productPrice, String productModel,String productType, Date arrivedDateTime) {
		super();
		this.productBrand = productBrand;
		this.productPrice = productPrice;
		this.productModel = productModel;
		this.productType = productType;
		this.arrivedDateTime = arrivedDateTime;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public String getProductModel() {
		return productModel;
	}

	public Date getArrivedDateTime() {
		return arrivedDateTime;
	}
	public String getProductType() {
		return productType;
	}

}
