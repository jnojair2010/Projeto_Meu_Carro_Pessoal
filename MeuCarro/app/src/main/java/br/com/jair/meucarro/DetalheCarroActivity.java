package br.com.jair.meucarro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.jair.meucarro.manager.Manager;
import br.com.jair.meucarro.model.Carro;

public class DetalheCarroActivity extends AppCompatActivity {

    private Carro carro = new Carro();
    private Manager manager;
    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_carro);
        ActionBar bar = getSupportActionBar();

        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2d2d2d")));
        bar.setTitle("Detalhe Carro");
        bar.setDisplayHomeAsUpEnabled(true);
            Intent intent = getIntent();
            int id = Integer.parseInt(intent.getSerializableExtra("id_carro").toString());
            this.carro.setId(id);
        manager= new Manager(getApplication());

        mViewHolder.nomeCarro = (TextView) findViewById(R.id.edtNomeCarro);
        mViewHolder.marca = (TextView) findViewById(R.id.edtFabricanteCarro);
        mViewHolder.cor = (TextView) findViewById(R.id.edtCorCarro);
        mViewHolder.ano = (TextView) findViewById(R.id.edtAnoCarro);
        mViewHolder.placa = (TextView) findViewById(R.id.edtPlacaCarro);
        mViewHolder.btnListaManutencao = (Button) findViewById(R.id.btnListaManutencao);

        gtCarro();
        atribuirValoresCarroView();
        verListaManutencao();


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

    private void atribuirValoresCarroView(){
        this.mViewHolder.nomeCarro.setText(carro.getNomeCarro()+" "+carro.getModelo());
        this.mViewHolder.marca.setText(carro.getFabricante());
        this.mViewHolder.cor.setText(carro.getCor());
        this.mViewHolder.ano.setText(carro.getAno());
        this.mViewHolder.placa.setText(carro.getPlaca());

    }

    public void verListaManutencao(){
        this.mViewHolder.btnListaManutencao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("ObjetoCarro",carro);
                Intent intent = new Intent(getApplication(), ListaManutencaoPorCarro.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    private void gtCarro(){
        for(int i =0; i<this.manager.getmArrayList().size();i++){
            if(this.manager.getmArrayList().get(i).getId()==carro.getId()){
            carro= this.manager.getmArrayList().get(i);
            }
        }

    }

    private void getToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public static class ViewHolder{
        TextView nomeCarro, marca, cor, ano, placa;
        Button btnListaManutencao;

    }


}