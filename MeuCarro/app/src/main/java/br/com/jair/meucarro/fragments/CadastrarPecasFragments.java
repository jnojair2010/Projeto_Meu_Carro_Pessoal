package br.com.jair.meucarro.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import br.com.jair.meucarro.bd.DataBaseConstants;
import br.com.jair.meucarro.manager.Manager;
import br.com.jair.meucarro.manager.ManagerManutencao;
import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.model.Pecas;

public class CadastrarPecasFragments extends Fragment implements DatePickerDialog.OnDateSetListener {

    View view;

    ViewHolder mViewHolder = new  ViewHolder();
    private Manager mManager;
    private String data;

    private Pecas peca = new Pecas();

    private int KmVidaUtilPecas =0;
    ArrayList<Carro> listaCarro = new ArrayList<>();


    public CadastrarPecasFragments(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.cadastrar_pecas_fragements, container, false);

        mManager = new Manager(getContext());

        this.mViewHolder.data=  (EditText) view.findViewById(R.id.edtEdtData);
        this.mViewHolder.nome = (EditText) view.findViewById(R.id.edtnome);
        this.mViewHolder.marca = (EditText) view.findViewById(R.id.edtMarca);
        this.mViewHolder.referencia = (EditText) view.findViewById(R.id.edtReferencia);
        this.mViewHolder.kmVidaUtil = (EditText) view.findViewById(R.id.edtKmVidaUtil);
        this.mViewHolder.localCompra = (EditText) view.findViewById(R.id.edtLocal);
        this.mViewHolder.preco = (EditText) view.findViewById(R.id.edtPreco);
        this.mViewHolder.cupom = (EditText) view.findViewById(R.id.edtCupon);
        this.mViewHolder.quantidade = (EditText) view.findViewById(R.id.edtQuantidade);
        this.mViewHolder.selecaoCarro = (Spinner) view.findViewById(R.id.spnSelectCarro);
        this.mViewHolder.btnSalvar = (Button) view.findViewById(R.id.btnSalvarPeca);

        this.mViewHolder.data.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Toast("entrou no onKey"+view.getId());
            }
        });

        loadSpiner();
        cadastarPeca();
        getCalendario();
        return view;
    }

    private void cadastarPeca(){

        this.mViewHolder.btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peca.setData(data);
                peca.setNome(mViewHolder.nome.getText().toString().toUpperCase());
                peca.setMarca(mViewHolder.marca.getText().toString().toUpperCase());
                peca.setKmVidaUtil( String.valueOf(KmVidaUtilPecas));
                peca.setReferencia(mViewHolder.referencia.getText().toString().toUpperCase());
                peca.setLocal(mViewHolder.localCompra.getText().toString().toUpperCase());
                peca.setPreco(mViewHolder.preco.getText().toString());
                peca.setCupom(mViewHolder.cupom.getText().toString());
                peca.setQuantidade(mViewHolder.quantidade.getText().toString());

                   for(int i = 0;i<listaCarro.size();i++){

                        if(mViewHolder.selecaoCarro.getSelectedItem().toString().equals(listaCarro.get(i).getNomeCarro())){
                           peca.setId_carro(listaCarro.get(i).getId());
                        }
                    }
                salvarPeca();
            }

        });

    }
    @Override
    public void onResume() {
        super.onResume();
        grandezaKm();
        gandezaPreco();
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


    private void salvarPeca(){
     boolean b =   mManager.salvarPeca(this.peca);
         if(b!=false){
             this.mViewHolder.nome.setText("");
             this.mViewHolder.data.setText("");
             this.mViewHolder.kmVidaUtil.setText("");
             this.mViewHolder.preco.setText("");
             this.mViewHolder.localCompra.setText("");
             this.mViewHolder.cupom.setText("");
             this.mViewHolder.referencia.setText("");
             this.mViewHolder.quantidade.setText("");
             this.mViewHolder.marca.setText("");
             getAlertDialogConfirmacao(" Peça Salvo com sucesso");
         }else{
             Toast(" Não foi Salvo, Algo deu Errado");
         }
    }


    private void loadSpiner(){

        //ArrayLista de Objeto String
        int tamanhoListaCarro = mManager.getListaCarro().size();

        String[] lista = new String[tamanhoListaCarro];

        this.listaCarro = mManager.getListaCarro();
        for(int i = 0;i<this.listaCarro.size();i++){
            lista[i] = listaCarro.get(i).getNomeCarro();
            Log.i(" inforCarro","/////////////////////////// o id do carro é: "+listaCarro.get(i).getId());
        }

        // Adapter
        SpinnerAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, lista);
        //elemento spinner
        this.mViewHolder.selecaoCarro.setAdapter(adapter);
    }
    private void getCalendario(){
        this.mViewHolder.data.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b!=false){
                    selecionarData();
                }

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
        this.mViewHolder.data.setText(data);
    }
    private void selecionarData(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);

        new DatePickerDialog(getContext(),this,year,month,dayOfMonth).show();
    }

    private void grandezaKm(){
        this.mViewHolder.kmVidaUtil.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b==false){
                    alteraGrandezaKM();
                }
            }
        });
    }
    public void alteraGrandezaKM(){
        Locale localeBR = new Locale("pt","BR");

        int numero = Integer.parseInt(mViewHolder.kmVidaUtil.getText().toString());

        String valor = mViewHolder.kmVidaUtil.getText().toString();
        this.KmVidaUtilPecas = Integer.parseInt(valor);

        int numeroCaracteres = valor.length();

        if(numeroCaracteres>3) {
            NumberFormat inter = NumberFormat.getInstance();
            String inteiro = inter.format(numero);
            mViewHolder.kmVidaUtil.setText(inteiro);
        }
    }


    private void Toast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void getAlertDialogConfirmacao(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(msg);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static class ViewHolder{

        EditText data;
        EditText nome;
        EditText marca;
        EditText referencia;
        EditText kmVidaUtil;
        EditText localCompra;
        EditText preco;
        EditText cupom;
        EditText quantidade;
        Spinner selecaoCarro;
        Button btnSalvar;
        Button buttonCalentadar;
    }
}

/*
                Locale localeBR = new Locale("pt","BR");
              int numero = Integer.parseInt(mViewHolder.kmVidaUtil.getText().toString());
              String tamanho = mViewHolder.kmVidaUtil.getText().toString();
              int numeroCaracteres = tamanho.length();
              if(numeroCaracteres>3){

                  NumberFormat inter = NumberFormat.getInstance();
                 String inteiro = inter.format(numero);
                  mViewHolder.kmVidaUtil.setText(inteiro);
                  Log.i("input", "////////////////////////////////// o numero de caracteres é : "+inteiro);


 */

