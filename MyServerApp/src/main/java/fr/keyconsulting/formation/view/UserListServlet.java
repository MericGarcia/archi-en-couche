package fr.keyconsulting.formation.view;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.keyconsulting.formation.controller.UserListController;
import fr.keyconsulting.formation.model.ObservableUserList;
import fr.keyconsulting.formation.model.User;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/Users")
public class UserListServlet extends HttpServlet implements Observer {

	private static final long serialVersionUID = 1L;
	
	UserListController controller = UserListController.get();
	List<User> users;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserListServlet() {
		super();
		controller.addUserListObserver(this);
		users = controller.getAllUsers();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("users", users);
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void update(Observable o, Object arg) {
		users = ((ObservableUserList) o).get();		
	}

}
