package br.com.bancohb.produtosbancariospf.exception;

import java.util.UUID;

public class ClienteException extends RuntimeException {
    public ClienteException(String messsage) {
        super(messsage);
    }

    public static class CadastroNaoEncontradoException extends ClienteException {
        public CadastroNaoEncontradoException(UUID id) {
            super("Cadastro não encontrado com id: " + id);
        }
    }

    public static class CadastroDuplicadoException extends ClienteException {
        public CadastroDuplicadoException() {
            super("Cliente já cadastrado");
        }
    }

    public static class DadosIncorretoNologinException extends ClienteException {
        public DadosIncorretoNologinException() {
            super("Os dados de login estão incorreto");
        }
    }

}
