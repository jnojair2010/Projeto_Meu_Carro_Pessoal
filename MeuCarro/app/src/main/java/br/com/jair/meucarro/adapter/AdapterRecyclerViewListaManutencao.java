package br.com.jair.meucarro.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.jair.meucarro.interfa.ClickManutencao;
import br.com.jair.meucarro.R;
import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.model.Manutencao;
import br.com.jair.meucarro.viewholder.ViewHolderListaManutencao;

public class AdapterRecyclerViewListaManutencao extends RecyclerView.Adapter<ViewHolderListaManutencao> {

    private static ArrayList<Manutencao> mListaManutencao = new ArrayList<>();

    private ClickManutencao mClickManutencao;

    private static ArrayList<Carro> listaCarro = new ArrayList<>();


    public AdapterRecyclerViewListaManutencao(ArrayList<Manutencao> lista, ClickManutencao mClickLista, ArrayList<Carro> listaCar){
        mListaManutencao = lista;
        this.listaCarro = listaCar;

        if(lista.size()>0){
            mClickManutencao = mClickLista;
        }

    }

    @NonNull
    @Override
    public ViewHolderListaManutencao onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.recyclerview_lista_manutencao,parent,false);
        return new ViewHolderListaManutencao(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListaManutencao holder, int position) {
        Manutencao mManutencao = mListaManutencao.get(position);
        holder.binding(mManutencao, mClickManutencao, listaCarro);

    }

    @Override
    public int getItemCount() {
        return mListaManutencao.size();
    }


}
