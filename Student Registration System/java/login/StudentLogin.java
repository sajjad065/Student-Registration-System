package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//This page is for handling the login request from Student
//if student id number matches with password then it will redirect into student's page otherwise it will display error message
@WebServlet("/StudentLogin")
public class StudentLogin extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		
		Class.forName("com.mysql.jdbc.Driver"); //loading driver
		
		//declaring variables as null initially
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		//establishing connection with the database 
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","123456789");
		
		
		//Retrieving student's input as student_id and password
		String student_id=((ServletRequest) request).getParameter("username");
		String password=((ServletRequest) request).getParameter("password");
		
		//select statement for retrieving the rows from the database's table where student id and password matches
			pst = con.prepareStatement("SELECT * FROM Login.StudentLogin_Detail WHERE Student_Id=? AND Password=?");
		
		//setting the respective column with respective input data
			pst.setString(1,  student_id);
		
		
			pst.setString(2,  password);
		
		
		
			rs = pst.executeQuery(); //executing the query
			
		//fetching the data set from the database
			if(rs.next())
			{
				String email=rs.getString("Email"); //getting email from the database
				String sid=rs.getString("Student_Id"); //getting student id from the database
			  request.setAttribute("email",email); //setting attribute to access in the next page
			  request.setAttribute("stu_id", sid); //setting student id to access in the next page
			  
			  //Dispatching the request into other page (StudentPage)
			  RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentPage"); 
			  dispatcher.forward(request, response);
			
				
			}
			
			//if the input student id and password does not match, display error page
			else
			{
				request.setAttribute("errorMessage", "Incorrect username or password");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		//catch statement to handle the possible exception thrown by the try block
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	
}

	}


