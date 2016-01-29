package fr.keyconsulting.formation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MyUser")
public class User {
	
	public String firstname;
	public String lastname;
	
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;

	public User() {
	}
	
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
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}