package avaliacao.backend.api.service;

import avaliacao.backend.api.repository.ClienteRepository;
import avaliacao.backend.entity.Cliente;
import ch.qos.logback.core.net.server.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente adicionarAtualizarCliente(Cliente cliente) {
        System.out.println(cliente.toString());
        return clienteRepository.save(cliente);
    }

    public Cliente obterClientePorId(Long cliente_id) {
        return clienteRepository.findById(cliente_id).orElse(null);
    }

    public void removerCliente(Long cliente_id) {
         clienteRepository.deleteById(cliente_id);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
}
