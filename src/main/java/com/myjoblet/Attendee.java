package com.myjoblet;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.web4thejob.context.ContextUtil;
import org.web4thejob.orm.AbstractHibernateEntity;

import com.myjoblet.validator.AttendeeLimit;

@AttendeeLimit
public class Attendee extends AbstractHibernateEntity {

	private Long id;
	@NotNull
	private Course course;
	@NotNull
	private Student student;

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

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();

		if (buffer.length() > 0) {
			buffer.append(" / ");
		}
		if (getCourse() != null) {
			buffer.append(ContextUtil.getMRS().deproxyEntity(course).toString());
		} else {
			buffer.append("?");
		}

		if (buffer.length() > 0) {
			buffer.append(" / ");
		}
		if (getStudent() != null) {
			buffer.append(ContextUtil.getMRS().deproxyEntity(student)
					.toString());
		} else {
			buffer.append("?");
		}

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
