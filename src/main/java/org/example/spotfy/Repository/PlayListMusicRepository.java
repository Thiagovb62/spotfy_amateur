package org.example.spotfy.Repository;

import org.example.spotfy.Models.PlayListMusic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListMusicRepository extends JpaRepository<PlayListMusic, Long> {
}
