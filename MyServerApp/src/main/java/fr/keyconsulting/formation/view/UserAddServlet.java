package fr.keyconsulting.formation.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.keyconsulting.formation.controller.UserListController;
import fr.keyconsulting.formation.model.User;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserAdd")
public class UserAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	UserListController controller = UserListController.get();	

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
		response.sendRedirect("Users");
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
			controller.add(new User(firstname, lastname));
		}
		doGet(request, response);
	}


	

}
