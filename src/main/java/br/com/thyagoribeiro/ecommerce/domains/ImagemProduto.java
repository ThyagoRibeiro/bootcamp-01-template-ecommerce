package br.com.thyagoribeiro.ecommerce.domains;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String path;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(String path, Produto produto) {
        this.path = path;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
