package br.com.bancohb.produtosbancariospf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.bancohb.produtosbancariospf.controller.ClienteController;
import br.com.bancohb.produtosbancariospf.exception.ClienteException;
import br.com.bancohb.produtosbancariospf.model.entity.Cliente;
import br.com.bancohb.produtosbancariospf.repository.ClienteRepository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private static Logger log = LoggerFactory.getLogger(ClienteController.class);

    public ClienteService(ClienteRepository repository) {
        this.clienteRepository = repository;
    }

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(Cliente clienteBody) {
        boolean cadastradoExiste = clienteRepository.existsBycpf(clienteBody.getCpf());

        if (cadastradoExiste) {
            throw new ClienteException.CadastroDuplicadoException();
        }
        return clienteRepository.save(clienteBody);
    }

    public Cliente atualizarCadastro(UUID id, Map<String, String> requestBody) throws IllegalAccessException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new ClienteException.CadastroNaoEncontradoException(id));

        if (!cliente.getLogado()) {
            throw new ClienteException.ClinteNaoEstaLogadoException();
        }
        // lista dos campos da minha model
        List<Field> camposDaModel = List.of(cliente.getClass().getDeclaredFields());

        for (Field campo : camposDaModel) {

            // tirando o privado
            campo.setAccessible(true);
            String nomeCampo = campo.getName();

            if (requestBody.containsKey(nomeCampo)) {

                log.info("entrou no if do service");

                cliente.setDataAtualizacao(LocalDateTime.now());
                String atualizacaoRequest = requestBody.get(nomeCampo);
                log.info("request recebeou o get.nomecampo");

                campo.set(cliente, atualizacaoRequest);
                log.info(" setou o campo no service");

            }
        }
        return clienteRepository.save(cliente);
    }

    public Cliente logarCliente(UUID id, Cliente clienteBody) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException.CadastroNaoEncontradoException(id));
        boolean cadastradoExiste = clienteRepository.existsByCpfAndSenha(clienteBody.getCpf(), clienteBody.getSenha());

        if (!cadastradoExiste) {
            throw new ClienteException.DadosIncorretoNologinException();
        }

        cliente.setLogado(true);
        return clienteRepository.save(cliente);
    }

    public Cliente deslogarCliente(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException.CadastroNaoEncontradoException(id));

        if (!cliente.getLogado()) {
            throw new ClienteException.ClinteNaoEstaLogadoException();
        }

        cliente.setLogado(false);
        return clienteRepository.save(cliente);
    }

}
