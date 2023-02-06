package com.example.hereis.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hereis.R;
import com.example.hereis.model.Lista;

import java.util.ArrayList;
import java.util.List;

public class ListaAdpater extends RecyclerView.Adapter<ListaAdpater.MyViewHolder> {

    private List<Lista> lista = new ArrayList<Lista>();

    public ListaAdpater(List<Lista> lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_adapter_view, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.estado.setText(this.lista.get(position).getEstado());
        holder.cidade.setText(this.lista.get(position).getCidade());
        holder.localidade.setText(this.lista.get(position).getLocalidade());
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView estado, cidade, localidade;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            estado = itemView.findViewById(R.id.txtNome);
            cidade = itemView.findViewById(R.id.txtEmail);
            localidade = itemView.findViewById(R.id.txtTelefone);
        }
    }

}