package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Hotel;

public interface HotelesDao extends JpaRepository<Hotel,Integer> {
	/* Expone un recurso que ante una petición get, devuelve la lista de hoteles disponibles.
	Expondrá. Un segundo recurso que permita obtener los datos de un hotel a partir de su
	nombre. */
	Hotel findByNombre(String nombre);

}
