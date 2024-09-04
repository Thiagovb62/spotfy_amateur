package org.example.spotfy.Repository;

import org.example.spotfy.Models.User.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByEmail(String username);

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario findOne(String email);
}
