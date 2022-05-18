package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Vuelo;
import service.VuelosService;
@CrossOrigin("*")
@RestController
public class VuelosController {
	
	@Autowired 
	VuelosService service;
	
	
	@GetMapping(value="Vuelos/{plazasReservadas}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vuelo> vuelos (@PathVariable("plazasReservadas") int plazasReservadas){
		return service.disponibilidadVuelos(plazasReservadas);
	}
	@PutMapping(value="Vuelos/{numeroVuelo}/{plazasReservadas}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String actualizar(@PathVariable("numeroVuelo") int numeroVuelo, 
			@PathVariable("plazasReservadas") int plazasReservadas) {
		return String.valueOf(service.actualizarPlazas(numeroVuelo, plazasReservadas));
	}
}
