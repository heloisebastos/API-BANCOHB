package br.com.bancohb.produtosbancariospf.model.value;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {

    private String rua;
    private int quadra;
    private int numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private int cep;

    public Endereco(String rua, int quadra, int numero, String bairro, String complemento, String cidade, int cep) {
        this.rua = rua;
        this.quadra = quadra;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.cep = cep;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getQuadra() {
        return quadra;
    }

    public void setQuadra(int quadra) {
        this.quadra = quadra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Endereco [rua=" + rua + ", quadra=" + quadra + ", numero=" + numero + ", bairro=" + bairro
                + ", complemento=" + complemento + ", cidade=" + cidade + ", cep=" + cep + "]";
    }

}
