package org.example.spotfy.Service;


import org.example.spotfy.Infra.Exception.DuplicateMusicInPlayListException;
import org.example.spotfy.Infra.Exception.RecordDoesntExistExecetion;
import org.example.spotfy.Models.DTO.CadastrarPlayListDto;
import org.example.spotfy.Models.DTO.CadastroMusicaOnPlayList;
import org.example.spotfy.Models.Musicas;
import org.example.spotfy.Models.PlayList;
import org.example.spotfy.Repository.MusicaRepository;
import org.example.spotfy.Repository.PlaylistRepository;
import org.example.spotfy.Repository.UserSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private UserSpotRepository userSpotRepository;


    public List<PlayList> ListAllPlayLists(){
        return playlistRepository.findAll();
    }


    public List<PlayList> ListMyPlayLists(Long user_id){
        List<PlayList> playLists = playlistRepository.findAllPlayListByUser_Spot_Id(user_id);
        if (playLists.isEmpty()){
            throw new RecordDoesntExistExecetion(String.format("Nao foi possivel localizar playlists do usuario %s", user_id));
        }
        return playLists;
    }


    public Page<PlayList> listAllPlayListSWithArtistName(String nome, Pageable page){
        Page<PlayList> playLists = playlistRepository.findPlayListsByAtistName(nome, page);
        if (playLists.isEmpty()){
            throw new RecordDoesntExistExecetion(String.format("Nao foi possivel localizar playlists com o Artista %s", nome));
        }
        return playLists;
    }


    public PlayList addMusic(Long playlistId, CadastroMusicaOnPlayList dto) {
        PlayList playList = playlistRepository.findById(playlistId).orElseThrow(() -> new RecordDoesntExistExecetion(String.format("Playlist with unique indetificator %s not found", playlistId)));

        Musicas musica = musicaRepository.findByNome(dto.NomeMusica());

        if (musica == null){
            throw new RecordDoesntExistExecetion(String.format("Music with name %s not found", dto.NomeMusica()));
        }

        boolean isMusicInPlayList = playList.getMusicas().stream()
                .anyMatch(m -> m.getNome().equalsIgnoreCase(dto.NomeMusica()) && m.getArtista().equals(dto.NomeArtista()));

        if (isMusicInPlayList) {
            throw new DuplicateMusicInPlayListException(String.format("Music %s already in playlist", dto.NomeMusica()));
        }

        playList.getMusicas().add(musica);
        playlistRepository.save(playList);

        return playList;
    }

    public PlayList CreateMyPlaylist(Long user_id,  CadastrarPlayListDto dto){
        PlayList playList = new PlayList(dto);
        playList.setCreated_by(getUserNickName(user_id));
        playList.getSpotUser().add(userSpotRepository.findById(user_id).get());
        playlistRepository.save(playList);
        return playList;
    }

    public PlayList adicionarPlaylistExistenteAoPerfil(Long user_id, String name){
        PlayList playList = playlistRepository.findPlayListByName(name);
        if (playList == null){
            throw new RecordDoesntExistExecetion(String.format("Playlist with name %s not found", name));
        }
        playList.getSpotUser().add(userSpotRepository.findById(user_id).get());
        playlistRepository.save(playList);
        return playList;
    }

    private String getUserNickName(Long user_id){
        return userSpotRepository.findById(user_id).get().getNickName();
    }
}
