package br.com.thyagoribeiro.ecommerce.rest;

import br.com.thyagoribeiro.ecommerce.domains.Produto;
import br.com.thyagoribeiro.ecommerce.domains.UsuarioLogado;
import br.com.thyagoribeiro.ecommerce.rest.contracts.NovoProdutoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NovoProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/api/produtos")
    @Transactional
    public ResponseEntity novoProduto(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest,
                                      @AuthenticationPrincipal UsuarioLogado usuarioLogado) {

        Produto produto = novoProdutoRequest.toModel(entityManager, usuarioLogado.getUsuario());
        entityManager.persist(produto);
        return ResponseEntity.ok(novoProdutoRequest);
    }

}
