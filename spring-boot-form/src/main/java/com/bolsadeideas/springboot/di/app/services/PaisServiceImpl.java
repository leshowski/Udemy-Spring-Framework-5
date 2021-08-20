package com.bolsadeideas.springboot.di.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.di.app.models.domain.Pais;

@Service
public class PaisServiceImpl implements PaisService {

	List<Pais> listaPaises;
	
	public PaisServiceImpl() {
		
		this.listaPaises = new ArrayList<Pais>();
		listaPaises.add(new Pais(1,"CL","Chile"));
		listaPaises.add(new Pais(2,"AR","Argentina"));
		listaPaises.add(new Pais(3,"PR","Peru"));
		listaPaises.add(new Pais(4,"BR","Brasil"));
		listaPaises.add(new Pais(5,"EC","Ecuador"));
		listaPaises.add(new Pais(6,"BL","Bolivia"));
		
	}
	
	@Override
	public List<Pais> listar() {
		
		return listaPaises;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		
		Pais paisTmp = null;
		
		for(Pais pais:listaPaises)
			if(pais.getId() == id) {
				paisTmp = pais;
				break;
			}
				
		return paisTmp;
	}

}
