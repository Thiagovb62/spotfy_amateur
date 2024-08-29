package org.example.spotfy.Models;


import jakarta.persistence.*;
import lombok.*;
import org.example.spotfy.Models.DTO.CadastrarMusicasDto;
import org.example.spotfy.Models.DTO.DetalharMusicasDto;

import java.util.List;


@Entity
@Table(name = "musicas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Musicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;

    private String artista;

    private String album;

    private String genero;

    private String duracao;




    public Musicas(CadastrarMusicasDto dto) {
          this.nome = dto.nome();
          this.artista = dto.artista();
          this.album = dto.album();
          this.genero = dto.genero();
          this.duracao = dto.duracao();
    }
}


