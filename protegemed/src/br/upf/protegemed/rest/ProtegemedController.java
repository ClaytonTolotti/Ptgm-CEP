package br.upf.protegemed.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.kie.api.KieServices;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.ParamRequest;
import br.upf.protegemed.beans.SalaCirurgia;
import br.upf.protegemed.beans.Tomada;
import br.upf.protegemed.dao.CapturaAtualDAO;
import br.upf.protegemed.dao.EquipamentoDAO;
import br.upf.protegemed.dao.HarmAtualDAO;
import br.upf.protegemed.dao.SalaCirurgiaDAO;
import br.upf.protegemed.enums.TypesRequests;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.utils.Utils;

@Path("/")
public class ProtegemedController {

	final static Logger logger = Logger.getLogger(ProtegemedController.class);
	
	@GET
	@Path("status")
	public String getStatus() {
		logger.info("Drools ON");
		return "ON";
	}

	@GET
	@Path("init-drools")
	public void getSession() {
		
		if (LoadConfiguration.getkSession() == null) {
			logger.info("Uninitialized drools instance... Initializing");
			LoadConfiguration.setKs(KieServices.Factory.get());
			LoadConfiguration.setkContainer(LoadConfiguration.getKs().getKieClasspathContainer());
			LoadConfiguration.setkSession(LoadConfiguration.getkContainer().newKieSession("protegemed"));
			logger.info("Initializing drools instance");
		} else {
			logger.info("Instance initialized drools");
		}
	}

	@POST//TODO Alterar o path da operação
	@Path("capture.php")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void postReceiveEvent(String c) throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException {
		
		try {
			// Separar os parÃ¢metros recebidos Ex: RFID=000&TYPE=00F
			String[] temp = c.split("&");
			List<HarmAtual> listHarmAtual = new ArrayList<>();
			CapturaAtual capturaAtual = new CapturaAtual();
			Equipamento equipamento;
			Eventos eventos = new Eventos();
			Tomada tomada = new Tomada();
			SalaCirurgia salaCirurgia;
			ParamRequest paramRequest;
			
			String[] arrayCos;
			String[] arraySen;
	
			paramRequest = splitRequest(temp);
			
			capturaAtual.setCodCaptura(new CapturaAtualDAO().getNextval());
			eventos.setCodEvento(Integer.parseInt(paramRequest.getTYPE()));
			tomada.setCodTomada(Integer.parseInt(paramRequest.getOUTLET()));
			salaCirurgia = new SalaCirurgiaDAO().querySalaCirurgia(tomada.getCodTomada());
			equipamento = new EquipamentoDAO().queryCodEquipament(paramRequest.getRFID());
			equipamento.setRfid(paramRequest.getRFID());
			capturaAtual.setOffset(Float.parseFloat(paramRequest.getOFFSET()));
			capturaAtual.setGain(Utils.convertHexToFloat(paramRequest.getGAIN()));
			capturaAtual.setEficaz(Utils.convertHexToFloat(paramRequest.getRMS()));
			capturaAtual.setMv(Utils.convertHexToFloat(paramRequest.getMV()));
			capturaAtual.setMv2(Utils.convertHexToFloat(paramRequest.getMV2()));
			capturaAtual.setUnder(Integer.parseInt(paramRequest.getUNDER()));
			capturaAtual.setOver(Integer.parseInt(paramRequest.getOVER()));
			capturaAtual.setDuracao(Integer.parseInt(paramRequest.getDURATION()));
			
			capturaAtual.setEventos(eventos);
			capturaAtual.setEquipamento(equipamento);
			capturaAtual.setTomada(tomada);
			capturaAtual.setSalaCirurgia(salaCirurgia);

			arraySen = paramRequest.getSIN().split("%");
			arrayCos = paramRequest.getCOS().split("%");
			
			for (int i = 0; i < arrayCos.length; i++) {
				HarmAtual harmAtual = new HarmAtual();
				harmAtual.setCodHarmonica(i);
				harmAtual.setSen(Utils.convertHexToFloat(arraySen[i].replaceAll("\\r\\n", "")));
				harmAtual.setCos(Utils.convertHexToFloat(arrayCos[i].replaceAll("\\r\\n", "")));
				listHarmAtual.add(harmAtual);
			}
	
			capturaAtual.setListHarmAtual(listHarmAtual);
			capturaAtual.setData(Calendar.getInstance());
			
			new CapturaAtualDAO().insertCapturaAtual(capturaAtual);
			new HarmAtualDAO().insertHarmAtual(capturaAtual);
			
			LoadConfiguration.getkSession().insert(capturaAtual);
			LoadConfiguration.getkSession().fireAllRules();
			
		} catch(SQLException pr) {
			throw new ProtegeDAOException(pr.getMessage());
		}
	}
	
	public ParamRequest splitRequest(String[] param) {
		
		String[] objetoTemp = null;
		ParamRequest paramRequest = new ParamRequest();
		
		for (String result : param) {
			// Separar atributos e valores RFID=00000, guardando apenas o valor
			objetoTemp = result.split("=");

			if(objetoTemp[0].equals(TypesRequests.TYPE.getUrl())) {
				paramRequest.setTYPE(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.OUTLET.getUrl())) {
				paramRequest.setOUTLET(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.RFID.getUrl())) {
				paramRequest.setRFID(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.OFFSET.getUrl())) {
				paramRequest.setOFFSET(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.GAIN.getUrl())) {
				paramRequest.setGAIN(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.RMS.getUrl())) {
				paramRequest.setRMS(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.MV.getUrl())) {
				paramRequest.setMV(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.MV2.getUrl())) {
				paramRequest.setMV2(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.UNDER.getUrl())) {
				paramRequest.setUNDER(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.OVER.getUrl())) {
				paramRequest.setOVER(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.DURATION.getUrl())) {
				paramRequest.setDURATION(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.SIN.getUrl())) {
				paramRequest.setSIN(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.COS.getUrl())) {
				paramRequest.setCOS(objetoTemp[1]);
			}
		}
		return paramRequest;
	}
}
