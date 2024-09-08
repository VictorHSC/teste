package avaliacao.backend.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;
import java.util.HashMap;

@RestControllerAdvice
public class ControllerAdvice {

    private static final String ERROR = "error";

    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleConflict() {
        // Nothing to do
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    // CLIENTE NAO ENCONTRADO
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(ClienteNaoEncontadoException.class)
    public Map<String, String> clienteNaoEncontadoException(ClienteNaoEncontadoException exception) {
        Map<String, String> error = new HashMap<>();
        error.put(ERROR, exception.getMessage());
        return error;
    }
}