<%@page import="dbUtilities.DBUtility" import = "java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Quiz Lit User Dashboard</title>
		<link rel="stylesheet" href="DashboardStyling.css">
	</head>
	<body>
	
		<% 
		
		String id = "";
		try{
			id = (String)session.getAttribute("userID");
			}
		catch(Exception e){
				e.printStackTrace();
			}
		
		String query = "SELECT user_first_name FROM `quizit`.`user` WHERE user_id = " + id + ";";
		ResultSet rs = DBUtility.executeQuery(query);
		rs.next();
		String fName = rs.getString("user_first_name");
		
			%>
		<h1>Welcome to Quiz Lit, <%= fName %>!</h1>
		<br>
		<div class="category-table">
			<table class="fixed_header">
			  <thead>
			    <tr>
			      <th>Quiz Category</th>
			      <th>Description</th>
			      <th>Average Score</th>
			      <th>Take Quiz</th>
			    </tr>
			  </thead>
			  <tbody>
			  	
			    <tr>
			      <td>row 1-0</td>
			      <td>row 1-1</td>
			      <td>row 1-2</td>
			      <td>row 1-3</td>
			    </tr>
			    <tr>
			      <td>row 2-0</td>
			      <td>row 2-1</td>
			      <td>row 2-2</td>
			      <td>row 2-3</td>
			    </tr>
			    <tr>
			      <td>row 3-0</td>
			      <td>row 3-1</td>
			      <td>row 3-2</td>
			      <td>row 3-3</td>
			    </tr>
			    <tr>
			      <td>row 4-0</td>
			      <td>row 4-1</td>
			      <td>row 4-2</td>
			      <td>row 4-3</td>
			    </tr>
			    <tr>
			      <td>row 5-0</td>
			      <td>row 5-1</td>
			      <td>row 5-2</td>
			      <td>row 5-3</td>
			    </tr>
			    <tr>
			      <td>row 6-0</td>
			      <td>row 6-1</td>
			      <td>row 6-2</td>
			      <td>row 6-3</td>
			    </tr>
			    <tr>
			      <td>row 7-0</td>
			      <td>row 7-1</td>
			      <td>row 7-2</td>
			      <td>row 7-3</td>
			    </tr>
			  </tbody>
			</table>
		</div>
	</body>
</html>