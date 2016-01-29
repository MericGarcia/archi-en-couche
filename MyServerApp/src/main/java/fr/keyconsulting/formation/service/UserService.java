package fr.keyconsulting.formation.service;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.keyconsulting.formation.model.User;
import fr.keyconsulting.formation.persistence.PersistenceService;

@Service @WebService
public class UserService {
	
	@Autowired @Qualifier("MyPersister")
	private PersistenceService persistenceService;
	
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
