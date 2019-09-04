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
		
		
		/*
		 * Sign in to User Dashboard or Admin Dashboard depending on whether they are one or the other:
		 * 1. Run a query on the DB: SELECT user_role_type FROM....
		 * 2. USe the resultset to determin if the user is admin or user
		 * 3. Use request Dispatcher to forward them to the correct dashboard page
		 */
		String queryAdmin = "SELECT user_role_type FROM quizit.user WHERE user_email = '" + email + "';";
        ResultSet rsAdmin = DBUtility.executeQuery(queryAdmin);
        HttpSession session = request.getSession();
        try {
            rsAdmin.first();
            if(rsAdmin.getString(1).equals("admin")) {
            	System.out.println("Admin Successfully Logged in!");
                session.setAttribute("userID", id);
                RequestDispatcher rdadmin = request.getRequestDispatcher("AdminDashboard.jsp");
                rdadmin.forward(request, response);
            }else {
            	System.out.println("User Successfully Logged in!");
                session.setAttribute("userID", id);
                RequestDispatcher rdID = request.getRequestDispatcher("UserDashboard.jsp");
                rdID.forward(request, response);
            }
        } catch (SQLException e) {
    	// TODO Auto-generated catch block
        	e.printStackTrace();
        } catch(Exception e) {
        	e.printStackTrace();
        }
	}

}
