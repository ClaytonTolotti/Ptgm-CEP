package br.upf.protegemed.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

@XmlRootElement
@Role(Type.EVENT)
public class OndaPadrao implements Serializable{

	private static final long serialVersionUID = 5634023677952019721L;
	private Integer codOndaPadrao;
	private TipoPadrao tipoPadrao;
	private TipoOnda tipoOnda;
	private Tomada tomada;
	private Equipamento equipamento;
	private float valorMedio;
	private float offset;
	private float gain;
	private float eficaz;
	private Calendar dataPadrao;
	private Integer codTipoPadrao;
	private List<HarmPadrao> listHarmPadrao;
	
	public OndaPadrao() {
		super();
	}
	
	public Integer getCodOndaPadrao() {
		return codOndaPadrao;
	}

	public void setCodOndaPadrao(Integer codOndaPadrao) {
		this.codOndaPadrao = codOndaPadrao;
	}

	public Calendar getDataPadrao() {
		return dataPadrao;
	}

	public void setDataPadrao(Calendar dataPadrao) {
		this.dataPadrao = dataPadrao;
	}

	public List<HarmPadrao> getListHarmPadrao() {
		return listHarmPadrao;
	}

	public void setListHarmPadrao(List<HarmPadrao> listHarmPadrao) {
		this.listHarmPadrao = listHarmPadrao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TipoPadrao getTipoPadrao() {
		return tipoPadrao;
	}

	public void setTipoPadrao(TipoPadrao tipoPadrao) {
		this.tipoPadrao = tipoPadrao;
	}

	public TipoOnda getTipoOnda() {
		return tipoOnda;
	}

	public void setTipoOnda(TipoOnda tipoOnda) {
		this.tipoOnda = tipoOnda;
	}

	public Tomada getTomada() {
		return tomada;
	}

	public void setTomada(Tomada tomada) {
		this.tomada = tomada;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public float getValorMedio() {
		return valorMedio;
	}

	public void setValorMedio(float valorMedio) {
		this.valorMedio = valorMedio;
	}

	public float getOffset() {
		return offset;
	}

	public void setOffset(float offset) {
		this.offset = offset;
	}

	public float getGain() {
		return gain;
	}

	public void setGain(float gain) {
		this.gain = gain;
	}

	public float getEficaz() {
		return eficaz;
	}

	public void setEficaz(float eficaz) {
		this.eficaz = eficaz;
	}

	public Integer getCodTipoPadrao() {
		return codTipoPadrao;
	}

	public void setCodTipoPadrao(Integer codTipoPadrao) {
		this.codTipoPadrao = codTipoPadrao;
	}
}
