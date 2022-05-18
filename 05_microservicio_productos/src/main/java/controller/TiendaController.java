package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.Producto;
import service.TiendaService;

@CrossOrigin("*")
@RestController 
public class TiendaController {
	@Autowired
	TiendaService ts;
	
	@PostMapping(value="Alta")
	public void insertar(@ModelAttribute Producto p) {
		ts.alta(p);
	}

	@GetMapping(value="Buscador", produces = MediaType.APPLICATION_JSON_VALUE) // Peticion que llega al Controller 
	public @ResponseBody List<Producto> buscar(@RequestParam("seccion") String seccion) {
		return ts.buscar(seccion); 
	}
	
	@DeleteMapping(value="Eliminar")
	public void eliminar(@RequestParam("idProducto") int idProducto){
		ts.eliminar(idProducto);
	}
	
	@GetMapping(value="Productos" , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Producto> productos(){
		return ts.todosProductos();
	}
	
	

}
