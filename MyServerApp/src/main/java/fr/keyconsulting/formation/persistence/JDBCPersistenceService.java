package fr.keyconsulting.formation.persistence;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.keyconsulting.formation.model.User;

public class JDBCPersistenceService {

	private static Logger logger = LoggerFactory.getLogger(JDBCPersistenceService.class);

	static String PASSWORD = "test";
	static String USER = "test";
	static String DRIVER = "org.postgresql.Driver";
	static String URL = "jdbc:postgresql://localhost:5432/myDB";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			logger.error("Error while loading jdbc driver", e);
		}
	}

	// fonction recuperant un user
	public List<User> selectAllUsers() {

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
	public void insertUser(User user) {

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
	
	public void deleteAllUsers() {

		Statement stmt = null;
		String query = "DELETE FROM test.USER";

		try {
			stmt = DriverManager.getConnection(URL, USER, PASSWORD).createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			logger.error("Problem while trying to delete all users", e);
		}

	}

}
