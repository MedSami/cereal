package com.aziza.cereal;

import android.content.Intent;
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

public class MenuTransformateur extends AppCompatActivity {
Button btnFacture,btnContacte;
String id_transformateur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_transformateur);

        btnContacte=findViewById(R.id.btnContacter);
        btnFacture=findViewById(R.id.btnFacture);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            id_transformateur = data.getString("id_transformateur");
            Toast.makeText(this, id_transformateur, Toast.LENGTH_SHORT).show();
        }

        btnFacture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiRequest api= RetrofitServer.getClient().create(ApiRequest.class);
                //Instance Call Methode
                Call<ResponseDataModel> demandeFacturation=api.Facturation(id_transformateur);
                demandeFacturation.enqueue(new Callback<ResponseDataModel>() {
                    @Override
                    public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {
                        Toast.makeText(MenuTransformateur.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseDataModel> call, Throwable t) {
                        Toast.makeText(MenuTransformateur.this, "Problem Connexion", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnContacte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuTransformateur.this,ContacterAdministration.class);
               intent.putExtra("id_transformateur",id_transformateur);
                startActivity(intent);
            }
        });
    }
}
