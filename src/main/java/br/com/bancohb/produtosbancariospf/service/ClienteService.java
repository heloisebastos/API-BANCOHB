package br.com.bancohb.produtosbancariospf.service;

import org.springframework.stereotype.Service;

import br.com.bancohb.produtosbancariospf.exception.ClienteException;
import br.com.bancohb.produtosbancariospf.model.entity.Cliente;
import br.com.bancohb.produtosbancariospf.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository repository) {
        this.clienteRepository = repository;
    }

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(Cliente clienteBody) {
        boolean cadastradoExiste = clienteRepository.existsBycpf(clienteBody.getCpf());

        if (cadastradoExiste) {
            throw new ClienteException.CadastroDuplicadoException();
        }
        return clienteRepository.save(clienteBody);
    }

}
