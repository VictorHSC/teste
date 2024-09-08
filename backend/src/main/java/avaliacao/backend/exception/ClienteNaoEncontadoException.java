package avaliacao.backend.exception;

public class ClienteNaoEncontadoException extends RuntimeException {

    public ClienteNaoEncontadoException() {
        super("Cliente não encontrado!");
    }
}