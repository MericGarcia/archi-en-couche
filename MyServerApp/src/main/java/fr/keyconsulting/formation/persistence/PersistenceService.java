package fr.keyconsulting.formation.persistence;

import java.util.List;

import fr.keyconsulting.formation.model.User;

public interface PersistenceService {

	// fonction recuperant un user
	List<User> selectAllUsers();

	// fonction enregistrant un user
	void insertUser(User user);

	void deleteAllUsers();

}