package com.aziza.cereal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aziza.cereal.Api.ApiRequest;
import com.aziza.cereal.Api.RetrofitServer;
import com.aziza.cereal.Model.ResponseDataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inscrire extends AppCompatActivity {
        EditText edtNom,edtPrenom,edtIdentifiant,edtPassword;
        Button btnInscrire,btnAnnuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrire);

        btnAnnuler=findViewById(R.id.btnAnnuler);
        btnInscrire=findViewById(R.id.btnInscrire);

    edtNom=findViewById(R.id.edtNom);
    edtPrenom=findViewById(R.id.edtPrenom);
    edtIdentifiant=findViewById(R.id.edtIdentifiant);
    edtPassword=findViewById(R.id.edtPassword);

    btnAnnuler.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            edtNom.setText("");
            edtPassword.setText("");
            edtPrenom.setText("");
            edtIdentifiant.setText("");
        }
    });

    btnInscrire.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(edtNom.getText().toString().equals("")){
                Toast.makeText(Inscrire.this, "Saisir votre Nom SVP", Toast.LENGTH_SHORT).show();
            }else if (edtPrenom.getText().toString().equals("")){
                Toast.makeText(Inscrire.this, "Saisir Votre Prenom SVP", Toast.LENGTH_SHORT).show();
            }else if(edtIdentifiant.getText().toString().equals("")){
                Toast.makeText(Inscrire.this, "Saisir Votre Identifiant SVP", Toast.LENGTH_SHORT).show();
            }else if (edtPassword.getText().toString().equals("")){
                Toast.makeText(Inscrire.this, "Saisir Votre Mot De Passe", Toast.LENGTH_SHORT).show();
            }else {
                ApiRequest api= RetrofitServer.getClient().create(ApiRequest.class);
                //Instance Call Methode
                Call<ResponseDataModel> Login=api.Inscrire(edtNom.getText().toString(),edtPrenom.getText().toString()
                ,edtPassword.getText().toString(),edtIdentifiant.getText().toString());

                Login.enqueue(new Callback<ResponseDataModel>() {
                    @Override
                    public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {
                        Toast.makeText(Inscrire.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseDataModel> call, Throwable t) {
                        Toast.makeText(Inscrire.this, "Problem Connexion", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    });


    }
}
