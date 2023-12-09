package contactMessage;

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


@WebServlet("/contact")
public class contact extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		//try block to handle possible exception that can be thrown by the code
		
		try {
		
			PrintWriter out=response.getWriter();
        	response.setContentType("text/html");
			
		Class.forName("com.mysql.jdbc.Driver"); //loading the JDBC driver
		
		//Initializing JDBC variable to null
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		//Establishing connection to the database
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","123456789");
		
		//Retrieving  name,email and message
		String name= (String) request.getParameter("name");
		String email= (String) request.getParameter("email");
		String message= (String) request.getParameter("message");
		
		
		//writing the insert query to insert the student's data into database's table
		String insertQuery ="INSERT INTO `Login`.`contact` (`Name`, `Email`, `Message`) VALUES (?,?,?)";

        PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
        
        //setting value to respective field in database table
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, message);
        // Execute the INSERT statement
        int rowsInserted = preparedStatement.executeUpdate();
        if(rowsInserted>0)
        {
        System.out.println("insert success");
        out.println("Mesage sent Successfully");
        
        }
        else
        {
        	System.out.println("error");
        	out.println("error");
        }
	}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	

}
