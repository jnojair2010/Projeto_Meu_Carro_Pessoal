package br.com.jair.meucarro.viewholder;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.jair.meucarro.R;
import br.com.jair.meucarro.model.Manutencao;

public class ViewHolderListaManutencaoPorCarro extends RecyclerView.ViewHolder{

    TextView nomeCarro, data, nomeManutencao;
    public ViewHolderListaManutencaoPorCarro(@NonNull View itemView) {
        super(itemView);
        this.nomeCarro = (TextView) itemView.findViewById(br.com.jair.meucarro.R.id.edtNomeCarro);
        this.data = (TextView) itemView.findViewById(R.id.edtlabelData);
        this.nomeManutencao = (TextView) itemView.findViewById(R.id.edtNomeManutencao);

    }

    public void bindin(Manutencao manutencao) {

        Log.i("Viewholder", "///////////////// o nome da manutencao é: "+manutencao.getNome());

        this.nomeCarro.setText("Manutencao:");
        this.data.setText(manutencao.getData());
        this.nomeManutencao.setText(manutencao.getNome());

    }
}
