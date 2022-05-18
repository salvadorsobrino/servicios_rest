package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

//No hay entity no hay JPA 
public class Pais {
	//nombre,capital,continente,poblacion y bandera
	@JsonAlias(value="name") //Solo deserialización. Si usas JsonProperty no mantiene los nombres de los atributos dados sino los suyos
	private String nombre;

	private String capital;
	
	@JsonAlias(value="region")
	private String continente;
	@JsonAlias(value="population")
	private long poblacion; //tipo de datos long se utiliza para contener el número entero más grande que int no puede contener.
	@JsonAlias(value="flag")
	private String bandera;

}
