package fr.keyconsulting.formation.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.keyconsulting.formation.model.User;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserAdd")
public class UserAddServlet extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(UserAddServlet.class);

	private static final long serialVersionUID = 1L;

	static String PASSWORD = "test";
	static String USER = "test";
	static String DRIVER = "org.postgresql.Driver";
	static String URL = "jdbc:postgresql://localhost:5432/myDB";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			logger.error("Error while loading jdbc driver",e);
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAddServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("Users");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		if (firstname != null && lastname != null) {
			request.setAttribute("firstname", firstname);
			request.setAttribute("lastname", lastname);
			insertQuery(new User(firstname, lastname));
		}
		doGet(request, response);
	}


	// fonction enregistrant un user
	void insertQuery(User user) {

		Statement stmt = null;
		String query = "INSERT INTO test.USER (firstname,lastname) VALUES('" + user.firstname + "','" + user.lastname
				+ "')";

		try {
			stmt = DriverManager.getConnection(URL, USER, PASSWORD).createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			logger.error("Problem while inserting user into database", e);
		}

	}

}
