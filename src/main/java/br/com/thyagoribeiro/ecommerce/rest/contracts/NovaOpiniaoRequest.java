package br.com.thyagoribeiro.ecommerce.rest.contracts;

import br.com.thyagoribeiro.ecommerce.domains.Opiniao;
import br.com.thyagoribeiro.ecommerce.domains.Produto;
import br.com.thyagoribeiro.ecommerce.domains.Usuario;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaOpiniaoRequest {

    @Min(1)
    @Max(5)
    @NotNull
    private Long nota;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    public NovaOpiniaoRequest() {
    }

    public NovaOpiniaoRequest(@Min(1) @Min(5) @NotNull Long nota, @NotBlank String titulo, @NotBlank String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Long getNota() {
        return nota;
    }

    public void setNota(Long nota) {
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Opiniao toModel(EntityManager entityManager, Produto produto, Usuario usuario){
        return new Opiniao(nota, titulo, descricao, produto, usuario);
    }

}
