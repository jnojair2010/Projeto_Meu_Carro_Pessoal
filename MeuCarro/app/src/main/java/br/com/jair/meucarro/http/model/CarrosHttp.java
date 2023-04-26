package br.com.jair.meucarro.http.model;

import com.google.gson.annotations.SerializedName;

public class CarrosHttp {

    @SerializedName("id")
    private String id;

    @SerializedName("fabricante")
    private String fabricante;

    @SerializedName("modelo")
    private String modelo;

    @SerializedName("nome")
    private String nome;

    @SerializedName("cor")
    private String cor;

    @SerializedName("ano")
    private String ano;

    @SerializedName("placa")
    private String placa;

    @SerializedName("idApp")
    private String idApp;

    @SerializedName("idUsuarios")
    private String idUsuarios;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getIdApp() {
        return idApp;
    }

    public void setIdApp(String idApp) {
        this.idApp = idApp;
    }

    public String getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(String idUsuarios) {
        this.idUsuarios = idUsuarios;
    }
}
