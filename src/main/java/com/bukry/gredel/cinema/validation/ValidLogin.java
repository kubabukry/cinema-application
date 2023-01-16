package com.bukry.gredel.cinema.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@NotBlank(message = "login is mandatory")
@Size(min = 3, max = 32, message = "login must be between 3 and 32 characters long")
@Pattern(regexp = "^\\w+$", message = "login can contain only letters, numbers or _ character")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Constraint(validatedBy = { })
public @interface ValidLogin {

    /**
     * @return the error message template
     */
    String message() default "{jakarta.validation.constraints.Pattern.message}";

    /**
     * @return the groups the constraint belongs to
     */
    Class<?>[] groups() default { };

    /**
     * @return the payload associated to the constraint
     */
    Class<? extends Payload>[] payload() default { };
}
