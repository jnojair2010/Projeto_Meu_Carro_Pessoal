package br.com.jair.meucarro.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import br.com.jair.meucarro.R;
import br.com.jair.meucarro.SelecionarPecasAmanutencao;
import br.com.jair.meucarro.manager.Manager;
import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.model.Manutencao;

public class CadastraManutencaoFragments extends Fragment implements DatePickerDialog.OnDateSetListener {

    View view;
    private ViewHolder mViewHolder = new ViewHolder();
    private String data;
    private String dtCompradaPecas;
  //  private boolean bDataManutencao = false;
  //  private boolean bDataCompradaPecas = false;
    private Manager mManager;
    private Manutencao manutencao = new Manutencao();

    Manutencao mManutencao = new Manutencao();
    ArrayList<Carro> listaCarro = new ArrayList<>();


    public CadastraManutencaoFragments(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.cadastrar_manutencao_fragements, container, false);

        mManager = new Manager(getContext());

        this.mViewHolder.dataManutencao = (EditText) view.findViewById(R.id.edtDataManutencao);     //
        this.mViewHolder.kmAtual = (EditText) view.findViewById(R.id.edtKmAtualCar);                //
        this.mViewHolder.nomeManutencao = (EditText)  view.findViewById(R.id.edtNomeManutencao);    ///
        this.mViewHolder.localManutencao = (EditText) view.findViewById(R.id.edtLocalManutencao);   //
        this.mViewHolder.nomeMecanico = (EditText) view.findViewById(R.id.edtNomeMecanico);         //
        this.mViewHolder.preco = (EditText) view.findViewById(R.id.edtPrecoManutencao);             //
        this.mViewHolder.selectCar = (Spinner) view.findViewById(R.id.spnCar);
       // this.mViewHolder.dataCompraDasPecas = (EditText) view.findViewById(R.id.edtDataCompraDasPecas); //
     //   this.mViewHolder.calendario2 = (Button) view.findViewById(R.id.btnCalendario2);             //
        this.mViewHolder.salvar = (Button) view.findViewById(R.id.btnSalvarManutencao);             //
        this.mViewHolder.edtKmProximaManutencao = (EditText) view.findViewById(R.id.edtKmProximaManutencao);          //
        this.mViewHolder.selectTipo = (Spinner) view.findViewById(R.id.spnTipo);
        this.mViewHolder.cupomNota  = (EditText) view.findViewById(R.id.edtCupomManutencao);

        SalvarManutencao();
        loadSpiner();
        getData();
        return view;

    }
    @Override
    public void onResume() {
        super.onResume();
        gandezaPreco();
    }

    private void SalvarManutencao(){
        this.mViewHolder.salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mManutencao.setNome(mViewHolder.nomeManutencao.getText().toString().toUpperCase());
                mManutencao.setData(mViewHolder.dataManutencao.getText().toString().toUpperCase());
                mManutencao.setLocal(mViewHolder.localManutencao.getText().toString().toUpperCase());
                mManutencao.setNomeMecanico(mViewHolder.nomeMecanico.getText().toString().toUpperCase());
               mManutencao.setValor(mViewHolder.preco.getText().toString().toUpperCase());
            //    mManutencao.setDataCompraPreca(mViewHolder.dataCompraDasPecas.getText().toString().toUpperCase());
                mManutencao.setKmFeitoManutencao(Integer.parseInt( mViewHolder.kmAtual.getText().toString()));
                mManutencao.setKmValidade(Integer.parseInt(mViewHolder.edtKmProximaManutencao.getText().toString()));
                mManutencao.setTipoMAnutencao(mViewHolder.selectTipo.getSelectedItem().toString());
                mManutencao.setCupom_nota(mViewHolder.cupomNota.getText().toString().toUpperCase());

                getIdCarroSelecionado();

                boolean b = mManager.salvarManutencao(mManutencao); // armazena em uma variável b o methodo salvar da classe Manager




                manutencao = mManager.getManutencao();

               if(b==true){
                   getAlertDialogConfirmacao("manutencao salva com sucesso");
                }else{
                    toast("Houve um erro não foi possível savar");
                }

            }
        });
    }

    private void gandezaPreco() {
        this.mViewHolder.preco.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                }
                if(!b){
                    alterarGrandezaPreco();
                }
            }
        });
    }

    private void alterarGrandezaPreco() {
        Locale ptBr = new Locale("pt", "BR");
        double precoInicial = Double.parseDouble(this.mViewHolder.preco.getText().toString());

        String precoFinal = NumberFormat.getCurrencyInstance(ptBr).format(precoInicial);
        this.mViewHolder.preco.setText(precoFinal);
    }

    private void getData(){
        this.mViewHolder.dataManutencao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b!=false) {
                    selecionarData();
                }
             //   bDataManutencao = true;
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int mother, int dayOfMother) {
        int motherUpdate = mother+1;

        if(dayOfMother<10){
            data = "0"+dayOfMother+"/0"+motherUpdate+"/"+year;
        }else{
            if(mother<10){
                data = "0"+dayOfMother+"/0"+motherUpdate+"/"+year;
            }
            else{
                data = dayOfMother+"/"+motherUpdate+"/"+year;
            }
        }
            mViewHolder.dataManutencao.setText(data);
      //  }else if(bDataManutencao==false){

     //   }
    }
    private void selecionarData(){

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);

        new DatePickerDialog(getContext(),this,year,month,dayOfMonth).show();
    }
    public void getIdCarroSelecionado(){
        String nomeCarroSelecionado = this.mViewHolder.selectCar.getSelectedItem().toString();
            for(int i=0; i<this.listaCarro.size();i++){
                    if(this.listaCarro.get(i).getNomeCarro().equals(nomeCarroSelecionado)){
                        this.mManutencao.setIdCarro(this.listaCarro.get(i).getId());
                    }
             }
    }

    public void getActivitySelecionarPecas(){

        Bundle bundle = new Bundle();
        bundle.putSerializable("Manutencao",manutencao);
        Intent intent = new Intent(getContext(), SelecionarPecasAmanutencao.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void toast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void loadSpiner(){

        int tamanhoListaCarro = mManager.getListaCarro().size();       // variável armazena o tamanho do array lista do objeto carr0.

        String[] lista = new String[tamanhoListaCarro];                // variável lista de string recebe o tamanho da lista na variavel acima e armazena os nome dos carros

        this.listaCarro = mManager.getListaCarro();                    // atribui a varia lista de carro toda a lista da class Manager

        for(int i = 0;i<this.listaCarro.size();i++){                    //
            lista[i] = listaCarro.get(i).getNomeCarro();                // for que atribui os nomes dos carro no array de string acima;
        }

        // Adapter
        SpinnerAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, lista);
        //elemento spinner
        this.mViewHolder.selectCar.setAdapter(adapter);
    }

    private void getAlertDialogConfirmacao(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(msg);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public static class ViewHolder{
        EditText dataManutencao,kmAtual,nomeManutencao, localManutencao, nomeMecanico, preco, edtKmProximaManutencao, cupomNota;
        Button calendario1, salvar;
        Spinner selectTipo, selectCar;
    }

}
