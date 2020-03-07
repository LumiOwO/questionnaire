package com.example.questionnaire.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WithoutSpaceValidator implements ConstraintValidator<WithoutSpace, String> {

	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		return !(s.contains(" ") || s.contains("\t") || s.contains("\n"));
	}
}
