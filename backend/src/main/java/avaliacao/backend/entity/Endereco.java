package avaliacao.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Endereco {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O campo 'cep' é de preenchimento obrigatório!")
    private String cep;

    @Column(nullable = false)
    @NotBlank(message = "O campo 'logradouro' é de preenchimento obrigatório!")
    private String logradouro;

    @Column(nullable = false)
    @NotBlank(message = "O campo 'numero' é de preenchimento obrigatório!")
    private String numero;

    @Column(nullable = false)
    @NotBlank(message = "O campo 'bairro' é de preenchimento obrigatório!")
    private String bairro;

    @Column(nullable = false)
    @NotBlank(message = "O campo 'estado' é de preenchimento obrigatório!")
    private String estado;

    @Column(nullable = false)
    @NotBlank(message = "O campo 'cidade' é de preenchimento obrigatório!")
    private String cidade;
}