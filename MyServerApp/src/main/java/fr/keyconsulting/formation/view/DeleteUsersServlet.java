package fr.keyconsulting.formation.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.keyconsulting.formation.controller.UserListController;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/DeleteUsers")
public class DeleteUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	UserListController controller = UserListController.get();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUsersServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Users");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		controller.deleteAllUsers();
		doGet(request, response);
	}

}
