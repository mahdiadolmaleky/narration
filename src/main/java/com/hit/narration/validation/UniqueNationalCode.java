package com.hit.narration.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueNationalCodeValidtor.class)
@Target( { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueNationalCode {
    String message() default "duplicate national code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
