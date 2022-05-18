package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Vuelo;


public interface VuelosDao extends JpaRepository<Vuelo, Integer>{
	/* Un recurso que devuelve la lista de vuelos disponibles al recibir una petición get. La
	URL incluye el total de plazas que se pretenden reservar.
	Un recurso que al recibir una petición put actualiza los datos del vuelo indicado. Recibe
	en la url el número de vuelo y las plazas reservadas. */
}
