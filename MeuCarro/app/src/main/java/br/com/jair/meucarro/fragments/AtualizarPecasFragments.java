package br.com.jair.meucarro.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import br.com.jair.meucarro.R;
import br.com.jair.meucarro.manager.Manager;
import br.com.jair.meucarro.model.Pecas;

public class AtualizarPecasFragments extends Fragment implements DatePickerDialog.OnDateSetListener{
    private View view;
    private String data;
    private EditText atData, atNome, atMarca, atReferencia, atKmVidaUtil, atLocal, atPreco, atCupom, atQuantidade, edtKmAtual;
    private Button atAtualizar;

    private Pecas  rPeca = new Pecas();

    private Pecas mPeca = new Pecas();

    private Manager mManager;

    public AtualizarPecasFragments(){

    }

    public AtualizarPecasFragments(Pecas peca){
        this.rPeca = peca;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.atualizar_pecas_fragments, container, false);
        atAtualizar = (Button) view.findViewById(R.id.btnAtAtualizarPeca);
        atData = (EditText) view.findViewById(R.id.edtAtData);
        atNome = (EditText) view.findViewById(R.id.edtAtNome);
        atMarca= (EditText) view.findViewById(R.id.edtAtMarca);
        atReferencia = (EditText) view.findViewById(R.id.edtAtReferencia);
        atKmVidaUtil = (EditText) view.findViewById(R.id.edtAtKmVidaUtil);
        atLocal= (EditText) view.findViewById(R.id.edtAtLocal);
        atPreco = (EditText) view.findViewById(R.id.edtAtPreco);
        atCupom = (EditText) view.findViewById(R.id.edtAtCupom);
        atQuantidade= (EditText) view.findViewById(R.id.edtAtQuantidade);
        edtKmAtual = (EditText) view.findViewById(R.id.edtAtKmAtual);


        atNome.setText(rPeca.getNome());
        atMarca.setText(rPeca.getMarca());
        atReferencia.setText(rPeca.getReferencia());
        atKmVidaUtil.setText(rPeca.getKmVidaUtil());
        atLocal.setText(rPeca.getLocal());
        atPreco.setText(rPeca.getPreco());
        atCupom.setText(rPeca.getCupom());
        atQuantidade.setText(rPeca.getQuantidade());

        this.mManager = new Manager(getContext());

        setData();
        AtualizarPeca();
            return view;
    }
    public void AtualizarPeca(){
        this.atAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(atData.getText().toString().equals("Data Selecionda")){
                        dialogAlerta("Selecione uma data!");
                    }else{
                        if(atData.getText().toString().equals("")){
                            dialogAlerta("Selecione uma data!");
                        }else{
                            if(edtKmAtual.getText().toString().equals("")){
                                dialogAlerta("Digite o km atual, \n de quando trocou essa pecas!");
                            }else{
                                mPeca.setId(rPeca.getId());
                                mPeca.setData(atData.getText().toString());
                                mPeca.setNome(atNome.getText().toString());
                                mPeca.setMarca(atMarca.getText().toString());
                                mPeca.setReferencia(atReferencia.getText().toString());
                                mPeca.setKmVidaUtil(atKmVidaUtil.getText().toString());
                                mPeca.setLocal(atLocal.getText().toString());
                                mPeca.setPreco(atPreco.getText().toString());
                                mPeca.setCupom(atCupom.getText().toString());
                                mPeca.setQuantidade(atQuantidade.getText().toString());
                                mPeca.setKmDeInstalacao(Integer.parseInt(edtKmAtual.getText().toString()));

                                boolean b = false;
                               b = mManager.atualizarPecas(mPeca);
                               if(b!=false){
                                    dialogAlerta("Atualização realizada com sucesso");
                                }else{
                                   dialogAlerta("Não foi possível atualizar devido \n a um erro enesperado");
                               }
                            }

                        }

                    }
            }
        });

    }

    private void setData(){
        this.atData.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        this.atData.setText(data);

    }
    private void selecionarData(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);

        new DatePickerDialog(getContext(),this,year,month,dayOfMonth).show();
    }

    private void dialogAlerta(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(msg);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
