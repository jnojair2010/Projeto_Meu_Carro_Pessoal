package br.com.jair.meucarro.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.jair.meucarro.R;
import br.com.jair.meucarro.SelecionarPecasAmanutencao;
import br.com.jair.meucarro.adapter.AdapterReciclyViewSelecaoPecaManutencao;
import br.com.jair.meucarro.adapter.AdapterRecyclerViewListaManutencao;
import br.com.jair.meucarro.interfa.ClickSelecaoPecasManutencao;
import br.com.jair.meucarro.manager.Manager;
import br.com.jair.meucarro.model.Manutencao;
import br.com.jair.meucarro.model.Pecas;

public class DetalheManutencaoFragments extends Fragment{

    private View view;

    private ViewHolder mViewHolder = new ViewHolder();
    private Manutencao manutencao = new Manutencao();

    ClickSelecaoPecasManutencao click;
   // private String data;
    private Manager mManger;
    SelecionarPecasAmanutencao activitySelectPecManutencao = new SelecionarPecasAmanutencao();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_detalhe_manutencao_fragments, container, false);

            Bundle  bundle = getArguments();
            if(bundle!=null){
                this.manutencao = (Manutencao) bundle.getSerializable("Manutencao");
            }

        mManger = new Manager(getContext());

        this.mViewHolder.nomeManutencao = (TextView) view.findViewById(R.id.txtNomeManutencao);
        this.mViewHolder.nomeMecanico = (TextView) view.findViewById(R.id.txtNomeMecanico);
        this.mViewHolder.localManutencao = (TextView) view.findViewById(R.id.txtLocalManutencao);
        this.mViewHolder.kmFeitoManutencao = (TextView) view.findViewById(R.id.txtKmFeitoManutencao);
        this.mViewHolder.kmProximaTroca = (TextView) view.findViewById(R.id.txtKmProxManutencao);
        this.mViewHolder.preco = (TextView) view.findViewById(R.id.txtPreco);
       // this.mViewHolder.calendario1 = (Button) view.findViewById(R.id.btnCalendario2);
       // this.mViewHolder.txtData = (TextView) view.findViewById(R.id.edtDataCompraDasPecas);
        this.mViewHolder.deletarManutencao = (Button) view.findViewById(R.id.btnDeletarManutencao);

        setAtributoManutencao();
      //  getData();
        deletarManutencao();


        this.click = new ClickSelecaoPecasManutencao() {
            @Override
            public void clickSelectId(Pecas peca, boolean marcacao) {
                    if(marcacao==true){
                        updatePeca(manutencao, peca, marcacao);
                    }else if(marcacao==false){
                        manutencao.setId(0);
                        manutencao.setKmFeitoManutencao(0);
                        updatePeca(manutencao, peca, marcacao);
                    }
            }

        };

            return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        getListaPecasManutencao();

    } // fim do metodo onResume

    private void getListaPecasManutencao(){

        ArrayList<Pecas> lista = new ArrayList<Pecas>();

        for(int i=0; i<this.mManger.getListaPecasManutencao().size();i++){
            if(manutencao.getIdCarro()==this.mManger.getListaPecasManutencao().get(i).getId_carro()){
                lista.add(this.mManger.getListaPecasManutencao().get(i));
            }
        }

      // lista =  this.mManger.getListaPecasManutencao();
       if(lista.size()>0){
           RecyclerView recyclerView = (RecyclerView) this.view.findViewById(R.id.recicleyViewSelecaoPecasMonitoramento);
           recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
           AdapterReciclyViewSelecaoPecaManutencao adapter = new AdapterReciclyViewSelecaoPecaManutencao(lista ,this.click, manutencao, mManger.getListaCarro());
           recyclerView.setAdapter(adapter);
       }else{
           toastMsg("Ainda não há pecas cadastar!");
       }

    }

    private void updatePeca(Manutencao mManutencao, Pecas peca, boolean marcacao){
            boolean b =this.mManger.updatePecas(mManutencao,peca);
            if(b==true && marcacao == true){
                toastMsg("Vinculado a Manutenção!");
            }else if(b==true && marcacao == false){
                toastMsg("Desvinculado a Manutenção!");
            }
    }

    private void deletarManutencao(){
        this.mViewHolder.deletarManutencao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean b = mManger.deletManutencao(manutencao.getId());

                if(b==true){
                    toastMsg("Manutenção deletado com sucesso");
                    setAtributoManutencaoDeletado();
                }else{
                    toastMsg("Houve um erro, não foi possível deletar");
                }


            }
        });
    }

    private void toastMsg(String mensagem){
        Toast.makeText(getContext(), mensagem, Toast.LENGTH_LONG).show();
    }

    private void setAtributoManutencao(){
        this.mViewHolder.nomeManutencao.setText(this.manutencao.getNome());
        this.mViewHolder.nomeMecanico.setText(this.manutencao.getNomeMecanico());
        this.mViewHolder.localManutencao.setText(this.manutencao.getLocal());
        this.mViewHolder.kmFeitoManutencao.setText( String.valueOf(this.manutencao.getKmFeitoManutencao()));
        this.mViewHolder.preco.setText(String.valueOf(manutencao.getValor()));
        this.mViewHolder.kmProximaTroca.setText(String.valueOf(manutencao.getKmValidade()));
    }
    private void setAtributoManutencaoDeletado(){
        this.mViewHolder.nomeManutencao.setText("");
        this.mViewHolder.nomeMecanico.setText("");
        this.mViewHolder.localManutencao.setText("");
        this.mViewHolder.kmFeitoManutencao.setText("");
        this.mViewHolder.preco.setText("");
        this.mViewHolder.kmProximaTroca.setText("");
    }

    private static class ViewHolder {

        TextView nomeManutencao, nomeMecanico, localManutencao, kmFeitoManutencao, kmProximaTroca, preco, txtData;
        Button calendario1, deletarManutencao;

    }

}
