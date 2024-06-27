package br.com.bancohb.produtosbancariospf.model.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import br.com.bancohb.produtosbancariospf.model.value.Endereco;
import jakarta.persistence.*;

@Entity
@Table(name = "cliente", uniqueConstraints = @UniqueConstraint(columnNames = { "cpf" }))
public class Cliente {
    @Id
    @UuidGenerator
    private UUID id;

    private String nome;

    private String email;

    private double rendaSalarial;

    private String senha;

    // Indica que o campo endereço é uma instância de uma classe embutida, Endereço,
    // cujos campos serão mapeados diretamente na tabela cliente.
    @Embedded
    private Endereco endereco;

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
