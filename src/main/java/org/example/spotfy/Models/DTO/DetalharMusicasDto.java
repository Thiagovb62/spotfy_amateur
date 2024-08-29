package org.example.spotfy.Models.DTO;

import org.example.spotfy.Models.Musicas;
import org.springframework.data.domain.Page;

import java.util.List;

public record DetalharMusicasDto(
        Long id,
        String nome,
        String artista,
        String album,
        String genero,
        String duracao) {

   public  DetalharMusicasDto(Musicas music){
        this(music.getId() , music.getNome(), music.getArtista(), music.getAlbum(), music.getGenero(), music.getDuracao());
   }

    public static List<DetalharMusicasDto> converter(List<Musicas> all) {
        return all.stream().map(DetalharMusicasDto::new).toList();
    }

    public static Page<DetalharMusicasDto> convert(Page<Musicas> musicas) {
        return musicas.map(DetalharMusicasDto::new);
    }
}
