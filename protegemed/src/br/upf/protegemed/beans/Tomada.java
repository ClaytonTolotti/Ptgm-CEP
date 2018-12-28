package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

@XmlRootElement
@Role(Type.EVENT)
public class Tomada implements Serializable{

	private static final long serialVersionUID = 5309860708642897391L;
	private Integer codTomada;
	private SalaCirurgia salaCirurgia;
	private Integer indice;
	private Integer modulo;
	private String desc;
	
	public Tomada() {
		super();
	}

	public Tomada(Integer codTomada, SalaCirurgia salaCirurgia, Integer indice, Integer modulo, String desc) {
		super();
		this.codTomada = codTomada;
		this.salaCirurgia = salaCirurgia;
		this.indice = indice;
		this.modulo = modulo;
		this.desc = desc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Integer getCodTomada() {
		return codTomada;
	}

	public void setCodTomada(Integer codTomada) {
		this.codTomada = codTomada;
	}

	public SalaCirurgia getSalaCirurgia() {
		return salaCirurgia;
	}

	public void setSalaCirurgia(SalaCirurgia salaCirurgia) {
		this.salaCirurgia = salaCirurgia;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public Integer getModulo() {
		return modulo;
	}

	public void setModulo(Integer modulo) {
		this.modulo = modulo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
