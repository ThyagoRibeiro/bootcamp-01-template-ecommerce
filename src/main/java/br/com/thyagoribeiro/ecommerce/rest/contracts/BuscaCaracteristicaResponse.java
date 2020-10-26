package br.com.thyagoribeiro.ecommerce.rest.contracts;

import br.com.thyagoribeiro.ecommerce.domains.Caracteristica;

import javax.validation.constraints.NotEmpty;

public class BuscaCaracteristicaResponse {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String valor;

    public BuscaCaracteristicaResponse(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.valor = caracteristica.getValor();
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
}
