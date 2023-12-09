<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search result</title>
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
        }

        h1 {
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h2>Student Search Result</h2>

<table border="1">
        <tr>
        	<th>Id </th>
            <th>First Name</th>
            <th>Last Name</th>
             <th>Email</th>
            <th>DOB</th>
             <th>Gender</th>
            <th>Phone</th>
             <th>Course</th>
            
        </tr>
        <c:forEach var="student" items="${resultList}">
            <tr>
                 <td>${student.id}</td>
                <td>${student.first_name}</td>
                 <td>${student.last_name}</td>
                <td>${student.email}</td>
                 <td>${student.dob}</td>
                <td>${student.gender}</td>
                 <td>${student.phone}</td>
                <td>${student.course}</td>
                
            </tr>
        </c:forEach>
        </table>

    

</body>
</html>