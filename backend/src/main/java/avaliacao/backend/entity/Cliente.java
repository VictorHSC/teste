package avaliacao.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O campo 'cpf' é de preenchimento obrigatório!")
    private String cpf;

    @Column(nullable = false)
    @NotBlank(message = "O campo 'nome' é de preenchimento obrigatório!")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "O campo 'sobrenome' é de preenchimento obrigatório!")
    private String sobrenome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    @NotBlank(message = "O campo 'telefone' é de preenchimento obrigatório!")
    private String telefone;

    private String whatsapp;

    @Email
    @Column(nullable = false)
    @NotBlank(message = "O campo 'email' é de preenchimento obrigatório!")
    private String email;

    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_domiciliar_id", nullable = false)
    private Endereco enderecoDomiciliar;

    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_cobranca_id", nullable = false)
    private Endereco enderecoCobranca;
}