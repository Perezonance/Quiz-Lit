package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbUtilities.DBUtility;

/**
 * Servlet implementation class CategoryTableController
 */
@WebServlet("/CategoryTableController")
public class CategoryTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryTableController() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//All Output will be translated as html text
		response.setContentType("text/html");
		
		String queryCat = "SELECT * FROM `quizit`.`category`;";
		
		/*
		 * <form action=\"QuizLandingPage.jsp\" method=\"post\">"
							+ "<input type=\"submit\" name=\"" + rsCat.getString("category_name") + "\">Take Quiz!</input>"
						+ "</form>
		 */
		
		ResultSet rsCat = DBUtility.executeQuery(queryCat);
		PrintWriter out = response.getWriter();
		out.println("<table class=\"fixed_header\">");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>Quiz Category</th>");
		out.println("<th>Description</th>");
		out.println("<th>Take Quiz</th>");
		out.println("</thead>");
		out.println("</tr>");
		try {
			while(rsCat.next()) {
				out.println("<tr>");
				out.println("<td>" + rsCat.getString("category_name") + "</td>");
				out.println("<td>" + rsCat.getString("category_description") + "</td>");
				out.println("<td><a href=\"QuizLandingPage.jsp\"><button>Take Quiz</button></a></td>");
				out.println("</tr>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("</tbody>");
		out.println("</table>");
		out.flush();
		
	}

}
