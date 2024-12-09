package com.khadri.jakarta.stock.servlet.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.khadri.jakarta.dao.util.UtilDao;
import com.khadri.jakarta.product.dao.ProductDao;
import com.khadri.jakarta.product.form.ProductForm;
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

public class StockViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StockDao stockDao;
	private ProductDao productDao;
	private List<MobileForm> listOfMobileForm;
	private List<ChargerForm> listOfChargerForm;
	private List<HeadSetForm> listOfHeadSetForm;
	private List<PowerBankForm> listOfPowerBankForm;
	private List<BackCoverForm> listOfBackCoverForm;
	private UtilDao utilDao;

	public void init() throws ServletException {
		utilDao = new UtilDao();
		stockDao = new StockDao(utilDao);
		productDao = new ProductDao(utilDao);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entered into StockViewServlet doGet(-,-)");

		List<ProductForm> listOfProducts = productDao.selectProductData();

		String type = req.getParameter("type");
		String product_brand = req.getParameter("product_brand");
		String product_model = req.getParameter("product_model");

		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();

		StringBuilder sb = new StringBuilder();

		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>View Stock Details</title>");
		sb.append("<link rel='stylesheet' type='text/css' href='styles.css'/>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<script type='text/javascript'>");
		sb.append("function formValidation() {");
		sb.append("var selectBoxComponent = document.querySelector('#type');");
		sb.append("var selectedIndex = selectBoxComponent.selectedIndex;");
		sb.append("var productBrandComponent = document.getElementById('product_brand');");
		sb.append("var productModelComponent = document.getElementById('product_model');");
		sb.append("if (selectedIndex == 0) {");
		sb.append("alert('Please select type.');");
		sb.append("return false;");
		sb.append("} ");
		sb.append("if(productBrandComponent.value == '') {");
		sb.append("alert('Please enter a product brand.');");
		sb.append("productBrandComponent.focus();");
		sb.append("return false;");
		sb.append("} ");
		sb.append("if (productModelComponent.value == '') {");
		sb.append("alert('Please enter product model.');");
		sb.append("productModelComponent.focus();");
		sb.append("return false;");
		sb.append("} ");
		sb.append("}");
		sb.append("</script>");

		sb.append("<form action='StockViewServlet' method='get' onsubmit='return formValidation()'>");
		sb.append("<table border=1>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("<label>Type:</label>");
		sb.append("</td>");
		sb.append("<td>");
		sb.append("<select name='type' id='type'>");
		sb.append("<option value=''>--select--</option>");
		listOfProducts.stream().forEach(eachProduct -> {
			sb.append("<option value='" + eachProduct.getName() + "'>");
			sb.append(eachProduct.getName());
			sb.append("</option>");

		});
		sb.append("</select>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("Product Brand:");
		sb.append("</td>");
		sb.append("<td>");
		sb.append("<input type='text' name='product_brand' id= 'product_brand'>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("Product Model:");
		sb.append("</td>");
		sb.append("<td>");
		sb.append("<input type='text' name='product_model' id= 'product_model'>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("<input type='submit' value='View Stock Search'>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</form>");
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
						sb.append("<td>").append(eachProduct.getProductModel()).append("</td>");
						sb.append("<td>").append(eachProduct.getProductPrice()).append("</td>");
						sb.append("<td>").append(eachProduct.getArrivedDateTime()).append("</td>");

						sb.append("</tr>");
					}

				} else {
					sb.append("<tr>");
					sb.append("<td colspan='4' id='nrf'>No Records Found</td>");
					sb.append("</tr>");
				}
				break;
			case "charger":
				listOfChargerForm = stockDao.viewChargerData(product_brand, product_model);
				if (listOfChargerForm != null && !listOfChargerForm.isEmpty()) {

					for (ChargerForm eachProduct : listOfChargerForm) {
						sb.append("<tr>");
						sb.append("<td>").append(eachProduct.getProductBrand()).append("</td>");
						sb.append("<td>").append(eachProduct.getProductModel()).append("</td>");
						sb.append("<td>").append(eachProduct.getProductPrice()).append("</td>");
						sb.append("<td>").append(eachProduct.getArrivedDateTime()).append("</td>");

						sb.append("</tr>");
					}

				} else {
					sb.append("<tr>");
					sb.append("<td colspan='4' id='nrf'>No Records Found</td>");
					sb.append("</tr>");
				}
				break;
			case "powerbank":
				listOfPowerBankForm = stockDao.viewPowerBankData(product_brand, product_model);

				if (listOfPowerBankForm != null && !listOfPowerBankForm.isEmpty()) {

					for (PowerBankForm eachProduct : listOfPowerBankForm) {
						sb.append("<tr>");
						sb.append("<td>").append(eachProduct.getProductBrand()).append("</td>");
						sb.append("<td>").append(eachProduct.getProductModel()).append("</td>");
						sb.append("<td>").append(eachProduct.getProductPrice()).append("</td>");
						sb.append("<td>").append(eachProduct.getArrivedDateTime()).append("</td>");

						sb.append("</tr>");
					}

				} else {
					sb.append("<tr>");
					sb.append("<td colspan='4' id='nrf'>No Records Found</td>");
					sb.append("</tr>");
				}
				break;
			case "headset":
				listOfHeadSetForm = stockDao.viewHeadSetData(product_brand, product_model);
				if (listOfHeadSetForm != null && !listOfHeadSetForm.isEmpty()) {

					for (HeadSetForm eachProduct : listOfHeadSetForm) {
						sb.append("<tr>");
						sb.append("<td>").append(eachProduct.getProductBrand()).append("</td>");
						sb.append("<td>").append(eachProduct.getProductModel()).append("</td>");
						sb.append("<td>").append(eachProduct.getProductPrice()).append("</td>");
						sb.append("<td>").append(eachProduct.getArrivedDateTime()).append("</td>");

						sb.append("</tr>");
					}
					sb.append("</tbody>");
					sb.append("</table>");
				} else {
					sb.append("<tr>");
					sb.append("<td colspan='4' id='nrf'>No Records Found</td>");
					sb.append("</tr>");
				}
				break;
			case "backcover":
				listOfBackCoverForm = stockDao.viewBackCoverData(product_brand, product_model);
				if (listOfBackCoverForm != null && !listOfBackCoverForm.isEmpty()) {

					for (BackCoverForm eachProduct : listOfBackCoverForm) {
						sb.append("<tr>");
						sb.append("<td>").append(eachProduct.getProductBrand()).append("</td>");
						sb.append("<td>").append(eachProduct.getProductModel()).append("</td>");
						sb.append("<td>").append(eachProduct.getProductPrice()).append("</td>");
						sb.append("<td>").append(eachProduct.getArrivedDateTime()).append("</td>");

						sb.append("</tr>");
					}

				} else {
					sb.append("<tr>");
					sb.append("<td colspan='4' id='nrf'>No Records Found</td>");
					sb.append("</tr>");
				}

				break;
			default:
				listOfMobileForm = null;
			}
		}

		sb.append("</body>");
		sb.append("</html>");

		pw.println(sb.toString());
	}
}
