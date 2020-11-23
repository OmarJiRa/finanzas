package pe.edu.upc.paymelater.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.paymelater.models.entities.Cliente;
import pe.edu.upc.paymelater.models.entities.Compra;
import pe.edu.upc.paymelater.models.entities.Tienda;
import pe.edu.upc.paymelater.security.UsuarioDetails;
import pe.edu.upc.paymelater.services.ClienteService;
import pe.edu.upc.paymelater.services.CompraService;
import pe.edu.upc.paymelater.services.TiendaService;


@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private TiendaService tiendaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CompraService compraService;
	
	@GetMapping("view")
	public String view(Model model) {
		Cliente cliente = new Cliente();
		// Sentencias para obtener el Segmento y el Id del Segmento
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails)authentication.getPrincipal();
		
		try {
			Optional<Tienda> optional = tiendaService.findById(usuarioDetails.getIdParalelo());
			if(optional.isPresent()) {
				model.addAttribute("tienda", optional.get());
				model.addAttribute("cliente", cliente);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "clientes/view-clientes";
	}
	
	@PostMapping("save")
	public String save(@ModelAttribute("cliente") Cliente cliente, SessionStatus status ) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails)authentication.getPrincipal();
		try {
			Optional<Tienda> optional = tiendaService.findById(usuarioDetails.getIdParalelo());
			cliente.setTienda(optional.get());
			
			cliente.setLineaCredito(cliente.getIngresoMensual()/10);
			clienteService.save(cliente);
			status.setComplete();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		//Devuelve la URL mapping
		return "redirect:/clientes/view";
	}
	
	@GetMapping("view-{id}")
	public String view(@PathVariable("id") Integer id, Model model) {

		try {
			Optional<Cliente> optional = clienteService.findById(id);
			List<Compra> compras = compraService.findByCliente(optional.get());;
			
			if (optional.isPresent()) {
				model.addAttribute("creador", optional.get());
				model.addAttribute("compras", compras);
				return "clientes/view";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/clientes";
	}
}