package org.example.spotfy.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.spotfy.Models.User.Usuario;
import org.example.spotfy.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
public class UserControler {

    private UserService userService;

    public UserControler(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody @Valid Usuario.DadosUser dadosUser) throws IllegalArgumentException {
        userService.createUser(dadosUser);
        return ResponseEntity.created(null).body("Usuário cadastrado com sucesso");
    }

    @PostMapping("/premium")
    public void bePremium(@PathVariable Long id) {
        userService.bePremium(id);
    }

    @PostMapping("/admin/{id}")
    public void beAdmin(@PathVariable Long id) {
        userService.beAdmin(id);
    }

}
