package fr.keyconsulting.formation.service;

import java.util.List;

import fr.keyconsulting.formation.model.User;
import fr.keyconsulting.formation.persistence.JDBCPersistenceService;

public class UserService {
	
	private JDBCPersistenceService persistenceService;
	
	public UserService(){
		this.persistenceService = new JDBCPersistenceService();
	}
	
	public void add(User user){
		persistenceService.insertUser(user);
	}
	
	public void deleteAllUsers(){
		persistenceService.deleteAllUsers();
	}
	
	public List<User> getAllUsers(){
		return persistenceService.selectAllUsers();
	}
	
}
