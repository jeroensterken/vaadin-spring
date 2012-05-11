package be.faros.vaadin.model;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

	private String name;
	private String firstName;
	private Date birthDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Person(String name, String firstName, Date birthDate) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.birthDate = birthDate;
	}

	public Person() {
		super();
	}
}
