package avaliacao.frontend.service;

import avaliacao.frontend.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${backend.api.url}")
    private String API_BASE_URL;

    public List<ClienteDTO> listarTodos() {
        // Fazendo uma requisição GET para o endpoint do backend
        ResponseEntity<ClienteDTO[]> response = restTemplate.getForEntity(API_BASE_URL, ClienteDTO[].class);

        // Convertendo o array de ClienteDTO para uma lista
        return Arrays.asList(response.getBody());
    }

    public ClienteDTO salvarCliente(ClienteDTO clienteDTO) {
        restTemplate.put(API_BASE_URL, clienteDTO);
        return clienteDTO; // Retornar o cliente atualizado ou criado
    }

    public void removerCliente(Long cliente_id) {
        restTemplate.delete(API_BASE_URL + "/" + cliente_id);
    }

    public ClienteDTO buscarClientePorId(Long cliente_id) {
        return restTemplate.getForObject(API_BASE_URL + "/" + cliente_id, ClienteDTO.class);
    }
}
