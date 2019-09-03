package controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import dbUtilities.DBConnection;
import dbUtilities.DBUtility;

/**
 * Servlet implementation class LoginUserController
 */
@WebServlet("/LoginUserController")
public class LoginUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DBUtility db;

// test comment
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		//Establish connection to DB
		db = new DBUtility(new File("dbAccess.properties"));
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		
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
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();  
		
		String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    
	    String queryEm = "SELECT user_password FROM `quizit`.`user` WHERE user_email = '" + email + "';";	    
		ResultSet rsEm = DBUtility.executeQuery(queryEm);
		
		String pass = "";
		try {
			rsEm.first();
			if(rsEm.getString(1) != null) {
				pass = rsEm.getString("user_password");
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("LoginPage.jsp");
				request.setAttribute("invalidLogin", "true");
				rd.forward(request, response);
			}
				
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		//Check for password matching to the email's password
		if(!(pass.equalsIgnoreCase(password))) {
			RequestDispatcher rd = request.getRequestDispatcher("LoginPage.jsp");
			request.setAttribute("invalidLogin", "true");
			rd.forward(request, response);
		}
		String queryID = "SELECT user_id FROM `quizit`.`user` WHERE user_email = '" + email + "';";
		ResultSet rsID = DBUtility.executeQuery(queryID);
		String id ="";
		try {
			rsID.next();
			id = rsID.getString("user_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		
		session.setAttribute("userID", id);
		
		RequestDispatcher rdID = request.getRequestDispatcher("UserDashboard.jsp");
		rdID.forward(request, response);
		//request.setAttribute("userID", id);
		
	    
	    		/*
			    if(email.equals("username") && password.equals("password")) {
			    	
			    	boolean validCredentials = true;
			    	String q = "SELECT user_id FROM quizit.user WHERE user_email = 'test@email.com';"; // how to identify the unique email chosen?
			    	// 3. session attribute passing
			    	HttpSession session = request.getSession();
			    	ResultSet rs = DBUtility.executeQuery(q);
					try {
						request.setAttribute("userID", rs.getString(1) );
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					RequestDispatcher rd = request.getRequestDispatcher("LoginPage.jsp");
					rd.forward(request, response);
			    	response.sendRedirect("welcome.jsp");
			    }
			    else if(!password.equals("password")) {
					HttpSession session = request.getSession();
					request.setAttribute("password", "false");
					RequestDispatcher rd = request.getRequestDispatcher("LoginPage.jsp");
					rd.forward(request, response);
			    }
			    */
	}

}
