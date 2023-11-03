<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome page</title>
</head>
<body>
<h1> welcome to the Admin's Page</h1>

 <form action="View" method="post">
        <input type="submit" name="action" value="View Database"> <br>
        </form>
        
       <a href="Studentform.html"> <input type="submit" name="action" value="Add"> </a> <br>
        
        <form action=".." method="post">
        <input type="submit" name="action" value="Delete"> <br>
        <input type="submit" name="action" value="Update"> <br>
        <input type="submit" name="action" value="Search">
    </form>

</body>
</html>