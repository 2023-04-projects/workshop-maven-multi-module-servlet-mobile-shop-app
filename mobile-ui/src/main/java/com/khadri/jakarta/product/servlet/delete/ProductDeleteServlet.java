package com.khadri.jakarta.product.servlet.delete;

import java.io.IOException;
import java.io.PrintWriter;

import com.khadri.jakarta.product.dao.ProductDao;
import com.khadri.jakarta.product.form.ProductForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao dao;
	private ProductForm form;

	public void init() throws ServletException {
		dao = new ProductDao();
		form = new ProductForm();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entered into ProductDeletServlet doPost(-,-)");
		String id = req.getParameter("ID");
		String name = req.getParameter("Name");
		if (id != null && !id.isEmpty()) {
			int parseInt = Integer.parseInt(id);
			form.setId(parseInt);
		} else {
			System.out.println("ID parameter is missing or empty.");
		}
		form.setName(name);

		int result = dao.deleteProduct(form);

		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<link rel='stylesheet' type='text/css' href='styles.css'/>");
		pw.println("</head>");
		pw.println("<body>");
		if (result == 1) {
			pw.println(result + "  Deleted sucessfully!!!! ");
		} else {
			pw.println("@@@@@Something went wrong@@@@@");
		}
		pw.println("</body>");
		pw.println("</html>");

	}
}
