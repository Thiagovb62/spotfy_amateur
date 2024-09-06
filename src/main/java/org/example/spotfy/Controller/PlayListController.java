package org.example.spotfy.Controller;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.spotfy.Models.DTO.*;
import org.example.spotfy.Models.PlayList;
import org.example.spotfy.Service.PlayListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping(value ="/list", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de playlists"),
    })
    public ResponseEntity<List<PlayList>> list() {
        List<PlayList> playLists = service.ListAllPlayLists();
        return ResponseEntity.ok().body(playLists);
    }

    @GetMapping(value ="/list/{user_id}", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de playlists do usuário"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<List<DetalharPlayListDto>> list(@PathVariable Long user_id) {
        System.out.println("user_id = " + user_id);
        List<PlayList> playLists = service.ListMyPlayLists(user_id);
        return ResponseEntity.ok().body(DetalharPlayListDto.convert(playLists));
    }

    @GetMapping(value ="/list/artista", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de playlists com o artista"),
            @ApiResponse(responseCode = "404", description = "Artista não encontrado")
    })
    public ResponseEntity<Page<DetalharPlayListDto>> listByArtist(@RequestBody @Parameter String name, @PageableDefault(size = 5, sort = {"nome"}) @Parameter Pageable pageable) {
        Page<PlayList> playLists = service.listAllPlayListSWithArtistName(name, pageable);
        return ResponseEntity.ok().body(DetalharPlayListDto.convert(playLists));
    }

    @PostMapping(value ="/criar/{user_id}", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Playlist criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar playlist"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @Transactional
    public ResponseEntity<CreateFisrtPlayListDetalheDto> salvar(@PathVariable @Parameter Long user_id, @RequestBody @Valid @Parameter CadastrarPlayListDto dto, UriComponentsBuilder uriBuilder) {
        PlayList playList = service.CreateMyPlaylist(user_id, dto);
        var uri = uriBuilder.path("/playList/{id}").buildAndExpand(playList.getId()).toUri();
        return ResponseEntity.created(uri).body(new CreateFisrtPlayListDetalheDto(playList));
    }

    @PostMapping(value = "/add/{playlist_id}", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Musica adicionada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Playlist não encontrada"),
            @ApiResponse(responseCode = "404", description = "Musica não encontrada"),
            @ApiResponse(responseCode = "400", description = "Erro ao adicionar musica"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @Transactional
    public ResponseEntity<DetalharPlayListDto> addMusic(@PathVariable @Parameter Long playlist_id, @Parameter @RequestBody @Valid CadastroMusicaOnPlayList dto, UriComponentsBuilder uriBuilder) {
        PlayList playList = service.addMusic(playlist_id, dto);
        var uri = uriBuilder.path("/playList/{id}").buildAndExpand(playList.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalharPlayListDto(playList));
    }

    @PostMapping(value ="/anexar/{user_id}", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Playlist anexada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Playlist não encontrada"),
            @ApiResponse(responseCode = "400", description = "Erro ao anexar playlist"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @Transactional
    public ResponseEntity<String> anexarPlaylistExistente(@PathVariable @Parameter Long user_id, @RequestBody @Parameter AnexarPlayListDto dto, UriComponentsBuilder uriBuilder) {
        PlayList playList = service.adicionarPlaylistExistenteAoPerfil(user_id, dto.nome());
        var uri = uriBuilder.path("/playList/{id}").buildAndExpand(playList.getId()).toUri();
        return ResponseEntity.created(uri).body("Playlist anexada com sucesso!");
    }

}
