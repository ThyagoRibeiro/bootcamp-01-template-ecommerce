package br.com.thyagoribeiro.ecommerce.rest;

import br.com.thyagoribeiro.ecommerce.domains.Produto;
import br.com.thyagoribeiro.ecommerce.domains.UsuarioLogado;
import br.com.thyagoribeiro.ecommerce.rest.contracts.NovaOpiniaoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NovaOpiniaoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/api/produtos/{id_produto}/opinioes")
    @Transactional
    public ResponseEntity novaOpiniao(@PathVariable("id_produto") Long idProduto, @RequestBody @Valid NovaOpiniaoRequest novaOpiniaoRequest, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {

        Produto produto = entityManager.find(Produto.class, idProduto);

        if(produto == null) {
            return ResponseEntity.ok().build();
        }

        produto.getOpiniaoList().add(novaOpiniaoRequest.toModel(entityManager, produto, usuarioLogado.getUsuario()));
        entityManager.persist(produto);

        return ResponseEntity.ok().build();
    }

}
