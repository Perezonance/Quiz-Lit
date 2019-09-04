<!DOCTYPE html>
<html>
	<head>
		<title>Dynamic Quiz Project</title>
		<link type='text/css' rel='stylesheet' href='QuizStyling.css'/>
		<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open Sans"/>
	</head>
	<body>
	
		<%%>
		<div id='container'>
			<div id='title'>
				<h1><%= request.getAttribute("categoryName") %>: Question #<%=request.getAttribute("qCount") %></h1>
			</div>
   			<br/>
  			<div>
  				<form action='GenerateNewQuizController' method='post'>
  					<p>
  						Question:<%= (String)request.getAttribute("q") %>
  					</p>
  					<br>
  					<p>
	  					<input type='radio' name='question' value='q1'><%= request.getAttribute("q1") %><br><br>
	  					<input type='radio' name='question' value='q2'><%= request.getAttribute("q2") %><br><br>
	  					<input type='radio' name='question' value='q3'><%= request.getAttribute("q3") %><br><br>
	  					<input type='radio' name='question' value='q4'><%= request.getAttribute("q4") %><br><br>
  					</p>
  					<input type='submit' value='Next'>
  				</form>
  			</div>
	</body>
</html>
