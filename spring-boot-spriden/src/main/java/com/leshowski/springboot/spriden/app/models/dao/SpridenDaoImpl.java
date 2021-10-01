package com.leshowski.springboot.spriden.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.leshowski.springboot.spriden.app.models.entity.Sorlcur;
import com.leshowski.springboot.spriden.app.models.entity.Sorlfos;
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
		
		Spriden spriden = (Spriden) em.createQuery("from Spriden where spriden_id = '".concat(id).concat("' and spriden_change_ind is null")).getSingleResult();
		
		return spriden;
	}
	
	@Override
	public List<Sorlfos> listSorlfos(Integer pidm) {
		
		Query query = em.createQuery("select f "
									 + "from Sorlcur e, "
									 + "Sorlfos f "
									 + "where e.sorlcurId.sorlcurPidm = f.sorlfosId.sorlfosPidm "
									 + "and e.sorlcurId.sorlcurSeqno = f.sorlfosId.sorlfosLcurSeqno "
									 + "and f.sorlfosCstsCode = 'INSTACCEPT'"
									 + "and e.sorlcurId.sorlcurPidm = :pidm "
									 + "and e.sorlcurLmodCode = 'ADMISSIONS' "
									 );
		query.setParameter("pidm", pidm);
		
		List<Sorlfos> resultado = query.getResultList();
		
		return resultado;
	}

}
