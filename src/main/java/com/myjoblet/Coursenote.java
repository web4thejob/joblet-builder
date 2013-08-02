package com.myjoblet;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.web4thejob.orm.AbstractHibernateEntity;
import org.web4thejob.orm.annotation.HtmlHolder;

public class Coursenote extends AbstractHibernateEntity {

	private Long id;
	@NotNull
	private Course course;
	@NotBlank @HtmlHolder
	private String notes;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
		if (course != null) {
			return course.toString() + " note";
		}
		return "a note";
	}
}
