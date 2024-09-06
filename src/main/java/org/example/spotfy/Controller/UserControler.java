package org.example.spotfy.Controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.spotfy.Models.User.Usuario;
import org.example.spotfy.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController()
@RequestMapping("/user")
public class UserControler {

    private final UserService userService;

    public UserControler(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar usuário"),
    })
    @Transactional
    public ResponseEntity<String> createUser(@RequestBody @Valid @Parameter Usuario.DadosUser dadosUser, UriComponentsBuilder uriComponentsBuilder ) throws IllegalArgumentException {
        userService.createUser(dadosUser);
       var url = uriComponentsBuilder.path("/user/{email}").buildAndExpand(dadosUser.email()).toUri();
        return ResponseEntity.created(url).body("Usuário cadastrado com sucesso");
    }

    @PostMapping(value ="/premium", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário agora é premium"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public void bePremium(@PathVariable @Parameter Long id) {
        userService.bePremium(id);
    }

    @PostMapping(value ="/admin/{id}", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário agora é admin"),
    })
    public void beAdmin(@PathVariable Long id) {
        userService.beAdmin(id);
    }

}
