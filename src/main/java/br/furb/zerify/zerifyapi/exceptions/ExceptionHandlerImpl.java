package br.furb.zerify.zerifyapi.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerImpl {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ServiceException.Error> handleServiceExceptionError(ServiceException ex) {
        return ex.getResponse();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<RecFieldError>> handle400Error(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(RecFieldError::new).collect(Collectors.toList()));
    }

    // Handle DB constraint violations (e.g. unique constraint on despensa.usuario_id)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        // For simplicity return a 409 with a readable message; more granular mapping could inspect cause for constraint name
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Data integrity violation: " + ex.getMostSpecificCause().getMessage());
    }

    public record RecFieldError(String field, String message) {
        public RecFieldError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
