package br.com.thyagoribeiro.ecommerce.rest.contracts;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class LoginUsuarioRequest {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String senha;

    @Deprecated
    public LoginUsuarioRequest() {
    }

    public LoginUsuarioRequest(@NotEmpty @Email String email, @NotEmpty String senha) {
        this.email = email;
        this.senha = senha;
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

    public UsernamePasswordAuthenticationToken build() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }
}
