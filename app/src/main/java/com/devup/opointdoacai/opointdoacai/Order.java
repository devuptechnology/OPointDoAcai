package com.devup.opointdoacai.opointdoacai;

public class Order {

    private String IdProdutos;
    private String Complementos;
    private String Quantidade;
    private String Preco;

    public Order() {
    }

    public Order(String idProdutos, String complementos, String quantidade, String preco) {
        IdProdutos = idProdutos;
        Complementos = complementos;
        Quantidade = quantidade;
        Preco = preco;
    }

    public String getIdProdutos() {
        return IdProdutos;
    }

    public void setIdProdutos(String idProdutos) {
        IdProdutos = idProdutos;
    }

    public String getComplementos() {
        return Complementos;
    }

    public void setComplementos(String complementos) {
        Complementos = complementos;
    }

    public String getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(String quantidade) {
        Quantidade = quantidade;
    }

    public String getPreco() {
        return Preco;
    }

    public void setPreco(String preco) {
        Preco = preco;
    }
}
