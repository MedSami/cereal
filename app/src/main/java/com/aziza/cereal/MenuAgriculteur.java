package com.aziza.cereal;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
    Button btnAppeler, btnRendezVous, btnReglement;
    String id_agriculteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_agriculteur);

        btnAppeler = findViewById(R.id.btnAppeler);
        btnRendezVous = findViewById(R.id.btnRendezVous);
        btnReglement = findViewById(R.id.btnReglement);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            id_agriculteur = data.getString("id_agriculteur");
            Toast.makeText(this, id_agriculteur, Toast.LENGTH_SHORT).show();
        }

        btnAppeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "52717722"));
                if (ActivityCompat.checkSelfPermission(MenuAgriculteur.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
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
                        if(response.isSuccessful()){
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
                    }

                    @Override
                    public void onFailure(Call<ResponseDataModel> call, Throwable t) {
                        Toast.makeText(MenuAgriculteur.this, "Problem Connexion", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        btnReglement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
