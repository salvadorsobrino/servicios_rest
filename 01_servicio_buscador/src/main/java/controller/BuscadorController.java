package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Pagina;
import service.BuscadorService;

//Controller Rest no hay vistas. En vez de usar @Controller hay que usar @RestController (devuelven datos al cliente no los interpretes como paginas a cargar)
@RestController
public class BuscadorController {
	@Autowired
	BuscadorService service;
	//Aqui no hay vistas, me pides todas las paginas te mando las paginas.
	@GetMapping(value="Paginas", produces = MediaType.APPLICATION_JSON_VALUE) //Direccion para que se ejecute este metodo
	public List<Pagina> paginas(){
		return service.paginas();
	}
	/*@GetMapping(value="Buscador", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pagina>buscador(@RequestParam("tematica")String tematica){
		return service.buscar(tematica);
	}*/
	
	@GetMapping(value="Paginas/{tematica}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pagina>buscador(@PathVariable("tematica")String tematica){
		return service.buscar(tematica);
	}
	@PostMapping(value="Pagina", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alta(@RequestBody Pagina pagina) {
		service.alta(pagina);
	}
	@DeleteMapping(value="Pagina")
	public void eliminar(@RequestParam("tematica") String tematica) {
		service.eliminar(tematica);
	}
	@PutMapping(value="Pagina", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Pagina actualizar(Pagina pagina) {
		return service.actualizar(pagina);
	}
	@PutMapping(value="Pagina")
	public void actualizarTematica(@RequestParam("direccion") String direccion,@RequestParam("tematica") String tematica) {
		service.actualizarTematica(direccion, tematica);
	}
}
