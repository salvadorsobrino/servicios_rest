package service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dtos.UserPwdDto;
import model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	@Value("${user}")
	String user;
	@Value("${pwd}")
	String pwd;
	
	String token;
	RestTemplate template; //para poder interactuar con el exterior
	
	String urlBase = "http://localhost:8000/crud";
	
	public StudentServiceImpl(@Autowired RestTemplate template) {
		super();
		this.template = template;
	}
	
	private void getToken() {
		//inferencia de tipos. declarar las variables con var, se infiere a partir del new o metodo que llames.
		var dto = new UserPwdDto(user,pwd); 
		token = template.postForObject(urlBase+"/login", dto, String.class);
	}
	
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+token);
		return headers;
	}

	@Override
	public void altaStudent(Student student) {
		if(token==null || token.equals("")) {
			getToken();
		}
		//construimos el encabezado Authorization con el token
		//HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", "Bearer "+token);
		template.exchange(urlBase+"/Alumno",HttpMethod.POST,new HttpEntity<>(student,getHeaders()),Void.class);
		
		//template.postForLocation(urlBase+"/Alumno", student); //3 valor para proporcionar a la URL.
		
	}

	@Override
	public List<Student> studentPorPutuacionMinima(double puntuacion) {
		if(token==null || token.equals("")) {
			getToken();
		}
		//HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", "Bearer "+token);
		//recuperamos todos los estudiantes
		Student[] estudiantes = template.exchange(urlBase+"/Alumnos",
				HttpMethod.GET,
				new HttpEntity<>(getHeaders()),
				Student[].class).getBody(); //OBTENEMOS EL CUERPO
		
		//recuperamos todos los estudiantes 
		//Student[] estudiantes = template.getForObject(urlBase+"/Alumnos", Student[].class); //3 valor para proporcionar a la URL.
		//utilizamos streams para filtrar y transformar en lista
		return Arrays.stream(estudiantes) //stream
		.filter(s->s.getPuntuacion()>puntuacion) // filtramos por puntuacion
		.collect(Collectors.toList()); //List<Student>
	}

}
