package br.com.bancohb.produtosbancariospf.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.bancohb.produtosbancariospf.exception.ClienteException;
import br.com.bancohb.produtosbancariospf.model.entity.Cliente;
import br.com.bancohb.produtosbancariospf.repository.ClienteRepository;
import br.com.bancohb.produtosbancariospf.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    private final ClienteService clienteService;

    private static Logger log = LoggerFactory.getLogger(ClienteController.class);

    public ClienteController(ClienteRepository clienteRepository, ClienteService clienteService) {
        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getCliente() {
        try {
            List<Cliente> cliente = clienteRepository.findAll();
            return ResponseEntity.ok(cliente);

        } catch (Exception e) {

            log.error("Erro ao buscar os clientes");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> create(@RequestBody Cliente clienteBody) {
        try {
            Cliente cliente = clienteService.create(clienteBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);

        } catch (ClienteException.CadastroDuplicadoException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        clienteRepository.deleteById(id);
    }

}
