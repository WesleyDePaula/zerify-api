package br.furb.zerify.zerifyapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ServiceException extends RuntimeException {

    private final HttpStatus status;

    private final String message;

    public ServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    protected ResponseEntity<Error> getResponse() {
        switch (status) {
            case BAD_REQUEST -> {
                return ResponseEntity.badRequest().body(new Error(this.message));
            }
            case NOT_FOUND -> {
                return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(new Error(this.message));
            }
            case INTERNAL_SERVER_ERROR,
            default -> {
                return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(new Error(this.message));
            }
        }
    }

    public record Error(String errorMessage){}

}
