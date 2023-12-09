
/* This servlet page is responsible to viewing all the registered courses of student
 * It displays the courses registered, Grade and Term
 */



package Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.Driver;

@WebServlet("/RegisteredCourses")
public class RegisteredCourses extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// JDBC database URL, username, and password 
        String jdbcUrl = "jdbc:mysql://localhost:3306/Login";
        String dbUser = "root";
        String dbPassword = "123456789";

        // JDBC variables for establishing connection
        try 
        {
        	Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
        
            // Retrieve student ID from the request parameter
            String studentId =request.getParameter("stud_id"); //getting student id from last page
            int sid = Integer.parseInt(studentId); //converting string into integer
            System.out.println("student id is :"+studentId);
            // SQL query to retrieve student information based on Student ID
            String sql = "SELECT * FROM Login.Courses_Grade WHERE Student_Id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, sid);
                ResultSet resultSet = preparedStatement.executeQuery();
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html");
                    out.println("<html>");
                    out.println("<head> ");
                    out.println("<style>");
                    out.println("table { border-collapse: collapse; width: 50%; margin: 20px; }");
                    out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
                    out.println("h2 { text-align: left;margin-left: 20px; }");
                    out.println("th { background-color: #f2f2f2; }");
                    out.println("</style>");
                    out.println("<title> Registered Course </title> </head>");
                    out.println("<body>");
                    out.println("<h2>Registered Courses</h2>");
                    out.println("<table border='1'>");
                    out.println("<tr><th> Courses </th> <th> Grade </th> <th> Term </th> </tr>");
                    while (resultSet.next()) {
                    	//getting rows
                    	String course=resultSet.getString("Courses");
                    	String grade=resultSet.getString("Grade");
                    	String term=resultSet.getString("Term");
                    	
                        // Display student information
                        out.println("<tr>");
                        out.println("<td>");
                        out.println(course);
                        out.println("</td>");
                        out.println("<td>");
                        out.println(grade);
                        out.println("</td>");
                        out.println("<td>");
                        out.println(term);
                        out.println("</td>");
                        out.println("</tr>");
                        
                        
                    } 
                    out.println("</table></body></html>");
                }
            
         catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions appropriately 
		
	}
	}
}
	
	
	
	
