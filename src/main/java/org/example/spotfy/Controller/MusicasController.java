package org.example.spotfy.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.spotfy.Models.DTO.CadastrarMusicasDto;
import org.example.spotfy.Models.DTO.DetalharMusicasDto;
import org.example.spotfy.Models.Musicas;
import org.example.spotfy.Service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/musicas")
@EnableMethodSecurity(securedEnabled = true)
public class MusicasController {

    @Autowired
    private MusicService service;


    @PostMapping("/salvar")
    public ResponseEntity<DetalharMusicasDto> salvar(@RequestBody @Valid CadastrarMusicasDto dto, UriComponentsBuilder uriBuilder) throws IllegalArgumentException {
            Musicas musica = service.adicionarMusicaAoCatologo(dto);
            var uri =  uriBuilder.path("/medicos/{id}").buildAndExpand(musica.getId()).toUri();
            return ResponseEntity.created(uri).body(new DetalharMusicasDto(musica));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalharMusicasDto>> listar( @PageableDefault(size = 5,sort = {"nome"}) Pageable pageable){
        Page<Musicas> musicas = service.listarMusicasNoCatalogo( pageable);
        return ResponseEntity.ok().body(DetalharMusicasDto.convert(musicas));
    }

    @GetMapping("/listar/artista")
    public ResponseEntity<Page<DetalharMusicasDto>> listarPorArtista(@RequestBody @NotBlank String artista, @PageableDefault(size = 5,sort = {"nome"}) Pageable pageable){
        Page<Musicas> musicas = service.listaMusicasByArtista(artista, pageable);
        return ResponseEntity.ok().body(DetalharMusicasDto.convert(musicas));
    }
}
