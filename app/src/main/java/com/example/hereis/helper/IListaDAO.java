package com.example.hereis.helper;
import com.example.hereis.model.Lista;
import java.util.List;

public interface IListaDAO {
    public boolean salvar(Lista lista);
    public boolean atualizar(Lista lista);
    public boolean deletar(Lista lista);
    public List<Lista> listar();
}
