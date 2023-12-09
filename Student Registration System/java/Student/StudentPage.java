package Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//This page handles the request after student successfully logged in with their correct student id and password
//This page will help in retrieving and storing all the information of the student
//All the student's information are stored  with their respective attributes 
// The stored information are forwarded to Stud.jsp page to display it

@WebServlet("/StudentPage")
public class StudentPage extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//try block to handle possible exception that can be thrown by the code
				
			try {
			
			Class.forName("com.mysql.jdbc.Driver"); //loading the JDBC driver
			
			//Initializing JDBC variable to null
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			//Establishing connection to the database
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","123456789");
			
			//Retrieving student's email and student id
			String email= (String) request.getAttribute("email");
			String sid= (String) request.getAttribute("stu_id");
			
			
			//Select query to fetch student information based on their student_id
				pst = con.prepareStatement("SELECT * FROM Login.Student_List WHERE Student_Id='"+sid+"';");		
				
				//executing the above select query
				rs = pst.executeQuery();	
				
				//this code will fetch the result set
				while(rs.next()) {
					
				//retrieving row from the database 
				String first_name=rs.getString("First_Name");
				String last_name=rs.getString("Last_Name");
				String email1=rs.getString("Email");
				String dob=rs.getString("Date_of_Birth");
				String gender=rs.getString("Gender");
				String phone=rs.getString("Phone");
				String course=rs.getString("Course"); 
				String student_id=rs.getString("Student_Id");
				
				//setting the above value to attributes to forward the data into the next page
				request.setAttribute("fname",first_name);
				request.setAttribute("lname",last_name);
				request.setAttribute("email",email1);
				request.setAttribute("dob",dob);
				request.setAttribute("gender",gender);
				request.setAttribute("phone",phone);
				request.setAttribute("course",course);
				request.setAttribute("stud_id",student_id);
				
				
				}
				
				//redirecting to new page named Stud.jsp 
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Stud.jsp");
				dispatcher.forward(request, response);
				
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

