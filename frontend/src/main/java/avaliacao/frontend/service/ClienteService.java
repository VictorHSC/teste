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

    @Autowired
    private RestTemplate restTemplate;

    @Value("${backend.api.url}")
    private String API_BASE_URL;

    @Value("${backend.api.key}")
    private String API_KEY;

    public ClienteDTO salvarCliente(ClienteDTO clienteDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", API_KEY);
        headers.set("Content-Type", "application/json");

        HttpEntity<ClienteDTO> entity = new HttpEntity<>(clienteDTO, headers);

        ResponseEntity<ClienteDTO> response = restTemplate.exchange(
                API_BASE_URL,
                HttpMethod.PUT,
                entity,
                ClienteDTO.class
        );

        return response.getBody();
    }

    public ClienteDTO buscarClientePorId(Long cliente_id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ClienteDTO> response = restTemplate.exchange(
                API_BASE_URL + "/" + cliente_id,
                HttpMethod.GET,
                entity,
                ClienteDTO.class
        );

        return response.getBody();
    }

    public PaginaDTO<ClienteDTO> obterListagemClientes(Integer pagina) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<PaginaDTO<ClienteDTO>> response = restTemplate.exchange(
                API_BASE_URL + "/listagem/" + pagina,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }

    public Integer removerCliente(Long cliente_id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                API_BASE_URL + "/" + cliente_id,
                HttpMethod.DELETE,
                entity,
                Void.class
        );

        return response.getStatusCode().value();
    }
}
