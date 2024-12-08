package com.khadri.jakarta.product.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.khadri.jakarta.product.form.ProductForm;

public class ProductDao {
	
	private Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/2024_batch_workshop_servlet_mobile_shop_app","root", "root");
	}

	public int insertMobileData(ProductForm form) {
		System.out.println("ProductDao insertProductData(-)");
		int result = 0;
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement("INSERT INTO product(Name) VALUES(?)")) {

			pstmt.setString(1, form.getName());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}
		return result;
	}

	public List<ProductForm> viewProductData(String productId) {
		System.out.println("ProductDao viewProductData(-)");
		List<ProductForm> listOfData = new ArrayList<>();

		try (Connection con = getConnection();
				Statement stmt = con.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT * FROM product WHERE Id='" + productId + "'")) {

			if (resultSet.next()) {
				ProductForm form = new ProductForm();
				form.setId(resultSet.getInt(1));
				form.setName(resultSet.getString(2));
				listOfData.add(form);
			}

		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}
		return listOfData;
	}

	public List<ProductForm> selectProductData() {
		System.out.println("ProductDao selectProductData(-)");
		List<ProductForm> listOfData = new ArrayList<>();

		try (Connection con = getConnection();
				Statement stmt = con.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT * FROM product")) {

			while (resultSet.next()) {
				ProductForm form = new ProductForm();
				form.setId(resultSet.getInt(1));
				form.setName(resultSet.getString(2));
				listOfData.add(form);
			}

		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}
		return listOfData;
	}

	public List<String> selectProductNames() {
		System.out.println("ProductDao selectProductNames(-)");
		List<String> listOfProductNames = new ArrayList<>();

		try (Connection con = getConnection();
				Statement stmt = con.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT Name FROM product")) {

			while (resultSet.next()) {
				listOfProductNames.add(resultSet.getString(1));
			}

		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}
		return listOfProductNames;
	}

	public int updateProduct(ProductForm form) {
		System.out.println("ProductDao updateProduct(-)");
		int result = 0;

		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement("UPDATE product SET Name=? WHERE ID=?")) {

			pstmt.setString(1, form.getName());
			pstmt.setInt(2, form.getId());

			result = pstmt.executeUpdate();
			System.out.println(result + " record(s) modified successfully!");

		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}
		return result;
	}

	public int deleteProduct(ProductForm form) {
		System.out.println("ProductDao deleteProduct(-)");
		int result = 0;
		try {
			Connection con = getConnection();

			PreparedStatement pstmt = con.prepareStatement("delete from product where ID=?");
			pstmt.setInt(1, form.getId());

			result = pstmt.executeUpdate();
			System.out.println(result + "Delete record sucessfully!!!");

		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return result;

	}
}
