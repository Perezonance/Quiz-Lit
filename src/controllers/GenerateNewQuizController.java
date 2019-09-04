package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbUtilities.DBUtility;

/**
 * Servlet implementation class GenerateNewQuizController
 */
@WebServlet("/GenerateNewQuizController")
public class GenerateNewQuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int questionCounter = 0;
    private static int grade = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateNewQuizController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session  = request.getSession();
		if(questionCounter == 0) {
			session.setAttribute("grade", Integer.toString(grade));
		}
		if(questionCounter >= 1) {
			if(request.getParameter("question").equals(session.getAttribute("correctAns"))) {
				System.out.println("Correct Answer!");
				int oldGrade = 10 + Integer.parseInt((String)session.getAttribute("grade"));
				System.out.println("Old Grade: " + oldGrade);
				String newGrade = Integer.toString(oldGrade);
				System.out.println("New Grade: " + newGrade);
				session.setAttribute("grade", newGrade);
				System.out.println("Session Grade: " + session.getAttribute("grade"));
			}
		}
		
		if(questionCounter == 10) {
			System.out.println(session.getAttribute("grade"));
			RequestDispatcher rd = request.getRequestDispatcher("QuizResultsPage.jsp");
			rd.forward(request, response);
		}
		
		
		questionCounter++;
		response.setContentType("text/html");
		
		String catID = (String)session.getAttribute("categoryID");
		String userID = (String)session.getAttribute("userID");
		
		String query1 = "SELECT category_name FROM `quizit`.`category` WHERE category_id = " + catID +";";
		
		ResultSet rsCat = DBUtility.executeQuery(query1);
		String cat = "";
		
		try {
			rsCat.first();
			cat = (String)rsCat.getString("category_name");
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch( Exception e) {
			e.printStackTrace();
		}
		
		String query2 = "SELECT * FROM `quizit`.`" + cat.toLowerCase() + "` WHERE question_id = " + questionCounter + ";";
		ResultSet rsQue = DBUtility.executeQuery(query2);
		String question = "";
		String rAns ="", wAns1 = "", wAns2 = "", wAns3 = "";
		try {
			rsQue.first();
			question = rsQue.getString("question");
			rAns = rsQue.getString("question_correct_answer");
			wAns1 = rsQue.getString("question_wrong_answer1");
			wAns2 = rsQue.getString("question_wrong_answer2");
			wAns3 = rsQue.getString("question_wrong_answer3");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int x = (int)(Math.random()*4) + 1;
		
		String q1 = "", q2 = "", q3 = "", q4 = "", correctAns = "";
		
		switch(x) {
			case 1:
				q1 = rAns;
				q2 = wAns1;
				q3 = wAns2;
				q4 = wAns3;
				correctAns = "q1";
				break;
			case 2:
				q1 = wAns1;
				q2 = rAns;
				q3 = wAns2;
				q4 = wAns3;
				correctAns = "q2";
				break;
			case 3:
				q1 = wAns2;
				q2 = wAns1;
				q3 = rAns;
				q4 = wAns3;
				correctAns = "q3";
				break;
			case 4:
				q1 = wAns3;
				q2 = wAns1;
				q3 = wAns2;
				q4 = rAns;
				correctAns = "q4";
				break;
		}
		session.setAttribute("correctAns", correctAns);
		request.setAttribute("q1", q1);
		request.setAttribute("q2", q2);
		request.setAttribute("q3", q3);
		request.setAttribute("q4", q4);
		request.setAttribute("categoryName", cat);
		request.setAttribute("qCount", Integer.toString(questionCounter));
		request.setAttribute("q", question);
		
		RequestDispatcher rd = request.getRequestDispatcher("QuizPage.jsp");
		rd.forward(request, response);
		
	}

}
