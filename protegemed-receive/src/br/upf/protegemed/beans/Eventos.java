package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

@XmlRootElement
@Role(Type.EVENT)
public class Eventos implements Serializable{

	private static final long serialVersionUID = -4346119141473145787L;
	private Integer codEvento;
	private String desc;
	private Integer formaOnda;
	
	public Eventos() {
		super();
	}

	public Eventos(Integer codEvento, String desc, Integer formaOnda) {
		super();
		this.codEvento = codEvento;
		this.desc = desc;
		this.formaOnda = formaOnda;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCodEvento() {
		return codEvento;
	}

	public void setCodEvento(Integer codEvento) {
		this.codEvento = codEvento;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getFormaOnda() {
		return formaOnda;
	}

	public void setFormaOnda(Integer formaOnda) {
		this.formaOnda = formaOnda;
	}
}
