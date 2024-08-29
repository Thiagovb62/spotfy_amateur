package org.example.spotfy.Controller;

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
@RequestMapping(value = "/user")
public class UserSpotController {

    @Autowired
    private UserSpotRepository userSpotRepository;


    @PostMapping("/register")
    public void salvar(@RequestBody @Valid CadastrarUserSpotDto dto){
        if (userSpotRepository.existsByNickName(dto.nickName())){
            throw new IllegalArgumentException("Nome de Usuario já existente!");
        }
        SpotUser userSpot = new SpotUser(dto);
        userSpotRepository.save(userSpot);
    }

}
