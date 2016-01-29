package fr.keyconsulting.formation.controller;

import java.util.List;
import java.util.Observer;

import fr.keyconsulting.formation.model.ObservableUserList;
import fr.keyconsulting.formation.model.User;
import fr.keyconsulting.formation.service.UserService;

public class UserListController {

	
	private static UserListController instance;
	
	private ObservableUserList userList;
	
	private UserService userService;
	

	private UserListController() {
		this.userService =  new UserService();
		this.userList =  new ObservableUserList(userService.getAllUsers());
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
