package br.upf.protegemed.beans.escala;

import java.io.Serializable;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Versao;
import br.upf.protegemed.rest.LoadConfiguration;

public class EscalaCorrente implements Serializable{

	private static final long serialVersionUID = -5683544751199682807L;
	private PericulosidadeFuga periculosidadeFuga;
	private Versao versao;
	private float valor;
	
	public EscalaCorrente() {
		super();
	}

	public EscalaCorrente(PericulosidadeFuga periculosidadeFuga, Versao versao, float valor) {
		super();
		this.periculosidadeFuga = periculosidadeFuga;
		this.versao = versao;
		this.valor = valor;
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

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * O método inicia a comparação pelo maior indíce retornado do banco de dados da tabela
	 * ESCALA_CORRENTE, sendo o 3 atualmente como PERIGO. Nunca há comparação com o menor valor
	 * atualmente é 1 - NORMAL default
	 * @param  Recebe a captura atual
	 * @return Retorna a escala da periculosidade atual
	 */
    public static Integer getStatusCorrente(CapturaAtual capturaAtual){        
        Integer sizeList = LoadConfiguration.getListEscalaCorrente().size();
        
    	for(int index = sizeList; index > 1; index--) {
    		if (capturaAtual.getEficaz() >= LoadConfiguration.getListEscalaCorrente().get(index-1).getValor())
	        	return 3;
	        else if (capturaAtual.getEficaz() >= LoadConfiguration.getListEscalaCorrente().get(index-1).getValor())
	        	return 2;
    	}
        return 1;
    }
}
