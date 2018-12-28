package br.upf.protegemed.beans;

public class Evento {

	private Integer quantity;

	public Evento() {
		super();
	}
	
	public Evento(Integer quantity) {
		super();
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
