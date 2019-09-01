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
			<%
			String em = (String)request.getAttribute("emailExists");
			if(em != null){
				if(em.equals("true")){
					out.print("<p> Uh Oh! Email already in use.</p>");
				}
			}
			String pass = (String)request.getAttribute("passMatch");
			if(pass != null){
				if(pass.equals("true")){
					out.print("<p> Uh Oh! Your passwords don't match.</p>");
				}
			}
			%>
			 <div class="form">
			    <form class="login-form" action="RegisterUserController" method="post">
			   	  <div class="login-header">Register New User</div>
			      <input type="text" placeholder="first name" name="user_first_name" required/>
			      <input type="text" placeholder="last name" name="user_last_name" required/>
			      <input type="text" placeholder="email" name="user_email" required/>
			      <input type="password" placeholder="password" name="user_password" required/>
			      <input type="password" placeholder="confirm password" name="password2" required/>
			      <button>Register New User</button>
			      <p class="message">Have an Account? <a href="LoginPage.jsp">Log in</a></p>
			      <p class="message">Have an Access Code? <a href="GuestPage.jsp">Log in as a Guest</a></p>
			    </form>
			 </div>
		</div>	
	</body>
</html>