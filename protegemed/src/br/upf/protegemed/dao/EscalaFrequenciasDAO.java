package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.upf.protegemed.beans.Versao;
import br.upf.protegemed.beans.escala.EscalaFrequencia;
import br.upf.protegemed.beans.escala.PericulosidadeFuga;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class EscalaFrequenciasDAO {
	
	public EscalaFrequencia queryFrequencias(Versao versao, PericulosidadeFuga periculosidadeFuga) throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		EscalaFrequencia escalaFrequencia;
		float[] valor = new float[12];
		float[] frequencia = new float[12];
		int indic = 0;
		
		try {
			stmt = ConnectionFactory.getConnection().prepareStatement(Utils.QuerySelect.QUERY_ESCALA_FREQUENCIA);
			
			stmt.setInt(1, periculosidadeFuga.getId());
			stmt.setInt(2, versao.getId());
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {		
				valor[indic] = resultSet.getFloat(1);
				frequencia[indic] = resultSet.getFloat(2);
				indic += 1;
			}
			
			escalaFrequencia = new EscalaFrequencia();
			escalaFrequencia.setFrequencia(frequencia);
			escalaFrequencia.setValor(valor);
			escalaFrequencia.setVersao(versao);
			escalaFrequencia.setPericulosidadeFuga(periculosidadeFuga);
			
			stmt.close();
			return escalaFrequencia;
					
		} catch (SQLException e) {
			throw new ProtegeDAOException(e.getMessage());
		}
	}
}
