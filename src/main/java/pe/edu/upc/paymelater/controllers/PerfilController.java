package pe.edu.upc.paymelater.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.paymelater.models.entities.Tienda;
import pe.edu.upc.paymelater.security.UsuarioDetails;
import pe.edu.upc.paymelater.services.TiendaService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private TiendaService tiendaService;
	
	@GetMapping()
	public String verPerfil(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails)authentication.getPrincipal();
		
		try {
			Optional<Tienda> optional = tiendaService.findById(usuarioDetails.getIdParalelo());
			if(optional.isPresent()) {
				model.addAttribute("tienda", optional.get());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "perfil/perfil";
	}
	
}
