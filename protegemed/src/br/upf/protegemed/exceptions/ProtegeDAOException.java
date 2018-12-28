package br.upf.protegemed.exceptions;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ProtegeDAOException extends SQLException implements Serializable{

	private static final long serialVersionUID = 8045525233561877155L;
	public static final String msgException = "Ocorreu um erro no Banco de Dados";
	static Logger logger = Logger.getLogger(ProtegeDAOException.class);
	
	public ProtegeDAOException(String msg) {
		super(msgException.concat("\n").concat(msg));
		logger.error(msg);
	}
}
