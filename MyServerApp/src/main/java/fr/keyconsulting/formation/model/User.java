package fr.keyconsulting.formation.model;

public class User {
	public String firstname;
	public String lastname;

	public User(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}
}