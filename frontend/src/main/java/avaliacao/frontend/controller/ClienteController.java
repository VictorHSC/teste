package avaliacao.frontend.controller;

import avaliacao.frontend.dto.ClienteDTO;
import avaliacao.frontend.dto.EnderecoDTO;
import avaliacao.frontend.dto.PaginaDTO;
import avaliacao.frontend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

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
        ClienteDTO cliente = clienteService.salvarCliente(clienteDTO);
        return "redirect:/clientes/editar/" + cliente.getId() + "?sucesso=true";
    }

    @GetMapping()
    public String listarClientes(@RequestParam(defaultValue = "0") Integer pagina, @RequestParam(required = false) Boolean removido, Model model) {
        PaginaDTO<ClienteDTO> clientesPage = clienteService.obterListagemClientes(pagina);
        model.addAttribute("clientes", clientesPage.getContent());
        model.addAttribute("paginaAtual", pagina);
        model.addAttribute("quantidadePaginas", clientesPage.getTotalPages());

        if (removido == Boolean.TRUE) {
            model.addAttribute("mensagem", "Cliente removido com sucesso!");
        }

        return "cliente/listar_clientes";
    }

    @GetMapping("/editar/{cliente_id}")
    public String editarCliente(@PathVariable Long cliente_id, @RequestParam(required = false) Boolean sucesso, Model model) {
        ClienteDTO clienteDTO = clienteService.buscarClientePorId(cliente_id);
        model.addAttribute("cliente", clienteDTO);

        if (sucesso == Boolean.TRUE) {
            model.addAttribute("mensagem", "Cliente salvo com sucesso!");
        }

        return "cliente/formulario_cliente";
    }

    @GetMapping("/remover/{cliente_id}")
    public String removerCliente(@PathVariable Long cliente_id, @RequestHeader(value = "referer", required = false) final String referer, Model model) {
        clienteService.removerCliente(cliente_id);
        return "redirect:" + referer + "&removido=true";
    }
}
