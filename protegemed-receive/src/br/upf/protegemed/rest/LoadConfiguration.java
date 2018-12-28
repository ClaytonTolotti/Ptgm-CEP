/*
, * author: Clayton Tootti: claytontolotti@gmail.com
 * Classe com métodos para carregar previamente algumas informações para a aplicação.
 * Os métodos estão sendo utilizados na classe RestfulApp, classe responsável por inicializar
 * o webservice pós deploy no tomcat.
 */

package br.upf.protegemed.rest;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import br.upf.protegemed.beans.Versao;
import br.upf.protegemed.beans.escala.EscalaCorrente;
import br.upf.protegemed.beans.escala.EscalaFrequencia;
import br.upf.protegemed.beans.escala.EscalaSimilaridade;
import br.upf.protegemed.beans.escala.PericulosidadeFuga;
import br.upf.protegemed.dao.EscalaCorrenteDAO;
import br.upf.protegemed.dao.EscalaFrequenciasDAO;
import br.upf.protegemed.dao.EscalaSimilaridadeDAO;
import br.upf.protegemed.dao.PericulosidadeFugaDAO;
import br.upf.protegemed.dao.VersaoDAO;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;

public class LoadConfiguration {

	final static Logger logger = Logger.getLogger(LoadConfiguration.class);
	public static final Integer ativarLog = 1;

	public static Integer getAtivarlog() {
		return ativarLog;
	}
	
	private static KieServices ks;
	private static KieContainer kContainer;
	private static KieSession kSession;
	private static Integer inicializaoDrools = 0;
	private static Versao versao;
	private static List<PericulosidadeFuga> listPericulosidade;
	private static List<EscalaSimilaridade> listEscalaSimilaridades;
	private static List<EscalaFrequencia> listEscalaFrequencia;
	private static List<EscalaCorrente> listEscalaCorrente;
	
	private LoadConfiguration() {
		super();
	}
	
	public static void loadConnection() throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, SQLException {
		ConnectionFactory.setConexao(ConnectionFactory.getConnection());
	}
	
	public static void loadVersao() throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException, ParseException {
		setVersao(new VersaoDAO().queryVersao("1.0"));
	}
	
	public static void loadPericulosidadeFuga() throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		setListPericulosidade(new PericulosidadeFugaDAO().queryPericulosidadeFuga());
	}
	
	public static void loadEscalaSimilaridade() throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		for(int i = 0; i < getListPericulosidade().size(); i++) {
			setListEscalaSimilaridades(new EscalaSimilaridadeDAO().
					queryEscalaSimilaridade(versao, listPericulosidade.get(i)));
		}
	}
	
	public static void loadEscalaFrequencias() throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		setListEscalaFrequencia(new ArrayList<>());
		for(int i = 0; i < listPericulosidade.size(); i++) {
			EscalaFrequencia escalaFrequencia = new EscalaFrequenciasDAO().queryFrequencias(versao, listPericulosidade.get(i));
			getListEscalaFrequencia().add(escalaFrequencia);
		}
	}
	
	public static void loadEscalaCorrente() throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		setListEscalaCorrente(new ArrayList<>());
		for(int i = 0; i < listPericulosidade.size(); i++) {
			EscalaCorrente escalaCorrente = new EscalaCorrenteDAO().queryCorrente(versao, listPericulosidade.get(i));
			getListEscalaCorrente().add(escalaCorrente);
		}
	}
	
	public static void initInstanceDrools() {
		if (getInicializaoDrools() == 0) {
			setKs(KieServices.Factory.get());
			setkContainer(getKs().getKieClasspathContainer());
			setkSession(getkContainer().newKieSession("protegemed"));
			setInicializaoDrools(1);
			//logger.info("Initializing drools instance");
		} else {
			logger.info("Instance ok " + getkSession().getIdentifier());
		}
	}

	public static KieServices getKs() {
		return ks;
	}

	public static void setKs(KieServices ks) {
		LoadConfiguration.ks = ks;
	}

	public static KieContainer getkContainer() {
		return kContainer;
	}

	public static void setkContainer(KieContainer kContainer) {
		LoadConfiguration.kContainer = kContainer;
	}

	public static KieSession getkSession() {
		return kSession;
	}

	public static void setkSession(KieSession kSession) {
		LoadConfiguration.kSession = kSession;
	}

	public static Integer getInicializaoDrools() {
		return inicializaoDrools;
	}

	public static void setInicializaoDrools(Integer inicializaoDrools) {
		LoadConfiguration.inicializaoDrools = inicializaoDrools;
	}

	public static Versao getVersao() {
		return versao;
	}

	public static void setVersao(Versao versao) {
		LoadConfiguration.versao = versao;
	}

	public static List<PericulosidadeFuga> getListPericulosidade() {
		return listPericulosidade;
	}

	public static void setListPericulosidade(List<PericulosidadeFuga> listPericulosidade) {
		LoadConfiguration.listPericulosidade = listPericulosidade;
	}

	public static List<EscalaSimilaridade> getListEscalaSimilaridades() {
		return listEscalaSimilaridades;
	}

	public static void setListEscalaSimilaridades(List<EscalaSimilaridade> listEscalaSimilaridades) {
		LoadConfiguration.listEscalaSimilaridades = listEscalaSimilaridades;
	}

	public static List<EscalaFrequencia> getListEscalaFrequencia() {
		return listEscalaFrequencia;
	}

	public static void setListEscalaFrequencia(List<EscalaFrequencia> listEscalaFrequencia) {
		LoadConfiguration.listEscalaFrequencia = listEscalaFrequencia;
	}

	public static List<EscalaCorrente> getListEscalaCorrente() {
		return listEscalaCorrente;
	}

	public static void setListEscalaCorrente(List<EscalaCorrente> listEscalaCorrente) {
		LoadConfiguration.listEscalaCorrente = listEscalaCorrente;
	}
}
