package com.example.hereis.model;

import java.io.Serializable;
public class Lista implements Serializable {

    private String estado;
    private String cidade;
    private String localidade;
    private long id;

    public Lista(String estado, String cidade, String localidade, long id) {
        this.estado = estado;
        this.cidade = cidade;
        this.localidade = localidade;
        this.id = id;
    }

    public Lista(){

    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

