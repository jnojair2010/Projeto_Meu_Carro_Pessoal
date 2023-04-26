package br.com.jair.meucarro.manager;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.model.Manutencao;
import br.com.jair.meucarro.model.Pecas;

public class Manager {
    private Context contex;

    private Repositorio mRepositorio;
    private ManagerManutencao mManagerManutencao;
    private static ArrayList<Pecas> listaPecas = new ArrayList<>();
    public static int kmAtual =0;
    public static Carro mCarro = new Carro();
    public static Manutencao manutencaoCadastrado = new Manutencao();

    private static ArrayList<Manutencao> listaManutencao = new ArrayList<>();
    private static ArrayList<Carro> mArrayList = new ArrayList<>();






    public static ArrayList<Carro> getmArrayList() {
        return mArrayList;
    }

    public static void setmArrayList(ArrayList<Carro> mArrayList) {
        Manager.mArrayList = mArrayList;
    }

    public Manager(Context context){
        this.mRepositorio = Repositorio.getInstance(context);
        mRepositorio = mRepositorio.getInstance(context);
    }


     // início dos metodos de pecas
     public boolean salvarPeca(Pecas peca){
         return this.mRepositorio.insertPeca(peca);

     }

    public ArrayList<Pecas> getListaPecas() {
       // Log.i(" km","//////////// chegou no metodo getListaPecas()");
        listaPecas =  this.mRepositorio.getAllPecas();

        return listaPecas;
    }
    public ArrayList<Pecas> getListaPecasManutencao() {
        return this.mRepositorio.getAllPecasManutencao();
    }

    public static void setListaPecas(ArrayList<Pecas> listaPecas) {
        Manager.listaPecas = listaPecas;
    }

    public  int getKmAtual() {

        return kmAtual;
    }

    public void setKmAtual(int km) {
        Manager.kmAtual = km;
    }

    public ArrayList<Manutencao> getListaManutencao() {
        this.getAllManutencao();
        return listaManutencao;
    }

    public static void setListaManutencao(ArrayList<Manutencao> listaManutencao) {
        Manager.listaManutencao = listaManutencao;
    }

    public boolean updatePecas(Manutencao mManutencao, Pecas pecas){
       return this.mRepositorio.updatePecas(mManutencao,pecas);

    }

    public boolean deletarPeca(int id){

        return this.mRepositorio.deletarPeca(id);
    }


    // fim dos metodos de pecas



    // daqui em diantes metodos só de carros

    public Boolean insertCarro(Carro carro){          // metodo de salvar carro na tabela do bd
        this.consultarCarro(carro);                 // primeiro consulta se há um carro

        if(this.mCarro.getId()>0){
        return true;
        }else{
            this.mRepositorio.insert(mCarro);
            consultarCarro(mCarro);
            if(this.mCarro.getId()>0){
                return true;
            }
            return false;
        }

    }
    public void consultarCarro(Carro carro){

       this.mCarro = this.mRepositorio.consultarCarro(carro);

    }
    public ArrayList<Carro> consultarMeusCarro(){
        mManagerManutencao.setListaCarro(this.mRepositorio.consultarMeusCarros());
        return this.mRepositorio.consultarMeusCarros();
    }

    public ArrayList<Carro> getListaCarro(){
        mManagerManutencao.setListaCarro(this.mRepositorio.consultarMeusCarros());
      return  this.mArrayList = this.mRepositorio.consultarMeusCarros();

    }


    public boolean deletarCarro(int id){
       // Log.i(" methodo","//////////// chamou o methodo dele na manageCarro");
        return  this.mRepositorio.deletarCarro(id);
    }

    public boolean salvarFoto(Carro carro){
        return this.mRepositorio.salvarFotosRespositorio(carro);
    }

    // fim dos metodos de carros



    // a partir daqui funcoes que trabalham só manutencao
    public boolean salvarManutencao(Manutencao manutencao){
        Manutencao manutencaoRetorno = new Manutencao();

        boolean b = this.mRepositorio.insertManutencao(manutencao);
        Log.i(" manutencao  ", "///////////////////// o id do carro é "+manutencao.getIdCarro());
        if(b==true){
            manutencaoRetorno = mRepositorio.getmanutencao(manutencao);
            Log.i(" manutencao  ", "////////////////////////// entrou na analize b como true e o id é ///////////////////////////////"+manutencaoRetorno.getId());
        }else if(manutencao.getId()>0){
            Log.i(" manutencao  ", "////////////////////////// entrou no id maior do que 0 e o id é  //////////////////////////////////"+manutencaoRetorno.getId());
            this.manutencaoCadastrado = manutencaoRetorno;
            b = true;
        } else{
            Log.i(" manutencao  ", "////////////////////////// entrou no b false //////////////////////////////////");
           b = false;
        }
        return b;
    }

    public Manutencao getManutencao(){
        return this.manutencaoCadastrado;
    }

    public void getAllManutencao(){
        ArrayList<Manutencao> lista = new ArrayList<>();
        listaManutencao = this.mRepositorio.gelAllManutencao();

    }

    public ArrayList<Manutencao> getManutencaoPorIdCarro(int id_carro){

        return this.mRepositorio.getListaManutencaoPorIdCarro(id_carro);
    }

    public boolean deletManutencao(int id){
        return  mRepositorio.deleManutencao(id);
    }
  /*  public Pecas getListaManutencao(String data){

    }*/

    public void inserirCarrroDownloadsServidor(Carro carro){
        boolean b;
       b = this.mRepositorio.inserirCarrroDownloadsServidor(carro);
    }
    public List<Carro> conultarListaCarroDonw(){
        return this.mRepositorio.consultarMeusCarros();
    }

    public boolean atualizarPecas(Pecas peca){
       return this.mRepositorio.aTualizarPeca(peca);

    }



}
