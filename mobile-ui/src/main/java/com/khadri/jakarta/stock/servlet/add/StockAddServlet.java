package com.khadri.jakarta.stock.servlet.add;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.khadri.jakarta.dao.util.UtilDao;
import com.khadri.jakarta.stock.dao.StockDao;
import com.khadri.jakarta.stock.form.BackCoverForm;
import com.khadri.jakarta.stock.form.ChargerForm;
import com.khadri.jakarta.stock.form.HeadSetForm;
import com.khadri.jakarta.stock.form.MobileForm;
import com.khadri.jakarta.stock.form.PowerBankForm;
import com.khadri.jakarta.stock.form.StockForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StockAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StockForm form;
	private MobileForm mobileForm;
	private ChargerForm chargerForm;
	private PowerBankForm powerBankForm;
	private HeadSetForm headSetForm;
	private BackCoverForm backCoverForm;
	private StockDao stockDao;
	private UtilDao utilDao;

	public void init() throws ServletException {
		utilDao = new UtilDao();
		stockDao = new StockDao(utilDao);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entered StockAddServlet doPost(-,-)");

		String type = req.getParameter("type");
		String productBrand = req.getParameter("Product_brand");
		String productPrice = req.getParameter("product_price");
		String productModel = req.getParameter("product_model");
		String arrivedDateTime = req.getParameter("arrived_date_time");

		if (productPrice != null && !productPrice.isEmpty()) {
			try {
				Double.parseDouble(productPrice);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println("Invalid price format.");
			}
		} else {
			System.out.println("productprice parameter is missing or empty.");
		}
		Date sqlDate = null;
		if (arrivedDateTime != null && !arrivedDateTime.isEmpty()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date parsedDate = dateFormat.parse(arrivedDateTime);
				sqlDate = new Date(parsedDate.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("arrivedDateTime parameter is missing or empty.");
		}

		mobileForm = new MobileForm(productBrand, Double.parseDouble(productPrice), productModel, sqlDate);
		chargerForm = new ChargerForm(productBrand, Double.parseDouble(productPrice), productModel, sqlDate);
		powerBankForm = new PowerBankForm(productBrand, Double.parseDouble(productPrice), productModel, sqlDate);
		backCoverForm = new BackCoverForm(productBrand, Double.parseDouble(productPrice), productModel, sqlDate);
		headSetForm = new HeadSetForm(productBrand, Double.parseDouble(productPrice), productModel, sqlDate);

		if (type != null) {
			System.out.println("entered into if block");
			try {
				form = stockDao.selectStockTypeRecord(type);
				if (form != null) {
					System.out.println("entered into if form block");
					int qty = form.getQty() + 1;
					stockDao.updateStockQty(type, qty);
				} else {
					System.out.println("entered in to else block");
					stockDao.insertIntoStock(type, 1);
				}
				System.out.println("inserted Successfully");
				int result = insertIntoStockType(type, form);
				if (result > 0) {
					PrintWriter pw = resp.getWriter();
					pw.println("<html>");
					pw.println("<head>");
					pw.println("<link rel='stylesheet' type='text/css' href='styles.css'/>");
					pw.println("</head>");
					pw.println("<body>");
					pw.println(result + " inserted successfully! ");
					pw.println("</body>");
					pw.println("</html>");
				}

			} catch (Exception e) {
				System.out.println("@@@@@@@Something went wrong@@@@@@@");

				e.printStackTrace();
			}
		}

	}

	private int insertIntoStockType(String type, StockForm form) throws Exception {
		System.out.println("insertIntoStockType method" + type);
		int result = 0;
		switch (type) {
		case "mobile":
			System.out.println("entered into switch case mobile  method");
			result = stockDao.insertMobile(mobileForm);
			System.out.println("inserted into switch case mobile  method");
			break;
		case "charger":
			System.out.println("entered into switch case charger method");
			result = stockDao.insertCharger(chargerForm);
			break;
		case "powerbank":
			System.out.println("entered into switch case powerbank method");
			result = stockDao.insertPowerBank(powerBankForm);
			break;
		case "headset":
			System.out.println("entered into switch case headset  method");
			result = stockDao.insertHeadSet(headSetForm);
			break;
		case "backcover":
			System.out.println("entered into switch case backcover  method");
			result = stockDao.insertBackCover(backCoverForm);
			break;
		default:
			System.out.println("invalid method type");
			break;
		}
		return result;
	}
}
