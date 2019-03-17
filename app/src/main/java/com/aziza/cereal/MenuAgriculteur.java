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
    Button btnAppeler, btnRendezVous, btnReglement;
    String id_agriculteur;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
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
               CheckUserPermsions();
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




    void CheckUserPermsions(){
        if ( Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
                    PackageManager.PERMISSION_GRANTED  ){
                requestPermissions(new String[]{
                                android.Manifest.permission.CALL_PHONE},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return ;
            }
        }

        AppelerServiceClient();// init the contact list

    }

    private void AppelerServiceClient() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "27181132"));
        startActivity(intent);
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AppelerServiceClient();// init the contact list
                } else {
                    // Permission Denied
                    Toast.makeText( this,"Tu doit accepter pour Appeler Administration" , Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
