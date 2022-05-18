package service;

import java.util.List;

import model.Vuelo;

public interface VuelosService {
	List<Vuelo> disponibilidadVuelos(int plazasReservadas);
	boolean actualizarPlazas(int numeroVuelo, int plazasReservadas);

}
