<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Courses Selection</title>
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        form {
            max-width: 400px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<%
String email=request.getParameter("email");
System.out.println(email);
String term=request.getParameter("term");
System.out.println("The term is: " +term);
String stud_id=request.getParameter("stud_id");
System.out.println("The student id is: " +stud_id);
%>
 <form action="courselist" method="post">
        <label for="subject">Select Subject:</label>
        <select id="subject" name="subject" required>
            <option value="Accounting">Accounting</option>
            <option value="Mathematics">Mathematics</option>
            <option value="Computer Science">Computer Science</option>
            <option value="Engineering">Engineering</option>     
        </select>
        <input type="hidden" value="<%=term%>" size='100' name="term">
        <input type="hidden" value="<%=email%>" name="email">
         <input type="hidden" value="<%=stud_id%>" name="stud_id">
          <input type="submit" value="Submit">
          </form>

</body>
</html>