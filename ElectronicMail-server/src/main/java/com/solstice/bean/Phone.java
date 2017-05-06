package com.solstice.bean;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD })
@Constraint(validatedBy = { PhoneImpl.class })
public @interface Phone {

	public String message();
	
	public String value();

	public Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
