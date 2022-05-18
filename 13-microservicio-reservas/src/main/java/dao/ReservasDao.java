package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Reserva;

public interface ReservasDao extends JpaRepository<Reserva, Integer> {
	/*
	 * Expone un recurso que ante una petición de tipo post, que recibe en el cuerpo
	 * de la misma un objeto JSON con el identificador vuelo, identificador hotel,
	 * nombre, dni y total de personas que forman la reserva, registrará la misma en
	 * la base de datos. Interacciona con el servicio de vuelos para actualizar las
	 * plazas disponibles al realizar la reserva (para simplificar el desarrollo de
	 * la aplicación, se considera que las plazas en el hotel son ilimitadas).
	 * 
	 * Dispone de un segundo recurso que en una petición get devuelve todas las
	 * reservas realizadas. Este recurso estará securizado mediante Basic, para que
	 * solo puedan acceder usuarios autenticados que pertenezcan a un determinado
	 * rol.
	 */
}
