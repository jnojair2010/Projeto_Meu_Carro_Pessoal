package br.com.jair.meucarro.manager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissoes {

    Context context;
    public static boolean valodarPermissoes(int requestCode,Activity activity, String[] permissoes){


        if(Build.VERSION.SDK_INT >= 23){

            List<String> listaPermission = new ArrayList<String>();



           for(String permissao : permissoes){
                 Boolean validaPermissao  =  ContextCompat.checkSelfPermission(activity,permissao) == PackageManager.PERMISSION_GRANTED;
                 if(!validaPermissao) listaPermission.add(permissao);
            }

            if(listaPermission.isEmpty()) return true;

            String[] novasPermissoes = new String[listaPermission.size()];

            listaPermission.toArray(novasPermissoes);


            ActivityCompat.requestPermissions(activity,novasPermissoes,requestCode);


        }
        return true;
    }
    public static boolean permissaoArquivos(int requestCode,Activity activity, String[] permissoes){


        if(Build.VERSION.SDK_INT >= 23){

            List<String> listaPermission = new ArrayList<String>();



            for(String permissao : permissoes){
                Boolean validaPermissao  =  ContextCompat.checkSelfPermission(activity,permissao) == PackageManager.PERMISSION_GRANTED;
                if(!validaPermissao) listaPermission.add(permissao);
            }

            if(listaPermission.isEmpty()) return true;

            String[] novasPermissoes = new String[listaPermission.size()];

            listaPermission.toArray(novasPermissoes);


            ActivityCompat.requestPermissions(activity,novasPermissoes,requestCode);


        }
        return true;
    }


}
