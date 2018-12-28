package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.upf.protegemed.beans.Versao;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class VersaoDAO {

	public Versao queryVersao(String idVersao) throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ParseException{
		
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Versao versao = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.MASK_YYYY_MM_DD);		
		
		try {
			stmt = ConnectionFactory.getConnection().prepareStatement(Utils.QuerySelect.QUERY_VERSAO);
			
			stmt.setString(1, idVersao);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				versao = new Versao();
				versao.setId(resultSet.getInt(1));
				calendar.setTime(sdf.parse(resultSet.getString(2)));
				versao.setData(calendar);
				versao.setIdVersao(resultSet.getString(3));
				versao.setDescricao(resultSet.getString(4));
			}
			stmt.close();
			return versao;
					
		} catch (SQLException e) {
			throw new ProtegeDAOException(e.getMessage());
		}
	}
}
