package com.bukry.gredel.cinema.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@NotBlank(message = "password is mandatory")
@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
        message = "password must contain: a digit, a lower case letter, an uppercase letter, a special character," +
                " must be at least 8 characters long and cannot contain any spaces")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Constraint(validatedBy = { })
public @interface ValidPassword {
    String message() default "{jakarta.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
