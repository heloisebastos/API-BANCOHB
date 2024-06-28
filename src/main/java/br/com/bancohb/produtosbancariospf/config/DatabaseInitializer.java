package br.com.bancohb.produtosbancariospf.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.bancohb.produtosbancariospf.repository.ClienteRepository;

@Configuration
public class DatabaseInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);

    private final ClienteRepository clienteRepository;

    public DatabaseInitializer(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Alo, banco ta conectado?");
        log.info("Opa, bom ? Ele esta funcionando.");

    }

}
