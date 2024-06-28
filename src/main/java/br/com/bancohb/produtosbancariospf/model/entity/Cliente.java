package br.com.bancohb.produtosbancariospf.model.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import br.com.bancohb.produtosbancariospf.model.value.Endereco;
import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @UuidGenerator
    private UUID id;

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    private String nome;

    private String telefone;

    private String email;

    private double rendaSalarial;

    private String senha;

    // Indica que o campo endereço é uma instância de uma classe embutida, Endereço,
    // cujos campos serão mapeados diretamente na tabela cliente.
    @Embedded
    private Endereco endereco;

    protected Cliente() {
    }

    public Cliente(String cpf, String nome, String telefone, String email, double rendaSalarial, String senha,
            Endereco endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.rendaSalarial = rendaSalarial;
        this.senha = senha;
        this.endereco = endereco;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getRendaSalarial() {
        return rendaSalarial;
    }

    public void setRendaSalarial(double rendaSalarial) {
        this.rendaSalarial = rendaSalarial;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
