package controllers;

import java.io.File;
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
		
		//TO-DO: Check if user email already exists
		
		if(DBUtility.existsinDB("`quizit`.`user`", userEmail, "user_email") == 1) {
			request.setAttribute("emailExists", "true");
			RequestDispatcher rd = request.getRequestDispatcher("RegisterPage.jsp");
			rd.forward(request, response);
		}
		
		//If Passwords dont match then redirect back to the registration page with error attribute
		if(!userPassword.equals(userPasswordConfirm)) {
			request.setAttribute("passMatch", "false");
			RequestDispatcher rd = request.getRequestDispatcher("RegisterPage.jsp");
			rd.forward(request, response);
		}
		
		String query = "INSERT INTO `quizit`.`user` (user_first_name, user_last_name, user_email, user_password, user_role_type) "
				+ "values('" + userFirstName +"','" + userLastName + "','" + userEmail + "', '" + userPassword +"', 'user' )";
		System.out.println(DBUtility.updateQuery(query));
		
		String queryId = "SELECT user_id FROM `quizit`.`user` WHERE user_email = '" + userEmail + "';";
		System.out.println("Select userID Query: " + queryId);
		ResultSet rsID = DBUtility.executeQuery(queryId);
		String id = "";
		try {
			rsID.next();
			id = rsID.getString("user_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("userID", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("UserDashboard.jsp");
		rd.forward(request, response);
		
	}

}
