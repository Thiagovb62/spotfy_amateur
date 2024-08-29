package org.example.spotfy.Infra.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;

public class MusicWithSingerArealdyExistException extends RuntimeException {

    public MusicWithSingerArealdyExistException(String message) {
        super(message);
    }

}
