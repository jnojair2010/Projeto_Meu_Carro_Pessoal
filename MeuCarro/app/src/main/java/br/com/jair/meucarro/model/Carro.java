package br.com.jair.meucarro.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.Serializable;
import java.util.Arrays;

public class Carro implements Serializable {
    private int id;
    private int id_manutencao;
    private String fabricante;
    private String modelo;
    private String nomeCarro;
    private byte[] imagem;
    private String cor;
    private String ano;
    private String placa;

    private String idAppServidor;

        // conttrutor da classe carro
    public Carro(){

    }


    //metodos gets e sets da classe carro


    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
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

    public String getNomeCarro() {
        return nomeCarro;
    }

    public void setNomeCarro(String marca) {
        this.nomeCarro= marca;
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

    public String getIdAppServidor() {
        return idAppServidor;
    }

    public void setIdAppServidor(String idServidor) {
        this.idAppServidor = idServidor;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", id_manutencao=" + id_manutencao +
                ", fabricante='" + fabricante + '\'' +
                ", modelo='" + modelo + '\'' +
                ", nomeCarro='" + nomeCarro + '\'' +
                ", imagem=" + Arrays.toString(imagem) +
                ", cor='" + cor + '\'' +
                ", ano='" + ano + '\'' +
                ", placa='" + placa + '\'' +
                ", idServidor='" + idAppServidor + '\'' +
                '}';
    }
}
