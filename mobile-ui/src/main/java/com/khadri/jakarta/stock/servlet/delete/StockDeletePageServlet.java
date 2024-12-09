package com.khadri.jakarta.stock.servlet.delete;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.khadri.jakarta.dao.util.UtilDao;
import com.khadri.jakarta.product.dao.ProductDao;
import com.khadri.jakarta.product.form.ProductForm;
import com.khadri.jakarta.stock.dao.StockDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StockDeletePageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StockDao stockDao;
	private ProductDao productDao;
	private UtilDao utilDao;

	public void init() throws ServletException {
		utilDao = new UtilDao();
		stockDao = new StockDao(utilDao);
		productDao = new ProductDao(utilDao);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Entered into StockDeletePageServlet doGet(-,-)");
		List<ProductForm> listOfProducts = productDao.selectProductData();
		String type = request.getParameter("type");
		String product_brand = request.getParameter("product_brand");
		String product_model = request.getParameter("product_model");

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		StringBuffer sb = new StringBuffer();

		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>Delete Stock Details</title>");
		sb.append("<link rel='stylesheet' type='text/css' href='styles.css'/>");
		sb.append("</head>");
		sb.append("<body>");
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

		if (type == null || type.isEmpty()) {
			sb.append("<form action='StockDeletePageServlet' method='get' onsubmit='return formValidation()'>");
			sb.append("<table border=1>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<label>Type:</label>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<select name='type' id='type'>");
			sb.append("<option value=''>--select--</option>");
			listOfProducts.stream().forEach(eachProduct -> {
				sb.append("<option value='" + eachProduct.getName() + "'>" + eachProduct.getName() + "</option>");
			});
			sb.append("</select>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<label>Product Brand:</label>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<input type='text' name='product_brand' id= 'product_brand'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<label>Product Model:</label>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<input type='text' name='product_model'  id='product_model'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<input type='submit' value='Delete Stock'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</form>");
		} else {

			int result = 0;
			switch (type) {
			case "mobile":
				result = stockDao.deleteMobile(product_brand, product_model);
				break;
			case "charger":
				result = stockDao.deleteCharger(product_brand, product_model);
				break;
			case "powerbank":
				result = stockDao.deletePowerBank(product_brand, product_model);
				break;
			case "headset":
				result = stockDao.deleteHeadSet(product_brand, product_model);
				break;
			case "backcover":
				result = stockDao.deleteBackCover(product_brand, product_model);
				break;
			default:
				pw.println("Invalid product type selected.");
				break;
			}

			if (result > 0) {
				pw.println("<html>");
				pw.println("<head>");
				pw.println("<link rel='stylesheet' type='text/css' href='styles.css'/>");
				pw.println("</head>");
				pw.println("<body>");
				pw.println(result + " deleted successfully.");

				pw.println("</body>");
				pw.println("</html>");
			} else {
				pw.println("<html>");
				pw.println("<head>");
				pw.println("<link rel='stylesheet' type='text/css' href='styles.css'/>");
				pw.println("</head>");
				pw.println("<body>");
				pw.println("Failed to delete " + type + " entry. Please check the details.");
				pw.println("</body>");
				pw.println("</html>");
			}
		}

		sb.append("</body>");
		sb.append("</html>");
		pw.println(sb.toString());
	}

}
