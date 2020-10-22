package br.com.thyagoribeiro.ecommerce.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistValidator implements ConstraintValidator<Exist, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> domainClass;
    private String fieldName;
    private boolean expected;
    private String message;

    @Override
    public void initialize(Exist constraintAnnotation) {
        domainClass = constraintAnnotation.domainClass();
        fieldName = constraintAnnotation.fieldName();
        expected = constraintAnnotation.expected();

        if (expected) // CDD 1 - branch if
            message = "deve existir na base";
        else // CDD 1 - branch else
            message = "não deve existir na base";    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("SELECT 1 FROM " + domainClass.getSimpleName() + " WHERE " + fieldName + " = :valor");
        query.setParameter("valor", value);

        List<?> result = query.getResultList();
        boolean exists = result.size() > 0;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

        return exists == expected; // CDD 1 - branch if
    }
}
