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





@WebServlet("/UpdateRecord")
public class UpdateRecord extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve data from the form
       
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        System.out.println("checking email:"+email);
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String course = request.getParameter("course");
       //hidden field 
        String sid = request.getParameter("stud_id");
  
        // JDBC code to update the data in the database
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Load the JDBC driver and establish a connection
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Login", "root", "123456789");

            // Update query
            String updateQuery = "UPDATE  Login.Student_List SET First_Name=?, Last_Name=?, Email=?, Date_of_Birth=?, Gender=?, Phone=?, Course=? WHERE Student_Id=?";
            preparedStatement = (PreparedStatement) connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, dob);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, course);
            preparedStatement.setString(8, sid);

            
            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                out.println("Update successful!");
            } else {
                out.println("Update failed!");
            }
        } catch (Exception e) {
        	System.out.println(e);
            
        } 
        }
    
}


