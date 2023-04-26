package br.com.jair.meucarro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.jair.meucarro.adapter.AdapterRecyclerViewListaManutencaoPorCarro;
import br.com.jair.meucarro.manager.Manager;
import br.com.jair.meucarro.model.Carro;
import br.com.jair.meucarro.model.Manutencao;

public class ListaManutencaoPorCarro extends AppCompatActivity {


    private ActionBar bar;
    private Manager manager;
    private Carro carro = new Carro();

    private ViewHolder mViewHolder = new ViewHolder();
    private static ArrayList<Manutencao> listaManutencao = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_manutencao_por_carro);

        carro = (Carro) getIntent().getSerializableExtra("ObjetoCarro");

        this.bar = getSupportActionBar();
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2d2d2d")));
        this.bar.setTitle("Manutenções do "+carro.getNomeCarro());
        bar.setDisplayHomeAsUpEnabled(true);

        manager = new Manager(getApplication());
        this.mViewHolder.reciclerViewListaManutencaoPorCarro = (RecyclerView) findViewById(R.id.reciclerViewListaManutencaoPorCarro);

        getListaManutencao();
        monstarLista();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void getListaManutencao(){
        listaManutencao = manager.getManutencaoPorIdCarro(carro.getId());

    }

    private void monstarLista(){
        this.mViewHolder.reciclerViewListaManutencaoPorCarro.setLayoutManager(new LinearLayoutManager(getApplication()));
        AdapterRecyclerViewListaManutencaoPorCarro adapter = new AdapterRecyclerViewListaManutencaoPorCarro(this.listaManutencao);
        this.mViewHolder.reciclerViewListaManutencaoPorCarro.setAdapter(adapter);
    }


    private void getToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public static class ViewHolder{
        RecyclerView reciclerViewListaManutencaoPorCarro;
    }

}