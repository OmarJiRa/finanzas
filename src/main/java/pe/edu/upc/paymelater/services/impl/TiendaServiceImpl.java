package pe.edu.upc.paymelater.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.paymelater.models.entities.Tienda;
import pe.edu.upc.paymelater.models.repositories.TiendaRepository;
import pe.edu.upc.paymelater.services.TiendaService;

@Service
public class TiendaServiceImpl implements TiendaService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TiendaRepository tiendaRepository;

	@Transactional
	@Override
	public Tienda save(Tienda entity) throws Exception {
		return tiendaRepository.save(entity);
	}

	@Transactional
	@Override
	public Tienda update(Tienda entity) throws Exception {
		return tiendaRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		tiendaRepository.deleteById(id);

	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Tienda> findById(Integer id) throws Exception {
		return tiendaRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Tienda> findAll() throws Exception {
		return tiendaRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Tienda> findByNombreContaining(String nombre) throws Exception {
		return tiendaRepository.findByNombreContaining(nombre);
	}

}
