package br.upf.protegemed.beans.escala;

import java.io.Serializable;

public class PericulosidadeFuga implements Serializable {

	private static final long serialVersionUID = -7857962707407479129L;
	private Integer id;
	private String tipo;
	private String descricao;
	
	public PericulosidadeFuga() {
		super();
	}

	public PericulosidadeFuga(Integer id, String tipo, String descricao) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
