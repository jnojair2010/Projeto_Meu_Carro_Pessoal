package br.com.jair.meucarro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.jair.meucarro.http.RetrofitCliente;
import br.com.jair.meucarro.http.ServiceWEb;
import br.com.jair.meucarro.http.model.CarrosHttp;
import br.com.jair.meucarro.http.model.ManutencaoHttp;
import br.com.jair.meucarro.http.model.ManutencaoHttpParaDeletar;
import br.com.jair.meucarro.http.model.PecasHttp;
import br.com.jair.meucarro.http.model.SalvarUsuario;
import br.com.jair.meucarro.http.model.UsuarioDaoHttp;
import br.com.jair.meucarro.manager.Manager;
import br.com.jair.meucarro.manager.Permissoes;
import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.model.Manutencao;
import br.com.jair.meucarro.model.Pecas;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityBackup extends AppCompatActivity {

    private ActionBar bar;

    private UsuarioDaoHttp usuarioServidor = new UsuarioDaoHttp();

    private Manutencao mManutencao = new Manutencao();
    private Carro mCarro = new Carro();
    private List<Carro> listaCarros = new ArrayList<>();
    private Pecas mPecas = new Pecas();

    private ViewHolder mViewHolder = new ViewHolder();
    private File xmlFile = null;

    private Manager mManager;

    private static final int CREATE_FILE = 1;

    private ServiceWEb mServiceWeb = RetrofitCliente.createService(ServiceWEb.class);

    JSONArray jsonListaManutencao = new JSONArray();
    JSONArray jsonListaPecas = new JSONArray();
    JSONArray jsonListaCarros = new JSONArray();

    private static String[] permissoesNecessarias = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup);

        Permissoes.permissaoArquivos(1,this, permissoesNecessarias);
        this.bar = getSupportActionBar();
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2d2d2d")));
        this.bar.setTitle("Backup");
        bar.setDisplayHomeAsUpEnabled(true);

        this.mViewHolder.btnDownlodas = (Button) findViewById(R.id.btn_downlodas);
        this.mViewHolder.btnUplodas = (Button) findViewById(R.id.btn_uplodas);
        this.mViewHolder.nome = (EditText) findViewById(R.id.edt_nome);
        this.mViewHolder.edtEmail =(EditText) findViewById(R.id.edt_email);
        this.mViewHolder.progressBarDownloads = (ProgressBar) findViewById(R.id.progressBarDownloads);

        mManager = new Manager(this);

        this.Uploads();
        this.downlodas();



    }


    private void downlodas() {
        this.mViewHolder.btnDownlodas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDialogDownlodas("Você clicou para baixar dados do servidor, para baixar click em confirmar");
            }
        });
    }

    private void Uploads(){
        this.mViewHolder.btnUplodas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialogUplodas(" Você clicou para enviar dados para servidor, para enviar click em confirmar");
            }
        });

    }

    private void salvarDados(){
        this.mViewHolder.progressBarDownloads.setVisibility(View.VISIBLE);

        getListaJsonManutencao();
        getListaJsonPecas();
        getListaJsonCarros();


        Call<SalvarUsuario> salvarDados = this.mServiceWeb.uploadUsuario(this.mViewHolder.nome.getText().toString(),this.mViewHolder.edtEmail.getText().toString(),jsonListaManutencao.toString(),jsonListaCarros.toString(),jsonListaPecas.toString());
                         salvarDados.enqueue(new Callback<SalvarUsuario>() {
                             @Override
                             public void onResponse(Call<SalvarUsuario> call, Response<SalvarUsuario> response) {
                                SalvarUsuario dados = response.body();

                                 Log.i("onResponsivo"," o nome do usuário é: "+dados.toString());

                                if(response.code()==500){
                                    getToast("codigo 500");
                                    //Log.i("onResponsivo"," o nome do usuário é: "+dados.getNome());
                                }else{
                                    if(response.code()==200){
                                        getToast("Backup realizado com sucesso!");
                                    }
                                }
                             }

                             @Override
                             public void onFailure(Call<SalvarUsuario> call, Throwable t) {
                                 Log.i("onFailure"," entrou na falha: "+t.getMessage().toString());
                             }
                         });
        this.mViewHolder.progressBarDownloads.setVisibility(View.INVISIBLE);
    }


    private void getDownlodasUsuario(){

            this.mViewHolder.progressBarDownloads.setVisibility(View.VISIBLE);

        Call<List<UsuarioDaoHttp>> getUser = this.mServiceWeb.getUsuario(this.mViewHolder.edtEmail.getText().toString(),"solicitarUsuario");
                                    getUser.enqueue(new Callback<List<UsuarioDaoHttp>>() {
                                        @Override
                                        public void onResponse(Call<List<UsuarioDaoHttp>> call, Response<List<UsuarioDaoHttp>> response) {

                                            usuarioServidor.setNome(response.body().get(0).getNome());
                                            usuarioServidor.setEmail(response.body().get(0).getEmail());
                                            usuarioServidor.setId(response.body().get(0).getId());

                                            getDownlodasListaCarros(response.body().get(0).getId());

                                            getDownlodasManutencao(response.body().get(0).getId());

                                            getDownlodasListaPecas(response.body().get(0).getId());

                                        }

                                        @Override
                                        public void onFailure(Call<List<UsuarioDaoHttp>> call, Throwable t) {
                                            Log.i("falha"," falha no get Usuario: "+t.getMessage());
                                        }
                                    });
    }
    private void getDownlodasListaCarros(String id){
        Call<List<CarrosHttp>> getListaCarros = this.mServiceWeb.getListaCarros(id, "solicitarListaCarro");
        getListaCarros.enqueue(new Callback<List<CarrosHttp>>() {
            @Override
            public void onResponse(Call<List<CarrosHttp>> call, Response<List<CarrosHttp>> response) {

                for(int i=0;i<response.body().size();i++){
                    mCarro.setId_manutencao(0);
                    mCarro.setFabricante(response.body().get(i).getFabricante());
                    mCarro.setNomeCarro(response.body().get(i).getNome());
                    mCarro.setModelo(response.body().get(i).getModelo());
                    mCarro.setAno(response.body().get(i).getAno());
                    mCarro.setCor(response.body().get(i).getCor());
                    mCarro.setPlaca(response.body().get(i).getPlaca());
                    mCarro.setIdAppServidor(response.body().get(i).getIdApp());
                    mManager.inserirCarrroDownloadsServidor(mCarro);
                    getListaCarrosServidor();
                }
            }

            @Override
            public void onFailure(Call<List<CarrosHttp>> call, Throwable t) {
                Log.i("Erro"," get lista Carros: "+t.getMessage());
            }
        });




        this.mViewHolder.progressBarDownloads.setVisibility(View.INVISIBLE);
    }

    private void getListaCarrosServidor(){
        this.listaCarros = mManager.conultarListaCarroDonw();


    }
    private void getDownlodasManutencao(String id){

        Call<List<ManutencaoHttp>> getListaManutencao = this.mServiceWeb.getListaManutencao(id,"solicitarListaManutencao");
                                   getListaManutencao.enqueue(new Callback<List<ManutencaoHttp>>() {
                                        @Override
                                        public void onResponse(Call<List<ManutencaoHttp>> call, Response<List<ManutencaoHttp>> response) {

                                            for(int b = 0; b<listaCarros.size();b++){

                                                for( int i =0;i<response.body().size();i++){

                                                    if(listaCarros.get(b).getIdAppServidor().equals(response.body().get(i).getIdApp())){
                                                        mManutencao.setNome(response.body().get(i).getNome());
                                                        mManutencao.setIdCarro(Integer.parseInt(response.body().get(i).getIdCarro()));
                                                        mManutencao.setKmFeitoManutencao(Integer.parseInt(response.body().get(i).getKmAtual()));
                                                        mManutencao.setData(response.body().get(i).getStrData());
                                                        mManutencao.setLocal(response.body().get(i).getLocalManutencao());
                                                        mManutencao.setKmValidade(Integer.parseInt(response.body().get(i).getKmProximatroca()));
                                                        mManutencao.setNomeMecanico(response.body().get(i).getNomeMecanico());
                                                        mManutencao.setValor(response.body().get(i).getPreco());
                                                        mManutencao.setCupom_nota(response.body().get(i).getCupom());
                                                        mManutencao.setTipoMAnutencao(response.body().get(i).getTipo());
                                                        mManutencao.setIdCarro(listaCarros.get(b).getId());

                                                        mManager.salvarManutencao(mManutencao);

                                                    }
                                                    else{
                                                       // getToast(" entrou no else do segundo for");
                                                    }

                                                }

                                            }  // fim do primeiro for
                                        }

                                        @Override
                                        public void onFailure(Call<List<ManutencaoHttp>> call, Throwable t) {
                                            Log.i("Erro"," Erro na get lista MANUTENCAO: "+t.getMessage());
                                        }
                                    });

    }
    private void getDownlodasListaPecas(String id){

        Call<List<PecasHttp>> getListaPecas = this.mServiceWeb.getListaPecas(id,"solicitarListaPecas");
                              getListaPecas.enqueue(new Callback<List<PecasHttp>>() {
                                  @Override
                                  public void onResponse(Call<List<PecasHttp>> call, Response<List<PecasHttp>> response) {

                                        for(int i=0;i<response.body().size();i++){
                                            mPecas.setId_manutencao(Integer.parseInt(response.body().get(i).getIdManutencao()));
                                            mPecas.setId_carro(Integer.parseInt(response.body().get(i).getIdCarro()));
                                            mPecas.setData(response.body().get(i).getStrData());
                                            mPecas.setNome(response.body().get(i).getNome());
                                            mPecas.setMarca(response.body().get(i).getMarca());
                                            mPecas.setReferencia(response.body().get(i).getReferencia());
                                            mPecas.setKmVidaUtil(response.body().get(i).getKmValidade());
                                            mPecas.setKmDeInstalacao(Integer.parseInt(response.body().get(i).getKmInstalacao()));
                                            mPecas.setQuantidade(response.body().get(i).getQuantidade());
                                            mPecas.setPreco(response.body().get(i).getPreco());
                                            mPecas.setCupom(response.body().get(i).getCupom());
                                            mPecas.setCupom(response.body().get(i).getCupom());
                                            mPecas.setLocal(response.body().get(i).getLocalCompra());

                                            mManager.salvarPeca(mPecas);
                                        }

                                  }

                                  @Override
                                  public void onFailure(Call<List<PecasHttp>> call, Throwable t) {
                                      Log.i("Erro"," get lista PECAS: "+t.getMessage());
                                  }
                              });


    }



  private void getListaJsonManutencao(){
              ArrayList<Manutencao> listaManutencao = new ArrayList<>();
              listaManutencao = this.mManager.getListaManutencao();



              for(int i =0;i<listaManutencao.size();i++){
                  String objetoManutencao ="{\"idApp\":\""+listaManutencao.get(i).getId()+"\",\"idCarro\":\""+listaManutencao.get(i).getIdCarro()+"\",\"nome\":\""+listaManutencao.get(i).getNome()+"\",\"km\":\""+listaManutencao.get(i).getKmFeitoManutencao()
                          +"\",\"local\":\""+listaManutencao.get(i).getLocal()+"\",\"mecanico\":\""+listaManutencao.get(i).getNomeMecanico()+"\",\"tipo\":\""+listaManutencao.get(i).getTipoMAnutencao()+"\",\"kmProximaTroca\":\"" +
                          ""+listaManutencao.get(i).getKmValidade()+"\",\"data\":\""+listaManutencao.get(i).getData()+"\",\"valorPago\":\""+listaManutencao.get(i).getValor()+"\",\"cupom\":\""+listaManutencao.get(i).getCupom_nota()+"\"}";

                  try{
                      JSONObject my_obj = new JSONObject(objetoManutencao);
                      this.jsonListaManutencao.put(my_obj);
                  }catch (Exception e){
                      Log.i("json"," o erro na formação do json foi"+e.getMessage());

                  }


                  } // fim do if do lista manutenção
                 /* try{
                      Log.i("json","a lista é: "+jsonListaManutencao.toString());
                  }catch (Exception e){
                      Log.i("json"," o erro ao listar jsonListaManutencao "+e.getMessage());
                  }*/
   }
  private void getListaJsonPecas(){
      ArrayList<Pecas> listaPecas = new ArrayList<>();
      listaPecas = this.mManager.getListaPecas();

      for(int i=0;i<listaPecas.size();i++){

          // cria a string json com os valores vindo do bd

          String  objetoPecas ="{\"idApp\":\""+listaPecas.get(i).getId()+"\",\"idCarro\":\""+listaPecas.get(i).getId_carro()+"\",\"idManutencao\":\""+listaPecas.get(i).getId_manutencao()
                  +"\",\"data\":\""+listaPecas.get(i).getData()+"\",\"nome\":\""+listaPecas.get(i).getNome()+"\",\"marca\":\""+listaPecas.get(i).getMarca()+"\",\"referencia\":\""+listaPecas.get(i).getReferencia()
                  +"\",\"kmValidade\":\""+listaPecas.get(i).getKmValidade()+"\",\"kmInstalacao\":\""+listaPecas.get(i).getKmDeInstalacao()+"\",\"quantidade\":\""+listaPecas.get(i).getQuantidade()
                  +"\",\"valor\":\""+listaPecas.get(i).getPreco()+"\",\"cupom\":\""+listaPecas.get(i).getCupom()+"\",\"local\":\""+listaPecas.get(i).getLocal()+"\"}";


          // montar o objeto json e adiciona oa array
          try{
              JSONObject my_obj = new JSONObject(objetoPecas);
               this.jsonListaPecas.put(my_obj);
          }catch (Exception e){
              Log.i("json"," o erro na formação do json foi"+e.getMessage());
          }
      }
              try{
                  Log.i("json","a lista de peças é: "+jsonListaPecas.toString());
              }catch (Exception e){
                  Log.i("json"," o erro ao listar jsonListaManutencao "+e.getMessage());
              }
    }
  private void getListaJsonCarros(){
      ArrayList<Carro> listaCarro = new ArrayList<>();
      listaCarro = this.mManager.getListaCarro();


      for(int i=0;i<listaCarro.size();i++){

          // cria a string json com os valores vindo do bd
          String  objetoCarros ="{\"idApp\":\""+listaCarro.get(i).getId()+"\",\"idManutencao\":\""+listaCarro.get(i).getId_manutencao()+"\",\"fabricante\":\""+listaCarro.get(i).getFabricante()+
                  "\",\"nomecarro\":\""+listaCarro.get(i).getNomeCarro()+"\",\"modelo\":\""+listaCarro.get(i).getModelo()+"\",\"ano\":\""+listaCarro.get(i).getAno()+"\",\"cor\":\""+listaCarro.get(i).getCor()
                  +"\",\"placa\":\""+listaCarro.get(i).getPlaca()+"\"}";


          // montar o objeto json e adiciona oa array
          try{
              JSONObject my_obj = new JSONObject(objetoCarros);
              this.jsonListaCarros.put(my_obj);

          }catch (Exception e){
              Log.i("json"," o erro na formação do json foi"+e.getMessage());
              }
         }
              try{
                  Log.i("json","a lista  de carros é: "+jsonListaCarros.toString());
              }catch (Exception e){
                  Log.i("json"," o erro ao listar jsonListaManutencao "+e.getMessage());
              }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
   private void getToast(String msg){
       Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
   }

   private void getDialogDownlodas(String msg){
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setMessage(msg);
       builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
               getDownlodasUsuario();
               getAlertDialogConfirmacao("Downlodas de dados realizado com Sucesso");
           }
       });
       builder.setNegativeButton("Negar", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
               getDownlodasUsuario();
           }
       });

       AlertDialog dialog = builder.create();
       dialog.show();
   }

    private void getDialogUplodas(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                salvarDados();
                getAlertDialogConfirmacao("Uploads de dados realizado com Sucesso");
            }
        });
        builder.setNegativeButton("Negar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void getAlertDialogConfirmacao(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public static class ViewHolder{
        Button btnDownlodas, btnUplodas;
        EditText nome, edtEmail;

        ProgressBar progressBarDownloads;
    }



}