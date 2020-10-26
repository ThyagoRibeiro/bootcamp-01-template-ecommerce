package br.com.thyagoribeiro.ecommerce.rest.contracts;

import br.com.thyagoribeiro.ecommerce.domains.Pergunta;
import br.com.thyagoribeiro.ecommerce.domains.Produto;
import br.com.thyagoribeiro.ecommerce.domains.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BuscaPerguntaResponse {

    @NotBlank
    private String titulo;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("instante_cadastro")
    private LocalDateTime instanteCadastro;

    @NotNull
    @ManyToOne
    private String username;

    public BuscaPerguntaResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.instanteCadastro = pergunta.getInstanteCadastro();
        this.username = pergunta.getUsuario().getEmail();
    }
}
