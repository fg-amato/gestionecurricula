package it.gestionecurricula.model;

import java.util.Date;

public class Esperienza {

	private Long id;
	private String descrizione;
	private Date dataInizio;
	private Date dataFine;
	private String conoscenzeAcquisite;
	private Curriculum curriculum;

	public Esperienza() {
		super();
	}

	public Esperienza(String descrizione, Date dataInizio, Date dataFine, String conoscenzeAcquisite,
			Curriculum curriculum) {
		super();
		this.descrizione = descrizione;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.conoscenzeAcquisite = conoscenzeAcquisite;
		this.curriculum = curriculum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getConoscenzeAcquisite() {
		return conoscenzeAcquisite;
	}

	public void setConoscenzeAcquisite(String conoscenzeAcquisite) {
		this.conoscenzeAcquisite = conoscenzeAcquisite;
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	@Override
	public String toString() {
		return "Esperienza [id=" + id + ", descrizione=" + descrizione + ", dataInizio=" + dataInizio + ", dataFine="
				+ dataFine + ", conoscenzeAcquisite=" + conoscenzeAcquisite + ", curriculum=" + curriculum + "]";
	}

}
