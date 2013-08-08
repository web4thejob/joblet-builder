package com.myjoblet;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.web4thejob.orm.AbstractHibernateEntity;

public class Course extends AbstractHibernateEntity {

	private Long id;
	@NotNull
	private Professor professor;
	@NotBlank
	private String code;
	@NotBlank
	private String description;
	private Coursenote coursenote;
	private Set<Attendee> attendees = new HashSet<Attendee>(0);

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Coursenote getCoursenote() {
		return this.coursenote;
	}

	public void setCoursenote(Coursenote coursenote) {
		this.coursenote = coursenote;
	}

	public Set<Attendee> getAttendees() {
		return this.attendees;
	}

	public void setAttendees(Set<Attendee> attendees) {
		this.attendees = attendees;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();

		if (buffer.length() > 0) {
			buffer.append(" / ");
		}
		buffer.append(code);

		return buffer.toString();
	}

	@Override
	public Serializable getIdentifierValue() {
		return id;
	}

	@Override
	public void setAsNew() {
		id = 0L;
	}

}
