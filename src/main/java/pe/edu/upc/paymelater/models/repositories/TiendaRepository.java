package pe.edu.upc.paymelater.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.paymelater.models.entities.Tienda;

import java.util.List;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Integer> {
	List<Tienda> findByNombreContaining(String nombre) throws Exception;
}