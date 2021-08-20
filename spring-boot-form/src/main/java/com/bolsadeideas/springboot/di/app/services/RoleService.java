package com.bolsadeideas.springboot.di.app.services;

import java.util.List;

import com.bolsadeideas.springboot.di.app.models.domain.Role;

public interface RoleService {
	
	public List<Role> listar();
	public Role obtenerPorId(Integer id);

}
