package com.khadri.jakarta.product.servlet.add;

import java.io.IOException;
import java.io.PrintWriter;

import com.khadri.jakarta.dao.util.UtilDao;
import com.khadri.jakarta.product.dao.ProductDao;
import com.khadri.jakarta.product.form.ProductForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProductDao dao;
	private ProductForm form;
	private UtilDao utilDao;

	public void init() throws ServletException {
		form = new ProductForm();
		utilDao = new UtilDao();
		dao = new ProductDao(utilDao);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entered into AddMobileServlet doPost(-,-)");
		String name = req.getParameter("productName");
		name = name.toLowerCase();
		form.setName(name);
		int result = dao.insertMobileData(form);

		PrintWriter pw = resp.getWriter();

		pw.println("<html>");
		pw.println("<head>");
		pw.println("<link rel='stylesheet' type='text/css' href='styles.css'/>");
		pw.println("</head>");
		pw.println("<body>");
		if (result == 1) {
			pw.println(result + " Inserted Successfully!!!!!");
		} else {
			pw.println("@@@@@Something went wrong@@@@@");
		}
		pw.println("</body>");
		pw.println("</html>");

	}

}
