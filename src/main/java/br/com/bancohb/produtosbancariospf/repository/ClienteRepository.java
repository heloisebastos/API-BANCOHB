package br.com.bancohb.produtosbancariospf.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bancohb.produtosbancariospf.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

}
