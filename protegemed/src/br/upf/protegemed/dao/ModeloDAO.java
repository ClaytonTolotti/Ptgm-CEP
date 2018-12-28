package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.upf.protegemed.beans.Modelo;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class ModeloDAO {

public List<Modelo> queryModels() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		
		PreparedStatement stmt;
		Modelo modelo;
		List<Modelo> listModels = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			stmt = ConnectionFactory.getConnection().prepareStatement(Utils.QuerySelect.QUERY_MODELO);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				modelo = new Modelo();
				modelo.setCodModelo(resultSet.getInt(1));
				modelo.setDesc(resultSet.getString(2));
				listModels.add(modelo);
			}
			
			stmt.close();
			return listModels;
		} catch(SQLException pr) {
			throw new ProtegeDAOException(pr.getMessage());
		}
	}
}
