package org.example.spotfy.Controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.spotfy.Models.DTO.CadastrarUserSpotDto;
import org.example.spotfy.Models.SpotUser;
import org.example.spotfy.Repository.UserSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/userSpot")
public class UserSpotController {

    @Autowired
    private UserSpotRepository userSpotRepository;


    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao cadastrar usuário")
    @ApiResponse(responseCode = "403", description = "Acesso negado")
    @Transactional
    public void salvar(@RequestBody @Valid CadastrarUserSpotDto dto){
        if (userSpotRepository.existsByNickName(dto.nickName())){
            throw new IllegalArgumentException("Nome de Usuario já existente!");
        }
        SpotUser userSpot = new SpotUser(dto);
        userSpotRepository.save(userSpot);
    }

}
