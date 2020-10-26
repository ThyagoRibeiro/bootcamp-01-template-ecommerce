package br.com.thyagoribeiro.ecommerce.rest;

import br.com.thyagoribeiro.ecommerce.domains.Produto;
import br.com.thyagoribeiro.ecommerce.rest.contracts.BuscaProdutoResponse;
import br.com.thyagoribeiro.ecommerce.utils.ErroPadronizado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;

@RestController
public class BuscaProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/api/produtos/{id_produto}")
    public ResponseEntity<?> buscaProduto(@PathVariable("id_produto") Long idProduto) {

        Produto produto = entityManager.find(Produto.class, idProduto);
        if(produto == null)
            return ResponseEntity.badRequest().body(new ErroPadronizado(Arrays.asList("Produto não encontrado.")));

        BuscaProdutoResponse buscaProdutoResponse = new BuscaProdutoResponse(produto);

        return ResponseEntity.ok(buscaProdutoResponse);
    }

}
