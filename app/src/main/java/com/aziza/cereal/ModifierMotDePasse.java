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

public class ModifierMotDePasse extends AppCompatActivity {
EditText edtAncien,edtNouveau,edtRepeat;
Button btnModifier;
MainActivity ma=new MainActivity();
String id_agriculteur,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_mot_de_passe);
    edtAncien=findViewById(R.id.edtAncienMP);
    edtNouveau=findViewById(R.id.edTNouveau);
    edtRepeat=findViewById(R.id.edtRepeat);
    btnModifier=findViewById(R.id.btnModifier);


        Bundle data = getIntent().getExtras();
        if (data != null) {
            id_agriculteur = data.getString("id_agriculteur");
            password = data.getString("password");
        }


        btnModifier.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(edtAncien.getText().toString().equals("")){
                Toast.makeText(ModifierMotDePasse.this, "Saisir Ancien Mot De Passe", Toast.LENGTH_SHORT).show();
            }else if(edtNouveau.getText().toString().equals("")){
                Toast.makeText(ModifierMotDePasse.this, "Saisir Nouveau Mot De Passe", Toast.LENGTH_SHORT).show();
            }else if(edtRepeat.getText().toString().equals("")){
                Toast.makeText(ModifierMotDePasse.this, "Repeter Nouveau Mot De Passe", Toast.LENGTH_SHORT).show();
            }
            else {
                String PssCrypter=ma.convertPassMd5(edtAncien.getText().toString());
                if(PssCrypter.equals(password)){
                    String NewPssCrypter=ma.convertPassMd5(edtNouveau.getText().toString());


                    ApiRequest api= RetrofitServer.getClient().create(ApiRequest.class);
                    //Instance Call Methode
                    Call<ResponseDataModel> ModifierPss=api.ModifierPss(id_agriculteur,PssCrypter,NewPssCrypter);
                    ModifierPss.enqueue(new Callback<ResponseDataModel>() {
                        @Override
                        public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {
                            Toast.makeText(ModifierMotDePasse.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseDataModel> call, Throwable t) {
                            Toast.makeText(ModifierMotDePasse.this, "Problem Connexion", Toast.LENGTH_SHORT).show();
                        }
                    });


                }else {
                    Toast.makeText(ModifierMotDePasse.this, "Ancien Mot De Passe Incorrect", Toast.LENGTH_SHORT).show();
                }
                
            }
        }
    });

    }
}
