package br.com.thyagoribeiro.ecommerce.rest;

import br.com.thyagoribeiro.ecommerce.domain.Usuario;
import br.com.thyagoribeiro.ecommerce.rest.contract.NovoUsuarioRequest;
import br.com.thyagoribeiro.ecommerce.validator.EmailUnicoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class NovoUsuarioController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmailUnicoValidator emailUnicoValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(emailUnicoValidator);
    }

    @PostMapping(value = "/api/usuarios")
    @Transactional
    public ResponseEntity<?> novoUsuario(@RequestBody @Valid NovoUsuarioRequest novoUsuarioRequest, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = novoUsuarioRequest.toModel();
        entityManager.persist(usuario);
        URI enderecoConsulta = uriComponentsBuilder.path("/api/usuarios").build(usuario.getId());
        return ResponseEntity.created(enderecoConsulta).build();
    }

}
