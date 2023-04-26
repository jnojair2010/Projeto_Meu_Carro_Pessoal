package br.com.jair.meucarro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.jair.meucarro.R;
import br.com.jair.meucarro.interfa.ClickSelecaoPecasManutencao;
import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.model.Manutencao;
import br.com.jair.meucarro.model.Pecas;
import br.com.jair.meucarro.viewholder.ViewHolderSelecaoPecaManutencao;

public class AdapterReciclyViewSelecaoPecaManutencao extends RecyclerView.Adapter<ViewHolderSelecaoPecaManutencao>{

    private ArrayList<Pecas> listaPecas;
    private ArrayList<Carro> listCar;
    private ClickSelecaoPecasManutencao Onclick;
    private Manutencao manutencao = new Manutencao();

    public AdapterReciclyViewSelecaoPecaManutencao(ArrayList<Pecas> lista, ClickSelecaoPecasManutencao click, Manutencao mManutencao, ArrayList<Carro> listaCarro){
        this.Onclick = click;
        this.manutencao = mManutencao;
        this.listCar = listaCarro;
        if(lista.size()>0){
                listaPecas = lista;
        }
    }

    @NonNull
    @Override
    public ViewHolderSelecaoPecaManutencao onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater infalter = LayoutInflater.from(context);
        View view  = infalter.inflate(R.layout.recyclerview_lista_pecas_selecao, parent, false);

        return new ViewHolderSelecaoPecaManutencao(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSelecaoPecaManutencao holder, int position) {
        Pecas p = listaPecas.get(position);
        holder.binding(p,Onclick, this.manutencao, listCar);

    }

    @Override
    public int getItemCount() {
        return this.listaPecas.size();
    }
}
