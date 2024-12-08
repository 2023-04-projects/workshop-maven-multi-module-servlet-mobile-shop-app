package com.khadri.jakarta.product.servlet.modify;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductModifyPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entered into ModifyPageServlet doGet(-,-)");
		PrintWriter pw = resp.getWriter();
		String productId = req.getParameter("ID");
		String productName = req.getParameter("Name");

		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<link rel='stylesheet' type='text/css' href='styles.css'/>");
		sb.append("</head>");
		sb.append("<body>");
		pw.println("<script type='text/javascript'>");
		pw.println("function formValidation() {");
		pw.println("var productNameComponent = document.getElementById('productName')");
		pw.println("if(productNameComponent.value == '') {");
		pw.println("alert('Please Update Product Name');");
		pw.println("productNameComponent.focus();");
		pw.println("return false;");
		pw.println("}");
		pw.println("}");
		pw.println("</script>");
		sb.append("<form action='productmodify' method='post' onsubmit= 'return formValidation()'>");
		sb.append("<table border=1>");
		sb.append("<tbody>");
		sb.append("<tr><td>ProductId :<input type='text' name='ID' value='" + productId + "' readonly></td></tr>");
		sb.append("<tr><td>Product Name :<input type='text' name='Name' id = 'productName' value='" + productName
				+ "'></td></tr>");
		sb.append("<tr><td><input type='submit' value='Product Modify'></td></tr>");
		sb.append("</tbody>");
		sb.append("</table>");
		sb.append("</form>");
		sb.append("</body>");
		sb.append("</html>");

		pw.println(sb);

	}
}
