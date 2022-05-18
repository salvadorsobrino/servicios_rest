package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dao.ReservasDao;
import model.Reserva;

@Service
public class ReservasServiceImpl implements ReservasService{

	RestTemplate template; //para poder interactuar con el exterior
	
	ReservasDao reservasDao;
	
	String urlBase = "http://localhost:8001/vuelos";
	
	public ReservasServiceImpl(@Autowired ReservasDao reservasDao,@Autowired RestTemplate template) {
		super();
		this.reservasDao = reservasDao;
		this.template = template;
	}
	
	@Override
	public void reserva(Reserva reserva, int plazasReservadas) {
		/* void put(String url, Object request, Map<String,?> uriVariables)
		Creates a new resource by PUTting the given object to URI template.*/
		
		//template.put(urlBase+"/Vuelos/"+reserva.getVuelo()+"/"+plazasReservadas, Vuelo.class);
		//Put es void, no devuelve nada. No sabemos si se hizo.
		//Solucion exchange. Permite realizar cualquier tipo de peticion
		//Le das la url, metodo que vas a usar,cuerpo+cabecera,tipo de la respuesta, variables en url.
		//el resultado es un response entity, tenemos cuerpo + cabecera 
		ResponseEntity<String> response = template.exchange(urlBase+"/Vuelos/{numeroVuelo}/{plazasReservadas}",
				HttpMethod.PUT, //tipo de peticion
				null, //new HttpEntity(dato_cuerpo)
				String.class,
				reserva.getVuelo(),plazasReservadas); //valores de los parametros, evitar concatenaciones
				//no tiene por que llamarse igual que los placeholders del put {numeroVuelo} 
		
		//Solo guardamos la reserva si se ha actualizado el numero de plazas del vuelo
		String cuerpo = response.getBody();
		if(cuerpo.equals("true")) {
			reservasDao.save(reserva);
			
		}
		
		
	}
	@Override
	public List<Reserva> reservasRealizadas() {
		return reservasDao.findAll();
	}
}
