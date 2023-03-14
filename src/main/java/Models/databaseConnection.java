package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection{
	
private static Connection connection = null;

 public static Connection getConnetion() throws ClassNotFoundException, SQLException {
    if (connection==null) {
     Class.forName("com.mysql.cj.jdbc.Driver");
    connection = DriverManager.getConnection ("jdbc:mysql://localhost:3306/footcapproject","root","");
     System.out.print("connected");
}  
    else { 
    	System.out.println("error");
    }
     return connection;
}  
}  

	    
	    
	     	
	


