package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

@WebServlet("/LoginServ")
public class LoginServ extends javax.servlet.http.HttpServlet {
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException  {
		
		try {
			System.out.println("started");
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("driver loaded");
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","123456789");
		System.out.println("con success");
		
		String username=((ServletRequest) request).getParameter("username");
		String password=((ServletRequest) request).getParameter("password");
		
		
			pst = con.prepareStatement("SELECT * FROM Login_Detail WHERE username=? AND password=? ");
		
		
			pst.setString(1,  username);
		
		
			pst.setString(2,  password);
		
		
		
			rs = pst.executeQuery();
		
		
		
	   
			
		
			if(rs.next())
			{
			  
			((HttpServletResponse) response).sendRedirect("Registration.jsp");
				
			}
			else
			{
				((HttpServletResponse) response).sendRedirect("Error.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	
}
}
