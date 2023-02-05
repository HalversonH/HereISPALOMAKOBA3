package com.example.hereis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hereis.retrofit.CEP;
import com.example.hereis.retrofit.CEPService;
import com.example.hereis.retrofit.MapsActivityretro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

private Button btnproximo;
String estado, cidade, localidade;
    private TextView textView;
    private EditText edtestado;
    private EditText edtcidade;
    private EditText edtlocali;
private Button btnbuscar;
    private Retrofit retrofit;

    public EditText getEdtestado() {
        return edtestado;
    }

    public EditText getEdtcidade() {
        return edtcidade;
    }

    public EditText getEdtlocali() {
        return edtlocali;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnproximo = findViewById(R.id.btnproximo);
        btnbuscar = findViewById(R.id.btnbuscar);

        textView = findViewById(R.id.textView);
        edtcidade = findViewById(R.id.edtcidade);
        edtestado = findViewById(R.id.edtestado);
        edtlocali = findViewById(R.id.edtlocali);
        String url = "https://viacep.com.br/ws/";
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();



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

            //Intent retro =new Intent(getApplicationContext(),buscaActivity.class);
          //  startActivity(retro);
            estado = edtestado.getText().toString();
            cidade = edtcidade.getText().toString();
            localidade = edtlocali.getText().toString();
            recuperarCep();


                                       }
                                                              }  );

    }private void recuperarCep(){
        CEPService cepService = retrofit.create(CEPService.class);
        Call<List<CEP>> call = cepService.recuperarCEP(estado, cidade,localidade);
        call.enqueue(new Callback<List<CEP>>(){
            @Override
            public void onResponse(Call<List<CEP>> call, Response<List<CEP>> response) {
                if(response.isSuccessful()){
                    List<CEP> cep = response.body();
                    String enderecos = "";
                    for (int i=0; i< cep.size(); i++){
                        enderecos += cep.get(i).getLogradouro()+"\n";
                    }
                    textView.setText(enderecos);
                }
            } @Override
            public void onFailure(Call<List<CEP>> call, Throwable t) {
                Log.d("Erro", t.getMessage());
            }
        });
    }
 }
