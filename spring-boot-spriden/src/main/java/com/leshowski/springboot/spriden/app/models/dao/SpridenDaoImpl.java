package com.leshowski.springboot.spriden.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.leshowski.springboot.spriden.app.models.entity.Spriden;

@Repository("SpridenDaoJPA")
public class SpridenDaoImpl implements ISpridenDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Spriden> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spriden findOne(String id) {
		return (Spriden) em.createQuery("from Spriden where spriden_id = '".concat(id).concat("' and spriden_change_ind is null")).getSingleResult();
	}

}
