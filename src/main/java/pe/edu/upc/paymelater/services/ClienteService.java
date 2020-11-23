package pe.edu.upc.paymelater.services;

import java.util.List;

import pe.edu.upc.paymelater.models.entities.Cliente;

public interface ClienteService extends CrudService<Cliente, Integer> {
	List<Cliente> findByNombresApellidosContaining(String nombresApellidos) throws Exception;
}
