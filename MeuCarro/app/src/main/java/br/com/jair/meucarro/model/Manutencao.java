package br.com.jair.meucarro.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Manutencao implements Serializable {
    private int id;
    private String data;
    private int idCarro;
    private int kmFeitoManutencao;
    private int kmValidade;
    private String nome;
    private String local;
    private String nomeMecanico;
    private String valor;
    private String dataCompraPeca;
    private String tipoMAnutencao;
    private String cupom_nota;



    public String getCupom_nota() {
        return cupom_nota;
    }

    public void setCupom_nota(String cupom_nota) {
        this.cupom_nota = cupom_nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getKmFeitoManutencao() {
        return kmFeitoManutencao;
    }

    public void setKmFeitoManutencao(int kmFeitoManutencao) {
        this.kmFeitoManutencao = kmFeitoManutencao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNomeMecanico() {
        return nomeMecanico;
    }

    public void setNomeMecanico(String nomeMecanico) {
        this.nomeMecanico = nomeMecanico;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDataCompraPeca() {
        return dataCompraPeca;
    }

    public void setDataCompraPreca(String dataCompraPreca) {
        this.dataCompraPeca = dataCompraPeca;
    }

    public int getKmValidade() {
        return kmValidade;
    }

    public void setKmValidade(int kmValidade) {
        this.kmValidade = kmValidade;
    }

    public String getTipoMAnutencao() {
        return tipoMAnutencao;
    }

    public void setTipoMAnutencao(String tipoMAnutencao) {
        this.tipoMAnutencao = tipoMAnutencao;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    @Override
    public String toString() {
        return "Manutencao{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", idCarro=" + idCarro +
                ", kmFeitoManutencao=" + kmFeitoManutencao +
                ", kmValidade=" + kmValidade +
                ", nome='" + nome + '\'' +
                ", local='" + local + '\'' +
                ", nomeMecanico='" + nomeMecanico + '\'' +
                ", valor=" + valor +
                ", dataCompraPreca='" + dataCompraPeca + '\'' +
                ", tipoMAnutencao='" + tipoMAnutencao + '\'' +
                ", cupom_nota='" + cupom_nota + '\'' +
                '}';
    }
}
