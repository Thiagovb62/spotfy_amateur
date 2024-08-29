package org.example.spotfy.Models.DTO;

import jakarta.validation.constraints.NotBlank;

public record CadastroMusicaOnPlayList(
        @NotBlank
        String NomeMusica,
        @NotBlank
        String NomeArtista
) {


}
