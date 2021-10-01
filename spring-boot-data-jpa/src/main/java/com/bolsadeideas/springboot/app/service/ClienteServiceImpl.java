package com.bolsadeideas.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IClienteDaoCR;
import com.bolsadeideas.springboot.app.models.dao.IProductoDao;
import com.bolsadeideas.springboot.app.models.dao.IfacturaDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.Producto;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	//private IClienteDao clienteDao;
	private IClienteDaoCR clienteDaoCR;
	
	@Autowired
	//private IClienteDao clienteDao;
	private IProductoDao productoDao;
	
	@Autowired
	private IfacturaDao facturaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		//return clienteDao.findAll();
		return (List<Cliente>)clienteDaoCR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clienteDaoCR.findAll(pageable);
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

	@Override
	@Transactional(readOnly = true) 
	public List<Producto> findByNombre(String term){
		return productoDao.findByNombre(term);
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
	}

	@Override
	@Transactional(readOnly = true) 
	public Producto findProductoById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true) 
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	
}
