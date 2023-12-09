
/*This servlet will handle the request of course drop by the students 
 * here all the courses that student can drop will be listed 
 * Fall courses can be dropped  till Septemeber, after september, student will not be able to drop the course
 * Summer courses can be dropped  only till the end of may
 * Spring courses can be dropped only within January last
 */


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
import java.time.Year;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DropCoursesList")
public class DropCoursesList extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			PrintWriter out=response.getWriter(); //getting Printwriter object
			response.setContentType("text/html"); //setting the response body as HTML
			String term = null; //initalizing term variable
			LocalDate currentDate = LocalDate.now(); //get current date
	        Month currentMonth = currentDate.getMonth(); //get current month
	        int currentYear=currentDate.getYear();  //get current year
	       
	        //just to check output in console
	        System.out.println("month is "+currentMonth);
	        System.out.println("current year is "+currentYear);
	        
	        String studentId =request.getParameter("stud_id"); //getting student id from last page
            int sid = Integer.parseInt(studentId); //converting string into integer
			 System.out.println("student id is :"+sid);//verifying the student id in console
			 
			 //if the current month is either december or january then it will set term as Spring +current year
			if(currentMonth.equals(Month.DECEMBER) || currentMonth.equals(Month.JANUARY))
			{
				 term=" Fall "+currentYear+" ";
				System.out.println("The current term is :"+term);
			}
			
			 //if the current month is either March or may then it will set term as Summer +current year
			else if(currentMonth.equals(Month.MARCH) || currentMonth.equals(Month.MAY))
			{
				term=" Summer "+currentYear+" ";
				System.out.println("The current term is :"+term);
			}
			
			 //if the current month is either August or September then it will set term as Fall +current year
			else if(currentMonth.equals(Month.AUGUST) || currentMonth.equals(Month.SEPTEMBER))
			{
			   term=" Fall "+currentYear+" ";
				System.out.println("The current term is :"+term);
			}
			
			//anything outside the above mentioned month, student will not be able to drop the courses.
			//it is because student will be at the middle of the semester
			else
			{
				out.println("currently you cannot drop any courses");
				System.out.println("currently you cannot drop any courses");
				return;
			}
			
			System.out.println("The current term is :"+term); //DISPLAY THE CURRENT TERM IN CONSOLE
			Class.forName("com.mysql.jdbc.Driver"); //LOADING JDBC DRIVER
			
			//INITIALIZING JDBC VARIABLES
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			//ESTABLISHING CONNECTION WITH THE DATABASE
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","123456789");
			
			//PREPRARING SELECT STATEMENT BASED ON TERM AND STUDENT ID
			//this select statement will retreive all the rows based on currentt Term selected and student id 
			//if term is not within the above mentioned month then student will not be able to drop the course
			pst = con.prepareStatement("SELECT * FROM Login.Courses_Grade WHERE Term=? AND Student_Id=?");
			pst.setString(1, term); 
			pst.setInt(2, sid); 
			rs= pst.executeQuery();
			
			
			//DISPLAYING THE COURSES THAT CAN BE DROPPED IN HTML FORMAT
			out.println("<html> <head>  </head> <body>");
			out.println("<table>");
		while(rs.next())
		{
			System.out.println("inside result set");
			String course=rs.getString("Courses");
			out.println("<form method='post' action='DropCourse' style='margin-bottom: 10px;'>");
			out.println("<tr>");
			out.println("<th align='left'>" + course );
			out.println(" </th>");
			out.println("<th>");
			out.println("<input type='hidden' name='course' value='"+course+"'>");
			out.println("</th>");
			out.println("<th>");
			out.println("<input type='hidden' name='sid' value='"+sid+"'>");
			out.println("</th>");
			out.println("<td align='right'>");
			out.println("<input type='submit' value='Drop' style='background-color: #4CAF50; color: white; padding: 8px 10px; border: none; border-radius: 4px; cursor: pointer;'>");
			out.println("</td> </tr>");
			out.println("</form> ");
			
		}
		out.println("</table>");
		out.println("</body> </html>");
		
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
