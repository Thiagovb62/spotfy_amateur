package org.example.spotfy.Service;

import org.example.spotfy.Models.User.RoleUser;
import org.example.spotfy.Models.User.Usuario;
import org.example.spotfy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        Usuario user = userRepository.findById(id).get();
        user.setIsPremium(true);
        userRepository.save(user);
    }

    public void beAdmin(Long id) {
        Usuario user = userRepository.findById(id).get();;
        user.setRole(RoleUser.ADMIN_USER);
        userRepository.save(user);
    }


}
