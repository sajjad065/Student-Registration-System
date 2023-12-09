<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome page</title>
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 20px;
            padding: 20px;
        }

        h1 {
            color: #009688;
        }

        form {
            margin-top: 20px;
             
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            margin: 5px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100px;
            
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
        
        div{
       text-align:center;
        }
    </style>
</head>
<body>
<div>
<h1> welcome to the Admin's Page</h1>

 <form action="View" method="post">
        <input type="submit" name="action" value="View Record"> <br> <br>
        </form>
        
       <a href="Studentform.html"> <input type="submit" name="action" value="Add"> </a> <br>
        
        <form action="DeleteRecord" method="post">
        <input type="submit" name="action" value="Delete"> <br> <br>
         </form>
        
       
    
</div>
</body>
</html>