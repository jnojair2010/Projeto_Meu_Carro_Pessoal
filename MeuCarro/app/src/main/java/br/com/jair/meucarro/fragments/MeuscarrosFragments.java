package br.com.jair.meucarro.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.jair.meucarro.ActivityPhotoCarro;
import br.com.jair.meucarro.DetalheCarroActivity;
import br.com.jair.meucarro.R;
import br.com.jair.meucarro.adapter.AdapterRecicleViewMeuCarro;
import br.com.jair.meucarro.interfa.ClicksMeusCarro;
import br.com.jair.meucarro.manager.Manager;
import br.com.jair.meucarro.manager.Permissoes;
import br.com.jair.meucarro.model.Carro;

public class MeuscarrosFragments extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    Manager managerCarro;
    private View view;
    int idCarroFoto =0;
    ClicksMeusCarro clickListener;

    private Carro carro = new Carro();

    private static ArrayList<Carro> mArrayListFragments = new ArrayList<>();

    private static String[] permissoesNecessarias = new String[]{
            android.Manifest.permission.CAMERA,

    };

        public MeuscarrosFragments(){

        }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.meus_carros_fragments,container,false);

        Permissoes.valodarPermissoes(1,getActivity(),permissoesNecessarias);

            managerCarro = new Manager(getContext());
        mArrayListFragments = managerCarro.consultarMeusCarro();

        this.clickListener = new ClicksMeusCarro(){
            @Override
            public void OnclickDelet(int id) {
                deletarCarro(id);
            }

            @Override
            public void OnclicktirarFoto(int id) {
                idCarroFoto = id;
               // getActivitPhotos(id);

                dispatchTakePictureIntent(id);
            }

            @Override
            public void OnclickverManutencao(int id) {
                getFragmentsManutencao(id);
            }
        };
            if(mArrayListFragments.size()>0){
                this.updateRecicleViewCarro();

            }else{
                if(managerCarro.getListaCarro().size()>0){
                    this.updateRecicleViewCarro();
                   // this.Toast("estamos Carregando seus Carros ");
                }else{
                    this.Toast("parece que vc ainda não tem carro para carregar");
                }
            }
        return view;
    }

    private void dispatchTakePictureIntent(int id) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        this.carro.setId(id);
        abreviatActivity.launch(intent);

    }

    // metodo que foi preciso usar, exigência da abreviatActivity.launch(intent); na linha 95
    ActivityResultLauncher<Intent> abreviatActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode()== Activity.RESULT_OK){

                    try{

                        Bundle bundle = result.getData().getExtras();
                        Bitmap img= (Bitmap) bundle.get("data");

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        img.compress(Bitmap.CompressFormat.JPEG,100,stream);
                        byte imagemEmByte[] = stream.toByteArray();

                        for(int i =0; i<mArrayListFragments.size();i++){

                           /* if(mArrayListFragments.get(i).getId()==idCarroFoto){
                                carro = mArrayListFragments.get(i);

                             //   this.updateRecicleViewCarro();
                            }*/

                        }
                        this.carro.setImagem(imagemEmByte);
                        salvarFotoBd(this.carro);

                    }catch (Exception e){
                        Log.i("Exempition", "/////////////////////// a imagem é: "+e.getMessage());
                    }

                }
            });

// metodo que peguei da internet para transforma imagem bipmao em byte

    public byte[] convertImageViewToByteArray(Bitmap image){

            Bitmap bitmap = image;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            return stream.toByteArray();

    }

    private void deletarCarro(int id){
            this.managerCarro.deletarCarro(id);
            this.updateRecicleViewCarro();

    }
    private void updateRecicleViewCarro(){

        mArrayListFragments = managerCarro.getListaCarro();
        RecyclerView recyclerView = (RecyclerView) this.view.findViewById(R.id.recicley_view_meus_carros_fragements);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterRecicleViewMeuCarro adaptadorMeusCarros = new AdapterRecicleViewMeuCarro(mArrayListFragments,clickListener);
        recyclerView.setAdapter(adaptadorMeusCarros);
    }

    private void salvarFotoBd(Carro car){
       boolean b =  this.managerCarro.salvarFoto(car);
       if(b==true){
        this.Toast("Foto Salvo com Sucesso");
       }else{
           this.Toast("Foto não salvo");
       }
    }

    private void getActivitPhotos(int id){
            Carro carro = new Carro();
            for(int i =0; i<mArrayListFragments.size();i++){
                if(mArrayListFragments.get(i).getId()==id){
                    carro = mArrayListFragments.get(i);
                }
            }

        Bundle bundle = new Bundle();
        bundle.putSerializable("ObjetoCarro",carro);
        Intent intent = new Intent(getContext(), ActivityPhotoCarro.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void getFragmentsManutencao(int id){
        Intent intent = new Intent(getContext(), DetalheCarroActivity.class);
        intent.putExtra("id_carro",id);
        startActivity(intent);
    }

    private void Toast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
       // Log.i("onStart", "/////////////////////// entrou no onStart ");
       // updateRecicleViewCarro();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume", "/////////////////////// entrou no onResume ");
        updateRecicleViewCarro();
    } // fim do metodo onResume


}
