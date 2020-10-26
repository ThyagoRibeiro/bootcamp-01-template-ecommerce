package br.com.thyagoribeiro.ecommerce.domains;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Long quantidade;

    @Size(min = 3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private List<Caracteristica> caracteristicaList;

    @NotNull
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    private LocalDateTime instanteCadastro;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private List<ImagemProduto> imagemProdutoList;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private List<Opiniao> opiniaoList;

    @Deprecated
    public Produto() {
    }

    public Produto(@NotEmpty String nome, @NotNull @Positive BigDecimal valor, @NotNull @PositiveOrZero Long quantidade, @Size(min = 3) List<Caracteristica> caracteristicaList, @NotNull @Size(max = 1000) String descricao, @NotNull Categoria categoria, LocalDateTime instanteCadastro, Usuario usuario, List<ImagemProduto> imagemProdutoList, List<Opiniao> opiniaoList) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicaList = caracteristicaList;
        this.caracteristicaList.forEach(caracteristica -> caracteristica.setProduto(this));
        this.descricao = descricao;
        this.categoria = categoria;
        this.instanteCadastro = instanteCadastro;
        this.usuario = usuario;
        this.imagemProdutoList = imagemProdutoList;
        this.opiniaoList = opiniaoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Caracteristica> getCaracteristicaList() {
        return caracteristicaList;
    }

    public void setCaracteristicaList(List<Caracteristica> caracteristicaList) {
        this.caracteristicaList = caracteristicaList;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public void setInstanteCadastro(LocalDateTime instanteCadastro) {
        this.instanteCadastro = instanteCadastro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ImagemProduto> getImagemProdutoList() {
        return imagemProdutoList;
    }

    public void setImagemProdutoList(List<ImagemProduto> imagemProdutoList) {
        this.imagemProdutoList = imagemProdutoList;
    }

    public List<Opiniao> getOpiniaoList() {
        return opiniaoList;
    }

    public void setOpiniaoList(List<Opiniao> opiniaoList) {
        this.opiniaoList = opiniaoList;
    }
}
