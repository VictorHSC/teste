package avaliacao.frontend.service;

import avaliacao.frontend.dto.ClienteDTO;
import avaliacao.frontend.dto.PaginaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ClienteService {

    private static final String X_API_KEY = "X-API-KEY";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${backend.api.url}")
    private String apiBaseUrl;

    @Value("${backend.api.key}")
    private String apiKey;

    public ClienteDTO salvarCliente(ClienteDTO clienteDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_API_KEY, apiKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<ClienteDTO> entity = new HttpEntity<>(clienteDTO, headers);

        ResponseEntity<ClienteDTO> response = restTemplate.exchange(apiBaseUrl, HttpMethod.PUT, entity, ClienteDTO.class);

        return response.getBody();
    }

    public ClienteDTO buscarClientePorId(Long clienteId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_API_KEY, apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ClienteDTO> response = restTemplate.exchange(apiBaseUrl + "/" + clienteId, HttpMethod.GET, entity, ClienteDTO.class);

        return response.getBody();
    }

    public PaginaDTO<ClienteDTO> obterListagemClientes(Integer pagina) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_API_KEY, apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<PaginaDTO<ClienteDTO>> response = restTemplate.exchange(apiBaseUrl + "/listagem/" + pagina, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });

        return response.getBody();
    }

    public Integer removerCliente(Long clienteId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_API_KEY, apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Void> response = restTemplate.exchange(apiBaseUrl + "/" + clienteId, HttpMethod.DELETE, entity, Void.class);

        return response.getStatusCode().value();
    }
}
