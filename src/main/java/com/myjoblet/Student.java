package com.myjoblet;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.web4thejob.orm.AbstractHibernateEntity;

public class Student extends AbstractHibernateEntity {

	private Long id;
	@NotBlank
	private String lastName;
	@NotBlank
	private String firstName;
	@NotNull
	private Date birthDate;
	private Set<Attendee> attendees = new HashSet<Attendee>(0);

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Set<Attendee> getAttendees() {
		return this.attendees;
	}

	public void setAttendees(Set<Attendee> attendees) {
		this.attendees = attendees;
	}

	@Override
	public Serializable getIdentifierValue() {
		return id;
	}

	@Override
	public void setAsNew() {
		id = 0L;
	}

	@Override
	public String toString() {
		return lastName + " " + firstName;
	}
}
