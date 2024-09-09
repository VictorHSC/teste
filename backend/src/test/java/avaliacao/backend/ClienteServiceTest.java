package avaliacao.backend;

import avaliacao.backend.api.repository.ClienteRepository;
import avaliacao.backend.api.service.ClienteService;
import avaliacao.backend.entity.Cliente;
import avaliacao.backend.entity.Endereco;
import avaliacao.backend.exception.ClienteNaoEncontadoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

class ClienteServiceTest {

    @Spy
    @InjectMocks
    private ClienteService clienteService;

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private ClienteRepository clienteRepository;

    private Endereco enderecoA;
    private Endereco enderecoB;
    private Endereco enderecoC;

    private Cliente clienteJoao;
    private Cliente clienteMaria;
    private Page<Cliente> listagemClientes;

    @BeforeEach
    void inicializarCenariosTeste() {
        MockitoAnnotations.initMocks(this);

        enderecoA = new Endereco();
        enderecoA.setId(1L);
        enderecoA.setCep("58057-546");
        enderecoA.setLogradouro("Rua Comerciante Ednaldo Paiva de Araújo");
        enderecoA.setNumero("240");
        enderecoA.setBairro("Mangabeira");
        enderecoA.setCidade("João Pessoa");
        enderecoA.setEstado("PB");

        enderecoB = new Endereco();
        enderecoB.setId(2L);
        enderecoB.setCep("69907-800");
        enderecoB.setLogradouro("Beco da Amizade");
        enderecoB.setNumero("63");
        enderecoB.setBairro("Belo Jardim I");
        enderecoB.setCidade("Rio Branco");
        enderecoB.setEstado("AC");

        enderecoC = new Endereco();
        enderecoC.setId(3L);
        enderecoC.setCep("64215-363");
        enderecoC.setLogradouro("Rua Francisco Sousa dos Santos");
        enderecoC.setNumero("983");
        enderecoC.setBairro("Pindorama");
        enderecoC.setCidade("Parnaíba");
        enderecoC.setEstado("PI");

        clienteJoao = new Cliente();
        clienteJoao.setId(1L);
        clienteJoao.setCpf("221.017.060-59");
        clienteJoao.setNome("João");
        clienteJoao.setSobrenome("Fernandes");
        clienteJoao.setDataNascimento(LocalDate.now());
        clienteJoao.setTelefone("(48) 99887-6655");
        clienteJoao.setWhatsapp("(48) 99887-6655");
        clienteJoao.setEmail("joao@gmail.com");
        clienteJoao.setEnderecoDomiciliar(enderecoA);
        clienteJoao.setEnderecoCobranca(enderecoB);

        clienteMaria = new Cliente();
        clienteMaria.setId(2L);
        clienteMaria.setCpf("322.450.670-96");
        clienteMaria.setNome("Maria");
        clienteMaria.setSobrenome("Pereira");
        clienteMaria.setDataNascimento(LocalDate.of(1992, 4, 13));
        clienteMaria.setTelefone("(48) 97721-6655");
        clienteMaria.setWhatsapp("(48) 97721-4444");
        clienteMaria.setEmail("maria@gmail.com");
        clienteMaria.setEnderecoDomiciliar(enderecoC);
        clienteMaria.setEnderecoCobranca(enderecoC);

        listagemClientes = new PageImpl<>(Arrays.asList(clienteJoao, clienteMaria));
    }

    @Test
    void deveAdicionarAtualizarCliente() {
        doReturn(clienteJoao).when(clienteRepository).save(any(Cliente.class));

        Cliente clienteSalvo = clienteService.adicionarAtualizarCliente(clienteJoao);

        assertEquals(clienteJoao.getId(), clienteSalvo.getId());
        assertEquals(clienteJoao.getCpf(), clienteSalvo.getCpf());
        assertEquals(clienteJoao.getNome(), clienteSalvo.getNome());
        assertEquals(clienteJoao.getSobrenome(), clienteSalvo.getSobrenome());
        assertEquals(clienteJoao.getDataNascimento(), clienteSalvo.getDataNascimento());
        assertEquals(clienteJoao.getTelefone(), clienteSalvo.getTelefone());
        assertEquals(clienteJoao.getWhatsapp(), clienteSalvo.getWhatsapp());
        assertEquals(clienteJoao.getEmail(), clienteSalvo.getEmail());
        assertEquals(clienteJoao.getEnderecoDomiciliar(), clienteSalvo.getEnderecoDomiciliar());
        assertEquals(clienteJoao.getEnderecoCobranca(), clienteSalvo.getEnderecoCobranca());
    }

    @Test
    void deveObterClientePorId() {
        when(clienteRepository.findById(any(Long.class))).thenReturn(Optional.of(clienteJoao));

        Cliente clienteObtido = clienteService.obterClientePorId(1L);

        assertEquals(clienteJoao.getId(), clienteObtido.getId());
        assertEquals(clienteJoao.getCpf(), clienteObtido.getCpf());
        assertEquals(clienteJoao.getNome(), clienteObtido.getNome());
        assertEquals(clienteJoao.getSobrenome(), clienteObtido.getSobrenome());
        assertEquals(clienteJoao.getDataNascimento(), clienteObtido.getDataNascimento());
        assertEquals(clienteJoao.getTelefone(), clienteObtido.getTelefone());
        assertEquals(clienteJoao.getWhatsapp(), clienteObtido.getWhatsapp());
        assertEquals(clienteJoao.getEmail(), clienteObtido.getEmail());
        assertEquals(clienteJoao.getEnderecoDomiciliar(), clienteObtido.getEnderecoDomiciliar());
        assertEquals(clienteJoao.getEnderecoCobranca(), clienteObtido.getEnderecoCobranca());
    }

    @Test
    void deveObterListagemClientes() {
        doReturn(listagemClientes).when(clienteRepository).findAll(any(PageRequest.class));

        Page<Cliente> clientes = clienteService.obterListagemClientes(0);

        assertEquals(2, clientes.getTotalElements());
        assertEquals(1, clientes.getTotalPages());

        // Asserts Joao
        assertEquals(clientes.getContent().get(0).getNome(), clienteJoao.getNome());
        assertEquals(clientes.getContent().get(0).getEnderecoDomiciliar(), enderecoA);
        assertEquals(clientes.getContent().get(0).getEnderecoCobranca(), enderecoB);

        // Asserts Maria
        assertEquals(clientes.getContent().get(1).getNome(), clienteMaria.getNome());
        assertEquals(clientes.getContent().get(1).getEnderecoDomiciliar(), enderecoC);
        assertEquals(clientes.getContent().get(1).getEnderecoCobranca(), enderecoC);
    }

    @Test
    void deveRemoverCliente() {
        clienteService.removerCliente(1L);

        assertEquals(1L, clienteJoao.getId());
    }

    @Test
    void deveLancarClienteNaoEncontradoException() {
        Exception exception = assertThrows(ClienteNaoEncontadoException.class, () -> {
            clienteService.obterClientePorId(3L);
        });

        assertEquals("Cliente não encontrado!", exception.getMessage());
    }
}