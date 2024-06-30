package br.com.bancohb.produtosbancariospf.exception;

public class ClienteException extends RuntimeException {
    public ClienteException(String messsage) {
        super(messsage);
    }

    public static class CadastroNaoEncontradoException extends ClienteException {
        public CadastroNaoEncontradoException(String messsage) {
            super(messsage);
        }
    }

    public static class CadastroDuplicadoException extends ClienteException {
        public CadastroDuplicadoException() {
            super("Cliente já cadastrado");
        }
    }

}
