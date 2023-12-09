<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Login Page</title>
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

    .login-container {
        background-color: #ffffff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        text-align: center;
        width: 250px;
    }

    h2 {
        color: #333;
    }

    form {
        margin-top: 20px;
    }

    .form-group {
        margin-bottom: 15px;
        text-align: left;
    }

    label {
        display: block;
        margin-bottom: 8px;
        color: #555;
    }

    input {
        width: 100%;
        padding: 10px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-top: 5px;
    }

    .submit-btn {
        background-color: #4caf50;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
    }

    .submit-btn:hover {
        background-color: #45a049;
    }

    a {
        text-decoration: none;
        color: #3498db;
    }

    a:hover {
        color: #2980b9;
    }
    .error-message
    {
    color:red;
    }
</style>

</head>

<body>
	<div class="login-container">
		<h2>Student Login</h2>
		<div class="error-message">${requestScope.errorMessage}</div>
		<form action="StudentLogin" method="post">
			<div class="form-group">
				<label for="username">Username:</label>
				<input type="text" id="username" name="username" required>
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
				<input type="password" id="password" name="password" required>
			</div>
			<button type="submit" class="submit-btn">Login</button>
			<h3> <a href="Signup.jsp"> New User</a></h3>
			<h3> <a href="adminlogin.html"> Login as Admin</a></h3>
		</form>
		
	</div>
	
</body>

</html>