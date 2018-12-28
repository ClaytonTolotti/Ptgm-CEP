package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.upf.protegemed.beans.Versao;
import br.upf.protegemed.beans.escala.EscalaCorrente;
import br.upf.protegemed.beans.escala.PericulosidadeFuga;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class EscalaCorrenteDAO {
	
	public EscalaCorrente queryCorrente(Versao versao, PericulosidadeFuga periculosidadeFuga) throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		EscalaCorrente escalaCorrente = null;
		
		try {
			stmt = ConnectionFactory.getConnection().prepareStatement(Utils.QuerySelect.QUERY_ESCALA_CORRENTE);
			
			stmt.setInt(1, periculosidadeFuga.getId());
			stmt.setInt(2, versao.getId());
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				escalaCorrente = new EscalaCorrente();
				escalaCorrente.setValor(resultSet.getFloat(1));
			}
			
			if (escalaCorrente != null) {
				escalaCorrente.setPericulosidadeFuga(periculosidadeFuga);
				escalaCorrente.setVersao(versao);
			}
			stmt.close();
			return escalaCorrente;
					
		} catch (SQLException e) {
			throw new ProtegeDAOException(e.getMessage());
		}
	}
}
