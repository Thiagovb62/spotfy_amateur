package org.example.spotfy.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Users_PlayLists")
@Data
public class UsersPlayList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private PlayList playlist;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SpotUser spotUser;

    // Getters e Setters
}