package br.upf.protegemed.exceptions;

import java.io.Serializable;
import java.text.ParseException;

import org.apache.log4j.Logger;

public class ProtegemedParserException extends ParseException implements Serializable{

	private static final long serialVersionUID = -8882882474413693051L;
	public static final String msgException = "Ocorreu um erro no parser de datas";
	static Logger logger = Logger.getLogger(ProtegemedParserException.class);
	
	public ProtegemedParserException(String msg, Integer errorOfSet) {
		super(msgException.concat("\n").concat(msg), errorOfSet);
		logger.error(msg);
	}
}
