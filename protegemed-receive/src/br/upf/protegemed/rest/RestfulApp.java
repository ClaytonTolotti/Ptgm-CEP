package br.upf.protegemed.rest;

import javax.ws.rs.ApplicationPath;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;

@ApplicationPath("")
public class RestfulApp extends ResourceConfig {
	
	final static Logger logger = Logger.getLogger(ResourceConfig.class);
	
	public RestfulApp() throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		
		try {
			packages("com.fasterxml.jackson.jaxrs.json");
			packages("br.upf.protegemed.rest");
			
			LoadConfiguration.loadConnection();
			LoadConfiguration.loadVersao();
			LoadConfiguration.loadPericulosidadeFuga();
			LoadConfiguration.loadEscalaSimilaridade();
			LoadConfiguration.loadEscalaFrequencias();
			LoadConfiguration.loadEscalaCorrente();
			LoadConfiguration.initInstanceDrools();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}