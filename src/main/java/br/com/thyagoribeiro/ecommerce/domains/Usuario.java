package br.com.thyagoribeiro.ecommerce.domains;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Future
    private LocalDateTime instanteCadastro;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotNull @Future LocalDateTime instanteCadastro, @NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        this.instanteCadastro = instanteCadastro;
        this.email = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public void setInstanteCadastro(LocalDateTime instanteCadastro) {
        this.instanteCadastro = instanteCadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
