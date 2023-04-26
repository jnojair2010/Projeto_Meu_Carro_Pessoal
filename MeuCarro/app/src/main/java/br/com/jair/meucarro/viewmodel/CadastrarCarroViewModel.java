package br.com.jair.meucarro.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import br.com.jair.meucarro.model.Carro;

public class CadastrarCarroViewModel extends AndroidViewModel {

    //private MeuCarroRepositorio meuCarroRepositorio;


    public CadastrarCarroViewModel(@NonNull Application application) {
        super(application);
       // this.meuCarroRepositorio = MeuCarroRepositorio.getInstance(application.getApplicationContext());
    }

    public void salvarCarro(Carro carro){
      //  this.meuCarroRepositorio.insert(carro);
    }

}
