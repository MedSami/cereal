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

public class Inscrire extends AppCompatActivity {
        EditText edtNom,edtPrenom,edtPassword,edtAddress,edtNumTel,edtEmail,edtCin;
        Button btnInscrire,btnAnnuler;
        int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrire);

        btnAnnuler=findViewById(R.id.btnAnnuler);
        btnInscrire=findViewById(R.id.btnInscrire);

        edtNom=findViewById(R.id.edtNom);
        edtPrenom=findViewById(R.id.edtPrenom);
        edtPassword=findViewById(R.id.edtPassword);
        edtAddress=findViewById(R.id.edtAdress);
        edtNumTel=findViewById(R.id.edtPhone);
        edtEmail=findViewById(R.id.edtEmail);
        edtCin=findViewById(R.id.edtCin);



        // Spinner element
        Spinner spinner =  findViewById(R.id.spinner);


        // Spinner Drop down elements
        List<String> utilisateurs = new ArrayList<String>();
        utilisateurs.add("Agriculteur");
        utilisateurs.add("Transformateur");
        utilisateurs.add("Collecteur");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, utilisateurs);

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



        btnAnnuler.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            edtNom.setText("");
            edtPassword.setText("");
            edtPrenom.setText("");
            edtNumTel.setText("");
            edtAddress.setText("");
            edtEmail.setText("");
            edtCin.setText("");
        }
    });

    btnInscrire.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(edtNom.getText().toString().equals("")){
                Toast.makeText(Inscrire.this, "Saisir votre Nom SVP", Toast.LENGTH_SHORT).show();
            }else if (edtPrenom.getText().toString().equals("")){
                Toast.makeText(Inscrire.this, "Saisir Votre Prenom SVP", Toast.LENGTH_SHORT).show();
            }else if (edtPassword.getText().toString().equals("")){
                Toast.makeText(Inscrire.this, "Saisir Votre Mot De Passe", Toast.LENGTH_SHORT).show();
            }else if(edtNumTel.getText().toString().equals("") && edtNumTel.getText().toString().length()!=8){
                Toast.makeText(Inscrire.this, "Verifier Votre Numero Tel SVP", Toast.LENGTH_SHORT).show();
            }else if (edtAddress.getText().toString().equals("")){
                Toast.makeText(Inscrire.this, "Saisir Votre Adresse", Toast.LENGTH_SHORT).show();
            }else if(edtCin.getText().toString().equals("") && edtCin.getText().toString().length()!=8){
                Toast.makeText(Inscrire.this, "Verifier Votre Numero CIN SVP", Toast.LENGTH_SHORT).show();
            }else {
                ApiRequest api= RetrofitServer.getClient().create(ApiRequest.class);
                //Instance Call Methode
                Call<ResponseDataModel> Login=api.Inscrire(edtNom.getText().toString(),edtPrenom.getText().toString()
                ,edtPassword.getText().toString(),edtNumTel.getText().toString(),edtAddress.getText().toString()
                        ,edtEmail.getText().toString(),edtCin.getText().toString(),""+index);

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
