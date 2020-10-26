package br.com.thyagoribeiro.ecommerce.rest;

import br.com.thyagoribeiro.ecommerce.domains.Pergunta;
import br.com.thyagoribeiro.ecommerce.domains.Produto;
import br.com.thyagoribeiro.ecommerce.domains.UsuarioLogado;
import br.com.thyagoribeiro.ecommerce.rest.contracts.NovaPerguntaRequest;
import br.com.thyagoribeiro.ecommerce.utils.ErroPadronizado;
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
import java.util.Arrays;

@RestController
public class NovaPerguntaController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/api/produtos/{id_produto}/perguntas")
    @Transactional
    public ResponseEntity novaPergunta(@PathVariable("id_produto") Long idProduto, @RequestBody @Valid NovaPerguntaRequest novaPerguntaRequest, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {

        Produto produto = entityManager.find(Produto.class, idProduto);
        if(produto == null)
            return ResponseEntity.badRequest().body(new ErroPadronizado(Arrays.asList("Esse produto não existe")));

        Pergunta pergunta = novaPerguntaRequest.toModel(produto, usuarioLogado.getUsuario());

        produto.getPerguntaList().add(pergunta);
        entityManager.persist(produto);

        System.out.println("Envia e-mail para " + usuarioLogado.getUsuario().getEmail() + "notificando que o produto " + produto.getNome() + " recebeu a pergunta " + pergunta.getTitulo());

        return ResponseEntity.ok(novaPerguntaRequest);
    }

}
