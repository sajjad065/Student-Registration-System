/* This page displays all the record of the database
 * Admin will be able to search the record of the student in this page
 */


package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
		
		Class.forName("com.mysql.jdbc.Driver"); //loading jdbc driver
		
		//initialzing jdbc variables
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		//establishing the connection with the database
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","123456789");
		
		
			//preparing select statement	
			pst = con.prepareStatement("SELECT * FROM Login.Student_List");		
			
			//executing the statement
			rs = pst.executeQuery();
			          
			response.setContentType("text/html"); //setting response as html format
			PrintWriter out=response.getWriter(); //getting writer object to write in a page
			
			//displaying the record in html format
			out.println("<html>");
            out.println("<head><title>Student Records</title>");
            
            out.println("<style>");
            out.println("table {");
            out.println("  border-collapse: collapse;");
            out.println("  width: 100%;");
            out.println("}");

            out.println("th, td {");
            out.println("  border: 1px solid #dddddd;");
            out.println("  text-align: left;");
            out.println("  padding: 8px;");
            out.println("}");

            out.println("tr:nth-child(even) {");
            out.println("  background-color: #f2f2f2;");
            out.println("}");

            out.println("form {");
            out.println("  margin-top: 20px;");
            out.println("}");

            out.println("label {");
            out.println("  font-weight: bold;");
            out.println("}");
           
            out.println("</style>");
            out.println("</head>");
            
            out.println("<body>");
            
           
            out.println("<form method='post' action='View' "); 
            out.println("<label for='studentID'>Search:</label> ");
            out.println("<input type='text' id='student' name='student'>"); //text field  for search keyword
            out.println("<input type='submit' name='search' value='Search' style='padding: 8px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer;'>");
            out.println("</form>");
            out.println("<br> <br> <br><h1>Student Record <h1>");
            
            out.println("<table border='1'>");
            out.println("<tr><th>Id</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Date of Birth</th><th>Gender</th><th>Phone</th><th>Course</th>"
            		+ "<th>Student Id</th></tr>");
            
            System.out.println("table started");
            while(rs.next())
			{
            	//getting each row at a time
				int id=rs.getInt("Id");
				String first_name=rs.getString("First_Name");
				String last_name=rs.getString("Last_Name");
				String email=rs.getString("Email");
				String dob=rs.getString("Date_of_Birth");
				String gender=rs.getString("Gender");
				String phone=rs.getString("Phone");
				String course=rs.getString("Course"); 
				String sid=rs.getString("Student_Id"); 
				
				
				//displaying the console for testing the value
				System.out.println("ID: " + id);
                System.out.println("First Name: " + first_name);
               System.out.println("Last Name: " + last_name);
                System.out.println("email: " + email);
                System.out.println("gender: " + gender);
                System.out.println("phone: " + phone);
                System.out.println("course: " + course); 
                System.out.println("course: " + sid); 
               
                out.print("<tr>");
                out.print("<td>");
                out.println(id);
                out.print("</td>");
                
                
               out.print("<td>");
                out.print(first_name);
                out.print("</td>");
                
                out.print("<td>");
                out.print(last_name);
                out.print("</td>");
                
                out.print("<td>");
                out.println(email);
                out.print("</td>");
                
                out.print("<td>");
                out.print(dob);
                out.print("</td>");
                
                out.print("<td>");
                out.print(gender);
                out.print("</td>");
                
                
                out.print("<td>");
                out.print(phone);
                out.print("</td>");
                
                out.print("<td>");
                out.print(course);
                out.print("</td>"); 
                
                out.print("<td>");
                out.print(sid);
                out.print("</td>"); 
                
                out.print("</tr>");
                
			}
			out.print("</table>");
			
			// The following statement for Searching the data
			String keyword=request.getParameter("student");
			
			//this statament will fetch the data based on the keyword types by the admin
		    pst = con.prepareStatement("SELECT * FROM Login.Student_List WHERE First_Name LIKE '%"+keyword+"%';");		
			System.out.println("success");
			 rs = pst.executeQuery();
			 
			 List <Student> resultList = new ArrayList<Student>(); //creating the array list 

			 String search=request.getParameter("search");
			 if(search!=null) {
			    while (rs.next()) {
				int id=rs.getInt("Id");
				String first_name=rs.getString("First_Name");
				String last_name=rs.getString("Last_Name");
				String email=rs.getString("Email");
				String dob=rs.getString("Date_of_Birth");
				String gender=rs.getString("Gender");
				String phone=rs.getString("Phone");
				String course=rs.getString("Course");
				System.out.println("lets check for phn");
				System.out.println(phone);

				
                
				
				Student student = new Student(id,first_name,last_name,email,dob,gender,phone,course);
                resultList.add(student);
			    }
			    request.setAttribute("resultList", resultList);
			// Forward to the JSP page
			RequestDispatcher dispatcher = request.getRequestDispatcher("searchresult.jsp");
			dispatcher.forward(request, response);
			 }
			
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
