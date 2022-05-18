package model;
//esto es un JavaBean 

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pagina {
	private String direccion;
	private String tematica;
	private String descripcion;
	
}
