package br.com.jair.meucarro.http.model;

import com.google.gson.annotations.SerializedName;

public class PecasHttp {

    @SerializedName("id")
    private String id;

    @SerializedName("idApp")
    private String idApp;

    @SerializedName("idManutencao")
    private String idManutencao;

    @SerializedName("idCarro")
    private String idCarro;

    @SerializedName("nome")
    private String nome;

    @SerializedName("marca")
    private String marca;

    @SerializedName("strData")
    private String strData;

    @SerializedName("referencia")
    private String referencia;

    @SerializedName("kmValidade")
    private String kmValidade;

    @SerializedName("kmInstalacao")
    private String kmInstalacao;

    @SerializedName("quantidade")
    private String quantidade;

    @SerializedName("preco")
    private String preco;

    @SerializedName("cupom")
    private String cupom;

    @SerializedName("localCompra")
    private String localCompra;

    @SerializedName("idUsuarios")
    private String idUsuarios;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdApp() {
        return idApp;
    }

    public void setIdApp(String idApp) {
        this.idApp = idApp;
    }

    public String getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(String idManutencao) {
        this.idManutencao = idManutencao;
    }

    public String getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(String idCarro) {
        this.idCarro = idCarro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getStrData() {
        return strData;
    }

    public void setStrData(String strData) {
        this.strData = strData;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getKmValidade() {
        return kmValidade;
    }

    public void setKmValidade(String kmValidade) {
        this.kmValidade = kmValidade;
    }

    public String getKmInstalacao() {
        return kmInstalacao;
    }

    public void setKmInstalacao(String kmInstalacao) {
        this.kmInstalacao = kmInstalacao;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getCupom() {
        return cupom;
    }

    public void setCupom(String cupom) {
        this.cupom = cupom;
    }

    public String getLocalCompra() {
        return localCompra;
    }

    public void setLocalCompra(String localCompra) {
        this.localCompra = localCompra;
    }

    public String getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(String idUsuarios) {
        this.idUsuarios = idUsuarios;
    }
}
