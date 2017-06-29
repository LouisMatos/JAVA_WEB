package br.com.luismatos.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/agenda?verifyServerCertificate=false&useSSL=true",
					"root", "1234");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
