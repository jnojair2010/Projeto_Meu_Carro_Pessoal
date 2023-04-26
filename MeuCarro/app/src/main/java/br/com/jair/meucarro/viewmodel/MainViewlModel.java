package br.com.jair.meucarro.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import br.com.jair.meucarro.manager.Repositorio;

public class MainViewlModel extends AndroidViewModel {

    private Repositorio meuCarroRepositorio;

    public MainViewlModel(@NonNull Application application) {
        super(application);
        this.meuCarroRepositorio = Repositorio.getInstance(application.getApplicationContext());
    }

}
