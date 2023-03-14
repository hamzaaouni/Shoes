package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;



import Models.cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveCart
 */
@WebServlet("/RemoveCart")
public class RemoveCart extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		
		String id=request.getParameter("id");
		
		if(id!=null) {
			ArrayList<cart> cart_list=(ArrayList<cart>) request.getSession().getAttribute("cart-list");
			if(cart_list!=null) {
				for(cart c:cart_list) {
					if(c.getId()==Integer.parseInt(id)) {
						cart_list.remove(cart_list.indexOf(c));
						break;
					}
				}
				response.sendRedirect("Views/cart.jsp");
			}
		}else {
			response.sendRedirect("Views/cart.jsp");  
		}
	}

}
