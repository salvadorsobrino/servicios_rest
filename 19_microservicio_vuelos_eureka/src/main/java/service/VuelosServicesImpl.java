package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.VuelosDao;
import model.Vuelo;
@Service
public class VuelosServicesImpl implements VuelosService{
	
	VuelosDao vuelosDao;
	
	public VuelosServicesImpl(@Autowired VuelosDao vuelosDao) {
		super();
		this.vuelosDao = vuelosDao;
	}

	@Override
	public List<Vuelo> disponibilidadVuelos(int plazasReservadas) {
		return vuelosDao.findAll()
				.stream()
				.filter(v->v.getPlazas()>=plazasReservadas && v.getPlazas()!=0)
				.collect(Collectors.toList());
	}

	@Override
	public boolean actualizarPlazas(int numeroVuelo, int plazasReservadas) {
		Vuelo vuelo = vuelosDao.findById(numeroVuelo).get();
		//Otra forma: comprobar con un option si esta present, luego usamos get para obtener vuelo.
		if (vuelo!=null && vuelo.getPlazas()>=plazasReservadas && vuelo.getPlazas()!=0) {
			vuelo.setPlazas(vuelo.getPlazas() - plazasReservadas);
			vuelosDao.save(vuelo);
			return true;
		}
		return false;
	}
}
