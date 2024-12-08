package com.khadri.jakarta.stock.form;

import java.sql.Date;

public class MobileForm {
	private String productBrand;
	private Double productPrice;
	private String productModel;
	private Date arrivedDateTime;

	public MobileForm(String productBrand, Double productPrice, String productModel, Date arrivedDateTime) {
		super();
		this.productBrand = productBrand;
		this.productPrice = productPrice;
		this.productModel = productModel;
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

}
