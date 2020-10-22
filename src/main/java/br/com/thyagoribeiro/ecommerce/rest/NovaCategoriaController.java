package br.com.thyagoribeiro.ecommerce.rest;

import br.com.thyagoribeiro.ecommerce.domain.Categoria;
import br.com.thyagoribeiro.ecommerce.rest.contract.NovaCategoriaRequest;
import br.com.thyagoribeiro.ecommerce.validator.CategoriaUnicaValidator;
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
public class NovaCategoriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CategoriaUnicaValidator categoriaUnicaValidator;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(categoriaUnicaValidator);
    }

    @PostMapping("/api/categorias")
    @Transactional
    public ResponseEntity novaCategoria(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest, UriComponentsBuilder uriComponentsBuilder) {
        Categoria categoria = novaCategoriaRequest.toModel(entityManager);
        entityManager.persist(categoria);
        URI enderecoConsulta = uriComponentsBuilder.path("/api/categorias").build(categoria.getId());
        return ResponseEntity.created(enderecoConsulta).build();
    }

}
