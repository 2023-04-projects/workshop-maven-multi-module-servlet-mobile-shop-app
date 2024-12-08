package com.khadri.jakarta.stock.servlet.modify;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.khadri.jakarta.stock.dao.StockDao;
import com.khadri.jakarta.stock.form.BackCoverForm;
import com.khadri.jakarta.stock.form.ChargerForm;
import com.khadri.jakarta.stock.form.HeadSetForm;
import com.khadri.jakarta.stock.form.MobileForm;
import com.khadri.jakarta.stock.form.PowerBankForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StockModifySearchPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StockDao stockDao;

	private List<MobileForm> listOfMobileForm;
	private List<ChargerForm> listOfChargerForm;
	private List<HeadSetForm> listOfHeadSetForm;
	private List<PowerBankForm> listOfPowerBankForm;
	private List<BackCoverForm> listOfBackCoverForm;

	@Override
	public void init() throws ServletException {
		stockDao = new StockDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entered into StockModifySearchPageServlet doGet(-,-)");

		String product_brand = req.getParameter("product_brand");
		String product_model = req.getParameter("product_model");
		String type = req.getParameter("type");

		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		StringBuilder sb = new StringBuilder();

		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>Modify Stock Details</title>");
		sb.append("<link rel='stylesheet' type='text/css' href='styles.css'/>");
		sb.append("</head>");
		sb.append("<body>");

		sb.append("<table border='1'>");
		sb.append("<thead>");
		sb.append(
				"<tr><th>Product Brand</th><th>Product Model</th><th>Product Price</th><th>ArrivedDateTime</th></tr>");
		sb.append("</thead>");
		sb.append("<tbody>");

		if (type != null && !type.isEmpty()) {
			switch (type) {
			case "mobile":
				listOfMobileForm = stockDao.viewMobileData(product_brand, product_model);
				if (listOfMobileForm != null && !listOfMobileForm.isEmpty()) {

					for (MobileForm eachProduct : listOfMobileForm) {
						sb.append("<tr>");
						sb.append("<td>").append(eachProduct.getProductBrand()).append("</td>");
						sb.append("<td><a href='stockmodifyservlet?product_model=")
								.append(eachProduct.getProductModel())
								.append("&product_price=" + eachProduct.getProductPrice() + "&type=" + type + "'>")
								.append(eachProduct.getProductModel()).append("</a></td>");
						sb.append("<td>").append(eachProduct.getProductPrice()).append("</td>");
						sb.append("<td>").append(eachProduct.getArrivedDateTime()).append("</td>");
						sb.append("</tr>");
					}

				}else {
					sb.append("<tr>");
					sb.append("<td colspan='4' id='nrf'>No Records Found</td>");
					sb.append("</tr>");
				}
				break;
			case "charger":
				listOfChargerForm = stockDao.viewChargerData(product_brand, product_model);

				if (listOfChargerForm != null && !listOfChargerForm.isEmpty()) {
					for (ChargerForm charger : listOfChargerForm) {
						sb.append("<tr>");
						sb.append("<td>").append(charger.getProductBrand()).append("</td>");
						sb.append("<td><a href='stockmodifyservlet?product_model=")
								.append(charger.getProductModel())
								.append("&product_price=" + charger.getProductPrice() + "&type=" + type + "'>")
								.append(charger.getProductModel()).append("</a></td>");
						sb.append("<td>").append(charger.getProductPrice()).append("</td>");
						sb.append("<td>").append(charger.getArrivedDateTime()).append("</td>");
						sb.append("</tr>");
					}
				}else {
					sb.append("<tr>");
					sb.append("<td colspan='4' id='nrf'>No Records Found</td>");
					sb.append("</tr>");
				}

				break;
			case "powerbank":
				listOfPowerBankForm = stockDao.viewPowerBankData(product_brand, product_model);

				if (listOfPowerBankForm != null && !listOfPowerBankForm.isEmpty()) {
					for (PowerBankForm powerbank : listOfPowerBankForm) {
						sb.append("<tr>");
						sb.append("<td>").append(powerbank.getProductBrand()).append("</td>");
						sb.append("<td><a href='stockmodifyservlet?product_model=")
								.append(powerbank.getProductModel())
								.append("&product_price=" + powerbank.getProductPrice() + "&type=" + type + "'>")
								.append(powerbank.getProductModel()).append("</a></td>");
						sb.append("<td>").append(powerbank.getProductPrice()).append("</td>");
						sb.append("<td>").append(powerbank.getArrivedDateTime()).append("</td>");
						sb.append("</tr>");
					}
				}else {
					sb.append("<tr>");
					sb.append("<td colspan='4' id='nrf'>No Records Found</td>");
					sb.append("</tr>");
				}

				break;
			case "headset":
				listOfHeadSetForm = stockDao.viewHeadSetData(product_brand, product_model);
				if (listOfHeadSetForm != null && !listOfHeadSetForm.isEmpty()) {
					for (HeadSetForm headset : listOfHeadSetForm) {
						sb.append("<tr>");
						sb.append("<td>").append(headset.getProductBrand()).append("</td>");
						sb.append("<td><a href='stockmodifyservlet?product_model=")
								.append(headset.getProductModel())
								.append("&product_price=" + headset.getProductPrice() + "&type=" + type + "'>")
								.append(headset.getProductModel()).append("</a></td>");
						sb.append("<td>").append(headset.getProductPrice()).append("</td>");
						sb.append("<td>").append(headset.getArrivedDateTime()).append("</td>");
						sb.append("</tr>");
					}
				}else {
					sb.append("<tr>");
					sb.append("<td colspan='4' id='nrf'>No Records Found</td>");
					sb.append("</tr>");
				}

				break;
			case "backcover":
				listOfBackCoverForm = stockDao.viewBackCoverData(product_brand, product_model);
				if (listOfBackCoverForm != null && !listOfBackCoverForm.isEmpty()) {
					for (BackCoverForm backcover : listOfBackCoverForm) {
						sb.append("<tr>");
						sb.append("<td>").append(backcover.getProductBrand()).append("</td>");
						sb.append("<td><a href='stockmodifyservlet?product_model=")
								.append(backcover.getProductModel())
								.append("&product_price=" + backcover.getProductPrice() + "&type=" + type + "'>")
								.append(backcover.getProductModel()).append("</a></td>");
						sb.append("<td>").append(backcover.getProductPrice()).append("</td>");
						sb.append("<td>").append(backcover.getArrivedDateTime()).append("</td>");
						sb.append("</tr>");
					}
				}else {
					sb.append("<tr>");
					sb.append("<td colspan='4' id='nrf'>No Records Found</td>");
					sb.append("</tr>");
				}

				break;
			default:
				listOfMobileForm = null;
			}
		}

		sb.append("</table>");
		sb.append("</tbody>");
		sb.append("</body>");
		sb.append("</html>");

		pw.println(sb);
	}
}
