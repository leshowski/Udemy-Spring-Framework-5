package com.leshowski.springboot.spriden.app.models.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "SORLCUR")
public class Sorlcur {
	
	@EmbeddedId
	private SorlcurId sorlcurId;
	
	@Column(name = "SORLCUR_PROGRAM")
	private String sorlcurProgram;
	
	@Column(name = "SORLCUR_TERM_CODE_ADMIT")
	private String sorlcurTermCodeAdmit;
	
	@Column(name = "SORLCUR_LMOD_CODE")
	private String sorlcurLmodCode;
	
	@Column(name = "SORLCUR_TERM_CODE")
	private String sorlcurTermCode;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="SORLCUR_PIDM",insertable = false,updatable = false)
	private Spriden spriden;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "sorlcur")
	private List<Sorlfos> sorlfosList;

	public SorlcurId getSorlcurId() {
		return sorlcurId;
	}

	public void setSorlcurId(SorlcurId sorlcurId) {
		this.sorlcurId = sorlcurId;
	}

	public String getSorlcurProgram() {
		return sorlcurProgram;
	}

	public void setSorlcurProgram(String sorlcurProgram) {
		this.sorlcurProgram = sorlcurProgram;
	}

	public String getSorlcurTermCodeAdmit() {
		return sorlcurTermCodeAdmit;
	}

	public void setSorlcurTermCodeAdmit(String sorlcurTermCodeAdmit) {
		this.sorlcurTermCodeAdmit = sorlcurTermCodeAdmit;
	}

	public String getSorlcurLmodCode() {
		return sorlcurLmodCode;
	}

	public void setSorlcurLmodCode(String sorlcurLmodCode) {
		this.sorlcurLmodCode = sorlcurLmodCode;
	}

	public Spriden getSpriden() {
		return spriden;
	}

	public void setSpriden(Spriden spriden) {
		this.spriden = spriden;
	}

	public String getSorlcurTermCode() {
		return sorlcurTermCode;
	}

	public void setSorlcurTermCode(String sorlcurTermCode) {
		this.sorlcurTermCode = sorlcurTermCode;
	}

	public List<Sorlfos> getSorlfosList() {
		return sorlfosList;
	}

	public void setSorlfosList(List<Sorlfos> sorlfosList) {
		this.sorlfosList = sorlfosList;
	}
	
}
