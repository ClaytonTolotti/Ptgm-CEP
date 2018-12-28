package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

@XmlRootElement
@Role(Type.EVENT)
public class UsoSalaEquip implements Serializable {

	private static final long serialVersionUID = 210676730110011539L;
	private Equipamento equipamento;
	private UsoSala usoSala;
	public UsoSalaEquip() {
		super();
	}
	public UsoSalaEquip(Equipamento equipamento, UsoSala usoSala) {
		super();
		this.equipamento = equipamento;
		this.usoSala = usoSala;
	}
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	public UsoSala getUsoSala() {
		return usoSala;
	}
	public void setUsoSala(UsoSala usoSala) {
		this.usoSala = usoSala;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
