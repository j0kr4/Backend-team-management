package fr.sdv.cnit.university.api.exception;

public class TeamInvalidException extends RuntimeException {
    public TeamInvalidException(String errorMessage) {
        super(errorMessage);
    }
}
