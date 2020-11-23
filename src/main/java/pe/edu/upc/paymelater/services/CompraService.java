package pe.edu.upc.paymelater.services;

import java.util.List;

import pe.edu.upc.paymelater.models.entities.Cliente;
import pe.edu.upc.paymelater.models.entities.Compra;
import pe.edu.upc.paymelater.utils.EstadoCompra;

public interface CompraService extends CrudService<Compra, Integer> {
	List<Compra> findByIdClienteAndEstadoCompra(Integer Id, EstadoCompra estadoCompra) throws Exception;
	List<Compra> findByCliente(Cliente cliente) throws Exception;
}
