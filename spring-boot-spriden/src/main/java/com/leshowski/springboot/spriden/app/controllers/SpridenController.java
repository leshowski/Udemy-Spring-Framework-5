package com.leshowski.springboot.spriden.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leshowski.springboot.spriden.app.models.entity.Spriden;
import com.leshowski.springboot.spriden.app.service.ISpridenService;

@RestController
public class SpridenController {

	@Autowired
	private ISpridenService iSpridenService;
	
	@RequestMapping("/spriden/{id}")
	public Spriden obtener(@PathVariable(value = "id")String id) {
		return iSpridenService.findOne(id);
	}
}
