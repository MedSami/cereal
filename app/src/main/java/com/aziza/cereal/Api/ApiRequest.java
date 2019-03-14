package com.aziza.cereal.Api;

import com.aziza.cereal.Model.ResponseDataModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequest {

    /******************** Authentification Login*******************/
    @GET("Login.php")
    Call<ResponseDataModel> Login(@Query("identifiant") String identifiant,
                                  @Query("type") int type);


    /*************** Inscription Agriculteur *******************/
    @FormUrlEncoded
    @POST("Inscrire.php")
    Call<ResponseDataModel> Inscrire(
            @Field("nom") String nom,
            @Field("prenom") String prenom,
            @Field("motdepasse") String motdepasse,
            @Field("identifiant") String identifiant
    );

    /*************** Demande Rendez Vous *******************/
    @FormUrlEncoded
    @POST("DemandeRendezVous.php")
    Call<ResponseDataModel> RendezVous(
            @Field("id_agriculteur") String id_agriculteur
    );


}
