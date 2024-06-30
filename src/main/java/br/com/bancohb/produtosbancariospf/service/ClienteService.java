package br.com.bancohb.produtosbancariospf.service;

import org.springframework.stereotype.Service;

import br.com.bancohb.produtosbancariospf.exception.ClienteException;
import br.com.bancohb.produtosbancariospf.model.entity.Cliente;
import br.com.bancohb.produtosbancariospf.repository.ClienteRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    public Cliente patch(UUID id, Map<String, String> requestBody) throws IllegalAccessException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new ClienteException.CadastroNaoEncontradoException("Cadastro não encontrado com id: " + id));

        // lista dos campos da minha model
        List<Field> camposDaModel = List.of(cliente.getClass().getDeclaredFields());

        for (Field campo : camposDaModel) {
            // tirando o privado
            campo.setAccessible(true);
            String nomeCampo = campo.getName();

            if (requestBody.containsKey(nomeCampo)) {
                // log.info(nomeCampo);
                String atualizacaoRequest = requestBody.get(nomeCampo);
                campo.set(cliente, atualizacaoRequest);
            }
        }
        return clienteRepository.save(cliente);
    }
}
