package org.example.spotfy.Models.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastrarMusicasDto(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Artista é obrigatório")
        String artista,

        @NotBlank(message = "Album é obrigatório")
        String album,

        @NotBlank(message = "Genero é obrigatório")
        String genero,

        @Pattern(regexp = "\\d{2}:\\d{2}", message = "Formato da duração é inválido")
        @NotBlank(message = "Duração é obrigatória")
        String duracao
    ) {
}
