package pe.edu.upc.paymelater.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.paymelater.models.entities.Cliente;
import pe.edu.upc.paymelater.models.entities.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
	List<Pago> findByCliente (Cliente cliente) throws Exception;
}
