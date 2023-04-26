package br.com.jair.meucarro.viewholder;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;

import br.com.jair.meucarro.interfa.ClickPecas;
import br.com.jair.meucarro.R;
import br.com.jair.meucarro.model.Pecas;

public class ViewHolderListaPecasIndicador extends RecyclerView.ViewHolder {

    TextView nomePecas, data, corIndicador, kmFaltante, kmUsado, kmValidade, porCento;
    Button btnDetalhes;

    public ViewHolderListaPecasIndicador(@NonNull View itemView) {
        super(itemView);
        this.data = (TextView) itemView.findViewById(R.id.txtDataPeca);
        this.nomePecas = (TextView) itemView.findViewById(R.id.txtNomeDaPeca);
        this.corIndicador = (TextView) itemView.findViewById(R.id.textViewIndicarCor);
        this.kmFaltante =(TextView) itemView.findViewById(R.id.km_faltantes);
        this.kmUsado = (TextView) itemView.findViewById(R.id.km_usado);
        this.kmValidade=(TextView) itemView.findViewById(R.id.km_validade);
        this.porCento = (TextView) itemView.findViewById(R.id.percent_faltante);
        this.btnDetalhes = (Button) itemView.findViewById(R.id.btnDetalhe);

    }

    @SuppressLint("ResourceAsColor")
    public void biding(Pecas peca, ClickPecas listener){
        this.nomePecas.setText(peca.getNome());
        NumberFormat inter = NumberFormat.getInstance();

        this.data.setText(peca.getData());

        String UsadoKm = inter.format(peca.getKmUsado());
        String faltamkm = inter.format(peca.getKmFaltente());
        String Validade = inter.format(peca.getNovaValidade());
        String porcento = String.valueOf(peca.getPercenteUsado());

        this.kmUsado.setText(UsadoKm+" km");
        this.kmFaltante.setText(faltamkm+" km");
        this.kmValidade.setText(Validade+" km");
        this.porCento.setText(porcento+"% \n uso  ");

        this.btnDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickListenerPecas(peca);
            }
        });

       if(peca.getPercenteUsado()==null){
           this.porCento.setText(porcento+"% \n   uso  ");
           this.porCento.setBackgroundResource(R.drawable.indicador_percent);
      }else{
           if(peca.getPercenteUsado()<=97.79){
               this.porCento.setText(" "+porcento+"% \n   uso  ");
               this.porCento.setBackgroundResource(R.drawable.indicador_percent_verde);

           }
           if(peca.getPercenteUsado()>=97.80 &&peca.getPercenteUsado()<=98.97){
               this.porCento.setText(" "+porcento+"% \n   uso  ");
               this.porCento.setBackgroundResource(R.drawable.indicador_percent_amarelo);
               this.porCento.setTextColor(Color.parseColor("#2F4F4F"));

           }
           if(peca.getPercenteUsado()>=98.98){
               this.porCento.setText(porcento+"% \n   uso  ");
               this.porCento.setBackgroundResource(R.drawable.indicador_percent_vermelho);
           }

       } // fim do else das porcentagens


    }
}
