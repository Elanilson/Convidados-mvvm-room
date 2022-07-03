package com.apkdoandroid.treino4.model;

public class Resposta {
    private Boolean status = true;
    private String mensagem = "";

    public Resposta() {
    }

    public Resposta(String mensagem) {
        this.mensagem = mensagem;
    }

    public Resposta(Boolean status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
