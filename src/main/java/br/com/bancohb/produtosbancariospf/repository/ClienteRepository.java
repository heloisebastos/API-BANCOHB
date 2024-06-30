package br.com.bancohb.produtosbancariospf.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bancohb.produtosbancariospf.model.entity.Cliente;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    boolean existsBycpf(Long cpf);

}
