package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.upf.protegemed.beans.SalaCirurgia;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class SalaCirurgiaDAO {

	private PreparedStatement stmt = null;
	private ResultSet resultSet;
	
	public SalaCirurgia querySalaCirurgia (Integer codTomada) throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException {
		
		SalaCirurgia sala = new SalaCirurgia();
		
		try {
			stmt = ConnectionFactory.getConnection().prepareStatement(Utils.QuerySelect.QUERY_COD_SALA);
			stmt.setInt(1, codTomada);
			
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				sala.setCodSala(resultSet.getInt(1));
				sala.setDesc(resultSet.getString(2));
			}
			resultSet.close();
			stmt.close();
			return sala;
		} catch (SQLException e) {
			throw new ProtegeDAOException(e.getMessage());
		}
	}
}
