package com.leshowski.springboot.spriden.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leshowski.springboot.spriden.app.models.dao.ISpridenDao;
import com.leshowski.springboot.spriden.app.models.entity.Sorlfos;
import com.leshowski.springboot.spriden.app.models.entity.Spriden;

@Service
public class SpridenServiceImpl implements ISpridenService{

	@Autowired
	private ISpridenDao iSpridenDao;
	
	@Override
	public List<Spriden> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spriden findOne(String id) {
		return iSpridenDao.findOne(id);
	}
	
	public List<Sorlfos> listSorlfos(Integer pidm){
		return iSpridenDao.listSorlfos(pidm);
	}

}
