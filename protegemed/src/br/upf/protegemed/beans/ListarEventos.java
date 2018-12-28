package br.upf.protegemed.beans;

import java.util.List;

public class ListarEventos {

	private Integer quantity;
	private List<CapturaAtual> listaCapturaAtual; 

	public ListarEventos() {
		super();
	}
	
	public ListarEventos(Integer quantity, List<CapturaAtual> listaCapturaAtual) {
		super();
		this.quantity = quantity;
		this.listaCapturaAtual = listaCapturaAtual;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<CapturaAtual> getListaCapturaAtual() {
		return listaCapturaAtual;
	}

	public void setListaCapturaAtual(List<CapturaAtual> listaCapturaAtual) {
		this.listaCapturaAtual = listaCapturaAtual;
	}
}
