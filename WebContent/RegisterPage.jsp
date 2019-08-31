<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Welcome to Quiz Lit!</title>
		<link rel="stylesheet" href="loginStyling.css">
	</head>
	<body>
		<div class="login-page">
			 <div class="form">
			    <form class="login-form" action="RegisterUserController" method="post">
			   	  <div class="login-header">Register New User</div>
			      <input type="text" placeholder="first name" name="fName"/>
			      <input type="text" placeholder="last name" name="lName"/>
			      <input type="text" placeholder="email" name="email"/>
			      <input type="text" placeholder="password" name="password"/>
			      <input type="text" placeholder="confirm password" name="password2"/>
			      <button>Register New User</button>
			      <p class="message">Have an Account? <a href="LoginPage.jsp">Log in</a></p>
			      <p class="message">Have an Access Code? <a href="GuestPage.jsp">Log in as a Guest</a></p>
			    </form>
			 </div>
		</div>	
	</body>
</html>