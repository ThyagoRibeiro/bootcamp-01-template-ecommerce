package br.com.thyagoribeiro.ecommerce.rest.contracts;

import br.com.thyagoribeiro.ecommerce.domains.Caracteristica;
import br.com.thyagoribeiro.ecommerce.domains.Categoria;
import br.com.thyagoribeiro.ecommerce.domains.Produto;
import br.com.thyagoribeiro.ecommerce.domains.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NovoProdutoRequest {

    @NotEmpty
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Long quantidade;

    @Size(min = 3)
    @JsonProperty("caracteristicas")
    private List<NovaCaracteristicaRequest> novaCaracteristicaRequestList;

    @NotNull
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @JsonProperty("categoria_id")
    private Long categoriaId;

    @JsonProperty("instante_cadastro")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime instanteCadastro;

    @Deprecated
    public NovoProdutoRequest() {
    }

    public NovoProdutoRequest(@NotEmpty String nome, @NotNull @Positive BigDecimal valor, @NotNull @PositiveOrZero Long quantidade, @Size(min = 3) List<NovaCaracteristicaRequest> caracteristicaList, @NotNull @Size(max = 1000) String descricao, @NotNull Long categoria, LocalDateTime instanteCadastro) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.novaCaracteristicaRequestList = caracteristicaList;
        this.descricao = descricao;
        this.categoriaId = categoria;
        this.instanteCadastro = instanteCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public List<NovaCaracteristicaRequest> getNovaCaracteristicaRequestList() {
        return novaCaracteristicaRequestList;
    }

    public void setNovaCaracteristicaRequestList(List<NovaCaracteristicaRequest> novaCaracteristicaRequestList) {
        this.novaCaracteristicaRequestList = novaCaracteristicaRequestList;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public void setInstanteCadastro(LocalDateTime instanteCadastro) {
        this.instanteCadastro = instanteCadastro;
    }

    public Produto toModel(EntityManager entityManager, Usuario usuario) {

        List<Caracteristica> caracteristicaList = new ArrayList<>();
        this.novaCaracteristicaRequestList.forEach(novaCaracteristicaRequest -> caracteristicaList.add(novaCaracteristicaRequest.toModel()));

        Categoria categoria = entityManager.find(Categoria.class, this.categoriaId);

        return new Produto(nome, valor,quantidade, caracteristicaList, descricao, categoria, instanteCadastro, usuario);
    }
}
