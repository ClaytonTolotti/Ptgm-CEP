package br.upf.protegemed.beans;

import java.io.Serializable;
import java.util.List;

public class Similaridade implements Serializable {

	private static final long serialVersionUID = 7254140251522290346L;
	private Integer id;
	private List<CapturaAtual> capturaAtual;
	private float resultado;
	
	public Similaridade() {
		super();
	}

	public Similaridade(List<CapturaAtual> capturaAtual, float resultado) {
		super();
		this.capturaAtual = capturaAtual;
		this.resultado = resultado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<CapturaAtual> getCapturaAtual() {
		return capturaAtual;
	}

	public void setCapturaAtual(List<CapturaAtual> capturaAtual) {
		this.capturaAtual = capturaAtual;
	}

	public float getResultado() {
		return resultado;
	}

	public void setResultado(float resultado) {
		this.resultado = resultado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
