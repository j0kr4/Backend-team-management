package fr.sdv.cnit.university.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TeamInvalidException.class)
    public ResponseEntity<String> handleTeamInvalidException(TeamInvalidException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}