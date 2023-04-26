package br.com.jair.meucarro.viewholder;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.jair.meucarro.R;
import br.com.jair.meucarro.interfa.ClickSelecaoPecasManutencao;
import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.model.Manutencao;
import br.com.jair.meucarro.model.Pecas;

public class ViewHolderSelecaoPecaManutencao extends RecyclerView.ViewHolder{

    TextView nomePecasCheckBox;
    CheckBox checkBoxSelecaoPeca;
    boolean checkboxEstado = false;
    public ViewHolderSelecaoPecaManutencao(@NonNull View itemView) {
        super(itemView);
        this.nomePecasCheckBox = (TextView) itemView.findViewById(R.id.txtNomePecasChechBox);
        this.checkBoxSelecaoPeca = (CheckBox) itemView.findViewById(R.id.checkBoxPecasSelecionada);

    }

    public void binding(Pecas peca, ClickSelecaoPecasManutencao click, Manutencao mManutencao, ArrayList<Carro> listCar){
        String nomeCarro = "";
        for (int i=0; i<listCar.size();i++){
            if(peca.getId_carro()==listCar.get(i).getId()){
                nomeCarro = listCar.get(i).getNomeCarro();
            }
        }

        this.nomePecasCheckBox.setText(peca.getNome()+" "+peca.getReferencia()+"  ("+peca.getData()+") para o carro "+nomeCarro);

        if(mManutencao.getId()==peca.getId_manutencao()){
            checkBoxSelecaoPeca.setSelected(true);
            checkBoxSelecaoPeca.setChecked(true);
            checkboxEstado = true;
            checkBoxSelecaoPeca.setText("Vinculado");
        }

        this.checkBoxSelecaoPeca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (checkboxEstado == false) {
                    checkBoxSelecaoPeca.setSelected(true);
                    checkboxEstado = true;
                    checkBoxSelecaoPeca.setText("Vinculado");
                } else if (checkboxEstado == true) {
                    checkboxEstado = false;
                    checkBoxSelecaoPeca.setSelected(false);
                    checkBoxSelecaoPeca.setText("Desvinculado");
                }
                click.clickSelectId(peca,checkboxEstado);
            }
        });

    }
}

