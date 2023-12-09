/* This servlet code is responsible for helping to updating the record of the student
 * It displays all the student information in a text field and give them option to edit those information and update them. 
 */




package Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Update")
public class Update extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try {
			
			
			String sid=request.getParameter("stud_id"); //getting student id
			Class.forName("com.mysql.jdbc.Driver"); //loading JDBC driver
			System.out.println("driver loaded");
			
			//initalizing the JDBC variables
			Connection con = null; 
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			//establishing connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","123456789");
			
			//preparing select statement to retreive all the record of a student (individual student)
				pst = con.prepareStatement("SELECT * FROM Login.Student_List WHERE Student_Id=?");		
				pst.setString(1, sid);
				//executing query
				rs = pst.executeQuery();
				response.setContentType("text/html"); //setting the response content to html
				PrintWriter out=response.getWriter(); //getting printwriter object
				
				//displaying in HTML format by adding CSS 
				out.println("<html>");
	            out.println("<head><title>Student Records</title>");
	            out.println("<style>");
	            out.println("table { border-collapse: collapse; width: 100%; }");
	            out.println("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }");
	            out.println("h1 { color: #333; }");
	            out.println("form { margin-top: 20px; }");
	            out.println("</style>");
	            out.println("</head>");
	            out.println("<body>");
	            out.println("<h1 style='text-align: center;'>Update Record <h1> <br>");
	            
	            //creating html table to display information
	            out.println("<table border='0'>");
	            out.println("<tr><th>First Name</th><th>Last Name</th><th>Email</th><th>Date of Birth</th><th>Gender</th><th>Phone</th><th>Course</th></tr>");
				while(rs.next())
				{
					//getting the row from the database
					int id=rs.getInt("Id");
					String first_name=rs.getString("First_Name");
					String last_name=rs.getString("Last_Name");
					String email=rs.getString("Email");
					String dob=rs.getString("Date_of_Birth");
					String gender=rs.getString("Gender");
					String phone=rs.getString("Phone");
					String course=rs.getString("Course"); 
					
					//displaying in the console to check the retreived data
					System.out.println("ID: " + id);
	                System.out.println("First Name: " + first_name);
	               System.out.println("Last Name: " + last_name);
	                System.out.println("email: " + email);
	                System.out.println("gender: " + gender);
	                System.out.println("phone: " + phone);
	                System.out.println("course: " + course); 
	                
	                //displaying the students information in the form field 
	                out.println("<form method='post' action='UpdateRecord'>");
	                out.println("<tr> <td>");
	                out.println("<input type='text' name='first_name' value='"+first_name+"'> ");
	                out.println("</td><td>");
	                out.println("<input type='text' name='last_name' value='"+last_name+"'> ");
	                out.println("</td><td>");
	                out.println("<input type='text' name='email' value='"+email+"'>");
	                out.println("</td><td>");
	                out.println("<input type='text' name='dob' value='"+dob+"'>");
	                out.println("</td><td>");
	                out.println("<input type='text' name='gender' value='"+gender+"'>");
	                out.println("</td><td>");
	                out.println("<input type='text' name='phone' value='"+phone+"'>");
	                out.println("</td><td>");
	                out.println("<input type='text' name='course' value='"+course+"'>");
	                out.println("</td><td> </tr> </table>");
	               
	                //this hidden field will help to retrieve the student id in the next page 
	                //it will not be displayed in the page
	                //update can be made as per the student id
	                out.println("<input type='hidden' name='stud_id' value='"+sid+"'>");
	                out.println("<br> <input type='submit' value='update' style='background-color: #4CAF50; color: white; padding: 8px 10px; border: none; font-size: 16px;border-radius: 4px; cursor: pointer;'>");
	                out.println("</form>");
	                
				}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}

}
