package pe.edu.upc.paymelater.services;

import java.util.List;

import pe.edu.upc.paymelater.models.entities.Cliente;
import pe.edu.upc.paymelater.models.entities.Pago;

public interface PagoService extends CrudService<Pago, Integer>{
	List<Pago> findByCliente (Cliente cliente) throws Exception;
}