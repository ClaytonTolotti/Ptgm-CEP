package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

@XmlRootElement
@Role(Type.EVENT)
public class HarmAtual implements Serializable{

	private static final long serialVersionUID = 7389569986793972029L;
	private CapturaAtual capturaAtual;
	private Integer codHarmonica;
	private float sen;
	private float cos;
	
	public HarmAtual() {
		super();
	}

	public HarmAtual(CapturaAtual capturaAtual, Integer codHarmonica, float sen, float cos) {
		super();
		this.capturaAtual = capturaAtual;
		this.codHarmonica = codHarmonica;
		this.sen = sen;
		this.cos = cos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CapturaAtual getCapturaAtual() {
		return capturaAtual;
	}

	public void setCapturaAtual(CapturaAtual capturaAtual) {
		this.capturaAtual = capturaAtual;
	}

	public Integer getCodHarmonica() {
		return codHarmonica;
	}

	public void setCodHarmonica(Integer codHarmonica) {
		this.codHarmonica = codHarmonica;
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
