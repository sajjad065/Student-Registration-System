package Student;

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

//This page will insert the data into Course_Grade table.
//it will insert courses selected or added by student to the Courses_Grade table

@WebServlet("/courseAdd")
public class courseAdd extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//try block to handle possible exception that can be thrown by the below code
		
		try {
			
			String stud_id=request.getParameter("stud_id"); //getting student_id
			String course=request.getParameter("cour"); //getting course selected by student to add
			String term=request.getParameter("term"); //getting term selected by student to add
			System.out.println("term is :"+term);
			Connection con = null; //initializing connection variable
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","123456789"); //connecting to database
			
			//creating insert statement to insert the courses selected by student into database
			String insertQuery ="INSERT INTO `Login`.`Courses_Grade` (`Student_Id`, `Courses`,`Term`) VALUES (?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
	        preparedStatement.setString(1, stud_id);
	        preparedStatement.setString(2, course);
	        preparedStatement.setString(3, term);
			
			
			 // Execute the INSERT statement
            int rowsInserted = preparedStatement.executeUpdate();
            
            
            // Close the resources
            preparedStatement.close();
            con.close();
            PrintWriter out=response.getWriter();
    		out.println("you sucessfully added "+course);
    		
            	
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		
		
		
		
	}

}
