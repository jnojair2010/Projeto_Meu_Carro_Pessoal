package br.com.jair.meucarro.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MeuCarroDataBaseHelpe extends SQLiteOpenHelper {

    private static final String BD_NOME = "meucarro.bd";
    private static final int BD_VERSION = 1;
    public static final String CREATE_TABLE_MANUTENCAO=" create table "+DataBaseConstants.MANUTENCAO.TABLE_MANUTENCAO+"("+
           DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID+" integer primary key autoincrement,"+
            DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID_CARRO+" integer, "+
           DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOME+" text,"+
           DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.DATA+" text,"+
           DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM+" text,"+
           DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.LOCAL+" text,"+
           DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.MECANICO+" text,"+
           DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.TIPO+" text,"+
           DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM_PROX_MANUTENCAO+" text,"+
           DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOTA_CUPOM+" text,"+
           DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.VALOR_PAGO+" real," +
           DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.IDSERVIDOR+ " text);";


    public static final String CREATE_TABLE_CARRO = " create table "+DataBaseConstants.MANUTENCAO.TABLE_MEU_CARRO+" ("+
            DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ID+" integer primary key autoincrement, "+
            DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ID_MANUTENCAO+" integer,"+
            DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.FABRICANTE+" text,"+
            DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.NOME_CARRO+" text,"+
            DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.MODELO+" text,"+
            DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ANO+" text,"+
            DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.COR+" text, "+
            DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.FOTO+" blob, "+
            DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.PLACA+" text,"+
            DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.IDSERVIDOR+ " text);";


    public static final String ITEMS_DE_MANUTENCAO = " create table "+DataBaseConstants.MANUTENCAO.TABLE_ITENS_MANUTENCAO+"( "+
            DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID+" integer primary key autoincrement, "+
            DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID_MANUTENCAO+" integer,"+
            DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID_CARRO+" integer, "+
            DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.DATA+" text,"+
            DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.NOME+" text,"+
            DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.MARCA+" text,"+
            DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.REFERENCIA+" text,"+
            DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.KM_VALIDADE+" integer,"+
            DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.KM_INSTALACAO+" integer,"+
            DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.QUANTIDADE+" text,"+
            DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.VALOR+" real,"+
            DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.CUPOM_NOTA+" text,"+
            DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.LOCAL_COMPRA+" text,"+
            DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.IDSERVIDOR+ " text);";

    public MeuCarroDataBaseHelpe(Context context) {
        super(context, BD_NOME, null, BD_VERSION);
        Log.i(" bd ","////////////////////////////////////////////////// entrou no MeuCarroDataBaseHelpe bd ////////////////////////");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_MANUTENCAO);
        sqLiteDatabase.execSQL(CREATE_TABLE_CARRO);
        sqLiteDatabase.execSQL(ITEMS_DE_MANUTENCAO);
        Log.i(" bd ","////////////////////////////////////////////////// entrou no onCreate db ////////////////////////");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
