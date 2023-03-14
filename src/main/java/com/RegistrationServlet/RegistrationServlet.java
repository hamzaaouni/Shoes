package com.RegistrationServlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


//@WebServlet("/register")
@WebServlet(name = "RegistrationServlet", urlPatterns = { "/RegistrationServlet" }, initParams = {
@WebInitParam(name = "registration", value = "/registration.jsp") })
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		 throws ServletException, IOException {
			String urname = request.getParameter("name");
			String uremail = request.getParameter("email");
			String urpass = request.getParameter("pass");
			String urcontact = request.getParameter("contact");
			Connection conn = null;
			//RequestDispatcher disp = null;
			
			try {
				if(urname == "" && uremail == "" && urpass == "" && urcontact == ""){
					//disp = request.getRequestDispatcher("registration.jsp");
					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Please fill in all required fields');");
					out.println("location='/Footcap/Views/registration.jsp';");
					out.println("</script>");
					//response.sendRedirect("login.jsp");
					
				}
				else if(urname == "" || uremail == "" || urpass == "" || urcontact == "") {
					//disp = request.getRequestDispatcher("registration.jsp");
					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Field cannot be empty.');");
					out.println("location='/Footcap/Views/registration.jsp';");
					out.println("</script>");
					//response.sendRedirect("login.jsp");
					
				}else {
					Class.forName("com.mysql.cj.jdbc.Driver");//?useSSL=false
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/register?useSSL=false","root",""); 
					PreparedStatement pst = conn.prepareStatement("INSERT INTO users(urname,uremail,urpass,urcontact) VALUES(?,?,?,?) ");
					
					pst.setString(1, urname);
					pst.setString(2, uremail);
					pst.setString(3, urpass);
					pst.setString(4, urcontact);
					
					int rowCount=pst.executeUpdate();
				
					if(rowCount>0) {
						//disp = request.getRequestDispatcher("/Views/login.jsp");
						request.setAttribute("status", "success");
						PrintWriter out = response.getWriter();
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Account Created Seccessfully!');");
						out.println("location='/Footcap/Views/login.jsp';");
						out.println("</script>");
					}else {
						request.setAttribute("status", "failed");
					}
						//disp.forward(request, response);
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					 if (conn != null) {
					        conn.close();
					   }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
 	}
}
