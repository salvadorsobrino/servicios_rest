package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="productos")
public class Producto {
	@Id //GeneratedValue No es necesario ya que no es autogenerada
	private int codigoProducto;
	private String producto;
	private double precioUnitario;
	private int stock;
	
	
}
