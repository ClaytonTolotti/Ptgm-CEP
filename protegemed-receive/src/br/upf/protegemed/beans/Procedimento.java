package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

@XmlRootElement
@Role(Type.EVENT)
public class Procedimento implements Serializable{

	private static final long serialVersionUID = -6944025631348308237L;
	private Integer codProced;
	private String desc;
	
	public Procedimento() {
		super();
	}

	public Procedimento(Integer codProced, String desc) {
		super();
		this.codProced = codProced;
		this.desc = desc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCodProced() {
		return codProced;
	}

	public void setCodProced(Integer codProced) {
		this.codProced = codProced;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
