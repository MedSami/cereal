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

public class ContacterAdministration extends AppCompatActivity {
String id_transformateur;
Button btnEnvoyer;
EditText edtMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacter_administration);

        btnEnvoyer=findViewById(R.id.btnEnvoyer);
        edtMsg=findViewById(R.id.edtMsg);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            id_transformateur = data.getString("id_transformateur");
            Toast.makeText(this, id_transformateur, Toast.LENGTH_SHORT).show();
        }

        btnEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtMsg.getText().toString().equals("")){
                    Toast.makeText(ContacterAdministration.this, "Ecrire une message SVP", Toast.LENGTH_SHORT).show();
                }else {
                    ApiRequest api= RetrofitServer.getClient().create(ApiRequest.class);
                    //Instance Call Methode
                    Call<ResponseDataModel> envoyerMsg=api.EnvoyerMsg(id_transformateur,edtMsg.getText().toString());
                    envoyerMsg.enqueue(new Callback<ResponseDataModel>() {
                        @Override
                        public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {
                            Toast.makeText(ContacterAdministration.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseDataModel> call, Throwable t) {
                            Toast.makeText(ContacterAdministration.this, "Problem Connexion", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}
