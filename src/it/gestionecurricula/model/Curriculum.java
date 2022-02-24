package it.gestionecurricula.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Curriculum {

	private Long id;
	private String nome;
	private String cognome;
	private Date dataDiNascita;
	private String telefono;
	private String email;
	private List<Esperienza> esperienze = new ArrayList<>();

	public Curriculum() {
		super();
	}

	public Curriculum(Long id, String nome, String cognome, Date dataDiNascita, String telefono, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.telefono = telefono;
		this.email = email;
	}

	public Curriculum(Long id, String nome, String cognome, Date dataDiNascita, String telefono, String email,
			List<Esperienza> esperienze) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.telefono = telefono;
		this.email = email;
		this.esperienze = esperienze;
	}

	public List<Esperienza> getEsperienze() {
		return esperienze;
	}

	public void setEsperienze(List<Esperienza> esperienze) {
		this.esperienze = esperienze;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
