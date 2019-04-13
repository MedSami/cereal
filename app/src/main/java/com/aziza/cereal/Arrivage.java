package com.aziza.cereal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aziza.cereal.Api.ApiRequest;
import com.aziza.cereal.Api.RetrofitServer;
import com.aziza.cereal.Model.ResponseDataModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Arrivage extends AppCompatActivity {
int index;
Button btnEnregistrer;
EditText edtQuantite, edtMatricule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrivage);
btnEnregistrer=findViewById(R.id.btnEnregistrer);
edtQuantite=findViewById(R.id.edtQuantite);
edtMatricule =findViewById(R.id.edtMatricule);



        // Spinner element
        Spinner spinner =  findViewById(R.id.spinner);


        // Spinner Drop down elements
        List<String> TypeCereal = new ArrayList<String>();
        TypeCereal.add("Choisir Type Cereal");
        TypeCereal.add("83ir");
        TypeCereal.add("Farina");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, TypeCereal);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                //item = adapterView.getItemAtPosition(position).toString();
                index= position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

btnEnregistrer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
         if(index==0){
                    Toast.makeText(Arrivage.this, "Chisir Type Cereal SVP", Toast.LENGTH_SHORT).show();
                }else if(edtQuantite.getText().toString().equals("")){
                    Toast.makeText(Arrivage.this, "Saisir Quantite En KG SVP", Toast.LENGTH_SHORT).show();
                }else if(edtMatricule.getText().toString().equals("")){
                    Toast.makeText(Arrivage.this, "Saisir Matricule SVP", Toast.LENGTH_SHORT).show();
                }else {
                    ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
                    //Instance Call Methode
                    Call<ResponseDataModel> enregistrerArrivage = api.EnregistrerCereal(index,edtQuantite.getText().toString(), edtMatricule.getText().toString());
                    enregistrerArrivage.enqueue(new Callback<ResponseDataModel>() {
                        @Override
                        public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {
                            Toast.makeText(Arrivage.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseDataModel> call, Throwable t) {
                            Toast.makeText(Arrivage.this, "Problem Connexion", Toast.LENGTH_SHORT).show();
                        }
                    });

         }
                }
});

    }
}
