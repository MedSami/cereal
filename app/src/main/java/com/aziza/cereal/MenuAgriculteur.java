package com.aziza.cereal;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aziza.cereal.Api.ApiRequest;
import com.aziza.cereal.Api.RetrofitServer;
import com.aziza.cereal.Model.ResponseDataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuAgriculteur extends AppCompatActivity {
    Button btnMotifierPss, btnRendezVous, btnTransaction,btnReponse;
    String id_agriculteur,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_agriculteur);

        btnMotifierPss = findViewById(R.id.btnModifierPss);
        btnRendezVous = findViewById(R.id.btnRendezVous);
        btnTransaction = findViewById(R.id.btnTransaction);
        btnReponse = findViewById(R.id.btnReponse);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            id_agriculteur = data.getString("id_agriculteur");
            password = data.getString("Password");
        }

        btnMotifierPss.setOnClickListener(new View.OnClickListener() {
            @Override
                        public void onClick(View view) {
                Intent i=new Intent(MenuAgriculteur.this,ModifierMotDePasse.class);
                i.putExtra("id_agriculteur",id_agriculteur);
                i.putExtra("password",password);
                startActivity(i);


            }
        });

        btnRendezVous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiRequest api= RetrofitServer.getClient().create(ApiRequest.class);
                //Instance Call Methode
                Call<ResponseDataModel> RendezVous=api.RendezVous(id_agriculteur);
                RendezVous.enqueue(new Callback<ResponseDataModel>() {
                    @Override
                    public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {

                           if (response.body().getCode().equals("1")) {
                                AlertDialog alertDialog = new AlertDialog.Builder(MenuAgriculteur.this).create();
                                alertDialog.setTitle("Info");
                                alertDialog.setMessage("Merci, Bientôt on va repondre a votre demande");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                            }
                        else {
                            Toast.makeText(MenuAgriculteur.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseDataModel> call, Throwable t) {
                        Toast.makeText(MenuAgriculteur.this, "Problem Connexion", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        btnTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MenuAgriculteur.this,ListTransactions.class);
                i.putExtra("id_agriculteur",id_agriculteur);

                startActivity(i);
            }
        });

        btnReponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiRequest api= RetrofitServer.getClient().create(ApiRequest.class);
                //Instance Call Methode
                Call<ResponseDataModel> ReponseRendezVous=api.ReponseRendezVous(id_agriculteur);
ReponseRendezVous.enqueue(new Callback<ResponseDataModel>() {
    @Override
    public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {
        if (response.isSuccessful()) {
            if (!response.body().getResult().isEmpty()) {
                if (response.body().getResult().get(0).getReponse().equals("Pas encore")) {


                    AlertDialog alertDialog = new AlertDialog.Builder(MenuAgriculteur.this).create();
                    alertDialog.setTitle("Info");
                    alertDialog.setMessage("Bientôt on va repondre a votre demande");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            else {
                AlertDialog alertDialog = new AlertDialog.Builder(MenuAgriculteur.this).create();
                alertDialog.setTitle("Info");
                alertDialog.setMessage(response.body().getResult().get(0).getReponse());
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }}else {
                Toast.makeText(MenuAgriculteur.this, "Empty Response", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Call<ResponseDataModel> call, Throwable t) {
        Toast.makeText(MenuAgriculteur.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
});
            }
        });

    }

}
