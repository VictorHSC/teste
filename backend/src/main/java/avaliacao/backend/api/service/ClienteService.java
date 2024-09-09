package avaliacao.backend.api.service;

import avaliacao.backend.exception.ClienteComCPFJaCadastroException;
import avaliacao.backend.exception.ClienteNaoEncontadoException;
import avaliacao.backend.api.repository.ClienteRepository;
import avaliacao.backend.entity.Cliente;

import avaliacao.backend.exception.ClienteSemEnderecoCobrancaException;
import avaliacao.backend.exception.ClienteSemEnderecoDomiciliarException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente adicionarAtualizarCliente(Cliente cliente) {
        if (cliente.getEnderecoDomiciliar() == null) throw new ClienteSemEnderecoDomiciliarException();
        if (cliente.getEnderecoCobranca() == null) throw new ClienteSemEnderecoCobrancaException();
        try {
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new ClienteComCPFJaCadastroException();
        }
    }

    @Transactional
    public Cliente obterClientePorId(Long clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(ClienteNaoEncontadoException::new);
    }

    public Page<Cliente> obterListagemClientes(Integer pagina) {
        PageRequest pageable = PageRequest.of(pagina, 10);
        return clienteRepository.findAll(pageable);
    }

    public void removerCliente(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}