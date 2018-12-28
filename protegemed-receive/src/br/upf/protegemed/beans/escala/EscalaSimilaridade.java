package br.upf.protegemed.beans.escala;

import java.io.Serializable;

import br.upf.protegemed.beans.Versao;

public class EscalaSimilaridade implements Serializable{

	private static final long serialVersionUID = -1185274568305195144L;
	private PericulosidadeFuga periculosidadeFuga;
	private Versao versao;
	private float valorMin;
	private float valorMax;
	
	public EscalaSimilaridade() {
		super();
	}

	public EscalaSimilaridade(PericulosidadeFuga periculosidadeFuga, Versao versao, float valorMin, float valorMax) {
		super();
		this.periculosidadeFuga = periculosidadeFuga;
		this.versao = versao;
		this.valorMin = valorMin;
		this.valorMax = valorMax;
	}

	public PericulosidadeFuga getPericulosidadeFuga() {
		return periculosidadeFuga;
	}

	public void setPericulosidadeFuga(PericulosidadeFuga periculosidadeFuga) {
		this.periculosidadeFuga = periculosidadeFuga;
	}

	public Versao getVersao() {
		return versao;
	}

	public void setVersao(Versao versao) {
		this.versao = versao;
	}

	public float getValorMin() {
		return valorMin;
	}

	public void setValorMin(float valorMin) {
		this.valorMin = valorMin;
	}

	public float getValorMax() {
		return valorMax;
	}

	public void setValorMax(float valorMax) {
		this.valorMax = valorMax;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
