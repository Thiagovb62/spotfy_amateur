package org.example.spotfy.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @PostMapping(value = "/salvar", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Música salva com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao salvar música"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @ResponseBody
    @ResponseStatus (code = org.springframework.http.HttpStatus.CREATED)
    public ResponseEntity<DetalharMusicasDto> salvar(@RequestBody @Valid @Parameter CadastrarMusicasDto dto, UriComponentsBuilder uriBuilder) throws IllegalArgumentException {
            Musicas musica = service.adicionarMusicaAoCatologo(dto);
            var uri =  uriBuilder.path("/medicos/{id}").buildAndExpand(musica.getId()).toUri();
            return ResponseEntity.created(uri).body(new DetalharMusicasDto(musica));
    }

    @GetMapping(value = "/listar", produces = "application/json")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de músicas no catálogo"),
    })
            public ResponseEntity<Page<DetalharMusicasDto>> listar( @PageableDefault(size = 5,sort = {"nome"}) @Parameter Pageable pageable){
        Page<Musicas> musicas = service.listarMusicasNoCatalogo( pageable);
        return ResponseEntity.ok().body(DetalharMusicasDto.convert(musicas));
    }

    @GetMapping(value = "/listar/artista", produces = "application/json")
    @ResponseBody
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna a lista de músicas do artista"),
        @ApiResponse(responseCode = "404", description = "Artista não encontrado")

    })
    public ResponseEntity<Page<DetalharMusicasDto>> listarPorArtista(@RequestBody @NotBlank String artista, @PageableDefault(size = 5,sort = {"nome"}) Pageable pageable){
        Page<Musicas> musicas = service.listaMusicasByArtista(artista, pageable);
        return ResponseEntity.ok().body(DetalharMusicasDto.convert(musicas));
    }
}
