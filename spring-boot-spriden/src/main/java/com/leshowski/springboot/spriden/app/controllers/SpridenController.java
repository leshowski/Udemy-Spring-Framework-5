package com.leshowski.springboot.spriden.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leshowski.springboot.spriden.app.models.entity.Sorlcur;
import com.leshowski.springboot.spriden.app.models.entity.Sorlfos;
import com.leshowski.springboot.spriden.app.models.entity.Spriden;
import com.leshowski.springboot.spriden.app.service.ISpridenService;

@RestController
public class SpridenController {

	@Autowired
	private ISpridenService iSpridenService;
	
	@RequestMapping("/spriden/{id}")
	public Spriden obtener(@PathVariable(value = "id")String id) {
		
		Spriden spridenTmp = iSpridenService.findOne(id);
		
		//spridenTmp.setSorlcurList(iSpridenService.listCurricular(spridenTmp.getSpriden_pidm()));
		
		return spridenTmp;
	}
	
	@RequestMapping("/spriden/{id}/listacampoestudio")
	public Sorlcur listaCampoEstudio(@PathVariable(value = "id")String id){
		
		Spriden spridenTmp = iSpridenService.findOne(id);
		
		List<Sorlfos> listaSorlfos = iSpridenService.listSorlfos(spridenTmp.getSpriden_pidm());
		
		
		return listaSorlfos.get(0).getSorlcur();
	}
	
}
