package com.aziza.cereal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuTransformateur extends AppCompatActivity {
Button btnPasserComande;
String id_transformateur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_transformateur);

        btnPasserComande=findViewById(R.id.btnPasseCommande);


        Bundle data = getIntent().getExtras();
        if (data != null) {
            id_transformateur = data.getString("id_transformateur");
        }



        btnPasserComande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuTransformateur.this, PasserCommande.class);
               intent.putExtra("id_transformateur",id_transformateur);
                startActivity(intent);
            }
        });
    }
}
