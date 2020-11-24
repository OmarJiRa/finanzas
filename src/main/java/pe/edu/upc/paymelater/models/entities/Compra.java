package pe.edu.upc.paymelater.models.entities;

import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import pe.edu.upc.paymelater.utils.EstadoCompra;

@Entity
@Table(name = "compras")
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Column(name = "precio_total", nullable = false)
	private Float precioTotal;
	
	@Transient
	private BigDecimal subtotal;
	
	@Transient
	private BigDecimal interes;
	
	@Transient
	private BigDecimal total;
	
	@Column(name = "fecha_compra", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaCompra;
	
	@Column(name = "descripcion", length = 200, nullable = false)
	private String descripcion;
	
	@Column(name = "estado_compra", nullable = false)
	private EstadoCompra estadoCompra;
	
	@OneToMany(mappedBy = "compra")
	private List<Pago> pagos;
	
	@ManyToOne
	@JoinColumn(name = "tienda_id")
	private Tienda tienda;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Float precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public EstadoCompra getEstadoCompra() {
		return estadoCompra;
	}

	public void setEstadoCompra(EstadoCompra estadoCompra) {
		this.estadoCompra = estadoCompra;
	}

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getInteres() {
		return interes;
	}

	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal bigDecimal) {
		this.total = bigDecimal;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
}
