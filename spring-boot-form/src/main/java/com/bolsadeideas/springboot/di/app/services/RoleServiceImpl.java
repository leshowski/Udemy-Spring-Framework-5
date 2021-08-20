package com.bolsadeideas.springboot.di.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.di.app.models.domain.Role;

@Service
public class RoleServiceImpl implements RoleService {

	private List<Role> roles;
	
	public RoleServiceImpl() {
		
		this.roles = new ArrayList<Role>();
		roles.add(new Role(1, "Administrador", "ROLE_ADMIN"));
		roles.add(new Role(2, "Usuario", "ROLE_USER"));
		roles.add(new Role(3, "Moderador", "ROLE_MODERATOR"));
	}
	
	@Override
	public List<Role> listar() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public Role obtenerPorId(Integer id) {
		Role resultado = null;
		for(Role role: roles) {
			if(role.getId() == id) {
				resultado = role;
				break;
			}
		}
		return resultado;
	}
}
