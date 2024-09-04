package org.example.spotfy.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.spotfy.Infra.Security.Jwt.TokenService;
import org.example.spotfy.Models.User.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid Usuario.DadosUser dadosUser){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosUser.email(), dadosUser.password());
        System.out.println(authenticationToken);
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(tokenJWT);
    }
}
