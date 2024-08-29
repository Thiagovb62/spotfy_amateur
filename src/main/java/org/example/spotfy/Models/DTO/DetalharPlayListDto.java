package org.example.spotfy.Models.DTO;

import org.example.spotfy.Models.PlayList;
import org.example.spotfy.Models.SpotUser;
import org.springframework.data.domain.Page;

import java.util.List;

public record DetalharPlayListDto(Long id, String nome, String descricao, String created_by, List<DetalharMusicasDto> musicas, List<SpotUser> spotUsers) {

    public DetalharPlayListDto(PlayList playList) {
        this(playList.getId(),
                playList.getNome(),
                playList.getDescricao(),
                playList.getCreated_by(),
                playList.getMusicas().stream().map(DetalharMusicasDto::new).toList(),
                playList.getSpotUser().stream().toList());
    }

    public static List<DetalharPlayListDto> convert(List<PlayList> playLists) {
        return playLists.stream().map(DetalharPlayListDto::new).toList();
    }

    public static Page<DetalharPlayListDto> convert(Page<PlayList> playLists) {
        return playLists.map(DetalharPlayListDto::new);
    }
}