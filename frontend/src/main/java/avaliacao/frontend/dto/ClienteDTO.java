package avaliacao.frontend.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

public class ClienteDTO {

    private Long id;

    @NumberFormat(pattern="000.000.000-00")
    private String cpf;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String telefone;
    private String whatsapp;
    private String email;
    private EnderecoDTO enderecoCobranca;
    private EnderecoDTO enderecoDomiciliar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnderecoDTO getEnderecoCobranca() {
        return enderecoCobranca;
    }

    public void setEnderecoCobranca(EnderecoDTO enderecoCobranca) {
        this.enderecoCobranca = enderecoCobranca;
    }

    public EnderecoDTO getEnderecoDomiciliar() {
        return enderecoDomiciliar;
    }

    public void setEnderecoDomiciliar(EnderecoDTO enderecoDomiciliar) {
        this.enderecoDomiciliar = enderecoDomiciliar;
    }
}