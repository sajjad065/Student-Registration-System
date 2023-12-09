/* This codeows will drop the courses that student select and update that in database
*/

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


@WebServlet("/DropCourse")
public class DropCourse extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try {
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			Class.forName("com.mysql.jdbc.Driver"); //loadind JDBC driver
			
			//initializing jdbc variable to null
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			//establishing connection to my database named "Login"
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","123456789");
			
			
			String studentId= (String) request.getParameter("sid");  //getting student id from last page
	            int sid = Integer.parseInt(studentId); //converting string into integer
			System.out.println("student id is :"+sid);
			String course= (String) request.getParameter("course");
			System.out.println(course);
			
			pst = con.prepareStatement("DELETE FROM Login.Courses_Grade WHERE Student_Id = ? AND Courses = ?");
			pst.setInt(1, sid);
			pst.setString(2, course);
			int rowsAffected = pst.executeUpdate();
		if(rowsAffected>0) {
		 out.println("You have dropped course successfully");
		}
		else
		{
			out.println("Error in dropping the course");
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
