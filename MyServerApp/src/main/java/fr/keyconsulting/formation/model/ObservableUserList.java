package fr.keyconsulting.formation.model;

import java.util.List;
import java.util.Observable;

public class ObservableUserList extends Observable{

	private List<User> users;
	
	
	public ObservableUserList(List<User> users) {
		super();
		this.users = users;
	}


	public List<User> get(){
		return users;
	}
	
}
