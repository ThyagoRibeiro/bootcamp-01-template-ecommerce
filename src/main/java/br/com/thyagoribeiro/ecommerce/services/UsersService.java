package br.com.thyagoribeiro.ecommerce.services;

import br.com.thyagoribeiro.ecommerce.mappers.UserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UsersService implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        List<?> objects = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email").setParameter("email", email).getResultList();
        Assert.isTrue(objects.size() <= 1, "Mais de um usuário autenticavel com esse email");

        if (objects.isEmpty()) {
            throw new UsernameNotFoundException("Não foi possível encontrar um usuário com o email: " + email);
        }

        return userDetailsMapper.map(objects.get(0));
    }
}
