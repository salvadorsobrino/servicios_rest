package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="pedidos")
public class Pedido {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPedido;
	private int codigoProducto;
	private int unidades;
	private double total;
	//Temporal(TemporalType.DATE) Obtiene solo la fecha, importar javax.persitance y date util. 
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPedido;
	
	
	public Pedido(int codigoProducto, int unidades) {
		super();
		this.codigoProducto = codigoProducto;
		this.unidades = unidades;
	}
	
	
}
