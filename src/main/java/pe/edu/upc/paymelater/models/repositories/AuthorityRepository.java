package pe.edu.upc.paymelater.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.paymelater.models.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	
}