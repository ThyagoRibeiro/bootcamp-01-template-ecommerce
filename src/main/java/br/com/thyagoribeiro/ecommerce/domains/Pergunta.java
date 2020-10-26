package br.com.thyagoribeiro.ecommerce.domains;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime instanteCadastro;

    @NotNull
    @ManyToOne
    private Produto produto;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    public Pergunta() {
    }

    public Pergunta(@NotBlank String titulo, @NotBlank LocalDateTime instanteCadastro, @NotNull Produto produto, @NotNull Usuario usuario) {
        this.titulo = titulo;
        this.instanteCadastro = instanteCadastro;
        this.produto = produto;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
