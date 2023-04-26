package br.com.jair.meucarro.viewholder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import br.com.jair.meucarro.interfa.ClicksMeusCarro;
import br.com.jair.meucarro.R;
import br.com.jair.meucarro.model.Carro;

public class ViewHolderMeuCarro extends RecyclerView.ViewHolder{

    TextView nomeCarro;
    ImageView imagemView;
    Button deletarCarro, listarManutencao, foto;

    public ViewHolderMeuCarro(@NonNull View itemView){
        super(itemView);
        this.imagemView = (ImageView) itemView.findViewById(R.id.foto_carro);
        this.nomeCarro = (TextView) itemView.findViewById(br.com.jair.meucarro.R.id.nome_meu_carro_fragments);
        this.deletarCarro =(Button)  itemView.findViewById(R.id.btn_deletar_carro);
        this.listarManutencao = (Button) itemView.findViewById(R.id.detalhe_manutencao);
        this.foto = (Button) itemView.findViewById(R.id.photoCarro);

    }

    public void binding(Carro carro, ClicksMeusCarro clickListener){
            this.nomeCarro.setText(carro.getNomeCarro());

            if(carro.getImagem()!=null){
                ByteArrayInputStream imagemStream = new ByteArrayInputStream(carro.getImagem());
                   Bitmap img = BitmapFactory.decodeStream(imagemStream);
                  imagemView.setImageBitmap(img);
                  this.nomeCarro.setTextColor(Color.parseColor("#d50000"));
                  this.imagemView.setBackgroundColor(Color.parseColor("#37474f"));

            }else{

            }

            this.deletarCarro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        clickListener.OnclickDelet(carro.getId());
                    }catch (Exception e) {
                        Log.i("deletado"," ////// erro view Holder, seu carro não foi deletado");
                    }
                }
            });
            this.listarManutencao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        clickListener.OnclickverManutencao(carro.getId());
                    }catch (Exception e){

                    }
                }
            });
            this.foto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.OnclicktirarFoto(carro.getId());
                }
            });


    }


}
