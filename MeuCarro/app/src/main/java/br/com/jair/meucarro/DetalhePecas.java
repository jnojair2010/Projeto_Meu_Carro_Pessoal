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
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import br.com.jair.meucarro.fragments.AtualizarPecasFragments;
import br.com.jair.meucarro.fragments.DetalhePecasFragments;
import br.com.jair.meucarro.manager.Manager;
import br.com.jair.meucarro.model.Manutencao;
import br.com.jair.meucarro.model.Pecas;

public class DetalhePecas extends AppCompatActivity {

    private Pecas peca = new Pecas();
    private Manager mManager;

    private Switch aSwitch;
    DetalhePecasFragments fragmentsDetalhePecas;
    AtualizarPecasFragments fragmentsAtualizarPecas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pecas);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2d2d2d")));
        bar.setTitle("Detalhe Peca");
        bar.setDisplayHomeAsUpEnabled(true);

        mManager = new Manager(this);

        aSwitch = (Switch) findViewById(R.id.onOf);

        peca  = (Pecas) getIntent().getSerializableExtra("ObjetoPeca");
        Log.i(" manutencao  ", "////////////////////////// o id "+peca.getId()+" Nome "+peca.getNome());

        fragmentsDetalhePecas = new DetalhePecasFragments(peca);
        fragmentsAtualizarPecas = new AtualizarPecasFragments(peca);

        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.detalhePecas,fragmentsDetalhePecas);
        fragmentTransaction1.commit();

        trocarFragmentes();

    } // fim do metodo oncreate

    private void trocarFragmentes(){
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if(checked!=false){
                        aSwitch.setText("Mostrar detalhes da peça");
                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.detalhePecas,fragmentsAtualizarPecas);
                        fragmentTransaction1.commit();
                    }else{
                        aSwitch.setText("Atualizar peça ");
                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.detalhePecas,fragmentsDetalhePecas);
                        fragmentTransaction1.commit();
                    }
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
    } // fim do metdodo onOptionItemSelected


    private void getToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}