package br.upf.protegemed.beans;

import java.io.Serializable;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

@XmlRootElement
@Role(Type.EVENT)
public class Equipamento implements Serializable{

	private static final long serialVersionUID = -1385384855633098166L;
	private Integer codEquip;
	private Marca marca;
	private Modelo modelo;
	private Tipo tipo;
	private Tomada tomada;
	private String rfid;
	private Integer codPatrimonio;
	private String desc;
	private Calendar dataUltimaFalha;
	private Calendar dataUltimaManutencao;
	private Integer tempoUso;
	
	public Equipamento() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCodEquip() {
		return codEquip;
	}

	public void setCodEquip(Integer codEquip) {
		this.codEquip = codEquip;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public Tomada getTomada() {
		return tomada;
	}

	public void setTomada(Tomada tomada) {
		this.tomada = tomada;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public Integer getCodPatrimonio() {
		return codPatrimonio;
	}

	public void setCodPatrimonio(Integer codPatrimonio) {
		this.codPatrimonio = codPatrimonio;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Calendar getDataUltimaFalha() {
		return dataUltimaFalha;
	}

	public void setDataUltimaFalha(Calendar dataUltimaFalha) {
		this.dataUltimaFalha = dataUltimaFalha;
	}

	public Calendar getDataUltimaManutencao() {
		return dataUltimaManutencao;
	}

	public void setDataUltimaManutencao(Calendar dataUltimaManutencao) {
		this.dataUltimaManutencao = dataUltimaManutencao;
	}

	public Integer getTempoUso() {
		return tempoUso;
	}

	public void setTempoUso(Integer tempoUso) {
		this.tempoUso = tempoUso;
	}
}
