<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Page</title>
<style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        h1 {
            color: #333;
        }

       

        form {
            margin-top: 15px;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }
        th, td {
        padding: 10px;
        text-align: left; /* Align <th> to the left and <td> to the right */
    }

   

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>



</head>
<body>
<div>
<h1> Welcome ${fname} </h1>
<table>
<tr>
<th> First Name: </th> 
<td> ${fname} </td>
</tr>

<tr>
<th> Last Name: </th> 
<td> ${lname} </td>
</tr>
<tr>
<th> Email: </th> 
<td> ${email} </td>
</tr>
<tr>
<th> Date of Birth: </th> 
<td> ${dob} </td>
</tr>
<tr>
<th> Gender: </th> 
<td> ${gender} </td>
</tr>
<tr>
<th> Phone: </th> 
<td> ${phone} </td>
</tr>
<tr>
<th> Course: </th> 
<td> ${course} </td>
</tr>
<tr>
<th> Student Id: </th> 
<td> ${stud_id} </td>
  </table>
 <form action="term.jsp" method="post">
 <input type="hidden" name="email" value="${email}"> 
  <input type="hidden" name="stud_id" value="${stud_id}"> 
  <input type="submit" value="ADD Courses"></form> <br>
  
  <form action="RegisteredCourses" method="post">
 <input type="hidden" name="email" value="${email}"> 
 <input type="hidden" name="stud_id" value="${stud_id}"> 
  <input type="submit" value="View Courses"></form> <br>
  
 <form method="post" action="DropCoursesList"> 
  <input type="submit" value="Drop Courses">
  <input type="hidden" name="email" value="${email}">
  <input type="hidden" name="stud_id" value="${stud_id}"> 
  </form>
  
  <form method="post" action="Update"> 
  <input type="submit" value="Update Information">
  <input type="hidden" name="email" value="${email}">
  <input type="hidden" name="stud_id" value="${stud_id}"> 
  </form>
  </div>
</body>
</html>