package com.example.hereis.helper;

import com.unir.listadecontatos.model.Contato;

import java.util.List;

public class ListaDAO {

    public boolean salvar(Lista lista);
    public boolean atualizar(Lista lista);
    public boolean deletar(Lista lista);
    public List<Lista> listar();
}
}
