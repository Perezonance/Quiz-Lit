<%@page import="dbUtilities.DBUtility" import="java.sql.ResultSet"%>
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
    <%
        String id = "";
        String catID = "";
        try {
            id = (String) session.getAttribute("userID");
            catID = (String)session.getAttribute("categoryID");
        } catch (Exception e) {
            e.printStackTrace();
        }
		
        String catName ="";
        try{
        String query = "SELECT category_name FROM `quizit`.`category` WHERE category_id = " + catID + ";";
        ResultSet rs = DBUtility.executeQuery(query);
        rs.next();
        catName = rs.getString("category_name");
        } catch(Exception e){
        	e.printStackTrace();
        }
    %>
	
    <div class="login-form">
        <div class="form">
            <form class="quiz-form" action="GenerateNewQuizController" method="post">
                <h1>    <%=catName%> Quiz</h1>
                <h3>Instructions</h3>
                <p align="left">Total Questions: 10 Questions</p>
                <p align="left">Time Alloted: 10 Minutes</p>
                <p align="left">Total Score will be displayed once you finish
                    the Quiz</p>
                 <p align="left">You cannot go back to a previously answered question</p>
                <p align="left">Click on Start button to begin the Quiz</p>
                <button>Start</button>
            </form>
        </div>
    </div>
</body>
</html>