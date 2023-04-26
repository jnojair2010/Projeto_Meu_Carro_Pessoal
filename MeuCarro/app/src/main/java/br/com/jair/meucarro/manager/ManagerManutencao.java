package br.com.jair.meucarro.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.com.jair.meucarro.bd.MeuCarroDataBaseHelpe;
import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.model.Pecas;

public class ManagerManutencao {

    private static Pecas mPecas;
    private static Carro mCarro;
    private static ArrayList<Carro> listaCarro = new ArrayList<>();
    private Context context;
    private ManutencaoRepository manutencaoRepository;


    public ManagerManutencao(Context context){
        this.context = context;
        this.manutencaoRepository = ManutencaoRepository.getINSTANCE(context.getApplicationContext());
    }


    public static ArrayList<Carro> getListaCarro() {
        return listaCarro;
    }

    public static void setListaCarro(ArrayList<Carro> listaCarro) {

        ManagerManutencao.listaCarro = listaCarro;
    }

    public static Carro getmCarro() {
        return mCarro;
    }

    public static void setmCarro(Carro mCarro) {
        ManagerManutencao.mCarro = mCarro;
    }

    public static Pecas getmPecas() {
        return mPecas;
    }

    public static void setmPecas(Pecas mPecas) {
        ManagerManutencao.mPecas = mPecas;
    }

    public void salvarPeca(Pecas peca){
      //  this.manutencaoRepository.insertPeca(peca);


    }

}
