package pe.edu.upc.paymelater.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.paymelater.models.entities.Cliente;
import pe.edu.upc.paymelater.models.entities.Compra;
import pe.edu.upc.paymelater.models.repositories.CompraRepository;
import pe.edu.upc.paymelater.services.CompraService;
import pe.edu.upc.paymelater.utils.EstadoCompra;

@Service
public class CompraServiceImpl implements CompraService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CompraRepository compraRepository;
	
	@Transactional
	@Override
	public Compra save(Compra entity) throws Exception {
		return compraRepository.save(entity);
	}

	@Transactional
	@Override
	public Compra update(Compra entity) throws Exception {
		return compraRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		compraRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Compra> findById(Integer id) throws Exception {
		return compraRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Compra> findAll() throws Exception {
		return compraRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Compra> findByIdClienteAndEstadoCompra(Integer Id, EstadoCompra estadoCompra) throws Exception {
		return compraRepository.findByIdClienteAndEstadoCompra(Id, estadoCompra);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Compra> findByCliente(Cliente cliente) throws Exception {
		return compraRepository.findByCliente(cliente);
	}
}