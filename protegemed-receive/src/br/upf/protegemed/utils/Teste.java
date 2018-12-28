package br.upf.protegemed.utils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.ParamRequest;
import br.upf.protegemed.beans.SalaCirurgia;
import br.upf.protegemed.beans.Tomada;
import br.upf.protegemed.dao.CapturaAtualDAO;
import br.upf.protegemed.dao.EquipamentoDAO;
import br.upf.protegemed.dao.SalaCirurgiaDAO;
import br.upf.protegemed.enums.TypesRequests;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;

public class Teste {

	public static void main(String[] args) throws ParseException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		String c = "TYPE=4&OUTLET=3&RFID=FFFF0003&OFFSET=2102&GAIN=283.7&RMS=0.107667&MV=538891&MV2=2105.04&UNDER=0&OVER=0&DURATION=0&SIN=338.98;4618.22;-347.399;989.141;-448.391;-117.156;-317.637;-154.334;-245.477;-231.1;-116.019;-93.7115&COS=-335.414;-1816.74;-185.407;-1804.26;317.234;-84.565;225.395;107.848;133.864;160.108;54.0758;102.271&DATE=2014-06-11;10:46:56";
		postReceiveEvent(c);
	}
	
	public static void postReceiveEvent(String c) throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ParseException {
		
		try {
			//logger.info(" REQUEST: " + c);
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
			String[] arrayData;
			
			paramRequest = splitRequest(temp);
			capturaAtual.setCodCaptura(new CapturaAtualDAO().getNextval());
			eventos.setCodEvento(Integer.parseInt(paramRequest.getTYPE()));
			tomada.setCodTomada(Integer.parseInt(paramRequest.getOUTLET()));
			salaCirurgia = new SalaCirurgiaDAO().querySalaCirurgia(tomada.getCodTomada());
			equipamento = new EquipamentoDAO().queryCodEquipament(paramRequest.getRFID());
			equipamento.setRfid(paramRequest.getRFID());
			capturaAtual.setOffset(Float.parseFloat(paramRequest.getOFFSET()));
			capturaAtual.setGain(Float.parseFloat(paramRequest.getGAIN()));
			capturaAtual.setEficaz(Float.parseFloat(paramRequest.getRMS()));
			capturaAtual.setMv(Float.parseFloat(paramRequest.getMV()));
			capturaAtual.setMv2(Float.parseFloat(paramRequest.getMV2()));
			capturaAtual.setUnder(Integer.parseInt(paramRequest.getUNDER()));
			capturaAtual.setOver(Integer.parseInt(paramRequest.getOVER()));
			capturaAtual.setDuracao(Integer.parseInt(paramRequest.getDURATION()));
			
			capturaAtual.setEventos(eventos);
			capturaAtual.setEquipamento(equipamento);
			capturaAtual.setTomada(tomada);
			capturaAtual.setSalaCirurgia(salaCirurgia);

			arraySen = paramRequest.getSIN().split(";");
			arrayCos = paramRequest.getCOS().split(";");
			arrayData = paramRequest.getDATE().split(";");
			
			for (int i = 0; i < arrayCos.length; i++) {
				HarmAtual harmAtual = new HarmAtual();
				harmAtual.setCodHarmonica(i);
				harmAtual.setSen(Float.parseFloat(arraySen[i].replaceAll("\\r\\n", "")));
				harmAtual.setCos(Float.parseFloat(arrayCos[i].replaceAll("\\r\\n", "")));
				listHarmAtual.add(harmAtual);
			}
	
			capturaAtual.setListHarmAtual(listHarmAtual);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(Utils.MASK_DATA);
			cal.setTime(sdf.parse(new String(arrayData[0] + " " + arrayData[1])));
			capturaAtual.setData(cal);
			new CapturaAtualDAO().insertCapturaAtual(capturaAtual);
//			new HarmAtualDAO().insertHarmAtual(capturaAtual);
			
//			LoadConfiguration.getkSession().insert(capturaAtual);
//			LoadConfiguration.getkSession().fireAllRules();
			
		} catch(SQLException pr) {
			throw new ProtegeDAOException(pr.getMessage());
		}
	}
	
	public static ParamRequest splitRequest(String[] param) {
		
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
			} else if (objetoTemp[0].equals(TypesRequests.DATE.getUrl())) {
				paramRequest.setDATE(objetoTemp[1]);
			}
		}
		return paramRequest;
	}
}
