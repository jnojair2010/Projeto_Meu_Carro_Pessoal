package br.com.jair.meucarro.http.model;

import com.google.gson.annotations.SerializedName;

public class SalvarUsuario {

    @SerializedName("id")
    private String id;
    @SerializedName("nome")
    private String nome;

    @SerializedName("email")
    private String email;
    @SerializedName("resultado")
    private String resultado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
