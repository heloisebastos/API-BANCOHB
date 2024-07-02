package br.com.bancohb.produtosbancariospf.controller;

import java.util.List;
import java.util.Map;
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
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/cadastrar-cliente")
    public ResponseEntity<Object> create(@RequestBody Cliente clienteBody) {

        try {
            Cliente cliente = clienteService.cadastrarCliente(clienteBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);

        } catch (ClienteException.CadastroDuplicadoException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}/deletar-cadastro")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        clienteRepository.deleteById(id);
    }

    @PatchMapping("/{id}/atualizar-cadastro")
    public ResponseEntity<Object> atualizarCadastro(@PathVariable UUID id,
            @RequestBody Map<String, String> requestBody)
            throws IllegalAccessException {

        try {
            Cliente clienteAtualizado = clienteService.atualizarCadastro(id,
                    requestBody);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (ClienteException.CadastroNaoEncontradoException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ClienteException.ClinteNaoEstaLogadoException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PatchMapping("/{id}/logar")
    public ResponseEntity<Object> autenticar(@PathVariable UUID id, @RequestBody Cliente clienteBody) {
        try {
            Cliente logarCliente = clienteService.logarCliente(id, clienteBody);
            return ResponseEntity.ok(logarCliente);
        } catch (ClienteException.ClinteNaoEstaLogadoException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PatchMapping("/{id}/deslogar")
    public ResponseEntity<Object> deslogar(@PathVariable UUID id) {
        try {
            Cliente logarCliente = clienteService.deslogarCliente(id);
            return ResponseEntity.ok(logarCliente);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

}
