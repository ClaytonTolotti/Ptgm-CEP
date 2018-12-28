package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class EventosDAO {

public List<Eventos> queryEvents() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException {
		
		PreparedStatement stmt;
		Eventos eventos;
		List<Eventos> listEvents = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			stmt = ConnectionFactory.getConnection().prepareStatement(Utils.QuerySelect.QUERY_EVENTOS);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				eventos = new Eventos();
				eventos.setCodEvento(resultSet.getInt(1));
				eventos.setDesc(resultSet.getString(2));
				eventos.setFormaOnda(resultSet.getInt(3));				
				listEvents.add(eventos);
			}
			
			stmt.close();
			return listEvents;
		} catch(SQLException pr) {
			throw new ProtegeDAOException(pr.getMessage());
		}
	}
}
