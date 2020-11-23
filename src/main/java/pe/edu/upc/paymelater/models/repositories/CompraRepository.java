package pe.edu.upc.paymelater.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.paymelater.models.entities.Cliente;
import pe.edu.upc.paymelater.models.entities.Compra;
import pe.edu.upc.paymelater.utils.EstadoCompra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>{
	@Query("select p from Compra p where p.cliente.id = ?1 and p.estadoCompra = ?2")
	List<Compra> findByIdClienteAndEstadoCompra(Integer Id, EstadoCompra estadoCompra) throws Exception;
	List<Compra> findByCliente(Cliente cliente) throws Exception;
}
