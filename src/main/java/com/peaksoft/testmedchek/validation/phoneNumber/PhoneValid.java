package com.peaksoft.testmedchek.validation.phoneNumber;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Documented
@Constraint(validatedBy = PhoneNumberValidation.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneValid {

    String message() default "Invalid phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}