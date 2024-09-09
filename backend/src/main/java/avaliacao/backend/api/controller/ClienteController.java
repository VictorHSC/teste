package avaliacao.backend.api.controller;

import avaliacao.backend.entity.Cliente;
import avaliacao.backend.api.service.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PutMapping()
    public ResponseEntity<Cliente> adicionarAtualizarCliente(@Valid @RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.adicionarAtualizarCliente(cliente), HttpStatus.CREATED);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable Long clienteId) {
        return new ResponseEntity<>(clienteService.obterClientePorId(clienteId), HttpStatus.OK);
    }

    @GetMapping("/listagem/{pagina}")
    public ResponseEntity<Page<Cliente>> obterListagemClientes(@PathVariable Integer pagina) {
        return new ResponseEntity<>(clienteService.obterListagemClientes(pagina), HttpStatus.OK);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> removerCliente(@PathVariable Long clienteId) {
        clienteService.removerCliente(clienteId);
        return ResponseEntity.ok().build();
    }
}
