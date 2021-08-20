package com.bolsadeideas.springboot.di.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.di.app.services.PaisService;

@Component
public class PaisPropertyeditor extends PropertyEditorSupport{

	@Autowired
	private PaisService service;
	
	@Override
	public void setAsText(String idString) throws IllegalArgumentException{
		
		if(idString != null && idString.length()>0) {
			
			try {
				Integer id = Integer.parseInt(idString);
				this.setValue(service.obtenerPorId(id));
			}catch(NumberFormatException e) {
				setValue(null);
			}
		}else {
			setValue(null);
		}
	}
}
