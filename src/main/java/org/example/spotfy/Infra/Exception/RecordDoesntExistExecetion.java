package org.example.spotfy.Infra.Exception;

public class RecordDoesntExistExecetion extends RuntimeException {

    public RecordDoesntExistExecetion(String message) {
        super(message);
    }
}
