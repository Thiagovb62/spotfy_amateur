package org.example.spotfy.Service;

import org.example.spotfy.Models.User.RoleUser;
import org.example.spotfy.Models.User.Usuario;
import org.example.spotfy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

     private UserRepository userRepository;

     @Autowired
    private PasswordEncoder passwordEncoder;


    UserService(UserRepository userRepository){
         this.userRepository = userRepository;
     }

    public void createUser(Usuario.DadosUser dadosUser) {
        if (userRepository.findOne(dadosUser.email()) != null) {
            throw new IllegalArgumentException("Usuário já cadastrado");
        }
        Usuario user = new Usuario(dadosUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void bePremium(Long id) {
        Optional<Usuario> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        user.get().setRole(RoleUser.PREMIUM_USER);
        userRepository.save(user.get());
    }

    public void beAdmin(Long id) {
        Usuario user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        user.setRole(RoleUser.ADMIN_USER);
        userRepository.save(user);
    }


}
