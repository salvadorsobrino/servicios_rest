package service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	RestTemplate template; //para poder interactuar con el exterior
	
	String urlBase = "http://servicio-alumnos/crud"; //donde ponia localhost:8000 poder usar una direccion virtual, un alias.
	
	public StudentServiceImpl(@Autowired RestTemplate template) {
		super();
		this.template = template;
	}

	@Override
	public void altaStudent(Student student) {
		template.postForLocation(urlBase+"/Alumno", student); //3 valor para proporcionar a la URL.
		
	}

	@Override
	public List<Student> studentPorPutuacionMinima(double puntuacion) {
		//recuperamos todos los estudiantes 
		Student[] estudiantes = template.getForObject(urlBase+"/Alumnos", Student[].class); //3 valor para proporcionar a la URL.
		//utilizamos streams para filtrar y transformar en lista
		return Arrays.stream(estudiantes) //stream
		.filter(s->s.getPuntuacion()>puntuacion) // filtramos por puntuacion
		.collect(Collectors.toList()); //List<Student>
	}

}
