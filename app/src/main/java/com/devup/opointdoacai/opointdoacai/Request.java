package com.devup.opointdoacai.opointdoacai;

import java.util.List;

public class Request {

    private String telefone;
    private String nome;
    private String endereco;
    private String total;
    private String paymentMethod;
    private String troco;
    private String status;
    private List<Order> pedidos;

    public Request() {
    }

    public Request(String telefone, String nome, String endereco, String total, String paymentMethod, String troco, String status, List<Order> pedidos) {
        this.telefone = telefone;
        this.nome = nome;
        this.endereco = endereco;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.troco = troco;
        this.status = status;
        this.pedidos = pedidos;
    }

    public String getTroco() {
        return troco;
    }

    public void setTroco(String troco) {
        this.troco = troco;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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
