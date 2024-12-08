package com.khadri.jakarta.stock.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.khadri.jakarta.stock.form.BackCoverForm;
import com.khadri.jakarta.stock.form.ChargerForm;
import com.khadri.jakarta.stock.form.HeadSetForm;
import com.khadri.jakarta.stock.form.MobileForm;
import com.khadri.jakarta.stock.form.PowerBankForm;
import com.khadri.jakarta.stock.form.ProductForm;
import com.khadri.jakarta.stock.form.StockForm;

public class StockDao {
	private Connection con;
	private PreparedStatement pstmt;
	

	
	private Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/2024_batch_workshop_servlet_mobile_shop_app", "root", "root");
	}

	public StockForm selectStockTypeRecord(String type) {
		System.out.println("Entered into StockDao selectStockTypeRecord(-)");
		StockForm form = null;
		Statement stmt = null;
		int result = 0;
		try {
			con = getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM stock WHERE type = '" + type + "'");
			if (rs.next()) {
				form = new StockForm(result, rs.getString("type"), rs.getInt("qty"));
			}
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return form;

	}

	public void updateStockQty(String type, int qty) throws Exception {
		System.out.println("Entered into StockDao updateStockQty(-,-)");
		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE stock SET qty = ? WHERE type = ?");
			pstmt.setInt(1, qty);
			pstmt.setString(2, type);
			pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertIntoStock(String type, int qty) {
		System.out.println("Entered into StockDao insertIntoStock(-,-)");
		try {
			con = getConnection();
			pstmt = con.prepareStatement("INSERT INTO stock (type, qty) VALUES (?, ?)");
			pstmt.setString(1, type);
			pstmt.setInt(2, qty);
			pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int insertMobile(MobileForm mobileForm) {
		System.out.println("Entered into StockDao insertMobile(-)");
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(
					"INSERT INTO mobile (Product_brand, product_price, product_model, arrived_date_time) VALUES (?, ?, ?,?)");
			pstmt.setString(1, mobileForm.getProductBrand());
			pstmt.setDouble(2, mobileForm.getProductPrice());
			pstmt.setString(3, mobileForm.getProductModel());
			pstmt.setDate(4, mobileForm.getArrivedDateTime());

			result = pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int insertCharger(ChargerForm chargerForm) {
		System.out.println("Entered into StockDao insertCharger(-)");
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(
					"INSERT INTO charger(Product_brand, product_price, product_model, arrived_date_time) VALUES (?, ?, ?,?)");
			pstmt.setString(1, chargerForm.getProductBrand());
			pstmt.setDouble(2, chargerForm.getProductPrice());
			pstmt.setString(3, chargerForm.getProductModel());
			pstmt.setDate(4, chargerForm.getArrivedDateTime());

			result = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public int insertHeadSet(HeadSetForm headSetForm) {
		System.out.println("Entered into StockDao insertHeadSet(-)");
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(
					"INSERT INTO headset (Product_brand, product_price, product_model, arrived_date_time) VALUES (?, ?, ?,?)");
			pstmt.setString(1, headSetForm.getProductBrand());
			pstmt.setDouble(2, headSetForm.getProductPrice());
			pstmt.setString(3, headSetForm.getProductModel());
			pstmt.setDate(4, headSetForm.getArrivedDateTime());

			result = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int insertPowerBank(PowerBankForm powerBankForm) {
		System.out.println("Entered into StockDao insertPowerBank(-)");
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(
					"INSERT INTO powerbank (Product_brand, product_price, product_model, arrived_date_time) VALUES (?, ?, ?,?)");
			pstmt.setString(1, powerBankForm.getProductBrand());
			pstmt.setDouble(2, powerBankForm.getProductPrice());
			pstmt.setString(3, powerBankForm.getProductModel());
			pstmt.setDate(4, powerBankForm.getArrivedDateTime());

			result = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int insertBackCover(BackCoverForm backCoverForm) {
		System.out.println("Entered into StockDao insertBackCover(-)");
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(
					"INSERT INTO backcover (Product_brand, product_price, product_model, arrived_date_time) VALUES (?, ?, ?,?)");
			pstmt.setString(1, backCoverForm.getProductBrand());
			pstmt.setDouble(2, backCoverForm.getProductPrice());
			pstmt.setString(3, backCoverForm.getProductModel());
			pstmt.setDate(4, backCoverForm.getArrivedDateTime());

			result = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<MobileForm> viewMobileData(String product_brand, String product_model) {
		System.out.println("Entered into StockDao insertBackCover(-,-)");
		List<MobileForm> listOfData = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con

					.prepareStatement("SELECT * FROM mobile WHERE product_brand = ? AND product_model = ?");

			pstmt.setString(1, product_brand);
			pstmt.setString(2, product_model);

			ResultSet resultSet = pstmt.executeQuery();
			System.out.println("Executing viewMobileData query");

			while (resultSet.next()) {
				MobileForm mobileForm = new MobileForm(resultSet.getString("product_brand"),
						resultSet.getDouble("product_price"), resultSet.getString("product_model"),
						resultSet.getDate("arrived_date_time"));

				listOfData.add(mobileForm);
			}

		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listOfData;
	}

	public List<ChargerForm> viewChargerData(String product_brand, String product_model) {
		System.out.println("Entered into StockDao viewChargerData(-,-)");
		List<ChargerForm> listOfData = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con

					.prepareStatement("SELECT * FROM charger WHERE product_brand = ? AND product_model = ?");

			pstmt.setString(1, product_brand);
			pstmt.setString(2, product_model);
			ResultSet resultSet = pstmt.executeQuery();
			System.out.println("Executing viewChargerData query");

			while (resultSet.next()) {
				ChargerForm chargerForm = new ChargerForm(resultSet.getString("product_brand"),
						resultSet.getDouble("product_price"), resultSet.getString("product_model"),
						resultSet.getDate("arrived_date_time"));

				listOfData.add(chargerForm);
			}

		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listOfData;
	}

	public List<PowerBankForm> viewPowerBankData(String product_brand, String product_model) {
		System.out.println("Entered into StockDao viewPowerBankData(-,-)");
		List<PowerBankForm> listOfData = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con

					.prepareStatement("SELECT * FROM powerbank WHERE product_brand = ? AND product_model = ?");

			pstmt.setString(1, product_brand);
			pstmt.setString(2, product_model);
			ResultSet resultSet = pstmt.executeQuery();
			System.out.println("Executing viewPowerBankData query");

			while (resultSet.next()) {
				PowerBankForm powerbankForm = new PowerBankForm(resultSet.getString("product_brand"),
						resultSet.getDouble("product_price"), resultSet.getString("product_model"),
						resultSet.getDate("arrived_date_time"));

				listOfData.add(powerbankForm);
			}

		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listOfData;
	}

	public List<HeadSetForm> viewHeadSetData(String product_brand, String product_model) {
		System.out.println("Entered into StockDao viewHeadSetData(-,-)");
		List<HeadSetForm> listOfData = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM headset WHERE product_brand = ? AND product_model = ?");

			pstmt.setString(1, product_brand);
			pstmt.setString(2, product_model);
			ResultSet resultSet = pstmt.executeQuery();
			System.out.println("Executing viewHeadSetData query");

			while (resultSet.next()) {
				HeadSetForm headsetForm = new HeadSetForm(resultSet.getString("product_brand"),
						resultSet.getDouble("product_price"), resultSet.getString("product_model"),
						resultSet.getDate("arrived_date_time"));

				listOfData.add(headsetForm);
			}

		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listOfData;
	}

	public List<BackCoverForm> viewBackCoverData(String product_brand, String product_model) {
		System.out.println("Entered into StockDao viewBackCoverData(-,-)");
		List<BackCoverForm> listOfData = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con

					.prepareStatement("SELECT * FROM backcover WHERE product_brand = ? AND product_model = ?");

			pstmt.setString(1, product_brand);
			pstmt.setString(2, product_model);
			ResultSet resultSet = pstmt.executeQuery();
			System.out.println("Executing viewHeadSetData query");

			while (resultSet.next()) {
				BackCoverForm backcoverForm = new BackCoverForm(resultSet.getString("product_brand"),
						resultSet.getDouble("product_price"), resultSet.getString("product_model"),
						resultSet.getDate("arrived_date_time"));

				listOfData.add(backcoverForm);
			}

		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listOfData;
	}

	public int deleteMobile(String product_brand, String product_model) {
		System.out.println("Entered into StockDao deleteMobile(-,-)");
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("DELETE FROM mobile WHERE  product_brand = ? AND product_model = ?");
			pstmt.setString(1, product_brand);
			pstmt.setString(2, product_model);

			result = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	public int deleteCharger(String product_brand, String product_model) {
		System.out.println("Entered into StockDao deleteCharger(-,-)");
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("DELETE FROM charger WHERE  product_brand = ? AND product_model = ?");
			pstmt.setString(1, product_brand);
			pstmt.setString(2, product_model);

			result = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	public int deletePowerBank(String product_brand, String product_model) {
		System.out.println("Entered into StockDao deletePowerBank(-,-)");
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("DELETE FROM powerbank WHERE  product_brand = ? AND product_model = ?");
			pstmt.setString(1, product_brand);
			pstmt.setString(2, product_model);

			result = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	public int deleteHeadSet(String product_brand, String product_model) {
		System.out.println("Entered into StockDao deleteHeadSet(-,-)");
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("DELETE FROM headset WHERE  product_brand = ? AND product_model = ?");
			pstmt.setString(1, product_brand);
			pstmt.setString(2, product_model);

			result = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public int deleteBackCover(String product_brand, String product_model) {
		System.out.println("Entered into StockDao deleteBackCover(-,-)");
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("DELETE FROM backcover WHERE  product_brand = ? AND product_model = ?");
			pstmt.setString(1, product_brand);
			pstmt.setString(2, product_model);

			result = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	public int updateMobilePrice(Double productPrice, String productModel) {
		System.out.println("Entered into StockDao updateMobilePrice(-,-)");
		int executeUpdate = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE mobile SET product_price = ? WHERE product_model = ?");
			pstmt.setDouble(1, productPrice);
			pstmt.setString(2, productModel);
			executeUpdate = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return executeUpdate;
	}

	public int updateChargerPrice(Double productPrice, String productModel) {
		System.out.println("Entered into StockDao updateChargerPrice(-,-)");
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE charger SET product_price = ? WHERE product_model = ?");
			pstmt.setDouble(1, productPrice);
			pstmt.setString(2, productModel);
			result = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int updatePowerBankPrice(Double productPrice, String productModel) {
		System.out.println("Entered into StockDao updatePowerBankPrice(-,-)");
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE powerbank SET product_price = ? WHERE product_model = ?");
			pstmt.setDouble(1, productPrice);
			pstmt.setString(2, productModel);
			result = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int updateHeadSetPrice(Double productPrice, String productModel) {
		System.out.println("Entered into StockDao updateHeadSetPrice(-,-)");
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE headset SET product_price = ? WHERE product_model = ?");
			pstmt.setDouble(1, productPrice);
			pstmt.setString(2, productModel);
			result = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateBackCoverPrice(Double productPrice, String productModel) {
		System.out.println("Entered into StockDao updateBackCoverPrice(-,-)");
		int result = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE backcover SET product_price = ? WHERE product_model = ?");
			pstmt.setDouble(1, productPrice);
			pstmt.setString(2, productModel);
			result = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<MobileForm> viewAllMobileData() {
		System.out.println("Entered into StockDao viewAllMobileData(-,-)");
		List<MobileForm> listOfData = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM mobile  ");
			ResultSet resultSet = pstmt.executeQuery();
			System.out.println("Executing ViewAllMobileData ");

			while (resultSet.next()) {
				MobileForm mobileForm = new MobileForm(resultSet.getString("product_brand"),
						resultSet.getDouble("product_price"), resultSet.getString("product_model"),
						resultSet.getDate("arrived_date_time"));

				listOfData.add(mobileForm);
			}

		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listOfData;
	}

	public List<ChargerForm> viewAllChargerData() {
		System.out.println("Entered into StockDao viewAllChargerData(-,-)");
		List<ChargerForm> listOfData = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM charger ");

			ResultSet resultSet = pstmt.executeQuery();
			System.out.println("Executing ViewAllChargerData query");

			while (resultSet.next()) {
				ChargerForm chargerForm = new ChargerForm(resultSet.getString("product_brand"),
						resultSet.getDouble("product_price"), resultSet.getString("product_model"),
						resultSet.getDate("arrived_date_time"));

				listOfData.add(chargerForm);
			}

		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listOfData;
	}

	public List<PowerBankForm> viewAllPowerBankData() {
		System.out.println("Entered into StockDao viewAllPowerBankData()");
		List<PowerBankForm> listOfData = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM powerbank ");

			ResultSet resultSet = pstmt.executeQuery();
			System.out.println("Executing viewPowerBankData query");

			while (resultSet.next()) {
				PowerBankForm powerbankForm = new PowerBankForm(resultSet.getString("product_brand"),
						resultSet.getDouble("product_price"), resultSet.getString("product_model"),
						resultSet.getDate("arrived_date_time"));

				listOfData.add(powerbankForm);
			}

		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listOfData;
	}

	public List<HeadSetForm> viewAllHeadSetData() {
		System.out.println("Entered into StockDao viewAllHeadSetData()");
		List<HeadSetForm> listOfData = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM headset ");

			ResultSet resultSet = pstmt.executeQuery();
			System.out.println("Executing viewHeadSetData query");

			while (resultSet.next()) {
				HeadSetForm headsetForm = new HeadSetForm(resultSet.getString("product_brand"),
						resultSet.getDouble("product_price"), resultSet.getString("product_model"),
						resultSet.getDate("arrived_date_time"));

				listOfData.add(headsetForm);
			}

		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listOfData;
	}

	public List<BackCoverForm> viewAllBackCoverData() {
		System.out.println("Entered into StockDao viewAllBackCoverData()");
		List<BackCoverForm> listOfData = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM backcover WHERE product_brand = ? AND product_model = ?");

			ResultSet resultSet = pstmt.executeQuery();
			System.out.println("Executing viewHeadSetData query");

			while (resultSet.next()) {
				BackCoverForm backcoverForm = new BackCoverForm(resultSet.getString("product_brand"),
						resultSet.getDouble("product_price"), resultSet.getString("product_model"),
						resultSet.getDate("arrived_date_time"));

				listOfData.add(backcoverForm);
			}

		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listOfData;
	}

	public List<ProductForm> selectStockByType(String type) {
		System.out.println("Entered into StockDao selectStockByType()");
		List<ProductForm> formsList = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM " + type);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				formsList.add(new ProductForm(resultSet.getString("product_brand"), resultSet.getDouble("product_price"),
						resultSet.getString("product_model"), type, resultSet.getDate("arrived_date_time")));

			}

		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.out.println(" SQL | CNFE " + sqlcnfe);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			System.out.println();
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return formsList;
	}

}
