package br.com.luismatos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.luismatos.model.Usuario;

@Component
public class UsuarioDAO {
	private Connection conn;
	
	@Autowired
	public UsuarioDAO() {
		this.conn = new ConnectionFactory().getConnection();
	}

	public boolean existeUsuario(Usuario usuario) {
		boolean existe = false;
		String sql = "select * from usuarios where login = ? and senha = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getLogin());
			System.out.println(usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			System.out.println(usuario.getSenha());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				existe = true;
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return existe;
	}
}
