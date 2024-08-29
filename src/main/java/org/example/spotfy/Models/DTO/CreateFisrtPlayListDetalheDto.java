package org.example.spotfy.Models.DTO;

import org.example.spotfy.Models.PlayList;


public record CreateFisrtPlayListDetalheDto(Long id, String nome, String descricao, String created_by) {

    public CreateFisrtPlayListDetalheDto(PlayList playList) {
        this(playList.getId(),
                playList.getNome(),
                playList.getDescricao(),
                playList.getCreated_by());
    }
}
