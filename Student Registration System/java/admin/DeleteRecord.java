/* This is admin's page where admin can delete any record they want
  There is a search option in this page where admin can search for student based on name and delete their record
 */


package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteRecord")
public class DeleteRecord extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver"); //loadinf jdbc driver
			
			//initializing jdbc variables
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			//establishing connection with database
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","123456789");
			
			response.setContentType("text/html"); //setting to display response to html format
			PrintWriter out=response.getWriter(); //getting printwriter object
			
			//displaying in html format
			out.println("<html>");
            out.println("<head><title>Student Records</title></head>");
          // out.println("<style> body { font-family: Arial, sans-serif;background-color: #f4f4f4;color: #333;  margin: 20px;padding: 20px; text-align: center;}");
         //  out.println( "h1{color:#009688;");
          // out.println("form {max-width: 600px;background-color: #fff;margin: 0 auto;padding: 20px;border-radius: 8px;  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); margin-bottom: 20px;}");
            

         // out.println("table { width: 100%;border-collapse: collapse;margin-top: 20px}");

         // out.println("table, th, td { border: 1px solid #ddd;text-align: left;}");

         // out.println( "th, td { padding: 10px;}");

          //out.println("th { background-color: #4CAF50;color: white;}");

       //   out.println("input[type='text'] {width: 80%;padding: 10px; margin: 5px 0;box-sizing: border-box;}");

        

         // out.println("input[type='submit']:hover {background-color: #c9302c;}");
          //out.println(" </style>");
            out.println("<body>");
            out.println("<h1 style='color:#009688;'>Enter student name to delete the data <h1>");
            out.println("<form method='post' action='DeleteRecord' "); 
            out.println("<label for='studentID' style='color:#009688;'>Search:</label> ");
            out.println("<input type='text' id='student' name='student'><br><br>");
            out.println("<input type='submit' name='search' value='Search' style='background-color: #4CAF50; color: white; padding: 8px 10px; border: none; border-radius: 4px; cursor: pointer;'>");
            out.println("</form>");
            
            //to get keyword to find the student information
            String keyword=request.getParameter("student");
			
            //preparing statement to get the data based on typed keyword
		    pst = con.prepareStatement("SELECT * FROM Login.Student_List WHERE First_Name LIKE '%"+keyword+"%';");		
			
			rs = pst.executeQuery(); //execute above query
			
			
            while(rs.next())
            {
            //html table to view data
            out.println("<table border='1'>");
    		out.println("<tr> <th>First Name </th> <th>Last Name</th><th>Email</th>");
    		out.println("<th>Date of Birth </th> <th>Gender</th><th>Phone</th><th>Course</th><th>Action</th></tr>");
            //getting rows from database	
            int id=rs.getInt("Id");
			String first_name=rs.getString("First_Name");
			String last_name=rs.getString("Last_Name");
			String email=rs.getString("Email");
			String dob=rs.getString("Date_of_Birth");
			String gender=rs.getString("Gender");
			String phone=rs.getString("Phone");
			String course=rs.getString("Course");
			
			//displaying the fetched rows into table
			out.println("<form action='' method='post'>");
			out.println("<input type='hidden' name='first' value='+first_name+'>");
			out.println("<input type='hidden' name='last' value='+last_name+'>");
			out.println("<input type='hidden' name='email' value='+email+'>");
			out.println("<input type='hidden' name='dob' value='+dob+'>");
			out.println("<input type='hidden' name='gender' value='+gender+'>");
			out.println("<input type='hidden' name='phone' value='+phone+'>");
			out.println("<input type='hidden' name='course' value='+course+'>");
			
			
			out.println("<tr><td>"+first_name+"</td><td>"+last_name+"</td><td>"+email+"</td><td>"+dob+"</td><td>"+gender+"</td>");
            out.println("<td>"+phone+"</td><td>"+course+"</td><td> <input type='submit' value='Delete' style='background-color: #4CAF50; color: white; padding: 8px 10px; border: none; border-radius: 4px; cursor: pointer;'></td></tr>");
            out.println("</form>");
            }
            out.println("</table> </body> </html>");
			
			
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
