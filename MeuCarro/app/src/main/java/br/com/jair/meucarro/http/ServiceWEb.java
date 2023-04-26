package br.com.jair.meucarro.http;

import java.util.List;

import br.com.jair.meucarro.http.model.CarrosHttp;
import br.com.jair.meucarro.http.model.ManutencaoHttp;
import br.com.jair.meucarro.http.model.ManutencaoHttpParaDeletar;
import br.com.jair.meucarro.http.model.PecasHttp;
import br.com.jair.meucarro.http.model.SalvarUsuario;
import br.com.jair.meucarro.http.model.UsuarioDaoHttp;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceWEb {

   @FormUrlEncoded
   @POST("/mycar/salvarproprietario.php")
   Call<SalvarUsuario> uploadUsuario(
           @Field("nome") String nome,
           @Field("email") String email,
           @Field("objsManutencao") String listaObjetosManutencao,
           @Field("objsCarros") String listaObjetosCarros,
           @Field("objsPecas") String listaObjetosPecas
   );

   @GET("/mycar/downmeucarro.php")
   Call<List<UsuarioDaoHttp>> getUsuario(
           @Query("email") String email,
           @Query("solicitacao") String solicitacao
   );

   @GET("/mycar/downmeucarro.php")
   Call<List<ManutencaoHttp>> getListaManutencao(
           @Query("idUsuario") String id,
           @Query("solicitacao") String solicitacao
   );
   @GET("/mycar/downmeucarro.php")
   Call<List<PecasHttp>> getListaPecas(
           @Query("idUsuario") String id,
           @Query("solicitacao") String solicitacao
   );

   @GET("/mycar/downmeucarro.php")
   Call<List<CarrosHttp>> getListaCarros(
           @Query("idUsuario") String id,
           @Query("solicitacao") String solicitacao
   );


}

/*@SerializedName("id")
private int id;


@SerializedName("data")
private String data;


@SerializedName("idCarro")
private int idCarro;


@SerializedName("kmFeitoManutencao")
private int kmFeitoManutencao;


@SerializedName("kmValidade")
private int kmValidade;


@SerializedName("nome")
private String nome;


@SerializedName("local")
private String local;


@SerializedName("nomeMecanico")
private String nomeMecanico;


@SerializedName("valor")
private String valor;


@SerializedName("dataCompraPeca")
private String dataCompraPreca;


@SerializedName("tipoMAnutencao")
private String tipoMAnutencao;


@SerializedName("cupom_nota")
private String cupom_nota; */
