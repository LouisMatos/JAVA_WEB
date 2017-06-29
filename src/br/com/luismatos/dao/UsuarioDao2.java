package br.com.luismatos.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.luismatos.model.Usuario;

@Component
public interface UsuarioDao2 {
	
	boolean existeUsuario(Usuario u);
	
	Usuario buscaPorId(Long id);

	List<Usuario> lista();

	void adiciona(Usuario u);

	void altera(Usuario u);

	void remove(Usuario u);

	
}
