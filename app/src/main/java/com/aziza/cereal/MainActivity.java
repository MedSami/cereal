package com.aziza.cereal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
EditText edtCin,edtPassword;
Button btnEntrer,btnInscrire;
int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    edtCin =findViewById(R.id.edtCin);
    edtPassword=findViewById(R.id.edtPassword);
    btnEntrer=findViewById(R.id.btnEntrer);
    btnInscrire=findViewById(R.id.btnInscrire);


        // Spinner element
        Spinner spinner =  findViewById(R.id.spinner);


        // Spinner Drop down elements
        List<String> acteurs = new ArrayList<String>();
        acteurs.add("Agriculteur");
        acteurs.add("Transformateur");
        acteurs.add("Collecteur");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, acteurs);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                index= position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btnInscrire.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(MainActivity.this,Inscrire.class);
            startActivity(i);
        }
    });


    btnEntrer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(edtCin.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "Saisir Votre CIN SVP", Toast.LENGTH_SHORT).show();
            }else if (edtPassword.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "Saisir Votre Mot De Passe SVP", Toast.LENGTH_SHORT).show();
            }else {
                ApiRequest api= RetrofitServer.getClient().create(ApiRequest.class);
                //Instance Call Methode
                Call<ResponseDataModel> Login=api.Login(edtCin.getText().toString(),index);

                Login.enqueue(new Callback<ResponseDataModel>() {
                    @Override
                    public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {

                        if(response.isSuccessful()){
                            if(!response.body().getResult().isEmpty()){
                                String CryptPass=convertPassMd5(edtPassword.getText().toString());
                                if(response.body().getResult().get(0).getCin().equals(edtCin.getText().toString())){
                                    if(response.body().getResult().get(0).getMotDePasse().equals(CryptPass)){
                                        if(index==0){

                                            Intent intent=new Intent(MainActivity.this,MenuAgriculteur.class);
                                            intent.putExtra("id_agriculteur",""+response.body().getResult().get(0).getId_utilisateur());
                                            intent.putExtra("Password",""+response.body().getResult().get(0).getMotDePasse());
                                            startActivity(intent);
                                        }
                                        if(index==1){

                                            Intent intent=new Intent(MainActivity.this,MenuTransformateur.class);
                                            intent.putExtra("id_transformateur",""+response.body().getResult().get(0).getId_utilisateur());
                                            startActivity(intent);
                                        }
                                        if(index==2){

                                            Intent intent=new Intent(MainActivity.this,MenuCollecteur.class);
                                            intent.putExtra("id_collecteur",""+response.body().getResult().get(0).getId_utilisateur());
                                            startActivity(intent);
                                        }

                                    }else {
                                        Toast.makeText(MainActivity.this, "Mot De Passe Incorrect", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else {
                                Toast.makeText(MainActivity.this, "CIN Incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "CIN Incorrect", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseDataModel> call, Throwable t) {
                        Log.e("TAG", "onFailure: ", t);
                        Toast.makeText(MainActivity.this, "Problem Connexion", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    });

    }



    public static String convertPassMd5(String pass) {
        String password = null;
        MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, mdEnc.digest()).toString(16);
            while (pass.length() < 32) {
                pass = "0" + pass;
            }
            password = pass;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return password;
    }
}
