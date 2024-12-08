package com.khadri.jakarta.stock.servlet.modify;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.khadri.jakarta.product.dao.ProductDao;
import com.khadri.jakarta.product.form.ProductForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StockModifyPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProductDao productDao;

	@Override
	public void init() throws ServletException {
		productDao = new ProductDao();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entered into StockModifyPageServlet doGet(-,-)");

		List<ProductForm> listOfProducts = productDao.selectProductData();

		PrintWriter pw = resp.getWriter();
		StringBuffer sb = new StringBuffer();
		String type = req.getParameter("type");
		resp.setContentType("text/html");

		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>Modify Stock Details</title>");
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

		if (type == null || type.isEmpty()) {
			sb.append("<form action='stockmodifysearchpage' method='get' onsubmit='return formValidation()'>");
			sb.append("<table border=1>");
			sb.append("<tr>");
			sb.append("<td>");

			sb.append("<label>Type:</label>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<select name='type' id='type' name='type'>");
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
			sb.append("<label>Product Brand:</label>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<input type='text' name='product_brand' id='product_brand'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<label>Product Model:</label>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<input type='text' name='product_model' id='product_model'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<input type='submit' value='Modify Stock Search'>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</form>");
			sb.append("</table>");
		}

		sb.append("</body>");
		sb.append("</html>");

		pw.println(sb);
	}
}
