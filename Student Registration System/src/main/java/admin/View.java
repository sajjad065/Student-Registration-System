package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/View")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		System.out.println("started");
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("driver loaded");
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","123456789");
		System.out.println("con success");
		
			pst = con.prepareStatement("SELECT * FROM Student_List");		
			rs = pst.executeQuery();
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.println("<html>");
            out.println("<head><title>Student Recordsy</title></head>");
            out.println("<body>");
            out.println("<h1>Student Record <h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>Id</th><th>Name</th><th>Age</th><th>Gender</th><th>Email</th><th>Phone</th><th>Department</th></tr>");
			while(rs.next())
			{
				int id=rs.getInt("Id");
				String name=rs.getString("Name");
				int age=rs.getInt("Age");
				String gender=rs.getString("Gender");
				String email=rs.getString("Email");
				String phone=rs.getString("Phone");
				String depart=rs.getString("Department");
				System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                out.print("<tr>");
                out.print("<td>");
                out.println(id);
                out.print("</td>");
                
                
                out.print("<td>");
                out.print(name);
                out.print("</td>");
                
                
                
                out.print("<td>");
                out.println(age);
                out.print("</td>");
                
                out.print("<td>");
                out.print(gender);
                out.print("</td>");
                
                
                out.print("<td>");
                out.print(email);
                out.print("</td>");
                
                out.print("<td>");
                out.print(phone);
                out.print("</td>");
                
                out.print("<td>");
                out.print(depart);
                out.print("</td>");
                
                out.print("</tr>");
                
			}
			out.print("</table>");
					
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	}

}
