package br.furb.zerify.zerifyapi.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerImpl {

    @ExceptionHandler(exception = ServiceException.class)
    public ResponseEntity<ServiceException.Error> handleServiceExceptionError(ServiceException ex) {
        return ex.getResponse();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<RecFieldError>> handle400Error(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(RecFieldError::new).collect(Collectors.toList()));
    }

    public record RecFieldError(String field, String message) {
        public RecFieldError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
