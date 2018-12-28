package br.upf.protegemed.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.drools.core.common.DefaultFactHandle;
import org.kie.api.runtime.rule.FactHandle;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.ListarEventos;

@Path("events")
public class EventosController {

	final static Logger logger = Logger.getLogger(ProtegemedController.class);

	@GET
	@Path("all-events")
	@Produces(MediaType.APPLICATION_JSON)
	public ListarEventos listAllEvents() {
		Collection<FactHandle> collect = LoadConfiguration.getkSession().getFactHandles();

		ListarEventos listarEventos = new ListarEventos();
		List<CapturaAtual> lista = new ArrayList<>();
		DefaultFactHandle df = null;

		for (FactHandle factHandle : collect) {

			df = (DefaultFactHandle) factHandle;

			if (df.getObjectClassName().equals(CapturaAtual.class.getName()))
				lista.add((CapturaAtual) df.getObject());
		}
		
		if(lista.isEmpty()) {
			listarEventos.setListaCapturaAtual(null);
			listarEventos.setQuantity(0);
		} else {
			listarEventos.setListaCapturaAtual(lista);
			listarEventos.setQuantity(collect.size());
		}
		return listarEventos;
	}
}
