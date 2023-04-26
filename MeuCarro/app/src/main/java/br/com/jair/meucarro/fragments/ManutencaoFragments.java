package br.com.jair.meucarro.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.jair.meucarro.interfa.ClickManutencao;
import br.com.jair.meucarro.R;
import br.com.jair.meucarro.SelecionarPecasAmanutencao;
import br.com.jair.meucarro.adapter.AdapterRecyclerViewListaManutencao;
import br.com.jair.meucarro.manager.Manager;
import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.model.Manutencao;

public class ManutencaoFragments extends Fragment {

   private View view;

   private Manager mManager;
   ClickManutencao mClickManutencao;

   ArrayList<Carro> listaCarro = new ArrayList<>();

    public ManutencaoFragments(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view = inflater.inflate(R.layout.manutencao_fragements, container,false);
      this.mManager = new Manager(getContext());

        this.getListaCarro();

       mClickManutencao = new ClickManutencao() {
           @Override
           public void getId(int id) {

           }

           @Override
           public void getManutencao(Manutencao mManutencao) {
               Bundle bundle = new Bundle();
               bundle.putSerializable("Manutencao",mManutencao);
               Intent intent = new Intent(getContext(), SelecionarPecasAmanutencao.class);
               intent.putExtras(bundle);
               intent.putExtra("statusManutencao",true);
               startActivity(intent);
           }
       };

     return view;
    }

    @Override
    public void onStart() {
     super.onStart();
     updateListaManutencao();
    }

    private void getListaCarro(){
        this.listaCarro = mManager.getmArrayList();
    }


    @Override
    public void onResume() {
     super.onResume();
     getToast("Click em uma Manutenção desta Lista");
     updateListaManutencao();

    } // fim do metodo onResume

    private void updateListaManutencao(){

     ArrayList<Manutencao> lista = new ArrayList<>();
     lista = this.mManager.getListaManutencao();

     RecyclerView recyclerView = (RecyclerView) this.view.findViewById(R.id.recycleviewListaManutencao);
     recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
     AdapterRecyclerViewListaManutencao mAdapter = new AdapterRecyclerViewListaManutencao(lista,mClickManutencao, this.listaCarro);
     recyclerView.setAdapter(mAdapter);

    }

    private void getToast(String msg){
        Toast.makeText(getContext(),msg, Toast.LENGTH_LONG).show();
    }

}
