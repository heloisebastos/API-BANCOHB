package br.com.bancohb.produtosbancariospf.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bancohb.produtosbancariospf.model.value.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @UuidGenerator
    private UUID id;

    @NotNull(message = "CPF não pode ser vazio")
    @Column(updatable = false)
    private Long cpf;

    @Column(updatable = false)
    private String nome;

    private String telefone;

    private String email;

    private double rendaSalarial;

    private String senha;

    private boolean logado = false;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(insertable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAtualizacao;

    // Indica que o campo endereço é uma instância de uma classe embutida, Endereço,
    // cujos campos serão mapeados diretamente na tabela cliente.
    @Embedded
    private Endereco endereco;

    protected Cliente() {
    }

    public Cliente(Long cpf, String nome, String telefone, String email, double rendaSalarial, String senha,
            Endereco endereco, boolean status) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.rendaSalarial = rendaSalarial;
        this.senha = senha;
        this.endereco = endereco;
        this.logado = status;
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

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email
                + ", rendaSalarial=" + rendaSalarial + ", senha=" + senha + ", endereco=" + endereco + "]";
    }

}
