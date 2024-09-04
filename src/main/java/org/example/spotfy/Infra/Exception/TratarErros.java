package org.example.spotfy.Infra.Exception;


import jakarta.persistence.EntityNotFoundException;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.nio.file.AccessDeniedException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class TratarErros{


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(SpotFyException.class)
    public ProblemDetail tratarErroDuplicado(SpotFyException ex) {
        return ex.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ProblemDetail DadosErroValidacao(MethodArgumentNotValidException e) {

        var fieldErros = e.getFieldErrors()
                .stream()
                .map(f ->new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("our request parameters didn't validate");
        pb.setProperty("invalidParams", fieldErros);

        return pb;
    }

    @ExceptionHandler(RecordDoesntExistExecetion.class)
    public ProblemDetail tratarErroRegistroNaoEncontrado(RecordDoesntExistExecetion ex) {

        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("Record doesn't exist");
        pb.setDetail(ex.getMessage());
        pb.setType(URI.create("/problems/record-doesnt-exist"));

        return pb;

    }

    @ExceptionHandler(MusicWithSingerArealdyExistException.class)
    public ProblemDetail tratarErroMusicaDuplicada(MusicWithSingerArealdyExistException ex) {

        var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        pb.setTitle("Music with singer already exist");
        pb.setDetail(ex.getMessage());
        pb.setType(URI.create("/problems/music-with-singer-already-exist"));

        return pb;

    }

    @ExceptionHandler(DuplicateMusicInPlayListException.class)
    public ProblemDetail tratarErroMusicaDuplicadaNaPlaylist(DuplicateMusicInPlayListException ex) {

        var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        pb.setTitle("Duplicate Music in Playlist");
        pb.setDetail(ex.getMessage());
        pb.setType(URI.create("/problems/duplicate-music-in-playlist"));

        return pb;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail tratarErroBadCredentials() {

        var pb = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);

        pb.setTitle("Bad Credentials");
        pb.setDetail("Credenciais inválidas");
        pb.setType(URI.create("/problems/bad-credentials"));

        return pb;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail tratarErroAuthentication() {

        var pb = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);

        pb.setTitle("Authentication Error");
        pb.setDetail("Falha na autenticação");
        pb.setType(URI.create("/problems/authentication-error"));

        return pb;

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail tratarErroAcessoNegado() {

        var pb = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);

        pb.setTitle("Access Denied");
        pb.setDetail("Acesso negado");
        pb.setType(URI.create("/problems/access-denied"));

        return pb;

    }



    private record InvalidParam(String field, String message) {
    }
}
