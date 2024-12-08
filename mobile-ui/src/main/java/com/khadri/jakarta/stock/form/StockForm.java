package com.khadri.jakarta.stock.form;

public class StockForm {

	private int id;
	private String type;
	private int qty;

	public StockForm(int id, String type, int qty) {
		super();
		this.id = id;
		this.type = type;
		this.qty = qty;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public int getQty() {
		return qty;
	}
}
