package pe.edu.upc.paymelater.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.paymelater.models.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	List<Cliente> findByNombresApellidosContaining(String nombresApellidos) throws Exception;
}
