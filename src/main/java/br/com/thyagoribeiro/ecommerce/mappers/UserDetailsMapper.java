package br.com.thyagoribeiro.ecommerce.mappers;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {

    UserDetails map(Object shouldBeASystemUser);

}
