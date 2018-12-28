package br.upf.protegemed.beans;

import java.io.Serializable;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

@XmlRootElement
@Role(Type.EVENT)
public class UsoSala implements Serializable{

	private static final long serialVersionUID = 4900327534704309570L;
	private Integer codUsoSala;
	private SalaCirurgia salaCirurgia;
	private Procedimento procedimento;
	private Responsavel responsavel;
	private Calendar horaInicio;
	private Calendar horaFinal;
	private Integer ativa;
	
	public UsoSala() {
		super();
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UsoSala(Integer codUsoSala, SalaCirurgia salaCirurgia, Procedimento procedimento, Responsavel responsavel,
			Calendar horaInicio, Calendar horaFinal, Integer ativa) {
		super();
		this.codUsoSala = codUsoSala;
		this.salaCirurgia = salaCirurgia;
		this.procedimento = procedimento;
		this.responsavel = responsavel;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		this.ativa = ativa;
	}

	public Integer getCodUsoSala() {
		return codUsoSala;
	}

	public void setCodUsoSala(Integer codUsoSala) {
		this.codUsoSala = codUsoSala;
	}

	public SalaCirurgia getSalaCirurgia() {
		return salaCirurgia;
	}

	public void setSalaCirurgia(SalaCirurgia salaCirurgia) {
		this.salaCirurgia = salaCirurgia;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Calendar getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Calendar horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Calendar getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Calendar horaFinal) {
		this.horaFinal = horaFinal;
	}

	public Integer getAtiva() {
		return ativa;
	}

	public void setAtiva(Integer ativa) {
		this.ativa = ativa;
	}
}
