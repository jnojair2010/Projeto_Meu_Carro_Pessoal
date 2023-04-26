package br.com.jair.meucarro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.jair.meucarro.R;
import br.com.jair.meucarro.model.Manutencao;
import br.com.jair.meucarro.viewholder.ViewHolderListaManutencaoPorCarro;

public class AdapterRecyclerViewListaManutencaoPorCarro  extends RecyclerView.Adapter<ViewHolderListaManutencaoPorCarro>{

    private static ArrayList<Manutencao> listaManutencao = new ArrayList<>();
    public AdapterRecyclerViewListaManutencaoPorCarro(ArrayList<Manutencao>  lista){
    this.listaManutencao = lista;
    }

    @NonNull
    @Override
    public ViewHolderListaManutencaoPorCarro onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.recicler_view_lista_manutencao_por_carro, parent, false);
        return new ViewHolderListaManutencaoPorCarro(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListaManutencaoPorCarro holder, int position) {
        Manutencao mManutencao = listaManutencao.get(position);
        holder.bindin(mManutencao);

    }

    @Override
    public int getItemCount() {
        return this.listaManutencao.size();
    }
}
