package br.com.jair.meucarro.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;

//import br.com.jair.meucarro.CadastrarCarro;
import br.com.jair.meucarro.R;
import br.com.jair.meucarro.manager.Manager;
import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.viewmodel.CadastrarCarroViewModel;

public class CadastarCarroFragments extends Fragment implements DatePickerDialog.OnDateSetListener {

    private View view;
    private ViewHolder mViewHolder = new ViewHolder();
    private CadastrarCarroViewModel cadastrarCarroViewModel;

    Carro mCarro = new Carro();

    public CadastarCarroFragments() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        this.view = inflater.inflate(R.layout.cadastro_carro_fragmemts, container, false);

        cadastrarCarroViewModel = new ViewModelProvider(this).get(CadastrarCarroViewModel.class);
        this.mViewHolder.txtFabricante = (EditText) view.findViewById(R.id.edtFabricante);
        this.mViewHolder.txtModelo = (EditText) view.findViewById(R.id.edtModelo);
        this.mViewHolder.txtNomeCarro = (EditText) view.findViewById(R.id.edtNomeCarro);
        this.mViewHolder.txtCor = (EditText) view.findViewById(R.id.edtCor);
        this.mViewHolder.txtAno = (EditText) view.findViewById(R.id.edtAno);
        this.mViewHolder.txtPlaca = (EditText) view.findViewById(R.id.edtPlaca);
        this.mViewHolder.btnSalvar = (Button) view.findViewById(R.id.btnSalvarCarro);
        setListener();

        return view;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

    private void selecionarData(){

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);

        new DatePickerDialog(getContext(),this,year,month,dayOfMonth).show();
    }
    private void setListener(){
        this.mViewHolder.btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Manager managerCarro = new Manager(getContext());
                Carro meuCarro = new Carro();
                mCarro.setFabricante(mViewHolder.txtFabricante.getText().toString().toUpperCase());
                mCarro.setModelo(mViewHolder.txtModelo.getText().toString().toUpperCase());
                mCarro.setNomeCarro(mViewHolder.txtNomeCarro.getText().toString().toUpperCase());
                mCarro.setCor(mViewHolder.txtCor.getText().toString().toUpperCase());
                mCarro.setAno(mViewHolder.txtAno.getText().toString().toUpperCase());
                mCarro.setPlaca(mViewHolder.txtPlaca.getText().toString().toUpperCase());
                //cadastrarCarroViewModel.salvarCarro(mCarro);

                Boolean booleanCarro = managerCarro.insertCarro(mCarro); // envia o objeto carro para ser inserido na tabela do bd

                if(booleanCarro!=false){
                    getAlertDialogConfirmacao(" Carro Salvo com Sucesso \n OU este carro ja estava salvo");
                }else{
                    getToast(" Algo deu errado seu Carro não foi salvo ");
                }

            }
        });


    }

    private void getToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }
    private void getAlertDialogConfirmacao(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(msg);

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public static class ViewHolder{

        EditText txtFabricante;
        EditText txtModelo;
        EditText txtNomeCarro;
        EditText txtCor;
        EditText txtAno;
        EditText txtPlaca;
        Button btnSalvar;

    }

}
