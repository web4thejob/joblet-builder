/*******************************************************************************
 * Copyright (c) 2013 Veniamin Isaias.
 * 
 * This file is part of joblet-builder.
 * 
 * Joblet-builder is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Joblet-builder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with joblet-builder.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.web4thejob.joblet;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.web4thejob.context.ContextUtil;
import org.web4thejob.joblet.base.AbstractORMTest;
import org.web4thejob.orm.query.Query;

import com.myjoblet.Attendee;
import com.myjoblet.Course;
import com.myjoblet.Professor;
import com.myjoblet.Student;

public class ORMTest extends AbstractORMTest {

	@Test
	@Transactional
	public void testStudent() {
		Calendar birthDate = Calendar.getInstance();
		birthDate.set(1977, 3, 3);

		Student student = new Student();
		student.setFirstName("Bob");
		student.setLastName("Sponge");
		student.setBirthDate(new Date(birthDate.getTimeInMillis()));
		ContextUtil.getDWS().save(student);
	}

	@Test
	@Transactional
	public void testCourse() {
		testProfessor();

		Query query = ContextUtil.getEntityFactory()
				.buildQuery(Professor.class);
		Professor professor = ContextUtil.getDRS().findFirstByQuery(query);
		Assert.assertNotNull(professor);

		SecureRandom random = new SecureRandom();
		String code = new BigInteger(30, random).toString();

		Course course = new Course();
		course.setCode(code);
		course.setProfessor(professor);
		course.setDescription("Blah blah blah...");
		ContextUtil.getDWS().save(course);
	}

	@Test
	@Transactional
	public void testProfessor() {

		Calendar birthDate = Calendar.getInstance();
		birthDate.set(1977, 3, 3);

		Professor professor = new Professor();
		professor.setFirstName("Albert");
		professor.setLastName("Einstein");
		ContextUtil.getDWS().save(professor);
	}

	@Test(expected = javax.validation.ConstraintViolationException.class)
	@Transactional
	public void testStudentAgeConstraint() {

		Calendar birthDate = Calendar.getInstance();
		birthDate.set(2000, 7, 1); // age = 13, we expect that this test will
									// fail

		Student student = new Student();
		student.setFirstName("Patrick");
		student.setLastName("Squid");
		student.setBirthDate(birthDate.getTime());
		ContextUtil.getDWS().save(student);
	}

	@Test
	@Transactional
	public void testAttendeeLimit() {
		Query query;

		testStudent();
		query = ContextUtil.getEntityFactory().buildQuery(Student.class);
		Student student = ContextUtil.getDRS().findFirstByQuery(query);
		Assert.assertNotNull(student);

		testProfessor();
		query = ContextUtil.getEntityFactory().buildQuery(Professor.class);
		Professor professor = ContextUtil.getDRS().findFirstByQuery(query);
		Assert.assertNotNull(professor);

		testCourse();
		query = ContextUtil.getEntityFactory().buildQuery(Course.class);
		Course course = ContextUtil.getDRS().findFirstByQuery(query);
		Assert.assertNotNull(course);

		Attendee attendee = new Attendee();
		attendee.setCourse(course);
		attendee.setStudent(student);
		ContextUtil.getDWS().save(attendee);
	}

	@Test(expected = javax.validation.ConstraintViolationException.class) @Transactional
	public void testAttendeeLimitExcess() {
		Query query;

		testProfessor();
		testCourse();

		query = ContextUtil.getEntityFactory().buildQuery(Course.class);
		Course course = ContextUtil.getDRS().findFirstByQuery(query);
		Assert.assertNotNull(course);

		Attendee attendee;
		for (int i = 1; i < 10; i++) {
			attendee = new Attendee();
			attendee.setCourse(course);
			Calendar birthDate = Calendar.getInstance();

			Student student = new Student();
			student.setFirstName("Bob");
			student.setLastName("Sponge");
			birthDate.set(1977, 3, 3);
			student.setBirthDate(new Date(birthDate.getTimeInMillis()));
			ContextUtil.getDWS().save(student);
			
			attendee.setStudent(student);
			ContextUtil.getDWS().save(attendee);

		}

	}
}
