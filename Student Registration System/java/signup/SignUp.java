/* This code handles the request of sign-up.  Student will be able to sign-up once their admission is accepted and they receive student_id number
  if student have already sign-up, it will display error message
  if student don't have student_id that is student is not admitted to university, then it will display error message
  if student get admitted to university and have student_id they will be able to sign up and get access to university portal      
  note: student receive student_id number in their email once they are admitted to university      
       */


package signup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		String sid = request.getParameter("username"); //getting student id from user as input
        String password = request.getParameter("password"); //getting password from user as input
        String email = request.getParameter("email"); //getting email from user as input

        // Database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/Login";
        String dbUser = "root";
        String dbPassword = "123456789";

        //try and catch is used to handle possible exception that can be thrown by the code below
        
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

           //creating connection
              Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
              
              //checking if student have already sign-up
              String checkQuery = "SELECT COUNT(*) FROM Login.StudentLogin_Detail WHERE Student_Id=? OR Email=?";
               PreparedStatement checkStatement = connection.prepareStatement(checkQuery) ;
                  checkStatement.setString(1, sid);
                  checkStatement.setString(2, email);
                  

                  // Execute the select statement
                  ResultSet resultSet = checkStatement.executeQuery();
                  resultSet.next();
                  int count = resultSet.getInt(1);

                  //if student have already signed up with student_id or email then it will display error
                  if (count > 0) {
                	// Email already exists, set an attribute to indicate the error
                      request.setAttribute("emailError", "Email or Sid already exists. Please use a different one.");
                      // Forwarding the request to the signup.jsp page
                      request.getRequestDispatcher("/Signup.jsp").forward(request, response);
                      return;
              
                  }
              
                  //checking whether student's id number is in the database or not
                  //admitted student will have their student id number in the database
              String selectQuery="SELECT * FROM Login.Student_List WHERE Student_Id=?" ;
              PreparedStatement selectStatement=connection.prepareStatement(selectQuery);
              selectStatement.setString(1,sid);
              ResultSet rSet = selectStatement.executeQuery();
              rSet.next();

              //if the inputed student id is not in the database then it will display error message .
              //it only happen when student is not admitted to university and tries to sign up with some random student id number
              if (!(rSet.next())) {
            	  
            	out.println("You need to get admitted to the university first");
            	return;
              }
              
               //if student tries to sign up with valid student id then the sign up will be successful
                // Insert data into the  table
                String insertQuery = "INSERT INTO Login.StudentLogin_Detail (Student_Id, Password, Email) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(insertQuery); 
                    preparedStatement.setString(1, sid);
                    preparedStatement.setString(2, password);
                    preparedStatement.setString(3, email);

                    // Execute the insert statement
                    int rowsAffected = preparedStatement.executeUpdate();

                    // Check if the data was successfully inserted
                    if (rowsAffected > 0) {
                        
                        out.println("<html><body><h2>Signup successful!</h2></body></html>");
                    } else {
                        
                        out.println("<html><body><h2>Registration failed. Please try again.</h2></body></html>");
                    }
                
            }
        //catch block to handle any exception thrown by the try block
         catch (Exception e) {
            e.printStackTrace();
		
         }
		
		
	}

}
