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
    private static final String STATUS = "status";
    private static final String UNPROCESSABLE_ENTITY = "Unprocessable Entity";

    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleConflict() {
        // Nothing to do
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(STATUS, "422");
        errors.put(ERROR, UNPROCESSABLE_ENTITY);
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(ClienteNaoEncontadoException.class)
    public Map<String, String> clienteNaoEncontadoException(ClienteNaoEncontadoException exception) {
        Map<String, String> error = new HashMap<>();
        error.put(ERROR, exception.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ClienteSemEnderecoDomiciliarException.class)
    public Map<String, String> clienteNaoEncontadoException(ClienteSemEnderecoDomiciliarException exception) {
        Map<String, String> erro = new HashMap<>();
        erro.put(STATUS, "422");
        erro.put(ERROR, UNPROCESSABLE_ENTITY);
        erro.put("enderecoDomiciliar", exception.getMessage());
        return erro;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ClienteSemEnderecoCobrancaException.class)
    public Map<String, String> clienteNaoEncontadoException(ClienteSemEnderecoCobrancaException exception) {
        Map<String, String> erro = new HashMap<>();
        erro.put(STATUS, "422");
        erro.put(ERROR, UNPROCESSABLE_ENTITY);
        erro.put("enderecoCobranca", exception.getMessage());
        return erro;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ClienteComCPFJaCadastroException.class)
    public Map<String, String> clienteNaoEncontadoException(ClienteComCPFJaCadastroException exception) {
        Map<String, String> erro = new HashMap<>();
        erro.put(STATUS, "409");
        erro.put(ERROR, "Conflict");
        erro.put("cliente", exception.getMessage());
        return erro;
    }
}