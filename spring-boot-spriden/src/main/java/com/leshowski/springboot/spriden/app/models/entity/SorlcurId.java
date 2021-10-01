package com.leshowski.springboot.spriden.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class SorlcurId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "sorlcur_pidm")
	private int sorlcurPidm;
	
	@Column(name = "sorlcur_seqno")
	private int sorlcurSeqno;

	public SorlcurId() {
		
	}

	public int getSorlcurPidm() {
		return sorlcurPidm;
	}

	public void setSorlcurPidm(int sorlcurPidm) {
		this.sorlcurPidm = sorlcurPidm;
	}

	public int getSorlcurSeqno() {
		return sorlcurSeqno;
	}

	public void setSorlcurSeqno(int sorlcurSeqno) {
		this.sorlcurSeqno = sorlcurSeqno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sorlcurPidm;
		result = prime * result + sorlcurSeqno;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof SorlcurId))
			return false;
		SorlcurId other = (SorlcurId) obj;
		if (sorlcurPidm != other.sorlcurPidm)
			return false;
		if (sorlcurSeqno != other.sorlcurSeqno)
			return false;
		return true;
	}
}
