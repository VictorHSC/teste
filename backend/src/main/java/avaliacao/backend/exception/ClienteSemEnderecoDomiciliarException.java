package avaliacao.backend.exception;

public class ClienteSemEnderecoDomiciliarException extends RuntimeException {

    public ClienteSemEnderecoDomiciliarException() {
        super("O objeto 'enderecoDomiciliar' é de preenchimento obrigatório!");
    }
}