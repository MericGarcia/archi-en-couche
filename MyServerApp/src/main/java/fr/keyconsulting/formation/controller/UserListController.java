package fr.keyconsulting.formation.controller;

import java.util.List;
import java.util.Observer;

import fr.keyconsulting.formation.model.ObservableUserList;
import fr.keyconsulting.formation.model.User;
import fr.keyconsulting.formation.service.UserPersistenceService;

public class UserListController {

	
	private static UserListController instance;
	
	private ObservableUserList userList;
	
	private UserPersistenceService userService;
	

	private UserListController() {
		this.userService =  new UserPersistenceService();
		this.userList =  new ObservableUserList(userService.getAllUser());
		instance = this;
	}

	public static UserListController get(){
		if(instance == null){
			instance = new UserListController();
		}
		return instance;
	}
	
	public List<User> getAllUsers() {
		return userList.get();
	}
	
	public void add(User user) {
		userService.add(user);
		synchronized(userList){
			userList.get().add(user);
			userList.notifyAll();
		}
	}

	public void addUserListObserver(Observer o) {
		userList.addObserver(o);	
	}

}
