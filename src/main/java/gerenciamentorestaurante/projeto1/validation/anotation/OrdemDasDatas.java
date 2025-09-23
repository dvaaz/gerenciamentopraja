package gerenciamentorestaurante.projeto1.validation.anotation;

// Referencia https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html

import gerenciamentorestaurante.projeto1.validation.validator.OrdemDasDatasValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(({ElementType.METHOD, ElementType.FIELD}))
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrdemDasDatasValidator.class)
public @interface OrdemDasDatas {
    String primeiraData();
    String segundaData();

    String message() default "A data de validade deve ser posterior à data de fabricação";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}


