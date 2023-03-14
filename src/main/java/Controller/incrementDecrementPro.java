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


@WebServlet("/incrementDecrementPro")
public class incrementDecrementPro extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
	    String action=request.getParameter("action");
	    int id=Integer.parseInt(request.getParameter("id"));
	    
	    ArrayList<cart> cart_list=(ArrayList<cart>) request.getSession().getAttribute("cart-list");
	    
	    if(action!=null && id>=1) {
	    	if (action.equals("inc")) {
				for(cart c :cart_list)	{
					if(c.getId()==id) {
						int quantity=c.getQuantity();
						quantity++;
						c.setQuantity(quantity);
						response.sendRedirect("Views/cart.jsp");    
					}
				}  
			} 
	    	if (action.equals("dec")) {
				for(cart c :cart_list)	{
					if(c.getId()==id && c.getQuantity()>1) {
						int quantity=c.getQuantity();
						quantity--;
						c.setQuantity(quantity);
						   break;
					}
				} 
				response.sendRedirect("Views/cart.jsp"); 
			}  
	    	
	    }
	    else {
	    	response.sendRedirect("Views/cart.jsp"); 
	    }    
	    
	}
   
}
