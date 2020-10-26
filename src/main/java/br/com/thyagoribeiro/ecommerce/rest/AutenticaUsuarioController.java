package br.com.thyagoribeiro.ecommerce.rest;

import br.com.thyagoribeiro.ecommerce.config.TokenManager;
import br.com.thyagoribeiro.ecommerce.rest.contracts.LoginUsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AutenticaUsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/api/auth")
    public ResponseEntity<?> autenticaUsuario(@RequestBody @Valid LoginUsuarioRequest loginUsuario) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = loginUsuario.build();

        try{

            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            String jtw = tokenManager.generateToken(authentication);
            return ResponseEntity.ok(jtw);

        } catch(AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
