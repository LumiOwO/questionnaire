package com.example.questionnaire.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.StandardCharsets;

public class DataBytesValidator implements ConstraintValidator<DataBytes, String> {

	private int min;
	private int max;

	/**
	 * @param constraintAnnotation
	 */
	@Override
	public void initialize(DataBytes constraintAnnotation) {
		min = constraintAnnotation.min();
		max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		int value = s.getBytes(StandardCharsets.UTF_8).length;
		return value >= min && value <= max;
	}

}
