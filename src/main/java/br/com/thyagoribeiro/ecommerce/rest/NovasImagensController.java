package br.com.thyagoribeiro.ecommerce.rest;

import br.com.thyagoribeiro.ecommerce.domains.ImagemProduto;
import br.com.thyagoribeiro.ecommerce.domains.Produto;
import br.com.thyagoribeiro.ecommerce.domains.UsuarioLogado;
import br.com.thyagoribeiro.ecommerce.http.UploadFile;
import br.com.thyagoribeiro.ecommerce.rest.contracts.NovasImagensRequest;
import br.com.thyagoribeiro.ecommerce.utils.ErroPadronizado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
public class NovasImagensController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/api/produtos/{id_produto}/imagens")
    @Transactional
    public ResponseEntity novaImagem(@PathVariable("id_produto") Long idProduto,
            @Valid NovasImagensRequest novasImagensRequest,
            @AuthenticationPrincipal UsuarioLogado usuarioLogado){

        Produto produto = entityManager.find(Produto.class, idProduto);

        if(produto == null) {
            return ResponseEntity.badRequest().body(new ErroPadronizado(Arrays.asList("Esse produto não existe")));
        }
        if(!usuarioLogado.getUsername().equals(produto.getUsuario().getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<URI> uriList = UploadFile.uploadFiles(novasImagensRequest.getImagem());
        uriList.forEach(path -> produto.getImagemProdutoList().add(new ImagemProduto(path.getPath(), produto)));

        entityManager.persist(produto);

        return ResponseEntity.ok().build();
    }

}
