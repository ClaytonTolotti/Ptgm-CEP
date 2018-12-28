package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.upf.protegemed.beans.Versao;
import br.upf.protegemed.beans.escala.EscalaSimilaridade;
import br.upf.protegemed.beans.escala.PericulosidadeFuga;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class EscalaSimilaridadeDAO {

	public List<EscalaSimilaridade> queryEscalaSimilaridade(Versao versao, PericulosidadeFuga periculosidadeFuga) throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<EscalaSimilaridade> listEscalaSimilaridade = new ArrayList<>();
		EscalaSimilaridade escalaSimilaridade;
		
		try {
			stmt = ConnectionFactory.getConnection().prepareStatement(Utils.QuerySelect.QUERY_ESCALA_SIMILARIDADE);
			
			stmt.setInt(1, periculosidadeFuga.getId());
			stmt.setInt(2, versao.getId());
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {

				escalaSimilaridade = new EscalaSimilaridade();
				escalaSimilaridade.setValorMin(resultSet.getFloat(1));
				escalaSimilaridade.setValorMax(resultSet.getFloat(2));
				escalaSimilaridade.setPericulosidadeFuga(periculosidadeFuga);
				escalaSimilaridade.setVersao(versao);
				listEscalaSimilaridade.add(escalaSimilaridade);
			}
			stmt.close();
			return listEscalaSimilaridade;
					
		} catch (SQLException e) {
			throw new ProtegeDAOException(e.getMessage());
		}
	}
}
