package fr.keyconsulting.formation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Entity
@Table(name="MyUser")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
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