package com.khadri.jakarta.stock.servlet.viewall;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.khadri.jakarta.product.dao.ProductDao;
import com.khadri.jakarta.stock.dao.StockDao;
import com.khadri.jakarta.stock.form.ProductForm;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StockViewAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StockDao stockDao;
	private ProductDao productDao;

	public void init() throws ServletException {
		stockDao = new StockDao();
		productDao = new ProductDao();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entered into StockViewAllServlet doGet(-,-)");

		List<String> selectProductNames = productDao.selectProductNames();

		List<List<ProductForm>> listOfLists = new ArrayList<>();

		selectProductNames.stream().forEach(eachName -> {
			listOfLists.add(stockDao.selectStockByType(eachName));
		});

		PrintWriter pw = resp.getWriter();
 
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<link rel='stylesheet' type='text/css' href='styles.css'/>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<table border=1>");
		pw.println("<thead>");
		pw.println("<tr>");
		pw.println("<th>");
		pw.println("PRODUCT TYPE");
		pw.println("</th>");
		pw.println("<th>");
		pw.println("PRODUCT BRAND");
		pw.println("</th>");
		pw.println("<th>");
		pw.println("PRODUCT PRICE");
		pw.println("</th>");
		pw.println("<th>");
		pw.println("PRODUCT MODEL");
		pw.println("</th>");
		pw.println("<th>");
		pw.println("PRODUCT ARRIVED DATE AND TIME");
		pw.println("</th>");
		pw.println("</tr>");
		pw.println("</thead>");
		pw.println("<tbody>");
		listOfLists.stream().flatMap(List::stream).forEach(eachProduct -> {
			pw.println("<tr><td>" + eachProduct.getProductType() + "</td><td>" + eachProduct.getProductBrand()
					+ "</td><td>" + eachProduct.getProductPrice() + "</td><td>" + eachProduct.getProductModel()
					+ "</td><td>" + eachProduct.getArrivedDateTime() + "</td></tr>");
		});

		pw.println("</tbody>");

		pw.println("</table>");

		pw.println("</body>");
		pw.println("</html>");

	}

}
