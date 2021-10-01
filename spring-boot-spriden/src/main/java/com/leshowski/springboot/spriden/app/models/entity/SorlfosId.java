package com.leshowski.springboot.spriden.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class SorlfosId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "sorlfos_pidm")
	private int sorlfosPidm;
	
	@Column(name = "sorlfos_lcur_seqno")
	private int sorlfosLcurSeqno;
	
	@Column(name = "sorlfos_seqno")
	private int sorlfosSeqno;

	public int getSorlfosPidm() {
		return sorlfosPidm;
	}

	public void setSorlfosPidm(int sorlfosPidm) {
		this.sorlfosPidm = sorlfosPidm;
	}

	public int getSorlfosLcurSeqno() {
		return sorlfosLcurSeqno;
	}

	public void setSorlfosLcurSeqno(int sorlfosLcurSeqno) {
		this.sorlfosLcurSeqno = sorlfosLcurSeqno;
	}

	public int getSorlfosSeqno() {
		return sorlfosSeqno;
	}

	public void setSorlfosSeqno(int sorlfosSeqno) {
		this.sorlfosSeqno = sorlfosSeqno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sorlfosLcurSeqno;
		result = prime * result + sorlfosPidm;
		result = prime * result + sorlfosSeqno;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof SorlfosId))
			return false;
		SorlfosId other = (SorlfosId) obj;
		if (sorlfosLcurSeqno != other.sorlfosLcurSeqno)
			return false;
		if (sorlfosPidm != other.sorlfosPidm)
			return false;
		if (sorlfosSeqno != other.sorlfosSeqno)
			return false;
		return true;
	}	
	
}
