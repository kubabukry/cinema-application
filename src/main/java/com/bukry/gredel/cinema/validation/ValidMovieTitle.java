package com.bukry.gredel.cinema.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@Size(min = 1, max = 128, message = "title must be between 1 and 128 characters long")
@NotBlank(message = "title is mandatory")
@Pattern(regexp = "^\\S.*\\S$", message = "title can't start or end with whitespace")
@Constraint(validatedBy = { })
public @interface ValidMovieTitle {
    String message() default "{jakarta.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
