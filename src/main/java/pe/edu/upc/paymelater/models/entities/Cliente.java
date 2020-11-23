package pe.edu.upc.paymelater.models.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "tienda_id")
	private Tienda tienda;

	@Column(name = "nombres_apellidos", length = 60, nullable = false)
	private String nombresApellidos; // lower cammel case

	@Column(name = "numero_documento", length = 12, nullable = false)
	private String numeroDocumento;

	@Column(name = "direccion", length = 50, nullable = false)
	private String direccion;

	@Column(name = "telefono", length = 15, nullable = false)
	private String telefono;

	@Column(name = "email", length = 30, nullable = false)
	private String email;
	
	@Column(name = "ingreso_mensual")
	private Float ingresoMensual;
	
	@Column(name = "linea_credito")
	private Float lineaCredito;
	
	@Column(name = "tipo_tasa", length = 10)
	private String tipoTasa;
	
	@Column(name = "tasa")
	private Float tasa;
	
	@Column(name = "tipo_mantenimiento", length = 15)
	private String tipoMant;
	
	@Column(name = "mantenimiento")
	private Float mantenimiento;
	
	@Column(name = "moneda", length = 10)
	private String moneda;
	
	@OneToMany(mappedBy = "cliente")
	private List<Compra> compras;
	
	@OneToMany(mappedBy = "cliente")
	private List<Pago> pagos;

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombresApellidos() {
		return nombresApellidos;
	}

	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Float getIngresoMensual() {
		return ingresoMensual;
	}

	public void setIngresoMensual(Float ingresoMensual) {
		this.ingresoMensual = ingresoMensual;
	}

	public Float getLineaCredito() {
		return lineaCredito;
	}

	public void setLineaCredito(Float lineaCredito) {
		this.lineaCredito = lineaCredito;
	}

	public String getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(String tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

	public Float getTasa() {
		return tasa;
	}

	public void setTasa(Float tasa) {
		this.tasa = tasa;
	}

	public String getTipoMant() {
		return tipoMant;
	}

	public void setTipoMant(String tipoMant) {
		this.tipoMant = tipoMant;
	}

	public Float getMantenimiento() {
		return mantenimiento;
	}

	public void setMantenimiento(Float mantenimiento) {
		this.mantenimiento = mantenimiento;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
}
