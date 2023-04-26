package br.com.jair.meucarro.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.com.jair.meucarro.R;
import br.com.jair.meucarro.manager.Manager;
import br.com.jair.meucarro.model.Pecas;

public class DetalhePecasFragments extends Fragment {

    private View view;
    private Button deletePeca;
    private Manager mManager;
    private TextView nomePeca, marcaPeca,localComprouPeca, dataCompraPeca, referenciaPeca, precoPeca, kmVidaUtilPeca, cupomPeca, quantidade;
    private Pecas peca = new Pecas();


    public DetalhePecasFragments(){

    }
    public DetalhePecasFragments(Pecas mPecas){
        this.peca = mPecas;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.detalhe_pecas_fragments, container, false);

        Log.i(" manutencao  ", "////////////////////////// o id "+peca.getId()+" Nome "+peca.getNome());

        mManager = new Manager(getContext());

        this.deletePeca = (Button) view.findViewById(R.id.btnDeletarPeca);

        this.nomePeca = (TextView) view.findViewById(R.id.txtNomePeca);
        this.marcaPeca = (TextView) view.findViewById(R.id.txtMarcaPeca);
        this.localComprouPeca = (TextView) view.findViewById(R.id.txtLocalComprouPeca);
        this.dataCompraPeca = (TextView) view.findViewById(R.id.txtDataCompraPeca);
        this.referenciaPeca = (TextView) view.findViewById(R.id.txtReferenciaPeca);
        this.precoPeca = (TextView) view.findViewById(R.id.txtPrecoPeca);
        this.kmVidaUtilPeca = (TextView) view.findViewById(R.id.txtKmVidaUtilPeca);
        this.cupomPeca = (TextView) view.findViewById(R.id.txtCupomPeca);
        this.quantidade = (TextView) view.findViewById(R.id.txtQuantidade);

        atribuirValores();
        deletarPeca(peca.getId());

        return view;

    }

    private void atribuirValores(){

        this.nomePeca.setText(this.peca.getNome());
        this.marcaPeca.setText(this.peca.getMarca());
        this.localComprouPeca.setText(this.peca.getLocal());
        this.dataCompraPeca.setText(this.peca.getData());
        this.referenciaPeca.setText(this.peca.getReferencia());
        this.precoPeca.setText(this.peca.getPreco());
        this.kmVidaUtilPeca.setText(this.peca.getKmVidaUtil());
        this.cupomPeca.setText(this.peca.getCupom());
        this.quantidade.setText(this.peca.getQuantidade());
    }
    private void deletarPeca(int id){
        this.deletePeca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = mManager.deletarPeca(id);
                if(b==true){
                    nomePeca.setText("");
                    marcaPeca.setText("");
                    localComprouPeca.setText("");
                    dataCompraPeca.setText("");
                    referenciaPeca.setText("");
                    precoPeca.setText("");
                    kmVidaUtilPeca.setText("");
                    cupomPeca.setText("");
                    quantidade.setText("");

                }else{

                }

            }
        });
    }  // fim do metodo deletarPeca

}
