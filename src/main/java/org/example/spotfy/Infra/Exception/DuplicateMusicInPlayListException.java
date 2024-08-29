package org.example.spotfy.Infra.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;

public class DuplicateMusicInPlayListException extends RuntimeException {

        public DuplicateMusicInPlayListException(String message) {
            super(message);
        }
}
