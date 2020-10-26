package br.com.thyagoribeiro.ecommerce.rest.contracts;

import br.com.thyagoribeiro.ecommerce.domains.Usuario;
import br.com.thyagoribeiro.ecommerce.validators.Exist;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class NovoUsuarioRequest {

    @JsonProperty("instante_cadastro")
    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime instanteCadastro;

    @JsonProperty("email")
    @NotBlank
    @Email
    @Exist(domainClass = Usuario.class, fieldName = "email", expected = false)
    private String email;

    @JsonProperty("senha")
    @NotBlank
    @Size(min = 6)
    private String senha;

    @Deprecated
    public NovoUsuarioRequest() {
    }

    public NovoUsuarioRequest(@NotNull @Future LocalDateTime instanteCriacao, @NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        this.instanteCadastro = instanteCriacao;
        this.email = login;
        this.senha = senha;
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

    public Usuario toModel(){
        return new Usuario(instanteCadastro, email, senha);
    }
}
