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
import br.upf.protegemed.beans.Evento;

@Path("/events")
public class EventsController {

	final static Logger logger = Logger.getLogger(EventsController.class);

	@GET
	@Path("quantity")
	@Produces(MediaType.APPLICATION_JSON)
	public Evento getQuantityEvents() {

		Collection<FactHandle> collect = LoadConfiguration.getkSession().getFactHandles();

		if (!collect.isEmpty()) {
			return new Evento(collect.size());
		} else {
			return new Evento(0);
		}
	}

	@GET
	@Path("events")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CapturaAtual> listAllEvents() {
		Collection<FactHandle> collect = LoadConfiguration.getkSession().getFactHandles();

		List<CapturaAtual> lista = new ArrayList<>();
		DefaultFactHandle df = null;

		for (FactHandle factHandle : collect) {

			df = (DefaultFactHandle) factHandle;

			if (df.getObjectClassName().equals(CapturaAtual.class.getName()))
				lista.add((CapturaAtual) df.getObject());
		}
		return lista;
	}
}
