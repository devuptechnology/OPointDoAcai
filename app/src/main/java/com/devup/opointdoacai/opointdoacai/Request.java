package com.devup.opointdoacai.opointdoacai;

import java.util.List;

public class Request {

    private String telefone;
    private String nome;
    private String endereco;
    private String total;
    private String status;
    private List<Order> pedidos;

    public Request() {
    }

    public Request(String telefone, String nome, String endereco, String total, List<Order> pedidos) {
        this.telefone = telefone;
        this.nome = nome;
        this.endereco = endereco;
        this.total = total;
        this.pedidos = pedidos;
        this.status  = "0"; //Default é 0: 0 = Pedido, 1 = A Caminho, 2 = Entregue
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Order> pedidos) {
        this.pedidos = pedidos;
    }
}
