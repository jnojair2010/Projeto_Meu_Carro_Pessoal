package br.com.jair.meucarro.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.com.jair.meucarro.bd.DataBaseConstants;
import br.com.jair.meucarro.bd.MeuCarroDataBaseHelpe;
import br.com.jair.meucarro.model.Pecas;

public class ManutencaoRepository {

    private static  ManutencaoRepository INSTANCE;
    private MeuCarroDataBaseHelpe DataBaseHelper;

    private ManutencaoRepository(Context context){
        this.DataBaseHelper = new MeuCarroDataBaseHelpe(context);
    }
    public static ManutencaoRepository getINSTANCE(Context context){
        if(INSTANCE!=null){
            INSTANCE = new  ManutencaoRepository(context);
        }
        return INSTANCE;
    }

    public void inserirItensDeManutencao(Pecas pecas){
        SQLiteDatabase db = DataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

    }



    }

