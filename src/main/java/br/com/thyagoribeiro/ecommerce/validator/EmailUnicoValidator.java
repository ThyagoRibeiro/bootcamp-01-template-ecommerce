package br.com.thyagoribeiro.ecommerce.validator;

import br.com.thyagoribeiro.ecommerce.rest.contract.NovoUsuarioRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class EmailUnicoValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoUsuarioRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        NovoUsuarioRequest novoUsuarioRequest = (NovoUsuarioRequest) target;

        Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE email = :email");
        query.setParameter("email", novoUsuarioRequest.getEmail());

        List<?> result = query.getResultList();

        if(!result.isEmpty()) {
            errors.rejectValue("email", null, "ja existe em outro usuario");
        }

    }
}
