package org.example.spotfy.Models.DTO;

import jakarta.validation.constraints.NotBlank;

public record CadastrarUserSpotDto(
        @NotBlank(message = "Nome é obrigatório")
         String nickName,

         String png
) {
}
