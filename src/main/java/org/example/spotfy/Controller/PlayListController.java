package org.example.spotfy.Controller;


import jakarta.validation.Valid;
import org.example.spotfy.Models.DTO.*;
import org.example.spotfy.Models.PlayList;
import org.example.spotfy.Service.PlayListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/playlist")
public class PlayListController {

    private static final Logger log = LoggerFactory.getLogger(PlayListController.class);

    @Autowired
    private PlayListService service;

    @GetMapping("/list")
    public ResponseEntity<List<PlayList>> list(){
        List<PlayList> playLists = service.ListAllPlayLists();
        return ResponseEntity.ok().body(playLists);
    }

    @GetMapping("/list/{user_id}")
    public ResponseEntity<List<DetalharPlayListDto>> list(@PathVariable Long user_id){
        System.out.println("user_id = " + user_id);
        List<PlayList> playLists = service.ListMyPlayLists(user_id);
        return ResponseEntity.ok().body(DetalharPlayListDto.convert(playLists));
    }

    @GetMapping("/list/artista")
    public ResponseEntity<List<DetalharPlayListDto>> listByArtist(@RequestBody String name,@PageableDefault(size = 5,sort = {"nome"}) Pageable pageable){
        List<PlayList> playLists = service.listAllPlayListSWithArtistName(name, pageable);
        return ResponseEntity.ok().body(DetalharPlayListDto.convert(playLists));
    }

    @PostMapping("/criar/{user_id}")
    public ResponseEntity<CreateFisrtPlayListDetalheDto> salvar(@PathVariable Long user_id, @RequestBody @Valid CadastrarPlayListDto dto,UriComponentsBuilder uriBuilder){
        System.out.println("user_id2 = " + user_id);
       PlayList playList = service.CreateMyPlaylist(user_id, dto);
        var uri =  uriBuilder.path("/medicos/{id}").buildAndExpand(playList.getId()).toUri();
       return ResponseEntity.created(uri).body(new CreateFisrtPlayListDetalheDto(playList));
    }

    @PostMapping("/add/{playlist_id}")
    public ResponseEntity<DetalharPlayListDto> addMusic(@PathVariable Long playlist_id, @RequestBody @Valid CadastroMusicaOnPlayList dto, UriComponentsBuilder uriBuilder){
        PlayList playList = service.addMusic(playlist_id, dto);
        var uri =  uriBuilder.path("/medicos/{id}").buildAndExpand(playList.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalharPlayListDto(playList));
    }

    @PostMapping("/anexar/{user_id}")
    public ResponseEntity<String> anexarPlaylistExistente(@PathVariable Long user_id, @RequestBody AnexarPlayListDto dto, UriComponentsBuilder uriBuilder){
        PlayList playList = service.adicionarPlaylistExistenteAoPerfil(user_id, dto.nome() );
        var uri =  uriBuilder.path("/medicos/{id}").buildAndExpand(playList.getId()).toUri();
        return ResponseEntity.created(uri).body("Playlist anexada com sucesso!");
    }

}
