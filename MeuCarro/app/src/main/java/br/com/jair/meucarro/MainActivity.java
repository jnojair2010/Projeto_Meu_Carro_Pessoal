 package br.com.jair.meucarro;

 import android.app.AlertDialog;
 import android.content.Context;
 import android.content.Intent;
 import android.graphics.Color;
 import android.graphics.drawable.ColorDrawable;
 import android.os.Bundle;
 import android.view.Menu;
 import android.view.MenuInflater;
 import android.view.MenuItem;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.ActionBar;
 import androidx.appcompat.app.AppCompatActivity;
 //import androidx.databinding.tool.Context;
 import androidx.fragment.app.FragmentTransaction;
 import androidx.lifecycle.ViewModelProvider;

 import com.google.android.material.bottomnavigation.BottomNavigationView;
 import com.google.android.material.navigation.NavigationBarView;

 import br.com.jair.meucarro.fragments.ManutencaoFragments;
 import br.com.jair.meucarro.fragments.MeuscarrosFragments;
 import br.com.jair.meucarro.fragments.PecaFragments;
 import br.com.jair.meucarro.manager.Manager;
 import br.com.jair.meucarro.manager.Permissoes;
 import br.com.jair.meucarro.model.Carro;
 import br.com.jair.meucarro.viewmodel.MainViewlModel;

 public class MainActivity extends AppCompatActivity {

     private ActionBar bar;
    BottomNavigationView bottomNavigationView;
    private MeuscarrosFragments mMeuscarrosFragments;
    private PecaFragments mItensDeManutencaoFragments;
    private ManutencaoFragments mManutencaoFragments;
    private Manager manager;
    private MainViewlModel mainViewlModel;
    private AlertDialog.Builder dialog;
    private Context context;

     private static String[] permissoesNecessarias = new String[]{
             android.Manifest.permission.CAMERA
     };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context= getApplicationContext();

        Permissoes.valodarPermissoes(1,this,permissoesNecessarias);

         this.bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2d2d2d")));
        bar.setTitle("Indicadores");

        manager= new Manager(getApplication());
        manager.getListaCarro();

        dialog = new AlertDialog.Builder(this);

        mainViewlModel = new ViewModelProvider(this).get(MainViewlModel.class);
        mMeuscarrosFragments = new MeuscarrosFragments();
        mItensDeManutencaoFragments = new PecaFragments();
        mManutencaoFragments = new ManutencaoFragments();

        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.flame_layout,mItensDeManutencaoFragments);
        fragmentTransaction1.commit();


                    bottomNavigationView = findViewById(R.id.button_navigation);
                    bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.itens_de_manutencao:
                                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction1.replace(R.id.flame_layout,mItensDeManutencaoFragments);
                                    fragmentTransaction1.commit();
                                    bar.setTitle("Gerencia Manutencao");
                                  //  Log.i(" item selecionado","////////////////////////////"+item.getTitle().toString());
                                    return true;
                                case R.id.manutencao:
                                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction3.replace(R.id.flame_layout,mManutencaoFragments);
                                    fragmentTransaction3.commit();
                                    bar.setTitle("Manutenção Geral");
                                  //  Log.i(" item selecionado","////////////////////////////"+item.getTitle().toString());
                                    return true;
                                case R.id.meus_carros:
                                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction2.replace(R.id.flame_layout,mMeuscarrosFragments);
                                    fragmentTransaction2.commit();
                                    bar.setTitle("Lista de Carro");
                                   // Log.i(" item selecionado","////////////////////////////"+item.getTitle().toString());
                                    return true;

                            }
                            return false;
                        }
                    });

    }
    public void onResume() {
        super.onResume();

    }

    public void onStart() {
        super.onStart();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);

         switch(item.getItemId()){
             case R.id.cadastros:
                 Intent intent1 = new Intent(this, Cadastros.class);
                 startActivity(intent1);

                 return true;
             case R.id.ajuda:
                 Intent intent = new Intent(this, Tour.class);
                 startActivity(intent);
                 return true;
             case R.id.backup:
                 Intent intentBackup = new Intent(this,ActivityBackup.class);
                 startActivity(intentBackup);
                 return true;
             case R.id.sobre:
                 Intent intentSobre = new Intent(this,Sobre.class);
                 startActivity(intentSobre);
                 return true;


         }

        return true;
    }




}