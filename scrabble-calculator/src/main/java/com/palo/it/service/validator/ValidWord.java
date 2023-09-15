package com.palo.it.service.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { ValidWordValidator.class })
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidWord {

    String message() default "Invalid entry for word";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default  {};
}
