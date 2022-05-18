package service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import model.Pais;

@Service
public class PaisServiceImpl implements PaisService {
	
	RestTemplate template; //para poder interactuar con el exterior
	
	String urlBase = "https://restcountries.com/v2/all";
	
	/*String url="https://restcountries.com/v2/all";
	Pais[] paises;
	@PostConstruct
	public void init(){ //Te trae el array a memoria nada mas crear el objeto, se ejecuta solo 1 vez. Notacion de Java Enterprise.
	
		paises= template.getForObject(url, Pais[].class);

	}*/
	
	public PaisServiceImpl(@Autowired RestTemplate template) {
		super();
		this.template = template;
	}
	
	@Override
	public List<String> continentes() {
		//recuperamos todos los paises 
		Pais[] paises = template.getForObject(urlBase, Pais[].class); //3 valor para proporcionar a la URL.
		//utilizamos streams para filtrar y transformar en lista
		return Arrays.stream(paises) //stream
				.map(p->p.getContinente()) //transformamos de Pais a String continente
				.distinct() //eliminamos duplicados
				.collect(Collectors.toList()); //List<String>
	}

	@Override
	public List<Pais> paisesPorContinente(String continente) {
		//recuperamos todos los paises 
		Pais[] paises = template.getForObject(urlBase, Pais[].class); //3 valor para proporcionar a la URL.
		return  Arrays.stream(paises) //stream
				.filter(p->p.getContinente().equals(continente)) //filtramos por continente
				.collect(Collectors.toList()); //List<Pais>
	}

	@Override
	public Long poblacionContinente(String continente) {
		//recuperamos todos los paises 
		Pais[] paises = template.getForObject(urlBase, Pais[].class); //3 valor para proporcionar a la URL.
		return Arrays.stream(paises) //stream
				.filter(p->p.getContinente().equals(continente)) //filtramos por continente 
				.mapToLong(p->p.getPoblacion()) //transformamos de Pais a Long poblacion
				.sum(); //sumamos las poblaciones
		
		/*return Arrays.stream(paises)
				.filter(p->p.getContinente().equals(continente))
				.collect(Collectors.summingLong(p->p.getHabitantes()));*/
	}

}
