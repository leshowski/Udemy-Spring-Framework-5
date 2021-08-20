package com.bolsadeideas.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.dao.IClienteDaoCR;
import com.bolsadeideas.springboot.app.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	//private IClienteDao clienteDao;
	private IClienteDaoCR clienteDaoCR;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		//return clienteDao.findAll();
		return (List<Cliente>)clienteDaoCR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		//return clienteDao.findOne(id);
		return clienteDaoCR.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void save(Cliente cliente) {
		//clienteDao.save(cliente);
		clienteDaoCR.save(cliente);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		//clienteDao.delete(id);
		clienteDaoCR.deleteById(id);
	}
	
}
