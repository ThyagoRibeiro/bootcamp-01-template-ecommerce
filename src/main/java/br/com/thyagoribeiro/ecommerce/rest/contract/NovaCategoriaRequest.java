package br.com.thyagoribeiro.ecommerce.rest.contract;

import br.com.thyagoribeiro.ecommerce.domain.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCategoriaRequest {

    @NotNull
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("categoria_mae")
    @Positive
    private Long categoriaMaeId;

    @Deprecated
    public NovaCategoriaRequest() {
    }

    public NovaCategoriaRequest(@NotNull String nome, @Positive long categoriaId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCategoriaMaeId() {
        return categoriaMaeId;
    }

    public void setCategoriaMaeId(long categoriaMaeId) {
        this.categoriaMaeId = categoriaMaeId;
    }

    public Categoria toModel(EntityManager entityManager) {
        Categoria categoria = new Categoria(nome);

        if(categoriaMaeId != null)
            categoria.setCategoriaMae(entityManager.find(Categoria.class, categoriaMaeId));

        return categoria;
    }
}
