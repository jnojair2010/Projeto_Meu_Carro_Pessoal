package br.com.jair.meucarro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import br.com.jair.meucarro.fragments.CadastarCarroFragments;
import br.com.jair.meucarro.fragments.CadastraManutencaoFragments;
import br.com.jair.meucarro.fragments.CadastrarPecasFragments;

public class Cadastros extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    CadastraManutencaoFragments mCadastraManutencaoFragments;
    CadastrarPecasFragments mCadastrarItensManutencaoFragments;

    CadastarCarroFragments mCadastarCarroFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastros);


        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2d2d2d")));
        bar.setTitle("Cadastrar Carro");
        bar.setDisplayHomeAsUpEnabled(true);

        mCadastraManutencaoFragments = new CadastraManutencaoFragments();
        mCadastrarItensManutencaoFragments = new CadastrarPecasFragments();
        mCadastarCarroFragments = new CadastarCarroFragments();

        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction2.replace(R.id.fragments_cadastro_manutencao,mCadastarCarroFragments);
        fragmentTransaction2.commit();

        bottomNavigationView = findViewById(R.id.button_navigation_fragments_cadastros);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.cadastrar_itens_manutenca:
                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.fragments_cadastro_manutencao,mCadastrarItensManutencaoFragments);
                        fragmentTransaction1.commit();
                        bar.setTitle("Cadastar Pecas");
                        Log.i(" item selecionado","////////////////////////////"+item.getTitle().toString());
                        return true;
                    case R.id.cadastar_manutencao:
                        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.fragments_cadastro_manutencao,mCadastraManutencaoFragments);
                        fragmentTransaction3.commit();
                        bar.setTitle("Cadastrar Manutenção");
                        Log.i(" item selecionado","////////////////////////////"+item.getTitle().toString());
                        return true;
                    case R.id.cadastar_carro:
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.fragments_cadastro_manutencao,mCadastarCarroFragments);
                        fragmentTransaction2.commit();
                        bar.setTitle("Cadastrar Carro");
                        Log.i(" Cadastar Carro","////////////////////////////"+item.getTitle().toString());
                        return true;

                }
                return false;
            }
        });



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
}