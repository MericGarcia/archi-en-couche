package fr.keyconsulting.formation.controller;

import java.util.List;
import java.util.Observer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.keyconsulting.formation.model.ObservableUserList;
import fr.keyconsulting.formation.model.User;
import fr.keyconsulting.formation.service.UserService;

public class UserListController {

	
	private static UserListController instance;
	
	private ObservableUserList userList;
	
	private UserService userService;
	

	private UserListController() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		this.userService =  context.getBean(UserService.class);
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
	
	public void deleteAllUsers() {
		userService.deleteAllUsers();
		synchronized(userList){
			userList.clear();
			userList.notifyAll();
		}
	}
	
	public void add(User user) {
		userService.add(user);
		synchronized(userList){
			userList.add(user);
			userList.notifyAll();
		}
	}

	public void addUserListObserver(Observer o) {
		userList.addObserver(o);	
	}

}
