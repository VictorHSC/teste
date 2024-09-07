package avaliacao.frontend.controller;

import avaliacao.frontend.dto.ClienteDTO;
import avaliacao.frontend.dto.EnderecoDTO;
import avaliacao.frontend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Create
    @GetMapping("/novo")
    public String adicionarCliente(Model model) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setEnderecoCobranca(new EnderecoDTO());
        clienteDTO.setEnderecoDomiciliar(new EnderecoDTO());
        model.addAttribute("cliente", clienteDTO);
        return "cliente/formulario_cliente";
    }

    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute("cliente") ClienteDTO clienteDTO) {
        clienteService.salvarCliente(clienteDTO);
        return "redirect:/clientes/listar"; // Redireciona para a lista de clientes após salvar
    }

    // Read
    @GetMapping("/listar")
    public String listarClientes(Model model) {
        List<ClienteDTO> clientes = clienteService.listarTodos();
        model.addAttribute("clientes", clientes);
        return "cliente/listar_clientes";  // Referência ao arquivo Thymeleaf a ser renderizado
    }

    // Update
    @GetMapping("/editar/{cliente_id}")
    public String editarCliente(@PathVariable Long cliente_id, Model model) {
        ClienteDTO clienteDTO = clienteService.buscarClientePorId(cliente_id);
        model.addAttribute("cliente", clienteDTO);
        return "cliente/formulario_cliente";
    }

    // Delete
    @GetMapping("/remover/{cliente_id}")
    public String removerCliente(@PathVariable Long cliente_id, RedirectAttributes redirectAttributes) {
        try {
            clienteService.removerCliente(cliente_id);
            redirectAttributes.addFlashAttribute("mensagem", "Cliente removido com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao remover cliente!");
        }
        return "redirect:/clientes/listar";
    }
}
