/* This page handles the adding record of the student in the database of the university

 * After the successful admission to the university, admin will add the students detail in this database
 * After adding record, student will get email with their student id .
 * They can use the student_id and sign up for their student portal
 * 
 */


package admin;

import java.awt.color.CMMException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import java.util.Properties;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.Inet4Address;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.xdevapi.Statement;


@WebServlet("/AddRecord")
public class AddRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			PrintWriter out=response.getWriter(); //getting printwriter object
			response.setContentType("text/html"); //setting content type as html to write in a html format
			
			//getting information from the registration form fields
			String first_name=request.getParameter("first_name");
			String last_name=request.getParameter("last_name");
			String email=request.getParameter("email");
			String dob=request.getParameter("dob");
			String gender=request.getParameter("gender");
			String phone=request.getParameter("phone");
			String course=request.getParameter("course");
			String studentId = generateStudentId();
			System.out.println(studentId);
			
			Class.forName("com.mysql.jdbc.Driver"); //loading jdbc driver
			
			//establishing connection with the database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","123456789");
			
            //writing the insert query to insert the student's data into database's table
			String insertQuery ="INSERT INTO `Login`.`Student_List` (`First_Name`, `Last_Name`, `Email`, `Date_of_Birth`, `Gender`, `Phone`,`Course`,`Student_Id`) VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            
            //setting value to respective field in database table
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, dob);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, course);
            preparedStatement.setString(8, studentId);


            // Execute the INSERT statement
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println("insert success");
            
            
         //Calling SendEmail method to set up email configuration to send email with the generated student ID
            try {
				sendEmail(email, studentId);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            // Close the resources
            preparedStatement.close();
            con.close();
            out.println("You have submitted the form successfully"); //displaying success text after successful registration
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	//email configuration code
	
	private void sendEmail(String toEmail, String studentId) throws MessagingException {

		
		System.out.println("email is :"+toEmail);
		System.out.println("ID is :"+studentId);
		
        // Email configuration
        String host = "smtp.gmail.com"; //  SMTP server of email provider
        final String username = "sajjad.9806781778@gmail.com"; // email address
        final String password = "pqky rpts dimp toiz"; // email password(app generated email password)

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.port", "587");

        
        
        javax.mail.Session session = javax.mail.Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
        	MimeMessage message = new MimeMessage(session);
        	message.setFrom(new InternetAddress(username));
        	message.addRecipient(RecipientType.TO, new InternetAddress(toEmail));
        	message.setSubject("Your Student ID"); //subject of the email
        	message.setText("Congratulations on you admission : Your student ID is: " + studentId); //actual email that will be sent to student
              
              
            // Send the message
            Transport.send((javax.mail.Message) message);
        } catch (MessagingException e) {
            // Handle messaging exception
            throw new MessagingException("Error sending email", e); //displaying error if there is any error in sending email
        }
    }

    
	
	

	private String generateStudentId() {
        // Generate a random 5-digit student ID
        Random random = new Random();
        int randomId = 10000 + random.nextInt(90000);
        return Integer.toString(randomId);
    }

}
