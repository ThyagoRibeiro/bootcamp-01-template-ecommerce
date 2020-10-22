package br.com.thyagoribeiro.ecommerce.validator;

import br.com.thyagoribeiro.ecommerce.domain.Categoria;
import br.com.thyagoribeiro.ecommerce.rest.contract.NovaCategoriaRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class CategoriaUnicaValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCategoriaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        NovaCategoriaRequest novaCategoriaRequest = (NovaCategoriaRequest) target;

        Query query = entityManager.createQuery("SELECT c FROM Categoria c WHERE nome = :nome");
        query.setParameter("nome", novaCategoriaRequest.getNome());

        List<Categoria> categoriaList = query.getResultList();

        if(!categoriaList.isEmpty())
            errors.rejectValue("nome", null, "ja esta cadastrada na base");
    }
}
