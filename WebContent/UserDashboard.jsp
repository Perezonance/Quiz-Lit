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
		<br>`
		<div class="category-table">
			
			  	<% RequestDispatcher rd = request.getRequestDispatcher("CategoryTableController");
			  	rd.include(request, response); %>
			   
			  
			
		</div>
	</body>
</html>