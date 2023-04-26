package br.com.jair.meucarro.viewholder;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.jair.meucarro.interfa.ClickManutencao;
import br.com.jair.meucarro.R;
import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.model.Manutencao;

public class ViewHolderListaManutencao extends RecyclerView.ViewHolder {

    TextView nome, data, nomeCarro;

    public ViewHolderListaManutencao(@NonNull View itemView) {
        super(itemView);
        this.nome = (TextView) itemView.findViewById(R.id.txtNomeManutencao);
        this.nomeCarro = (TextView) itemView.findViewById(R.id.txtNomeCarro);
        this.data = (TextView) itemView.findViewById(R.id.labelData);
    }

    public void binding(Manutencao manutencao, ClickManutencao clickManutencao, ArrayList<Carro> listaCarro){

        Carro  car = new Carro();
        for(int i =0; i<listaCarro.size(); i++){
            if(listaCarro.get(i).getId()==manutencao.getIdCarro()){
                car= listaCarro.get(i);
            }
        }
        data.setText(manutencao.getData());
        nomeCarro.setText(car.getNomeCarro()+":");
        nome.setText(manutencao.getNome());
        nome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickManutencao.getManutencao(manutencao);

            }
        });

       // data.setText(manutencao.getData());
    }

}
