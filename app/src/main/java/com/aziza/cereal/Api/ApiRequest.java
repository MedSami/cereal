package com.aziza.cereal.Api;

import com.aziza.cereal.Inscrire;
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
    Call<ResponseDataModel> Login(@Query("cin") String cin,
                                  @Query("acteur") int acteur);
    /******************** Modifier Mot De Passe *******************/
    @FormUrlEncoded
    @POST("ModifierPss.php")
    Call<ResponseDataModel> ModifierPss(@Field("id") String id_utilisateur,
                                        @Field("ancien") String AncienPss,
                                        @Field("nouveau") String NouveauPss);


    /******************** Transactions *******************/
    @GET("SelectTransaction.php")
    Call<ResponseDataModel> Transactions(@Query("idAgriculteur") String id_agriculteur);


    /******************** Transactions *******************/
    @GET("SelectTypeCereal.php")
    Call<ResponseDataModel> TypeCereal();

    /*************** Inscription Agriculteur *******************/
    @FormUrlEncoded
    @POST("Inscrire.php")
    Call<ResponseDataModel>Inscrire(
            @Field("nom") String nom,
            @Field("prenom") String prenom,
            @Field("motdepasse") String motdepasse,
            @Field("numTel") String numTel,
            @Field("adresse") String adresse,
            @Field("email") String email,
            @Field("cin") String cin,
            @Field("acteur") String acteur
            );

    /*************** Demande Rendez Vous *******************/
    @FormUrlEncoded
    @POST("DemandeRendezVous.php")
    Call<ResponseDataModel> RendezVous(
            @Field("id_agriculteur") String id_agriculteur);

    /*************** Envoyer message *******************/
    @FormUrlEncoded
    @POST("messageTransformateur.php")
    Call<ResponseDataModel> EnvoyerMsg(
            @Field("id_transformateur") String id_transformateur,
            @Field("msg") String msg
    );

    /*************** Envoyer message *******************/
    @FormUrlEncoded
    @POST("PasserCommande.php")
    Call<ResponseDataModel> Commande(
            @Field("id_transformateur") String id_transformateur,
            @Field("id_type_cereal") int id_type_cereal,
            @Field("quantite") String quantite
    );

    /*************** Demande Facturation *******************/
    @FormUrlEncoded
    @POST("demandeFacturation.php")
    Call<ResponseDataModel> Facturation(
            @Field("id_transformateur") String id_transformateur
    );


    /*************** Inscription Agriculteur *******************/
    @FormUrlEncoded
    @POST("EnregistrerCereal.php")
    Call<ResponseDataModel> EnregistrerCereal(
            @Field("id_type_cereal") int id_type_cereal,
            @Field("quantite") String quantite,
            @Field("matricule") String matriculeBateau
    );
}
