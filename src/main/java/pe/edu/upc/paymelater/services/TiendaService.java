package pe.edu.upc.paymelater.services;

import java.util.List;

import pe.edu.upc.paymelater.models.entities.Tienda;

public interface TiendaService extends CrudService<Tienda, Integer>{
	List<Tienda> findByNombreContaining(String nombre) throws Exception;
}