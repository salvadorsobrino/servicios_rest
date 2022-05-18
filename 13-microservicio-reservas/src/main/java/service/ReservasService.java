package service;

import java.util.List;
import model.Reserva;

public interface ReservasService {
	void reserva(Reserva reserva, int plazasReservadas);
	List<Reserva> reservasRealizadas(); //Este recurso estará securizado mediante Basic
	
}
