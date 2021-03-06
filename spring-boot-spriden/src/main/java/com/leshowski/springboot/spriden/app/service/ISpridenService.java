package com.leshowski.springboot.spriden.app.service;

import java.util.List;

import com.leshowski.springboot.spriden.app.models.entity.Sorlcur;
import com.leshowski.springboot.spriden.app.models.entity.Sorlfos;
import com.leshowski.springboot.spriden.app.models.entity.Spriden;


public interface ISpridenService {

	public List<Spriden> findAll();
	public Spriden findOne(String id);
	public List<Sorlfos> listSorlfos(Integer pidm);

}
