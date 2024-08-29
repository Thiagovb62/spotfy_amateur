package org.example.spotfy.Infra.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class SpotFyException extends RuntimeException {


    public ProblemDetail toProblemDetail() {
       var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("Spotfy internal server error");

        return pb;
    }
}
