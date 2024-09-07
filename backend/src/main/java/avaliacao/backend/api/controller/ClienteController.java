package avaliacao.backend.api.controller;

import avaliacao.backend.entity.Cliente;
import avaliacao.backend.api.service.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return new ResponseEntity<List<Cliente>>(clienteService.listarClientes(), HttpStatus.OK);
    }


    @GetMapping("/{cliente_id}")
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable Long cliente_id) {
        return new ResponseEntity<>(clienteService.obterClientePorId(cliente_id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Cliente> adicionarAtualizarCliente(@Valid @RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.adicionarAtualizarCliente(cliente), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cliente_id}")
    public ResponseEntity<Void> removerCliente(@PathVariable Long cliente_id) {
        clienteService.removerCliente(cliente_id);
        return ResponseEntity.noContent().build();
    }
}
