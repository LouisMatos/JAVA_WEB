package br.com.luismatos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.luismatos.model.Usuario;

@Repository
public class JpaUsuarioDao2 implements UsuarioDao2 {

	@PersistenceContext
	EntityManager manager;

	@Override
	public boolean existeUsuario(Usuario u) {
		boolean existe = false;
		 TypedQuery<Usuario> query = manager.createQuery(
			        "SELECT c FROM Usuario c WHERE c.login = :login and c.senha = :senha", Usuario.class);
			Usuario usu = query.setParameter("login", u.getLogin()).setParameter("senha", u.getSenha()).getSingleResult();
		if(usu != null){
			existe = true;
		}
		return existe;
	}

	@Override
	public List<Usuario> lista() {
		return manager.createQuery("select u from Usuario u").getResultList();
	}

	@Override
	public void adiciona(Usuario u) {
		manager.persist(u);
	}

	@Override
	public void altera(Usuario u) {
		manager.merge(u);
	}

	@Override
	public void remove(Usuario u) {
		Usuario removeUsuario = buscaPorId(u.getId());
		manager.remove(removeUsuario);
	}

	@Override
	public Usuario buscaPorId(Long id) {
		return manager.find(Usuario.class, id);
	}

	
}