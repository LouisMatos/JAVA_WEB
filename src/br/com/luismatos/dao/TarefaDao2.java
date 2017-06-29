package br.com.luismatos.dao;

import java.util.List;

import br.com.luismatos.model.Tarefa;

public interface TarefaDao2 {

	Tarefa buscaPorId(Long id);

	List<Tarefa> lista();

	void adiciona(Tarefa t);

	void altera(Tarefa t);

	void remove(Tarefa t);

	void finaliza(Long id);
}
