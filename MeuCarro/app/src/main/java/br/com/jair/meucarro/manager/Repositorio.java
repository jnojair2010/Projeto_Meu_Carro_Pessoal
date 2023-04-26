package br.com.jair.meucarro.manager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import br.com.jair.meucarro.bd.DataBaseConstants;
import br.com.jair.meucarro.bd.MeuCarroDataBaseHelpe;
import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.model.Manutencao;
import br.com.jair.meucarro.model.Pecas;

public class Repositorio {

    private static Repositorio INSTANCE;
    private MeuCarroDataBaseHelpe DataBaseHelper;

    private Repositorio(Context context) {
        this.DataBaseHelper = new MeuCarroDataBaseHelpe(context);
    }

    public static Repositorio getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Repositorio(context);

        }
        return INSTANCE;
    }

    public boolean insert(Carro mCarro) {            // metodo de inserção de carro na tabela do bd
        SQLiteDatabase db = DataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.FABRICANTE, mCarro.getFabricante());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.MODELO, mCarro.getModelo());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.NOME_CARRO, mCarro.getNomeCarro());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.COR, mCarro.getCor());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ANO, mCarro.getAno());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.PLACA, mCarro.getPlaca());

        try {
            db.insert(DataBaseConstants.MANUTENCAO.TABLE_MEU_CARRO, null, contentValues);
            db.close();
            return true;
        } catch (Exception e) {
            Log.i("erro db", "//////////////************** erro de inserção ********///////");
            return false;
        }


    }

    public Carro consultarCarro(Carro mCarro) {
        Carro car = new Carro();

        SQLiteDatabase db = DataBaseHelper.getReadableDatabase();

        String tabela = DataBaseConstants.MANUTENCAO.TABLE_MEU_CARRO;
        String[] colunas = {
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ID,
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.FABRICANTE,
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.MODELO,
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.NOME_CARRO,
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.COR,
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ANO,
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.PLACA};

        String selection = DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.PLACA + " = ?";
        String[] selectionArgs = {mCarro.getPlaca()};

        Cursor cursor = db.query(tabela, colunas, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ID));
            @SuppressLint("Range") String cor = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.COR));
            @SuppressLint("Range") String marca = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.NOME_CARRO));
            @SuppressLint("Range") String fabricante = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.FABRICANTE));
            @SuppressLint("Range") String modelo = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.MODELO));
            @SuppressLint("Range") String ano = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ANO));
            @SuppressLint("Range") String placa = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.PLACA));

            car.setId(Integer.parseInt(id));
            car.setFabricante(fabricante);
            car.setCor(cor);
            car.setNomeCarro(marca);
            car.setModelo(modelo);
            car.setAno(ano);
            car.setPlaca(placa);
            db.close();
            return car;
        } else {
            return mCarro;
        }
    }

    public Carro consultarMeuCarro() {
        Carro car = new Carro();

        SQLiteDatabase db = DataBaseHelper.getReadableDatabase();

        String tabela = DataBaseConstants.MANUTENCAO.TABLE_MEU_CARRO;
        String[] colunas = {
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ID,
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.FABRICANTE,
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.MODELO,
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.NOME_CARRO,
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.COR,
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ANO,
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.FOTO,
                DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.PLACA};


        Cursor cursor = db.query(tabela, colunas, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();


            car.setId(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ID));
            car.setCor(String.valueOf(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.COR)));
            car.setNomeCarro(String.valueOf(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.NOME_CARRO)));
            car.setFabricante(String.valueOf(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.FABRICANTE)));
            car.setModelo(String.valueOf(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.MODELO)));
            car.setAno(String.valueOf(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ANO)));

           byte[] imagem = cursor.getBlob(6);
           car.setImagem(imagem);


            db.close();
            return car;
        }
        return car;
    }

    public ArrayList<Carro> consultarMeusCarros() {

        ArrayList<Carro> mListaCarro = new ArrayList<>();

        try {
            SQLiteDatabase db = DataBaseHelper.getReadableDatabase();

            String tabela = DataBaseConstants.MANUTENCAO.TABLE_MEU_CARRO;
            String[] colunas = {
                    DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ID,
                    DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.FABRICANTE,
                    DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.MODELO,
                    DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.NOME_CARRO,
                    DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.COR,
                    DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ANO,
                    DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.FOTO,
                    DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.PLACA,
                    DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.IDSERVIDOR};


            Cursor cursor = db.query(tabela, colunas, null, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Carro car = new Carro();

                    @SuppressLint("Range") String id =  cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ID));
                    car.setId(Integer.parseInt(id));
                    @SuppressLint("Range") String nome =  cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.NOME_CARRO));
                    car.setNomeCarro(nome);
                    @SuppressLint("Range") String cor =  cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.COR));
                    car.setCor(cor);
                    @SuppressLint("Range") String fabricante=  cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.FABRICANTE));
                    car.setFabricante(fabricante);
                    @SuppressLint("Range") String modelo =  cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.MODELO));
                    car.setModelo(modelo);
                    @SuppressLint("Range") String ano =  cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ANO));
                    car.setAno(ano);
                    @SuppressLint("Range") String placa = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.PLACA));
                    car.setPlaca(placa);
                    @SuppressLint("Range") String idServidor = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.IDSERVIDOR));
                    car.setIdAppServidor(idServidor);

                    byte[] imagem = cursor.getBlob(6);
                    car.setImagem(imagem);

                    mListaCarro.add(car);
                }
            }
            Log.i("imagem", "/////////////////////// o tamanho  lista carro: "+mListaCarro.size());
            db.close();
            return mListaCarro;

        } catch (Exception e) {
            return mListaCarro;
        }

    }

    public boolean deletarCarro(int id) {
        try {
            SQLiteDatabase db = DataBaseHelper.getWritableDatabase();
            String where = DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ID + " = ?";
            String[] args = {String.valueOf(id)};
            db.delete(DataBaseConstants.MANUTENCAO.TABLE_MEU_CARRO, where, args);
            db.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }
    public boolean salvarFotosRespositorio(Carro carro){

        try{
            SQLiteDatabase db = DataBaseHelper.getWritableDatabase();
                String tabela = DataBaseConstants.MANUTENCAO.TABLE_MEU_CARRO;
                ContentValues contentValues = new ContentValues();
                contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.FOTO,carro.getImagem());

                String where = DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ID+" = ? ";

                String[] args ={String.valueOf(carro.getId())};

            db.update(tabela,contentValues,where,args);

            db.close();
            return true;

        }catch (Exception e){

            Log.i("update", "//////////////////// erro no update de pecas///////////////"+e.getMessage());
            return false;
        }
    }


    // fim do metodo deletarCarro




    // inicia metodos de pecas

    public boolean insertPeca(Pecas peca) {

        SQLiteDatabase db = DataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID_CARRO, peca.getId_carro());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.NOME, peca.getNome());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.DATA, peca.getData());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.MARCA, peca.getMarca());
        //contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID_MANUTENCAO,peca.getId_manutencao());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.REFERENCIA, peca.getReferencia());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.KM_VALIDADE, peca.getKmVidaUtil());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.LOCAL_COMPRA, peca.getLocal());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.VALOR, peca.getPreco());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.CUPOM_NOTA, peca.getCupom());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.QUANTIDADE, peca.getQuantidade());

        try {
            db.insert(DataBaseConstants.MANUTENCAO.TABLE_ITENS_MANUTENCAO, null, contentValues);
            db.close();
            return true;
        } catch (Exception e) {
            Log.i("erro db", "//////////////************** erro de inserção ********///////");
            return false;
        }
    } // fim do metodo inserPeca

    public ArrayList<Pecas> getAllPecas(){

        ArrayList<Pecas> mListaPecas = new ArrayList<>();

        try {
            SQLiteDatabase db = DataBaseHelper.getReadableDatabase();

            String tabela = DataBaseConstants.MANUTENCAO.TABLE_ITENS_MANUTENCAO;

            String[] colunas = {
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID_MANUTENCAO,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID_CARRO,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.NOME,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.DATA,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.MARCA,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.REFERENCIA,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.KM_VALIDADE,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.KM_INSTALACAO,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.LOCAL_COMPRA,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.VALOR,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.CUPOM_NOTA,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.QUANTIDADE };


            Cursor cursor = db.query(tabela, colunas, null, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Pecas peca = new Pecas();

                    @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID));
                    @SuppressLint("Range") String id_carro = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID_CARRO));
                    @SuppressLint("Range") String id_manutencao = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID_MANUTENCAO));
                    @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.NOME));
                    @SuppressLint("Range") String data = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.DATA));
                    @SuppressLint("Range") String marca= cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.MARCA));
                    @SuppressLint("Range") String referencia = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.REFERENCIA));
                    @SuppressLint("Range") String km_validade = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.KM_VALIDADE));
                    @SuppressLint("Range") String kmInstalacao = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.KM_INSTALACAO));
                    @SuppressLint("Range") String localCompra = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.LOCAL_COMPRA));
                    @SuppressLint("Range") String valor = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.VALOR));
                    @SuppressLint("Range") String cupom = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.CUPOM_NOTA));
                    @SuppressLint("Range") String quantidade = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.QUANTIDADE));

                    peca.setId(Integer.parseInt(id));
                    peca.setId_carro(Integer.parseInt(id_carro));
                     if(id_manutencao!=""){
                            peca.setId_manutencao(Integer.parseInt(id_manutencao));
                        }else{
                           peca.setId_manutencao(0);
                       }
                        if(kmInstalacao.equals("")){
                            peca.setKm_atual(0);
                        }else{
                            peca.setKm_atual(Integer.parseInt(kmInstalacao));
                        }
                    peca.setNome(nome);
                    peca.setData(data);
                    peca.setMarca(marca);
                    peca.setReferencia(referencia);
                    peca.setKmVidaUtil(km_validade);
                    peca.setKmValidade(Integer.parseInt(km_validade.replaceAll(",", ""))); // elimina a virgula do valor vindo do bd
                    peca.setLocal(localCompra);
                    peca.setPreco(valor);
                    peca.setCupom(cupom);
                    peca.setQuantidade(quantidade);

                    mListaPecas.add(peca);
                }
            }
            db.close();

            return mListaPecas;

        } catch (Exception e) {
            Log.i(" nome_peca"," /////////////////////// entrou no exception getAllPecas //////////// "+e.getMessage());
            return mListaPecas;
        }
    }
    public ArrayList<Pecas> getAllPecasManutencao(){

        Log.i(" nome_peca"," /////////////////////// entrou no getAllPecasData(String dt) //////////// ");
        ArrayList<Pecas> mListaPecas = new ArrayList<>();

        try {
            SQLiteDatabase db = DataBaseHelper.getReadableDatabase();

            String tabela = DataBaseConstants.MANUTENCAO.TABLE_ITENS_MANUTENCAO;

            String[] colunas = {
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID_MANUTENCAO,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID_CARRO,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.NOME,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.DATA,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.MARCA,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.REFERENCIA,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.KM_VALIDADE,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.LOCAL_COMPRA,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.VALOR,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.CUPOM_NOTA,
                    DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.QUANTIDADE };

          //  String selection = DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.DATA + " = ?";
           // String[] selectionArgs = {dt};

            Cursor cursor = db.query(tabela, colunas, null, null, null, null, null);

          //  Cursor cursor = db.query(tabela, colunas, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Pecas peca = new Pecas();



                    @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID));
                    @SuppressLint("Range") String id_carro = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID_CARRO));
                    @SuppressLint("Range") String id_manutencao = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID_MANUTENCAO));
                    @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.NOME));
                    @SuppressLint("Range") String data = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.DATA));
                    @SuppressLint("Range") String marca= cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.MARCA));
                    @SuppressLint("Range") String referencia = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.REFERENCIA));
                    @SuppressLint("Range") String km_validade = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.KM_VALIDADE));
                    @SuppressLint("Range") String localCompra = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.LOCAL_COMPRA));
                    @SuppressLint("Range") String valor = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.VALOR));
                    @SuppressLint("Range") String cupom = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.CUPOM_NOTA));
                    @SuppressLint("Range") String quantidade = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.QUANTIDADE));

                    peca.setId(Integer.parseInt(id));
                    peca.setId_carro(Integer.parseInt(id_carro));
                    if(id_manutencao!=null){
                        peca.setId_manutencao(Integer.parseInt(id_manutencao));
                    }
                    peca.setNome(nome);
                    peca.setData(data);
                    peca.setMarca(marca);
                    peca.setReferencia(referencia);
                    peca.setKmVidaUtil(km_validade);
                    peca.setLocal(localCompra);
                    peca.setPreco(valor);
                    peca.setCupom(cupom);
                    peca.setQuantidade(quantidade);

                    mListaPecas.add(peca);
                }
            }
            db.close();

            return mListaPecas;

        } catch (Exception e) {
            Log.i(" nome_peca"," /////////////////////// entrou no exception getAllPecas //////////// "+e.getMessage());
            return mListaPecas;
        }
    }

    public boolean updatePecas(Manutencao mManutencao, Pecas peca){
        try{
            SQLiteDatabase db = DataBaseHelper.getWritableDatabase();

            String tabela = DataBaseConstants.MANUTENCAO.TABLE_ITENS_MANUTENCAO;
            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID_MANUTENCAO,mManutencao.getId());
            contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.KM_INSTALACAO,mManutencao.getKmFeitoManutencao());
            String where = DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID+" = ? ";
            String[] args ={String.valueOf(peca.getId())};

            db.update(tabela,contentValues,where,args);

            db.close();
            return true;

        }catch (Exception e){

                Log.i("update", "//////////////////// erro no update de pecas///////////////"+e.getMessage());
            return false;
        }

    }
    public boolean deletarPeca(int id) {
        try {
            SQLiteDatabase db = DataBaseHelper.getWritableDatabase();
            String where = DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID + " = ?";
            String[] args = {String.valueOf(id)};
            db.delete(DataBaseConstants.MANUTENCAO.TABLE_ITENS_MANUTENCAO, where, args);
            db.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean aTualizarPeca(Pecas peca){
        try{
            SQLiteDatabase db = DataBaseHelper.getWritableDatabase();

            String tabela = DataBaseConstants.MANUTENCAO.TABLE_ITENS_MANUTENCAO;
            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.NOME, peca.getNome());
            contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.DATA, peca.getData());
            contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.MARCA, peca.getMarca());
            contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.REFERENCIA, peca.getReferencia());
            contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.KM_VALIDADE, peca.getKmVidaUtil());
            contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.LOCAL_COMPRA, peca.getLocal());
            contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.VALOR, peca.getPreco());
            contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.CUPOM_NOTA, peca.getCupom());
            contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.QUANTIDADE, peca.getQuantidade());
            contentValues.put(DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.KM_INSTALACAO, peca.getKmDeInstalacao());
            String where = DataBaseConstants.MANUTENCAO.COLUNAS_ITENS_MANUTENCAO.ID+" = ? ";
            String[] args ={String.valueOf(peca.getId())};

            db.update(tabela,contentValues,where,args);

            db.close();
            return true;

        }catch (Exception e){

            Log.i("update", "//////////////////// erro no update de pecas///////////////"+e.getMessage());
            return false;
        }

    }



    /////////////////fim da area de peças ///////////////////////////////////////////////////

    ///////////////// início da area de manutencao//////////////////////////////////////////////////////

    public boolean insertManutencao(Manutencao manutencao){
        SQLiteDatabase db = DataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID_CARRO,manutencao.getIdCarro());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.DATA,manutencao.getData());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOME,manutencao.getNome());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM,manutencao.getKmFeitoManutencao());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.LOCAL, manutencao.getLocal());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.MECANICO,manutencao.getNomeMecanico());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.TIPO,manutencao.getTipoMAnutencao());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM, manutencao.getKmFeitoManutencao());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM_PROX_MANUTENCAO,manutencao.getKmValidade());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOTA_CUPOM, manutencao.getCupom_nota());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.VALOR_PAGO, manutencao.getValor());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOTA_CUPOM,manutencao.getCupom_nota());

        try {
            db.insert(DataBaseConstants.MANUTENCAO.TABLE_MANUTENCAO, null, contentValues);
            db.close();
            return true;
        } catch (Exception e) {
            Log.i("erro db", "//////////////************** erro de inserção ********///////");
            return false;
        }

    }

    public Manutencao getmanutencao(Manutencao manutencao){
        Manutencao mManutencao = new Manutencao();

        SQLiteDatabase db = DataBaseHelper.getWritableDatabase();

        String tabela = DataBaseConstants.MANUTENCAO.TABLE_MANUTENCAO;
        String[] colunas = {DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.DATA,
                            DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID,
                            DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID_CARRO,
                            DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM_PROX_MANUTENCAO,
                            DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM,
                            DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOME,
                            DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.TIPO,
                            DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.MECANICO,
                            DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.LOCAL,
                            DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.VALOR_PAGO,
                            DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOTA_CUPOM};

        String selection = DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.DATA + " = ? AND "+DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOME+" = ?";
        String[] selectionArgs = {manutencao.getData(), manutencao.getNome()};

        Cursor cursor = db.query(tabela, colunas, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID));
            @SuppressLint("Range") String kmProximaManutencao = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM_PROX_MANUTENCAO));
            @SuppressLint("Range") String idCarro = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID_CARRO));
            @SuppressLint("Range") String kmAtual = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM));
            @SuppressLint("Range") String nome= cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOME));
            @SuppressLint("Range") String tipo = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.TIPO));
            @SuppressLint("Range") String mecanico = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.MECANICO));
            @SuppressLint("Range") String local = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.LOCAL));
            @SuppressLint("Range") String valorPago = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.VALOR_PAGO));
            @SuppressLint("Range") String notaCupom = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOTA_CUPOM));
            @SuppressLint("Range") String data = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.DATA));

            mManutencao.setNome(nome);
            mManutencao.setNome(data);
            mManutencao.setIdCarro(Integer.parseInt(idCarro));
            mManutencao.setKmFeitoManutencao(Integer.parseInt(kmAtual));
            mManutencao.setKmValidade(Integer.parseInt(kmProximaManutencao));
            mManutencao.setId(Integer.parseInt(id));
            mManutencao.setTipoMAnutencao(tipo);
            mManutencao.setNomeMecanico(mecanico);
            mManutencao.setLocal(local);
            mManutencao.setValor(valorPago);
            mManutencao.setCupom_nota(notaCupom);

            db.close();
            return mManutencao;
        }

        return mManutencao;
    }

    public ArrayList<Manutencao> gelAllManutencao(){
        ArrayList<Manutencao> listaManutencao = new ArrayList<>();

        try{
            SQLiteDatabase db = DataBaseHelper.getReadableDatabase();

            String tabela = DataBaseConstants.MANUTENCAO.TABLE_MANUTENCAO;

            String[] colunas = {
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID_CARRO,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.DATA,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOME,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM_PROX_MANUTENCAO,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.LOCAL,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.MECANICO,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.TIPO,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOTA_CUPOM,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.VALOR_PAGO};

            Cursor cursor = db.query(tabela, colunas, null, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Manutencao mManutencao = new Manutencao();

                    @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID));
                    @SuppressLint("Range") String id_carro = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID_CARRO));
                    @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOME));
                    @SuppressLint("Range") String data = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.DATA));
                    @SuppressLint("Range") String km= cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM));
                    @SuppressLint("Range") String kmProximaManutencao = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM_PROX_MANUTENCAO));
                    @SuppressLint("Range") String local = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.LOCAL));
                    @SuppressLint("Range") String mecanico = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.MECANICO));
                    @SuppressLint("Range") String tipo = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.TIPO));
                    @SuppressLint("Range") String notaCupom = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOTA_CUPOM));
                    @SuppressLint("Range") String valorPago = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.VALOR_PAGO));

                    mManutencao.setId(Integer.parseInt(id));
                    mManutencao.setIdCarro(Integer.parseInt(id_carro));
                    mManutencao.setNome(nome);
                    mManutencao.setData(data);
                    mManutencao.setKmFeitoManutencao(Integer.parseInt(km));
                    mManutencao.setKmValidade(Integer.parseInt(kmProximaManutencao));
                    mManutencao.setLocal(local);
                    mManutencao.setNomeMecanico(mecanico);
                    mManutencao.setTipoMAnutencao(tipo);
                    mManutencao.setCupom_nota(notaCupom);
                    mManutencao.setValor(valorPago);

                    listaManutencao.add(mManutencao);

                }
            }
            db.close();

            return listaManutencao;


        }catch (Exception e){
            return listaManutencao;
        }
    }

    public ArrayList<Manutencao> getListaManutencaoPorIdCarro(int idCarro){
        ArrayList<Manutencao> listaDeManutencao = new ArrayList<>();

        try{

            SQLiteDatabase db = DataBaseHelper.getReadableDatabase();

            String tabela = DataBaseConstants.MANUTENCAO.TABLE_MANUTENCAO;

            String[] colunas = {
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID_CARRO,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.DATA,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOME,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM_PROX_MANUTENCAO,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.LOCAL,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.MECANICO,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.TIPO,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOTA_CUPOM,
                    DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.VALOR_PAGO};

            String selection = DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID_CARRO+" = ? ";
            String[] selectionArgs = {String.valueOf(idCarro)};

            Cursor cursor = db.query(tabela, colunas, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Manutencao mManutencao = new Manutencao();

                    @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID));
                    @SuppressLint("Range") String id_carro = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID_CARRO));
                    @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOME));
                    @SuppressLint("Range") String data = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.DATA));
                    @SuppressLint("Range") String km= cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM));
                    @SuppressLint("Range") String kmProximaManutencao = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.KM_PROX_MANUTENCAO));
                    @SuppressLint("Range") String local = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.LOCAL));
                    @SuppressLint("Range") String mecanico = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.MECANICO));
                    @SuppressLint("Range") String tipo = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.TIPO));
                    @SuppressLint("Range") String notaCupom = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.NOTA_CUPOM));
                    @SuppressLint("Range") String valorPago = cursor.getString(cursor.getColumnIndex(DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.VALOR_PAGO));

                    mManutencao.setId(Integer.parseInt(id));
                    mManutencao.setIdCarro(Integer.parseInt(id_carro));
                    mManutencao.setNome(nome);
                    mManutencao.setData(data);
                    mManutencao.setKmFeitoManutencao(Integer.parseInt(km));
                    mManutencao.setKmValidade(Integer.parseInt(kmProximaManutencao));
                    mManutencao.setLocal(local);
                    mManutencao.setNomeMecanico(mecanico);
                    mManutencao.setTipoMAnutencao(tipo);
                    mManutencao.setCupom_nota(notaCupom);
                    mManutencao.setValor(valorPago);

                    listaDeManutencao.add(mManutencao);

                }
            }
            db.close();

            return listaDeManutencao;


        }catch (Exception e){
            return listaDeManutencao;
        }
    }

    public boolean deleManutencao(int id){
        try {
            SQLiteDatabase db = DataBaseHelper.getWritableDatabase();
            String where = DataBaseConstants.MANUTENCAO.COLUNASMANUTENCAO.ID + " = ?";
            String[] args = {String.valueOf(id)};
            db.delete(DataBaseConstants.MANUTENCAO.TABLE_MANUTENCAO, where, args);
            db.close();
            return true;

        } catch (Exception e) {
            return false;
        }

    }



    ///////////////////////// fim da área de manutencao



    /// a partir daqui as sessão do servidor

    public boolean inserirCarrroDownloadsServidor(Carro mCarro){

        SQLiteDatabase db = DataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.FABRICANTE, mCarro.getFabricante());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.MODELO, mCarro.getModelo());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.NOME_CARRO, mCarro.getNomeCarro());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.COR, mCarro.getCor());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.ANO, mCarro.getAno());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.PLACA, mCarro.getPlaca());
        contentValues.put(DataBaseConstants.MANUTENCAO.COLUNASMEUCARRO.IDSERVIDOR, mCarro.getIdAppServidor());

        try {
            db.insert(DataBaseConstants.MANUTENCAO.TABLE_MEU_CARRO, null, contentValues);
            db.close();
            return true;
        } catch (Exception e) {
            Log.i("erro db", "//////////////************** erro de inserção ********///////");
            return false;
        }
    }


}
