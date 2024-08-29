package org.example.spotfy.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.spotfy.Models.DTO.CadastrarUserSpotDto;
import org.example.spotfy.Models.DTO.DetalharUserSpotDto;

import java.util.List;

@Entity
@Table(name = "spotUser")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SpotUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    private String png;

    public SpotUser(CadastrarUserSpotDto dto) {
        this.nickName = dto.nickName();
        this.png = dto.png();
    }
}
