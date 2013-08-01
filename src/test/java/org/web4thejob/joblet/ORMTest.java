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

import java.util.Date;

import org.junit.Test;
import org.web4thejob.context.ContextUtil;
import org.web4thejob.joblet.base.AbstractORMTest;

import com.myjoblet.Professor;
import com.myjoblet.Student;

public class ORMTest extends AbstractORMTest {

	@Test
	public void test1() {
		Student student = new Student();
		student.setLastName("Sponge");
		student.setFirstName("Bob");
		student.setBirthDate(new Date());
		ContextUtil.getDWS().save(student);

		Professor professor = new Professor();
		professor.setLastName("Albert");
		professor.setFirstName("Einstein");
		ContextUtil.getDWS().save(professor);
	}

}
