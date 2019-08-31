<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Quiz Lit Login</title>
		<link rel="stylesheet" href="loginStyling.css">
	</head>
	<body>
		<div class="login-page">
		  <div class="form">
		    <form class="login-form" action="LoginUserController" method="post">
		   	  <div class="login-header">Welcome to Quiz Lit!</div>
		      <input type="text" placeholder="email"/>
		      <input type="password" placeholder="password"/>
		      <button>login</button>
		      <p class="message">Not registered? <a href="RegisterPage.jsp">Register</a></p>
		      <p class="message">Have a Quiz Access Code? <a href="GuestPage.jsp">Guest Sign In</a></p>
		   <!--   (Optional)  <p class="message">Forgot Password? <a href="recoverPasswordPage">Recover Password</a></p> -->
		    </form>
		  </div>
		</div>	
	</body>
</html>
