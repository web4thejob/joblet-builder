package com.myjoblet.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.junit.Assert;
import org.web4thejob.context.ContextUtil;
import org.web4thejob.orm.Entity;
import org.web4thejob.orm.Path;
import org.web4thejob.orm.query.Condition;
import org.web4thejob.orm.query.Query;

import com.myjoblet.Attendee;

public class AttendeeLimitValidator implements
		ConstraintValidator<AttendeeLimit, Attendee> {
	public static int LIMIT = 5;

	@Override
	public void initialize(AttendeeLimit constraintAnnotation) {

	}

	@Override
	public boolean isValid(Attendee attendee, ConstraintValidatorContext context) {
		if (attendee == null) {
			return true;
		}

		Assert.assertNotNull(attendee.getCourse());
		Assert.assertFalse(attendee.getCourse().isNewInstance());

		Query query = ContextUtil.getEntityFactory().buildQuery(Attendee.class);
		query.addCriterion(new Path("course"), Condition.EQ,
				attendee.getCourse());
		List<Entity> attendees = ContextUtil.getDRS().findByQuery(query);
		return attendees.size() < LIMIT;
	}

}
