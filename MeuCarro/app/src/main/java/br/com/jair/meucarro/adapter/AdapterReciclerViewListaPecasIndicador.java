package br.com.jair.meucarro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.jair.meucarro.interfa.ClickPecas;
import br.com.jair.meucarro.R;
import br.com.jair.meucarro.model.Pecas;
import br.com.jair.meucarro.viewholder.ViewHolderListaPecasIndicador;

public class AdapterReciclerViewListaPecasIndicador extends RecyclerView.Adapter<ViewHolderListaPecasIndicador>{

    private static ArrayList<Pecas> listaPecas;
    private ClickPecas clickListnerPecas;

    public AdapterReciclerViewListaPecasIndicador(ArrayList<Pecas> lista, ClickPecas clickListener){
           listaPecas= lista;
           clickListnerPecas = clickListener;
    }

    @NonNull
    @Override
    public ViewHolderListaPecasIndicador onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_lista_pecas_indicador, parent, false);
        return new ViewHolderListaPecasIndicador(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListaPecasIndicador holder, int position) {
        Pecas pecas = listaPecas.get(position);
        holder.biding(pecas, clickListnerPecas);

    }

    @Override
    public int getItemCount() {
        return listaPecas.size();
    }
}
