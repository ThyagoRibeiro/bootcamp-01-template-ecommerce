package br.com.thyagoribeiro.ecommerce.rest.contracts;

import br.com.thyagoribeiro.ecommerce.domains.Caracteristica;

import javax.validation.constraints.NotNull;

public class NovaCaracteristicaRequest {

    @NotNull
    private String nome;

    @NotNull
    private String valor;

    @Deprecated
    public NovaCaracteristicaRequest() {
    }

    public NovaCaracteristicaRequest(@NotNull String nome, @NotNull String valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Caracteristica toModel() {
        return new Caracteristica(nome, valor);
    }

}
