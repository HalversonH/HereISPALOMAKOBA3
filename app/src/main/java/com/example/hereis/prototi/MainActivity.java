package com.example.hereis.prototi;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hereis.R;
import com.example.hereis.adapter.ListaAdpater;
import com.example.hereis.buscaActivity;
import com.example.hereis.databinding.ActivityMainBinding;
import com.example.hereis.helper.ListaDAO;
import com.example.hereis.helper.RecyclerItemClickListener;
import com.example.hereis.model.Lista;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private RecyclerView recyclerView;
    private ListaAdpater listaAdpater;
    private FloatingActionButton fab;
    RecyclerView.LayoutManager layoutManager;
    private List<Lista> listaContatos = new ArrayList<Lista>();
    private Lista listaSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainproto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = findViewById(R.id.recyclerview);
        setSupportActionBar(toolbar);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, buscaActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(),
                        recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        listaSelecionado = listaContatos.get(position);
                        Intent intent = new Intent(MainActivity.this, buscaActivity.class);
                        intent.putExtra("listaSelecionado", listaSelecionado);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        listaSelecionado = listaContatos.get(position);
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Confirmar exclusão");
                        builder.setMessage("Deseja confirmar a exlusão do endereço: " + listaSelecionado.getEstado()+"?");
                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ListaDAO listaDAO = new ListaDAO(getApplicationContext());
                                if (listaDAO.deletar(listaSelecionado)){
                                    carregarLista();
                                    Toast.makeText(getApplicationContext(), "Endereço exlucluído!",
                                            Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Erro ao excluir Endereço!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("Não", null);
                        builder.create().show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }));
    } public void carregarLista(){
       ListaDAO listaDAO = new ListaDAO(getApplicationContext());
        listaContatos = listaDAO.listar();

        listaAdpater = new ListaAdpater(listaContatos);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                LinearLayout.VERTICAL));
        recyclerView.setAdapter(listaAdpater);

    }

    @Override
    protected void onStart() {
        carregarLista();
        super.onStart();
    }
}