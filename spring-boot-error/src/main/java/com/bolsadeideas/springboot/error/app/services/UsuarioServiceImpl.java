package com.bolsadeideas.springboot.error.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.error.app.models.domain.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private List<Usuario> lista;
	
	public UsuarioServiceImpl() {
		this.lista = new ArrayList<>();
		
		this.lista.add(new Usuario(1,"Luis1","Ponce1"));
		this.lista.add(new Usuario(2,"Luis2","Ponce2"));
		this.lista.add(new Usuario(3,"Luis3","Ponce3"));
		this.lista.add(new Usuario(4,"Luis4","Ponce4"));
		this.lista.add(new Usuario(5,"Luis5","Ponce5"));
		this.lista.add(new Usuario(6,"Luis6","Ponce6"));
		this.lista.add(new Usuario(7,"Luis7","Ponce7"));
		
	}
	
	
	@Override
	public List<Usuario> listar() {
		return this.lista;
	}

	@Override
	public Usuario obtenerPorId(Integer id) {
		Usuario resultado = null;
		
		for(Usuario usr:lista) {
			if(usr.getId().equals(id)) {
				resultado = usr;
				break;
			}
		}
		return resultado;
	}

}
