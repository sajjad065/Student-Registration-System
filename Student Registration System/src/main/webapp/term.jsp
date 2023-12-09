<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select Term</title>
<style>
    body {
        font-family: Arial, sans-serif;
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
        text-align: center;
    }

    form {
        width: 50%;
        margin: 20px;
        padding: 20px;
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    label {
        display: block;
        margin-bottom: 10px;
    }

    select {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }

    input[type="submit"] {
        background-color: #4caf50;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
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
 System.out.println("The output is " +email);
 String stud_id=request.getParameter("stud_id");
 System.out.println("The student id is " +stud_id);
 
 %>
 
  <form action="selection.jsp" method="post">
	  
	  	 <label for="course1">Select Term:</label>
   		 <select id="term" name="term" required>
        <option value="" disabled selected>Select Course</option>
        <option value="Summer 2023">Summer 2023</option>
        <option value="Fall 2023">Fall 2023</option>
        <option value="Spring 2023">Spring 2023</option> </select>
		<input type="hidden" name="email" value="<%=email%>">
		<input type="hidden" name="stud_id" value="<%=stud_id%>">

    	<input type="submit" value="Submit">
</form>
</body>
</html>