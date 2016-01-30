package fr.keyconsulting.formation.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.keyconsulting.formation.model.User;
import fr.keyconsulting.formation.persistence.PersistenceService;
import fr.keyconsulting.formation.service.jms.JmsService;

@Service @WebService
public class UserService {
	
	@Autowired @Qualifier("MyPersister")
	private PersistenceService persistenceService;
	
	@Autowired
	private JmsService jmsService;
	
	public void add(User user){
		persistenceService.insertUser(user);
		jmsService.send(user);
		
	}
	
	public void deleteAllUsers(){
		persistenceService.deleteAllUsers();
	}
	
	public List<User> getAllUsers(){
		return persistenceService.selectAllUsers();
	}
	
	public List<User> getAllEnqueuedUsers(){
		User user = jmsService.nextUser();
		List<User> users = new ArrayList<User>();
		while( user != null){
			users.add(user);
			user = jmsService.nextUser();
		}
		return users;
	}
	
}
