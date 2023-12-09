<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup Form</title>
<style>
       body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: flex-start; /* Change 'left' to 'flex-start' */
    height: 90vh;
}


        form {
            
            padding: 40px;
            border-radius: 5px;
            
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            box-sizing: border-box;
            font-size: 36px;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: #fff;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error-message {
    color: red;
}
    </style>
</head>
<body>
	
	
<form action="SignUp" method="post">
	<h1>  Sign Up </h1>
	<label for="error" class="error-message">${requestScope.emailError}</label>
        <label for="username">Student Id:</label> 
        <input type="text" id="username" name="username" required> <br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required> <br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required> <br>

        <input type="submit" value="Sign Up">
    </form>
</body>
</html>