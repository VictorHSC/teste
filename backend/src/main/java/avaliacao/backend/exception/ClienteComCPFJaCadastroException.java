package avaliacao.backend.exception;

public class ClienteComCPFJaCadastroException extends RuntimeException {

    public ClienteComCPFJaCadastroException() {
        super("Já existe um cliente cadastro com este CPF!");
    }
}