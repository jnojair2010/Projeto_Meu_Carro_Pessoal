package br.com.jair.meucarro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import br.com.jair.meucarro.fragments.AtualizarManutencao;
import br.com.jair.meucarro.fragments.DetalheManutencaoFragments;
import br.com.jair.meucarro.manager.Manager;
import br.com.jair.meucarro.model.Manutencao;

public class SelecionarPecasAmanutencao extends AppCompatActivity {

    private Manutencao manutencao = new Manutencao();
    private AtualizarManutencao mAtualizarManutencao;
    private ActionBar bar;
    private DetalheManutencaoFragments mDetalheManutencaoFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_pecas_amanutencao);
        this.bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2d2d2d")));
        bar.setDisplayHomeAsUpEnabled(true);

        mAtualizarManutencao = new AtualizarManutencao();
        mDetalheManutencaoFragments = new DetalheManutencaoFragments();

        Intent intent = getIntent();

        this.manutencao =  (Manutencao) intent.getSerializableExtra("Manutencao");
        boolean b = intent.getExtras().getBoolean("statusManutencao");
        Bundle extras = getIntent().getExtras();

        if(intent.getExtras().getBoolean("statusManutencao")==true) {
            fragmentos();
        }

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


    public void fragmentos(){
        this.bar.setTitle("Selecione pecas a manutenção");
        Bundle bundler = new Bundle();
        bundler.putSerializable("Manutencao", this.manutencao);

        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        mDetalheManutencaoFragments.setArguments(bundler);
        fragmentTransaction1.replace(R.id.rcViewPecasEmManutencao, mDetalheManutencaoFragments);
        fragmentTransaction1.commit();
    }



}