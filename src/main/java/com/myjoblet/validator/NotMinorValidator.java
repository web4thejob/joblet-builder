package com.myjoblet.validator;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotMinorValidator implements ConstraintValidator<NotMinor, Date> {
	private int minAgeInYears;

	@Override
	public void initialize(NotMinor constraintAnnotation) {
		minAgeInYears = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(Date birthDate, ConstraintValidatorContext context) {
		if (birthDate == null) {
			return true;
		}

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c2.clear();
		c2.setTime(birthDate);

		long diff = c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
		return diff >= minAgeInYears;
	}

}
