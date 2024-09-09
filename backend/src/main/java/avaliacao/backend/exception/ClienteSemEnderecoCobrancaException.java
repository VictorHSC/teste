package avaliacao.backend.exception;

public class ClienteSemEnderecoCobrancaException extends RuntimeException {

    public ClienteSemEnderecoCobrancaException() {
        super("O objeto 'enderecoCobranca' é de preenchimento obrigatório!");
    }
}