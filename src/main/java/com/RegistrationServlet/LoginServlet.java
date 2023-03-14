package com.RegistrationServlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Servlet implementation class login
 */

@WebServlet(name = "LoginServlet", urlPatterns = { "/LoginServlet" }, initParams = {
@WebInitParam(name = "login", value = "/login.jsp") })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		String urname = request.getParameter("username");	
		String urpass = request.getParameter("password");
		HttpSession session =request.getSession();
		//RequestDispatcher dis = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//jdbc:mysql://localhost:3306/javabase
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/register?useSSL=false","root","");
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM users WHERE urname = ? AND urpass = ?");
			pst.setString(1, urname);
			pst.setString(2, urpass);
			ResultSet r = pst.executeQuery();
			if(r.next()) {
				//request.setAttribute("status", "success");
				
				session.setAttribute("name", r.getString("urname")); 
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Logged in successfully!');");
				//out.println("urname!=null;");
				//out.println("alert('Sorry','Wrong username or password!','error');");
				out.println("location='/Footcap/';");
				out.println("</script>");
				//dis = request.getRequestD
							
				
				//dis = request.getRequestDispatcher("/Views/index.jsp");
			}else {
				//request.setAttribute("status", "failed");
				//PrintWriter out = response.getWriter();
				
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Wrong username or password!');");
				//out.println("alert('Sorry','Wrong username or password!','error');");
				out.println("location='/Footcap/Views/login.jsp';");
				out.println("</script>");
				//dis = request.getRequestDispatcher("/Views/login.jsp");
			}
			response.setContentType("text/html");
			//dis.forward(request, response);
		} catch (Exception e) {
			
		}
				
	}
}
