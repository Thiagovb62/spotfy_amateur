package org.example.spotfy.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Playlists_Musicas")
@Data
public class PlayListMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private PlayList playlist;

    @ManyToOne
    @JoinColumn(name = "musica_id")
    private Musicas musica;

    // Getters e Setters
}