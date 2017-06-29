package br.com.luismatos.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.luismatos.model.Tarefa;

public class TarefaDAO {
	private Connection conn;

	public TarefaDAO() {
		this.conn = new ConnectionFactory().getConnection();
	}

	public void adiciona(Tarefa tarefa) {
		String sql = "insert into tarefas "
				+ " (descricao, finalizado, datafinalizacao)"
				+ " values (?,?,?) ";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			tarefa.setFinalizado(false);
			tarefa.setDataFinalizacao(Calendar.getInstance());

			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setNull(3, java.sql.Types.DATE);
			//stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void finaliza(Long id) {
		String sql = "update tarefas set finalizado=true, datafinalizacao=? where id ="+id;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDate(1,new Date(Calendar.getInstance().getTimeInMillis()));

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	public List<Tarefa> lista() {
		String sql = "select * from tarefas";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			List<Tarefa> lista = new ArrayList<Tarefa>();

			while (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));

				if (rs.getDate("dataFinalizacao") != null){
					Calendar dataFinalizacao = Calendar.getInstance();
					dataFinalizacao.setTime(rs.getDate("dataFinalizacao"));
					tarefa.setDataFinalizacao(dataFinalizacao);
				}else{
					tarefa.setDataFinalizacao(null);
				}
				lista.add(tarefa);
			}

			rs.close();
			stmt.close();
			conn.close();

			return lista;

		} catch (Exception e) {

			throw new RuntimeException(e);
		}

	}

	public Tarefa buscaPorId(long id) {
		String sql = "select * from tarefas where id =" + id;

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();

			Tarefa tarefa = new Tarefa();
			tarefa.setId(rs.getLong("id"));
			tarefa.setDescricao(rs.getString("descricao"));
			tarefa.setFinalizado(rs.getBoolean("finalizado"));
			
			if (rs.getDate("dataFinalizacao") != null){
				Calendar dataFinalizacao = Calendar.getInstance();
				dataFinalizacao.setTime(rs.getDate("dataFinalizacao"));
				tarefa.setDataFinalizacao(dataFinalizacao);
			}else{
				tarefa.setDataFinalizacao(null);
			}

			stmt.execute();
			stmt.close();

			return tarefa;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void altera(Tarefa tarefa) {
		String sql = "update tarefas set descricao=?, finalizado=?, datafinalizacao=? where id =?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			if (tarefa.getDataFinalizacao() != null){
				stmt.setDate(3, new Date(tarefa.getDataFinalizacao()
						.getTimeInMillis()));
			}else{
				stmt.setNull(3, java.sql.Types.DATE);
			}
			stmt.setLong(4, tarefa.getId());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	
	public void remove(Tarefa tarefa) {
		String sql = "delete from tarefas where id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
