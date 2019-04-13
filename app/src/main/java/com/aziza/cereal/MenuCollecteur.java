package com.aziza.cereal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuCollecteur extends AppCompatActivity {
Button btnEnregistrerArrivage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_collecteur);

    btnEnregistrerArrivage =findViewById(R.id.btnArrivage);


    btnEnregistrerArrivage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Intent i= new Intent(MenuCollecteur.this, Arrivage.class);
        startActivity(i);
        }
    });

    }
}
