package pe.edu.upc.paymelater.controllers;

import java.math.BigDecimal;
import java.util.Date;
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
import pe.edu.upc.paymelater.utils.EstadoCompra;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private TiendaService tiendaService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private CompraService compraService;

	public static Date getFechaActual() {
		Date ahora = new Date();
		return ahora;
	}

	public static Date getHoraActual() {
		Date ahora = new Date();
		return ahora;
	}

	@GetMapping("view")
	public String view(Model model) {
		Cliente cliente = new Cliente();
		// Sentencias para obtener el Segmento y el Id del Segmento
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();

		try {
			Optional<Tienda> optional = tiendaService.findById(usuarioDetails.getIdParalelo());
			if (optional.isPresent()) {
				model.addAttribute("tienda", optional.get());
				model.addAttribute("cliente", cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "clientes/view-clientes";
	}

	@PostMapping("save")
	public String save(@ModelAttribute("cliente") Cliente cliente, SessionStatus status) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();
		try {
			Optional<Tienda> optional = tiendaService.findById(usuarioDetails.getIdParalelo());
			cliente.setTienda(optional.get());

			cliente.setLineaCredito(cliente.getIngresoMensual() / 10);
			clienteService.save(cliente);
			status.setComplete();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		// Devuelve la URL mapping
		return "redirect:/clientes/view";
	}

	@GetMapping("view-{id}")
	public String view(@PathVariable("id") Integer id, Model model) {

		Compra compra = new Compra();
		try {
			Optional<Cliente> optional = clienteService.findById(id);
			List<Compra> lista = compraService.findByIdClienteAndEstadoCompra(id, EstadoCompra.PENDIENTE); 
			Double deudaTotal = (double) 0;
			for (int i = 0; i < lista.size(); i++) {

				if (lista.get(i).getEstadoCompra() == EstadoCompra.PENDIENTE) {
					Float monto = (lista.get(i).getPrecioTotal() * (1 + lista.get(i).getCliente().getTasa()
							* (((getFechaActual().getTime() - lista.get(i).getFechaCompra().getTime()) / 86400000))
							/ 360));

					Double nuevoInteres = (double) (monto - lista.get(i).getPrecioTotal());
					Double subtotal = (double) (lista.get(i).getPrecioTotal());
					BigDecimal subt = new BigDecimal(subtotal);
					BigDecimal inte = new BigDecimal(nuevoInteres);
					BigDecimal mont = new BigDecimal(monto);

					lista.get(i).setSubtotal(subt.setScale(2, BigDecimal.ROUND_HALF_UP));
					lista.get(i).setInteres(inte.setScale(2, BigDecimal.ROUND_HALF_UP));
					lista.get(i).setTotal(mont.setScale(2, BigDecimal.ROUND_HALF_UP));

					deudaTotal = deudaTotal + subtotal + nuevoInteres;

				}
				
			}
			
			BigDecimal deudaT = new BigDecimal(deudaTotal);
			if (optional.isPresent()) {
				optional.get().setDeuda(deudaT.setScale(2, BigDecimal.ROUND_HALF_UP));
				model.addAttribute("cliente", optional.get());
				model.addAttribute("compra", compra);
				model.addAttribute("compras", lista);
				return "clientes/view";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/clientes";
	}

	@PostMapping("saveCompra-{id}")
	public String save(@ModelAttribute("compra") Compra compra, @PathVariable("id") Integer id, SessionStatus status) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();
		try {
			Optional<Cliente> cliente = clienteService.findById(id);
			Optional<Tienda> optional = tiendaService.findById(usuarioDetails.getIdParalelo());
			List<Compra> allCompras = compraService.findAll();

			compra.setTienda(optional.get());
			compra.setCliente(cliente.get());
			compra.setEstadoCompra(EstadoCompra.PENDIENTE);
			compra.setFechaCompra(getFechaActual());
			compra.setId(allCompras.size() + 1);
						
			compraService.save(compra);
			status.setComplete();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		// Devuelve la URL mapping
		return "redirect:/clientes/view-{id}";
	}
}