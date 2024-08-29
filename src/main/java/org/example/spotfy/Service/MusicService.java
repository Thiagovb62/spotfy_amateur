package org.example.spotfy.Service;

import org.example.spotfy.Infra.Exception.MusicWithSingerArealdyExistException;
import org.example.spotfy.Infra.Exception.RecordDoesntExistExecetion;
import org.example.spotfy.Models.DTO.CadastrarMusicasDto;
import org.example.spotfy.Models.Musicas;
import org.example.spotfy.Repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {

    @Autowired
    private MusicaRepository musicasRepository;


    public Musicas adicionarMusicaAoCatologo(CadastrarMusicasDto dto) {

        Musicas musica = new Musicas(dto);
        return musicasRepository.save(musica);
    }

    public Page<Musicas> listarMusicasNoCatalogo(Pageable pageable){
        return  musicasRepository.findAll(pageable);
    }

    public Page<Musicas> listaMusicasByArtista(String artista, Pageable pageable){

        Page<Musicas> musicas = musicasRepository.findAllByArtista(artista, pageable);

        if(musicas.isEmpty()){
            throw new RecordDoesntExistExecetion(String.format("Nao foi possivel localizar musicas com o Artista %", artista));
        }

        return musicasRepository.findAllByArtista( artista, pageable);
    }

}
