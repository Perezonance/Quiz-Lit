package controllers;

import java.io.File;
import java.io.IOException;

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
 * Servlet implementation class RegisterUserController
 */
@WebServlet("/RegisterUserController")
public class RegisterUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBUtility db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		db = new DBUtility(new File("dbAccess.properties"));
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
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
		//Retrieve data from the registration form
		String userFirstName=request.getParameter("user_first_name");
		String userLastName=request.getParameter("user_last_name");
		String userEmail=request.getParameter("user_email");
		String userPassword=request.getParameter("user_password");
		String userPasswordConfirm=request.getParameter("password2");
		
		//TO-DO: Check if user email exists
		System.out.println("Test Case: 1");
		if(DBUtility.existsinDB("`quizit`.`user`", userEmail, "user_email")) {
			HttpSession session = request.getSession();
			request.setAttribute("emailExists", "true");
			RequestDispatcher rd = request.getRequestDispatcher("RegisterPage.jsp");
			rd.forward(request, response);
		}
		System.out.println("Test Case: 2");
		//If Passwords dont match then redirect back to the registration page with error attribute
		if(!userPassword.equals(userPasswordConfirm)) {
			HttpSession session = request.getSession();
			request.setAttribute("passMatch", "false");
			RequestDispatcher rd = request.getRequestDispatcher("RegisterPage.jsp");
			rd.forward(request, response);
		}
		
		String query = "INSERT INTO `quizit`.`user` (user_first_name, user_last_name, user_email, user_password) "
				+ "values('" + userFirstName +"','" + userLastName + "','" + userEmail + "', '" + userPassword +"')";
		System.out.println("Test Case: 3");
		System.out.println(DBUtility.updateQuery(query));
		
		
		request.setAttribute("userID", "2");
		
		RequestDispatcher rd = request.getRequestDispatcher("UserDashboard.jsp");
		rd.forward(request, response);
		
	}

}
