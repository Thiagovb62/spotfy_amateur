package org.example.spotfy.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.spotfy.Models.DTO.CadastrarPlayListDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "playlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private String created_by;

    @ManyToMany
    @JoinTable(
            name = "Playlists_Musicas",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "musica_id")
    )
    private Set<Musicas> musicas = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "Users_PlayLists",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<SpotUser> spotUser = new ArrayList<>();

    public PlayList(CadastrarPlayListDto dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
    }
}
