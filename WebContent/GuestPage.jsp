<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Welcome to Quiz Lit!</title>
		<link rel="stylesheet" href="loginStyling.css">
	</head>
	<body>
		<div class="login-page">
			 <div class="form">
			    <form class="login-form" action="GuestLoginController" method="post">
			   	  <div class="login-header">Have a Quiz Access Code?</div>
			      <input type="text" placeholder="code"/>
			      <button>Start Test as Guest</button>
			      <p class="message">Want to Register? <a href="RegisterPage.jsp">Register</a></p>
			      <p class="message">Have an Account? <a href="LoginPage.jsp">Log in</a></p>
			    </form>
			 </div>
		</div>	
	</body>
</html>