package com.example.hereis;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hereis.helper.ListaDAO;
import com.example.hereis.model.Lista;
import com.google.android.material.textfield.TextInputEditText;
public class buscaActivity extends AppCompatActivity {

    private TextInputEditText estado, cidade, localidade;
    private Lista listaAtual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);
        estado = findViewById(R.id.edtNomeContato);
        cidade = findViewById(R.id.edtTelefone);
        localidade = findViewById(R.id.edtEmail);
        Intent intent = getIntent();
        listaAtual = (Lista) intent.getSerializableExtra("listaSelecionado");
        if (listaAtual != null){
            estado.setText(listaAtual.getEstado());
            cidade.setText(listaAtual.getCidade());
            localidade.setText(listaAtual.getLocalidade());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemSalvar:
                ListaDAO listaDAO = new ListaDAO(getApplicationContext());
                if (listaAtual != null){
                    String estad = estado.getText().toString();
                    String cidad = cidade.getText().toString();
                    String localidad = localidade.getText().toString();
                    Long id = listaAtual.getId();
                    Lista lista = new Lista(estad, cidad, localidad, id);
                    if (listaDAO.atualizar(lista)){
                        Toast.makeText(getApplicationContext(), "endereço atualizado!",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Erro ao atualizar endereço!",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    String estad = estado.getText().toString();
                    String cidad = cidade.getText().toString();
                    String localidad = localidade.getText().toString();
                    Lista lista = new Lista();
                    lista.setEstado(estad);
                    lista.setCidade(cidad);
                    lista.setLocalidade(localidad);
                    if (listaDAO.salvar(lista)){
                        Toast.makeText(getApplicationContext(), "endereço salvo!",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Erro ao salvar Endereço!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}