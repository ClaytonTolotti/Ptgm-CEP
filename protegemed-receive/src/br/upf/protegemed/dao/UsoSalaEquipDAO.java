package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.UsoSala;
import br.upf.protegemed.beans.UsoSalaEquip;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class UsoSalaEquipDAO {

public List<UsoSalaEquip> queryUseRoomEquip() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		
		PreparedStatement stmt;
		UsoSalaEquip usoSalaEquip;
		Equipamento equipamento;
		UsoSala usoSala;
		List<UsoSalaEquip> listUsoSalaEquip = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			stmt = ConnectionFactory.getConnection().prepareStatement(Utils.QuerySelect.QUERY_USO_SALA_EQUIP);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				usoSalaEquip = new UsoSalaEquip();
				usoSala = new UsoSala();
				equipamento = new Equipamento();
				equipamento.setCodEquip(resultSet.getInt(1));
				usoSala.setCodUsoSala(resultSet.getInt(2));
				usoSalaEquip.setEquipamento(equipamento);
				usoSalaEquip.setUsoSala(usoSala);
				listUsoSalaEquip.add(usoSalaEquip);
			}
		
			stmt.close();
			return listUsoSalaEquip;
		} catch(SQLException pr) {
			throw new ProtegeDAOException(pr.getMessage());
		}
	}
}
