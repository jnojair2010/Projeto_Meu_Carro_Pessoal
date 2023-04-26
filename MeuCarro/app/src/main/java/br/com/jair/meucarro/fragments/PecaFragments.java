package br.com.jair.meucarro.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.jair.meucarro.DetalhePecas;
import br.com.jair.meucarro.SelecionarPecasAmanutencao;
import br.com.jair.meucarro.interfa.ClickPecas;
import br.com.jair.meucarro.R;
import br.com.jair.meucarro.adapter.AdapterReciclerViewListaPecasIndicador;
import br.com.jair.meucarro.manager.Manager;
import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.model.Pecas;

public class PecaFragments extends Fragment {
    ClickPecas clicklistnerPecas;
    View view;
    private Manager mManager;
    private static ArrayList<Pecas> listaPecas = new ArrayList<>();
    Button btnBuscar;

    TextView edtKM;
    private int km;
    private viewHolder mViewHolder = new viewHolder();
    ArrayList<Carro> listaCarro = new ArrayList<>();

    private Carro  car = new Carro();

   private static ArrayList<Pecas>  listPecas = new ArrayList<>();                                                 // váriável objeto arraay lista

    public PecaFragments(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.pecas_fragements, container, false);
        this.mViewHolder.btnBuscar = (Button) view.findViewById(R.id.btnBuscar);
        this.mViewHolder.edtKM = (TextView) view.findViewById(R.id.edtKM);
        this.mViewHolder.selectCar = (Spinner) view.findViewById(R.id.spnSelectCarroPecasFragments);

        this.mViewHolder.edtKM.setText("Digite o KM");
        this.mManager = new Manager(getContext());

        this.listaPecas = mManager.getListaPecas();

        loadSpiner();

        Buscar();
        limpaEdtKmOnFocu();

        this.clicklistnerPecas = new ClickPecas() {
            @Override
            public void onClickId(int id) {
            }

            @Override
            public void onClickListenerPecas(Pecas peca) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("ObjetoPeca",peca);
                Intent intent = new Intent(getContext(), DetalhePecas.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
        return view;

    } // fim do metodo onCreat

    @Override
    public void onStart() {
        super.onStart();

    } // fim do metodo onStar

    @Override
    public void onResume() {
        super.onResume();
        getListaPecas();

    } // fim do metodo onResume

    private void limpaEdtKmOnFocu(){
        this.mViewHolder.edtKM.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mViewHolder.edtKM.setText("");
            }
        });
    }

    private void Buscar(){                                                                          // metodo que busca lista de peca para montar na tela
        this.mViewHolder.btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    listaPecas = mManager.getListaPecas();                                          // adiciona a variável listaPecas todos os objetos consultado no banco de dado

                            listPecas.clear();                                                      // esvazia a variavel lispecas

                    for(int i =0; i<listaCarro.size();i++){                                         // for que verifica o carro por nome e adciona a variável objeto de carro o carro corespondente
                            if(mViewHolder.selectCar.getSelectedItem().toString().equals(listaCarro.get(i).getNomeCarro())){
                                car = listaCarro.get(i);
                            }
                    }

                    for(int i=0;i<listaPecas.size();i++){
                        if(car.getId()==listaPecas.get(i).getId_carro()){

                            listPecas.add(listaPecas.get(i));
                        }

                    }

                updateRecycleViewPecas();
            }   // fim do metodo onclik da função seOnClick|Listener
        });
    }

    private void updateRecycleViewPecas(){
             String km = this.mViewHolder.edtKM.getText().toString();
                   if(km.equals("")){
                       getToast("Campo vazio, preencha campo Corretamente0");
                   }else if(km.equals("Digite o KM")){
                       getToast("Digite um número km Válido");
                   }else{
                       this.mManager.setKmAtual(Integer.parseInt(km));
                       monstrarRecucliViewPecas();
                   }
    }

    private void monstrarRecucliViewPecas(){
        boolean listado = false;
        int kmAtual = mManager.getKmAtual();

        for(int i =0;i<listaPecas.size();i++){
            listaPecas.get(i).CalcularKmFaltante(kmAtual, getContext());
        }
        RecyclerView recyclerView = (RecyclerView) this.view.findViewById(R.id.recycleviewListaPecas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterReciclerViewListaPecasIndicador adapterListaPecas = new AdapterReciclerViewListaPecasIndicador(listPecas,clicklistnerPecas);
        recyclerView.setAdapter(adapterListaPecas);
    }


    private void getListaPecas(){

        this.listaPecas = mManager.getListaPecas();

    }

    private void getToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void loadSpiner(){

        //ArrayLista de Objeto String
        int tamanhoListaCarro = mManager.getListaCarro().size();

        String[] lista = new String[tamanhoListaCarro];

        this.listaCarro = mManager.getListaCarro();
        for(int i = 0;i<this.listaCarro.size();i++){
            lista[i] = listaCarro.get(i).getNomeCarro();
            Log.i(" inforCarro","/////////////////////////// o id do carro é: "+listaCarro.get(i).getId());
        }

        // Adapter
        SpinnerAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, lista);
        //elemento spinner
        this.mViewHolder.selectCar.setAdapter(adapter);
    }
    private static class viewHolder{
         Spinner selectCar;
         Button btnBuscar;
        TextView edtKM;
    }

}
