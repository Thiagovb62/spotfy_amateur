package org.example.spotfy.Models.DTO;

import org.example.spotfy.Models.SpotUser;

public record DetalharUserSpotDto(

        Long id,
        String nickName,
        String png
) {
    public DetalharUserSpotDto(SpotUser spotUser) {
        this(spotUser.getId(),
                spotUser.getNickName(),
                spotUser.getPng());
    }
}
