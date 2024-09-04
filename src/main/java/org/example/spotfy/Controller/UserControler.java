package org.example.spotfy.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.spotfy.Models.User.Usuario;
import org.example.spotfy.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
public class UserControler {

    private UserService userService;

    public UserControler(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public void createUser(@RequestBody @Valid Usuario.DadosUser dadosUser) {
        userService.createUser(dadosUser);
    }

    @PostMapping("/premium")
    public void bePremium(String email) {
        userService.bePremium(email);
    }

    @PostMapping("/admin")
    public void beAdmin(String email) {
        userService.beAdmin(email);
    }

}
