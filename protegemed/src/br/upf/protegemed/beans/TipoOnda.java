package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

@XmlRootElement
@Role(Type.EVENT)
public class TipoOnda implements Serializable{

	private static final long serialVersionUID = -823404179050554838L;
	private Integer codTipoOnda;
	private String desc;
	
	public TipoOnda() {
		super();
	}
	public TipoOnda(Integer codTipoOnda, String desc) {
		super();
		this.codTipoOnda = codTipoOnda;
		this.desc = desc;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Integer getCodTipoOnda() {
		return codTipoOnda;
	}
	
	public void setCodTipoOnda(Integer codTipoOnda) {
		this.codTipoOnda = codTipoOnda;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
