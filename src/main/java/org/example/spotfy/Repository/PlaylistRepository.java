package org.example.spotfy.Repository;

import org.example.spotfy.Models.PlayList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaylistRepository  extends JpaRepository<PlayList, Long> {


    @Query("SELECT  p FROM PlayList p JOIN UsersPlayList up on p.id = up.playlist.id join PlayListMusic pm on pm.playlist.id = p.id join Musicas m on pm.musica.id =m.id where up.spotUser.id = :id ")
    List<PlayList> findAllPlayListByUser_Spot_Id(Long id);


    @Query("SELECT p FROM PlayList p where p.nome = :nome")
    PlayList findPlayListByName(String nome);


    @Query("SELECT DISTINCT p FROM PlayList p JOIN PlayListMusic pm on pm.playlist.id = p.id join Musicas m on pm.musica.id = m.id WHERE LOWER(m.artista) LIKE LOWER(CONCAT(:artista, '%')) OR LOWER(m.artista) = LOWER(:artista)")
    Page<PlayList> findPlayListsByAtistName(String artista, Pageable pageable);


    Boolean existsByNome(String nome);
}
