package br.com.jair.meucarro.http.model;

import com.google.gson.annotations.SerializedName;

public class ManutencaoHttp {

    @SerializedName("id")
    private String id;

    @SerializedName("strData")
    private String strData;

    @SerializedName("tipo")
    private String tipo;

    @SerializedName("idCarro")
    private String idCarro;

    @SerializedName("kmAtual")
    private String kmAtual;

    @SerializedName("nome")
    private String nome;

    @SerializedName("localManutencao")
    private String localManutencao;

    @SerializedName("nomeMecanico")
    private String nomeMecanico;

    @SerializedName("preco")
    private String preco;

    @SerializedName("kmProximaTroca")
    private String kmProximaTroca;

    @SerializedName("cupom")
    private String cupom;

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

    public String getStrData() {
        return strData;
    }

    public void setStrData(String strData) {
        this.strData = strData;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(String idCarro) {
        this.idCarro = idCarro;
    }

    public String getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(String kmAtual) {
        this.kmAtual = kmAtual;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalManutencao() {
        return localManutencao;
    }

    public void setLocalManutencao(String localManutencao) {
        this.localManutencao = localManutencao;
    }

    public String getNomeMecanico() {
        return nomeMecanico;
    }

    public void setNomeMecanico(String nomeMecanico) {
        this.nomeMecanico = nomeMecanico;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getKmProximatroca() {
        return kmProximaTroca;
    }

    public void setKmProximatroca(String kmProximatroca) {
        this.kmProximaTroca= kmProximatroca;
    }

    public String getCupom() {
        return cupom;
    }

    public void setCupom(String cupom) {
        this.cupom = cupom;
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
