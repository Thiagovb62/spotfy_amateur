package org.example.spotfy.Repository;

import org.example.spotfy.Models.Musicas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MusicaRepository extends JpaRepository<Musicas, Long> {

     Musicas findByNome(String nome);

    @Query("SELECT m FROM Musicas m WHERE m.artista = :artista")
    Page<Musicas> findAllByArtista(String artista, Pageable pageable);


}
