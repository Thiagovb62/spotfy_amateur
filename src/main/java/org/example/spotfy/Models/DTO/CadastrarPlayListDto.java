package org.example.spotfy.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastrarPlayListDto(

        @NotBlank(message = "Nome é obrigatório")
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Nome da playlist inválido")
        String nome,

        String descricao
) {
}
