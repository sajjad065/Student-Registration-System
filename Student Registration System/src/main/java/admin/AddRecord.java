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

import com.mysql.cj.xdevapi.Statement;


@WebServlet("/AddRecord")
public class AddRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			String first_name=request.getParameter("first_name");
			String last_name=request.getParameter("last_name");
			String email=request.getParameter("email");
			String dob=request.getParameter("dob");
			String gender=request.getParameter("gender");
			String phone=request.getParameter("phone");
			String course=request.getParameter("course");
			
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","123456789");
			System.out.println("connection established");
			//String insertQuery = "INSERT INTO Student_List (First Name, Last Name, Email, Gender,Phone) VALUES (?, ?, ?, ?, ?)";
			String insertQuery ="INSERT INTO `Login`.`Student_List` (`First Name`, `Last Name`, `Email`, `Date of Birth`, `Gender`, `Phone`,`Course`) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, dob);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, course);


            // Execute the INSERT statement
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println("insert success");
            
            // Close the resources
            preparedStatement.close();
            con.close();
            out.println("You have submitted the form successfully");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
