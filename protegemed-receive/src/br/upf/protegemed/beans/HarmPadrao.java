package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

@XmlRootElement
@Role(Type.EVENT)
public class HarmPadrao implements Serializable{

	private static final long serialVersionUID = -7433375480682564451L;
	private Integer codHarmonica;
	private Integer codOndaPadrao;
	private float sen;
	private float cos;
	
	public HarmPadrao() {
		super();
	}

	public HarmPadrao(Integer codHarmonica, Integer codOndaPadrao, float sen, float cos) {
		super();
		this.codHarmonica = codHarmonica;
		this.codOndaPadrao = codOndaPadrao;
		this.sen = sen;
		this.cos = cos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCodHarmonica() {
		return codHarmonica;
	}

	public void setCodHarmonica(Integer codHarmonica) {
		this.codHarmonica = codHarmonica;
	}

	public Integer getCodOndaPadrao() {
		return codOndaPadrao;
	}

	public void setCodOndaPadrao(Integer codOndaPadrao) {
		this.codOndaPadrao = codOndaPadrao;
	}

	public float getSen() {
		return sen;
	}

	public void setSen(float sen) {
		this.sen = sen;
	}

	public float getCos() {
		return cos;
	}

	public void setCos(float cos) {
		this.cos = cos;
	}
}
