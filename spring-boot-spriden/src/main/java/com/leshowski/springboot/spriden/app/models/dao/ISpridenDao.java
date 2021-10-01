package com.leshowski.springboot.spriden.app.models.dao;

import java.util.List;

import com.leshowski.springboot.spriden.app.models.entity.Sorlcur;
import com.leshowski.springboot.spriden.app.models.entity.Sorlfos;
import com.leshowski.springboot.spriden.app.models.entity.Spriden;

public interface ISpridenDao {

	public List<Spriden> findAll();
	public Spriden findOne(String id);
	List<Sorlfos> listSorlfos(Integer pidm);
	
}
