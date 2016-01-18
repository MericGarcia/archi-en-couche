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
@WebServlet("/Users")
public class UserListServlet extends HttpServlet {

	Logger logger = LoggerFactory.getLogger(UserListServlet.class);

	private static final long serialVersionUID = 1L;

	static String PASSWORD = "test";
	static String USER = "test";
	static String DRIVER = "org.postgresql.Driver";
	static String URL = "jdbc:postgresql://localhost:5432/myDB";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserListServlet() {
		super();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("users", selectAllQuery());
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
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

	// fonction recuperant un user
	private List<User> selectAllQuery() {

		List<User> users = new ArrayList<>();
		Statement stmt = null;
		String query = "SELECT * FROM test.USER";

		try {
			stmt = DriverManager.getConnection(URL, USER, PASSWORD).createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				users.add(new User(firstname, lastname));
			}
		} catch (SQLException e) {
			logger.error("Problem while retrieving users list", e);
		}

		return users;
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
