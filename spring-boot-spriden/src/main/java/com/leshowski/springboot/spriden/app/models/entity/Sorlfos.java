package com.leshowski.springboot.spriden.app.models.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "SORLFOS")
public class Sorlfos {
	
	@EmbeddedId
	private SorlfosId sorlfosId;
	
	@Column(name = "SORLFOS_CSTS_CODE")
	private String sorlfosCstsCode;
	
	@Column(name = "SORLFOS_TERM_CODE")
	private String sorlfosTermCode;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="SORLFOS_PIDM",insertable = false,updatable = false)
	@JoinColumn(name="SORLFOS_LCUR_SEQNO",insertable = false,updatable = false)
	private Sorlcur sorlcur;

	public SorlfosId getSorlfosId() {
		return sorlfosId;
	}

	public void setSorlfosId(SorlfosId sorlfosId) {
		this.sorlfosId = sorlfosId;
	}

	public String getSorlfosCstsCode() {
		return sorlfosCstsCode;
	}

	public void setSorlfosCstsCode(String sorlfosCstsCode) {
		this.sorlfosCstsCode = sorlfosCstsCode;
	}

	public String getSorlfosTermCode() {
		return sorlfosTermCode;
	}

	public void setSorlfosTermCode(String sorlfosTermCode) {
		this.sorlfosTermCode = sorlfosTermCode;
	}

	public Sorlcur getSorlcur() {
		return sorlcur;
	}

	public void setSorlcur(Sorlcur sorlcur) {
		this.sorlcur = sorlcur;
	}

}
