package br.com.jair.meucarro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.jair.meucarro.interfa.ClicksMeusCarro;
import br.com.jair.meucarro.R;
import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.viewholder.ViewHolderMeuCarro;

public class AdapterRecicleViewMeuCarro extends RecyclerView.Adapter<ViewHolderMeuCarro>{

    private ArrayList<Carro> mlistCar = new ArrayList<Carro>();

    private ClicksMeusCarro mClickListener;


    public AdapterRecicleViewMeuCarro(ArrayList<Carro> carros, ClicksMeusCarro mClickListener){
        this.mClickListener = mClickListener;
        this.mlistCar = carros;
    }

    @NonNull
    @Override
    public ViewHolderMeuCarro onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.recicley_view_meus_carros, parent, false);
        return new ViewHolderMeuCarro(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMeuCarro holder, int position) {
        Carro carros = this.mlistCar.get(position);
        holder.binding(carros,mClickListener);

    }

    @Override
    public int getItemCount() {

        return this.mlistCar.size();
    }
}
