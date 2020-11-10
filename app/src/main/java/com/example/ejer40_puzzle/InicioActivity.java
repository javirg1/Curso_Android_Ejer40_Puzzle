package com.example.ejer40_puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        findViewById(R.id.ivTigre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent saltar_pantalla = new Intent(InicioActivity.this,MainActivity.class);
                startActivity(saltar_pantalla);
            }
        });
    }
}