package br.com.jair.meucarro;

import static android.media.MediaRecorder.VideoSource.CAMERA;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.jair.meucarro.manager.Permissoes;
import br.com.jair.meucarro.model.Carro;

public class ActivityPhotoCarro extends AppCompatActivity {
    private ActionBar bar;
    private Carro carro = new Carro();
    private Button btnTirarFoto;
    private ImageView imageView;

    static final int REQUEST_TAKE_PHOTO = 1;

    private File photoFile = null;
    String currentPhotoPath;

    private static String[] permissoesNecessarias = new String[]{
            android.Manifest.permission.CAMERA,

    };


    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_carro);

        carro = (Carro) getIntent().getSerializableExtra("ObjetoCarro");

        Permissoes.valodarPermissoes(1,this,permissoesNecessarias);

        this.bar = getSupportActionBar();
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2d2d2d")));
        this.bar.setTitle("Foto Carro");
        bar.setDisplayHomeAsUpEnabled(true);

        btnTirarFoto = (Button) findViewById(R.id.btnTirarFoto);
        imageView = (ImageView) findViewById(R.id.imgFoto);
        tirarFoto();
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

    private void tirarFoto(){
        this.btnTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Certifique-se de que haja uma atividade de câmera para lidar com a intenção
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //Crie o arquivo onde a foto deve ir
            try {
                this.photoFile = createImageFile();
            } catch (IOException ex) {
                Log.i("dispatchTakePictureIntent", "/////////////////////////////// erro no try da variavell que chama metodo criar arquivos"+ex.getMessage()+" ************************");
            }
            // Continue apenas se o arquivo foi criado com sucesso

            try{
                if (this.photoFile != null) {

                    Uri photoURI = FileProvider.getUriForFile(getBaseContext(),
                            getBaseContext().getApplicationContext().getPackageName()+".provider",
                            // "br.com.jair.meucarro.provider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    abreviatActivity.launch(takePictureIntent);
                    //startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }

            }catch (IllegalArgumentException ex){
                Log.i("ERROR", "/////////////////////////////// erro  no IllegalArgumentException é: "+ex.getMessage()+" ************************");
            }

        }
    }
    ActivityResultLauncher<Intent> abreviatActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode()== Activity.RESULT_OK){
                    try{
                        Bundle bundle = result.getData().getExtras();
                        Bitmap img = (Bitmap) bundle.get("data");

                        sendBroadcast(new Intent(
                                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                                Uri.fromFile(this.photoFile)
                        ));                        //criar o nome do arquivo
                        createImageFile();

                        imageView.setImageBitmap(img);

                    }catch (Exception e){
                        Log.i("Exempition", "/////////////////////// a imagem é: "+e.getMessage());
                    }

                }
            });


    private File createImageFile() throws IOException {              // metodo para criar o nome da foto
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = carro.getNomeCarro()+"_";;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */

        );

        // Save a file: path for use with ACTION_VIEW intents

        currentPhotoPath = image.getAbsolutePath();

        Log.i("Exempition", "///////////////////////  entrou na formacao de arquivos "+ imageFileName);
        return image;
    }               //------------------------------------------------fim do metodo para criar nome da foto




    private void getToast(String msg){
        Toast.makeText(getApplication(), msg, Toast.LENGTH_LONG).show();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResult){
        super.onRequestPermissionsResult(requestCode,permissions,grantResult);

        for(int resultado : grantResult){
            if(resultado == PackageManager.PERMISSION_DENIED){
                dialogPermissionTratar(String.valueOf(resultado));
            }
        }
    }

    private void  dialogPermissionTratar(String permicao){
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("Permissoes Negada");
        build.setMessage("Para tirar a foto e gravar no seu dispositivo é preciso autorizar as permissões "+permicao);
        build.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {;
            }
        });
        AlertDialog dialog = build.create();
        dialog.show();

    }


}