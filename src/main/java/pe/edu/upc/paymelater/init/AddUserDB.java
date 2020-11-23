/*package pe.edu.upc.paymelater.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upc.paymelater.models.entities.Usuario;
import pe.edu.upc.paymelater.models.repositories.UsuarioRepository;


@Service
public class AddUserDB implements CommandLineRunner{

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		
		Usuario tienda1 = new Usuario();
		tienda1.setUsername("donpepe@pml.com");
		tienda1.setPassword( bcpe.encode("donpepe123") );
		tienda1.setEnable(true);
			
		Usuario tienda2 = new Usuario();
		tienda2.setUsername("donamaria@pml.com");
		tienda2.setPassword( bcpe.encode("donamaria123") );
		tienda2.setEnable(true);
		
		Usuario tienda3 = new Usuario();
		tienda3.setUsername("donmario@pml.com");
		tienda3.setPassword( bcpe.encode("donmario123") );
		tienda3.setEnable(true);
		
		userRepository.save(tienda1);
		userRepository.save(tienda2);
		userRepository.save(tienda3);

		
	}

}*/
