package br.upf.protegemed.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.utils.Utils;

public class ConnectionFactory {
	
	private static Connection conexao = null;
	
	private ConnectionFactory() {
		super();
	}
	
	public static Connection getConnection() throws ProtegeDAOException, ProtegeInstanciaException,
			ProtegeIllegalAccessException, ProtegeClassException {
		try {
			
			if (conexao == null) {
				Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
				
				return DriverManager.getConnection(
						"jdbc:" + Utils.JDBC + "://" + Utils.HOST + ":" + Utils.PORT + "/" + Utils.BD, Utils.USER,
						Utils.PASSWORD);
			} else {
				return conexao;
			}
		} catch (SQLException pr) {
			throw new ProtegeDAOException(pr.getMessage());
		} catch (InstantiationException in) {
			throw new ProtegeInstanciaException(in.getMessage());
		} catch (IllegalAccessException il) {
			throw new ProtegeIllegalAccessException(il.getMessage());
		} catch (ClassNotFoundException cl) {
			throw new ProtegeClassException(cl.getMessage());
		} catch (IllegalArgumentException e) {
			throw new ProtegeClassException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new ProtegeClassException(e.getMessage());
		} catch (NoSuchMethodException e) {
			throw new ProtegeClassException(e.getMessage());
		} catch (SecurityException e) {
			throw new ProtegeClassException(e.getMessage());
		}
	}

	public static Connection getConexao() {
		return conexao;
	}

	public static void setConexao(Connection conexao) {
		ConnectionFactory.conexao = conexao;
	}
}
