package com.example.hereis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btnproximo;
Button btnbuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnproximo = findViewById(R.id.btnproximo);
        btnbuscar = findViewById(R.id.btnbuscar);
        btnproximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
 Intent atimap =new Intent(getApplicationContext(),MapsActivity.class);
          startActivity(atimap);
            }
        });

     btnbuscar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent retro =new Intent(getApplicationContext(),MapsActivityretro.class);
            startActivity(retro);
                                       }
                                                              }  );

                                                        }
                                                    }

