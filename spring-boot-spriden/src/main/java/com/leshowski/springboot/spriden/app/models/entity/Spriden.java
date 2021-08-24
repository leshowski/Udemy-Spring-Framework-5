package com.leshowski.springboot.spriden.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spriden")
public class Spriden {

	@Id
	Integer spriden_pidm;
	String spriden_id;
	String spriden_first_name;
	String spriden_last_name;
	
	public Integer getSpriden_pidm() {
		return spriden_pidm;
	}
	public void setSpriden_pidm(Integer spriden_pidm) {
		this.spriden_pidm = spriden_pidm;
	}
	public String getSpriden_id() {
		return spriden_id;
	}
	public void setSpriden_id(String spriden_id) {
		this.spriden_id = spriden_id;
	}
	public String getSpriden_first_name() {
		return spriden_first_name;
	}
	public void setSpriden_first_name(String spriden_first_name) {
		this.spriden_first_name = spriden_first_name;
	}
	public String getSpriden_last_name() {
		return spriden_last_name;
	}
	public void setSpriden_last_name(String spriden_last_name) {
		this.spriden_last_name = spriden_last_name;
	}
	
	
}
