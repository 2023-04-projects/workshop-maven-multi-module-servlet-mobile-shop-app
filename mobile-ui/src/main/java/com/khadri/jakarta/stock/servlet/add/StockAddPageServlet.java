package com.khadri.jakarta.stock.servlet.add;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.khadri.jakarta.dao.util.UtilDao;
import com.khadri.jakarta.product.dao.ProductDao;
import com.khadri.jakarta.product.form.ProductForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StockAddPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProductDao productDao;
	private UtilDao utilDao;

	@Override
	public void init() throws ServletException {
		utilDao = new UtilDao();
		productDao = new ProductDao(utilDao);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entered StockAddPageServlet doGet(-,-)");

		List<ProductForm> listOfProducts = productDao.selectProductData();

		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<html>");
		stringBuffer.append("<head>");
		stringBuffer.append("<title>Add Stock Details</title>");
		stringBuffer.append("<link rel='stylesheet' type='text/css' href='styles.css'/>");
		stringBuffer.append("</head>");

		stringBuffer.append("<body>");
		stringBuffer.append("<script type='text/javascript'>");
		stringBuffer.append("function formValidation() {");
		stringBuffer.append("var selectBoxComponent = document.querySelector('#type');");
		stringBuffer.append("var selectedIndex = selectBoxComponent.selectedIndex;");
		stringBuffer.append("var productBrandComponent = document.getElementById('pb');");
		stringBuffer.append("var productPriceComponent = document.getElementById('pp');");
		stringBuffer.append("var productModelComponent = document.getElementById('pm');");
		stringBuffer.append("var arriveddateComponent = document.getElementById('adt');");
		stringBuffer.append("if (selectedIndex == 0) {");
		stringBuffer.append("alert('Please select type.');");
		stringBuffer.append("return false;");
		stringBuffer.append("} ");
		stringBuffer.append("if (productBrandComponent.value == '') {");
		stringBuffer.append("alert('Please enter product brand.');");
		stringBuffer.append("productBrandComponent.focus();");
		stringBuffer.append("return false;");
		stringBuffer.append("} ");
		stringBuffer.append("if (productModelComponent.value == '') {");
		stringBuffer.append("alert('Please enter product model.');");
		stringBuffer.append("productModelComponent.focus();");
		stringBuffer.append("return false;");
		stringBuffer.append("} ");
		stringBuffer.append("if (productPriceComponent.value == '') {");
		stringBuffer.append("alert('Please enter product price.');");
		stringBuffer.append("productPriceComponent.focus();");
		stringBuffer.append("return false;");
		stringBuffer.append("} ");
		stringBuffer.append("if (arriveddateComponent.value == '') {");
		stringBuffer.append("alert('Please enter arrived date time.');");
		stringBuffer.append("arriveddateComponent.focus();");
		stringBuffer.append("return false;");
		stringBuffer.append("}");
		stringBuffer.append("}");
		stringBuffer.append("</script>");
		stringBuffer.append("<form action='stockadd' method='post' onsubmit='return formValidation()'>");
		stringBuffer.append("<table border=1>");
		stringBuffer.append("<tbody>");
		stringBuffer.append("<tr><td><label>Type:</label><select name='type' id='type'>");
		stringBuffer.append("<option>--select--</option>");
		listOfProducts.stream().forEach(eachProduct -> {
			stringBuffer.append("<option value='" + eachProduct.getName() + "'>");
			stringBuffer.append(eachProduct.getName());
			stringBuffer.append("</option>");
		});
		stringBuffer.append("</select></td></tr>");
		stringBuffer.append(
				"<tr><td>Arrived Date/Time:<input type='datetime-local' name='arrived_date_time' id='adt'></td></tr>");
		stringBuffer.append("<tr><td>Product Brand:<input type='text' name='Product_brand' id='pb'></td></tr>");
		stringBuffer.append("<tr><td>Product Price:<input type='text' name='product_price' id='pp'></td></tr>");
		stringBuffer.append("<tr><td>Product Model:<input type='text' name='product_model' id='pm'></td></tr>");
		stringBuffer.append("<tr><td><input type='submit' value='Add Stock'></td></tr>");
		stringBuffer.append("</tbody>");
		stringBuffer.append("</table>");
		stringBuffer.append("</form>");
		stringBuffer.append("</body>");
		stringBuffer.append("</html>");

		pw.println(stringBuffer.toString());

	}

}
