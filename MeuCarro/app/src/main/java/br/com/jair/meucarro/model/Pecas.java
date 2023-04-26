package br.com.jair.meucarro.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Pecas implements Serializable {
    private int id;
    private int id_manutencao;
    private  int id_carro;
    private String data;
    private String nome;
    private String marca;
    private String referencia;
    private String kmVidaUtil;
    private int kmDeInstalacao;
    private String Local;
    private String preco;
    private String cupom;
    private String quantidade;
    private int kmFaltente;

    private int kmValidade;
    private int novaValidade;
    private Double percenteFaltanete;
    private int kmUsado;
    private Double percenteUsado;

    public Pecas(){

    }

    public void CalcularKmFaltante(int km, Context context){

        if(km>0){
            this.kmUsado = km-this.kmDeInstalacao;
            this.kmFaltente = (this.kmValidade +this.kmDeInstalacao)-km;
            this.novaValidade = (this.kmValidade +this.kmDeInstalacao);

            Double fracao  = ( Double.parseDouble(String.valueOf(this.kmUsado ))/Double.parseDouble(String.valueOf(this.kmValidade)))*100;

            String format = String.format("%.2f",fracao);
            try{
                this.percenteUsado = Double.parseDouble(format.replace(",","."));;
            }catch (Exception e){
                Toast.makeText(context, "Verifique o Numero digitado", Toast.LENGTH_LONG).show();
            }

        }


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_manutencao() {
        return id_manutencao;
    }

    public void setId_manutencao(int id_manutencao) {
        this.id_manutencao = id_manutencao;
    }

    public int getId_carro() {
        return id_carro;
    }

    public void setId_carro(int id_carro) {
        this.id_carro = id_carro;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getKmVidaUtil() {
        return kmVidaUtil;
    }

    public void setKmVidaUtil(String kmVidaUtil) {
        this.kmVidaUtil = kmVidaUtil;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
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

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public int getKmFaltente() {
        return kmFaltente;
    }

    public void setKmFaltente(int kmFaltente) {
        this.kmFaltente = kmFaltente;
    }

    public Double getPercenteFaltanete() {
        return percenteFaltanete;
    }

    public void setPercenteFaltanete(Double percenteFaltanete) {
        this.percenteFaltanete = percenteFaltanete;
    }

    public int getKmDeInstalacao() {
        return kmDeInstalacao;
    }

    public void setKmDeInstalacao(int kmDeInstalacao) {
        this.kmDeInstalacao = kmDeInstalacao;
    }

    public int getKmValidade() {
        return kmValidade;
    }

    public void setKmValidade(int kmValidade) {
        this.kmValidade = kmValidade;
    }

    public int getKmUsado() {
        return kmUsado;
    }

    public void setKmUsado(int kmUsado) {
        this.kmUsado = kmUsado;
    }

    public Double getPercenteUsado() {
        return percenteUsado;
    }

    public void setPercenteUsado(Double percenteUsado) {
        this.percenteUsado = percenteUsado;
    }

    public int getKm_atual() {
        return kmDeInstalacao;
    }

    public int getNovaValidade() {
        return novaValidade;
    }

    public void setNovaValidade(int novaValidade) {
        this.novaValidade = novaValidade;
    }

    public void setKm_atual(int kmDeInstalacao) {
        this.kmDeInstalacao = kmDeInstalacao;
    }

    @Override
    public String toString() {
        return "Pecas{" +
                "id=" + id +
                ", id_manutencao=" + id_manutencao +
                ", id_carro=" + id_carro +
                ", data='" + data + '\'' +
                ", nome='" + nome + '\'' +
                ", marca='" + marca + '\'' +
                ", referencia='" + referencia + '\'' +
                ", kmVidaUtil='" + kmVidaUtil + '\'' +
                ", kmDeInstalacao=" + kmDeInstalacao +
                ", Local='" + Local + '\'' +
                ", preco='" + preco + '\'' +
                ", cupom='" + cupom + '\'' +
                ", quantidade='" + quantidade + '\'' +
                ", kmFaltente=" + kmFaltente +
                ", kmValidade=" + kmValidade +
                ", novaValidade=" + novaValidade +
                ", percenteFaltanete=" + percenteFaltanete +
                ", kmUsado=" + kmUsado +
                ", percenteUsado=" + percenteUsado +
                '}';
    }
}
