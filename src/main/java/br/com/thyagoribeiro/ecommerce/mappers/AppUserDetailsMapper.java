package br.com.thyagoribeiro.ecommerce.mappers;

import br.com.thyagoribeiro.ecommerce.domains.Usuario;
import br.com.thyagoribeiro.ecommerce.domains.UsuarioLogado;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AppUserDetailsMapper implements UserDetailsMapper {

    @Override
    public UserDetails map(Object shouldBeASystemUser) {
        return new UsuarioLogado((Usuario) shouldBeASystemUser);
    }
}
