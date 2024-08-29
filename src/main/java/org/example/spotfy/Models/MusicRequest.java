package org.example.spotfy.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MusicRequest {

    @JsonProperty("nomesMusicas")
    public List<String> musicNames;
}