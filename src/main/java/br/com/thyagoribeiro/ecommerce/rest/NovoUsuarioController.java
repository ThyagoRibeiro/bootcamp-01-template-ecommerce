package br.com.thyagoribeiro.ecommerce.rest;

import br.com.thyagoribeiro.ecommerce.domain.Usuario;
import br.com.thyagoribeiro.ecommerce.rest.contract.NovoUsuarioRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NovoUsuarioController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(value = "/api/usuario")
    @Transactional
    public ResponseEntity<?> novoUsuario(@RequestBody @Valid NovoUsuarioRequest novoUsuarioRequest) {
        Usuario usuario = novoUsuarioRequest.toModel();
        entityManager.persist(usuario);
        return ResponseEntity.ok().build();
    }

}