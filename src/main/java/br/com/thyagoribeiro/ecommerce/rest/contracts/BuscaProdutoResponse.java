package br.com.thyagoribeiro.ecommerce.rest.contracts;

import br.com.thyagoribeiro.ecommerce.domains.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BuscaProdutoResponse {

    @JsonProperty("imagens")
    private List<String> imagemList;

    private String nome;

    private BigDecimal preco;

    @JsonProperty("caracteristicas")
    private List<BuscaCaracteristicaResponse> buscaCaracteristicaResponseList;

    private String descricao;

    @JsonProperty("media_notas")
    private BigDecimal mediaNotas;

    @JsonProperty("total_notas")
    private Long totalNotas;

    @JsonProperty("opinioes")
    private List<BuscaOpiniaoResponse> buscaOpiniaoResponseList;

    @JsonProperty("perguntas")
    private List<BuscaPerguntaResponse> buscaPerguntaResponseList;

    public BuscaProdutoResponse() {
    }

    public BuscaProdutoResponse(Produto produto) {

        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.mediaNotas = produto.mediaNotas();
        this.totalNotas = produto.totalNotas();

        this.imagemList = new ArrayList<>();
        produto.getImagemProdutoList().forEach(imagemProduto -> imagemList.add((imagemProduto.getPath())));

        this.buscaCaracteristicaResponseList = new ArrayList<>();
        produto.getCaracteristicaList().forEach(caracteristica -> buscaCaracteristicaResponseList.add(new BuscaCaracteristicaResponse(caracteristica)));

        this.buscaOpiniaoResponseList = new ArrayList<>();
        produto.getOpiniaoList().forEach(opiniao -> buscaOpiniaoResponseList.add(new BuscaOpiniaoResponse(opiniao)));

        this.buscaPerguntaResponseList = new ArrayList<>();
        produto.getPerguntaList().forEach(pergunta -> buscaPerguntaResponseList.add(new BuscaPerguntaResponse(pergunta)));

    }

    public List<String> getImagemList() {
        return imagemList;
    }

    public void setImagemList(List<String> imagemList) {
        this.imagemList = imagemList;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public List<BuscaCaracteristicaResponse> getBuscaCaracteristicaResponseList() {
        return buscaCaracteristicaResponseList;
    }

    public void setBuscaCaracteristicaResponseList(List<BuscaCaracteristicaResponse> buscaCaracteristicaResponseList) {
        this.buscaCaracteristicaResponseList = buscaCaracteristicaResponseList;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getMediaNotas() {
        return mediaNotas;
    }

    public void setMediaNotas(BigDecimal mediaNotas) {
        this.mediaNotas = mediaNotas;
    }

    public Long getTotalNotas() {
        return totalNotas;
    }

    public void setTotalNotas(Long totalNotas) {
        this.totalNotas = totalNotas;
    }

    public List<BuscaOpiniaoResponse> getBuscaOpiniaoResponseList() {
        return buscaOpiniaoResponseList;
    }

    public void setBuscaOpiniaoResponseList(List<BuscaOpiniaoResponse> buscaOpiniaoResponseList) {
        this.buscaOpiniaoResponseList = buscaOpiniaoResponseList;
    }

    public List<BuscaPerguntaResponse> getBuscaPerguntaResponseList() {
        return buscaPerguntaResponseList;
    }

    public void setBuscaPerguntaResponseList(List<BuscaPerguntaResponse> buscaPerguntaResponseList) {
        this.buscaPerguntaResponseList = buscaPerguntaResponseList;
    }
}
