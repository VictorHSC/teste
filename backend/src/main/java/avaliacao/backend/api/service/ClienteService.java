package avaliacao.backend.api.service;

import avaliacao.backend.exception.ClienteNaoEncontadoException;
import avaliacao.backend.api.repository.ClienteRepository;
import avaliacao.backend.entity.Cliente;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente adicionarAtualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente obterClientePorId(Long cliente_id) {
        return clienteRepository.findById(cliente_id).orElseThrow(ClienteNaoEncontadoException::new);
    }

    public Page<Cliente> obterListagemClientes(Integer pagina) {
        PageRequest pageable = PageRequest.of(pagina, 10);
        return clienteRepository.findAll(pageable);
    }

    public void removerCliente(Long cliente_id) {
        clienteRepository.deleteById(cliente_id);
    }
}