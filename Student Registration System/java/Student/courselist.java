package Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//This page handles the request of course selection based on subject and term selected by the student
//This page will display the list of courses that student can add based on subject and term

@WebServlet("/courselist")
public class courselist extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		try {
		
		Class.forName("com.mysql.jdbc.Driver"); //loading JDBC driver for database connection
         
		//initializing the JDBC variables
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		//establishing connection with the database
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","123456789");
		
		
		String stud_id=request.getParameter("stud_id");//Retrieving student id number,
		String subject=request.getParameter("subject"); //Retrieving subject selected by student for adding course
		String term=request.getParameter("term");  //Retrieving term selected by student for adding course
		System.out.println("here it is "+term);
		
		 //select query to select all the rows that matches the subject and term selected by the student 
			pst = con.prepareStatement("SELECT * FROM Login.Courses WHERE Subject=? AND Term=?");
			
			//setting the value of subject and term to select the rows
			pst.setString(1, subject); 
			pst.setString(2, term); 
			rs = pst.executeQuery(); //executing the above select statement
			PrintWriter out=response.getWriter();//getting PrintWriter object to write text as HTML
			response.setContentType("text/html"); //setting response as text or HTML
			
			//displaying text in HTML format
			out.println("<html>  <head> ");
			out.println("<style>");
			out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 10px; padding: 10px; }");
			out.println("input[type='submit'] { background-color: #4CAF50; color: white; padding: 12px 15px; border: none; border-radius: 4px; cursor: pointer; font-size: 12px; }");
			out.println("input[type='submit']:hover { background-color: #45a049; }");
			out.println("</style>");
			out.println("</head> <body>");
			
			//getting the data of the above select statement
			while (rs.next()) {
				 String courses=rs.getString("Courses"); //getting value course field one at a time based on above select statement
				 
				 //displaying list of courses that student can add 
					out.println(courses);
					out.println("<form action='courseAdd' method='post'>");
					out.println("<input type='submit' value='ADD'>");
					out.println("<input type='hidden' name='cour' size='50' value='" + courses + "'>");
			        
					//this is hidden field to store the value of the student_id and term
					//it wont be displayed in the page
					out.println("<input type='hidden' name='stud_id'  value=' "+stud_id+ " '>");
					out.println("<input type='hidden' name='term'   value=' "+term+ " '>");
					
					out.println("<br>  </form>");
	                
	            }
		
		
		} 
		
		//catch statements to handle possible exceptions that can be thrown by the try block above
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

}
