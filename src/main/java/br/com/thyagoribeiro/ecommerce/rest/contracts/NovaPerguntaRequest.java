package br.com.thyagoribeiro.ecommerce.rest.contracts;

import br.com.thyagoribeiro.ecommerce.domains.Pergunta;
import br.com.thyagoribeiro.ecommerce.domains.Produto;
import br.com.thyagoribeiro.ecommerce.domains.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    @NotNull
    @JsonProperty("instante_cadastro")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime instanteCadastro;

    @Deprecated
    public NovaPerguntaRequest() {
    }

    public NovaPerguntaRequest(@NotBlank String titulo, @NotBlank LocalDateTime instanteCadastro) {
        this.titulo = titulo;
        this.instanteCadastro = instanteCadastro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public void setInstanteCadastro(LocalDateTime instanteCadastro) {
        this.instanteCadastro = instanteCadastro;
    }

    public Pergunta toModel(Produto produto, Usuario usuario) {
        return new Pergunta(titulo, instanteCadastro, produto, usuario);
    }

}
