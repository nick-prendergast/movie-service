package com.example.movieservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {MovieValidator.class, RatingValidator.class})
public @interface MovieValidation {

    String message() default "Invalid movie: does not exist in OMDB";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
