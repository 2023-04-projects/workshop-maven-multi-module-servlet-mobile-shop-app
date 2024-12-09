package com.khadri.jakarta.stock.servlet.modify;

import java.io.IOException;
import java.io.PrintWriter;

import com.khadri.jakarta.dao.util.UtilDao;
import com.khadri.jakarta.stock.dao.StockDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StockModifyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StockDao stockDao;
	private UtilDao utilDao;

	public void init() throws ServletException {
		utilDao = new UtilDao();
		stockDao = new StockDao(utilDao);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entered into StockModifyServlet doGet(-,-)");
		String productModel = req.getParameter("product_model");
		String productPriceStr = req.getParameter("product_price");
		String productType = req.getParameter("type");
		String mode = req.getParameter("mode_modify");
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");

		pw.println("<html>");
		pw.println("<head>");
		pw.println("<link rel='stylesheet' type='text/css' href='styles.css'/>");
		pw.println("</head>");
		pw.println("<body>");

		if (mode == null || mode.equals("")) {
			StringBuffer sb = new StringBuffer();
			sb.append("<form>");
			sb.append("<table border=1>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<label>Product Model:</label>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<input type='text' name='product_model' value=" + productModel + ">");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<label>Product price:</label>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<input type='text' name='product_price' value=" + productPriceStr + ">");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<input type='hidden' name='type' value=" + productType + ">");
			sb.append("<input type='hidden' name='mode_modify' value=modify>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<input type='submit' value='Stock Modify'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</form>");
			pw.println(sb);
		} else {

			if (mode != null) {
				int result = 0;
				switch (productType) {
				case "mobile":
					result = stockDao.updateMobilePrice(Double.valueOf(productPriceStr), productModel);
					pw.println(result + " updated successfulyy");
					break;
				case "charger":
					result = stockDao.updateChargerPrice(Double.valueOf(productPriceStr), productModel);
					pw.println(result + " updated successfulyy");
					break;
				case "powerbank":
					result = stockDao.updatePowerBankPrice(Double.valueOf(productPriceStr), productModel);
					pw.println(result + " updated successfulyy");
					break;
				case "headset":
					result = stockDao.updateHeadSetPrice(Double.valueOf(productPriceStr), productModel);
					pw.println(result + " updated successfulyy");
					break;
				case "backcover":
					result = stockDao.updateBackCoverPrice(Double.valueOf(productPriceStr), productModel);
					pw.println(result + " updated successfulyy");
					break;
				default:
					System.out.println("########################No Match Found########################");
				}
			}

		}

		pw.println("</body>");
		pw.println("</html>");

	}
}
